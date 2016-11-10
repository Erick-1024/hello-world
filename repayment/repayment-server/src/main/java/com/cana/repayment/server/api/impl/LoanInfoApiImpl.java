package com.cana.repayment.server.api.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.cana.repayment.api.ILoanInfoApi;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample.Criteria;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.repayment.server.validate.LoanInfoBizVerifyRuleDesc;
import com.cana.repayment.server.validate.RepaymentValidator;
import com.cana.repayment.service.IAccountService;
import com.cana.repayment.service.ILoanInfoService;
import com.cana.repayment.service.IRepaymentPlanService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.transaction.ILoanInfoTransactionService;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.repayment.dto.LoanInfoElementsDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisIntegration;
import com.cana.vbam.common.repayment.dto.LoanInfoSearchCriteriaDTO;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.repayment.enums.VerifyStatus;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.dianping.cat.Cat;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;

public class LoanInfoApiImpl implements ILoanInfoApi{

	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private RepaymentValidator validator;
	
	@Resource
	private ILoanInfoTransactionService loanInfoTransactionService;
	
	@Resource
	private ILoanInfoService loanInfoServiceImpl;
	
	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	
	@Resource
	private IRepaymentPlanService repaymentPlanServiceImpl;
	
	@Resource
	private IAccountService accountInteractiveService;
	
	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	private static final Logger LGR = LoggerFactory.getLogger(LoanInfoApiImpl.class);
	
	@Override
	public String generateRecordToRedis(BusinessMode businessMode, InputMethod inputMethod, String operatorId){
		LoanInfoRedisIntegration loanInfoRedisIntegration = new LoanInfoRedisIntegration();
		loanInfoRedisIntegration.setBusinessMode(businessMode);
		loanInfoRedisIntegration.setInputMethod(inputMethod);
		String key = null;
		try {
			key = generateRedisId();
			redisCache.save(RedisUtils.generateLoanInfoRedisKeyByOperator(key, operatorId), loanInfoRedisIntegration, getRedisExpireTime());
			LGR.info("生成redis记录成功");
		} catch (Exception e) {
			LGR.error("数据库异常或配置文件读取失败", e);
			throw WebException.instance("服务器异常");
		}
		return key;
	}
	
	@Override
	public void deleteRecordFromRedis(String key) throws Exception{
		redisCache.delete(key);
	}

	@Override
	public void batchSaveToRedis(String masterId, String key, List<LoanInfoRedisDTO> loanInfoRedisDTOs) throws Exception {
		LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(key);
		loanInfoRedisIntegration.setSuccessedLoanInfoRedisDTOs(new ArrayList<LoanInfoRedisDTO>());
		loanInfoRedisIntegration.setFailedLoanInfoRedisDTOs(new ArrayList<LoanInfoRedisDTO>());
		redisCache.save(key, loanInfoRedisIntegration);
		for (LoanInfoRedisDTO loanInfoRedisDTO : loanInfoRedisDTOs) {
			singleSaveToRedis(masterId, key, loanInfoRedisDTO);
		}
		Iterator<LoanInfoRedisDTO> iterator = loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().iterator();
		while (iterator.hasNext()) {
			LoanInfoRedisDTO successedLoanInfoRedisDTO =  iterator.next();
			if(VerifyStatus.NEGATIVE.name().equals(successedLoanInfoRedisDTO.getVerifyStatus())){
				iterator.remove();
				loanInfoRedisIntegration.addFailedLoanInfoRedisDTO(successedLoanInfoRedisDTO);
			}
		}
	}

	@Override
	public void singleSaveToRedis(String masterId, String key, LoanInfoRedisDTO loanInfoRedisDTO) throws Exception {
		LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(key);
		if(null != loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs()){
			if(loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().contains(loanInfoRedisDTO)){
				loanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.LOANNO_VERIFY);
				loanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
				loanInfoRedisIntegration.addFailedLoanInfoRedisDTO(loanInfoRedisDTO);
				Iterator<LoanInfoRedisDTO> iterator = loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().iterator();
				while (iterator.hasNext()) {
					LoanInfoRedisDTO successedLoanInfoRedisDTO =  iterator.next();
					if(successedLoanInfoRedisDTO.equals(loanInfoRedisDTO)){
						successedLoanInfoRedisDTO.setVerifyFailReason(LoanInfoBizVerifyRuleDesc.LOANNO_VERIFY);
						successedLoanInfoRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
					}
				}
			}else if(validator.ValidateloanInfo(masterId, loanInfoRedisDTO)){
				loanInfoRedisDTO.setVerifyStatus(VerifyStatus.PASS.name());
				trimLoanInfoRedisDTO(loanInfoRedisDTO);
				loanInfoRedisIntegration.addSuccessedLoanInfoRedisDTO(loanInfoRedisDTO);
			}else {
				loanInfoRedisIntegration.addFailedLoanInfoRedisDTO(loanInfoRedisDTO);
			}
		}else if(validator.ValidateloanInfo(masterId, loanInfoRedisDTO)) {
			loanInfoRedisDTO.setVerifyStatus(VerifyStatus.PASS.name());
			trimLoanInfoRedisDTO(loanInfoRedisDTO);
			loanInfoRedisIntegration.addSuccessedLoanInfoRedisDTO(loanInfoRedisDTO);
		}else {
			loanInfoRedisIntegration.addFailedLoanInfoRedisDTO(loanInfoRedisDTO);
		}
		redisCache.save(key, loanInfoRedisIntegration, getRedisExpireTime());
	}
	
	@Override
	public boolean isLoanNoExist(String loanNo, String factorId) {
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andLoanNoEqualTo(loanNo).andFactorIdEqualTo(factorId);
		return loanInfoServiceImpl.isLoanNoExist(example);
	}

	private String generateRedisId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.REDIS_LOAN_INFO_ID, 4);
	}
	
	private int getRedisExpireTime(){
		return Integer.parseInt(TopsConfReader.getConfContent("properties/repayment-common.properties", "redis.temp.timeout", ConfScope.G));
	}

	@Override
	public ListResult<LoanInfoRedisDTO> queryLoanInfoFromRedis(String key, String status, int page, int pageSize) {
		long startTime = System.currentTimeMillis();
		ListResult<LoanInfoRedisDTO> result = new ListResult<LoanInfoRedisDTO>();
		if("success".equals(status)){
			try {
				LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(key);
				List<LoanInfoRedisDTO> loanInfoRedisDTOs = new ArrayList<>();
				if(null != loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs()){
					loanInfoRedisDTOs = loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs()));
					result.setTotalNum(loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().size());
				}
				result.setData(loanInfoRedisDTOs);
				result.setStatus(AjaxResponseStatus.SUCCESS);
				Cat.logMetricForDuration("query_loan_info_from_redis", System.currentTimeMillis()-startTime);
				LGR.info("查询redis成功");
			} catch (Exception e) {
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage("查询失败");
				LGR.error("查询redis失败", e);
			}
		}else {
			try {
				LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(key);
				List<LoanInfoRedisDTO> loanInfoRedisDTOs = new ArrayList<>();
				if(null != loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs()){
					loanInfoRedisDTOs = loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs().subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs()));
					result.setTotalNum(loanInfoRedisIntegration.getFailedLoanInfoRedisDTOs().size());
				}
				result.setData(loanInfoRedisDTOs);
				result.setStatus(AjaxResponseStatus.SUCCESS);
				Cat.logMetricForDuration("query_loan_info_from_redis", System.currentTimeMillis()-startTime);
				LGR.info("查询redis成功");
			} catch (Exception e) {
				result.setStatus(AjaxResponseStatus.FAILED);
				result.setMessage("查询失败");
				LGR.error("查询redis失败", e);
			}
		}
		return result;
	}
	
	private int getStartIndex(int page, int pageSize){
		return (page - 1) * pageSize;
	}
	
	private int getEndIndex(int page, int pageSize, List<?> curList) {
		return page * pageSize < curList.size() ? page * pageSize : curList.size();
	}

	@Override
	public LoanInfoRedisIntegration queryLoanInfoRedisIntegrationFromRedis(String key) throws Exception {
		return (LoanInfoRedisIntegration)redisCache.read(key);
	}

	@Override
	public LoanInfoRedisDTO queryOneLoanInfoFromRedis(String key, String loanNo) throws Exception {
		long startTime = System.currentTimeMillis();
		LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(key);
		LoanInfoRedisDTO loanInfoRedisDTOTemp = new LoanInfoRedisDTO();
		loanInfoRedisDTOTemp.setLoanNo(loanNo);
		LoanInfoRedisDTO successdloanInfoRedisDTO = loanInfoRedisIntegration.querySuccessedLoanInfoRedisDTO(loanInfoRedisDTOTemp);
		Cat.logMetricForDuration("query_single_loan_info_from_redis", System.currentTimeMillis()-startTime);
		if(null != successdloanInfoRedisDTO)
			return successdloanInfoRedisDTO;
		return loanInfoRedisIntegration.queryFailedLoanInfoRedisDTO(loanInfoRedisDTOTemp);
	}

	@Override
	public void deleteLoanInfoFromRedis(String key, String loanNo) throws Exception {
		LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(key);
		LoanInfoRedisDTO loanInfoRedisDTOTemp = new LoanInfoRedisDTO();
		loanInfoRedisDTOTemp.setLoanNo(loanNo);
		loanInfoRedisIntegration.getSuccessedLoanInfoRedisDTOs().remove(loanInfoRedisDTOTemp);
		redisCache.save(key, loanInfoRedisIntegration, getRedisExpireTime());
	}

	@Override
	public void modifyLoanInfoFromRedis(String masterId, String key, LoanInfoRedisDTO loanInfoRedisDTO) throws Exception {
		LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(key);
		if(validator.ValidateloanInfo(masterId, loanInfoRedisDTO)){
			trimLoanInfoRedisDTO(loanInfoRedisDTO);
			loanInfoRedisIntegration.modifySuccessedLoanInfoRedisDTO(loanInfoRedisDTO);
		}
		redisCache.save(key, loanInfoRedisIntegration, getRedisExpireTime());
	}

	@Override
	public ListResult<LoanInfoRedisDTO> queryLoanInfoListFromDB(String masterId, LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO) throws Exception {
		long startTime = System.currentTimeMillis();
		ListResult<LoanInfoRedisDTO> result = new ListResult<LoanInfoRedisDTO>();
		RepaymentLoanInfoExample loanInfoExample = convertLoanInfoSearchCriteriaDTOToRepaymentLoanInfoExample(masterId, loanInfoSearchCriteriaDTO);
		List<RepaymentLoanInfo> repaymentLoanInfos = loanInfoServiceImpl.queryLoanInfoListFromDB(loanInfoExample);
		List<RepaymentLoanInfo> repaymentLoanInfostemp = new ArrayList<>();
		if(CollectionUtils.isEmpty(repaymentLoanInfos)){
			LGR.info("放款信息为空");
			result.setTotalNum(0);
			result.setData(null);
			return result;
		}
		for(RepaymentLoanInfo loanInfo : repaymentLoanInfos){
			RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
			repaymentPlanExample.createCriteria().andLoanInfoIdEqualTo(loanInfo.getId());
			int count = repaymentPlanServiceImpl.queryRepaymentPlanCount(repaymentPlanExample);
//			if(count != (int)loanInfo.getRepaymentPeriod()){
			if(count == 0){
				repaymentLoanInfostemp.add(loanInfo);
			}
		}
		int startIndex = (loanInfoSearchCriteriaDTO.getPage()-1) * loanInfoSearchCriteriaDTO.getPageSize();
		int endIndex = loanInfoSearchCriteriaDTO.getPage() * loanInfoSearchCriteriaDTO.getPageSize();
		result.setTotalNum(repaymentLoanInfostemp.size());
		List<RepaymentLoanInfo> loanInfoData = repaymentLoanInfostemp.subList(startIndex, endIndex > repaymentLoanInfostemp.size() ? repaymentLoanInfostemp.size() : endIndex);
		result.setData(convertRepaymentLoanInfoToLoanInfoRedisDTO(loanInfoData));
		Cat.logMetricForDuration("query_loan_info_from_DB", System.currentTimeMillis()-startTime);
		return result;
	}
	
	@Override
	public ListResult<LoanInfoRedisDTO> queryLoanInfoListByAccountSupervisionId(int page, int pageSize,
			String accountSupervisionId){
		long startTime = System.currentTimeMillis();
		ListResult<LoanInfoRedisDTO> result = new ListResult<LoanInfoRedisDTO>();
		RepaymentLoanInfoExample loanInfoExample = new RepaymentLoanInfoExample();
		loanInfoExample.setLimitStart((page-1) * pageSize);
		loanInfoExample.setLimitEnd(pageSize);
		loanInfoExample.setOrderByClause("-id");
		loanInfoExample.createCriteria().andAccountSupervisionIdEqualTo(accountSupervisionId);
		List<RepaymentLoanInfo> repaymentLoanInfos = loanInfoServiceImpl.queryLoanInfoListFromDB(loanInfoExample);
		result.setData(convertRepaymentLoanInfoToLoanInfoRedisDTO(repaymentLoanInfos));
		result.setTotalNum(loanInfoServiceImpl.queryLoanInfoCountFromDB(loanInfoExample));
		Cat.logMetricForDuration("query_loan_info_by_account_supervision_id", System.currentTimeMillis()-startTime);
		return result;
	}

	@Override
	public Long queryTotalFinanceBalance(String accountSupervisionId) {
		RepaymentLoanInfoExample loanInfoExample = new RepaymentLoanInfoExample();
		loanInfoExample.createCriteria().andAccountSupervisionIdEqualTo(accountSupervisionId);
		List<RepaymentLoanInfo> repaymentLoanInfos = loanInfoServiceImpl.queryLoanInfoListFromDB(loanInfoExample);
		return CalculateTotalFinanceBalance(repaymentLoanInfos);
	}

	@Override
	public LoanInfoRedisDTO queryLoanInfodetailFromDB(String id) throws Exception {
		long startTime = System.currentTimeMillis();
		RepaymentLoanInfo repaymentLoanInfo = loanInfoServiceImpl.queryLoanInfodetailFromDB(id);
		LoanInfoRedisDTO loanInfoRedisDTO = new LoanInfoRedisDTO();
		BeanUtils.copyProperties(repaymentLoanInfo, loanInfoRedisDTO);
		loanInfoRedisDTO.setRepaymentMethod(RepaymentType.valueOf(loanInfoRedisDTO.getRepaymentMethod()).desc());
		loanInfoRedisDTO.setReceivablesAmount(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getReceivablesAmount()));
		loanInfoRedisDTO.setReceivablesBalance(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getReceivablesBalance()));
		loanInfoRedisDTO.setFinanceAmount(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getFinanceAmount()));
		loanInfoRedisDTO.setFinanceBalance(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getFinanceBalance()));
		loanInfoRedisDTO.setInterestRate(MoneyArithUtil.convertInterestRateToString(repaymentLoanInfo.getInterestRate()));
		loanInfoRedisDTO.setRepaymentPeriod(repaymentLoanInfo.getRepaymentPeriod()==null?"":repaymentLoanInfo.getRepaymentPeriod().toString());
		Cat.logMetricForDuration("query_loan_info_detail", System.currentTimeMillis()-startTime);
		return loanInfoRedisDTO;
	}

	private RepaymentLoanInfoExample convertLoanInfoSearchCriteriaDTOToRepaymentLoanInfoExample(String masterId, LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO) {
		RepaymentLoanInfoExample loanInfoExample = new RepaymentLoanInfoExample();
//		loanInfoExample.setLimitStart((loanInfoSearchCriteriaDTO.getPage()-1) * loanInfoSearchCriteriaDTO.getPageSize());
//		loanInfoExample.setLimitEnd(loanInfoSearchCriteriaDTO.getPageSize());
		loanInfoExample.setOrderByClause("-id");
		Criteria criteria = loanInfoExample.createCriteria();
		criteria.andFactorIdEqualTo(masterId);
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getFinanceCompany())){
			criteria.andFinanceCompanyLike("%" + loanInfoSearchCriteriaDTO.getFinanceCompany() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getCoreCompanyName())){
			criteria.andCoreCompanyNameLike("%" + loanInfoSearchCriteriaDTO.getCoreCompanyName() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getBusinessContractNo())){
			criteria.andBusinessContractNoLike("%" + loanInfoSearchCriteriaDTO.getBusinessContractNo() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getBusinessProduct())){
			criteria.andBusinessProductLike("%" + loanInfoSearchCriteriaDTO.getBusinessProduct() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getLoanStartDate())){
			criteria.andLoanDateGreaterThanOrEqualTo(loanInfoSearchCriteriaDTO.getLoanStartDate());
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getLoanEndDate())){
			criteria.andLoanDateLessThanOrEqualTo(loanInfoSearchCriteriaDTO.getLoanEndDate());
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getDueStartDate())){
			criteria.andDueDateGreaterThanOrEqualTo(loanInfoSearchCriteriaDTO.getDueStartDate());
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getDueEndDate())){
			criteria.andDueDateLessThanOrEqualTo(loanInfoSearchCriteriaDTO.getDueEndDate());
		}
		return loanInfoExample;
	}
	
	private List<LoanInfoRedisDTO> convertRepaymentLoanInfoToLoanInfoRedisDTO(List<RepaymentLoanInfo> repaymentLoanInfos){
		List<LoanInfoRedisDTO> loanInfoRedisDTOs = new ArrayList<LoanInfoRedisDTO>();
		for (RepaymentLoanInfo repaymentLoanInfo : repaymentLoanInfos) {
			RepaymentLoanInfoBO repaymentLoanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
			LoanInfoRedisDTO loanInfoRedisDTO = new LoanInfoRedisDTO();
			BeanUtils.copyProperties(repaymentLoanInfo, loanInfoRedisDTO);
			loanInfoRedisDTO.setRepaymentMethod(RepaymentType.valueOf(repaymentLoanInfo.getRepaymentMethod()).desc());
			loanInfoRedisDTO.setReceivablesAmount(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getReceivablesAmount()));
			loanInfoRedisDTO.setReceivablesBalance(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getReceivablesBalance()));
			loanInfoRedisDTO.setFinanceAmount(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getFinanceAmount()));
			loanInfoRedisDTO.setFinanceBalance(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getFinanceBalance()));
			loanInfoRedisDTO.setInterestRate(MoneyArithUtil.convertInterestRateToString(repaymentLoanInfo.getInterestRate()));
			loanInfoRedisDTO.setRepaymentPeriod(repaymentLoanInfo.getRepaymentPeriod()==null?"":repaymentLoanInfo.getRepaymentPeriod().toString());
			loanInfoRedisDTO.setInterestRateUnit(StringUtils.isBlank(repaymentLoanInfo.getInterestRateUnit())?"":InterestRateUnit.valueOf(repaymentLoanInfo.getInterestRateUnit()).desc());
			loanInfoRedisDTO.setSettleStatus(StringUtils.isBlank(repaymentLoanInfo.getSettleStatus())?"":SettleStatus.valueOf(repaymentLoanInfo.getSettleStatus()).desc());
			loanInfoRedisDTO.setAccessToAdjustAndOfflineRepayment(repaymentLoanInfoBO.containNonAutoRepaymentPlans());
			loanInfoRedisDTOs.add(loanInfoRedisDTO);
		}
		return loanInfoRedisDTOs;
	}
	
	private Long CalculateTotalFinanceBalance(List<RepaymentLoanInfo> repaymentLoanInfos) {
		long totalFinanceBalance = 0L;
		for (RepaymentLoanInfo repaymentLoanInfo : repaymentLoanInfos) {
			totalFinanceBalance += repaymentLoanInfo.getFinanceBalance();
		}
		return totalFinanceBalance;
	}

	@Override
	public void saveLoanInfoListToDB(LoanInfoRedisIntegration loanInfoRedisIntegration, String factorId) throws Exception
	{
//		loanInfoTransactionService.saveLoanInfoListToDB(loanInfoRedisIntegration, factorId);
		loanInfoServiceImpl.convertLoanInfoList(loanInfoRedisIntegration, factorId);
	}

	@Override
	public List<AccountDTO> queryRepaymentAccounts(String factorId, String finaceName){
		return accountInteractiveService.queryRepaymentAccounts(factorId, finaceName);
	}
	
	@Override
	public AccountDTO getDefaultAccount(String factorId, String finaceName)
	{
		// TODO Auto-generated method stub
		return accountInteractiveService.getDefaultAccount(factorId, finaceName);
	}

	@Override
	public void updateLoanInfoAccountNo(String redisKey, LoanInfoRedisDTO loanInfoRedisDTO) throws Exception
	{
		LoanInfoRedisIntegration loanInfoRedisIntegration = (LoanInfoRedisIntegration)redisCache.read(redisKey);
		LoanInfoRedisDTO oldLoanInfoRedisDTO = loanInfoRedisIntegration.querySuccessedLoanInfoRedisDTO(loanInfoRedisDTO);
		if(null != oldLoanInfoRedisDTO){
			oldLoanInfoRedisDTO.setAccountSupervisionId(loanInfoRedisDTO.getAccountSupervisionId());
//			oldLoanInfoRedisDTO.setAccountId(loanInfoRedisDTO.getAccountId());
			oldLoanInfoRedisDTO.setAccountNo(loanInfoRedisDTO.getAccountNo());
			loanInfoRedisIntegration.modifySuccessedLoanInfoRedisDTO(oldLoanInfoRedisDTO);
		}
		redisCache.save(redisKey, loanInfoRedisIntegration, getRedisExpireTime());
		
	}

	@Override
	public ListResult<LoanInfoRedisDTO> queryLoanInfoList(String masterId,LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO) throws Exception
	{
		long startTime = System.currentTimeMillis();
		ListResult<LoanInfoRedisDTO> result = new ListResult<LoanInfoRedisDTO>();
		RepaymentLoanInfoExample loanInfoExample = setQueryLoanInfoListConditions(masterId, loanInfoSearchCriteriaDTO);
		List<RepaymentLoanInfo> repaymentLoanInfos = loanInfoServiceImpl.queryLoanInfoListFromDB(loanInfoExample);
		result.setData(convertRepaymentLoanInfoToLoanInfoRedisDTO(repaymentLoanInfos));
		result.setTotalNum(loanInfoServiceImpl.queryLoanInfoCountFromDB(loanInfoExample));
		Cat.logMetricForDuration("query_loan_info_from_redis", System.currentTimeMillis()-startTime);
		return result;
	}
	
	/**
	 * 将放款信息转换为放款要素
	 * @param repaymentLoanInfos
	 * @return
	 */
	private LoanInfoElementsDTO convertRepaymentLoanInfoToLoanInfoElementsDTO(RepaymentLoanInfo repaymentLoanInfo)throws Exception
	{
		LoanInfoElementsDTO loanInfoElementsDTO = new LoanInfoElementsDTO();
		BeanUtils.copyProperties(repaymentLoanInfo, loanInfoElementsDTO);
		loanInfoElementsDTO.setRepaymentMethod(RepaymentType.valueOf(repaymentLoanInfo.getRepaymentMethod()).desc());
		loanInfoElementsDTO.setReceivablesAmount(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getReceivablesAmount()));
		loanInfoElementsDTO.setReceivablesBalance(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getReceivablesBalance()));
		loanInfoElementsDTO.setFinanceAmount(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getFinanceAmount())));
		loanInfoElementsDTO.setFinanceBalance(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentLoanInfo.getFinanceBalance())));
		loanInfoElementsDTO.setInterestRate(MoneyArithUtil.convertInterestRateToString(repaymentLoanInfo.getInterestRate()));
		loanInfoElementsDTO.setRepaymentPeriod(repaymentLoanInfo.getRepaymentPeriod()==null?"":repaymentLoanInfo.getRepaymentPeriod().toString());
		loanInfoElementsDTO.setInterestRateUnit(StringUtils.isBlank(repaymentLoanInfo.getInterestRateUnit())?"":InterestRateUnit.valueOf(repaymentLoanInfo.getInterestRateUnit()).desc());
//		loanInfoElementsDTO.setAccountNo(AccountNoUtil.formatBankAccountNo(repaymentLoanInfo.getAccountNo()==null?"":repaymentLoanInfo.getAccountNo()));
		loanInfoElementsDTO.setAccountNo(AccountNoUtil.formatBankAccountNo(repaymentLoanInfo.getAccountNo()));
		
		//添加放款要素必要字段
		LoanInfoConfig loanInfoConfig = loanInfoServiceImpl.queryLoanInfoConfigFromDB(repaymentLoanInfo.getId());
		loanInfoElementsDTO.setExtensionRatio(MoneyArithUtil.convertInterestRateToString(loanInfoConfig.getExtensionRatio()));
		loanInfoElementsDTO.setEarlyRepaymentChargeRatio(MoneyArithUtil.convertInterestRateToString(loanInfoConfig.getEarlyRepaymentChargeRatio()));
		loanInfoElementsDTO.setPenaltyRate(MoneyArithUtil.convertInterestRateToString(loanInfoConfig.getPenaltyRate()));
		return loanInfoElementsDTO;
	}
	
	/**
	 * 设置查询款信息列表的查询条件
	 * @param masterId
	 * @param loanInfoSearchCriteriaDTO
	 * @return
	 */
	private RepaymentLoanInfoExample setQueryLoanInfoListConditions(String masterId, LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO) {
		RepaymentLoanInfoExample loanInfoExample = new RepaymentLoanInfoExample();
		loanInfoExample.setLimitStart((loanInfoSearchCriteriaDTO.getPage()-1) * loanInfoSearchCriteriaDTO.getPageSize());
		loanInfoExample.setLimitEnd(loanInfoSearchCriteriaDTO.getPageSize());
		loanInfoExample.setOrderByClause("-id");
		Criteria criteria = loanInfoExample.createCriteria();
	
		//根据用户类型
		if(UserType.FACTOR == loanInfoSearchCriteriaDTO.getUserType())
		{
			criteria.andFactorIdEqualTo(masterId);
		}
		if(UserType.FINACE == loanInfoSearchCriteriaDTO.getUserType())
		{
			criteria.andFinanceIdEqualTo(masterId);
		}
		if(UserType.CORECOMPANY == loanInfoSearchCriteriaDTO.getUserType())
		{
			criteria.andCoreCompanyIdEqualTo(masterId);
		}
		//根据还款帐号有无
		if("EXIST".equals(loanInfoSearchCriteriaDTO.getAccountNoStatus()))
		{
			criteria.andAccountNoIsNotNull();
		}
		if("NOTEXIST".equals(loanInfoSearchCriteriaDTO.getAccountNoStatus()))
		{
			criteria.andAccountNoIsNull();
		}
		
		if(StringUtils.isNoneBlank(loanInfoSearchCriteriaDTO.getFactorCompany()))
		{
			criteria.andFactorCompanyLike("%" + loanInfoSearchCriteriaDTO.getFactorCompany() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getFinanceCompany())){
			criteria.andFinanceCompanyLike("%" + loanInfoSearchCriteriaDTO.getFinanceCompany() + "%");
		}
		if (StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getOutCustomerName())) {
			criteria.andOutCustomerNameLike("%" + loanInfoSearchCriteriaDTO.getOutCustomerName() + "%");
		}
		if(StringUtils.isNoneBlank(loanInfoSearchCriteriaDTO.getCoreCompanyName())){
			criteria.andCoreCompanyNameLike("%" + loanInfoSearchCriteriaDTO.getCoreCompanyName() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getBusinessContractNo())){
			criteria.andBusinessContractNoLike("%" + loanInfoSearchCriteriaDTO.getBusinessContractNo() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getBusinessProduct())){
			criteria.andBusinessProductLike("%" + loanInfoSearchCriteriaDTO.getBusinessProduct() + "%");
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getLoanStartDate())){
			criteria.andLoanDateGreaterThanOrEqualTo(loanInfoSearchCriteriaDTO.getLoanStartDate());
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getLoanEndDate())){
			criteria.andLoanDateLessThanOrEqualTo(loanInfoSearchCriteriaDTO.getLoanEndDate());
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getDueStartDate())){
			criteria.andDueDateGreaterThanOrEqualTo(loanInfoSearchCriteriaDTO.getDueStartDate());
		}
		if(StringUtils.isNotEmpty(loanInfoSearchCriteriaDTO.getDueEndDate())){
			criteria.andDueDateLessThanOrEqualTo(loanInfoSearchCriteriaDTO.getDueEndDate());
		}
		if(StringUtils.isNotBlank(loanInfoSearchCriteriaDTO.getSettleStatus())){
			criteria.andSettleStatusEqualTo(loanInfoSearchCriteriaDTO.getSettleStatus());
		}
		if(StringUtils.isNotBlank(loanInfoSearchCriteriaDTO.getLoanId())){
			criteria.andIdEqualTo(loanInfoSearchCriteriaDTO.getLoanId());
		}
		return loanInfoExample;
	}

	@Override
	public LoanInfoElementsDTO getLoanInfoElements(String loanId) throws Exception
	{
		RepaymentLoanInfo repaymentLoanInfo = loanInfoServiceImpl.queryLoanInfodetailFromDB(loanId);
		return convertRepaymentLoanInfoToLoanInfoElementsDTO(repaymentLoanInfo);
	}

	@Override
	public void updateAccountNoToDB(LoanInfoRedisDTO loanInfoRedisDTO) throws Exception
	{
		loanInfoTransactionService.updateAccountNoToDB(loanInfoRedisDTO);
	}

	private void trimLoanInfoRedisDTO(LoanInfoRedisDTO loanInfoRedisDTO) {
		StringUtil.trimObjectFields(loanInfoRedisDTO);
		loanInfoRedisDTO.setAccountNo("".equals(loanInfoRedisDTO.getAccountNo()) ? null : loanInfoRedisDTO.getAccountNo());
		loanInfoRedisDTO.setAccountSupervisionId("".equals(loanInfoRedisDTO.getAccountSupervisionId()) ? null : loanInfoRedisDTO.getAccountSupervisionId());
	}

	@Override
	public boolean accessToActiveRepayment(String loanInfoId) {
		RepaymentLoanInfoBO repaymentLoanInfoBO = new RepaymentLoanInfoBO(loanInfoId);
		return repaymentLoanInfoBO.containNonAutoRepaymentPlans();
	}
}

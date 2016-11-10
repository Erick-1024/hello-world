package com.cana.repayment.server.api.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.repayment.api.IRepaymentPlanApi;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleDistributeDetailMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.repayment.dao.po.RepaymentPlanExample.Criteria;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleCollectExample;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetailExample;
import com.cana.repayment.server.validate.RepaymentValidator;
import com.cana.repayment.service.ILoanInfoService;
import com.cana.repayment.service.IRepaymentPlanService;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.handler.RepaymentCalcFactory;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.repayment.dto.LoanInfoSearchCriteriaDTO;
import com.cana.vbam.common.repayment.dto.RepaymentAmount;
import com.cana.vbam.common.repayment.dto.RepaymentDetailsHistoryDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseDBDTO;
import com.cana.vbam.common.repayment.dto.RepaymentExpenseRedisDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanDBDTO;
import com.cana.vbam.common.repayment.dto.RepaymentPlanInfoRedisIntegration;
import com.cana.vbam.common.repayment.dto.RepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.ChargeStandard;
import com.cana.vbam.common.repayment.enums.EditAble;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.repayment.enums.VerifyStatus;
import com.cana.vbam.common.service.impl.VbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;

public class RepaymentPlanApi implements IRepaymentPlanApi {
	
	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private RepaymentValidator validator;
	
	@Resource
	private IRepaymentTransactionService repaymentTransactionService;
	
	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	
	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;
	
	@Resource
	private RepaymentSingleCollectMapper repaymentSingleCollectMapper;
	
	@Resource
	private RepaymentSingleDistributeDetailMapper repaymentSingleDistributeDetailMapper;
	
	@Resource
	private IRepaymentPlanService repaymentPlanServiceImpl;
	
	@Resource
	private ILoanInfoService loanInfoService;
	
	@Resource
	private IRepositoryService repositoryService;

	@Resource
	private VbamCommonService commonService;
	
	@Override
	public String saveRepaymentPlanRedisWithModeAndMethod(String businessMode, String inputMethod, String loanInfoId, String operatorId) throws Exception {
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = new RepaymentPlanInfoRedisIntegration();
		repaymentPlanInfoRedisIntegration.setBusinessMode(businessMode);
		repaymentPlanInfoRedisIntegration.setInputMethod(inputMethod);
		String key = generateRepaymentPlanInfoId();
		redisCache.save(RedisUtils.generateRepaymentPlanRedisKeyByOperator(loanInfoId, key, operatorId), repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
		return key;
	}

	@Override
	public void saveRepaymentPlanRedisDTOSingleLine(String redisKey,RepaymentPlanRedisDTO repaymentPlanRedisDTO,String masterId,String id) throws Exception {
		if(null == repaymentPlanRedisDTO){
			throw WebException.instance("需要保存的内容为空，保存失败");
		}
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		repaymentPlanRedisDTO.setId(generateRepaymentPlanInfoId());
		if(validator.validateForRepaymentPlan(repaymentPlanRedisDTO,masterId,repaymentPlanInfoRedisIntegration)){
			validator.repaymentPeriodValidateForManual(repaymentPlanRedisDTO,repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect(),id);
			if(StringUtils.equals(repaymentPlanRedisDTO.getVerifyStatus(), VerifyStatus.PASS.name())){
				List<RepaymentPlanRedisDTO> repaymentPlanCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect();
				repaymentPlanRedisDTO.setLoanInfoId(id);
				if(null == repaymentPlanCorrectList){
					repaymentPlanCorrectList = new ArrayList<RepaymentPlanRedisDTO>();
					repaymentPlanCorrectList.add(repaymentPlanRedisDTO);
					repaymentPlanInfoRedisIntegration.setRepaymentPlanCorrect(repaymentPlanCorrectList);
				}else{
					repaymentPlanCorrectList.add(repaymentPlanRedisDTO);
				}
				redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
			}else{
				throw WebException.instance(repaymentPlanRedisDTO.getVerifyFailReason());
			}
		}else{
			throw WebException.instance(repaymentPlanRedisDTO.getVerifyFailReason());
		}
	}

	@Override
	public void saveRepaymentExpenseRedisDTOSingleLine(String redisKey,RepaymentExpenseRedisDTO repaymentExpenseRedisDTO,String masterId) throws Exception {
		if( null == repaymentExpenseRedisDTO ){
			throw WebException.instance("需要保存的内容为空，保存失败");
		}
		List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOList = new ArrayList<RepaymentExpenseRedisDTO>();
		String[] repaymentDateList = repaymentExpenseRedisDTO.getRepaymentDate().split(",");
		List<RepaymentExpenseRedisDTO> repaymentExpenseList = new ArrayList<RepaymentExpenseRedisDTO>();
		for(String repaymentDate:repaymentDateList){
			RepaymentExpenseRedisDTO repaymentExpenseRedisDTOTemp = new RepaymentExpenseRedisDTO();
			BeanUtils.copyProperties(repaymentExpenseRedisDTO, repaymentExpenseRedisDTOTemp);
			repaymentExpenseRedisDTOTemp.setId(generateRepaymentExpenseId());
			repaymentExpenseRedisDTOTemp.setRepaymentDate(repaymentDate);
			repaymentExpenseRedisDTOList.add(repaymentExpenseRedisDTOTemp);
		}
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		List<RepaymentExpenseRedisDTO> repaymentExpenseCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect();
		// 统一进行校验
		for(RepaymentExpenseRedisDTO repaymentExpenseDTO:repaymentExpenseRedisDTOList){
			if(validator.validateForRepaymentExpense(repaymentExpenseDTO,masterId,repaymentExpenseCorrectList)){
				repaymentExpenseDTO.setVerifyStatus(VerifyStatus.PASS.name());
				repaymentExpenseCorrectList.add(repaymentExpenseDTO);
			}
		}
		// 根据校验状态判断是否添加到redis中
		for(RepaymentExpenseRedisDTO repaymentExpenseDTO:repaymentExpenseRedisDTOList){
			if(StringUtils.equals(repaymentExpenseDTO.getVerifyStatus(), VerifyStatus.PASS.name())){
				repaymentExpenseList.add(repaymentExpenseDTO);
			}else{
				//移除已经保存的费用
				repaymentExpenseList.removeAll(repaymentExpenseList);
				throw WebException.instance(repaymentExpenseDTO.getVerifyFailReason());
			}
		}
		redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
	}

	@Override
	public void saveRepaymentPlanInfoRedisIntegration(String redisKey,List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOList,List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOList,String masterId) throws Exception {
		if(CollectionUtils.isEmpty(repaymentPlanRedisDTOList) && CollectionUtils.isEmpty(repaymentExpenseRedisDTOList)){
			throw WebException.instance("需要保存的内容为空，保存失败");
		}
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		clearPlanExpenseListData(repaymentPlanInfoRedisIntegration);
		//Excel导入还款计划验证和保存
		repaymentPlanValidateAndSave(repaymentPlanInfoRedisIntegration,repaymentPlanRedisDTOList,masterId);
		//Excel导入还款费用验证和保存
		repaymentExpenseValidateAndSave(repaymentPlanInfoRedisIntegration,repaymentExpenseRedisDTOList,masterId);
		redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
	}

	@Override
	public RepaymentPlanRedisDTO queryRepaymentPlanRedisDTOSingleLine(String redisKey, String id) throws Exception {
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		List<RepaymentPlanRedisDTO> repaymentPlanCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect();
		RepaymentPlanRedisDTO repaymentPlanRedisDTO = new RepaymentPlanRedisDTO();
		repaymentPlanRedisDTO.setId(id);
		int index = repaymentPlanCorrectList.indexOf(repaymentPlanRedisDTO);
		if( index < 0 ){
			throw WebException.instance("未找到相应数据，获取失败");
		}
		return repaymentPlanCorrectList.get(index);
	}

	@Override
	public RepaymentExpenseRedisDTO queryRepaymentExpenseRedisDTOSingleLine(String redisKey, String id)
			throws Exception {
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		List<RepaymentExpenseRedisDTO> repaymentPlanCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect();
		RepaymentExpenseRedisDTO repaymentExpenseRedisDTO = new RepaymentExpenseRedisDTO();
		repaymentExpenseRedisDTO.setId(id);
		int index = repaymentPlanCorrectList.indexOf(repaymentExpenseRedisDTO);
		if( index < 0){
			throw WebException.instance("未找到相应数据，获取失败");
		}
		return repaymentPlanCorrectList.get(index);
	}

	@Override
	public void updateRepaymentPlanRedisDTOSingleLine(String redisKey,RepaymentPlanRedisDTO repaymentPlanRedisDTO,String masterId,String id) throws Exception {
		if( null == repaymentPlanRedisDTO ){
			throw WebException.instance("需要更新的内容为空，保存失败");
		}
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration) redisCache.get(redisKey);
		if(validator.validateForRepaymentPlan(repaymentPlanRedisDTO,masterId,repaymentPlanInfoRedisIntegration)){
			validator.repaymentPeriodValidateForManual(repaymentPlanRedisDTO,repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect(),id);
			if(StringUtils.equals(repaymentPlanRedisDTO.getVerifyStatus(), VerifyStatus.PASS.name())){
				List<RepaymentPlanRedisDTO> repaymentPlanCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect();
				int index=-1;
				index = repaymentPlanCorrectList.indexOf(repaymentPlanRedisDTO);
				if( index < 0){
					throw WebException.instance("未找到相应数据，更新失败");
				}
				repaymentPlanCorrectList.set(index, repaymentPlanRedisDTO);
				redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
			}else{
				throw WebException.instance(repaymentPlanRedisDTO.getVerifyFailReason());
			}
		}else{
			throw WebException.instance(repaymentPlanRedisDTO.getVerifyFailReason());
		}
	}

	@Override
	public void updateRepaymentExpenseRedisDTOSingleLine(String redisKey,RepaymentExpenseRedisDTO repaymentExpenseRedisDTO,String masterId) throws Exception {
		if( null == repaymentExpenseRedisDTO ){
			throw WebException.instance("需要更新的内容为空，更新失败");
		}
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration =(RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		if(validator.validateForRepaymentExpense(repaymentExpenseRedisDTO,masterId,repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect())){
			List<RepaymentExpenseRedisDTO> repaymentExpenseCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect();
			int index=-1;
			index = repaymentExpenseCorrectList.indexOf(repaymentExpenseRedisDTO);
			if( index < 0 ){
				throw WebException.instance("未找到相应数据，更新失败");
			}
			repaymentExpenseCorrectList.set(index, repaymentExpenseRedisDTO);
			redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
		}else{
			throw WebException.instance(repaymentExpenseRedisDTO.getVerifyFailReason());
		}
	}

	@Override
	public void deleteRepaymentPlanRedisDTOSingleLine(String redisKey,String id) throws Exception {
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		repaymentPlanInfoRedisIntegration.removeSingleCorrectPlan(id);
		redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
	}

	@Override
	public void deleteRepaymentExpenseRedisDTOSingleLine(String redisKey,String id) throws Exception {
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		repaymentPlanInfoRedisIntegration.removeSingleCorrectExpense(id);
		redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
	}
	
	@Override
	public RepaymentPlanInfoRedisIntegration getRepaymentPlanInfoRedisIntegration(String redisKey) throws Exception {
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		Collections.sort(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect(), new Comparator<RepaymentPlanRedisDTO>(){
			@Override
			public int compare(RepaymentPlanRedisDTO o1,RepaymentPlanRedisDTO o2) {
				if(StringUtils.equals(o1.getLoanNo(), o2.getLoanNo())){
					int repaymentPeriodO1 = Integer.parseInt(o1.getRepaymentPeriod());
					int repaymentPeriodO2 = Integer.parseInt(o2.getRepaymentPeriod());
					if( repaymentPeriodO1 > repaymentPeriodO2 )
						return 1;
					else if(repaymentPeriodO1 == repaymentPeriodO2)
						return 0;
					else
						return -1;
				}else{
					return o1.getLoanNo().compareTo(o2.getLoanNo());
				}
			}
		});
		Collections.sort(repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect(), new Comparator<RepaymentPlanRedisDTO>(){
			@Override
			public int compare(RepaymentPlanRedisDTO o1,RepaymentPlanRedisDTO o2) {
				if(StringUtils.isBlank(o1.getRepaymentPeriod()) || StringUtils.isBlank(o2.getRepaymentPeriod())){
					return 1;
				}
				if(StringUtils.equals(o1.getLoanNo(), o2.getLoanNo())){
					return o1.getRepaymentPeriod().compareTo(o2.getRepaymentPeriod());
				}else{
					return o1.getLoanNo().compareTo(o2.getLoanNo());
				}
			}
		});
		return repaymentPlanInfoRedisIntegration;
	}
	
	@Override
	public void saveRepaymentPlanAndExpense(String redisKey,String masterId,String loanInfoIdForSave) throws Exception {
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		if(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect())&&CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect())){
			throw WebException.instance("您未添加任何还款计划，保存失败");
		}
		List<List<RepaymentPlanRedisDTO>> afterClassifyList = new ArrayList<List<RepaymentPlanRedisDTO>>();
		if(CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect())){
			for(RepaymentPlanRedisDTO repaymentPlanRedisDTO:repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect()){
				boolean findFlag = false;
				for(List<RepaymentPlanRedisDTO> list:afterClassifyList){
					if(StringUtils.equals(list.get(0).getLoanNo(), repaymentPlanRedisDTO.getLoanNo()) && StringUtils.equals(list.get(0).getFinanceCompany(), repaymentPlanRedisDTO.getFinanceCompany())){
						list.add(repaymentPlanRedisDTO);
						findFlag = true;
					}
				}
				if(!findFlag){
					List<RepaymentPlanRedisDTO> newList = new ArrayList<RepaymentPlanRedisDTO>();
					newList.add(repaymentPlanRedisDTO);
					afterClassifyList.add(newList);
				}
			}
		}
		for(List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOList:afterClassifyList){
			List<RepaymentPlan> repaymentPlanList = new ArrayList<RepaymentPlan>();
			List<RepaymentExpense> repaymentExpenseList = new ArrayList<RepaymentExpense>();
			RepaymentLoanInfo repaymentLoanInfo = loanInfoService.queryLoanInfodetailFromDB(repaymentPlanRedisDTOList.get(0).getLoanInfoId());
			repaymentPlanConvert(repaymentPlanInfoRedisIntegration.getBusinessMode(), repaymentPlanInfoRedisIntegration.getInputMethod(), repaymentPlanRedisDTOList, repaymentPlanList, masterId, repaymentLoanInfo.getFinanceId());
			repaymentExpenseConvert(repaymentPlanInfoRedisIntegration, repaymentExpenseList, masterId, repaymentPlanRedisDTOList.get(0).getLoanNo(), repaymentPlanRedisDTOList.get(0).getFinanceCompany(), repaymentLoanInfo.getFinanceId());
			RepaymentLoanInfoExample repaymentLoanInfoExample = new RepaymentLoanInfoExample();
			repaymentLoanInfoExample.createCriteria().andLoanNoEqualTo(repaymentPlanRedisDTOList.get(0).getLoanNo()).andFactorIdEqualTo(masterId);
			List<RepaymentLoanInfo> list = loanInfoMapper.selectByExample(repaymentLoanInfoExample);
			if(CollectionUtils.isEmpty(list)){
				throw WebException.instance("放款信息未找到，保存失败");
			}
			repaymentTransactionService.saveRepymentPlanAndExpenseInfo(repaymentPlanList, repaymentExpenseList, list.get(0).getId(), DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
		}
		if(CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect()) && CollectionUtils.isNotEmpty(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect())){
			List<RepaymentPlan> repaymentPlanList = new ArrayList<RepaymentPlan>();
			List<RepaymentExpense> repaymentExpenseList = new ArrayList<RepaymentExpense>();
			RepaymentLoanInfo repaymentLoanInfo = loanInfoService.queryLoanInfodetailFromDB(repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect().get(0).getLoanInfoId());
			repaymentExpenseConvertForExpenseOnly(repaymentPlanInfoRedisIntegration, repaymentExpenseList, masterId, repaymentLoanInfo.getFinanceId());
			repaymentTransactionService.saveRepymentPlanAndExpenseInfo(repaymentPlanList, repaymentExpenseList, repaymentExpenseList.get(0).getLoanInfoId(), DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_VERSION, 4));
		}
	}
	
	@Override
	public List<RepaymentPlanRedisDTO> queryRepaymentPlanExist(String id) throws Exception {
		RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
		repaymentPlanExample.createCriteria().andLoanInfoIdEqualTo(id);
		List<RepaymentPlan> repaymentPlanList = repaymentPlanMapper.selectByExample(repaymentPlanExample);
		List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOList = new ArrayList<RepaymentPlanRedisDTO>();
		for(RepaymentPlan repaymentPlan:repaymentPlanList){
			RepaymentPlanRedisDTO repaymentPlanRedisDTO = new RepaymentPlanRedisDTO(); 
			BeanUtils.copyProperties(repaymentPlan, repaymentPlanRedisDTO);
			repaymentPlanRedisDTO.setFinanceAmount(MoneyArithUtil.convertMoneyToString(repaymentPlan.getFinanceAmount()));
			repaymentPlanRedisDTO.setFinanceBalance(MoneyArithUtil.convertMoneyToString(repaymentPlan.getFinanceBalance()));
			repaymentPlanRedisDTO.setRepaymentPeriod(repaymentPlan.getRepaymentPeriod().toString());
			repaymentPlanRedisDTO.setAccountRepaymentPrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountPrincipal()));
			repaymentPlanRedisDTO.setAccountRepaymentInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountInterest()));
			repaymentPlanRedisDTO.setAccountRepaymentServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountServiceCharge()));
			repaymentPlanRedisDTO.setAccountRepaymentTotal(MoneyArithUtil.convertMoneyToString((repaymentPlan.getAccountPrincipal()+repaymentPlan.getAccountInterest()+repaymentPlan.getAccountServiceCharge())));
			repaymentPlanRedisDTO.setSettleStatus(SettleStatus.valueOf(repaymentPlan.getSettleStatus()).desc());
			repaymentPlanRedisDTO.setEditAble(EditAble.EDITUNADLE.name());
			repaymentPlanRedisDTOList.add(repaymentPlanRedisDTO);
		}
		Collections.sort(repaymentPlanRedisDTOList, new Comparator<RepaymentPlanRedisDTO>(){
			@Override
			public int compare(RepaymentPlanRedisDTO o1,
					RepaymentPlanRedisDTO o2) {
				return o1.getRepaymentPeriod().compareTo(o2.getRepaymentPeriod());
			}
		});
		return repaymentPlanRedisDTOList;
	}
	
	@Override
	public List<RepaymentExpenseRedisDTO> queryRepaymentExpenseRedisDTOExist(String id) throws Exception {
		RepaymentExpenseExample repaymentExpenseExample = new RepaymentExpenseExample();
		repaymentExpenseExample.createCriteria().andLoanInfoIdEqualTo(id);
		List<RepaymentExpense> repaymentExpenseList = repaymentExpenseMapper.selectByExample(repaymentExpenseExample);
		List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOList = new ArrayList<RepaymentExpenseRedisDTO>();
		for(RepaymentExpense repaymentExpense:repaymentExpenseList){
			RepaymentExpenseRedisDTO repaymentExpenseRedisDTO = new RepaymentExpenseRedisDTO(); 
			BeanUtils.copyProperties(repaymentExpense, repaymentExpenseRedisDTO);
			repaymentExpenseRedisDTO.setRepaymentAmount(MoneyArithUtil.convertMoneyToString(repaymentExpense.getRepaymentAmount()));
			if(repaymentExpense.getChargeStandard()==0){
				repaymentExpenseRedisDTO.setChargeStandard("");
			}else{
				repaymentExpenseRedisDTO.setChargeStandard(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentExpense.getChargeStandard())));
			}
			repaymentExpenseRedisDTO.setChargeAmount(repaymentExpense.getChargeAmount() == 0 ? "" : MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentExpense.getChargeAmount())));
			repaymentExpenseRedisDTO.setChargeRatio(repaymentExpense.getChargeRatio().doubleValue() == 0 ? "" : MoneyArithUtil.convertInterestRateToString(repaymentExpense.getChargeRatio()));
			repaymentExpenseRedisDTO.setSettleStatus(SettleStatus.valueOf(repaymentExpense.getSettleStatus()).desc());
			if(StringUtils.isNotBlank(repaymentExpense.getChargeMethod())){
				repaymentExpenseRedisDTO.setChargeMethod(ChargeMethod.valueOf(repaymentExpense.getChargeMethod()).desc());
			}
			repaymentExpenseRedisDTO.setEditAble(EditAble.EDITUNADLE.name());
			repaymentExpenseRedisDTOList.add(repaymentExpenseRedisDTO);
		}
		return repaymentExpenseRedisDTOList;
	}
	
	
	/**
	 * Excel导入还款计划验证和保存
	 * @param repaymentPlanInfoRedisIntegration
	 * @param repaymentPlanRedisDTOList
	 * @param masterId
	 * @throws Exception
	 */
	private void repaymentPlanValidateAndSave(RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration,List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOList,String masterId) throws Exception{
		List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOCorrectList = new ArrayList<RepaymentPlanRedisDTO>();
		List<RepaymentPlanRedisDTO> repaymentPlanRedisDTOIncorrectList = new ArrayList<RepaymentPlanRedisDTO>();
		if( !CollectionUtils.isEmpty(repaymentPlanRedisDTOList) ){
			for(RepaymentPlanRedisDTO repaymentPlanRedisDTO:repaymentPlanRedisDTOList){
				repaymentPlanRedisDTO.setId(generateRepaymentPlanInfoId());
				if(validator.validateForRepaymentPlan(repaymentPlanRedisDTO,masterId,repaymentPlanInfoRedisIntegration)){
					repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.PASS.name());
					repaymentPlanRedisDTOCorrectList.add(repaymentPlanRedisDTO);
					repaymentPlanInfoRedisIntegration.setRepaymentPlanCorrect(repaymentPlanRedisDTOCorrectList);
				}else{
					repaymentPlanRedisDTO.setVerifyStatus(VerifyStatus.NEGATIVE.name());
					repaymentPlanRedisDTOIncorrectList.add(repaymentPlanRedisDTO);
					repaymentPlanInfoRedisIntegration.setRepaymentPlanIncorrect(repaymentPlanRedisDTOIncorrectList);
				}
			}
		}
		// 对所有导入的数据进行分类用于期数的校验
		List<List<RepaymentPlanRedisDTO>> afterClassifyList = new ArrayList<List<RepaymentPlanRedisDTO>>();
		if(!CollectionUtils.isEmpty(repaymentPlanRedisDTOCorrectList)){
			for(RepaymentPlanRedisDTO repaymentPlanRedisDTO:repaymentPlanRedisDTOCorrectList){
				boolean findFlag = false;
				for(List<RepaymentPlanRedisDTO> list:afterClassifyList){
					if(StringUtils.equals(list.get(0).getLoanNo(), repaymentPlanRedisDTO.getLoanNo()) && StringUtils.equals(list.get(0).getFinanceCompany(), repaymentPlanRedisDTO.getFinanceCompany())){
						list.add(repaymentPlanRedisDTO);
						findFlag = true;
					}
				}
				if(!findFlag){
					List<RepaymentPlanRedisDTO> newList = new ArrayList<RepaymentPlanRedisDTO>();
					newList.add(repaymentPlanRedisDTO);
					afterClassifyList.add(newList);
				}
			}
			validator.repaymentPeriodValidateForExcel(afterClassifyList, repaymentPlanRedisDTOCorrectList, repaymentPlanRedisDTOIncorrectList, masterId);
		}
		for(RepaymentPlanRedisDTO repaymentPlanRedisDTO:repaymentPlanRedisDTOIncorrectList){
			repaymentPlanRedisDTOCorrectList.remove(repaymentPlanRedisDTO);
		}
		repaymentPlanInfoRedisIntegration.setTotalRepyamentPlanNum(CollectionUtils.isEmpty(repaymentPlanRedisDTOList) ? 0 : repaymentPlanRedisDTOList.size());
		repaymentPlanInfoRedisIntegration.setRepyamentPlanCorrectNum(CollectionUtils.isEmpty(repaymentPlanRedisDTOCorrectList) ? 0 : repaymentPlanRedisDTOCorrectList.size());
		repaymentPlanInfoRedisIntegration.setRepyamentPlanIncorrectNum(CollectionUtils.isEmpty(repaymentPlanRedisDTOIncorrectList) ? 0 : repaymentPlanRedisDTOIncorrectList.size());
		repaymentPlanInfoRedisIntegration.setRepaymentPlanCorrect(repaymentPlanRedisDTOCorrectList);
		repaymentPlanInfoRedisIntegration.setRepaymentPlanIncorrect(repaymentPlanRedisDTOIncorrectList);
	}
	
	/**
	 * Excel导入还款费用验证和保存
	 * @param repaymentPlanInfoRedisIntegration
	 * @param repaymentExpenseRedisDTOList
	 * @param masterId
	 * @throws Exception
	 */
	private void repaymentExpenseValidateAndSave(RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration,List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOList,String masterId) throws Exception{
		List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOCorrectList = new ArrayList<RepaymentExpenseRedisDTO>();
		List<RepaymentExpenseRedisDTO> repaymentExpenseRedisDTOIncorrectList = new ArrayList<RepaymentExpenseRedisDTO>();
		if(!CollectionUtils.isEmpty(repaymentExpenseRedisDTOList)){
			for(RepaymentExpenseRedisDTO repaymentExpenseRedisDTO:repaymentExpenseRedisDTOList){
				repaymentExpenseRedisDTO.setId(generateRepaymentPlanInfoId());
				if(validator.validateForRepaymentExpense(repaymentExpenseRedisDTO,masterId,repaymentExpenseRedisDTOCorrectList)){
					repaymentExpenseRedisDTOCorrectList.add(repaymentExpenseRedisDTO);
				}else{
					repaymentExpenseRedisDTOIncorrectList.add(repaymentExpenseRedisDTO);
				}
			}
		}
		repaymentPlanInfoRedisIntegration.setTotalRepyamentPlanNum(repaymentExpenseRedisDTOList.size());
		repaymentPlanInfoRedisIntegration.setRepyamentExpenseCorrectNum(repaymentExpenseRedisDTOCorrectList.size());
		repaymentPlanInfoRedisIntegration.setRepyamentExpenseIncorrectNum(repaymentExpenseRedisDTOIncorrectList.size());
		repaymentPlanInfoRedisIntegration.setRepaymentExpenseCorrect(repaymentExpenseRedisDTOCorrectList);
		repaymentPlanInfoRedisIntegration.setRepaymentExpenseIncorrect(repaymentExpenseRedisDTOIncorrectList);
	}
	
	/**
	 * 还款计划信息转换
	 * @param repaymentPlanInfoRedisIntegration
	 * @param repaymentPlanList
	 */
	private void repaymentPlanConvert(String businessMode, String inputMethod, List<RepaymentPlanRedisDTO> repaymentPlanCorrectList, List<RepaymentPlan> repaymentPlanList, String masterId,String financeId){
//		List<RepaymentPlanRedisDTO> repaymentPlanCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect();
		if(CollectionUtils.isEmpty(repaymentPlanCorrectList) ){
			return;
		}
		for(RepaymentPlanRedisDTO repaymentPlanRedisDTO:repaymentPlanCorrectList){
			RepaymentPlan repaymentPlan = new RepaymentPlan();
			BeanUtils.copyProperties(repaymentPlanRedisDTO, repaymentPlan);
			repaymentPlan.setBusinessMode(businessMode);
			repaymentPlan.setInputMethod(inputMethod);
			repaymentPlan.setSettleStatus(SettleStatus.getValue(repaymentPlanRedisDTO.getSettleStatus()).name());
			repaymentPlan.setFinanceId(financeId);
			repaymentPlan.setFactorId(masterId);
			repaymentPlan.setRepaymentPeriod(Integer.parseInt(repaymentPlanRedisDTO.getRepaymentPeriod()));
			repaymentPlan.setLoanInfoId(repaymentPlanRedisDTO.getLoanInfoId());
			repaymentPlan.setFinanceAmount(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getFinanceAmount()));
			repaymentPlan.setFinanceBalance(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getFinanceBalance()));
			if(StringUtils.equals(repaymentPlan.getSettleStatus(), SettleStatus.SETTLED.name())){
				repaymentPlan.setAccountPrincipal(0L);
				repaymentPlan.setAccountInterest(0L);
				repaymentPlan.setAccountServiceCharge(0L);
				repaymentPlan.setPaidNormalPrincipal(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentPrincipal()));
				repaymentPlan.setPaidNormalInterest(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentInterest()));
				repaymentPlan.setPaidNormalServiceCharge(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentServiceCharge()));
			}else{
				repaymentPlan.setAccountPrincipal(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentPrincipal()));
				repaymentPlan.setAccountInterest(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentInterest()));
				repaymentPlan.setAccountServiceCharge(MoneyArithUtil.convertStringToMoney(repaymentPlanRedisDTO.getAccountRepaymentServiceCharge()));
			}
			repaymentPlan.setCreateTime(new Date());
			repaymentPlanList.add(repaymentPlan);
		}
	}
	
	/**
	 * 还款费用信息转换
	 * @param repaymentPlanInfoRedisIntegration
	 * @param repaymentExpenseList
	 */
	private void repaymentExpenseConvert(RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration, List<RepaymentExpense> repaymentExpenseList, String masterId, String loanNo, String financeCompany, String financeId){
		List<RepaymentExpenseRedisDTO> repaymentExpenseCorrectlist = repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect();
		if(CollectionUtils.isEmpty(repaymentExpenseCorrectlist) ){
			return;
		}
		for(RepaymentExpenseRedisDTO repaymentExpenseRedisDTO:repaymentExpenseCorrectlist){
			if(StringUtils.equals(repaymentExpenseRedisDTO.getLoanNo(), loanNo) && StringUtils.equals(repaymentExpenseRedisDTO.getFinanceCompany(), financeCompany)){
				RepaymentExpense repaymentExpense = new RepaymentExpense();
				BeanUtils.copyProperties(repaymentExpenseRedisDTO, repaymentExpense);
				repaymentExpense.setLoanInfoId(repaymentExpenseRedisDTO.getLoanInfoId());
				repaymentExpense.setBusinessMode(repaymentPlanInfoRedisIntegration.getBusinessMode());
				repaymentExpense.setInputMethod(repaymentPlanInfoRedisIntegration.getInputMethod());
				repaymentExpense.setSettleStatus(SettleStatus.getValue(repaymentExpenseRedisDTO.getSettleStatus()).name());
				repaymentExpense.setCreateTime(new Date());
				repaymentExpense.setFinanceId(financeId);
				repaymentExpense.setFactorId(masterId);
				if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeAmount()))
					repaymentExpense.setChargeAmount(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getChargeAmount()));
				if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeRatio()))
					repaymentExpense.setChargeRatio(MoneyArithUtil.convertStringToInterestRate(repaymentExpenseRedisDTO.getChargeRatio()));
				if(StringUtils.equals(repaymentExpense.getSettleStatus(), SettleStatus.SETTLED.name())){
					repaymentExpense.setRepaymentAmount(0L);
					repaymentExpense.setPaidAmount(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getRepaymentAmount()));
				}else{
					repaymentExpense.setRepaymentAmount(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getRepaymentAmount()));
				}
				if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeMethod()))
					repaymentExpense.setChargeMethod(ChargeMethod.getValue(repaymentExpenseRedisDTO.getChargeMethod()).name());
				if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeStandard())){
					if(null == ChargeStandard.getValue(repaymentExpenseRedisDTO.getChargeStandard()))
						repaymentExpense.setChargeStandard(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getChargeStandard()));
					else if(ChargeStandard.FINANCEAMOUNT == ChargeStandard.getValue(repaymentExpenseRedisDTO.getChargeStandard())){
						RepaymentLoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(repaymentExpenseRedisDTO.getLoanInfoId());
						repaymentExpense.setChargeStandard(loanInfo.getFinanceAmount());
					}
					else {
						RepaymentLoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(repaymentExpenseRedisDTO.getLoanInfoId());
						repaymentExpense.setChargeStandard(loanInfo.getFinanceBalance());
					}
				}
				repaymentExpenseList.add(repaymentExpense);
			}
		}
	}
	
	/**
	 * 还款费用信息转换(仅含费用)
	 * @param repaymentPlanInfoRedisIntegration
	 * @param repaymentExpenseList
	 */
	private void repaymentExpenseConvertForExpenseOnly(RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration, List<RepaymentExpense> repaymentExpenseList, String masterId, String financeId){
		List<RepaymentExpenseRedisDTO> repaymentExpenseCorrectlist = repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect();
		if(CollectionUtils.isEmpty(repaymentExpenseCorrectlist) ){
			return;
		}
		for(RepaymentExpenseRedisDTO repaymentExpenseRedisDTO:repaymentExpenseCorrectlist){
			RepaymentExpense repaymentExpense = new RepaymentExpense();
			BeanUtils.copyProperties(repaymentExpenseRedisDTO, repaymentExpense);
			repaymentExpense.setLoanInfoId(repaymentExpenseRedisDTO.getLoanInfoId());
			repaymentExpense.setBusinessMode(repaymentPlanInfoRedisIntegration.getBusinessMode());
			repaymentExpense.setInputMethod(repaymentPlanInfoRedisIntegration.getInputMethod());
			repaymentExpense.setSettleStatus(SettleStatus.getValue(repaymentExpenseRedisDTO.getSettleStatus()).name());
			repaymentExpense.setCreateTime(new Date());
			repaymentExpense.setFinanceId(financeId);
			repaymentExpense.setFactorId(masterId);
			if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeAmount()))
				repaymentExpense.setChargeAmount(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getChargeAmount()));
			if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeRatio()))
				repaymentExpense.setChargeRatio(MoneyArithUtil.convertStringToInterestRate(repaymentExpenseRedisDTO.getChargeRatio()));
			if(StringUtils.equals(repaymentExpense.getSettleStatus(), SettleStatus.SETTLED.name())){
				repaymentExpense.setRepaymentAmount(0L);
				repaymentExpense.setPaidAmount(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getRepaymentAmount()));
			}else{
				repaymentExpense.setRepaymentAmount(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getRepaymentAmount()));
			}
			if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeMethod()))
				repaymentExpense.setChargeMethod(ChargeMethod.getValue(repaymentExpenseRedisDTO.getChargeMethod()).name());
			if(StringUtils.isNotBlank(repaymentExpenseRedisDTO.getChargeStandard())){
				if(null == ChargeStandard.getValue(repaymentExpenseRedisDTO.getChargeStandard()))
					repaymentExpense.setChargeStandard(MoneyArithUtil.convertStringToMoney(repaymentExpenseRedisDTO.getChargeStandard()));
				else if(ChargeStandard.FINANCEAMOUNT == ChargeStandard.getValue(repaymentExpenseRedisDTO.getChargeStandard())){
					RepaymentLoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(repaymentExpenseRedisDTO.getLoanInfoId());
					repaymentExpense.setChargeStandard(loanInfo.getFinanceAmount());
				}
				else {
					RepaymentLoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(repaymentExpenseRedisDTO.getLoanInfoId());
					repaymentExpense.setChargeStandard(loanInfo.getFinanceBalance());
				}
			}
			repaymentExpenseList.add(repaymentExpense);
		}
	}
	
	/**
	 * 生成还款计划id
	 * @return
	 * @throws Exception
	 */
	private String generateRepaymentPlanInfoId() throws Exception{
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.REDIS_REPAYMENT_PLAN_INFO_ID, 4);
	}
	/**
	 * 生成还款费用id
	 * @return
	 * @throws Exception
	 */
	private String generateRepaymentExpenseId() throws Exception{
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.REDIS_REPAYMENT_EXPENSE_ID, 4);
	}
	
	private int getRedisExpireTimeFromProperties(){
		return Integer.parseInt(TopsConfReader.getConfContent("properties/repayment-common.properties", "redis.temp.timeout", ConfScope.G));
	}

	
	@Override
	public ListResult<RepaymentPlanDBDTO> queryRepaymentPlanFromDB(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO) throws Exception{
		ListResult<RepaymentPlanDBDTO> result = new ListResult<RepaymentPlanDBDTO>();
		RepaymentPlanExample repaymentPlanExample = setQueryRepaymentPlanConditions(loanInfoSearchCriteriaDTO);
		List<RepaymentPlan> repaymentPlans = repaymentPlanMapper.selectByExample(repaymentPlanExample);
		result.setData(convertRepaymentPlanListToRepaymentPlanDBDTOList(RepaymentPlanBO.poList2boList(repaymentPlans)));
		result.setTotalNum(repaymentPlanMapper.countByExample(repaymentPlanExample));
		return result;
	}
	
	private List<RepaymentPlanDBDTO> convertRepaymentPlanListToRepaymentPlanDBDTOList(List<RepaymentPlanBO> repaymentPlans) throws Exception{
		
		List<RepaymentPlanDBDTO> repaymentPlanDBDTOs = new ArrayList<RepaymentPlanDBDTO>();
		
		for(RepaymentPlanBO planBO : repaymentPlans){
			RepaymentLoanInfoBO loanInfoBO = planBO.lazyLoadLoanInfoBO();
			RepaymentPlanDBDTO repaymentPlanDBDTO = new RepaymentPlanDBDTO();
			BeanUtils.copyProperties(planBO, repaymentPlanDBDTO);
			repaymentPlanDBDTO.setRepaymentPeriod(planBO.getRepaymentPeriod()==null?"":planBO.getRepaymentPeriod().toString());
			repaymentPlanDBDTO.setFinanceAmount(MoneyArithUtil.convertMoneyToString(planBO.getFinanceAmount()));
			repaymentPlanDBDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(planBO.getAccountPrincipal()));
			RepaymentAmount accountInterestAndAccountServiceCharge = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcAccountInterestAndAccountServiceChargeUntilNow(loanInfoBO, planBO);
			repaymentPlanDBDTO.setAccountInterestUntilNow(MoneyArithUtil.convertMoneyToString(accountInterestAndAccountServiceCharge.getAccountInterest()));
			repaymentPlanDBDTO.setAccountInterest(MoneyArithUtil.convertMoneyToString(planBO.getAccountInterest()));
			repaymentPlanDBDTO.setAccountServiceCharge(MoneyArithUtil.convertMoneyToString(planBO.getAccountServiceCharge()));
			repaymentPlanDBDTO.setPaidEarlyRepaymentCharge(MoneyArithUtil.convertMoneyToString(planBO.getPaidEarlyRepaymentCharge()));
			repaymentPlanDBDTO.setOverduePrincipal(MoneyArithUtil.convertMoneyToString(planBO.getOverduePrincipal()));
			repaymentPlanDBDTO.setOverdueInterest(MoneyArithUtil.convertMoneyToString(planBO.getOverdueInterest()));
			repaymentPlanDBDTO.setOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(planBO.getOverdueServiceCharge()));
			repaymentPlanDBDTO.setAccountExtensionCharge(MoneyArithUtil.convertMoneyToString(planBO.getAccountExtensionCharge()));
			repaymentPlanDBDTO.setOverdueManageCharge(MoneyArithUtil.convertMoneyToString(getOverdueManageCharge(planBO)));
			repaymentPlanDBDTO.setAccountTotalAmount(MoneyArithUtil.convertMoneyToString(getAccountTotalAmount(planBO)));
			repaymentPlanDBDTO.setSettleStatus(SettleStatus.valueOf(planBO.getSettleStatus()).desc());
			repaymentPlanDBDTO.setAccessToActiveRepayment(loanInfoBO.containNonAutoRepaymentPlans());
			repaymentPlanDBDTOs.add(repaymentPlanDBDTO);
		}
		return repaymentPlanDBDTOs;
	}
	
	/**
	 * 逾期管理费用=逾期本金罚息+逾期利息罚息+逾期服务费罚息+其他罚息
	 * @param repaymentPlan
	 * @return
	 */
	private Long getOverdueManageCharge(RepaymentPlan repaymentPlan)
	{
		Long overdueManageCharge = new Long(0);
		overdueManageCharge += repaymentPlan.getOverduePrincipalPenalty()==null?0:repaymentPlan.getOverduePrincipalPenalty();
		overdueManageCharge += repaymentPlan.getOverdueInterestPenalty()==null?0:repaymentPlan.getOverdueInterestPenalty();
		overdueManageCharge += repaymentPlan.getOverdueServiceChargePenalty()==null?0:repaymentPlan.getOverdueServiceChargePenalty();
		overdueManageCharge += repaymentPlan.getOtherPenalty()==null?0:repaymentPlan.getOtherPenalty() ;
		return overdueManageCharge;
	}
	
	/**
	 * 应还总金额=应还本金+应还利息+应还服务费+应还展期费用+逾期本金+逾期利息+逾期服务费+逾期管理费用(逾期本金罚息+逾期利息罚息+逾期服务费罚息+其他罚息)
	 * @param repaymentPlan
	 * @return
	 */
	private Long getAccountTotalAmount(RepaymentPlan repaymentPlan)
	{
		Long accountTotalAmount = new Long(0);
		accountTotalAmount += repaymentPlan.getAccountPrincipal()==null?0:repaymentPlan.getAccountPrincipal();
		accountTotalAmount += repaymentPlan.getAccountInterest()==null?0:repaymentPlan.getAccountInterest();
		accountTotalAmount += repaymentPlan.getAccountExtensionCharge()==null?0:repaymentPlan.getAccountExtensionCharge();
		accountTotalAmount += repaymentPlan.getAccountServiceCharge()==null?0:repaymentPlan.getAccountServiceCharge();
		accountTotalAmount += repaymentPlan.getOverduePrincipal() == null?0:repaymentPlan.getOverduePrincipal();
		accountTotalAmount += repaymentPlan.getOverdueInterest() == null?0:repaymentPlan.getOverdueInterest();
		accountTotalAmount += repaymentPlan.getOverdueServiceCharge() == null?0:repaymentPlan.getOverdueServiceCharge();
		accountTotalAmount += repaymentPlan.getOverduePrincipalPenalty()==null?0:repaymentPlan.getOverduePrincipalPenalty();
		accountTotalAmount += repaymentPlan.getOverdueInterestPenalty()==null?0:repaymentPlan.getOverdueInterestPenalty();
		accountTotalAmount += repaymentPlan.getOverdueServiceChargePenalty()==null?0:repaymentPlan.getOverdueServiceChargePenalty();
		accountTotalAmount += repaymentPlan.getOtherPenalty()==null?0:repaymentPlan.getOtherPenalty();
		return accountTotalAmount;
	}
	
	private RepaymentPlanDBDTO convertRepaymentPlanToRepaymentPlanDBDTO(RepaymentPlan repaymentPlan){
		RepaymentPlanDBDTO repaymentPlanDBDTO = new RepaymentPlanDBDTO();
		BeanUtils.copyProperties(repaymentPlan, repaymentPlanDBDTO);
		repaymentPlanDBDTO.setRepaymentPeriod(repaymentPlan.getRepaymentPeriod()==null?"":repaymentPlan.getRepaymentPeriod().toString());
		repaymentPlanDBDTO.setFinanceAmount(MoneyUtil.parseMoney(MoneyArithUtil.convertMoneyToString(repaymentPlan.getFinanceAmount())));
		repaymentPlanDBDTO.setAccountPrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountPrincipal()));
		repaymentPlanDBDTO.setAccountInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountInterest()));
		repaymentPlanDBDTO.setAccountServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountServiceCharge()));
		repaymentPlanDBDTO.setPaidEarlyRepaymentCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidEarlyRepaymentCharge()));
		repaymentPlanDBDTO.setOverduePrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getOverduePrincipal()));
		repaymentPlanDBDTO.setOverdueInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getOverdueInterest()));
		repaymentPlanDBDTO.setOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getOverdueServiceCharge()));
		repaymentPlanDBDTO.setAccountExtensionCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getAccountExtensionCharge()));
		repaymentPlanDBDTO.setOverdueManageCharge(MoneyArithUtil.convertMoneyToString((repaymentPlan.getOverduePrincipalPenalty()==null?0:repaymentPlan.getOverduePrincipalPenalty())
																				+(repaymentPlan.getOverdueInterestPenalty()==null?0:repaymentPlan.getOverdueInterestPenalty())
																				+(repaymentPlan.getOverdueServiceChargePenalty()==null?0:repaymentPlan.getOverdueServiceChargePenalty())
																				+(repaymentPlan.getOtherPenalty())));
		repaymentPlanDBDTO.setAccountTotalAmount(MoneyArithUtil.convertMoneyToString((repaymentPlan.getAccountPrincipal()==null?0:repaymentPlan.getAccountPrincipal())
																				+(repaymentPlan.getAccountInterest()==null?0:repaymentPlan.getAccountInterest())
																				+(repaymentPlan.getAccountExtensionCharge()==null?0:repaymentPlan.getAccountExtensionCharge())
																				+(repaymentPlan.getAccountServiceCharge()==null?0:repaymentPlan.getAccountServiceCharge())));
		repaymentPlanDBDTO.setSettleStatus(SettleStatus.valueOf(repaymentPlan.getSettleStatus()).desc());
		
		/*------------ 还款详情所需的额外字段 -------------*/
		repaymentPlanDBDTO.setFinanceBalance(MoneyArithUtil.convertMoneyToString(repaymentPlan.getFinanceBalance()));
		repaymentPlanDBDTO.setPaidNormalPrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidNormalPrincipal()));
		repaymentPlanDBDTO.setPaidNormalInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidNormalInterest()));
		repaymentPlanDBDTO.setPaidNormalServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidNormalServiceCharge()));
		repaymentPlanDBDTO.setPaidExtensionCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidExtensionCharge()));
		repaymentPlanDBDTO.setPaidOverduePrincipal(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidOverduePrincipal()));
		repaymentPlanDBDTO.setPaidOverdueInterest(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidOverdueInterest()));
		repaymentPlanDBDTO.setPaidOverdueServiceCharge(MoneyArithUtil.convertMoneyToString(repaymentPlan.getPaidOverdueServiceCharge()));
		repaymentPlanDBDTO.setPaidOverdueManageCharge(MoneyArithUtil.convertMoneyToString((repaymentPlan.getPaidOverduePrincipalPenalty()==null?0:repaymentPlan.getPaidOverduePrincipalPenalty())
													+(repaymentPlan.getPaidOverdueInterestPenalty()==null?0:repaymentPlan.getPaidOverdueInterestPenalty())
													+(repaymentPlan.getPaidOverdueServiceChargePenalty()==null?0:repaymentPlan.getPaidOverdueServiceChargePenalty())
													+(repaymentPlan.getPaidOtherPenalty())));
		/*------------ 判断是否逾期 -------------*/
		RepaymentPlanBO planBO = new RepaymentPlanBO(repaymentPlan);
		repaymentPlanDBDTO.setIsOverdue(planBO.inOverdueStateAfterSettled(commonService.getCurrentDate()));
		return repaymentPlanDBDTO;
	}
	
	private RepaymentPlanExample setQueryRepaymentPlanConditions(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO){
		RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
		StringUtil.trimObjectFields(loanInfoSearchCriteriaDTO);
		repaymentPlanExample.setLimitStart((loanInfoSearchCriteriaDTO.getPage()-1) * loanInfoSearchCriteriaDTO.getPageSize());
		repaymentPlanExample.setLimitEnd(loanInfoSearchCriteriaDTO.getPageSize());
//		repaymentPlanExample.setOrderByClause("-id");
		repaymentPlanExample.setOrderByClause("repayment_period");
		Criteria criteria = repaymentPlanExample.createCriteria();
		if(StringUtils.isNotBlank(loanInfoSearchCriteriaDTO.getLoanId()))
			criteria.andLoanInfoIdEqualTo(loanInfoSearchCriteriaDTO.getLoanId());
		return repaymentPlanExample;
	}
	
	private RepaymentExpenseExample setQueryRepaymentExpenseConditions(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO){
		RepaymentExpenseExample repaymentExpenseExample = new RepaymentExpenseExample();
		StringUtil.trimObjectFields(loanInfoSearchCriteriaDTO);
		repaymentExpenseExample.setLimitStart((loanInfoSearchCriteriaDTO.getPage()-1) * loanInfoSearchCriteriaDTO.getPageSize());
		repaymentExpenseExample.setLimitEnd(loanInfoSearchCriteriaDTO.getPageSize());
		repaymentExpenseExample.setOrderByClause("-id");
	    com.cana.repayment.dao.po.RepaymentExpenseExample.Criteria criteria = repaymentExpenseExample.createCriteria();
		if(StringUtils.isNotBlank(loanInfoSearchCriteriaDTO.getLoanId()))
			criteria.andLoanInfoIdEqualTo(loanInfoSearchCriteriaDTO.getLoanId());
		return repaymentExpenseExample;
	}
	
	private List<RepaymentExpenseDBDTO> convertRepaymentExpenseToRepaymentExpenseDBDTO(List<RepaymentExpense> repaymentExpenses){
		List<RepaymentExpenseDBDTO> repaymentExpenseDBDTOs = new ArrayList<RepaymentExpenseDBDTO>();
		for(RepaymentExpense repaymentExpense : repaymentExpenses){
			RepaymentExpenseDBDTO repaymentExpenseDBDTO = new RepaymentExpenseDBDTO();
			BeanUtils.copyProperties(repaymentExpense, repaymentExpenseDBDTO);
			
			if(StringUtils.isBlank(repaymentExpense.getChargeMethod()))
			{
				repaymentExpenseDBDTO.setChargeMethod("");
				repaymentExpenseDBDTO.setChargeValue("");
				repaymentExpenseDBDTO.setChargeStandard("");
			}
			else
			{
				repaymentExpenseDBDTO.setChargeStandard(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentExpense.getChargeStandard())));
				repaymentExpenseDBDTO.setChargeMethod(ChargeMethod.valueOf(repaymentExpense.getChargeMethod()).desc());
				if(ChargeMethod.AMOUNT == ChargeMethod.valueOf(repaymentExpense.getChargeMethod())){
					repaymentExpenseDBDTO.setChargeValue(MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentExpense.getChargeAmount())));
				}
//				else if(ChargeMethod.RATIO == ChargeMethod.valueOf(repaymentExpense.getChargeMethod()))
				else 
				{
					repaymentExpenseDBDTO.setChargeValue(MoneyArithUtil.convertInterestRateToString(repaymentExpense.getChargeRatio()));
				}
			}
			repaymentExpenseDBDTO.setPaidNormalAmount(MoneyArithUtil.convertMoneyToString(repaymentExpense.getPaidAmount()));
			repaymentExpenseDBDTO.setRepaymentAmount(MoneyArithUtil.convertMoneyToString(repaymentExpense.getRepaymentAmount()));
			repaymentExpenseDBDTO.setSettleStatus(SettleStatus.valueOf(repaymentExpense.getSettleStatus()).desc());
			repaymentExpenseDBDTOs.add(repaymentExpenseDBDTO);
		}
		return repaymentExpenseDBDTOs;
	}
	
	@Override
	public ListResult<RepaymentExpenseDBDTO> queryExpenseListFromDB(LoanInfoSearchCriteriaDTO loanInfoSearchCriteriaDTO) throws Exception {
		ListResult<RepaymentExpenseDBDTO> result = new ListResult<RepaymentExpenseDBDTO>();
		RepaymentExpenseExample repaymentExpenseExample = setQueryRepaymentExpenseConditions(loanInfoSearchCriteriaDTO);
		List<RepaymentExpense> repaymentExpenses = repaymentExpenseMapper.selectByExample(repaymentExpenseExample);
		result.setData(convertRepaymentExpenseToRepaymentExpenseDBDTO(repaymentExpenses));
		result.setTotalNum(repaymentExpenseMapper.countByExample(repaymentExpenseExample));
		return result;
	}

	@Override
	public ObjectResult<RepaymentPlanDBDTO> queryRepaymentDetails(String repaymentPlanId) throws Exception{
		ObjectResult<RepaymentPlanDBDTO> result = new ObjectResult<RepaymentPlanDBDTO>();
		StringUtil.trim(repaymentPlanId);
		RepaymentPlan repaymentPlan = repaymentPlanMapper.selectByPrimaryKey(repaymentPlanId);
		result.setData(convertRepaymentPlanToRepaymentPlanDBDTO(repaymentPlan));
		return result;
	}

	@Override
	public ListResult<RepaymentDetailsHistoryDTO> queryRepaymentDetailsHistory(String loanId) throws Exception{
		StringUtil.trim(loanId);
		ListResult<RepaymentDetailsHistoryDTO> result = new ListResult<RepaymentDetailsHistoryDTO>();
		RepaymentSingleCollectExample repaymentSingleCollectExample = new RepaymentSingleCollectExample();
		com.cana.repayment.dao.po.RepaymentSingleCollectExample.Criteria criteria = repaymentSingleCollectExample.createCriteria();
		criteria.andLoanInfoIdEqualTo(loanId);
		List<RepaymentSingleCollect> repaymentSingleCollects = repaymentSingleCollectMapper.selectByExample(repaymentSingleCollectExample);
		if(CollectionUtils.isEmpty(repaymentSingleCollects)){
			result.setData(null);
		}else 
			result.setData(convertRepaymentSingleCollectListToRepaymentDetailsHistoryDTOList(repaymentSingleCollects));
		return result;
	}

	@Override
	public ObjectResult<String> getRepaymentPeriod(String redisKey, String id) throws Exception {
		ObjectResult<String> result = new ObjectResult<String>();
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		RepaymentPlanExample repaymentPlanExample = new RepaymentPlanExample();
		repaymentPlanExample.createCriteria().andLoanInfoIdEqualTo(id);
		int count = repaymentPlanMapper.countByExample(repaymentPlanExample);
		int period = (CollectionUtils.isEmpty(repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect()) ? 0 : repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect().size());
		result.setData((count + period + 1)+"");
		return result;
	}
	
	private List<RepaymentDetailsHistoryDTO> convertRepaymentSingleCollectListToRepaymentDetailsHistoryDTOList(List<RepaymentSingleCollect> repaymentSingleCollects){
		List<RepaymentDetailsHistoryDTO> repaymentDetailsHistoryDTOs = new ArrayList<RepaymentDetailsHistoryDTO>();
		for(RepaymentSingleCollect repaymentSingleCollect : repaymentSingleCollects){
			RepaymentDetailsHistoryDTO repaymentDetailsHistoryDTO = new RepaymentDetailsHistoryDTO();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			repaymentDetailsHistoryDTO.setOperatingTime(simpleDateFormat.format(repaymentSingleCollect.getCreateTime()));
			repaymentDetailsHistoryDTO.setRepaymentMethod(RepaymentMethod.valueOf(repaymentSingleCollect.getRepaymentType()).desc());
			if(RepaymentMethod.OFFLINE == RepaymentMethod.valueOf(repaymentSingleCollect.getRepaymentType())){
				repaymentDetailsHistoryDTO.setOfflineTime(StringUtils.isBlank(repaymentSingleCollect.getRepaymentDate())?"":repaymentSingleCollect.getRepaymentDate());
			}
			else
				repaymentDetailsHistoryDTO.setOfflineTime("");
			String amountDetails = "";
			amountDetails += MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(repaymentSingleCollect.getRepaymentTotalAmount()));
			amountDetails += getAmountDetails(repaymentSingleCollect.getId());
			repaymentDetailsHistoryDTO.setAmountDetails(amountDetails);
			repaymentDetailsHistoryDTOs.add(repaymentDetailsHistoryDTO);
		}
		return repaymentDetailsHistoryDTOs;
	}
	
	@Override
	public String getAmountDetails(String repaymentSingleCollectId){
		String amountDetails = " (";
		RepaymentSingleDistributeDetailExample repaymentSingleDistributeDetailExample = new RepaymentSingleDistributeDetailExample();
		com.cana.repayment.dao.po.RepaymentSingleDistributeDetailExample.Criteria criteria = repaymentSingleDistributeDetailExample.createCriteria();
		criteria.andRepaymentSingleCollectIdEqualTo(repaymentSingleCollectId);
		List<RepaymentSingleDistributeDetail> repaymentSingleDistributeDetails = repaymentSingleDistributeDetailMapper.selectByExample(repaymentSingleDistributeDetailExample);
		if(CollectionUtils.isEmpty(repaymentSingleDistributeDetails)){
			return "";
		}
		Long principal = new Long(0);
		Long interest = new Long(0);
		Long serviceCharge = new Long(0);
		Long earlyRepaymentCharge = new Long(0);
		Long overduePrincipal = new Long(0);
		Long overdueInterest = new Long(0);
		Long overdueServiceCharge = new Long(0);
		Long extensionCharge = new Long(0);
		Long overdueManageCharge = new Long(0);
//		Long otherDefaultInterest = new Long(0);
		Long defaultCharge = new Long(0);
		for(RepaymentSingleDistributeDetail repaymentSingleDistributeDetail :repaymentSingleDistributeDetails){
			principal += repaymentSingleDistributeDetail.getPayNormalPrincipal();
			interest += repaymentSingleDistributeDetail.getPayNormalInterest();
			serviceCharge += repaymentSingleDistributeDetail.getPayNormalServiceCharge();
			earlyRepaymentCharge += repaymentSingleDistributeDetail.getEarlyRepaymentCharge();
			overduePrincipal += repaymentSingleDistributeDetail.getPayOverduePrincipal();
			overdueInterest += repaymentSingleDistributeDetail.getPayOverdueInterest();
			overdueServiceCharge += repaymentSingleDistributeDetail.getPayOverdueServiceCharge();
			extensionCharge += repaymentSingleDistributeDetail.getPayExtensionCharge();
			overdueManageCharge += repaymentSingleDistributeDetail.getPayOverdueInterestPenalty() + repaymentSingleDistributeDetail.getPayOverduePrincipalPenalty() + repaymentSingleDistributeDetail.getPayOverdueServiceChargePenalty() + repaymentSingleDistributeDetail.getPayOtherPenalty();
//			otherDefaultInterest += repaymentSingleDistributeDetail.getPayOtherPenalty();
			defaultCharge += repaymentSingleDistributeDetail.getPayExpense();
		}
		if(0 != principal){
			amountDetails += " 本金"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(principal));
		}
		if(0 != interest){
			amountDetails += " 利息"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(interest));
		}
		if(0 != serviceCharge){
			amountDetails += " 服务费"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(serviceCharge));
		}
		if(0 != earlyRepaymentCharge){
			amountDetails += " 提前还款手续费"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(earlyRepaymentCharge));
		}
		if(0 != overduePrincipal){
			amountDetails += " 逾期本金"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(overduePrincipal));
		}
		if(0 != overdueInterest){
			amountDetails += " 逾期利息"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(overdueInterest));
		}
		if(0 != overdueServiceCharge){
			amountDetails += " 逾期服务费"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(overdueServiceCharge));
		}
		if(0 != extensionCharge){
			amountDetails += " 展期费用"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(extensionCharge));
		}
		if(0 != overdueManageCharge){
			amountDetails += " 逾期管理费"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(overdueManageCharge));
		}
//		if(0 != otherDefaultInterest){
//			amountDetails += " 其他罚息"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(otherDefaultInterest));
//		}
		if(0 != defaultCharge){
			amountDetails += " 固定费用"+MoneyUtil.formatMoney(MoneyArithUtil.convertMoneyToString(defaultCharge));
		}
		if(" (".equals(amountDetails))
			return "";
		amountDetails += ")";
		return amountDetails;
	}
	
	/**
	 * 清空List中的值
	 * @param repaymentPlanInfoRedisIntegration
	 */
	private void clearPlanExpenseListData(RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration){
		List<RepaymentPlanRedisDTO> repaymentPlanCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentPlanCorrect();
		if(!CollectionUtils.isEmpty(repaymentPlanCorrectList)){
			repaymentPlanCorrectList = new ArrayList<RepaymentPlanRedisDTO>();
			repaymentPlanInfoRedisIntegration.setRepaymentPlanCorrect(repaymentPlanCorrectList);
		}
		List<RepaymentPlanRedisDTO> repaymentPlanIncorrectList = repaymentPlanInfoRedisIntegration.getRepaymentPlanIncorrect();
		if(!CollectionUtils.isEmpty(repaymentPlanIncorrectList)){
			repaymentPlanIncorrectList = new ArrayList<RepaymentPlanRedisDTO>();
			repaymentPlanInfoRedisIntegration.setRepaymentPlanIncorrect(repaymentPlanIncorrectList);
		}
		List<RepaymentExpenseRedisDTO> repaymentExpenseCorrectList = repaymentPlanInfoRedisIntegration.getRepaymentExpenseCorrect();
		if(!CollectionUtils.isEmpty(repaymentExpenseCorrectList)){
			repaymentExpenseCorrectList = new ArrayList<RepaymentExpenseRedisDTO>();
			repaymentPlanInfoRedisIntegration.setRepaymentExpenseCorrect(repaymentExpenseCorrectList);
		}
		List<RepaymentExpenseRedisDTO> repaymentExpenseIncorrectList = repaymentPlanInfoRedisIntegration.getRepaymentExpenseIncorrect();
		if(!CollectionUtils.isEmpty(repaymentExpenseIncorrectList)){
			repaymentExpenseIncorrectList = new ArrayList<RepaymentExpenseRedisDTO>();
			repaymentPlanInfoRedisIntegration.setRepaymentExpenseIncorrect(repaymentExpenseIncorrectList);
		}
	}

	@Override
	public void prepareForManualInput(String redisKey, String id, String operatorId) throws Exception {
		if( StringUtils.isBlank(redisKey) || StringUtils.isBlank(id) || StringUtils.isBlank(operatorId) ){
			throw WebException.instance("参数为空，录入计划失败");
		}
		RepaymentLoanInfo repaymentLoanInfo = loanInfoMapper.selectByPrimaryKey(id);
		if(!StringUtils.equals(operatorId, repaymentLoanInfo.getFactorId())){
			throw WebException.instance("当前用户无权录入该放款信息的放款计划，录入计划失败");
		}
		RepaymentPlanInfoRedisIntegration repaymentPlanInfoRedisIntegration = (RepaymentPlanInfoRedisIntegration)redisCache.get(redisKey);
		if(repaymentPlanInfoRedisIntegration == null){
			throw WebException.instance("数据为空，录入计划失败");
		}
		redisCache.save(redisKey, repaymentPlanInfoRedisIntegration, getRedisExpireTimeFromProperties());
	}
}

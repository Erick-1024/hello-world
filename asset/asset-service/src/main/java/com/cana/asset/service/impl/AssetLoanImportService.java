package com.cana.asset.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.service.IAssetLoanImportService;
import com.cana.asset.service.IAssetLoanInfoService;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.asset.service.transaction.IAssetInvoiceTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.util.ValidateRules;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.loan.dto.AssetLoanInfoExcelDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanInfoRedisDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPlanExcelDTO;
import com.cana.vbam.common.asset.loan.dto.AssetLoanPlanRedisDTO;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.dto.RepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.RedisUtils;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.redis.client.SpringRedisClient;

/**
 * @author hu
 *
 */
@Service
public class AssetLoanImportService implements IAssetLoanImportService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetLoanInfoService loanInfoService;
	
	@Resource
	private IAssetLoanInfoTransactionService loanInfoTransactionService;
	
	@Resource
	private IAssetFactorBusinessTransactionService factorBusinessTransactionService;
	
	@Resource
	private IAssetInvoiceTransactionService invoiceTransactionService;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	@Resource
	private IVbamCommonService commonService;
	
	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	@Override
	public void importLoanInfoExcel2Redis(List<AssetLoanInfoExcelDTO> loanExcelList, String operatorId, String rediskey){
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey) || CollectionUtils.isEmpty(loanExcelList))
			throw WebException.instance("参数异常");
		
		AssetLoanInfoRedisDTO redisDTO = null;
		List<AssetLoanInfoExcelDTO> passLoanInfoList = null;
		List<AssetLoanInfoExcelDTO> notPassLoanInfoList = null;
		Set<String> loanInfoIds = null;
		Set<String> invoiceNos = null;
		String key = RedisUtils.generateAssetLoanInfoRedisKeyByOperator(rediskey,operatorId);
		Object excelRedis = redisCache.read(key);
		if(null != excelRedis){
			redisDTO = (AssetLoanInfoRedisDTO)excelRedis;
			passLoanInfoList = redisDTO.getPassLoanInfoList();
			notPassLoanInfoList = redisDTO.getNotPassLoanInfoList();
			loanInfoIds = redisDTO.getLoanInfoIds();
			invoiceNos = redisDTO.getInvoiceNos();
		}else{
			redisDTO = new AssetLoanInfoRedisDTO();
			passLoanInfoList = Lists.newArrayList();
			notPassLoanInfoList = Lists.newArrayList();
			loanInfoIds = new HashSet<>();
			invoiceNos = new HashSet<>();
		}
		for(AssetLoanInfoExcelDTO loanInfoExcelDTO : loanExcelList){
			try{
				if(StringUtils.isBlank(loanInfoExcelDTO.getLoanInfoId()))
					throw WebException.instance("放款编号不能为空");
				if(StringUtils.isBlank(loanInfoExcelDTO.getInvoiceNo()))
					throw WebException.instance("单证号不能为空");
				if(loanInfoIds.contains(loanInfoExcelDTO.getLoanInfoId()))
					throw WebException.instance("放款编号已存在");
				if(invoiceNos.contains(loanInfoExcelDTO.getInvoiceNo()))
					throw WebException.instance("单证号重复");
				checkLoanInfoIsValid(loanInfoExcelDTO);
				checkBusinessForLoanInfo(loanInfoExcelDTO, userVo);
				loanInfoTransactionService.checkImportAssetLoanInfoRequest(userVo, loanInfoExcelDTO);
				
				loanInfoIds.add(loanInfoExcelDTO.getLoanInfoId());
				invoiceNos.add(loanInfoExcelDTO.getInvoiceNo());
				passLoanInfoList.add(loanInfoExcelDTO);
			}catch (WebException e) {
				loanInfoExcelDTO.setCheckFailedMessage(e.getMessage());
				notPassLoanInfoList.add(loanInfoExcelDTO);
			}
		}
		redisDTO.setPassLoanInfoList(passLoanInfoList);
		redisDTO.setNotPassLoanInfoList(notPassLoanInfoList);
		redisDTO.setLoanInfoIds(loanInfoIds);
		redisDTO.setInvoiceNos(invoiceNos);
		redisCache.save(key, redisDTO);
	}
	
	/**
	 * 放款信息导入基础数据校验
	 * @param loanInfoExcelDTO
	 */
	private void checkLoanInfoIsValid(AssetLoanInfoExcelDTO loanInfoExcelDTO){
		try{
			int dayCountConvention = Integer.parseInt(loanInfoExcelDTO.getDayCountConventionStr());
			loanInfoExcelDTO.setDayCountConvention(dayCountConvention);
		}catch(Exception e){
			throw WebException.instance("计息基准天数不为数字");
		}
		try{
			int loanPeriod = Integer.parseInt(loanInfoExcelDTO.getLoanPeriodStr());
			loanInfoExcelDTO.setLoanPeriod(loanPeriod);
		}catch(Exception e){
			throw WebException.instance("贷款期限不为数字");
		}
		try{
			InterestRateUnit interestRateUnit = InterestRateUnit.getValue(loanInfoExcelDTO.getInterestRateUnitDesc());
			loanInfoExcelDTO.setInterestRateUnit(interestRateUnit.name());
		}catch(Exception e){
			throw WebException.instance("利率单位有误");
		}
		try{
			DateUnit dateUnit = DateUnit.getValue(loanInfoExcelDTO.getLoanPeriodUnitDesc());
			loanInfoExcelDTO.setLoanPeriodUnit(dateUnit.name());
		}catch(Exception e){
			throw WebException.instance("放款期限单位有误");
		}
		try{
			RepaymentType repaymentType = RepaymentType.getValue(loanInfoExcelDTO.getRepaymentTypeDesc());
			loanInfoExcelDTO.setRepaymentType(repaymentType.name());
		}catch(Exception e){
			throw WebException.instance("还款方式有误");
		}
		if(StringUtils.isBlank(loanInfoExcelDTO.getContractNo()))
			throw WebException.instance("业务合同号为空");
	}
	
	/**
	 * 放款信息导入业务逻辑校验
	 * @param loanInfoExcelDTO
	 */
	public void checkBusinessForLoanInfo(AssetLoanInfoExcelDTO loanInfoExcelDTO, UserVo userVo){
		if(!StringUtils.equals(loanInfoExcelDTO.getFinanceAmount(), loanInfoExcelDTO.getFinanceBalance()))
			throw WebException.instance("融资余额不等于融资金额");

		FactorBusinessDTO factorBusinessDTO = factorBusinessTransactionService.queryFactorBusinessInfoByBusinessContractNo(loanInfoExcelDTO.getContractNo(), userVo.getCustomerId());
		if(null == factorBusinessDTO)
			throw WebException.instance("业务合同不存在");
		if(!factorBusinessDTO.getCustomerName().equals(loanInfoExcelDTO.getFinanceName()))
			throw WebException.instance("融资客户不匹配");
		if(!factorBusinessDTO.getBusinessProduct().desc().equals(loanInfoExcelDTO.getBusinessProduct()))
			throw WebException.instance("业务产品不匹配");
		
		List<BusinessCounterpartyDTO> CounterpartyList= factorBusinessDTO.getCounterpartyList();
		if (CollectionUtils.isEmpty(CounterpartyList))
			throw WebException.instance("业务未添加交易对手信息");
		for(BusinessCounterpartyDTO counterpartyDTO : CounterpartyList){
			if(counterpartyDTO.getCounterparty().equals(loanInfoExcelDTO.getCounterpartyName()))
				loanInfoExcelDTO.setCounterpartyId(counterpartyDTO.getCounterpartyId());
		}
		if(StringUtils.isBlank(loanInfoExcelDTO.getCounterpartyId()))
			throw WebException.instance("交易对手不存在于业务中");
		
		InvoiceQueryDTO queryDTO = new InvoiceQueryDTO();
		queryDTO.setBusinessContractNo(loanInfoExcelDTO.getContractNo());
		queryDTO.setCounterpartyId(loanInfoExcelDTO.getCounterpartyId());
		InvoiceListDTO invoiceDTOs = invoiceTransactionService.getInvByExample(queryDTO);
		if (invoiceDTOs == null || CollectionUtils.isEmpty(invoiceDTOs.getInvoiceInfoDTOs()))
			throw WebException.instance("应收账款不存在");
		InvoiceInfoDTO invoiceInfo = null;
		for(InvoiceInfoDTO invoiceInfoDTO : invoiceDTOs.getInvoiceInfoDTOs()){
			if(invoiceInfoDTO.getInvoiceNo().equals(loanInfoExcelDTO.getInvoiceNo())){
				invoiceInfo = invoiceInfoDTO;
				break;
			}
		}
		if(null == invoiceInfo)
			throw WebException.instance("单证号不存在于应收账款");
		if(!invoiceInfo.getNominvoiceAmt().equals(loanInfoExcelDTO.getNominvoiceAmt()))
			throw WebException.instance("单证面额不匹配");
		if(!invoiceInfo.getInvoiceAmt().equals(loanInfoExcelDTO.getInvoiceAmt()))
			throw WebException.instance("应收金额不匹配");
		
		loanInfoExcelDTO.setInvoiceInfoIds(Lists.newArrayList(invoiceInfo.getId()));
	}

	@Override
	public ListResult<AssetLoanInfoExcelDTO> getLoanInfoFromRedis(String redisKey, String operatorId, boolean passed,
			int page, int pageSize) {
		page = page < 1 ? 1 : page;
		pageSize = page < 1 ? 10 : pageSize;
		if(redisCache.read(RedisUtils.generateAssetLoanInfoRedisKeyByOperator(redisKey, operatorId)) == null)
			return ListResult.fail("无数据");
		AssetLoanInfoRedisDTO  redisDTO = (AssetLoanInfoRedisDTO)redisCache.read(RedisUtils.generateAssetLoanInfoRedisKeyByOperator(redisKey, operatorId));
		List<AssetLoanInfoExcelDTO> loanInfoList = null;
		if(passed)
			loanInfoList = redisDTO.getPassLoanInfoList();
		else
			loanInfoList = redisDTO.getNotPassLoanInfoList();
		int totalNum = loanInfoList.size();
		loanInfoList = loanInfoList.subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, totalNum));
		return ListResult.success(loanInfoList, totalNum);
	}
	
	private int getStartIndex(int page, int pageSize){
		return (page - 1) * pageSize;
	}
	
	private <T> int getEndIndex(int page, int pageSize, int size) {
		return page * pageSize < size ? page * pageSize : size;
	}

	@Override
	public void importLoanInfoExcel2DB(String operatorId, String rediskey) {
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey))
			throw WebException.instance("参数异常");
		
		if(redisCache.read(RedisUtils.generateAssetLoanInfoRedisKeyByOperator(rediskey, operatorId)) == null)
			throw WebException.instance("无数据");
		AssetLoanInfoRedisDTO  redisDTO = (AssetLoanInfoRedisDTO)redisCache.read(RedisUtils.generateAssetLoanInfoRedisKeyByOperator(rediskey, operatorId));
		List<AssetLoanInfoExcelDTO> loanInfoList = redisDTO.getPassLoanInfoList();
		if(CollectionUtils.isEmpty(loanInfoList))
			throw WebException.instance("无校验通过放款");
		for(AssetLoanInfoExcelDTO loanInfoExcelDTO: loanInfoList){
			try{
				checkLoanInfoIsValid(loanInfoExcelDTO);
				checkBusinessForLoanInfo(loanInfoExcelDTO, userVo);
			}catch(WebException e){
				logger.info(e.getMessage());
				throw WebException.instance("数据发生变更, 请重新导入");
			}
		}
		List<EditAssetLoanRequest> requestList = Lists.newArrayList();
		requestList.addAll(loanInfoList);
		loanInfoTransactionService.importAssetLoanInfo(userVo, requestList);
	}

	@Override
	public void importLoanPlanExcel2Redis(List<AssetLoanPlanExcelDTO> loanExcelList, String operatorId,
			String rediskey) {
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey) || CollectionUtils.isEmpty(loanExcelList))
			throw WebException.instance("参数异常");
		
		AssetLoanPlanRedisDTO redisDTO = null;
		List<AssetLoanPlanExcelDTO> loanPlanList = null;
		List<AssetLoanPlanExcelDTO> passLoanPlanList = Lists.newArrayList();
		List<AssetLoanPlanExcelDTO> notPassLoanPlanList = Lists.newArrayList();
		String key = RedisUtils.generateAssetLoanPlanRedisKeyByOperator(rediskey,operatorId);
		Object excelRedis = redisCache.read(key);
		if(null != excelRedis){
			redisDTO = (AssetLoanPlanRedisDTO)excelRedis;
			loanPlanList = redisDTO.getLoanPlanExcelList();
		}else{
			redisDTO = new AssetLoanPlanRedisDTO();
			loanPlanList = Lists.newArrayList();
		}
		loanPlanList.addAll(loanExcelList);
		redisDTO.setLoanPlanExcelList(loanPlanList);
		Map<String, List<AssetLoanPlanExcelDTO>> loanMap = convertPlanByLoanInfo(loanPlanList);
		for(String loanNo : loanMap.keySet()){
			List<AssetLoanPlanExcelDTO> loanPlanExcelList = loanMap.get(loanNo);
			LoanInfo loanInfo = null;
			try{
				loanInfo = loanInfoService.getLoanInfo(loanNo);
			}catch(WebException e){
				logger.info(e.getMessage());
			}
			if(checkLoanPlan(loanPlanExcelList, loanInfo, userVo)){
				EditAssetLoanRequest request = convertPlan2AssetLoanRequest(loanInfo, loanPlanExcelList);
				try{
					loanInfoTransactionService.checkImportAssetLoanPlanRequest(userVo, request);
					passLoanPlanList.addAll(loanPlanExcelList);
					continue;
				}catch(WebException e){
					batchSetFailedMassage(loanPlanExcelList, e.getMessage());
				}
			}
			notPassLoanPlanList.addAll(loanPlanExcelList);
		}
		redisDTO.setPassLoanPlanExcelList(passLoanPlanList);
		redisDTO.setNotPassLoanPlanExcelList(notPassLoanPlanList);
		redisCache.save(key, redisDTO);
	}

	@Override
	public ListResult<AssetLoanPlanExcelDTO> getLoanPlanFromRedis(String redisKey, String operatorId, boolean passed,
			int page, int pageSize) {
		page = page < 1 ? 1 : page;
		pageSize = page < 1 ? 10 : pageSize;
		if(redisCache.read(RedisUtils.generateAssetLoanPlanRedisKeyByOperator(redisKey, operatorId)) == null)
			return ListResult.fail("无数据");
		AssetLoanPlanRedisDTO  redisDTO = (AssetLoanPlanRedisDTO)redisCache.read(RedisUtils.generateAssetLoanPlanRedisKeyByOperator(redisKey, operatorId));
		List<AssetLoanPlanExcelDTO> loanPlanList = null;
		if(passed)
			loanPlanList = redisDTO.getPassLoanPlanExcelList();
		else
			loanPlanList = redisDTO.getNotPassLoanPlanExcelList();
		int totalNum = loanPlanList.size();
		loanPlanList = loanPlanList.subList(getStartIndex(page, pageSize), getEndIndex(page, pageSize, totalNum));
		return ListResult.success(loanPlanList, totalNum);
	}

	@Override
	public void importLoanPlanExcel2DB(String operatorId, String rediskey) {
		UserVo userVo = memberQueryApi.findUserById(operatorId);
		if(null == userVo || StringUtils.isBlank(rediskey))
			throw WebException.instance("参数异常");
		
		Object object = redisCache.read(RedisUtils.generateAssetLoanPlanRedisKeyByOperator(rediskey, operatorId));
		if(object == null)
			throw WebException.instance("无数据");
		AssetLoanPlanRedisDTO  redisDTO = (AssetLoanPlanRedisDTO)object;
		List<AssetLoanPlanExcelDTO> loanPlanList = redisDTO.getPassLoanPlanExcelList();
		if(CollectionUtils.isEmpty(loanPlanList))
			throw WebException.instance("无校验通过还款计划");
		
		Map<String, List<AssetLoanPlanExcelDTO>> loanMap = convertPlanByLoanInfo(loanPlanList);
		List<EditAssetLoanRequest> requestList = Lists.newArrayList();
		for(String loanNo : loanMap.keySet()){
			List<AssetLoanPlanExcelDTO> loanPlanExcelList = loanMap.get(loanNo);
			LoanInfo loanInfo = loanInfoService.getLoanInfo(loanNo);
			
			if(checkLoanPlan(loanPlanExcelList, loanInfo, userVo)){
				EditAssetLoanRequest request = convertPlan2AssetLoanRequest(loanInfo, loanPlanExcelList);
				requestList.add(request);
			}else
				throw WebException.instance("数据发生变更, 请重新导入");
		}
		loanInfoTransactionService.importAssetLoanPlan(userVo, requestList);
	}
	
	private Map<String, List<AssetLoanPlanExcelDTO>> convertPlanByLoanInfo(List<AssetLoanPlanExcelDTO> loanPlanList){
		Collections.sort(loanPlanList, new Comparator<AssetLoanPlanExcelDTO>(){
			@Override
			public int compare(AssetLoanPlanExcelDTO o1,AssetLoanPlanExcelDTO o2) {
				if(StringUtils.equals(o1.getLoanInfoId(), o2.getLoanInfoId())){
					return o1.getPeriod().compareTo(o2.getPeriod());
				}else{
					return o1.getLoanInfoId().compareTo(o2.getLoanInfoId());
				}
			}
		});
		Map<String, List<AssetLoanPlanExcelDTO>> loanMap = new HashMap<>();
		String loanNo = "";
		List<AssetLoanPlanExcelDTO> planList = Lists.newArrayList();
		for(AssetLoanPlanExcelDTO loanPlanExcel : loanPlanList){
			if(!loanNo.equals(loanPlanExcel.getLoanInfoId())){
				if(CollectionUtils.isNotEmpty(planList)){
					loanMap.put(loanNo, planList);
					planList = Lists.newArrayList();					
				}
				loanNo = loanPlanExcel.getLoanInfoId();
			}
			planList.add(loanPlanExcel);
		}
		if(CollectionUtils.isNotEmpty(planList)){
			loanMap.put(loanNo, planList);
		}
		return loanMap;
	}
	
	private boolean checkLoanPlan(List<AssetLoanPlanExcelDTO> loanPlanExcelList, LoanInfo loanInfo, UserVo userVo){
		boolean pass = true;
		int period = 0;
		for(AssetLoanPlanExcelDTO loanInfoExcelDTO: loanPlanExcelList){
			if(null == loanInfo){
				loanInfoExcelDTO.setCheckFailedMessage("放款信息不存在");
				pass = false;
				continue;
			}
			
			try{
				checkLoanPlanIsValid(loanInfoExcelDTO);
			}catch(WebException e){
				loanInfoExcelDTO.setCheckFailedMessage(e.getMessage());
				pass = false;
				break;
			}
			
			if(!loanInfo.getCustomerName().equals(loanInfoExcelDTO.getFinanceName())){
				loanInfoExcelDTO.setCheckFailedMessage("融资客户名称不匹配");
				pass = false;
				break;
			}
			if(!MoneyArithUtil.convertMoneyToString(loanInfo.getFinanceAmount()).equals(loanInfoExcelDTO.getFinanceAmount())){
				loanInfoExcelDTO.setCheckFailedMessage("融资金额不匹配");
				pass = false;
				break;
			}
			if(!loanInfo.getLoanDate().equals(loanInfoExcelDTO.getLoanDate())){
				loanInfoExcelDTO.setCheckFailedMessage("放款日不匹配");
				pass = false;
				break;
			}
			if(!loanInfo.getDueDate().equals(loanInfoExcelDTO.getDueDate())){
				loanInfoExcelDTO.setCheckFailedMessage("到期日不匹配");
				pass = false;
				break;
			}
			
			loanInfoExcelDTO.setContractNo(loanInfo.getBusinessContractNo());
			loanInfoExcelDTO.setBusinessProduct(BusinessProduct.valueOf(loanInfo.getBusinessProduct()).desc());
			
			int currentPeriod = Integer.parseInt(loanInfoExcelDTO.getPeriod());
			if( period != 0 && (period + 1) != currentPeriod){
				loanInfoExcelDTO.setCheckFailedMessage("期数不连续"); 
				pass = false;
				break;
			}
			period = currentPeriod;
		}
		
		return pass;
	}
	
	/**
	 * 还款计划基础校验
	 * @param loanPlanExcel
	 */
	private void checkLoanPlanIsValid(AssetLoanPlanExcelDTO loanPlanExcel){
		if(StringUtils.isBlank(loanPlanExcel.getFinanceName()))
			throw WebException.instance("融资客户不能为空");
		if (StringUtils.isBlank(loanPlanExcel.getFinanceAmount())
				|| !ValidateRules.regexAmountCheck(loanPlanExcel.getFinanceAmount())
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getFinanceAmount()) <= 0)
			throw WebException.instance("融资金额不正确");
		
		if (StringUtils.isBlank(loanPlanExcel.getFinanceBalance())
				|| !ValidateRules.regexAmountCheck(loanPlanExcel.getFinanceBalance())
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getFinanceBalance()) < 0)
			throw WebException.instance("融资余额不正确");
		if(MoneyUtil.yuan2Cent(loanPlanExcel.getFinanceAmount()) < MoneyUtil.yuan2Cent(loanPlanExcel.getFinanceBalance()))
			throw WebException.instance("融资金额不能小于融资余额");
		
		if (!DateTimeUtil.validateDate10(loanPlanExcel.getLoanDate()))
			throw WebException.instance("放款日格式不正确");
		if (!DateTimeUtil.validateDate10(loanPlanExcel.getDueDate()))
			throw WebException.instance("到期日格式不正确");
		
		try{
			Integer.parseInt(loanPlanExcel.getPeriod());
		}catch(Exception e){
			throw WebException.instance("期数不为数字");
		}
		
		if (!DateTimeUtil.validateDate10(loanPlanExcel.getValueDate()))
			throw WebException.instance("起息日格式不正确");
		
		if (!DateTimeUtil.validateDate10(loanPlanExcel.getSettleInterestDate()))
			throw WebException.instance("结息日格式不正确");
		
		if (loanPlanExcel.getSettleInterestDate().compareTo(loanPlanExcel.getValueDate()) <= 0)
			throw WebException.instance("结息日必须大于起息日");
		
		if (!DateTimeUtil.validateDate10(loanPlanExcel.getRepaymentDate()))
			throw WebException.instance("还款日格式不正确");
		if (loanPlanExcel.getRepaymentDate().compareTo(loanPlanExcel.getSettleInterestDate()) != 0)
			throw WebException.instance("还款日必须等于结息日");
		
		if (StringUtils.isBlank(loanPlanExcel.getAccountPrincipal())
				|| !ValidateRules.regexAmountCheck(loanPlanExcel.getAccountPrincipal())
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getAccountPrincipal()) < 0)
			throw WebException.instance("应还本金不正确");
		if(MoneyUtil.yuan2Cent(loanPlanExcel.getAccountPrincipal()) > MoneyUtil.yuan2Cent(loanPlanExcel.getFinanceBalance()))
			throw WebException.instance("应还本金不能大于融资余额");
		
		if (StringUtils.isBlank(loanPlanExcel.getAccountInterest())
				|| !ValidateRules.regexAmountCheck(loanPlanExcel.getAccountInterest())
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getAccountInterest()) < 0)
			throw WebException.instance("应还利息不正确");
		
		if (StringUtils.isBlank(loanPlanExcel.getAccountOverdue())
				|| !ValidateRules.regexAmountCheck(loanPlanExcel.getAccountOverdue())
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getAccountOverdue()) < 0)
			throw WebException.instance("逾期费不正确");
		
		if (StringUtils.isBlank(loanPlanExcel.getAccountAmount())
				|| !ValidateRules.regexAmountCheck(loanPlanExcel.getAccountAmount())
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getAccountAmount()) < 0
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getAccountAmount()) != (MoneyUtil.yuan2Cent(loanPlanExcel.getAccountPrincipal()) +
						MoneyUtil.yuan2Cent(loanPlanExcel.getAccountInterest()) + MoneyUtil.yuan2Cent(loanPlanExcel.getAccountOverdue())))
			throw WebException.instance("应还总金额不正确");
		
		if (StringUtils.isBlank(loanPlanExcel.getAccountAmount())
				|| !ValidateRules.regexAmountCheck(loanPlanExcel.getAccountAmount())
				|| MoneyUtil.yuan2Cent(loanPlanExcel.getAccountAmount()) < 0)
			throw WebException.instance("应还总金额不正确");
		
		if(SettleStatus.UNSETTLE.desc().equals(loanPlanExcel.getSettleStatusDesc())){
			SettleStatus settleStatus = SettleStatus.getValue(loanPlanExcel.getSettleStatusDesc());
			loanPlanExcel.setSettleStatus(settleStatus.name());
		}else{
			throw WebException.instance("结清状态有误");
		}
		
		if(SettleStatus.UNSETTLE.equals(loanPlanExcel.getSettleStatus())){
			if(loanPlanExcel.getRepaymentDate().compareTo(commonService.getCurrentDate()) < 0)
				throw WebException.instance("还款日不能小于当前营业日");
		}
	}
	
	private EditAssetLoanRequest convertPlan2AssetLoanRequest(LoanInfo loanInfo, List<AssetLoanPlanExcelDTO> loanPlanExcelList){
		EditAssetLoanRequest request = new EditAssetLoanRequest();
		request.setLoanInfoId(loanInfo.getId());
		request.setContractNo(loanInfo.getBusinessContractNo());
		request.setLoanDate(loanInfo.getLoanDate());
		request.setDueDate(loanInfo.getDueDate());
		request.setFinanceAmount(MoneyArithUtil.convertMoneyToString(loanInfo.getFinanceAmount()));
		List<LoanPlanDTO> planList = Lists.newArrayList();
		planList.addAll(loanPlanExcelList);
		request.setPlans(planList);
		return request;
	}
	
	private void batchSetFailedMassage(List<AssetLoanPlanExcelDTO> loanPlanExcelList, String failedMassage){
		for(AssetLoanPlanExcelDTO loanPlanExcel : loanPlanExcelList){
			loanPlanExcel.setCheckFailedMessage(failedMassage);
		}
	}
}

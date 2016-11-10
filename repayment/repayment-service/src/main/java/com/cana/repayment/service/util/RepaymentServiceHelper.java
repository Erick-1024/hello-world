package com.cana.repayment.service.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.message.client.message.MessageClient;
import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentRuleMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.dao.po.RepaymentRuleExample;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.SmsMessageType;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.message.dto.AdjustSuccessMessage;
import com.cana.vbam.common.repayment.message.dto.RepaymentSuccessMessage;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.InterestRateConverter;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.vj.template.VJSmsMessageTemplate;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskState;
import com.travelzen.framework.retry.dict.RetryTaskType;
import com.travelzen.framework.retry.policy.RetryTaskBackOffPolicy;

@Service
public class RepaymentServiceHelper implements IRepaymentServiceHelper{
	
	@Resource
	private RepaymentRuleMapper repaymentRuleMapper;
	
	@Resource
	private LoanInfoConfigMapper loanInfoConfigMapper;
	
	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	@Resource
    private MessageClient messageClient;
	
	/**
	 * 查找此时适用于保理商和融资客户的还款规则
	 * @param factorId
	 * @param financeId
	 * @return
	 */
	@Override
	public RepaymentRule getRepaymentRule(String factorId, String financeId){
		RepaymentRuleExample repaymentRuleExample = new RepaymentRuleExample();
		repaymentRuleExample.createCriteria().andFactorIdEqualTo(factorId).andFianceCustomerIdsLike("%" + financeId + "%");
		List<RepaymentRule> repaymentRules = repaymentRuleMapper.selectByExample(repaymentRuleExample);
		RepaymentRule repaymentRule = null;
		if(CollectionUtils.isNotEmpty(repaymentRules)){
			repaymentRule = repaymentRules.get(0);
		}else {
			RepaymentRuleExample defaultRepaymentRuleExample = new RepaymentRuleExample();
			defaultRepaymentRuleExample.createCriteria().andFactorIdEqualTo(factorId).andFianceCustomerIdsIsNull();
			List<RepaymentRule> defaultRepaymentRules = repaymentRuleMapper.selectByExample(defaultRepaymentRuleExample);
			if(CollectionUtils.isNotEmpty(defaultRepaymentRules)){
				repaymentRule = defaultRepaymentRules.get(0);
			}
		}
		return repaymentRule;
	}
	
	@Override
	public void createLoanInfoConfig(RepaymentLoanInfo loanInfo, BigDecimal penaltyRate){
		LoanInfoConfig loanInfoConfig = new LoanInfoConfig();
		RepaymentRule repaymentRule = getRepaymentRule(loanInfo.getFactorId(), loanInfo.getFinanceId());
		if(null == repaymentRule)
			throw WebException.instance("默认还款规则不存在");
		BeanUtils.copyProperties(repaymentRule, loanInfoConfig, "id", "createTime", "updateTime" );
		loanInfoConfig.setId(loanInfo.getId());
		loanInfoConfig.setCreateTime(new Date());
		// 自动生成的放款，展期利率等于正常日利率，修改于2016-09-20
		if (InputMethod.AUTO.name().equals(loanInfo.getInputMethod()))
			loanInfoConfig.setExtensionRatio(InterestRateConverter.getDailyRate(
					InterestRateUnit.valueOf(loanInfo.getInterestRateUnit()), loanInfo.getInterestRate()));
		if (penaltyRate != null)
			loanInfoConfig.setPenaltyRate(MoneyArithUtil.roundInterestRate(penaltyRate));
		loanInfoConfigMapper.insertSelective(loanInfoConfig);
	}
	/**
	 * 根据融资客户id和产品id锁定未还清的放款信息, 返回结果按照放款日期排序
	 * @param financeId
	 * @return
	 */
	@Override
	public List<RepaymentLoanInfo> lockUnsettleLoanInfosByFinanceIdAndProductId(String financeId, String businessProductId){
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andFinanceIdEqualTo(financeId)
		                        .andBusinessProductIdEqualTo(businessProductId)
		                        .andSettleStatusNotEqualTo("SETTLED");
		example.setOrderByClause("loan_date");
		return loanInfoMapper.lockByExample(example);
		 
	}
	
	/**
	 * 生成账扣子任务id
	 * @return
	 */
	@Override
	public String generateTaskItemId(){
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_TASK_ITEM_ID, 4);
	}

	@Override
	public void insertRepaymentSuccessNotificationRetryTaskRecord(RepaymentLoanInfoBO loanInfoBO, RepaymentSingleCollectBO repaymentRecord) {
		RepaymentSuccessMessage message = new RepaymentSuccessMessage();
		message.setMessageId(repaymentRecord.getId());
		message.setRepaymentSummaryRecordId(repaymentRecord.getId());
		message.setLoanInfoId(loanInfoBO.getId());
		message.setAccountNo(loanInfoBO.getAccountNo());
		message.setFactorId(loanInfoBO.getFactorId());
		message.setFinanceId(loanInfoBO.getFinanceId());
		message.setOutCustomerId(loanInfoBO.getOutCustomerId());
		message.setLoanNo(loanInfoBO.getLoanNo());
		message.setRepaymentDate(repaymentRecord.getRepaymentDate());
		message.setTotal(repaymentRecord.calcTotal());
		message.setBusinessProductId(loanInfoBO.getBusinessProductId());
		RepaymentMethod repaymentMethod = RepaymentMethod.valueOf(repaymentRecord.getRepaymentType());
		message.setRepaymentMethod(repaymentMethod);
		if(RepaymentMethod.REFUND == repaymentMethod && Constants.TRAVELZEN_FINANCE_PRODUCT_ID.equals(loanInfoBO.getBusinessProductId())){
			message.setInstitutionName(Institution.travelzen.desc());
		}
		
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.REPAYMENT_SUCCESS_NOTIFY.name());
		task.setTaskId(repaymentRecord.getId());
		task.setState(RetryTaskState.not_begin.name());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setData(new Gson().toJson(message));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);	
	}
	
	@Override
	public void insertActiveRepaymentSuccessNotificationRetryTaskRecord(RepaymentLoanInfo loanInfo, RepaymentSingleCollect repaymentRecord){
		
		RepaymentSuccessMessage message = new RepaymentSuccessMessage();
		message.setFinanceCompany(loanInfo.getFinanceCompany());
		message.setMessageId(repaymentRecord.getId());
		message.setLoanInfoId(loanInfo.getId());
		message.setAccountNo(loanInfo.getAccountNo());
		message.setFactorId(loanInfo.getFactorId());
		message.setFinanceId(loanInfo.getFinanceId());
		message.setOutCustomerId(loanInfo.getOutCustomerId());
		message.setLoanNo(loanInfo.getLoanNo());
		message.setRepaymentDate(repaymentRecord.getRepaymentDate());
		message.setTotal(repaymentRecord.getRepaymentTotalAmount());
		message.setBusinessProductId(loanInfo.getBusinessProductId());
		message.setRepaymentMethod(RepaymentMethod.valueOf(repaymentRecord.getRepaymentType()));
		
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.REPAYMENT_SUCCESS_NOTIFY.name());
		task.setTaskId(repaymentRecord.getId());
		task.setState(RetryTaskState.not_begin.name());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setData(new Gson().toJson(message));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);	
	}

	@Override
	public RepaymentLoanInfo lockLoanInfoByFinanceIdAndProductIdAndLoanDate(String factorId, String financeId, String businessProductId,
			String loanDate, String outCustomerId) {
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		RepaymentLoanInfoExample.Criteria criteria = example.createCriteria();
		criteria.andFactorIdEqualTo(factorId).andFinanceIdEqualTo(financeId).andBusinessProductIdEqualTo(businessProductId)
				.andLoanDateEqualTo(loanDate);
		if (StringUtils.isNotBlank(outCustomerId))
			criteria.andOutCustomerIdEqualTo(outCustomerId);
		example.setOrderByClause("loan_no");
		List<RepaymentLoanInfo> loanInfos = loanInfoMapper.lockByExample(example);
		if(CollectionUtils.isEmpty(loanInfos))
			return null;
		else return loanInfos.get(0); 
	}

	@Override
	public String generateLoanNo() {
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_LOAN_NO, 4);
	}

	@Override
	public void insertAdjustSuccessNotificationRetryTaskRecord(RepaymentLoanInfo loanInfo) {
		String id = generateAdjustNo();
		AdjustSuccessMessage message = new AdjustSuccessMessage();
		message.setMessageId(id);
		message.setFactorCompany(loanInfo.getFactorCompany());
		message.setLoanInfoId(loanInfo.getId());
		message.setFactorId(loanInfo.getFactorId());
		message.setFinanceId(loanInfo.getFinanceId());
		message.setLoanNo(loanInfo.getLoanNo());
		
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.ADJUST_SUCCESS_NOTIFY.name());
		task.setTaskId(id);
		task.setState(RetryTaskState.not_begin.name());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setData(new Gson().toJson(message));
		task.setTaskDeadline(DateUtils.addDays(new Date(), 1));
		retryTaskMapper.insertSelective(task);	
	}

	private String generateAdjustNo() {
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.REPAYMENT_ADJUSTMENT_ID, 5);
	}

	@Override
	public RepaymentLoanInfoBO lockLoanInfoById(String id) {
		return new RepaymentLoanInfoBO(loanInfoMapper.lockByPrimaryKey(id));
	}

	@Override
	public void createLoanInfoConfig(RepaymentLoanInfo loanInfo, CreateLoanRequest request) {
		LoanInfoConfig loanInfoConfig = new LoanInfoConfig();
		loanInfoConfig.setId(loanInfo.getId());
		loanInfoConfig.setFactorTransferInAccountNo(request.getFactorTransferInAccountNo());
		loanInfoConfig.setDeductionTime(request.getDeductionTime());
		loanInfoConfig.setDeductionRule(request.getDeductionRule().name());
		loanInfoConfig.setExtensionDays(request.getExtensionDays());
		// 自动生成的放款，展期利率等于正常日利率，修改于2016-09-20
		if (InputMethod.AUTO.name().equals(loanInfo.getInputMethod())) {
			loanInfoConfig.setExtensionRatio(InterestRateConverter.getDailyRate(
					InterestRateUnit.valueOf(loanInfo.getInterestRateUnit()), loanInfo.getInterestRate()));
//		} else {
//			BigDecimal extensionRatio = null;
//			if (ChargeMethod.AMOUNT == request.getExtensionChargeMethod())
//				extensionRatio = request.getExtensionRatio();
//			else if (ChargeMethod.RATIO == request.getExtensionChargeMethod()) {
//				extensionRatio = request.getExtensionRatio().add(BigDecimal.ONE).multiply(InterestRateConverter.getDailyRate(request.getInterestRateUnit(), request.getInterestRate()));
//			}
//			loanInfoConfig.setExtensionRatio(MoneyArithUtil.roundInterestRate(extensionRatio));
		}
		BigDecimal penaltyRatio = null;
		if (ChargeMethod.AMOUNT == request.getPenaltyChargeMethod())
			penaltyRatio = request.getPenaltyRatio();
		else if (ChargeMethod.RATIO == request.getPenaltyChargeMethod()) {
			penaltyRatio = request.getPenaltyRatio().add(BigDecimal.ONE).multiply(InterestRateConverter.getDailyRate(request.getInterestRateUnit(), request.getInterestRate()));
		}
		loanInfoConfig.setPenaltyRate(MoneyArithUtil.roundInterestRate(penaltyRatio));
		loanInfoConfig.setEarlyRepaymentChargeRatio(request.getEarlyRepaymentChargeRatio());
		loanInfoConfig.setCreateTime(new Date());
		loanInfoConfigMapper.insertSelective(loanInfoConfig);
	}

	@Override
	public String generateVJMessageContent(String template, List<String> dataItems) {
		for(String dataItem : dataItems){
			if(StringUtils.isNotBlank(dataItem)){
				template = template.replaceFirst(VJSmsMessageTemplate.matchcharactersReg, dataItem);
			}
		}
		return template;
	}

	@Override
	public void sendVJSmsMessage(String content, String receivePhoneNum) {
		SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
		smsMessageDTO.setContent(content);
		smsMessageDTO.setPrefix(VJSmsMessageTemplate.prefix);
		smsMessageDTO.setReceivePhoneNum(receivePhoneNum);
		smsMessageDTO.setSmsMessageType(SmsMessageType.NOTICE);
		messageClient.sendSmsMessage(smsMessageDTO);
		
	}
}

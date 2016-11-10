package com.cana.credit.service.transaction.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.credit.dao.mapper.CreditTableLockMapper;
import com.cana.credit.dao.mapper.gen.CreditTradeMapper;
import com.cana.credit.dao.mapper.gen.CreditTransferMapper;
import com.cana.credit.dao.mapper.gen.OutCustomerMapper;
import com.cana.credit.dao.po.CreditTrade;
import com.cana.credit.dao.po.CreditTradeExample;
import com.cana.credit.dao.po.CreditTransfer;
import com.cana.credit.dao.po.CreditTransferExample;
import com.cana.credit.dao.po.OutCustomer;
import com.cana.credit.dao.po.OutCustomerExample;
import com.cana.credit.dao.utils.IDGenerator;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitAuditMapper;
import com.cana.credit.limit.dao.mapper.gen.CreditLimitMapper;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.limit.service.transaction.ICommonCreditLimitTransactionService;
import com.cana.credit.service.IRetryTaskService;
import com.cana.credit.service.convertors.CreditTradeConvertor;
import com.cana.credit.service.transaction.ICreditLimitTransactionService;
import com.cana.credit.service.transaction.ICreditTradeTransactionService;
import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.FactorInfo;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.credit.dto.trade.Refund2CustomerSuccessMessage;
import com.cana.vbam.common.credit.enums.CreditLimitAuditType;
import com.cana.vbam.common.credit.enums.CreditTradeStatus;
import com.cana.vbam.common.credit.enums.CreditTradeType;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.enums.CreditTransferType;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.RepaymentRequest;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundInfo;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundResult;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskState;
import com.travelzen.framework.retry.dict.RetryTaskType;
import com.travelzen.framework.retry.policy.RetryTaskBackOffPolicy;
import com.travelzen.framework.util.DateUtils;

@Service
public class CreditTradeTransactionServiceImpl implements ICreditTradeTransactionService{
	
	private static final Logger logger = LoggerFactory.getLogger(CreditTradeTransactionServiceImpl.class);

	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Resource
	private CreditTradeMapper creditTradeMapper;
	
	@Resource
	private OutCustomerMapper outCustomerMapper;
	
	@Resource
	private CreditLimitMapper creditLimitMapper;
	
	@Resource
	private CreditTableLockMapper creditTableLockMapper;
	
	@Resource
    private CreditTransferMapper creditTransferMapper;
	
	@Resource
	private IRepaymentTransactionService repaymentTransactionServiceImpl;
	
	@Resource
	private IRetryTaskService retryTaskServiceImpl;
	
	@Resource
	private CreditLimitAuditMapper creditLimitAuditMapper;
	
	@Resource
	private ICreditLimitTransactionService creditLimitTransactionServiceImpl;
	
	@Resource
	private ICommonCreditLimitTransactionService commonCreditLimitTransactionService;
	
	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	@Resource 
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private IRepaymentServiceHelper serviceHelper;
	
	/**
	 * 参数检查－新建或更新融资模块放款－新建授信支付交易记录－扣额度－新增额度变化记录－通知真旅支付状态为成功－新建一条转账记录
	 */
	@Override
	public CreditTransfer creditPay(ProjectInfo projectInfo, CreditPayDTO creditPayDTO, ContractInfoDTO contractInfoDTO) {
		Long paymentFee = creditPayDTO.getPaymentFee();
		OutCustomer outCustomer = getOutCustomerByCustomerId(creditPayDTO.getCustomerId(),creditPayDTO.getInstitution());
		CreditLimit creditLimit = creditLimitTransactionServiceImpl.getCreditLimitByMemberId(outCustomer.getMemberId(), outCustomer.getOutCustomerId());
		checkLimitBalanceEnough(creditLimit,paymentFee);
		checkDuplicatedTrade(creditPayDTO.getInstitution(), creditPayDTO.getTradeNo());

		Date tradeDate = new Date();
		FactorInfo factorInfo = getFactorInfoFromProject(projectInfo, contractInfoDTO.getFactorId());
		if (factorInfo == null)
			throw WebException.instance(ReturnCode.ERROR, "资金方不存在");

		String summaryId = addTravelzenFinanceLoan(projectInfo, factorInfo, tradeDate,paymentFee,outCustomer, contractInfoDTO, creditLimit);
		String tradeId = IDGenerator.generateCreditTradeId();
		//增加交易记录
		CreditTrade creditTrade = CreditTradeConvertor.convertCreditPayDTO2CreditTrade(creditPayDTO);
		creditTrade.setId(tradeId);
		creditTrade.setSummaryId(summaryId);
		creditTrade.setTradeStartTime(tradeDate);
		creditTrade.setTradeEndTime(tradeDate);
		creditTrade.setFinaceCustomerId(creditLimit.getMemberId());
		creditTrade.setFinaceCustomerName(creditLimit.getCompanyName());
		creditTradeMapper.insertSelective(creditTrade);
		
		Long newUsedLimit = (creditLimit.getUsedLimit()==null?0:creditLimit.getUsedLimit())+paymentFee;
		creditLimitTransactionServiceImpl.insertCreditLimitAudit(creditLimit, CreditLimitAuditType.PAYMENT, newUsedLimit, tradeId);
		updateCreditLimit(creditLimit,newUsedLimit);
		//通知真旅支付状态为成功
		String outTradeNo = creditPayDTO.getTradeNo();
		String creditTradeType = CreditTradeType.PAYMENT.name();
		String creditTradeStatus = CreditTradeStatus.SUCCESS.name();
		String creditTransferEndTimesStr = DateTimeUtil.format(tradeDate, DateTimeUtil.DATE_TIME_PATTERN);
		String sign = null;
		try {
			sign = new String(vbamCommonServiceImpl.sign((outTradeNo + tradeId + creditTradeType + creditTradeStatus + creditTransferEndTimesStr).getBytes(), Institution.cana.name(), false));
		} catch (Exception e) {
			logger.error("支付成功的签名操作失败！", e);
			throw WebException.instance(ReturnCode.TP3022);
		}
		if(StringUtils.isNotBlank(creditPayDTO.getNotifyURL()))
			retryTaskServiceImpl.createCreditTradeResult(creditPayDTO.getNotifyURL(), outTradeNo, tradeId, creditTradeType, creditTradeStatus, creditTransferEndTimesStr, sign);
		
		return insertCreditTransfer(tradeId, paymentFee, CreditTransferType.LOAN,
				factorInfo.getAccountNo(), factorInfo.getCompanyName(),
				projectInfo.getCoreAccountNo(), projectInfo.getCoreCompanyName());
	}
	
	@Override
	public CreditTransfer creditRefund(CreditRefundDTO creditRefundDTO) {
		// 数据校验
		String institution = creditRefundDTO.getInstitution();
		String originTradeNo = creditRefundDTO.getOriginTradeNo();
		CreditTrade originTrade = creditTableLockMapper.lockCreditTradeByTradeNoAndInstitution(originTradeNo, institution);
		if(originTrade == null)
			throw WebException.instance(ReturnCode.TP3018);
		if(!CreditTradeType.PAYMENT.name().equals(originTrade.getTradeType()))
			throw WebException.instance(ReturnCode.TP3019);
		if(!creditRefundDTO.getCustomerId().equals(originTrade.getOutCustomerId()))
			throw WebException.instance(ReturnCode.TP3002);
		checkDuplicatedTrade(institution, creditRefundDTO.getTradeNo());
		CreditTradeExample creditTradeExample = new CreditTradeExample();
		creditTradeExample.createCriteria().andOutOriginTradeNoEqualTo(originTradeNo).andInstitutionEqualTo(institution).andTradeStatusIn(Arrays.asList(CreditTradeStatus.SUCCESS.name(), CreditTradeStatus.HANDING.name()));
		List<CreditTrade> refundTrades = creditTradeMapper.selectByExample(creditTradeExample);
		for (CreditTrade refundTrade : refundTrades)
			originTrade.setFee(originTrade.getFee() - refundTrade.getFee());
		if(creditRefundDTO.getRefundFee() > originTrade.getFee())
			throw WebException.instance(ReturnCode.TP3021);
		
		// 保存退款交易记录
		CreditTrade creditTrade = CreditTradeConvertor.convertCreditTradeRefundDTO2CreditTrade(creditRefundDTO);
		creditTrade.setSummaryId(originTrade.getSummaryId());
		creditTrade.setOriginTradeEndTime(originTrade.getTradeEndTime());
		creditTrade.setOutCustomerName(originTrade.getOutCustomerName());
		creditTrade.setOrderInfo(originTrade.getOrderInfo());
		String id = IDGenerator.generateCreditTradeId();
		creditTrade.setId(id);
		creditTrade.setFinaceCustomerId(originTrade.getFinaceCustomerId());
		creditTrade.setFinaceCustomerName(originTrade.getFinaceCustomerName());
		creditTradeMapper.insertSelective(creditTrade);
		 
		// 保存授信转账记录
		CreditTransferExample example = new CreditTransferExample();
		example.createCriteria().andCreditTradeIdEqualTo(originTrade.getId());
		List<CreditTransfer> originTransfer = creditTransferMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(originTransfer) || originTransfer.size() > 1)
			throw WebException.instance(ReturnCode.ERROR, "原交易存在异常");

		String refundFromAccountNo = originTransfer.get(0).getToAccountNo();
		String refundFromAccountName = originTransfer.get(0).getToAccountName();
		String refundToAccountNo = originTransfer.get(0).getFromAccountNo();
		String refundToAccountName = originTransfer.get(0).getFromAccountName();
		return insertCreditTransfer(id, creditRefundDTO.getRefundFee(), CreditTransferType.REFUND2FACTOR,
				refundFromAccountNo, refundFromAccountName, refundToAccountNo, refundToAccountName);
	}
	

	@Override
	public void creditTransferFail(CreditTransfer creditTransfer) {
		// 更新交易转账表
		updateCreditTransferStatus(creditTransfer, CreditTransferStatus.FAIL);
		
		// 更新交易记录表
		String creditTradeId = creditTransfer.getCreditTradeId();
		Date creditTransferEndTime = creditTransfer.getTransferEndTime();
		String creditTradeStatus = CreditTradeStatus.FAIL.name();
		CreditTrade creditTrade = updateCreditTradeStatus(creditTransferEndTime, creditTradeId, creditTradeStatus);
		
		// 通知真旅
		String outTradeNo = creditTrade.getOutTradeNo();
		String creditTradeType = creditTrade.getTradeType();
		String creditTransferEndTimesStr = DateUtils.formatDate(creditTransferEndTime, "");
		String sign = null;
		try {
			sign = new String(vbamCommonServiceImpl.sign((outTradeNo + creditTradeId + creditTradeType + creditTradeStatus + creditTransferEndTimesStr).getBytes(), Institution.cana.name(), false));
		} catch (Exception e) {
			logger.error("退款失败的签名操作失败！", e);
			throw WebException.instance(ReturnCode.TP3022);
		}
		if(StringUtils.isNotBlank(creditTrade.getNotifyUrl()))
			retryTaskServiceImpl.createCreditTradeResult(creditTrade.getNotifyUrl(), outTradeNo, creditTradeId, creditTradeType, creditTradeStatus, creditTransferEndTimesStr, sign);
	}

	@Override
	public CreditTransfer creditRefundSuccess(CreditTransfer creditTransfer, final Map<Object, Object> extra, String toAccountNo) {
		// 更新交易转账表
		updateCreditTransferStatus(creditTransfer, CreditTransferStatus.SUCCESS);
		
		// 更新交易记录表
		String creditTradeId = creditTransfer.getCreditTradeId();
		Date creditTransferEndTime = creditTransfer.getTransferEndTime();
		String creditTradeStatus = CreditTradeStatus.SUCCESS.name();
		CreditTrade creditTrade = updateCreditTradeStatus(creditTransferEndTime, creditTradeId, creditTradeStatus);
		
		// 调用融资模块还款
		String memberId = creditTrade.getFinaceCustomerId();
		long refundFee = creditTransfer.getFee();
		TravelzenUserRefundInfo travelzenUserRefundInfo = new TravelzenUserRefundInfo();
		travelzenUserRefundInfo.setFinanceId(memberId);
		travelzenUserRefundInfo.setRefundAmount(refundFee);
		travelzenUserRefundInfo.setTxnId(creditTradeId);
		travelzenUserRefundInfo.setOutCustomerId(creditTrade.getOutCustomerId());
		TravelzenUserRefundResult travelzenUserRefundResult = null;
		try {
			travelzenUserRefundResult = repaymentTransactionServiceImpl.refundByTravelzenFinancier(travelzenUserRefundInfo);
			extra.put(Constants.CREDIT_TRAVELZEN_USER_REFUND_RESULT, travelzenUserRefundResult);
		} catch (Exception e) {
			logger.error("调用融资模块还款失败:", e);
			throw WebException.instance(ReturnCode.ERROR);
		}
		
		// 通知真旅
		String outTradeNo = creditTrade.getOutTradeNo();
		String creditTradeType = creditTrade.getTradeType();
		String creditTransferEndTimesStr = DateUtils.formatDate(creditTransferEndTime, "");
		String sign = null;
		try {
			sign = new String(vbamCommonServiceImpl.sign((outTradeNo + creditTradeId + creditTradeType + creditTradeStatus + creditTransferEndTimesStr).getBytes(), Institution.cana.name(), false));
		} catch (Exception e) {
			logger.error("退款成功的签名操作失败！", e);
			throw WebException.instance(ReturnCode.TP3022);
		}
		if(StringUtils.isNotBlank(creditTrade.getNotifyUrl()))
			retryTaskServiceImpl.createCreditTradeResult(creditTrade.getNotifyUrl(), outTradeNo, creditTradeId, creditTradeType, creditTradeStatus, creditTransferEndTimesStr, sign);
		
		long remainingAmount = travelzenUserRefundResult.getRemainingAmount();
		CreditTransfer returnValue = null;
		if(remainingAmount > 0)
			returnValue = insertCreditTransfer(creditTradeId, remainingAmount,  CreditTransferType.REFUND2CUSTOMER,
					creditTransfer.getToAccountNo(), creditTransfer.getToAccountName(),
					toAccountNo, creditTrade.getFinaceCustomerName());
		return returnValue;
	}

	@Override
	public void creditAgentRepaymentSuccess(CreditTransfer creditTransfer) {
		// 更新交易转账表
		updateCreditTransferStatus(creditTransfer, CreditTransferStatus.SUCCESS);
		
		// 更新交易记录表
		String creditTradeId = creditTransfer.getCreditTradeId();
		Date creditTransferEndTime = creditTransfer.getTransferEndTime();
		String creditTradeStatus = CreditTradeStatus.SUCCESS.name();
		CreditTrade creditTrade = updateCreditTradeStatus(creditTransferEndTime, creditTradeId, creditTradeStatus);
		
		// 调用融资模块还款
		RepaymentRequest repaymentRequest = new RepaymentRequest();
		repaymentRequest.setLoanId(creditTrade.getSummaryId());
		repaymentRequest.setRepaymentAmount(creditTransfer.getFee());
		repaymentRequest.setSendMessage(true);
		try {
			LoanInfoRepaymentResult loanInfoRepaymentResult = repaymentTransactionServiceImpl.repayment(repaymentRequest,RepaymentMethod.TZACCOUNT);
			if(creditTransfer.getFee() - loanInfoRepaymentResult.getActualRepaymentTotalAmount() > 0){
				Cat.logMetricForCount("tz_agent_repayment_above_max_repayment_amount");
				logger.warn("还款金额大于最大还款金额", new Gson().toJson(loanInfoRepaymentResult));
				throw WebException.instance("还款失败，您可能多还了款，具体情况请联系cana管理员！");
			}
		} catch (WebException e) {
			throw WebException.instance(e.getMessage());
		} catch (Exception e) {
			throw WebException.instance("系统错误，请联系cana管理员！");
		}
		
		// 通知真旅
		String outTradeNo = creditTrade.getOutTradeNo();
		String creditTradeType = creditTrade.getTradeType();
		String creditTransferEndTimesStr = DateUtils.formatDate(creditTransferEndTime, "");
		String sign = null;
		try {
			sign = new String(vbamCommonServiceImpl.sign((outTradeNo + creditTradeId + creditTradeType + creditTradeStatus + creditTransferEndTimesStr).getBytes(), Institution.cana.name(), false));
		} catch (Exception e) {
			logger.error("账户还款成功的签名操作失败！", e);
			throw WebException.instance(ReturnCode.TP3022);
		}
		if(StringUtils.isNotBlank(creditTrade.getNotifyUrl()))
			retryTaskServiceImpl.createCreditTradeResult(creditTrade.getNotifyUrl(), outTradeNo, creditTradeId, creditTradeType, creditTradeStatus, creditTransferEndTimesStr, sign);
	}
	
	@Override
	public void updateCreditTransferStatus(CreditTransfer creditTransfer, CreditTransferStatus creditTransferStatus) {
		creditTransfer.setTransferStatus(creditTransferStatus.name());
		creditTransfer.setTransferEndTime(new Date());
		creditTransferMapper.updateByPrimaryKey(creditTransfer);
		if(needInsertRefund2CustomerSuccessRetryTaskRecord(creditTransfer, creditTransferStatus)){
			insertRefund2CustomerSuccessRetryTaskRecord(creditTransfer, creditTransferStatus);
		}
	}
	
	/**
	 * 代还款
	 */
	@Override
	public CreditTransfer creditAgentRepayment(ProjectInfo projectInfo,CreditAgentRepaymentDTO creditAgentRepaymentDTO,ContractInfoDTO contractInfoDTO) {
		//锁住放款记录
		RepaymentLoanInfoBO loanInfo = serviceHelper.lockLoanInfoById(creditAgentRepaymentDTO.getLoanInfoId());
		
		if(!contractInfoDTO.getMemberId().equals(loanInfo.getFinanceId())){
			logger.info("需要账户还款的用户id为{},放款信息id为{},对应的融资客户id为{}",contractInfoDTO.getMemberId(),loanInfo.getId(),loanInfo.getFinanceId());
			throw WebException.instance(ReturnCode.ERROR,"客户与放款信息不匹配");
		}
		
		FactorInfo factorInfo = getFactorInfoFromProject(projectInfo, contractInfoDTO.getFactorId());
		if (factorInfo == null)
			throw WebException.instance(ReturnCode.ERROR, "资金方不存在");
		
		String fromAccountNo = projectInfo.getCoreAccountNo();//真旅的
		String fromAccountName = projectInfo.getCoreCompanyName();
		String toAccountNo = factorInfo.getAccountNo();//凯拿的
		String toAccountName = factorInfo.getCompanyName();
		
		// 保存代还款交易记录
		CreditTrade creditTrade = CreditTradeConvertor.convertCreditAgentRepaymentDTO2CreditTrade(creditAgentRepaymentDTO);
		String id = IDGenerator.generateCreditTradeId();
		creditTrade.setId(id);
		creditTrade.setSummaryId(creditAgentRepaymentDTO.getLoanInfoId());
		creditTrade.setOutCustomerName(loanInfo.getOutCustomerName());
		creditTrade.setFinaceCustomerId(loanInfo.getFinanceId());
		creditTrade.setFinaceCustomerName(loanInfo.getFinanceCompany());
		creditTradeMapper.insertSelective(creditTrade);
		 
		// 保存授信转账记录
		return insertCreditTransfer(id, creditAgentRepaymentDTO.getFee(), CreditTransferType.AGENT_REPAYMENT,
				fromAccountNo, fromAccountName, toAccountNo, toAccountName);
	
	}
	
	private void insertRefund2CustomerSuccessRetryTaskRecord(CreditTransfer creditTransfer, CreditTransferStatus creditTransferStatus) {
		
		Refund2CustomerSuccessMessage message = new Refund2CustomerSuccessMessage();
		message.setAmount(creditTransfer.getFee());
		message.setToAccountName(creditTransfer.getToAccountName());
		message.setToAccountNo(creditTransfer.getToAccountNo());
		
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.REFUND2CUSTOMER_SUCCESS_NOTIFY.name());
		task.setTaskId(creditTransfer.getId());
		task.setState(RetryTaskState.not_begin.name());
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setFixedBackoffPeriod(10L);
		task.setData(new Gson().toJson(message));
		task.setTaskDeadline(DateTimeUtil.addDay(new Date(), 1).toDate());
		retryTaskMapper.insertSelective(task);	
	}

	private boolean needInsertRefund2CustomerSuccessRetryTaskRecord(CreditTransfer creditTransfer, CreditTransferStatus creditTransferStatus) {
		return creditTransferStatus == CreditTransferStatus.SUCCESS && CreditTransferType.REFUND2CUSTOMER.name().equals(creditTransfer.getTransferType());
	}

	/**
	 * 校验交易是否重复，需要在获得锁之后调用此方法
	 * @param institution
	 * @param tradeNo
	 */
	private void checkDuplicatedTrade(String institution, String tradeNo) {
		CreditTradeExample creditTradeExample = new CreditTradeExample();
		creditTradeExample.createCriteria().andOutTradeNoEqualTo(tradeNo).andInstitutionEqualTo(institution);
		if(!CollectionUtils.isEmpty(creditTradeMapper.selectByExample(creditTradeExample)))
			throw WebException.instance(ReturnCode.TP3020);
	}
	
	/**
	 * 根据outCustomerId,institutionId获取memerId(cana的用户Id)
	 * @param outCustomerId 外部客户Id
	 * @param institutionId 外部客户机构Id（比如travelzen） 
	 */
	private OutCustomer getOutCustomerByCustomerId(String outCustomerId,String institutionId){
		OutCustomerExample example = new OutCustomerExample();
		example.createCriteria().andOutCustomerIdEqualTo(outCustomerId).andInstitutionIdEqualTo(institutionId);
		List<OutCustomer> userComparisons = outCustomerMapper.selectByExample(example);
		if(userComparisons == null || userComparisons.isEmpty())
			throw WebException.instance(ReturnCode.TP3002);
		return userComparisons.get(0);
	}
	
	/**
	 * 判断额度余额是否足够（额度余额=总额度-已用额度）
	 * @param creditLimit
	 * @param fee 交易金额
	 * @return
	 */
	private void checkLimitBalanceEnough(CreditLimit creditLimit,Long fee){
		Long totalLimit = creditLimit.getTotalLimit();
		Long usedLimit = creditLimit.getUsedLimit() == null ? 0 : creditLimit.getUsedLimit();
		if(totalLimit == null || totalLimit - usedLimit < fee)
			throw WebException.instance(ReturnCode.TP3013);
	}
	
	/**
	 * 更新该记录的授信额度
	 * @param creditLimit
	 * @param fee 交易金额
	 */
	private void updateCreditLimit(CreditLimit creditLimit,Long newUsedLimit){
		creditLimit.setUsedLimit(newUsedLimit);
		creditLimitMapper.updateByPrimaryKeySelective(creditLimit);
	}
	
	/**
	 * 向CreditTransfer表中插入一条记录
	 * @param creditTradeId 交易流水号
	 * @param fee 转账金额
	 * @param transferType 转账类型
	 * @param fromAccountNo 资金转出账户号
	 * @param fromAccountName 资金出账户名
	 * @param toAccountNo 资金转入账户号
	 * @param toAccountName 资金转入账户名
	 * @return
	 */
	private CreditTransfer insertCreditTransfer(String creditTradeId, Long fee, CreditTransferType transferType, String fromAccountNo, String fromAccountName, String toAccountNo, String toAccountName) {
		CreditTransfer creditTransfer = new CreditTransfer();
		creditTransfer.setId(IDGenerator.generateCreditTransferId());
		creditTransfer.setBusinessSeq(IDGenerator.generateCreditTransferBusinessSeq());
		creditTransfer.setCreditTradeId(creditTradeId);
		creditTransfer.setTransferStatus(CreditTransferStatus.HANDING.name());
		creditTransfer.setFee(fee);
		creditTransfer.setTransferType(transferType.name());
		creditTransfer.setFromAccountNo(fromAccountNo);
		creditTransfer.setFromAccountName(fromAccountName);
		creditTransfer.setToAccountNo(toAccountNo);
		creditTransfer.setToAccountName(toAccountName);
		creditTransfer.setTransferStartTime(new Date());
		creditTransferMapper.insertSelective(creditTransfer);
		return creditTransfer;
	}
	
	/**
	 * 调新建放款接口，得到放款编号
	 * @param tradeDate 支付日期（交易开始日期）
	 * @param paymentFee 交易金额
	 * @param outCustomer 外部客户映射
	 * @param creditLimit
	 * @return
	 */
	private String addTravelzenFinanceLoan(ProjectInfo projectInfo, FactorInfo factorInfo, Date tradeDate, Long paymentFee,
			OutCustomer outCustomer, ContractInfoDTO contractInfoDTO, CreditLimit creditLimit) {

		CreateLoanRequest request = new CreateLoanRequest();
		request.setLoanDate(DateTimeUtil.date10(tradeDate));
		request.setFinanceId(outCustomer.getMemberId());
		request.setFinanceCompany(outCustomer.getCompanyName());
		request.setOutCustomerId(creditLimit.getOutCustomerId());
		request.setOutCustomerName(creditLimit.getOutCustomerName());
		request.setCoreCompanyId(projectInfo.getCoreCompanyId());
		request.setCoreCompanyName(projectInfo.getCoreCompanyName());
		request.setFactorId(factorInfo.getCompanyId());
		request.setFactorCompany(factorInfo.getCompanyName());

		request.setAccountNo(contractInfoDTO.getAccountNo());
		request.setAccountSupervisionId(contractInfoDTO.getAccountSupervisionId());
		request.setFinanceAmount(paymentFee);
		request.setLoanPeriodUnit(projectInfo.getLoanPeriodUnit());
		request.setLoanPeriod(projectInfo.getLoanPeriodLower());
		request.setInterestRateUnit(projectInfo.getInterestRateUnit());
		request.setInterestRate(projectInfo.getInterestRateLower());
		request.setRepaymentMethod(RepaymentType.ORDER);
		request.setProductId(projectInfo.getId());
		request.setProductName(projectInfo.getProjectName());
		request.setInstitutionName(Institution.travelzen.desc());
		request.setPenaltyRatio(projectInfo.getPenaltyRatio());
		request.setPenaltyChargeMethod(projectInfo.getPenaltyChargeMethod());

		request.setFactorTransferInAccountNo(factorInfo.getAccountNo());
		request.setDeductionTime(projectInfo.getDeductionTime());
		request.setDeductionRule(projectInfo.getDeductionRule());

		request.setExtensionDays(projectInfo.getExtensionDays());
		request.setExtensionRatio(projectInfo.getExtensionRatio());
		request.setExtensionChargeMethod(projectInfo.getExtensionChargeMethod());
		request.setEarlyRepaymentChargeRatio(projectInfo.getEarlyRepaymentChargeRatio());
		request.setUseHolidayPolicy(projectInfo.isUseHolidayPolicy());
		
		try {
			RepaymentLoanInfoBO repaymentLoanInfoBO = repaymentTransactionServiceImpl.addTravelzenFinanceLoan(request);
			return repaymentLoanInfoBO.getId();
		} catch (Exception e) {
			logger.error("增加放款失败", e);
			throw WebException.instance(ReturnCode.ERROR);
		}
	}

	private FactorInfo getFactorInfoFromProject(ProjectInfo project, String factorId) {
		if (project == null)
			throw WebException.instance(ReturnCode.ERROR, "项目不存在");
		if (CollectionUtils.isEmpty(project.getFactors()))
			throw WebException.instance(ReturnCode.ERROR, "项目中资金方不存在");
		for (FactorInfo factor : project.getFactors())
			if (StringUtils.equals(factor.getCompanyId(), factorId))
				return factor;
		return null;
	}

	private CreditTrade updateCreditTradeStatus(Date creditTransferEndTime, String creditTradeId, String creditTradeStatus) {
		CreditTrade creditTrade = creditTradeMapper.selectByPrimaryKey(creditTradeId);
		creditTrade.setTradeEndTime(creditTransferEndTime);
		creditTrade.setTradeStatus(creditTradeStatus);
		creditTradeMapper.updateByPrimaryKey(creditTrade);
		return creditTrade;
	}

	@Override
	public CreditTransfer manualUpdateCreditTransfer(String id,String operatorId) {
		CreditTransfer transfer=creditTableLockMapper.lockCreditTransferById(id);
		String businessSeq=transfer.getBusinessSeq();
		transfer.setBusinessSeq(IDGenerator.generateCreditTransferBusinessSeq());
		transfer.setTransferStartTime(new Date());
		transfer.setTransferStatus(CreditTransferStatus.HANDING.name());
		transfer.setOperatorId(operatorId);
		transfer.setTransferEndTime(null);
		transfer.setBusinessSeqHistory(businessSeq+",");
		creditTransferMapper.updateByPrimaryKey(transfer);
		return transfer;
	}

	@Override
	public void updateCreditTransferStatusById(String id, CreditTransferStatus creditTransferStatus) {
		CreditTransfer transfer=creditTableLockMapper.lockCreditTransferById(id);
		transfer.setTransferStatus(creditTransferStatus.name());
		transfer.setTransferEndTime(new Date());
		creditTransferMapper.updateByPrimaryKeySelective(transfer);
	}

}

package com.cana.credit.server.api.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.asset.api.IAssetApi;
import com.cana.credit.api.ICreditApi;
import com.cana.credit.dao.po.AccessRule;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.service.IAuditResultService;
import com.cana.credit.service.ICreditLimitAuditService;
import com.cana.credit.service.ICreditLimitService;
import com.cana.credit.service.ICreditMarketingService;
import com.cana.credit.service.ICreditMessageService;
import com.cana.credit.service.ICreditTradeService;
import com.cana.credit.service.ICustomerApplyService;
import com.cana.credit.service.IOutCustomerService;
import com.cana.credit.service.IWhiteListService;
import com.cana.credit.service.transaction.IAccessRulesTransationService;
import com.cana.credit.service.transaction.ICreditLimitTransactionService;
import com.cana.credit.service.transaction.ICustomerApplyTransactionService;
import com.cana.credit.service.utils.CheckParamUtil;
import com.cana.credit.service.utils.CreditLimitCalculateUtil3;
import com.cana.credit.service.utils.NewestAccessRuleHolder;
import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.flight.finance.common.dto.CreditTradeDTO;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.flight.finance.common.dto.QueryCreditTradeStateDTO;
import com.cana.flight.finance.service.utils.IFlightFinanceServiceHelper;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.credit.dto.apply.AccessRuleDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyDetailDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyListQueryDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyMinDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;
import com.cana.vbam.common.credit.dto.apply.TravelzenCustomerAuditResultDTO;
import com.cana.vbam.common.credit.dto.limit.CalculateLimitResult;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitDTO;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfo;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitInfoQueryCriteria;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitQueryDTO;
import com.cana.vbam.common.credit.dto.limit.CreditUsedLimitRequest;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListQueryDTO;
import com.cana.vbam.common.credit.dto.limit.CustomerLimitListResponseDTO;
import com.cana.vbam.common.credit.dto.limit.PreGenerateCreditLimitResponse;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitResponse;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityRequest;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityResponse;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductRequest;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductResponse;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerDTO;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerQuery;
import com.cana.vbam.common.credit.dto.trade.CreditStatementDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeFlowDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeOperateDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeStateResultDTO;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerParamDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleParamDTO;
import com.cana.vbam.common.credit.enums.AccessRuleFitObject;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.enums.NoticeScene;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailRequest;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailResponse;
import com.cana.vbam.common.credit.openapi.CreditNoticeParam;
import com.cana.vbam.common.credit.openapi.LoanInfoDetailRequest;
import com.cana.vbam.common.credit.openapi.LoanListResponse;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.dianping.cat.Cat;
import com.google.gson.Gson;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

public class CreditApiImpl implements ICreditApi {

	@Resource
	private IWhiteListService whiteListServiceImpl;
	@Resource
	private IAuditResultService auditResultService;
	@Resource
	private ICustomerApplyService customerApplyService;
	@Resource
	private ICreditLimitService creditLimitServiceImpl;
	@Resource
	private ICreditLimitTransactionService creditLimitTransactionService;
	@Resource
	private ICreditTradeService creditTradeService;
	@Resource
	private ICustomerApplyTransactionService customerApplyTransactionServiceImpl;
	@Resource
	private IVbamCommonService vbamCommonService;
	@Resource
	private IOutCustomerService outCusomterServiceImpl;
	@Resource
	private ICreditLimitAuditService creditLimitAuditServiceImpl;
	@Resource
	private ICreditMarketingService creditMarketingServiceImpl;
	@Resource
	private ICreditMessageService creditMessageServiceImpl; 
	@Resource
	private IUserApi userApi;
	@Resource
    private IAssetApi assetApi;
	@Resource
	private IAccessRulesTransationService accessRulesTransationServiceImpl;
	@Resource
	private IFlightFinanceServiceHelper flightFinanceServiceHelper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Boolean precheck(String customerId) {
		logger.info("dubbo服务调用客户申请初步校验接口，客户ID：{}", customerId);
		//是白名单 => 通过
		if(whiteListServiceImpl.isAvailableWhiteCustomer(customerId))
			return true;
		//cana平台没有客票数据 => 不通过
		if(!auditResultService.checkTzCustomerInfoExist(customerId))
			return false;
		//非白名单，基础数据校验通过 => 通过
		AccessRule accessRule = NewestAccessRuleHolder.nonWhiteCustomerNewestAccessRule;
		List<ReturnClass> errorReturnClassList = accessRulesTransationServiceImpl.checkBaseApplyData(accessRule, customerId);
		if(CollectionUtils.isEmpty(errorReturnClassList))
			return true;
		return false;
	}

	@Override
	public PageList<WhiteCustomerRuleDTO> getWhiteCustomerRules(WhiteCustomerRuleParamDTO param) {
		return whiteListServiceImpl.getWhiteCustomerRules(param);
	}

	@Override
	public PageList<WhiteCustomerDTO> getWhiteCustomers(WhiteCustomerParamDTO param) {
		return whiteListServiceImpl.getWhiteCustomers(param);
	}

	@Override
	public void saveTravelzenAuditResult(CustomerApplyRequestDTO customerApplyDTO)throws Exception {
		auditResultService.saveTravelzenAuditResult(customerApplyDTO);
	}

	@Override
	public PageList<CustomerApplyMinDTO> getCustomerApplyList(CustomerApplyListQueryDTO customerApplyListQueryDTO) {
		return customerApplyService.getCustomerApplyList(customerApplyListQueryDTO);
	}

	@Override
	public CustomerApplyDetailDTO getCustomerApplyInfo(String id) {
		return customerApplyService.getCustomerApplyInfo(id);
	}

	public void auditTravelzenCustomer(TravelzenCustomerAuditResultDTO resultDTO) {
		customerApplyService.auditTravelzenCustomer(resultDTO);
	}

    @Override
    public PageList<CustomerLimitListResponseDTO> getCustomerLimitList(CustomerLimitListQueryDTO customerLimitListQueryDTO) {
        return creditLimitServiceImpl.getCustomerLimitList(customerLimitListQueryDTO);
    }

	@Override
	public List<CreditUsedLimit> queryUsedLimit(Date startDate, Date endDate, String institution) {
		return creditTradeService.queryUsedLimit(startDate, endDate, institution);
	}
    
	@Override
	public List<CreditUsedLimit> queryUsedLimit(Date date, String institution, String productId) {
		CreditUsedLimitQueryDTO creditUsedLimitQueryDTO = new CreditUsedLimitQueryDTO();
		creditUsedLimitQueryDTO.setDate(date);
		creditUsedLimitQueryDTO.setInstitution(institution);
		creditUsedLimitQueryDTO.setProductId(productId);
		return creditLimitAuditServiceImpl.queryUsedLimit(creditUsedLimitQueryDTO);
	}
	
	@Override
	public List<CreditUsedLimitInfo> queryCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria) {
		CheckParamUtil.checkInstitution(creditUsedLimitInfoQueryCriteria.getInstitution());
		return creditLimitServiceImpl.queryCreditUsedLimitInfo(creditUsedLimitInfoQueryCriteria);
	}
	
	@Override
	public String queryOutCustomerName(String productId, String memberId, String outCustomerId) {
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("memberId不能为空");
		if(StringUtils.isBlank(outCustomerId))
			throw WebException.instance("outCustomerId不能为空");
		return creditLimitServiceImpl.queryOutCustomerName(productId, memberId, outCustomerId);
	}

	
	@Override
	public int queryCountCreditUsedLimitInfo(CreditUsedLimitInfoQueryCriteria creditUsedLimitInfoQueryCriteria) {
		CheckParamUtil.checkInstitution(creditUsedLimitInfoQueryCriteria.getInstitution());
		return creditLimitServiceImpl.queryCountCreditUsedLimitInfo(creditUsedLimitInfoQueryCriteria);
	}
	
	@Override
	@Deprecated
	public void activateCreditLimit(String memberId) {
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("memberId不能为空！");
		List<CreditLimit> activeLimits = creditLimitTransactionService.activateCreditLimit(memberId);

		if (CollectionUtils.isNotEmpty(activeLimits)) {
			CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(memberId);
			CreditNoticeParam creditNoticeParam = new CreditNoticeParam();
			creditNoticeParam.setNoticeScene(NoticeScene.LIMIT_ACTIVE);
			String accountNo = assetApi.getAccountNoByTravelzenFinanceId(memberId);
			creditNoticeParam.setAccountNo(accountNo);
			creditNoticeParam.setCompanyName(customerDetailDTO.getCompanyName());
			creditNoticeParam.setEmail(customerDetailDTO.getContactMail());
			creditNoticeParam.setPhoneNumber(customerDetailDTO.getContactTel());
			creditMessageServiceImpl.sendMailForTzCustomerApply(creditNoticeParam);
			creditMessageServiceImpl.sendSmsMessageForTzCustomerApply(creditNoticeParam);
		}
	}

	@Override
	public void activateCreditLimitByLimitId(String limitId) {
		if(StringUtils.isBlank(limitId))
			throw WebException.instance("limitId不能为空！");
		CreditLimit limit = creditLimitTransactionService.getCreditLimit(limitId);
		if (limit == null)
			throw WebException.instance("额度不存在");
		if (!assetApi.checkContractExistByTravelzenFinanceId(limit.getMemberId()))
			throw WebException.instance("该客户还未签署真旅项目合同，不能激活额度");

		limit = creditLimitTransactionService.activateCreditLimitByLimitId(limitId);
		CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(limit.getMemberId());
		CreditNoticeParam creditNoticeParam = new CreditNoticeParam();
		creditNoticeParam.setNoticeScene(NoticeScene.LIMIT_ACTIVE);
		String accountNo = assetApi.getAccountNoByTravelzenFinanceId(limit.getMemberId());
		creditNoticeParam.setAccountNo(accountNo);
		creditNoticeParam.setCompanyName(customerDetailDTO.getCompanyName());
		creditNoticeParam.setEmail(customerDetailDTO.getContactMail());
		creditNoticeParam.setPhoneNumber(customerDetailDTO.getContactTel());
		creditMessageServiceImpl.sendMailForTzCustomerApply(creditNoticeParam);
		creditMessageServiceImpl.sendSmsMessageForTzCustomerApply(creditNoticeParam);
	}

	@Override
	public List<AccessRuleDTO> queryAccessRule(AccessRuleFitObject fitObject) {
		return customerApplyTransactionServiceImpl.queryAccessRule(fitObject);
	}

	@Override
	public Map<String, Map<String, String>> getAllOutCustomer() {
		return outCusomterServiceImpl.getAllOutCustomer();
	}
	
	@Override
	public List<OutCustomerDTO> getOutCustomerDTO(OutCustomerQuery outCustomerQuery) {
		return outCusomterServiceImpl.getOutCustomerDTO(outCustomerQuery);
	}
	
	@Override
	public String creditPay(CreditPayDTO creditPayDTO) {
		String creditTradeId = null;
		try {
			creditTradeId = creditTradeService.creditPay(creditPayDTO);
			Cat.logMetricForCount("授信支付交易成功");
		} catch(Exception e) {
			Cat.logError(e);
			Cat.logMetricForCount("授信支付交易失败");
			throw e;
		}
		return creditTradeId;
	}
	
	@Override
	public String creditRefund(CreditRefundDTO creditRefundDTO) {
		String creditTradeId = null;
		try {
			CheckParamUtil.checkInstitution(creditRefundDTO.getInstitution());
			if(StringUtils.isBlank(creditRefundDTO.getCustomerId()))
				throw WebException.instance(ReturnCode.TP3002);
			if(StringUtils.isBlank(creditRefundDTO.getTradeNo()))
				throw WebException.instance(ReturnCode.TP3003);
			if(StringUtils.isBlank(creditRefundDTO.getOriginTradeNo()))
				throw WebException.instance(ReturnCode.TP3010);
			if(StringUtils.isBlank(creditRefundDTO.getNotifyURL()))
				throw WebException.instance(ReturnCode.TP3005);
			if(creditRefundDTO.getRefundFee() == null || creditRefundDTO.getRefundFee() <= 0)
				throw WebException.instance(ReturnCode.TP3004);
			if(StringUtils.isBlank(creditRefundDTO.getTradeTime()) || !DateTimeUtil.isValdateDate(creditRefundDTO.getTradeTime(), 8))
				throw WebException.instance(ReturnCode.TP3007);
			if(StringUtils.isBlank(creditRefundDTO.getSign()))
				throw WebException.instance(ReturnCode.TP3023);
			creditTradeId = creditTradeService.creditRefund(creditRefundDTO);
			Cat.logMetricForCount("授信退款交易成功");
		} catch (Exception e) {
			Cat.logError(e);
			Cat.logMetricForCount("授信退款交易失败");
			throw e;
		}
		return creditTradeId;
	}
	
	@Override
	public String creditAgentRepayment(CreditAgentRepaymentDTO creditAgentRepaymentDTO) {
		String creditTradeId = null;
		try {
			CheckParamUtil.checkInstitution(creditAgentRepaymentDTO.getInstitution());
			if(StringUtils.isBlank(creditAgentRepaymentDTO.getCustomerId()))
				throw WebException.instance(ReturnCode.TP3002);
			if(StringUtils.isBlank(creditAgentRepaymentDTO.getTradeNo()))
				throw WebException.instance(ReturnCode.TP3003);
			if(StringUtils.isBlank(creditAgentRepaymentDTO.getLoanInfoId()))
				throw WebException.instance(ReturnCode.TP3026);
			if(StringUtils.isBlank(creditAgentRepaymentDTO.getNotifyURL()))
				throw WebException.instance(ReturnCode.TP3005);
			if(creditAgentRepaymentDTO.getFee() == null || creditAgentRepaymentDTO.getFee() <= 0)
				throw WebException.instance(ReturnCode.TP3004);
			if(StringUtils.isBlank(creditAgentRepaymentDTO.getTradeTime()) || !DateTimeUtil.isValdateDate(creditAgentRepaymentDTO.getTradeTime(), 8))
				throw WebException.instance(ReturnCode.TP3007);
			if(StringUtils.isBlank(creditAgentRepaymentDTO.getSign()))
				throw WebException.instance(ReturnCode.TP3023);
			creditTradeId = creditTradeService.creditAgentRepayment(creditAgentRepaymentDTO);
			Cat.logMetricForCount("账户还款成功");
		} catch (Exception e) {
			Cat.logError(e);
			Cat.logMetricForCount("账户还款失败");
			throw e;
		}
		return creditTradeId;
	}
	
    @Override
    public CreditTradeStateResultDTO queryCreditTradeState(QueryCreditTradeStateDTO queryDTO) {
    	String institution = queryDTO.getInstitution();
        CheckParamUtil.checkInstitution(institution);
        if(StringUtils.isBlank(queryDTO.getTradeNo()))
            throw WebException.instance(ReturnCode.TP3003);
        if(StringUtils.isBlank(queryDTO.getTradeType()))
            throw WebException.instance(ReturnCode.TP3011);
        StringBuilder checkSign=new StringBuilder();
        checkSign.append(institution).append(queryDTO.getTradeNo()).append(queryDTO.getTradeType());
        vbamCommonService.rsaVerify(checkSign.toString().getBytes(), institution, queryDTO.getSign().getBytes(), true);
        return creditTradeService.queryCreditTradeState(queryDTO);
    }

    @Override
    public QueryCreditLimitResponse queryCreditLimitBalance(QueryCreditLimitDTO queryDTO) {
    	String institution = queryDTO.getInstitution();
        CheckParamUtil.checkInstitution(institution);
        if(StringUtils.isBlank(queryDTO.getCustomerId()))
            throw WebException.instance(ReturnCode.TP3002);
        if (StringUtils.isBlank(queryDTO.getSign()))
        	throw WebException.instance(ReturnCode.TP3023);
        StringBuilder checkSign=new StringBuilder();
        checkSign.append(institution).append(queryDTO.getCustomerId());
        vbamCommonService.rsaVerify(checkSign.toString().getBytes(), institution, queryDTO.getSign().toString().getBytes(), true);
        return creditLimitServiceImpl.queryCreditLimitBalance(queryDTO);
    }

	@Override
	public Map<String, String> queryOutCustomerIdAndMemberId(String projectId, String companyName, String outCustomerName) {
		return creditLimitServiceImpl.queryOutCustomerIdAndMemberId(projectId, companyName, outCustomerName);
	}
    
	@Override
	public PageResult<CreditTradeDTO> queryCreditLoanDetailsDetail(int page,int pageSize,String summaryId, String loginFinanceId) {
		return creditTradeService.queryCreditLoanDetailsDetail(page,pageSize,summaryId, loginFinanceId);
	}

	@Override
	public PageResult<CreditTradeDTO> queryCreditTrades(CreditTradeQueryCriteria criteria) {
		return creditTradeService.queryCreditTrades(criteria);
	}

	@Override
	public PageResult<CreditTradeFlowDTO> queryCreditFlowList(CreditTradeQueryCriteria criteria) {
		return creditTradeService.queryCreditFlowList(criteria);
	}
	
	@Override
	public PageResult<CreditStatementDTO> getCreditStatementNoPaging(CreditTradeQueryCriteria criteria) {
		return creditTradeService.getCreditStatementNoPaging(criteria);
	}

	@Override
	public CreditTradeOperateDTO getCreditTransferInfo(String id) {
		return creditTradeService.queryCreditTransferInfo(id);
	}

	@Override
	public CreditTransferStatus manualOperateCreditTransfer(String id,String operator) {
		return creditTradeService.manualOperateCreditTransfer(id, operator);
	}

	@Override
	public LoanListResponse queryLoanInfoList(LoanInfoDetailRequest request) throws Exception{
		return creditTradeService.queryLoanInfoList(request);
	}
	@Override
	public CreditLoanDetailResponse getCreditLoanDetail(CreditLoanDetailRequest request) throws Exception{
		logger.info("真旅项目——【账单明细详情】接口传参如下：{}",new Gson().toJson(request));
		String instutution = request.getInstitution();
		CheckParamUtil.checkInstitution(instutution);
		if(StringUtils.isBlank(request.getLoanInfoId()))
			throw WebException.instance(ReturnCode.TP3026);
		if(StringUtils.isBlank(request.getSign()))
			throw WebException.instance(ReturnCode.TP3023);
		StringBuilder checkSign=new StringBuilder();
		checkSign.append(instutution).append(request.getLoanInfoId());
		vbamCommonService.rsaVerify(checkSign.toString().getBytes(), instutution, request.getSign().getBytes(), true);
		return creditTradeService.getCreditLoanDetail(request);
	}

	public String getCanaFinanceIdByOutCustomerId(String institution, String outCustomerId) {
		return outCusomterServiceImpl.getCanaFinanceIdByOutCustomerId(institution, outCustomerId);
	}

	@Override
	public int getOutCustomerNumber(String institution) {
		return outCusomterServiceImpl.getOutCustomerNumber(institution);
	}

	@Override
	public CurrentActivityResponse getCurrentActivity(CurrentActivityRequest currentActivityRequest) {
		String institution = currentActivityRequest.getInstitution();
		CheckParamUtil.checkInstitution(institution);
		vbamCommonService.rsaVerify((institution + currentActivityRequest.getCustomerId()).getBytes(), institution, currentActivityRequest.getSign().getBytes(), true);
		return creditMarketingServiceImpl.getCurrentActivity(currentActivityRequest);
	}

	@Override
	public PrepayProductResponse getPrepayProduct(PrepayProductRequest prepayProductRequest) {
		String institution = prepayProductRequest.getInstitution();
		CheckParamUtil.checkInstitution(institution);
		long prepayAmount = prepayProductRequest.getPrepayAmount();
		if(prepayAmount <= 0)
			throw WebException.instance(ReturnCode.TP3029);
		vbamCommonService.rsaVerify((institution + prepayProductRequest.getCustomerId() + prepayAmount).getBytes(), institution, prepayProductRequest.getSign().getBytes(), true);
		return creditMarketingServiceImpl.getPrepayProduct(prepayProductRequest);
	}

	@Override
	public List<MonitorMoneyDTO> getFlightTicketSales(String date10) {
		return outCusomterServiceImpl.getFlightTicketSales(date10);
	}

	@Override
	public List<MonitorMoneyDTO> getQualifiedAR(String startRecordId) {
		return outCusomterServiceImpl.getQualifiedAR(startRecordId);
	}

	@Override
	public List<MonitorMoneyDTO> getDailySales(String startMonth, String endMonth, int dayNumber) {
		return outCusomterServiceImpl.getDailySales(startMonth, endMonth, dayNumber);
	}

	@Override
	public PreGenerateCreditLimitResponse preGenerateCreditLimit(String customerId) {
		PreGenerateCreditLimitResponse preGenerateCreditLimitResponse = new PreGenerateCreditLimitResponse();
		CalculateLimitResult limitResult = new CalculateLimitResult(0l);
		if(whiteListServiceImpl.isAvailableWhiteCustomer(customerId)){
			logger.info("是白名单");
			limitResult = CreditLimitCalculateUtil3.calculateCreditLimit(customerId);
		}
		Long limit = limitResult.getFinalLimit();
		logger.info("客户id为：{},最终额度为{}",customerId,limit);
		preGenerateCreditLimitResponse.setTotalLimit(limit);
		preGenerateCreditLimitResponse.setExistLimit(limit > 0?true:false);
		return preGenerateCreditLimitResponse;
	}
	
	/**
	 * 韵达监控查询已使用额度接口
	 */
	@Override
	public List<CreditUsedLimitDTO> getCreditUsedLimitInfo(CreditUsedLimitRequest creditUsedLimitRequest) {
		CreditUsedLimitQueryDTO creditUsedLimitQueryDTO = new CreditUsedLimitQueryDTO();
		creditUsedLimitQueryDTO.setProductId(creditUsedLimitRequest.getProductId());
		creditUsedLimitQueryDTO.setCustomerName(creditUsedLimitRequest.getCustomerName());
		creditUsedLimitQueryDTO.setPage(creditUsedLimitRequest.getPage());
		creditUsedLimitQueryDTO.setPageSize(creditUsedLimitRequest.getPageSize());
		return creditLimitAuditServiceImpl.queryYundaUsedLimit(creditUsedLimitQueryDTO);
	}
	/**
	 * 韵达监控查询已经使用额度总条数　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
	 */
	@Override
	public int getCreditUsedLimitInfoCount(CreditUsedLimitRequest creditUsedLimitRequest) {
		CreditUsedLimitQueryDTO creditUsedLimitQueryDTO = new CreditUsedLimitQueryDTO();
		creditUsedLimitQueryDTO.setProductId(creditUsedLimitRequest.getProductId());
		creditUsedLimitQueryDTO.setCustomerName(creditUsedLimitRequest.getCustomerName());
		creditUsedLimitQueryDTO.setPage(creditUsedLimitRequest.getPage());
		creditUsedLimitQueryDTO.setPageSize(creditUsedLimitRequest.getPageSize());
		return creditLimitAuditServiceImpl.queryYundaUsedLimitCount(creditUsedLimitQueryDTO);
	}

}

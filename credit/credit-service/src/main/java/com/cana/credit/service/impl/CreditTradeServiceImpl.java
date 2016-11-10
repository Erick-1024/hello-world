package com.cana.credit.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.account.api.IAccountApi;
import com.cana.asset.api.IAssetApi;
import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.credit.dao.mapper.CreditTradeDetailMapper;
import com.cana.credit.dao.mapper.gen.CreditTradeMapper;
import com.cana.credit.dao.mapper.gen.CreditTransferMapper;
import com.cana.credit.dao.mapper.gen.OutCustomerMapper;
import com.cana.credit.dao.po.CreditTrade;
import com.cana.credit.dao.po.CreditTradeExample;
import com.cana.credit.dao.po.CreditTradeExample.Criteria;
import com.cana.credit.dao.po.CreditTradeFlow;
import com.cana.credit.dao.po.CreditTradeRequest;
import com.cana.credit.dao.po.CreditTransfer;
import com.cana.credit.dao.po.CreditTransferExample;
import com.cana.credit.dao.po.OutCustomer;
import com.cana.credit.dao.po.OutCustomerExample;
import com.cana.credit.limit.dao.po.CreditLimit;
import com.cana.credit.service.ICreditTradeService;
import com.cana.credit.service.IOutCustomerService;
import com.cana.credit.service.convertors.CreditTradeConvertor;
import com.cana.credit.service.transaction.ICreditLimitTransactionService;
import com.cana.credit.service.transaction.ICreditTradeTransactionService;
import com.cana.credit.service.utils.CheckParamUtil;
import com.cana.credit.service.utils.CreditTransferThreadPoolExecutor;
import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.flight.finance.common.dto.CreditTradeDTO;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.flight.finance.common.dto.QueryCreditTradeStateDTO;
import com.cana.marketing.api.IInterestRateApi;
import com.cana.repayment.api.IFinanceReportApi;
import com.cana.repayment.service.IRepaymentCalcService;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.asset.dto.ContractInfoDTO;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.credit.dto.trade.CreditStatementDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeFlowDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeOperateDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeStateResultDTO;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimitQueryCriteria;
import com.cana.vbam.common.credit.enums.CreditTradeType;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailRequest;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailResponse;
import com.cana.vbam.common.credit.openapi.LoanInfoDetailRequest;
import com.cana.vbam.common.credit.openapi.LoanInfoDetailVo;
import com.cana.vbam.common.credit.openapi.LoanListResponse;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountRequest;
import com.cana.vbam.common.marketing.activity.dto.InterestRateDiscountResponse;
import com.cana.vbam.common.repayment.dto.LoanInfoDetail;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoDetailRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoDetailResponseDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListResponseDTO;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class CreditTradeServiceImpl implements ICreditTradeService {

	@Resource
	private CreditTradeMapper creditTradeMapper;
	@Resource
	private IVbamCommonService vbamCommonService;
	@Resource
	private ICreditTradeTransactionService creditTradeTransactionServiceImpl;
	@Resource
	private CreditTransferThreadPoolExecutor creditTransferThreadPoolExecutor;
	@Resource
	private CreditTradeDetailMapper creditTradeDetailMapper;
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IInterestRateApi interestRateApi;
	@Resource
	private OutCustomerMapper outCustomerMapper;
	@Resource
	private IOutCustomerService outCustomerServiceImpl;
	@Resource
	private CreditTransferMapper creditTransferMapper;
	@Resource
	private IFinanceReportApi financeReportApi;
	
	@Resource
	private IAssetApi assetApiImpl;
	@Resource
	private IAssetProjectManageApi projectApi;
	
	@Resource
	private IRepaymentCalcService repaymentCalcServiceImpl;
	
	@Resource
	private ICreditLimitTransactionService creditLimitTransactionServiceImpl;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 新启事务 －>参数检查－新建或更新融资模块放款－新建授信支付交易记录－扣额度－新增额度变化记录－通知真旅支付状态为成功－新建一条转账记录
	 * －>退出事务 －>cana保理商账户转账至真旅网账户
	 */
	@Override
	public String creditPay(CreditPayDTO creditPayDTO) {
		checkCreditPayParam(creditPayDTO);

		ProjectInfo projectInfo = projectApi.getProjectInfo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		if (projectInfo == null)
			throw WebException.instance(ReturnCode.ERROR, "真旅项目不存在");
		
		String memberId = outCustomerServiceImpl.getCanaFinanceIdByOutCustomerId(creditPayDTO.getInstitution(), creditPayDTO.getCustomerId());
		ContractQueryCriteria contractQueryCriteria = new ContractQueryCriteria();
		contractQueryCriteria.setMemberId(memberId);
		contractQueryCriteria.setProductId(projectInfo.getId());
		List<ContractInfoDTO> contractInfoDTOs = assetApiImpl.getContractsWithoutPaging(contractQueryCriteria);	
		if(CollectionUtils.isEmpty(contractInfoDTOs)){
			logger.error("合同表监管账户为空");
			throw WebException.instance(ReturnCode.ERROR, "该客户未签署合同");
		}
		if (contractInfoDTOs.size() > 1)
			throw WebException.instance(ReturnCode.ERROR, "该客户合同数量存在异常");

		//根据额度计算利率
		CreditLimit creditLimit = creditLimitTransactionServiceImpl.getCreditLimitByMemberId(memberId, creditPayDTO.getCustomerId());
		BigDecimal newInterestRate = calculateInterestRateLowerByLimit(creditLimit.getTotalLimit());
		logger.info("根据额度计算利率，新的利率为：{}",newInterestRate);
		projectInfo.setInterestRateLower(newInterestRate);
		
		if(StringUtils.isNotBlank(creditPayDTO.getActivityId()))
			getTravelzenFinanceProductAfterDiscount(projectInfo, contractInfoDTOs.get(0), creditPayDTO);
		
		CreditTransfer creditTransfer = creditTradeTransactionServiceImpl.creditPay(projectInfo, creditPayDTO, contractInfoDTOs.get(0));
		creditTransferThreadPoolExecutor.doTransfer(creditTransfer, "订单编号：" + creditPayDTO.getTradeNo());
		return creditTransfer.getCreditTradeId();
	}
	
	/**
	 * 支付接口--检验真旅传过来的参数是否正确
	 * 1.参数不能为空
	 * 2.支付金额大于0
	 * 3.验证签名是否正确
	 * 4.根据传过来的customerId判断用户是否存在
	 * 5.判断该用户的额度是否是正常状态并且是否够用，则锁住该额度
	 * @param creditPayDTO
	 */
	private void checkCreditPayParam(CreditPayDTO creditPayDTO){
		String institution = creditPayDTO.getInstitution();
		CheckParamUtil.checkInstitution(institution);
		if(StringUtils.isBlank(creditPayDTO.getCustomerId()))
			throw WebException.instance(ReturnCode.TP3002);
		if(StringUtils.isBlank(creditPayDTO.getTradeNo()))
			throw WebException.instance(ReturnCode.TP3003);
		if(creditPayDTO.getPaymentFee()==null || creditPayDTO.getPaymentFee().longValue()<=0)
			throw WebException.instance(ReturnCode.TP3004);
		if(StringUtils.isBlank(creditPayDTO.getNotifyURL()))
			throw WebException.instance(ReturnCode.TP3005);
		if(StringUtils.isBlank(creditPayDTO.getTradeTime()) || !DateTimeUtil.isValdateDate(creditPayDTO.getTradeTime(), 8))
			throw WebException.instance(ReturnCode.TP3007);
		if(StringUtils.isBlank(creditPayDTO.getCustomerName()))
			throw WebException.instance(ReturnCode.TP3008);
		if(StringUtils.isBlank(creditPayDTO.getOrderInfo()))
			throw WebException.instance(ReturnCode.TP3009);
		if (StringUtils.isBlank(creditPayDTO.getSign()))
			throw WebException.instance(ReturnCode.TP3023);
			
		//验签
		StringBuilder verifyStringBuilder = new StringBuilder();
		verifyStringBuilder.append(institution).append(creditPayDTO.getCustomerId()).append(creditPayDTO.getTradeNo()).append(creditPayDTO.getPaymentFee());
		vbamCommonService.rsaVerify(verifyStringBuilder.toString().getBytes(), institution, creditPayDTO.getSign().getBytes(), true);
	}
	
	/**
	 * 根据额度计算利率
	 * @param limit
	 * @return
	 */
	private BigDecimal calculateInterestRateLowerByLimit(Long limit){
		if(limit<50000000L)
			return new BigDecimal(0.00040);
		else if(limit<80000000L)
			return new BigDecimal(0.00036);
		else
			return new BigDecimal(0.00033);
	}
	
	/**
	 * 获取产品信息（打完折扣后的利率，利率单位）
	 * @param creditPayDTO
	 */
	private void getTravelzenFinanceProductAfterDiscount(ProjectInfo projectInfo, ContractInfoDTO contractInfoDTO, CreditPayDTO creditPayDTO){
		InterestRateDiscountRequest interestRateDiscountRequest = new InterestRateDiscountRequest();
		interestRateDiscountRequest.setFactorId(contractInfoDTO.getFactorId());
		String financeId = outCustomerServiceImpl.getCanaFinanceIdByOutCustomerId(Institution.travelzen.name(), creditPayDTO.getCustomerId());
		interestRateDiscountRequest.setFinanceId(financeId);
		interestRateDiscountRequest.setOriginInterestRate(projectInfo.getInterestRateLower());
		interestRateDiscountRequest.setOriginInterestRateUnit(projectInfo.getInterestRateUnit());
		interestRateDiscountRequest.setProductId(projectInfo.getId());
		InterestRateDiscountResponse interestRateDiscountResponse = interestRateApi.getInterestRateAfterDiscount(interestRateDiscountRequest);
		if(!creditPayDTO.getActivityId().equals(interestRateDiscountResponse.getDiscountInfo().getActivityId()))
			throw WebException.instance(ReturnCode.TP3028);
		projectInfo.setInterestRateUnit(interestRateDiscountResponse.getInterestRateUnit());
		projectInfo.setInterestRateLower(interestRateDiscountResponse.getInterestRate());
		projectInfo.setInterestRateUpper(interestRateDiscountResponse.getInterestRate());
	}

	@Override
	public CreditTradeStateResultDTO queryCreditTradeState(QueryCreditTradeStateDTO queryDTO) {
		CreditTradeExample example = new CreditTradeExample();
		Criteria criteria = example.createCriteria();
		criteria.andInstitutionEqualTo(queryDTO.getInstitution().trim());
		criteria.andOutTradeNoEqualTo(queryDTO.getTradeNo().trim());
		criteria.andTradeTypeEqualTo(queryDTO.getTradeType().trim());
		List<CreditTrade> creditTrades = creditTradeMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(creditTrades))
			throw WebException.instance(ReturnCode.NOT_FOUND);
		return CreditTradeConvertor.convertCreditTradeDao2Dto(creditTrades.get(0));
	}

	@Override
	public String creditRefund(CreditRefundDTO creditRefundDTO) {
		String institution = creditRefundDTO.getInstitution();
		logger.info("{}发生退款", institution);
		String plain = institution + creditRefundDTO.getCustomerId();
		plain += creditRefundDTO.getTradeNo() + creditRefundDTO.getOriginTradeNo() + creditRefundDTO.getRefundFee();
		vbamCommonService.rsaVerify(plain.getBytes(), institution, creditRefundDTO.getSign().getBytes(), true);
		CreditTransfer creditTransfer = creditTradeTransactionServiceImpl.creditRefund(creditRefundDTO);
		creditTransferThreadPoolExecutor.doTransfer(creditTransfer, "订单编号：" + creditRefundDTO.getTradeNo());
		return creditTransfer.getCreditTradeId();
	}

	@Override
	public String creditAgentRepayment(CreditAgentRepaymentDTO creditAgentRepaymentDTO) {
		String institution = creditAgentRepaymentDTO.getInstitution();
		logger.info("{}发生账户还款", institution);
		String plain = institution + creditAgentRepaymentDTO.getCustomerId() + creditAgentRepaymentDTO.getTradeNo() + creditAgentRepaymentDTO.getLoanInfoId() + creditAgentRepaymentDTO.getFee();
		vbamCommonService.rsaVerify(plain.getBytes(), institution, creditAgentRepaymentDTO.getSign().getBytes(), true);
		ProjectInfo projectInfo = projectApi.getProjectInfo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		if (projectInfo == null)
			throw WebException.instance(ReturnCode.ERROR, "真旅项目不存在");
		
		ContractQueryCriteria contractQueryCriteria = new ContractQueryCriteria();
		contractQueryCriteria.setMemberId(outCustomerServiceImpl.getCanaFinanceIdByOutCustomerId(institution, creditAgentRepaymentDTO.getCustomerId()));
		contractQueryCriteria.setProductId(projectInfo.getId());
		List<ContractInfoDTO> contractInfoDTOs = assetApiImpl.getContractsWithoutPaging(contractQueryCriteria);	
		if(CollectionUtils.isEmpty(contractInfoDTOs)){
			logger.error("合同表监管账户为空");
			throw WebException.instance(ReturnCode.ERROR, "该客户未签署合同");
		}
		if (contractInfoDTOs.size() > 1)
			throw WebException.instance(ReturnCode.ERROR, "该客户合同数量存在异常");

		//校验还款金额
		long miniRepaymentAmount;
		long maxiRepaymentAmount;
		try {
			miniRepaymentAmount = repaymentCalcServiceImpl.calcMinimumRepaymentAmount(creditAgentRepaymentDTO.getLoanInfoId());
			maxiRepaymentAmount = repaymentCalcServiceImpl.calcMaximumRepaymentAmount(creditAgentRepaymentDTO.getLoanInfoId());
		}catch(IllegalArgumentException e){
			throw WebException.instance(ReturnCode.ERROR, "放款批次号无效");
		}catch (Exception e) {
			throw WebException.instance(ReturnCode.ERROR, e.getMessage());
		}
		if(creditAgentRepaymentDTO.getFee() < miniRepaymentAmount)
			throw WebException.instance(ReturnCode.ERROR, "还款金额小于最低还款金额");
		if(creditAgentRepaymentDTO.getFee() > maxiRepaymentAmount)
			throw WebException.instance(ReturnCode.ERROR, "还款金额大于最大还款金额");
		
		CreditTransfer creditTransfer = creditTradeTransactionServiceImpl.creditAgentRepayment(projectInfo,creditAgentRepaymentDTO,contractInfoDTOs.get(0));
		creditTransferThreadPoolExecutor.doTransfer(creditTransfer, "订单编号：" + creditAgentRepaymentDTO.getTradeNo());
		return creditTransfer.getCreditTradeId();
	}

	@Override
	public PageResult<CreditTradeDTO> queryCreditLoanDetailsDetail(int page, int pageSize, String summaryId, String loginFinanceId) {
		CreditTradeExample example = new CreditTradeExample();
		Criteria criteria = example.createCriteria();
		criteria.andTradeTypeEqualTo(CreditTradeType.PAYMENT.name()).andSummaryIdLike("%" + summaryId.trim() + "%");
		if (StringUtils.isNotBlank(loginFinanceId)) {
			criteria.andFinaceCustomerIdEqualTo(loginFinanceId);
		}
		int total = creditTradeMapper.countByExample(example);
		example.setOrderByClause("trade_start_time desc");
		example.setLimitStart((page - 1) * pageSize);
		example.setLimitEnd(pageSize);
		List<CreditTrade> creditTrades = creditTradeMapper.selectByExample(example);
		List<CreditTradeDTO> creditTradeDTOs = CreditTradeConvertor.convert2CreditTradeDTOs(creditTrades);
		return new PageResult<CreditTradeDTO>(creditTradeDTOs, total);
	}

	@Override
	public PageResult<CreditTradeDTO> queryCreditTrades(CreditTradeQueryCriteria criteria) {
		CreditTradeExample example = getCreditTradeExample(criteria);
		int total = creditTradeMapper.countByExample(example);
		int page = criteria.getPage() > 0 ? criteria.getPage() : 1;
		int size = criteria.getPageSize() > 0 ? criteria.getPageSize() : 10;
		example.setOrderByClause("trade_start_time desc");
		example.setLimitStart((page - 1) * size);
		example.setLimitEnd(size);
		List<CreditTrade> creditTrades = creditTradeMapper.selectByExample(example);
		List<CreditTradeDTO> creditTradeDTOs = CreditTradeConvertor.convert2CreditTradeDTOs(creditTrades);
		return new PageResult<CreditTradeDTO>(creditTradeDTOs, total);
	}

	/**
	 * 將creditTrade转换成example
	 * 
	 * @param criteria
	 * @return
	 */
	private CreditTradeExample getCreditTradeExample(CreditTradeQueryCriteria criteria) {
		CreditTradeExample example = new CreditTradeExample();
		CreditTradeExample.Criteria criteriaDb = example.createCriteria();
		if (StringUtils.isNotBlank(criteria.getTradeStartDate())) {
			DateTime date = DateTimeUtil.parseDate10(criteria.getTradeStartDate());
			criteriaDb.andOriginTradeEndTimeGreaterThanOrEqualTo(date.toDate());
		}
		if (StringUtils.isNotBlank(criteria.getTradeEndDate())) {
			DateTime date = DateTimeUtil.parseDate10(criteria.getTradeEndDate());
			date = date.plusDays(1);
			criteriaDb.andOriginTradeEndTimeLessThan(date.toDate());
		}

		if (StringUtils.isNotBlank(criteria.getRefundStartDate())) {
			DateTime date = DateTimeUtil.parseDate10(criteria.getRefundStartDate());
			criteriaDb.andTradeStartTimeGreaterThanOrEqualTo(date.toDate());
		}
		if (StringUtils.isNotBlank(criteria.getRefundEndDate())) {
			DateTime date = DateTimeUtil.parseDate10(criteria.getRefundEndDate());
			date = date.plusDays(1);
			criteriaDb.andTradeStartTimeLessThan(date.toDate());
		}
		if (StringUtils.isNotBlank(criteria.getOrderNum()))
			criteriaDb.andOutTradeNoLike("%" + criteria.getOrderNum().trim() + "%");
		if (StringUtils.isNotBlank(criteria.getSummaryId()))
			criteriaDb.andSummaryIdLike("%" + criteria.getSummaryId().trim() + "%");
		if (StringUtils.isNotBlank(criteria.getCustomerName()))
			criteriaDb.andFinaceCustomerNameLike("%" + criteria.getCustomerName().trim() + "%");
		if (StringUtils.isNotBlank(criteria.getTradeType()))
			criteriaDb.andTradeTypeEqualTo(criteria.getTradeType());
		if (StringUtils.isNotBlank(criteria.getFinanceId()))
			criteriaDb.andFinaceCustomerIdEqualTo(criteria.getFinanceId());
		return example;
	}

	@Override
	public PageResult<CreditTradeFlowDTO> queryCreditFlowList(CreditTradeQueryCriteria criteria) {
		PageResult<CreditTradeFlowDTO> pageResult = new PageResult<>();
		CreditTradeRequest cRequest = new CreditTradeRequest();
		cRequest = CreditTradeConvertor.convert2tradeRequest(criteria);
		List<CreditTradeFlow> creditTrades = creditTradeDetailMapper.getCreditLoanFlowList(cRequest);
		List<CreditTradeFlowDTO> creditTradeDTOs = CreditTradeConvertor.convertFlow2DTO(creditTrades);
		int total = creditTradeDetailMapper.getCreditTradeFlowCount(cRequest);
		pageResult.setData(creditTradeDTOs);
		pageResult.setTotal(total);
		return pageResult;
	}
	
	@Override
	public PageResult<CreditStatementDTO> getCreditStatementNoPaging(CreditTradeQueryCriteria criteria) {
		PageResult<CreditStatementDTO> pageResult = new PageResult<>();
		CreditTradeRequest cRequest = new CreditTradeRequest();
		cRequest = CreditTradeConvertor.convert2tradeRequest(criteria);
		List<CreditTradeFlow> creditTrades = creditTradeDetailMapper.getCreditStatementNoPaging(cRequest);
		List<CreditStatementDTO> creditStatementDTOs = CreditTradeConvertor.convertFlow2StatementDTO(creditTrades);
		int total = creditTradeDetailMapper.getCreditTradeFlowCount(cRequest);
		pageResult.setData(creditStatementDTOs);
		pageResult.setTotal(total);
		return pageResult;
	}

	@Override
	public CreditTradeOperateDTO queryCreditTransferInfo(String id) {
		CreditTradeOperateDTO operateDTO = new CreditTradeOperateDTO();
		CreditTransfer creditTransfer = creditTransferMapper.selectByPrimaryKey(id);
		operateDTO = CreditTradeConvertor.convertTransfer2DTO(creditTransfer);
		return operateDTO;
	}

	/**
	 * 新启事务，完成转账状态（失败-->交易中)、修改业务流水号等，退出事务 通知银行转账
	 */
	@Override
	public CreditTransferStatus manualOperateCreditTransfer(String id, String operatorId) {
		CreditTransfer creditTransfer = creditTradeTransactionServiceImpl.manualUpdateCreditTransfer(id, operatorId);
		CreditTrade creditTrade = creditTradeMapper.selectByPrimaryKey(creditTransfer.getCreditTradeId());
		TransferFundForCreditRequestDTO creditDTO = CreditTradeConvertor.convert2TransferFundDTO(creditTransfer, creditTrade);
		AccountTradeStatus accountTradeStatus = accountApi.transferFundForCredit(creditDTO);
		CreditTransferStatus cStatus = CreditTransferStatus.HANDING;
		if (AccountTradeStatus.TRADE_FAIL == accountTradeStatus)
			cStatus = CreditTransferStatus.FAIL;
		if (AccountTradeStatus.TRADE_SUCCESS == accountTradeStatus)
			cStatus = CreditTransferStatus.SUCCESS;
		creditTradeTransactionServiceImpl.updateCreditTransferStatusById(id, cStatus);
		return cStatus;
	}

	@Override
	public List<CreditUsedLimit> queryUsedLimit(Date startDate, Date endDate, String institution) {
		CreditUsedLimitQueryCriteria creditUsedLimitQueryCriteria = new CreditUsedLimitQueryCriteria();
		creditUsedLimitQueryCriteria.setStartDate(startDate);
		creditUsedLimitQueryCriteria.setEndDate(endDate);
		creditUsedLimitQueryCriteria.setInstitution(institution);
		return creditTradeDetailMapper.getCreditUsedLimit(creditUsedLimitQueryCriteria);
	}

	@Override
	public CreditLoanDetailResponse getCreditLoanDetail(CreditLoanDetailRequest request) throws Exception {
		QueryLoanInfoDetailRequestDTO requestDto = new QueryLoanInfoDetailRequestDTO();
		requestDto.setLoanInfoId(request.getLoanInfoId());
		QueryLoanInfoDetailResponseDTO dto = financeReportApi.queryLoanInfoDetail(requestDto);
		CreditLoanDetailResponse response = CreditTradeConvertor.convert2CreditLoanDetailResponse(dto);
		return response;
	}

	@Override
	public LoanListResponse queryLoanInfoList(LoanInfoDetailRequest request) throws Exception {
		logger.info("真旅项目——【账单明细列表】接口传参如下：{}",new Gson().toJson(request));
		checkLoanInfoDetailRequestParam(request);
		QueryLoanInfoListRequestDTO queryLoanInfoListRequestDTO = new QueryLoanInfoListRequestDTO();
		if(StringUtils.isNotBlank(request.getCustomerId())){
			OutCustomer outCustomer = getOutCustomerByCustomerId(request.getInstitution(), request.getCustomerId());
			queryLoanInfoListRequestDTO.setFinanceId(outCustomer.getMemberId());
			queryLoanInfoListRequestDTO.setOutCustomerId(outCustomer.getOutCustomerId());
		}
		queryLoanInfoListRequestDTO.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		queryLoanInfoListRequestDTO.setLoanDateStart(request.getLoanDateStart());
		if(StringUtils.isNotBlank(request.getLoanDateEnd())){
			DateTime loanDateEnd = DateTimeUtil.parseDate10(request.getLoanDateEnd());
			loanDateEnd = loanDateEnd.plusDays(1);
			queryLoanInfoListRequestDTO.setLoanDateEnd(DateTimeUtil.format(loanDateEnd, "yyyy-MM-dd"));
		}
		queryLoanInfoListRequestDTO.setDueDateStart(request.getDueDateStart());
		if(StringUtils.isNotBlank(request.getDueDateEnd())){
			DateTime dueDateEnd = DateTimeUtil.parseDate10(request.getDueDateEnd());
			dueDateEnd = dueDateEnd.plusDays(1);
			queryLoanInfoListRequestDTO.setDueDateEnd(DateTimeUtil.format(dueDateEnd, "yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(request.getSettleStatus()))
			queryLoanInfoListRequestDTO.setSettleStatus(SettleStatus.valueOf(request.getSettleStatus()));
		queryLoanInfoListRequestDTO.setPage(request.getPage());
		queryLoanInfoListRequestDTO.setPageSize(request.getPageSize());
		QueryLoanInfoListResponseDTO queryLoanInfoListResponseDTO = financeReportApi.queryLoanInfoList(queryLoanInfoListRequestDTO);
		if(CollectionUtils.isEmpty(queryLoanInfoListResponseDTO.getLoanInfoDetailList()))
			return new LoanListResponse(new Long(0),new ArrayList<LoanInfoDetailVo>());
		List<String> memberIds = new ArrayList<String>();
		for(LoanInfoDetail loanInfoDetail : queryLoanInfoListResponseDTO.getLoanInfoDetailList())
			memberIds.add(loanInfoDetail.getFinanceId());
		LoanListResponse loanListResponse = CreditTradeConvertor.convert2LoanListResponse(queryLoanInfoListResponseDTO);
		return loanListResponse;
	}


	@Override
	public List<CreditTransfer> getHandlingCreditTrade() {
		CreditTransferExample creditTransferExample = new CreditTransferExample();
		creditTransferExample.createCriteria().andTransferStatusEqualTo(CreditTransferStatus.HANDING.name());
		return creditTransferMapper.selectByExample(creditTransferExample);
	}
	
	@Override
	public String getMemberIdById(String id) {
		return creditTradeMapper.selectByPrimaryKey(id).getFinaceCustomerId();
	}
	
	/**
	 * 检查参数
	 * 
	 * @param request
	 *            LoanInfoDetailRequest
	 */
	private void checkLoanInfoDetailRequestParam(LoanInfoDetailRequest request) {
		if (StringUtils.isBlank(request.getInstitution()))
			throw WebException.instance(ReturnCode.TP3001);
		if (StringUtils.isNotBlank(request.getLoanDateStart()) && !DateTimeUtil.validateDate10(request.getLoanDateStart()))
			throw WebException.instance(ReturnCode.TP3024);
		if (StringUtils.isNotBlank(request.getLoanDateEnd()) && !DateTimeUtil.validateDate10(request.getLoanDateEnd()))
			throw WebException.instance(ReturnCode.TP3024);
		if (StringUtils.isNotBlank(request.getDueDateStart()) && !DateTimeUtil.validateDate10(request.getDueDateStart()))
			throw WebException.instance(ReturnCode.TP3024);
		if (StringUtils.isNotBlank(request.getDueDateEnd()) && !DateTimeUtil.validateDate10(request.getDueDateEnd()))
			throw WebException.instance(ReturnCode.TP3024);
		if (request.getPageSize() > 30)
			throw WebException.instance(ReturnCode.TP3025);
		if (StringUtils.isNotBlank(request.getSettleStatus()) && !EnumUtils.isValidEnum(SettleStatus.class, request.getSettleStatus()))
			throw WebException.instance(ReturnCode.TP3027);
		// 验签
		StringBuilder verifyStringBuilder = new StringBuilder();
		verifyStringBuilder.append(request.getInstitution());
		if(StringUtils.isNotBlank(request.getCustomerId()))
			verifyStringBuilder.append(request.getCustomerId());
		if(StringUtils.isNotBlank(request.getLoanDateStart()))
			verifyStringBuilder.append(request.getLoanDateStart());
		if(StringUtils.isNotBlank(request.getLoanDateEnd()))
			verifyStringBuilder.append(request.getLoanDateEnd());
		if(StringUtils.isNotBlank(request.getDueDateStart()))
			verifyStringBuilder.append(request.getDueDateStart());
		if(StringUtils.isNotBlank(request.getDueDateEnd()))
			verifyStringBuilder.append(request.getDueDateEnd());
		if(StringUtils.isNotBlank(request.getSettleStatus()))
			verifyStringBuilder.append(request.getSettleStatus());
		verifyStringBuilder.append(request.getPage()).append(request.getPageSize());
		vbamCommonService.rsaVerify(verifyStringBuilder.toString().getBytes(), request.getInstitution(),
				request.getSign().getBytes(), true);
	}

	/**
	 * 根据outCustomerId,institutionId获取memerId(cana的用户Id)
	 * 
	 * @param institutionId
	 *            外部客户机构Id（比如travelzen）
	 * @param outCustomerId
	 *            外部客户Id
	 */
	private OutCustomer getOutCustomerByCustomerId(String institutionId, String outCustomerId) {
		OutCustomerExample example = new OutCustomerExample();
		example.createCriteria().andOutCustomerIdEqualTo(outCustomerId).andInstitutionIdEqualTo(institutionId);
		List<OutCustomer> userComparisons = outCustomerMapper.selectByExample(example);
		if (userComparisons == null || userComparisons.isEmpty())
			throw WebException.instance(ReturnCode.TP3002);
		return userComparisons.get(0);
	}

}

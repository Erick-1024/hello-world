package com.cana.credit.service.convertors;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import com.cana.credit.dao.po.CreditTrade;
import com.cana.credit.dao.po.CreditTradeFlow;
import com.cana.credit.dao.po.CreditTradeRequest;
import com.cana.credit.dao.po.CreditTransfer;
import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.flight.finance.common.dto.CreditTradeDTO;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.credit.dto.trade.CreditStatementDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeFlowDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeOperateDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeStateResultDTO;
import com.cana.vbam.common.credit.enums.CreditTradeStatus;
import com.cana.vbam.common.credit.enums.CreditTradeType;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailResponse;
import com.cana.vbam.common.credit.openapi.LoanInfoDetailVo;
import com.cana.vbam.common.credit.openapi.LoanListResponse;
import com.cana.vbam.common.credit.openapi.PaidAmountVo;
import com.cana.vbam.common.credit.openapi.RepaymentRecordVo;
import com.cana.vbam.common.repayment.dto.LoanInfoDetail;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoDetailResponseDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListResponseDTO;
import com.cana.vbam.common.repayment.dto.RepaymentDetail;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.util.DateUtils;

public class CreditTradeConvertor {
	 
    public static CreditTradeStateResultDTO convertCreditTradeDao2Dto(CreditTrade creditTrade){
        CreditTradeStateResultDTO queryDTO=new CreditTradeStateResultDTO();
        queryDTO.setTradeNo(creditTrade.getOutTradeNo());
        queryDTO.setTradeStatus(creditTrade.getTradeStatus());
        if (creditTrade.getTradeEndTime() != null)
        	queryDTO.setTradeSuccessTime(DateTimeUtil.format(creditTrade.getTradeEndTime(), DateTimeUtil.DATE_TIME_PATTERN));
        queryDTO.setTradeType(creditTrade.getTradeType());
        queryDTO.setTranSeq(creditTrade.getId());
        return queryDTO;
    }
    
	public static CreditTrade convertCreditPayDTO2CreditTrade(CreditPayDTO creditPayDTO){
		CreditTrade creditTrade = new CreditTrade();
		creditTrade.setOutTradeNo(creditPayDTO.getTradeNo());
		creditTrade.setOutCustomerId(creditPayDTO.getCustomerId());
		creditTrade.setOutTradeTime(DateTimeUtil.parseDate(DateTimeUtil.DATE_TIME_PATTERN, creditPayDTO.getTradeTime()).toDate());
		creditTrade.setOutCustomerName(creditPayDTO.getCustomerName());
		creditTrade.setInstitution(creditPayDTO.getInstitution());
		creditTrade.setSign(creditPayDTO.getSign());
		creditTrade.setFee(creditPayDTO.getPaymentFee());
		creditTrade.setOrderInfo(creditPayDTO.getOrderInfo());
		creditTrade.setNotifyUrl(creditPayDTO.getNotifyURL());
		creditTrade.setTradeType(CreditTradeType.PAYMENT.name());
		creditTrade.setTradeStatus(CreditTradeStatus.SUCCESS.name());
		return creditTrade;
	}
	
    public static CreditTrade convertCreditTradeRefundDTO2CreditTrade(CreditRefundDTO creditRefundDTO) {
    	CreditTrade creditTrade = new CreditTrade();
    	creditTrade.setInstitution(creditRefundDTO.getInstitution());
    	creditTrade.setOutCustomerId(creditRefundDTO.getCustomerId());
    	creditTrade.setOutTradeNo(creditRefundDTO.getTradeNo());
    	creditTrade.setOutOriginTradeNo(creditRefundDTO.getOriginTradeNo());
    	creditTrade.setFee(creditRefundDTO.getRefundFee());
    	creditTrade.setNotifyUrl(creditRefundDTO.getNotifyURL());
    	creditTrade.setSign(creditRefundDTO.getSign());
    	creditTrade.setOutTradeTime(DateTimeUtil.parseDate(DateTimeUtil.DATE_TIME_PATTERN, creditRefundDTO.getTradeTime()).toDate());
    	creditTrade.setTradeType(CreditTradeType.REFUND.name());
    	creditTrade.setTradeStatus(CreditTradeStatus.HANDING.name());
    	creditTrade.setTradeStartTime(new Date());
    	return creditTrade;
    }
    
    public static CreditTrade convertCreditAgentRepaymentDTO2CreditTrade(CreditAgentRepaymentDTO creditAgentRepaymentDTO) {
    	CreditTrade creditTrade = new CreditTrade();
    	creditTrade.setInstitution(creditAgentRepaymentDTO.getInstitution());
    	creditTrade.setOutCustomerId(creditAgentRepaymentDTO.getCustomerId());
    	creditTrade.setOutTradeNo(creditAgentRepaymentDTO.getTradeNo());
    	creditTrade.setFee(creditAgentRepaymentDTO.getFee());
    	creditTrade.setNotifyUrl(creditAgentRepaymentDTO.getNotifyURL());
    	creditTrade.setSign(creditAgentRepaymentDTO.getSign());
    	creditTrade.setOutTradeTime(DateTimeUtil.parseDate(DateTimeUtil.DATE_TIME_PATTERN, creditAgentRepaymentDTO.getTradeTime()).toDate());
    	creditTrade.setTradeType(CreditTradeType.AGENT_REPAYMENT.name());
    	creditTrade.setTradeStatus(CreditTradeStatus.HANDING.name());
    	creditTrade.setTradeStartTime(new Date());
    	return creditTrade;
    }
    
    public static List<CreditTradeDTO> convert2CreditTradeDTOs(List<CreditTrade> creditTrades){
    	if(creditTrades == null || creditTrades.isEmpty())
    		return null;
    	List<CreditTradeDTO> creditTradeDTOs = new ArrayList<>();
    	for(CreditTrade creditTrade : creditTrades){
    		CreditTradeDTO dto = new CreditTradeDTO();
    		dto.setId(creditTrade.getId());
    		dto.setOutTradeNo(creditTrade.getOutTradeNo());
    		dto.setOutCustomerId(creditTrade.getOutCustomerId());
    		dto.setOutTradeTime(creditTrade.getOutTradeTime());
    		dto.setOutCustomerName(creditTrade.getOutCustomerName());
    		dto.setOutOriginTradeNo(creditTrade.getOutOriginTradeNo());
    		dto.setFinaceCustomerId(creditTrade.getFinaceCustomerId());
    		dto.setFinaceCustomerName(creditTrade.getFinaceCustomerName());
    		dto.setInstitution(creditTrade.getInstitution());
    		dto.setSign(creditTrade.getSign());
    		if(creditTrade.getFee()!=null)
    			dto.setFee(MoneyUtil.cent2Yuan(creditTrade.getFee()));
    		dto.setOrderInfo(creditTrade.getOrderInfo());
    		dto.setNotifyUrl(creditTrade.getNotifyUrl());
    		dto.setTradeType(creditTrade.getTradeType());
    		dto.setTradeStatus(creditTrade.getTradeStatus());
    		dto.setTradeStatusDesc(CreditTradeStatus.valueOf(dto.getTradeStatus()).desc());
    		dto.setSummaryId(creditTrade.getSummaryId());
    		dto.setTradeStartTime(creditTrade.getTradeStartTime());
    		dto.setTradeEndTime(creditTrade.getTradeEndTime());
    		dto.setOriginTradeEndTime(creditTrade.getOriginTradeEndTime());
    		creditTradeDTOs.add(dto);
    	}
    	return creditTradeDTOs;
    }
    
    public static List<CreditTradeFlowDTO> convertFlow2DTO(List<CreditTradeFlow> creditTradeFlows){
    	List<CreditTradeFlowDTO> creditTradeDTOs=new ArrayList<>();
    	for(CreditTradeFlow creditTradeFlow:creditTradeFlows){
    		CreditTradeFlowDTO creditTradeDTO=new CreditTradeFlowDTO();
    		creditTradeDTO.setId(creditTradeFlow.getId());
    		creditTradeDTO.setOutTradeNo(creditTradeFlow.getOutTradeNo());
    		creditTradeDTO.setSummaryId(creditTradeFlow.getSummaryId());
    		creditTradeDTO.setFinanceCustomerName(creditTradeFlow.getFinaceCustomerName());
    		creditTradeDTO.setTradeStartTime(creditTradeFlow.getTradeStartTime());
    		creditTradeDTO.setTransferStartTime(creditTradeFlow.getTransferStartTime());
    		creditTradeDTO.setTransferStatus(CreditTransferStatus.valueOf(creditTradeFlow.getTransferStatus()).desc());
    		String fee=MoneyUtil.formatMoney(creditTradeFlow.getFee()==null?0:creditTradeFlow.getFee());
    		creditTradeDTO.setFee(fee);
    		creditTradeDTO.setFromAccountNo(creditTradeFlow.getFromAccountNo());
    		creditTradeDTO.setTransferType(creditTradeFlow.getTransferType());
    		//对账单下载新加的
    		creditTradeDTO.setCreditTradeId(creditTradeFlow.getCreditTradeId());
    		if(StringUtils.isNotBlank(creditTradeFlow.getTradeType()))
    			creditTradeDTO.setTradeType(CreditTradeType.valueOf(creditTradeFlow.getTradeType()).desc());
    		if(StringUtils.isNotBlank(creditTradeFlow.getTradeStatus()))
    				creditTradeDTO.setTradeStatus(CreditTradeStatus.valueOf(creditTradeFlow.getTradeStatus()).desc());
    		creditTradeDTO.setBusinessSeq(creditTradeFlow.getBusinessSeq());
    		creditTradeDTO.setOutCustomerName(creditTradeFlow.getOutCustomerName());
    		creditTradeDTOs.add(creditTradeDTO);
    	}
    	return creditTradeDTOs;
    }
    
    public static List<CreditStatementDTO> convertFlow2StatementDTO(List<CreditTradeFlow> creditTradeFlows){
    	List<CreditStatementDTO> creditStatementDTOs=new ArrayList<>();
    	for(CreditTradeFlow creditTradeFlow:creditTradeFlows){
    		CreditStatementDTO creditStatementDTO = new CreditStatementDTO();
    		creditStatementDTO.setOutTradeNo(creditTradeFlow.getOutTradeNo());
    		creditStatementDTO.setOutCustomerName(creditTradeFlow.getOutCustomerName());
    		Date tradeStartTime = creditTradeFlow.getTradeStartTime();
    		creditStatementDTO.setTradeStartTime(tradeStartTime == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(tradeStartTime));
    		if(StringUtils.isNotBlank(creditTradeFlow.getTradeType()))
    			creditStatementDTO.setTradeType(CreditTradeType.valueOf(creditTradeFlow.getTradeType()).desc());
    		if(StringUtils.isNotBlank(creditTradeFlow.getTradeStatus()))
    			creditStatementDTO.setTradeStatus(CreditTradeStatus.valueOf(creditTradeFlow.getTradeStatus()).desc());
    		creditStatementDTO.setTransferStatus(CreditTransferStatus.valueOf(creditTradeFlow.getTransferStatus()).desc());
    		String fee=MoneyUtil.formatMoney(creditTradeFlow.getFee()==null?0:creditTradeFlow.getFee());
    		if("退款".equals(creditStatementDTO.getTradeType()) || "账户还款".equals(creditStatementDTO.getTradeType()))
    			fee = "-" +fee;
    		creditStatementDTO.setFee(fee);
    		creditStatementDTO.setBusinessSeq(creditTradeFlow.getBusinessSeq());
    		creditStatementDTOs.add(creditStatementDTO);
    	}
    	return creditStatementDTOs;
    }
    
    public static CreditTradeRequest convert2tradeRequest(CreditTradeQueryCriteria criteria){
    	CreditTradeRequest request=new CreditTradeRequest();
    	request.setPage(criteria.getPage());
    	request.setPageSize(criteria.getPageSize());
    	if(StringUtils.isNotBlank(criteria.getCustomerName()))
    		request.setCustomerName(criteria.getCustomerName());
    	if(StringUtils.isNotBlank(criteria.getSummaryId()))
    		request.setSummaryId(criteria.getSummaryId());
    	if(StringUtils.isNotBlank(criteria.getTradeStartDate()))
    		request.setTradeStartDate(criteria.getTradeStartDate());
    	if(StringUtils.isNotBlank(criteria.getTradeEndDate()))
    		request.setTradeEndDate(stringPlusDay(criteria.getTradeEndDate()));
    	if(StringUtils.isNotBlank(criteria.getTradeStatus()))
    		request.setTradeStatus(criteria.getTradeStatus());
    	if(StringUtils.isNotBlank(criteria.getTradeType()))
    		request.setTradeType(criteria.getTradeType());
    	if(StringUtils.isNotBlank(criteria.getTransferType()))
    		request.setTransferType(criteria.getTransferType());
    	request.setStatementSpecificField(criteria.isStatementSpecificField());
    	return request;
    }
     
    private static String stringPlusDay(String originDate){
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String newDate = sdf.format(new DateTime(originDate).plusDays(1).toDate());
		return newDate;
	}
    
    public static CreditTradeOperateDTO convertTransfer2DTO(CreditTransfer creditTransfer){
    	CreditTradeOperateDTO cDto=new CreditTradeOperateDTO();
    	cDto.setFromAccountNo(creditTransfer.getFromAccountNo());
    	cDto.setFromAccountName(creditTransfer.getFromAccountName());
    	cDto.setToAccountNo(creditTransfer.getToAccountNo());
    	cDto.setToAccountName(creditTransfer.getToAccountName());
    	String toFee=MoneyUtil.formatMoney(creditTransfer.getFee()==null?0:creditTransfer.getFee());
    	cDto.setToFee(toFee);
    	return cDto;
    }
    
    public static TransferFundForCreditRequestDTO convert2TransferFundDTO(CreditTransfer creditTransfer, CreditTrade creditTrade){
    	TransferFundForCreditRequestDTO creditRequestDTO=new TransferFundForCreditRequestDTO();
    	creditRequestDTO.setAmount(creditTransfer.getFee());
    	creditRequestDTO.setBusinessSeq(creditTransfer.getBusinessSeq());
    	creditRequestDTO.setTransferOutAccountNo(creditTransfer.getFromAccountNo());
    	creditRequestDTO.setTransferInAccountNo(creditTransfer.getToAccountNo());
    	creditRequestDTO.setBankRemark("订单编号：" + creditTrade.getOutTradeNo());
    	return creditRequestDTO;
    }
    
    public static CreditLoanDetailResponse convert2CreditLoanDetailResponse(QueryLoanInfoDetailResponseDTO dto) throws IllegalAccessException, InvocationTargetException{
    	CreditLoanDetailResponse response=new CreditLoanDetailResponse();
    	if(dto==null){
    		return response;
    	}
    	response.setRepaymentAmount(dto.getRepaymentAmount());
    	response.setExtensionDays(dto.getExtensionDays());
    	List<RepaymentRecordVo> recordVos=new ArrayList<>();
    	for(RepaymentDetail repaymentDetail:dto.getRepaymentDetailList()){
    		RepaymentRecordVo recordVo=new RepaymentRecordVo();
    		recordVo.setRepaymentDate(repaymentDetail.getRepaymentDate());
    		recordVo.setRepaymentMethod(repaymentDetail.getRepaymentMethod().name());
    		recordVo.setOpTime(DateUtils.format(repaymentDetail.getOpTime(), 20));
    		PaidAmountVo amountVo=new PaidAmountVo();
    		BeanUtils.copyProperties(amountVo, repaymentDetail.getRepaymentAmount());
    		recordVo.setPaidAmount(amountVo);
    		recordVos.add(recordVo);
    	}
    	response.setRepaymentRecords(recordVos);
    	return response;
    }
    
    public static LoanListResponse convert2LoanListResponse(QueryLoanInfoListResponseDTO queryLoanInfoListResponseDTO){
    		
    	LoanListResponse loanListResponse = new LoanListResponse();
    	List<LoanInfoDetailVo> loanInfoDetailVos = new ArrayList<LoanInfoDetailVo>();
    	for(LoanInfoDetail loanInfoDetail : queryLoanInfoListResponseDTO.getLoanInfoDetailList()){
    		LoanInfoDetailVo loanInfoDetailvo = new LoanInfoDetailVo();
    		loanInfoDetailvo.setLoanInfoId(loanInfoDetail.getLoanInfoId());
    		loanInfoDetailvo.setLoanNo(loanInfoDetail.getLoanNo());
    		loanInfoDetailvo.setLoanDate(loanInfoDetail.getLoanDate());
    		loanInfoDetailvo.setDueDate(loanInfoDetail.getDueDate());
    		loanInfoDetailvo.setRepaymentAmount(loanInfoDetail.getRepaymentAmount());
    		loanInfoDetailvo.setSettleStatus(loanInfoDetail.getSettleStatus().name());
    		loanInfoDetailvo.setCustomerId(loanInfoDetail.getOutCustomerId());
    		loanInfoDetailvo.setExtensionDays(loanInfoDetail.getExtensionDays());
    		loanInfoDetailVos.add(loanInfoDetailvo);
    	}
    	loanListResponse.setTotal(new Long(queryLoanInfoListResponseDTO.getTotalNum()));
    	loanListResponse.setLoanInfos(loanInfoDetailVos);
    	return loanListResponse;
    	
    }
    
}

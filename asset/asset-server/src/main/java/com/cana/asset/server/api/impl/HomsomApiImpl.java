package com.cana.asset.server.api.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.account.api.IAccountApi;
import com.cana.asset.api.IHomsomApi;
import com.cana.asset.service.IHomsomService;
import com.cana.asset.service.transaction.IHomsomTransactionService;
import com.cana.homsom.dao.po.HomsomDailyReportTransferDetail;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigDTO;
import com.cana.vbam.common.homsom.dto.CounterpartyConfigRequestDTO;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomBuyBackTicketExcelListRequest;
import com.cana.vbam.common.homsom.dto.HomsomBuybackCounterpartyExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementCounterpartyExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTicketExcelListRequest;
import com.cana.vbam.common.homsom.dto.LoanAuditDTO;
import com.cana.vbam.common.homsom.dto.LoanAuditDetailRequest;
import com.cana.vbam.common.homsom.dto.LoanAuditExcelDTO;
import com.cana.vbam.common.homsom.dto.LoanAuditListRequest;
import com.cana.vbam.common.homsom.dto.PaymentDataRequestDTO;
import com.cana.vbam.common.homsom.dto.SettlementCounterpartyDTO;
import com.cana.vbam.common.homsom.dto.SettlementSummaryDTO;
import com.cana.vbam.common.homsom.dto.StatementExcelDTO;
import com.cana.vbam.common.homsom.dto.SubmitSettlementRequestDTO;
import com.cana.vbam.common.homsom.dto.TicketDTO;
import com.cana.vbam.common.homsom.dto.TicketExcelDTO;
import com.cana.vbam.common.homsom.dto.TicketImportRequest;
import com.cana.vbam.common.homsom.dto.TicketListRequest;
import com.cana.vbam.common.homsom.dto.TicketLoanDetailDTO;
import com.cana.vbam.common.homsom.dto.TicketLoanRequestDTO;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.PaginationUtils;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

public class HomsomApiImpl implements IHomsomApi{

	@Resource
	private IHomsomService homsomService;
	
	@Resource
	private IHomsomTransactionService homsomTransactionServiceImpl;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	@Resource
	private IAccountApi accountApi;
	
	@Override
	public void ticketImport(TicketImportRequest request) throws Exception {
		homsomService.ticketImport(request);
	}

	@Override
	public ListResult<TicketDTO> getTicketList(TicketListRequest ticketListRequest) {
		PaginationUtils.StandardizingPagination(ticketListRequest);
		return homsomService.getTicketList(ticketListRequest);
	}

	@Override
	public List<TicketExcelDTO> getExcelDTOs(TicketListRequest ticketListRequest) {
		return homsomService.getExcelDTOs(ticketListRequest);
	}

	@Override
	public ListResult<LoanAuditDTO> getLoanAuditList(LoanAuditListRequest loanAuditListRequest) {
		PaginationUtils.StandardizingPagination(loanAuditListRequest);
		return homsomService.getLoanAuditList(loanAuditListRequest);
	}

	@Override
	public List<LoanAuditExcelDTO> getExcelDTOs(LoanAuditListRequest loanAuditListRequest) {
		return homsomService.getExcelDTOs(loanAuditListRequest);
	}

	@Override
	public ListResult<LoanAuditDTO> getLoanAuditDetailList(LoanAuditDetailRequest loanAuditDetailRequest) {
		PaginationUtils.StandardizingPagination(loanAuditDetailRequest);
		return homsomService.getLoanAuditDetailList(loanAuditDetailRequest);
	}

	@Override
	public void confirmLoan(String date, Channel channel) {
		if(!DateTimeUtil.validateDate10(date))
			throw WebException.instance("日期格式不正确");
		if(channel == null)
			 throw WebException.instance("渠道不合法");
		HomsomDailyReportTransferDetail transferDetail = homsomTransactionServiceImpl.confirmLoan(date, channel);
		if (transferDetail != null) {
			TransferFundForCreditRequestDTO transferRequest = new TransferFundForCreditRequestDTO();
			transferRequest.setBusinessSeq(transferDetail.getTransferBusinessSeq());
			transferRequest.setTransferOutAccountNo(transferDetail.getFactorAccountNo());
			transferRequest.setTransferInAccountNo(transferDetail.getTransferAccountNo());
			transferRequest.setAmount(transferDetail.getAmount());
			transferRequest.setBankRemark(channel.desc() + "确认放款转账");
			// TODO 增加回调地址
			accountApi.transferFundForCredit(transferRequest);
		}
	}
	
	@Override
	public ListResult<CounterpartyConfigDTO> getCounterpartyConfig(CounterpartyConfigRequestDTO requestDTO) {
		return homsomService.getCounterpartyConfig(requestDTO);
	}

	@Override
	public ObjectResult<CounterpartyConfigDTO> getCounterpartyConfigDTO(CounterpartyConfigDTO queryDTO) {
		return homsomTransactionServiceImpl.getCounterpartyConfigDTO(queryDTO);
	}

	@Override
	public ObjectResult<String> modifyCounterpartyConfig(CounterpartyConfigDTO counterpartyConfigDTO){
		return homsomTransactionServiceImpl.modifyCounterpartyConfig(counterpartyConfigDTO);
	}

	@Override
	public String generateSettlementRedisKey() {
		return homsomService.generateSettlementRedisKey();
	}

	@Override
	public void importSettlement(List<List<String>> list, String userId, String rediskey, String mediaId, Channel channel) {
		if(StringUtils.isBlank(rediskey) || channel == null)
			throw WebException.instance("参数异常");
		homsomService.importSettlement(list, userId, rediskey, mediaId, channel);
	}

	@Override
	public void importSettlement2DB(String userId, String rediskey) {
		if(StringUtils.isBlank(rediskey))
			throw WebException.instance("参数异常");
		homsomTransactionServiceImpl.importSettlement2DB(userId, rediskey);
	}
	
	@Override
	public void importBuyBack(List<List<String>> list, String userId, String rediskey, String mediaId, Channel channel) {
		if(StringUtils.isBlank(rediskey) || channel == null)
			throw WebException.instance("参数异常");
		homsomService.importBuyBack(list, userId, rediskey, mediaId, channel);
	}

	@Override
	public void importBuyBack2DB(String userId, String rediskey) {
		if(StringUtils.isBlank(rediskey))
			throw WebException.instance("参数异常");
		homsomTransactionServiceImpl.importBuyBack2DB(userId, rediskey);
	}
	
	@Override
	public ListResult<HomsomSettlementTicketExcelDTO> getHomsomSettlementTicketExcelDTOFromRedis(HomsomSettlementTicketExcelListRequest homsomSettlementTicketExcelListRequest) {
		if(StringUtils.isBlank(homsomSettlementTicketExcelListRequest.getRediskey()))
			throw WebException.instance("参数异常");
		PaginationUtils.StandardizingPagination(homsomSettlementTicketExcelListRequest);
		return homsomService.getHomsomSettlementTicketExcelDTOFromRedis(homsomSettlementTicketExcelListRequest);
	}
	
	@Override
	public ListResult<HomsomBuyBackTicketExcelDTO> getHomsomBuyBackTicketExcelDTOFromRedis(HomsomBuyBackTicketExcelListRequest homsomBuyBackTicketExcelListRequest) {
		if(StringUtils.isBlank(homsomBuyBackTicketExcelListRequest.getRediskey()))
			throw WebException.instance("参数异常");
		PaginationUtils.StandardizingPagination(homsomBuyBackTicketExcelListRequest);
		return homsomService.getHomsomBuyBackTicketExcelDTOFromRedis(homsomBuyBackTicketExcelListRequest);
	}
	
	@Override
	public SettlementSummaryDTO querySummaryDataByPaymentType(PaymentDataRequestDTO queryDTO){
		return homsomService.querySummaryDataByPaymentType(queryDTO);
	}
	
	@Override
	public ListResult<TicketLoanDetailDTO> queryTicketLoanDetail(TicketLoanRequestDTO queryDTO){
		return homsomService.queryTicketLoanDetail(queryDTO);
	}

	@Override
	public Map<String, StatementExcelDTO> getSettlementExceDetaillList(Channel channel) {
		if(channel == null)
			throw WebException.instance("参数错误");
		return homsomService.getSettlementExcelDetailList(channel);
	}

	@Override
	public Collection<StatementExcelDTO> getBuyBackExcelDetailList(Channel channel) {
		if(channel == null)
			throw WebException.instance("参数错误");
		return homsomService.getBuyBackExcelDetailList(channel);
	}

	@Override
	public Collection<HomsomSettlementCounterpartyExcelDTO> getSettlementCounterpartyExcelList(Channel channel) {
		if(channel == null)
			throw WebException.instance("参数错误");
		return homsomService.getSettlementCounterpartyExcelList(channel);
	}

	@Override
	public Collection<HomsomBuybackCounterpartyExcelDTO> getBuybackCounterpartyExcelList(Channel channel) {
		if(channel == null)
			throw WebException.instance("参数错误");
		return homsomService.getBuybackCounterpartyExcelList(channel);
	}
	
	@Override
	public List<StatementExcelDTO> getRepaymentNoticeList(Channel channel) {
		if(channel == null)
			throw WebException.instance("参数错误");
		return homsomService.getRepaymentNoticeList(channel);
	}
	
	@Override
	public ListResult<SettlementCounterpartyDTO> querySettlementCounterpartyDetail(TicketLoanRequestDTO queryDTO){
		return homsomService.querySettlementCounterpartyDetail(queryDTO);
	}
	
	@Override
	public ListResult<String> buybackTicketListCheck(TicketLoanRequestDTO queryDTO){
		return homsomService.buybackTicketListCheck(queryDTO);
	}
	
	public ObjectResult<String> selttlementConfirm(String userId, SubmitSettlementRequestDTO requestDTO){
		UserVo userVo = memberQueryApi.findUserById(userId);
		return homsomTransactionServiceImpl.selttlementConfirm(userVo, requestDTO);
	}

}

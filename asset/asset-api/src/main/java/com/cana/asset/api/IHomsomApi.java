package com.cana.asset.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

public interface IHomsomApi {
	
	/**
	 * 导入客票
	 * @param request
	 */
	public void ticketImport(TicketImportRequest request) throws Exception;
	
	/**
	 * 获取客票列表数据
	 * @param ticketListRequest
	 * @return
	 */
	public ListResult<TicketDTO> getTicketList(TicketListRequest ticketListRequest);
	
	/*
	 * 获取客票记录导出记录
	 */
	public List<TicketExcelDTO> getExcelDTOs(TicketListRequest ticketListRequest);
	
	/**
	 * 获取放款审核列表数据
	 * @param loanAuditListRequest
	 * @return
	 */
	public ListResult<LoanAuditDTO> getLoanAuditList(LoanAuditListRequest loanAuditListRequest);
	
	/**
	 * 获取放款申请导出记录
	 * @param loanAuditListRequest
	 * @return
	 */
	public List<LoanAuditExcelDTO> getExcelDTOs(LoanAuditListRequest loanAuditListRequest);
	
	/**
	 * 获取放款明细
	 * @param loanAuditDetailRequest
	 * @return
	 */
	public ListResult<LoanAuditDTO> getLoanAuditDetailList(LoanAuditDetailRequest loanAuditDetailRequest);
	
	/*
	 * 获取交易对手配置信息
	 * @param requestDTO
	 * @return
	 */
	public ListResult<CounterpartyConfigDTO> getCounterpartyConfig(CounterpartyConfigRequestDTO requestDTO);
	
	/**
	 * 查询已有配置的交易对手信息
	 * @param requestDTO
	 * @return
	 */
	public ObjectResult<CounterpartyConfigDTO> getCounterpartyConfigDTO(CounterpartyConfigDTO queryDTO);
	
	/**
	 * 保存交易对手配置
	 * @param counterpartyConfigDTO
	 */
	public ObjectResult<String> modifyCounterpartyConfig(CounterpartyConfigDTO counterpartyConfigDTO);
	
	/**
	 * 对某个业务合同下的某天的所有交易对手产生的放款申请进行放款
	 * @param date
	 * @param channel
	 */
	public void confirmLoan(String date, Channel channel);
	
	public String generateSettlementRedisKey();
	
	public void importSettlement(List<List<String>> list, String userId, String rediskey, String mediaId, Channel channel);
	
	public void importSettlement2DB(String userId, String rediskey);
	
	public void importBuyBack(List<List<String>> list, String userId, String rediskey, String mediaId, Channel channel);
	
	public void importBuyBack2DB(String userId, String rediskey);
	
	/**
	 * 查询核销/回购汇总数据
	 * @param paymentType
	 * @return
	 */
	public SettlementSummaryDTO querySummaryDataByPaymentType(PaymentDataRequestDTO queryDTO);
	
	public ListResult<HomsomSettlementTicketExcelDTO> getHomsomSettlementTicketExcelDTOFromRedis(HomsomSettlementTicketExcelListRequest homsomSettlementTicketExcelListRequest);
	
	public ListResult<HomsomBuyBackTicketExcelDTO> getHomsomBuyBackTicketExcelDTOFromRedis(HomsomBuyBackTicketExcelListRequest homsomBuyBackTicketExcelListRequest);
	
	/**
	 * 查询核销/回购交易对手详细数据
	 * @param queryDTO
	 * @return
	 */
	public ListResult<TicketLoanDetailDTO> queryTicketLoanDetail(TicketLoanRequestDTO queryDTO);

	/**
	 * 查询核销/回购交易对手汇总数据
	 * @param queryDTO
	 * @return
	 */
	public ListResult<SettlementCounterpartyDTO> querySettlementCounterpartyDetail(TicketLoanRequestDTO queryDTO);
	
	/**
	 * 核销/回购确认
	 * @param userVo 操作人
	 * @param requestDTO
	 * @return
	 */
	public ObjectResult<String> selttlementConfirm(String userId, SubmitSettlementRequestDTO requestDTO);
	
	public Map<String, StatementExcelDTO> getSettlementExceDetaillList(Channel channel);
	
	public Collection<StatementExcelDTO> getBuyBackExcelDetailList(Channel channel);
	
	public Collection<HomsomSettlementCounterpartyExcelDTO> getSettlementCounterpartyExcelList(Channel channel);
	
	public Collection<HomsomBuybackCounterpartyExcelDTO> getBuybackCounterpartyExcelList(Channel channel);
	
	public List<StatementExcelDTO> getRepaymentNoticeList(Channel channel);
	
	/**
	 * 回购机票票号校验
	 * @param queryDTO
	 * @return
	 */
	public ListResult<String> buybackTicketListCheck(TicketLoanRequestDTO queryDTO);
	
}

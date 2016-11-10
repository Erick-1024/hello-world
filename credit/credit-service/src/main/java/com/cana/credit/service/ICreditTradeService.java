package com.cana.credit.service;

import java.util.Date;
import java.util.List;

import com.cana.credit.dao.po.CreditTransfer;
import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.flight.finance.common.dto.CreditTradeDTO;
import com.cana.flight.finance.common.dto.CreditTradeQueryCriteria;
import com.cana.flight.finance.common.dto.QueryCreditTradeStateDTO;
import com.cana.vbam.common.credit.dto.trade.CreditStatementDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeFlowDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeOperateDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeStateResultDTO;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailRequest;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailResponse;
import com.cana.vbam.common.credit.openapi.LoanInfoDetailRequest;
import com.cana.vbam.common.credit.openapi.LoanListResponse;
import com.cana.vbam.common.dto.PageResult;

public interface ICreditTradeService {

    /**
     * 授信退款接口
     * @param creditRefundDTO
     * @return 如果退款受理成功的话（不包含转账）返回Cana平台的交易流水号
     */
	public String creditRefund(CreditRefundDTO creditRefundDTO);
	
    /**
     * 授信支付接口
     * @param creditPayDTO 传入值
     * @return 如果支付成功的话返回Cana平台的交易流水号
     */
    public String creditPay(CreditPayDTO creditPayDTO);
    
    /**
     * 代还款接口
     * @param creditAgentRepaymentDTO
     * @return 如果代还款受理成功的话（不包含转账）返回Cana平台的交易流水号
     */
	public String creditAgentRepayment(CreditAgentRepaymentDTO creditAgentRepaymentDTO);
	
    /**
     * 授信支付、退款状态查询
     * @param queryDTO
     * @return
     */
    public CreditTradeStateResultDTO queryCreditTradeState(QueryCreditTradeStateDTO queryDTO);
    
    /**
	 * 查询放款明细详情
	 * @param summaryId 汇总编号
	 * @return
	 */
	public PageResult<CreditTradeDTO> queryCreditLoanDetailsDetail(int page,int pageSize,String summaryId, String loginFinanceId);
	
	/**
	 * 查询退款明细列表
	 * @param criteria 查询条件
	 * @return
	 */
	public PageResult<CreditTradeDTO> queryCreditTrades(CreditTradeQueryCriteria criteria);
	
	/**
	 * 查询放款流水列表
	 * @param criteria
	 * @return
	 */
	public PageResult<CreditTradeFlowDTO> queryCreditFlowList(CreditTradeQueryCriteria criteria);
	
	/**
	 * 查询对账单（没有分页）
	 * @param criteria
	 * @return
	 */
	public PageResult<CreditStatementDTO> getCreditStatementNoPaging(CreditTradeQueryCriteria criteria);
	
	public CreditTradeOperateDTO queryCreditTransferInfo(String id);
	
	/**
	 * 人工干预支付接口
	 */
	public CreditTransferStatus manualOperateCreditTransfer(String id,String operator);
	
	/**
	 * 查询客户某段时间内的额度使用值
	 * @param startDate 开始时间(包含)
	 * @param endDate 结束时间(包含)
	 * @param institution 机构
	 * @return
	 */
	public List<CreditUsedLimit> queryUsedLimit(Date startDate, Date endDate, String institution);
	
	/**
	 * openApi
	 * 查询放款信息列表
	 * @param request
	 * @return
	 */
	public LoanListResponse queryLoanInfoList(LoanInfoDetailRequest request) throws Exception;
	/**
	 * 查询账单明细详情
	 * @param request
	 * @return
	 */
	public CreditLoanDetailResponse getCreditLoanDetail(CreditLoanDetailRequest request)throws Exception;
	
	/**
	 * 获得所有状态处于转账中的授信交易记录
	 * @return
	 */
	public List<CreditTransfer> getHandlingCreditTrade();
	
	/**
	 * 获取获取某笔交易的所属用户
	 * @param id CreditTradeId
	 * @return 用户Id
	 */
	public String getMemberIdById(String id);

}

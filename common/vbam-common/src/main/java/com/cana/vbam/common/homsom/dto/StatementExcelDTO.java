package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class StatementExcelDTO implements Serializable {

    /**
     *出票日期，格式:yyyy-MM-dd，即开票日期
     */
    private String issueDate;

    /**
     *代理商编号，即单位编号
     */
    private String agentCode;

    /**
     *交易对手名称，即单位客户名称
     */
    private String counterpartyName;

    /**
     *订单号，即销售单号
     */
    private String orderId;

    /**
     *票号
     */
    private String ticketNo;

    /**
     *结算金额，即实还金额＝提现金额＋应还利息＋应还逾期费＋应退金额
     */
    private String settleAmount;
    
    /**
     *回购日期，格式:yyyy-MM-dd
     */
    private String buybackDate;

    /**
     *贷款本金，即提现金额
     */
    private String loanAmount;
    
    /**
     *贷款日期，格式:yyyy-MM-dd，即提现日期
     */
    private String loanDate;
    
    /**
     *备注
     */
    private String remark;
    
    /**
     *还款日期，格式:yyyy-MM-dd，即上传excel日期
     */
    private String settleDate;
    
    /**
     *上传excel中的账单账期
     */
    private String billingPeriod;

    /**
     *上传excel中的结算账期
     */
    private String settlePeriod;
    
    /**
     *应还利息
     */
    private String interestAmount;
    
    /**
     *20%退款
     */
    private String refundFeeOf20per;

    /**
     *应退金额
     */
    private String refundAmount;
    
    /**
     *核销状态: 未核销、已核销
     */
    private String state;
	
	private static final long serialVersionUID = 3695924147119202947L;

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}

	public String getBuybackDate() {
		return buybackDate;
	}

	public void setBuybackDate(String buybackDate) {
		this.buybackDate = buybackDate;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public String getSettlePeriod() {
		return settlePeriod;
	}

	public void setSettlePeriod(String settlePeriod) {
		this.settlePeriod = settlePeriod;
	}

	public String getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getRefundFeeOf20per() {
		return refundFeeOf20per;
	}

	public void setRefundFeeOf20per(String refundFeeOf20per) {
		this.refundFeeOf20per = refundFeeOf20per;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

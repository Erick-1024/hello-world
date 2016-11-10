package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;
import java.util.Date;

public class HomsomSettlementTicketDTO implements Serializable {

	private static final long serialVersionUID = 2955341946482567302L;

	/**
     *主键
     */
    private String id;

    /**
     *渠道
     */
    private String channel;

    /**
     *出票日期，格式:yyyy-MM-dd，即开票日期
     */
    private String issueDate;

    /**
     *代理商编号，即单位编号
     */
    private String agentCode;

    /**
     *交易对手ID
     */
    private String counterpartyId;

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
     *放款编号
     */
    private String loanNo;
    
    /**
     *贷款本金，即提现金额
     */
    private Long loanAmount;

    /**
     *应还利息
     */
    private Long interestAmount;

    /**
     *应还逾期费
     */
    private Long overdueAmount;

    /**
     *回购日期，格式:yyyy-MM-dd
     */
    private String buybackDate;

    /**
     *贷款日期，格式:yyyy-MM-dd，即提现日期
     */
    private String loanDate;

    /**
     *备注
     */
    private String remark;

    /**
     *上传excel中的账单账期
     */
    private String billingPeriod;

    /**
     *上传excel中的结算账期
     */
    private String settlePeriod;

    /**
     *放款天数＝还款日期－放款日期
     */
    private Integer loanDays;

    /**
     *20%退款
     */
    private Long refundFeeOf20per;

    /**
     *核销状态: 未核销、已核销
     */
    private String state;

    /**
     *上传该条记录的文件媒体id
     */
    private String mediaId;
    
    /**
     *创建时间
     */
    private Date createTime;

    /**
     *创建时间
     */
    private Date updateTime;
    
    private String reason;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

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

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
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

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Long getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(Long interestAmount) {
		this.interestAmount = interestAmount;
	}

	public Long getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(Long overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public String getBuybackDate() {
		return buybackDate;
	}

	public void setBuybackDate(String buybackDate) {
		this.buybackDate = buybackDate;
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

	public Integer getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(Integer loanDays) {
		this.loanDays = loanDays;
	}

	public Long getRefundFeeOf20per() {
		return refundFeeOf20per;
	}

	public void setRefundFeeOf20per(Long refundFeeOf20per) {
		this.refundFeeOf20per = refundFeeOf20per;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}

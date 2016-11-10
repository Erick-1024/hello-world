package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 恒顺核销履历DTO
 * @author jiangzhou.Ren
 * @time 2016年10月18日下午4:31:49
 */
public class HomsomSettlementTrackDTO implements Serializable{

	private static final long serialVersionUID = 3061901686670647650L;
	
	/**
     *主键
     */
    private String id;

    /**
     *渠道
     */
    private String channel;

    /**
     *交易对手ID
     */
    private String counterpartyId;

    /**
     *交易对手名称，即单位客户名称
     */
    private String counterpartyName;

    /**
     *放款编号
     */
    private String loanNo;

    /**
     *票号
     */
    private String ticketNo;

    /**
     *贷款总本金
     */
    private String loanAmount;

    /**
     *应还总利息
     */
    private String interestAmount;

    /**
     *应还总逾期费
     */
    private String overdueAmount;

    /**
     *出票日期，格式:yyyy-MM-dd
     */
    private String issueDate;

    /**
     *贷款日期，格式:yyyy-MM-dd
     */
    private String loanDate;

    /**
     *核销日期，格式:yyyy-MM-dd
     */
    private String settleDate;

    /**
     *回购日期，格式:yyyy-MM-dd
     */
    private String buybackDate;

    /**
     *放款天数＝还款日期－放款日期
     */
    private Integer loanDays;

    /**
     *核销履历状态: 已核销、已回购
     */
    private String state;

    /**
     *创建时间
     */
    private Date createTime;

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

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(String overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getBuybackDate() {
		return buybackDate;
	}

	public void setBuybackDate(String buybackDate) {
		this.buybackDate = buybackDate;
	}

	public Integer getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(Integer loanDays) {
		this.loanDays = loanDays;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
    
}

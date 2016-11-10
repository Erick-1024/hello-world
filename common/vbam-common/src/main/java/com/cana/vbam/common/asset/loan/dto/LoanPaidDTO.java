package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;
import java.util.Date;

public class LoanPaidDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     *主键
     */
    private String id;

    /**
     *放款信息Id
     */
    private String loanInfoId;

    /**
     *还款的还款计划Id
     */
    private String loanPlanId;

    /**
     *还款总金额
     */
    private Long paidAmount;

    /**
     *已还本金
     */
    private Long paidPrincipal;

    /**
     *已还利息
     */
    private Long paidInterest;

    /**
     *已还逾期费
     */
    private Long paidOverdue;

    /**
     *入账日期
     */
    private String paidDate;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    private String repaymentDate; // 固定还款日
    
	private String forwardDays; // 提前天数
	
	private String overdueDays; // 逾期天数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getLoanPlanId() {
		return loanPlanId;
	}

	public void setLoanPlanId(String loanPlanId) {
		this.loanPlanId = loanPlanId;
	}

	public Long getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Long getPaidPrincipal() {
		return paidPrincipal;
	}

	public void setPaidPrincipal(Long paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}

	public Long getPaidInterest() {
		return paidInterest;
	}

	public void setPaidInterest(Long paidInterest) {
		this.paidInterest = paidInterest;
	}

	public Long getPaidOverdue() {
		return paidOverdue;
	}

	public void setPaidOverdue(Long paidOverdue) {
		this.paidOverdue = paidOverdue;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
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

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getForwardDays() {
		return forwardDays;
	}

	public void setForwardDays(String forwardDays) {
		this.forwardDays = forwardDays;
	}

	public String getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(String overdueDays) {
		this.overdueDays = overdueDays;
	}
	
}

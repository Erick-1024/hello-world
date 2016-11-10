/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
public class RepaymentPlanDBDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6005492774538855442L;

	/**
    *主键（唯一判断，保理商+还款编号）
    */
    private String id;
    
    /**
    *放款编号
    */
    private String loanNo;
    
    /**
    *期数
    */
    private String repaymentPeriod;
    
    /**
    *融资金额
    */
    private String financeAmount;
    
    /**
    *起息日
    */
    private String valueDate;

    /**
    *结息日
    */
    private String settleInterestDate;
     
	/**
	*还款日
	*/
    private String repaymentDate;
    
    
    /**
    *应还本金
    */
    private String accountPrincipal;
    
    /**
     * 截止到今日应还利息
     */
    private String accountInterestUntilNow;
    
    /**
    *到期应还利息
    */
    private String accountInterest;
    
    /**
    *应还服务费
    */
    private String accountServiceCharge;
    
    /**
    *已还提前还款手续费
    */
    private String paidEarlyRepaymentCharge;
    
    /**
    *逾期本金
    */
    private String overduePrincipal;

    /**
    *逾期利息
    */
    private String overdueInterest;
    
    /**
    *逾期服务费
    */
    private String overdueServiceCharge;
    
    /**
    *应还展期费用
    */
    private String accountExtensionCharge;
    
    /**
     * 逾期管理费用=逾期本金罚息+逾期利息罚息+逾期服务费罚息+其他罚息
     */
    private String overdueManageCharge;
    
    /**
     * 应还总金额=应还本金+应还利息+应还服务费+应还展期费用+逾期本金+逾期利息+逾期服务费+已还提前还款手续费+逾期管理费用
     */
    private String accountTotalAmount;
    
    /**
    *结清状态（已结清、未结清）
    */
    private String settleStatus;
    
    /*------------ 还款详情所需的额外字段 -------------*/

    /**
    *融资余额
    */
    private String financeBalance;
    
    /**
    *已还正常本金
    */
    private String paidNormalPrincipal;
    
    /**
    *已还正常利息
    */
    private String paidNormalInterest;
    
    /**
    *已还正常服务费
    */
    private String paidNormalServiceCharge;
    
    /**
    *已还展期费用
    */
    private String paidExtensionCharge;
    
    /**
    *已还逾期本金
    */
    private String paidOverduePrincipal;
    
    /**
    *已还逾期利息
    */
    private String paidOverdueInterest;
    
    /**
    *已还逾期服务费
    */
    private String paidOverdueServiceCharge;
    
    /**
     * 已还逾期管理费用=已还逾期本金罚息+已还逾期利息罚息+已还逾期服务费罚息+其他罚息
     */
    private String paidOverdueManageCharge;
    
    /**
     * 是否逾期，逾期为true,否则为false
     */
    private Boolean isOverdue;
    
    /**
     * 主动还款权限按钮
     */
    private Boolean accessToActiveRepayment;

    /**
     * 展期天数
     */
    private int extensionDays;
    
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getLoanNo()
	{
		return loanNo;
	}

	public void setLoanNo(String loanNo)
	{
		this.loanNo = loanNo;
	}

	public String getRepaymentPeriod()
	{
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(String repaymentPeriod)
	{
		this.repaymentPeriod = repaymentPeriod;
	}

	public String getFinanceAmount()
	{
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount)
	{
		this.financeAmount = financeAmount;
	}

	public String getValueDate()
	{
		return valueDate;
	}

	public void setValueDate(String valueDate)
	{
		this.valueDate = valueDate;
	}

	public String getSettleInterestDate()
	{
		return settleInterestDate;
	}

	public void setSettleInterestDate(String settleInterestDate)
	{
		this.settleInterestDate = settleInterestDate;
	}

	public String getRepaymentDate()
	{
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate)
	{
		this.repaymentDate = repaymentDate;
	}

	public String getAccountPrincipal()
	{
		return accountPrincipal;
	}

	public void setAccountPrincipal(String accountPrincipal)
	{
		this.accountPrincipal = accountPrincipal;
	}

	public String getAccountInterest()
	{
		return accountInterest;
	}

	public void setAccountInterest(String accountInterest)
	{
		this.accountInterest = accountInterest;
	}

	public String getAccountServiceCharge()
	{
		return accountServiceCharge;
	}

	public void setAccountServiceCharge(String accountServiceCharge)
	{
		this.accountServiceCharge = accountServiceCharge;
	}

	public String getPaidEarlyRepaymentCharge()
	{
		return paidEarlyRepaymentCharge;
	}

	public void setPaidEarlyRepaymentCharge(String paidEarlyRepaymentCharge)
	{
		this.paidEarlyRepaymentCharge = paidEarlyRepaymentCharge;
	}

	public String getOverduePrincipal()
	{
		return overduePrincipal;
	}

	public void setOverduePrincipal(String overduePrincipal)
	{
		this.overduePrincipal = overduePrincipal;
	}

	public String getOverdueInterest()
	{
		return overdueInterest;
	}

	public void setOverdueInterest(String overdueInterest)
	{
		this.overdueInterest = overdueInterest;
	}

	public String getOverdueServiceCharge()
	{
		return overdueServiceCharge;
	}

	public void setOverdueServiceCharge(String overdueServiceCharge)
	{
		this.overdueServiceCharge = overdueServiceCharge;
	}

	public String getAccountExtensionCharge()
	{
		return accountExtensionCharge;
	}

	public void setAccountExtensionCharge(String accountExtensionCharge)
	{
		this.accountExtensionCharge = accountExtensionCharge;
	}

	public String getOverdueManageCharge()
	{
		return overdueManageCharge;
	}

	public void setOverdueManageCharge(String overdueManageCharge)
	{
		this.overdueManageCharge = overdueManageCharge;
	}

	public String getAccountTotalAmount()
	{
		return accountTotalAmount;
	}

	public void setAccountTotalAmount(String accountTotalAmount)
	{
		this.accountTotalAmount = accountTotalAmount;
	}

	public String getSettleStatus()
	{
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus)
	{
		this.settleStatus = settleStatus;
	}

	public String getFinanceBalance()
	{
		return financeBalance;
	}

	public void setFinanceBalance(String financeBalance)
	{
		this.financeBalance = financeBalance;
	}

	public String getPaidNormalPrincipal()
	{
		return paidNormalPrincipal;
	}

	public void setPaidNormalPrincipal(String paidNormalPrincipal)
	{
		this.paidNormalPrincipal = paidNormalPrincipal;
	}

	public String getPaidNormalInterest()
	{
		return paidNormalInterest;
	}

	public void setPaidNormalInterest(String paidNormalInterest)
	{
		this.paidNormalInterest = paidNormalInterest;
	}

	public String getPaidNormalServiceCharge()
	{
		return paidNormalServiceCharge;
	}

	public void setPaidNormalServiceCharge(String paidNormalServiceCharge)
	{
		this.paidNormalServiceCharge = paidNormalServiceCharge;
	}

	public String getPaidExtensionCharge()
	{
		return paidExtensionCharge;
	}

	public void setPaidExtensionCharge(String paidExtensionCharge)
	{
		this.paidExtensionCharge = paidExtensionCharge;
	}

	public String getPaidOverduePrincipal()
	{
		return paidOverduePrincipal;
	}

	public void setPaidOverduePrincipal(String paidOverduePrincipal)
	{
		this.paidOverduePrincipal = paidOverduePrincipal;
	}

	public String getPaidOverdueInterest()
	{
		return paidOverdueInterest;
	}

	public void setPaidOverdueInterest(String paidOverdueInterest)
	{
		this.paidOverdueInterest = paidOverdueInterest;
	}

	public String getPaidOverdueServiceCharge()
	{
		return paidOverdueServiceCharge;
	}

	public void setPaidOverdueServiceCharge(String paidOverdueServiceCharge)
	{
		this.paidOverdueServiceCharge = paidOverdueServiceCharge;
	}

	public String getPaidOverdueManageCharge()
	{
		return paidOverdueManageCharge;
	}

	public void setPaidOverdueManageCharge(String paidOverdueManageCharge)
	{
		this.paidOverdueManageCharge = paidOverdueManageCharge;
	}

	public Boolean getIsOverdue()
	{
		return isOverdue;
	}

	public void setIsOverdue(Boolean isOverdue)
	{
		this.isOverdue = isOverdue;
	}

	public Boolean getAccessToActiveRepayment() {
		return accessToActiveRepayment;
	}

	public void setAccessToActiveRepayment(Boolean accessToActiveRepayment) {
		this.accessToActiveRepayment = accessToActiveRepayment;
	}

	public String getAccountInterestUntilNow() {
		return accountInterestUntilNow;
	}

	public void setAccountInterestUntilNow(String accountInterestUntilNow) {
		this.accountInterestUntilNow = accountInterestUntilNow;
	}

	public int getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(int extensionDays) {
		this.extensionDays = extensionDays;
	}
}
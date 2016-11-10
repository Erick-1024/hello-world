package com.cana.vbam.common.wechat.repayment;

import java.io.Serializable;

/**
 * 微信平台——融资信息返回
 * @author yihong.tang
 */
public class LoanInfoResponse implements Serializable {
	
	private static final long serialVersionUID = 4051339122236872845L;
	
	private String id;//主键

    private String loanNo;//放款编号

    private String businessContractNo;//业务合同号
    
    private String factorCompany;//资金方公司名称
    
    private String financeCompany;//融资客户公司名称
 
    private String outCustomerName;//融资客户外部账户名称
    
    private String coreCompanyName;//核心企业

    private String businessProduct;//业务产品
    
    private String financeAmount;//融资金额
    
    private String financeBalance;//融资余额 = 融资金额 - 所有计划（已还本金+已还逾期本金）
    
    private String loanDate;//放款日

    private String dueDate;//到期日

    private String interestRateUnit;//费率单位
    
    private String interestRate;//费率

    private String repaymentMethod;//还款方式
    
    private String accountNo;//还款账号
    
    private String settleStatus;//结清状态（已结清、未结清）
     
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getFactorCompany() {
		return factorCompany;
	}

	public void setFactorCompany(String factorCompany) {
		this.factorCompany = factorCompany;
	}

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	
	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}
}
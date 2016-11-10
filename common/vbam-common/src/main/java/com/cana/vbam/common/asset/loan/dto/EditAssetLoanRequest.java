package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 创建或者修改放款请求
 * 
 * @author XuMeng
 *
 */
public class EditAssetLoanRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String contractNo; // 业务合同号
	private String loanInfoId; // 放款编号
	private String counterpartyId; // 交易对手ID
	private String financeAmount; // 融资金额，单位元
	private String currency; // 币种，目前仅支持RMB
	private String repaymentType; // 还款方式
	private String loanPeriodUnit; // 放款期限单位
	private int loanPeriod; // 放款期限
	private int dayCountConvention; // 计息基准天数，360或者365
	private String interestRateUnit; // 利率单位
	private String interestRate; // 利率，百分之
	private String loanDate; // 放款日期，yyyy-MM-dd
	private String dueDate; // 到期日，yyyy-MM-dd，首次使用 LoanDueDateCalcUtil 进行生成到期日，客户可以修改
	private String penaltyRate; // 逾期费率，日息
	private String repaymentAccountNo; // 从业务信息管理中取到的还款账号
	private List<String> invoiceInfoIds; // 应收账款ID列表
	private List<Expense> expenses; // 费用列表，允许为空
	private List<LoanPlanDTO> plans; // 还款计划列表

	/**
	 * 费用
	 */
	public static class Expense implements Serializable {
		private static final long serialVersionUID = 1L;
		private String subject; // 科目
		private String amount; // 金额，单位元

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(String loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public int getDayCountConvention() {
		return dayCountConvention;
	}

	public void setDayCountConvention(int dayCountConvention) {
		this.dayCountConvention = dayCountConvention;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(String penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public String getRepaymentAccountNo() {
		return repaymentAccountNo;
	}

	public void setRepaymentAccountNo(String repaymentAccountNo) {
		this.repaymentAccountNo = repaymentAccountNo;
	}

	public List<String> getInvoiceInfoIds() {
		return invoiceInfoIds;
	}

	public void setInvoiceInfoIds(List<String> invoiceInfoIds) {
		this.invoiceInfoIds = invoiceInfoIds;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<LoanPlanDTO> getPlans() {
		return plans;
	}

	public void setPlans(List<LoanPlanDTO> plans) {
		this.plans = plans;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}

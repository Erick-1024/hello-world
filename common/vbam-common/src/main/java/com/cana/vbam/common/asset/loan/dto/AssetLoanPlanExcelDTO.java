package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class AssetLoanPlanExcelDTO extends LoanPlanDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4194172341883963593L;
	
	private String loanInfoId;
	private String financeName;
	private String financeAmount;
	private String loanDate; // 放款日期，yyyy-MM-dd
	private String dueDate; // 到期日，yyyy-MM-dd，首次使用 LoanDueDateCalcUtil 进行生成到期日，客户可以修改
	private String period; //期数
	private String checkFailedMessage;
	
	private String contractNo;
	private String businessProduct;
	public String getLoanInfoId() {
		return loanInfoId;
	}
	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
	public String getFinanceName() {
		return financeName;
	}
	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}
	public String getFinanceAmount() {
		return financeAmount;
	}
	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getCheckFailedMessage() {
		return checkFailedMessage;
	}
	public void setCheckFailedMessage(String checkFailedMessage) {
		this.checkFailedMessage = checkFailedMessage;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getBusinessProduct() {
		return businessProduct;
	}
	public void setBusinessProduct(String businessProduct) {
		this.businessProduct = businessProduct;
	}
}

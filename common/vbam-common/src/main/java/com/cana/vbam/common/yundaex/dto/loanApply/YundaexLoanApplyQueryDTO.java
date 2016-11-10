/**
 * 
 */
package com.cana.vbam.common.yundaex.dto.loanApply;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cana.vbam.common.repayment.enums.RepaymentType;

/**
 * 韵达项目-韵达客户申请查询DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexLoanApplyQueryDTO implements Serializable {
	private static final long serialVersionUID = -6340266449039724922L;

	/**
	 * 申请放款金额
	 */
	private String loanAmt;

	/**
	 * 放款金额
	 */
	private Long paymentFee;

	/**
	 * 利率
	 */
	private BigDecimal interestRate;

	/**
	 * 利率单位
	 */
	private String interestRateUnit;

	/**
	 * 还款方式
	 */
	private String repaymentMethod;

	private RepaymentType repaymentType;

	/**
	 * 放款日
	 */
	private String loanDate;

	/**
	 * 到期日
	 * 
	 */
	private String dueDate;

	/**
	 * 放款期限单位
	 */
	private String loanPeriodUnit;

	/**
	 * 放款期限
	 */
	private String LoanPeriod;

	/**
	 * 产品ID
	 */
	private String proId;

	/**
	 * 保理合同号
	 */
	private String protocolNo;

	/**
	 * 单笔融资合同ID
	 */
	private String contractId;

	/**
	 * 签署单笔融资合同word
	 */
	private byte[] wordContent;

	public byte[] getWordContent() {
		return wordContent;
	}

	public void setWordContent(byte[] wordContent) {
		this.wordContent = wordContent;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public RepaymentType getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(RepaymentType repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Long getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(Long paymentFee) {
		this.paymentFee = paymentFee;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
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

	public String getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(String loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public String getLoanPeriod() {
		return LoanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		LoanPeriod = loanPeriod;
	}

}

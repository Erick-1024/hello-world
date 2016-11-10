package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentSingleCollectDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311934232392479932L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 放款信息Id
	 */
	private String loanInfoId;
	
	/**
	 * 还款类型（账扣、手动、线下）
	 */
	private String repaymentType;
	
	/**
	 * 还款凭证
	 */
	private String repaymentCertificate;
	
	/**
	 * 还款总金额
	 */
	private String repaymentTotalAmount;
	
	/**
	 * 还款日期
	 */
	private String repaymentDate;
	
	/**
	 * 费用类型
	 */
	private String chargeType;

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

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getRepaymentCertificate() {
		return repaymentCertificate;
	}

	public void setRepaymentCertificate(String repaymentCertificate) {
		this.repaymentCertificate = repaymentCertificate;
	}

	public String getRepaymentTotalAmount() {
		return repaymentTotalAmount;
	}

	public void setRepaymentTotalAmount(String repaymentTotalAmount) {
		this.repaymentTotalAmount = repaymentTotalAmount;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepaymentSingleCollectDTO other = (RepaymentSingleCollectDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

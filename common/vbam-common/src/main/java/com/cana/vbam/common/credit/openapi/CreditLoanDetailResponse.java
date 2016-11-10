package com.cana.vbam.common.credit.openapi;

import java.util.List;

import com.cana.vbam.common.repayment.dto.RepaymentAmount;

public class CreditLoanDetailResponse extends TravelzenBaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RepaymentAmount repaymentAmount;
	
	private Integer extensionDays;//展期天数
	
	private List<RepaymentRecordVo> repaymentRecords;

	public RepaymentAmount getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(RepaymentAmount repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public List<RepaymentRecordVo> getRepaymentRecords() {
		return repaymentRecords;
	}

	public void setRepaymentRecords(List<RepaymentRecordVo> repaymentRecords) {
		this.repaymentRecords = repaymentRecords;
	}

	public Integer getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(Integer extensionDays) {
		this.extensionDays = extensionDays;
	}

	

}

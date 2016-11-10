package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

/**
 * 还款记录
 *
 */
public class RepaymentRecordVo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//还款方式 
	private String repaymentMethod;
	
	//金额明细
	private PaidAmountVo paidAmount;
	
	//还款日,"yyyy-MM-dd"
	private String repaymentDate;
	
	//操作时间 "yyyy-MM-dd HH:mm:ss"
	private String opTime;

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public PaidAmountVo getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(PaidAmountVo paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	
	
}

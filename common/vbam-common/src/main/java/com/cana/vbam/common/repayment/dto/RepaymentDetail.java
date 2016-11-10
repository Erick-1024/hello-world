package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.repayment.enums.RepaymentMethod;

/**
 * 还款明细
 * @author renshui
 *
 */
public class RepaymentDetail implements Serializable{
	private static final long serialVersionUID = 1311353818423156701L;
	// 还款日，格式：yyyy-MM-dd
	private String repaymentDate;
	// 还款方式
	private RepaymentMethod repaymentMethod;
	// 金额明细
	private RepaymentAmount repaymentAmount;
	// 操作时间
	private Date opTime;
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	public RepaymentMethod getRepaymentMethod() {
		return repaymentMethod;
	}
	public void setRepaymentMethod(RepaymentMethod repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}
	public RepaymentAmount getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(RepaymentAmount repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
}

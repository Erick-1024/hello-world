package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 查询放款信息详情响应
 * @author renshui
 *
 */
public class QueryLoanInfoDetailResponseDTO implements Serializable{
	
	private static final long serialVersionUID = -2807300023746923682L;

	private RepaymentAmount repaymentAmount;
	
	private Integer extensionDays; //当前放款的第一期还款计划的展期天数
	
	private List<RepaymentDetail> repaymentDetailList;

	public RepaymentAmount getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(RepaymentAmount repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public List<RepaymentDetail> getRepaymentDetailList() {
		return repaymentDetailList;
	}

	public void setRepaymentDetailList(List<RepaymentDetail> repaymentDetailList) {
		this.repaymentDetailList = repaymentDetailList;
	}

	public Integer getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(Integer extensionDays) {
		this.extensionDays = extensionDays;
	}

}

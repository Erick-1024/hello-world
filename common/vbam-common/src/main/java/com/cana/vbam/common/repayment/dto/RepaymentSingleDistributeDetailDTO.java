package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentSingleDistributeDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 835141092752477400L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 关联者Id（还款计划、费用）
	 */
	private String relatedId;

	/**
	 * 关联者类型
	 */
	private String relatedType;

	/**
	 * 还正常本金
	 */
	private String payNormalPrincipal = "0.00";

	/**
	 * 还正常利息
	 */
	private String payNormalInterest = "0.00";

	/**
	 * 还正常服务费
	 */
	private String payNormalServiceCharge = "0.00";

	/**
	 * 提前还款手续费
	 */
	private String earlyRepaymentCharge = "0.00";

	/**
	 * 提前还款手续费率
	 */
	private String earlyRepaymentChargeRatio;

	/**
	 * 还展期费用
	 */
	private String payExtensionCharge = "0.00";

	/**
	 * 还逾期本金
	 */
	private String payOverduePrincipal = "0.00";
	
	/**
	 * 还逾期利息
	 */
	private String payOverdueInterest = "0.00";
	
	/**
	 * 还逾期服务费
	 */
	private String payOverdueServiceCharge = "0.00";
	
	/**
	 * 还逾期本金罚息
	 */
	private String payOverduePrincipalPenalty = "0.00";
	
	/**
	 * 还逾期利息罚息
	 */
	private String payOverdueInterestPenalty = "0.00";
	
	/**
	 * 还逾期服务费罚息
	 */
	private String payOverdueServiceChargePenalty = "0.00";
	
	/**
	 * 还其他罚息
	 */
	private String payOtherPenalty = "0.00";
	
	/**
	 * 还正常固定费用
	 */
	private String payNormalExpense = "0.00";
	
	/**
	 * 还款汇总表Id
	 */
	private String repaymentSingleCollectId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	public String getRelatedType() {
		return relatedType;
	}

	public void setRelatedType(String relatedType) {
		this.relatedType = relatedType;
	}

	public String getPayNormalPrincipal() {
		return payNormalPrincipal;
	}

	public void setPayNormalPrincipal(String payNormalPrincipal) {
		this.payNormalPrincipal = payNormalPrincipal;
	}

	public String getPayNormalInterest() {
		return payNormalInterest;
	}

	public void setPayNormalInterest(String payNormalInterest) {
		this.payNormalInterest = payNormalInterest;
	}

	public String getPayNormalServiceCharge() {
		return payNormalServiceCharge;
	}

	public void setPayNormalServiceCharge(String payNormalServiceCharge) {
		this.payNormalServiceCharge = payNormalServiceCharge;
	}

	public String getEarlyRepaymentCharge() {
		return earlyRepaymentCharge;
	}

	public void setEarlyRepaymentCharge(String earlyRepaymentCharge) {
		this.earlyRepaymentCharge = earlyRepaymentCharge;
	}

	public String getEarlyRepaymentChargeRatio() {
		return earlyRepaymentChargeRatio;
	}

	public void setEarlyRepaymentChargeRatio(String earlyRepaymentChargeRatio) {
		this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
	}

	public String getPayExtensionCharge() {
		return payExtensionCharge;
	}

	public void setPayExtensionCharge(String payExtensionCharge) {
		this.payExtensionCharge = payExtensionCharge;
	}

	public String getPayOverduePrincipal() {
		return payOverduePrincipal;
	}

	public void setPayOverduePrincipal(String payOverduePrincipal) {
		this.payOverduePrincipal = payOverduePrincipal;
	}

	public String getPayOverdueInterest() {
		return payOverdueInterest;
	}

	public void setPayOverdueInterest(String payOverdueInterest) {
		this.payOverdueInterest = payOverdueInterest;
	}

	public String getPayOverdueServiceCharge() {
		return payOverdueServiceCharge;
	}

	public void setPayOverdueServiceCharge(String payOverdueServiceCharge) {
		this.payOverdueServiceCharge = payOverdueServiceCharge;
	}

	public String getPayOverduePrincipalPenalty() {
		return payOverduePrincipalPenalty;
	}

	public void setPayOverduePrincipalPenalty(String payOverduePrincipalPenalty) {
		this.payOverduePrincipalPenalty = payOverduePrincipalPenalty;
	}

	public String getPayOverdueInterestPenalty() {
		return payOverdueInterestPenalty;
	}

	public void setPayOverdueInterestPenalty(String payOverdueInterestPenalty) {
		this.payOverdueInterestPenalty = payOverdueInterestPenalty;
	}

	public String getPayOverdueServiceChargePenalty() {
		return payOverdueServiceChargePenalty;
	}

	public void setPayOverdueServiceChargePenalty(
			String payOverdueServiceChargePenalty) {
		this.payOverdueServiceChargePenalty = payOverdueServiceChargePenalty;
	}

	public String getPayOtherPenalty() {
		return payOtherPenalty;
	}

	public void setPayOtherPenalty(String payOtherPenalty) {
		this.payOtherPenalty = payOtherPenalty;
	}

	public String getPayNormalExpense() {
		return payNormalExpense;
	}

	public void setPayNormalExpense(String payNormalExpense) {
		this.payNormalExpense = payNormalExpense;
	}

	public String getRepaymentSingleCollectId() {
		return repaymentSingleCollectId;
	}

	public void setRepaymentSingleCollectId(String repaymentSingleCollectId) {
		this.repaymentSingleCollectId = repaymentSingleCollectId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((relatedId == null) ? 0 : relatedId.hashCode());
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
		RepaymentSingleDistributeDetailDTO other = (RepaymentSingleDistributeDetailDTO) obj;
		if (relatedId == null) {
			if (other.relatedId != null)
				return false;
		} else if (!relatedId.equals(other.relatedId))
			return false;
		return true;
	}

}

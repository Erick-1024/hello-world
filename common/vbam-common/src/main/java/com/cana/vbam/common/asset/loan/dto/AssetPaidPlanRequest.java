package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 资产管理系统 还款冲销请求对象
 * 
 * @author XuMeng
 *
 */
public class AssetPaidPlanRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loanInfoId; // 放款ID
	private List<PaidSinglePlan> paidPlans; // 还款冲销数据

	/**
	 * 单笔冲销数据
	 */
	public static class PaidSinglePlan implements Serializable {
		private static final long serialVersionUID = 1L;

		private String loanPlanId; // 冲销的还款计划ID
		private String paidDate; // 入账日期，yyyy-MM-dd
		private String paidPrincipal; // 本次还款本金，单位元
		private String paidInterest; // 本次还款利息，单位元
		private String paidOverdue; // 本次还款逾期费，单位元
		private String settleStatus; // 本次冲销后的结清状态，不可为空

		public String getLoanPlanId() {
			return loanPlanId;
		}

		public void setLoanPlanId(String loanPlanId) {
			this.loanPlanId = loanPlanId;
		}

		public String getPaidDate() {
			return paidDate;
		}

		public void setPaidDate(String paidDate) {
			this.paidDate = paidDate;
		}

		public String getPaidPrincipal() {
			return paidPrincipal;
		}

		public void setPaidPrincipal(String paidPrincipal) {
			this.paidPrincipal = paidPrincipal;
		}

		public String getPaidInterest() {
			return paidInterest;
		}

		public void setPaidInterest(String paidInterest) {
			this.paidInterest = paidInterest;
		}

		public String getPaidOverdue() {
			return paidOverdue;
		}

		public void setPaidOverdue(String paidOverdue) {
			this.paidOverdue = paidOverdue;
		}

		public String getSettleStatus() {
			return settleStatus;
		}

		public void setSettleStatus(String settleStatus) {
			this.settleStatus = settleStatus;
		}
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public List<PaidSinglePlan> getPaidPlans() {
		return paidPlans;
	}

	public void setPaidPlans(List<PaidSinglePlan> paidPlans) {
		this.paidPlans = paidPlans;
	}

}

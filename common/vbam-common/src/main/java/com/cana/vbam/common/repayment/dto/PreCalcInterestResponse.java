package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 预算利息请求
 * 
 * @author renshui
 *
 */
public class PreCalcInterestResponse implements Serializable {

	private static final long serialVersionUID = -7891134051573305414L;

	private List<PreCalcRepaymentPlan> repaymentPlans;

	public List<PreCalcRepaymentPlan> getRepaymentPlans() {
		return repaymentPlans;
	}

	public void setRepaymentPlans(List<PreCalcRepaymentPlan> repaymentPlans) {
		this.repaymentPlans = repaymentPlans;
	}

}

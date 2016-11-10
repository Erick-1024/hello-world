package com.cana.vbam.common.vj.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.repayment.dto.PreCalcRepaymentPlan;

public class QueryInterestResponseData implements Serializable {

	private static final long serialVersionUID = -7803162269015035804L;

	private List<PreCalcRepaymentPlan> repaymentPlans;

	public List<PreCalcRepaymentPlan> getRepaymentPlans() {
		return repaymentPlans;
	}

	public void setRepaymentPlans(List<PreCalcRepaymentPlan> repaymentPlans) {
		this.repaymentPlans = repaymentPlans;
	}
	
}

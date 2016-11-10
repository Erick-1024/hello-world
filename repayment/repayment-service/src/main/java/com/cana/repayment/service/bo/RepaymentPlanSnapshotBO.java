package com.cana.repayment.service.bo;

import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.po.RepaymentPlanSnapshot;

public class RepaymentPlanSnapshotBO extends RepaymentPlanSnapshot{
	

	private static final long serialVersionUID = -265365622025310489L;

	public RepaymentPlanSnapshotBO(RepaymentPlanSnapshot po){
		BeanUtils.copyProperties(po, this);
	}
}

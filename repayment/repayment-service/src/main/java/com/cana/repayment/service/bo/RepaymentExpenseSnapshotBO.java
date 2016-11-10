package com.cana.repayment.service.bo;

import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.po.RepaymentExpenseSnapshot;

public class RepaymentExpenseSnapshotBO extends RepaymentExpenseSnapshot{
	
	private static final long serialVersionUID = 6100713085912594476L;

	public RepaymentExpenseSnapshotBO(RepaymentExpenseSnapshot po){
		BeanUtils.copyProperties(po, this);
	}
}

package com.cana.repayment.service;

import java.util.List;

import com.cana.repayment.dao.po.RepaymentExpenseSnapshot;
import com.cana.repayment.dao.po.RepaymentPlanSnapshot;

public interface IRepaymentPlanSnapshotService {
	
	public List<RepaymentPlanSnapshot> getRepaymentPlanSnapshotByLoanInfoSnapshotId(String loanInfoSnapshotId);
	
	public List<RepaymentExpenseSnapshot> getRepaymentExpenseSnapshotByLoanInfoSnapshotId(String loanInfoSnapshotId);
}

package com.cana.repayment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.gen.RepaymentExpenseSnapshotMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanSnapshotMapper;
import com.cana.repayment.dao.po.RepaymentExpenseSnapshot;
import com.cana.repayment.dao.po.RepaymentExpenseSnapshotExample;
import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.repayment.dao.po.RepaymentPlanSnapshotExample;
import com.cana.repayment.service.IRepaymentPlanSnapshotService;

@Service
public class RepaymentPlanSnapshotServiceImpl implements IRepaymentPlanSnapshotService {
	
	@Resource
	private RepaymentPlanSnapshotMapper repaymentPlanSnapshotMapper;
	
	@Resource
	private RepaymentExpenseSnapshotMapper repaymentExpenseSnapshotMapper;

	@Override
	public List<RepaymentPlanSnapshot> getRepaymentPlanSnapshotByLoanInfoSnapshotId(String loanInfoSnapshotId) {
		RepaymentPlanSnapshotExample example = new RepaymentPlanSnapshotExample();
		example.setOrderByClause("repayment_period");
		example.createCriteria().andLoanInfoSnapshotIdEqualTo(loanInfoSnapshotId);
		return repaymentPlanSnapshotMapper.selectByExample(example);
	}

	@Override
	public List<RepaymentExpenseSnapshot> getRepaymentExpenseSnapshotByLoanInfoSnapshotId(String loanInfoSnapshotId) {
		RepaymentExpenseSnapshotExample example = new RepaymentExpenseSnapshotExample();
		example.setOrderByClause("repayment_expense_id");
		example.createCriteria().andLoanInfoSnapshotIdEqualTo(loanInfoSnapshotId);
		return repaymentExpenseSnapshotMapper.selectByExample(example);
	}

}

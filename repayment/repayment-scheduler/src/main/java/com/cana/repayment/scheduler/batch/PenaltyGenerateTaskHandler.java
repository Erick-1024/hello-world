package com.cana.repayment.scheduler.batch;

import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;

public class PenaltyGenerateTaskHandler extends AbstractBatchTaskHandler{

	public PenaltyGenerateTaskHandler(RepaymentDailyBatchTask task, RepaymentDailyBatchTaskItem taskItem) {
		super(task, taskItem);
	}

	@Override
	public void doExecute() throws Exception {
		
		transactionService.generatePenalty(taskBO, taskItemBO);
		
	}

}

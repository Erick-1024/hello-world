package com.cana.repayment.scheduler.batch;

import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;

public class OverdueGenerateTaskHandler extends AbstractBatchTaskHandler{

	public OverdueGenerateTaskHandler(RepaymentDailyBatchTask task, RepaymentDailyBatchTaskItem taskItem) {
		super(task, taskItem);
	}

	@Override
	public void doExecute() throws Exception {
		transactionService.generateOverdue(taskBO, taskItemBO);
	}

}

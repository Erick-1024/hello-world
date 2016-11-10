package com.cana.repayment.scheduler.batch;

import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;

/**
 * 展期费用生成
 * @author renshui
 *
 */
public class ExtensionChargeGenerateTaskHandler extends AbstractBatchTaskHandler{

	public ExtensionChargeGenerateTaskHandler(RepaymentDailyBatchTask task, RepaymentDailyBatchTaskItem taskItem) {
		super(task, taskItem);
	}

	@Override
	public void doExecute() throws Exception {
		transactionService.generateExtensionCharge(taskBO, taskItemBO);
	}

}

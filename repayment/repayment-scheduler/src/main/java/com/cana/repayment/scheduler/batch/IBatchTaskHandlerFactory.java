package com.cana.repayment.scheduler.batch;

import com.cana.repayment.dao.po.RepaymentDailyBatchTask;

public interface IBatchTaskHandlerFactory {
	
	/**
	 * 返回处理返款信息下一任务的处理器
	 */
	public IBatchTaskHandler getHandler(RepaymentDailyBatchTask task);

}

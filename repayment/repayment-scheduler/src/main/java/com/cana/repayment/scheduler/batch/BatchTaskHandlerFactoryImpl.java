package com.cana.repayment.scheduler.batch;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskItemMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.vbam.common.repayment.enums.BatchTaskType;

@Component
public class BatchTaskHandlerFactoryImpl implements IBatchTaskHandlerFactory{
	
	@Resource
	private RepaymentDailyBatchTaskItemMapper taskItemMapper;

	@Override
	public IBatchTaskHandler getHandler(RepaymentDailyBatchTask task) {
		RepaymentDailyBatchTaskItem taskItem = taskItemMapper.selectByPrimaryKey(task.getNextTaskItemId());
		BatchTaskType taskType = BatchTaskType.valueOf(taskItem.getTaskType());
		switch(taskType){
		case deduct:
			return new DeductTaskHandler(task, taskItem);
		case extension_charge_generate:
			return new ExtensionChargeGenerateTaskHandler(task, taskItem);
		case overdue_generate:
			return new OverdueGenerateTaskHandler(task, taskItem);
		case penalty_generate:
			return new PenaltyGenerateTaskHandler(task, taskItem);
		default:
			return null;
		}
	}

}

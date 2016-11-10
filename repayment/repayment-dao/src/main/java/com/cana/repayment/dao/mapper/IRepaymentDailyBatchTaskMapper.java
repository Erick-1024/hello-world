package com.cana.repayment.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.vbam.common.repayment.enums.BatchTaskType;

public interface IRepaymentDailyBatchTaskMapper {
	
	/**
	 * 获取所有未生成日批任务的放款信息id
	 * @param date
	 * @return
	 */
	@Select("select id from repayment_loan_info where (active_repayment_status is null or active_repayment_status = 'complete_active_repayment') and id not in "
			+ "(select loan_info_id from repayment_daily_batch_task where date=#{date}) and (settle_status is null or settle_status != 'SETTLED')")
	public List<String> getAllUnGenerateDailyBatchTaskLoanInfoIds(@Param("date") String date);
	
	/**
	 * 获取所有当前待处理的任务
	 * @param date
	 * @return
	 */
	@Select("select * from repayment_daily_batch_task where date=#{date} and (next_task_item_id is not null and next_task_item_id!='') "
			+ "and (next_task_item_execute_time is null or next_task_item_execute_time='' or next_task_item_execute_time<=#{time})"
			+ "and (fail_task_item_id is null or fail_task_item_id='' or can_retry=1)")
	public List<RepaymentDailyBatchTask> getAllPendingTasks(@Param("date") String date, @Param("time") String time);

	/**
	 * 获取融资客户可运行的最早放款的账扣任务ID
	 * 
	 * 账扣任务类型枚举见 {@link BatchTaskType}
	 * 
	 * @return itemId
	 */
	@Select("select item.id "
			+ "from repayment_daily_batch_task_item item left join repayment_daily_batch_task task on item.task_id = task.id "
			+ "where task.date=#{date} " // 当天
			+ "and task.customer_id = #{customerId} " // 融资客户
			+ "and (task.next_task_item_id is not null and task.next_task_item_id != '') " // 下一个可执行任务
			+ "and (task.next_task_item_execute_time is null or task.next_task_item_execute_time='' or task.next_task_item_execute_time<=#{time}) "
			+ "and (task.fail_task_item_id is null or task.fail_task_item_id='' or task.can_retry=1) "
			+ "and item.sequence = task.sequence " // 运行的任务
			+ "and item.task_type = 'deduct' " // 账扣任务
			+ "order by task.loan_info_id asc " // 按照放款顺序
			+ "limit 0,1"
			)
	public String getEarliestExecutableDeductItemId(@Param("customerId") String customerId, @Param("date") String date, @Param("time") String time);
	
	/**
	 * 获取当日默认账扣未完成的数量
	 * @param date
	 * @return
	 */
	@Select("select count(1) "
		    + "from `repayment_daily_batch_task` task join `repayment_daily_batch_task_item` item on task.id=item.task_id "
		    + "where date=#{date} and task.next_task_item_id is not null and task.next_task_item_id=item.id and item.task_type='deduct' and item.extra_data like '%\"defaultDeduct\":\"true\"%'"
		    )
	public int countUnFinishedDefaultDeduct(@Param("date") String date);


}

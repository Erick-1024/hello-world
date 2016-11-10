package com.cana.repayment.service.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.service.IRepositoryService;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentDailyBatchTaskBO extends RepaymentDailyBatchTask{
	
	private static final long serialVersionUID = -8385576681595895850L;
	
	private RepaymentDailyBatchTaskMapper taskMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentDailyBatchTaskMapper.class);
	
	private IRepositoryService repositoryService = SpringApplicationContext.getApplicationContext().getBean(IRepositoryService.class);
	
	private List<RepaymentDailyBatchTaskItemBO> taskItemBOs;  

	
	public RepaymentDailyBatchTaskBO(String id){
		this(SpringApplicationContext.getApplicationContext().getBean(RepaymentDailyBatchTaskMapper.class).selectByPrimaryKey(id));
	}
	
	public RepaymentDailyBatchTaskBO(RepaymentDailyBatchTask po){
		BeanUtils.copyProperties(po, this);
	}
	
	public List<RepaymentDailyBatchTaskItemBO> lazyLoadTaskItemBOs(){
		if(taskItemBOs != null)
			return taskItemBOs;
		taskItemBOs = new ArrayList<>();
		for(RepaymentDailyBatchTaskItem taskItem : repositoryService.getTaskItemsByTaskId(getId()))
			taskItemBOs.add(new RepaymentDailyBatchTaskItemBO(taskItem));
		return taskItemBOs;
	}
	
	/**
	 * 当前正在执行的taskItem
	 * @return
	 */
	public RepaymentDailyBatchTaskItemBO currentTaskItemBO(){
		for(RepaymentDailyBatchTaskItemBO taskItemBO : lazyLoadTaskItemBOs())
			if(getNextTaskItemId().equals(taskItemBO.getId()))
				return taskItemBO;
		return null;
	}
	
	/**
	 * 设置为执行下一个任务
	 */
	public void advanceToNextTask() {
		advanceTo(currentTaskItemBO().nextTaskItemBO());
		
	}
	
	/**
	 * 设置为下一次执行参数中指定的任务
	 * @param taskItemBO
	 */
	public void advanceTo(RepaymentDailyBatchTaskItemBO taskItemBO){
		if(taskItemBO == null){ //全部任务已经执行完
			setSequence(null);
			setNextTaskItemId("");
			setNextTaskItemExecuteTime("");	
		}else{
			setSequence(taskItemBO.getSequence());
			setNextTaskItemId(taskItemBO.getId());
			setNextTaskItemExecuteTime(taskItemBO.getExecuteTime());
		}
        setCanRetry(false);
		setFailMessage("");
		setFailTaskItemId("");
		setUpateTime(new Date());
		taskMapper.updateByPrimaryKey(this);
	}

	
}

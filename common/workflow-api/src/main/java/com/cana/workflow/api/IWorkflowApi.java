package com.cana.workflow.api;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.workflow.dto.WorkflowTaskDTO;

/**
 *
 * @since Nov 19, 20152:37:15 PM
 * @author dev1
 *
 */
public interface IWorkflowApi {

	/**
	 * 启动流程
	 * @param processDefinitionKey
	 * @param variables 流程参数
	 * @return
	 */
	public String startProcessInstance(String processDefinitionKey, Map<String, Object> variables);
	
	/**
	 * 根据条件查询个人当前任务
	 * @param userId
	 * @param conditions
	 * @return
	 */
	public List<WorkflowTaskDTO> queryPersonTask(String userId, Map<String, Object> conditions);
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param taskVariables
	 */
	public void completeTask(String taskId, Map<String, Object> taskVariables);
	
	/**
	 * 查询某个流程的所有流程实例
	 * @param processDefinitionKey
	 * @return
	 */
	public Map<String, Map<String, Object>> queryProcessInstanceVariables(String processDefinitionKey);
}

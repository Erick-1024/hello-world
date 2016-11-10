package com.cana.workflow.server.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.collections.CollectionUtils;

import com.cana.vbam.common.workflow.dto.WorkflowTaskDTO;
import com.cana.workflow.api.IWorkflowApi;

/**
 *
 * @since Nov 19, 20152:37:21 PM
 * @author dev1
 *
 */
public class WorkflowApiImpl implements IWorkflowApi{

	@Resource
	RepositoryService repositoryService;
	
	@Resource
	RuntimeService runtimeService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	HistoryService historyService;
	
	public void init() {
		repositoryService.createDeployment()
	      .addClasspathResource("bpmn/account-workflow.bpmn")
	      .deploy();
	}
	
	@Override
	public String startProcessInstance(String processDefinitionKey, Map<String, Object> variables){
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
		return processInstance.getId();
	}
	
	@Override
	public List<WorkflowTaskDTO> queryPersonTask(String userId, Map<String, Object> conditions){
		List<Task> taskList = null;
		TaskQuery taskQuery = taskService.createTaskQuery();
		if(null != conditions){
			for(String key: conditions.keySet()){
				taskQuery.processVariableValueEquals(key, conditions.get(key));
			}
		}
		taskQuery.includeProcessVariables();
		taskQuery.taskAssignee(userId);
		taskList = taskQuery.list();
		
		return convert2TaskDTO(taskList);
		
	}
	
	@Override
	public void completeTask(String taskId, Map<String, Object> taskVariables){
		taskService.complete(taskId, taskVariables);
	}
	
	@Override
	public Map<String, Map<String, Object>> queryProcessInstanceVariables(String processDefinitionKey){
		HistoricProcessInstanceQuery processQuery = historyService.createHistoricProcessInstanceQuery();
		processQuery.includeProcessVariables();
		processQuery.processDefinitionKey(processDefinitionKey);
		List<HistoricProcessInstance> processList = processQuery.list();
		Map<String, Map<String, Object>> paramsMap = new HashMap<>();
		for(HistoricProcessInstance process : processList){
			
			paramsMap.put(process.getId(), process.getProcessVariables());
		}
		
		return paramsMap;
	}
	
	private List<WorkflowTaskDTO> convert2TaskDTO(List<Task> taskList){
		if(CollectionUtils.isEmpty(taskList)){
			return null;
		}
		List<WorkflowTaskDTO> taskDTOList = new ArrayList<>();
		for(Task task : taskList){
			WorkflowTaskDTO taskDTO = new WorkflowTaskDTO();
			taskDTO.setAssignee(task.getAssignee());
			taskDTO.setId(task.getId());
			taskDTO.setName(task.getName());
			taskDTO.setProcessVariables(task.getProcessVariables());
			taskDTOList.add(taskDTO);
		}
		return taskDTOList;
	}
}

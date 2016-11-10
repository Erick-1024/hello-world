import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.vbam.common.workflow.dto.WorkflowTaskDTO;
import com.cana.vbam.common.workflow.enums.ProcessDefinition;
import com.cana.workflow.api.IWorkflowApi;

@ContextConfiguration(locations = "classpath:META-INF/spring/workflow-server-context.xml")
public class ProcessTest extends AbstractJUnit4SpringContextTests{

	@Resource
	RuntimeService runtimeService;
	
	@Resource
	RepositoryService repositoryService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	HistoryService historyService;
	
	@Resource
	IWorkflowApi workflowApi;
	
	@Test
	public void test() {
		repositoryService.createDeployment()
	      .addClasspathResource("bpmn/account-workflow.bpmn")
	      .deploy();
		System.out.println("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("applyUserId", "jiaxing");
		variables.put("auditor", "huzhiwen");
		variables.put("accountId", "123");
		variables.put("type", "转账");
		String procId = runtimeService.startProcessInstanceByKey("accountRequest", variables).getId();
		
		System.out.println("流程id："+procId);
		
		List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("huzhiwen").list();
		System.out.println(taskList.size());
		for(Task task : taskList){
			System.out.println("任务列表："+task.getName());
			taskService.claim(task.getId(), "huzhiwen");
		}
		
		List<Task> taskList2 = taskService.createTaskQuery().processVariableValueEquals("type", "转账").taskAssignee("huzhiwen").includeProcessVariables().list();
		for(Task task1 : taskList2){
			System.out.println("个人任务列表："+task1.getName());
			System.out.println(task1.getDescription());
			System.out.println(task1.getProcessVariables());
			System.out.println(task1.getTaskLocalVariables());
			System.out.println(task1.getParentTaskId());
			System.out.println(task1.getAssignee());
			Map<String, Object> taskVariables = new HashMap<String, Object>();
			taskVariables.put("accountApproved", "true");
			taskVariables.put("comment", "可以");
			taskService.complete(task1.getId(), taskVariables);
			
		}
		
		List<HistoricProcessInstance> historicProcessInstance = historyService.createHistoricProcessInstanceQuery().includeProcessVariables().list();
	    System.out.println("Process instance end time: " + historicProcessInstance.get(1).getEndTime());
	    System.out.println("Process instance end time: " + historicProcessInstance.get(1).getStartUserId());
	    System.out.println("Process instance end time: " + historicProcessInstance.get(1).getProcessVariables());
	    
	    List<HistoricVariableInstance> var = historyService.createHistoricVariableInstanceQuery().processInstanceId(procId).list();
	    System.out.println(var.toString());
	}

//	@Test
	public void test2() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("applyUserId", "jiaxing");
		variables.put("auditor", "huzhiwen");
		variables.put("accountId", "123");
		variables.put("type", "转账");
		String proId = workflowApi.startProcessInstance(ProcessDefinition.AccountRequest.desc(), variables);
		
		System.out.println("流程id："+proId);
		
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("type", "转账");
		List<WorkflowTaskDTO> taskDTOList = workflowApi.queryPersonTask("huzhiwen", conditions);
		for(WorkflowTaskDTO task : taskDTOList){
			System.out.println("个人任务列表："+task.getName());
			System.out.println(task.getProcessVariables());
			System.out.println(task.getAssignee());
			
			Map<String, Object> taskVariables = new HashMap<String, Object>();
			taskVariables.put("accountApproved", "true");
			taskVariables.put("comment", "可以");
			workflowApi.completeTask(task.getId(), taskVariables);
		}
		
		Map<String, Map<String, Object>> data = workflowApi.queryProcessInstanceVariables(ProcessDefinition.AccountRequest.desc());
		
		for(String id : data.keySet()){
			System.out.println("流程Id："+id+", "+data.get(id));
		}
	}
	
}

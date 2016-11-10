package com.cana.vbam.common.workflow.dto;

import java.io.Serializable;
import java.util.Map;

public class WorkflowTaskDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2376461624273592884L;
	private String id;
	private String name;
	private String assignee;
	private Map<String, Object> processVariables;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}
	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}
}

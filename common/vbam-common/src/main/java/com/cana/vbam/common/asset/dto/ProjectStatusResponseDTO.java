package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.cana.vbam.common.asset.enums.ProjectErrorFieldEnum;


/**
 * @author jiangzhou.Ren
 * @time 2016年5月16日下午3:02:17
 */
public class ProjectStatusResponseDTO implements Serializable {
	private static final long serialVersionUID = -1823607567722566718L;

	private String projectId;
	private Boolean status;
	private Map<ProjectErrorFieldEnum,String> errorInfos;
	
	/**
	 * 如果errorInfos 为空，则状态为真
	 */
	public ProjectStatusResponseDTO(Map<ProjectErrorFieldEnum, String> errorInfos) {
		this.status = CollectionUtils.isEmpty(errorInfos);
		this.errorInfos = errorInfos;
	}
	
	public ProjectStatusResponseDTO(String projectId, Map<ProjectErrorFieldEnum, String> errorInfos) {
		this(errorInfos);
		this.projectId = projectId;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Map<ProjectErrorFieldEnum, String> getErrorInfos() {
		return errorInfos;
	}
	public void setErrorInfos(Map<ProjectErrorFieldEnum, String> errorInfos) {
		this.errorInfos = errorInfos;
	}
	
}
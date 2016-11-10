package com.cana.asset.service;

import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;

public interface IAssetProjectPersistenceService {

	/**
	 * 当前用户userId
	 * 新增项目
	 */
	public ProjectStatusResponseDTO addProject(String userId, ProjectRequestDTO projectRequest);

	/**
	 * 当前用户userId
	 * 修改项目
	 */
	public ProjectStatusResponseDTO updateProject(String userId, ProjectRequestDTO projectRequest);
}

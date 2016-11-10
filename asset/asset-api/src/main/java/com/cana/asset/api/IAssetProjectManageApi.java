package com.cana.asset.api;

import java.util.List;

import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.asset.dto.ProjectListRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectListResponseDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;
import com.cana.vbam.common.dto.ListResult;

/**
 * 资产管理接口
 * 
 * @author jiangzhou.Ren
 * @time 2016年5月16日下午3:07:47
 */
public interface IAssetProjectManageApi {

	/**
	 * 当前用户userId
	 * 项目管理列表查询
	 */
	public ListResult<ProjectListResponseDTO> getProjectList(String userId, ProjectListRequestDTO request);
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

	/**
	 * 当前用户userId
	 * 项目管理详情查询
	 */
	public ProjectDTO getProjectDetail(String userId, String projectId);
	
	/**
	 * 根据项目id查询项目详情（没有权限）
	 */
	public ProjectDTO getProjectDetail(String projectId);
	
	/**
	 * 根据项目id查询项目。专给其他模块获取使用的。
	 * @param projectId
	 * @return
	 */
	public ProjectInfo getProjectInfo(String projectId);

	/**
	 * 获取所有使用节假日政策的项目ID列表
	 * @author XuMeng
	 */
	public List<String> getUseHolidayPolicyProjectIds();

}

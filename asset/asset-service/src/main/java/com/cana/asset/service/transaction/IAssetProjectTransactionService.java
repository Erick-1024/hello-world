package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.asset.dto.ProjectListRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectListResponseDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

/**
 * @ @author jiangzhou.Ren
 * @time 2016年5月17日下午2:43:02
 */
public interface IAssetProjectTransactionService {

	/**
	 * 当前用户findUserById
	 * 项目管理列表查询
	 */
	public ListResult<ProjectListResponseDTO> getProjectList(UserVo userDetail ,ProjectListRequestDTO request);

	/**
	 * 当前用户userId
	 * 项目管理查询
	 */
	public ProjectDTO getProjectDetail(UserVo userDetail ,String projectId);
	
	/**
	 * 根据项目id
	 * 项目管理查询
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

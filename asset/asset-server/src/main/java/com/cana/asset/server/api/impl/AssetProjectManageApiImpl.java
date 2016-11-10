package com.cana.asset.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.asset.service.IAssetProjectPersistenceService;
import com.cana.asset.service.transaction.IAssetProjectTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.ProjectDTO;
import com.cana.vbam.common.asset.dto.ProjectInfo;
import com.cana.vbam.common.asset.dto.ProjectListRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectListResponseDTO;
import com.cana.vbam.common.asset.dto.ProjectRequestDTO;
import com.cana.vbam.common.asset.dto.ProjectStatusResponseDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author jiangzhou.Ren
 * @time 2016年5月17日下午2:49:00
 */
public class AssetProjectManageApiImpl implements IAssetProjectManageApi {
	
	@Resource
	private IAssetProjectTransactionService assetProjectTransactionService;
	@Resource
	private IAssetProjectPersistenceService assetProjectPersistenceService;

	
	@Resource
	private IMemberQueryApi memberQueryApi;
	@Override
	public ListResult<ProjectListResponseDTO> getProjectList(String userId, ProjectListRequestDTO request) {
		//查询用户详情
		UserVo userDetail = getUserDetail(userId);
		return assetProjectTransactionService.getProjectList(userDetail ,request);
	}

	/**
	 * 查询userDetail
	 * @param userId
	 * @return
	 */
	public UserVo getUserDetail(String userId) {
		if(StringUtils.isBlank(userId)){
			WebException.instance("userId为空");
		}
		//根据userId查询用户信息
		UserVo userDetail = memberQueryApi.findUserById(userId);
		if (userDetail == null) {
			throw WebException.instance("用户不存在");
		}
		userDetail.getCustomer().getUserType();
		return userDetail;
	}

	@Override
	public ProjectStatusResponseDTO addProject(String userId, ProjectRequestDTO projectRequest) {
		return assetProjectPersistenceService.addProject(userId ,projectRequest);
	}

	@Override
	public ProjectStatusResponseDTO updateProject(String userId, ProjectRequestDTO projectRequest) {
		return assetProjectPersistenceService.updateProject(userId ,projectRequest);
	}


	@Override
	public ProjectDTO getProjectDetail(String userId, String projectId) {
		//查询用户详情
		UserVo userDetail = getUserDetail(userId);
		return assetProjectTransactionService.getProjectDetail(userDetail, projectId);
	}
	@Override
	public ProjectDTO getProjectDetail(String projectId){
		return assetProjectTransactionService.getProjectDetail(projectId);
	}

	@Override
	public ProjectInfo getProjectInfo(String projectId) {
		return assetProjectTransactionService.getProjectInfo(projectId);
	}

	@Override
	public List<String> getUseHolidayPolicyProjectIds() {
		return assetProjectTransactionService.getUseHolidayPolicyProjectIds();
	}
	

}

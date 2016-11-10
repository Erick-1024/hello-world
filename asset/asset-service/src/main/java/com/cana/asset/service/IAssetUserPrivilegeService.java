package com.cana.asset.service;

import com.cana.vbam.common.asset.dto.AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

public interface IAssetUserPrivilegeService {
	
	/**
	 * 增加权限
	 * @param userVO 当前登录用户
	 * @param request 增加权限请求
	 */
	public void add(UserVo currentLoginUserVO, AddUserPrivilegeRequest request);
	
	/**
	 * 增加权限时查询企业信息
	 * @param currentLoginUserVO
	 * @param request
	 * @return
	 */
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompany4Add(UserVo currentLoginUserVO, QueryCompany4AddUserPrivilegeRequest request);

	
	
}

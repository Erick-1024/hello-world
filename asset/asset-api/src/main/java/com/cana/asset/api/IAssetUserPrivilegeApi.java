package com.cana.asset.api;

import com.cana.vbam.common.asset.dto.AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

public interface IAssetUserPrivilegeApi {
	
	/**
	 * 增加权限
	 * @param userVO 当前登录用户
	 * @param request 增加权限请求
	 */
	public void add(UserVo currentLoginUserVO, AddUserPrivilegeRequest request);
	
	/**
	 * 查询权限列表
	 * @param currentLoginUserVO
	 * @param request
	 * @return
	 */
	public ListResult<QueryUserPrivilegeListItem> queryList(UserVo currentLoginUserVO, QueryUserPrivilegeListRequest request);
	
	/**
	 * 增加权限时查询企业信息
	 * @param currentLoginUserVO
	 * @param request
	 * @return
	 */
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompany4Add(UserVo currentLoginUserVO, QueryCompany4AddUserPrivilegeRequest request);
	
	/**
	 * 增加权限时查询客户信息
	 * @param currentLoginUserVO
	 * @param request
	 * @return
	 */
	public ListResult<QueryCustomer4AddUserPrivilegeListItem> queryCustomer4Add(UserVo currentLoginUserVO, QueryCustomer4AddUserPrivilegeRequest request);
	
	/**
	 * 删除权限
	 * @param currentLoginUserVO
	 * @param id
	 */
	public void delete(UserVo currentLoginUserVO, String id);


}

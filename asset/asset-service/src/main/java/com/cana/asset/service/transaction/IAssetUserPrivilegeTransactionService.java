package com.cana.asset.service.transaction;

import java.util.List;
import java.util.Set;

import com.cana.asset.dao.po.AssetUserPrivilege;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

public interface IAssetUserPrivilegeTransactionService {

	/**
	 * 获取登陆用户可以查看的客户列表
	 * @param masterId 登陆用户的主账号id
	 * @return
	 */
	public Set<String> allowedCustomerIdList(String masterId);
	
	/**
	 * 平台用户是否可以查看指定客户的信息
	 * @param masterId 登陆用户的主账号id
	 * @param customerId 客户id
	 * @return
	 */
	public boolean allow(String masterId, String customerId);
	
	/**
	 * 新增权限
	 * @param privileges
	 */
	public void add(List<AssetUserPrivilege> privileges);
	
	/**
	 * 查询权限列表
	 * @param currentLoginUserVO
	 * @param request
	 * @return
	 */
	public ListResult<QueryUserPrivilegeListItem> queryList(UserVo currentLoginUserVO, QueryUserPrivilegeListRequest request);

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

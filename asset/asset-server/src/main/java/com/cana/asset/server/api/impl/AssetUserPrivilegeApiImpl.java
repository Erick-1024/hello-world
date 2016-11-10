package com.cana.asset.server.api.impl;

import javax.annotation.Resource;

import com.cana.asset.api.IAssetUserPrivilegeApi;
import com.cana.asset.service.IAssetUserPrivilegeService;
import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.vbam.common.asset.dto.AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;

public class AssetUserPrivilegeApiImpl implements IAssetUserPrivilegeApi{
	
	@Resource
	private IAssetUserPrivilegeService privilegeService;
	@Resource
	private IAssetUserPrivilegeTransactionService privilegeTransactionService;

	@Override
	public void add(UserVo currentLoginUserVO, AddUserPrivilegeRequest request) {
		privilegeService.add(currentLoginUserVO, request);
	}

	@Override
	public ListResult<QueryUserPrivilegeListItem> queryList(UserVo currentLoginUserVO, QueryUserPrivilegeListRequest request) {
		return privilegeTransactionService.queryList(currentLoginUserVO, request);
	}

	@Override
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompany4Add(UserVo currentLoginUserVO,
			QueryCompany4AddUserPrivilegeRequest request) {
		return privilegeService.queryCompany4Add(currentLoginUserVO, request);
	}

	@Override
	public ListResult<QueryCustomer4AddUserPrivilegeListItem> queryCustomer4Add(UserVo currentLoginUserVO, QueryCustomer4AddUserPrivilegeRequest request) {
		return privilegeTransactionService.queryCustomer4Add(currentLoginUserVO, request);
	}

	@Override
	public void delete(UserVo currentLoginUserVO, String id) {
		privilegeTransactionService.delete(currentLoginUserVO, id);
	}

}

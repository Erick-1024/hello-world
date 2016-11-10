package com.cana.asset.server.api.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.cana.asset.api.IAssetCustomerApi;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.service.IAssetCustomerService;
import com.cana.asset.service.transaction.IAssetCustomerTransactionService;
import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.asset.service.transaction.util.DataPermissionValidator;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerListRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.dto.CustomerRequestDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午11:37:03
 */
public class AssetCustomerApiImpl implements IAssetCustomerApi{
	
	@Resource
	private IAssetCustomerTransactionService assetCustomerTransactionService;
	
	@Resource
	private IAssetCustomerService assetCustomerServiceImpl;
	
	@Resource
	private IMemberQueryApi memberQueryApi;
	
	

	@Resource
	private DataPermissionValidator dataPermissionValidator;
	
	//客户信息列表
	@Override
	public ListResult<CustomerListResponseDTO> getCustomerList(CustomerListRequestDTO request) {
		//查询用户详情
		UserVo userDetail = getUserDetail(request.getUserId());
		return assetCustomerTransactionService.getCustomerList(userDetail,request);
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

	//客户信息详情
	@Override
	public CustomerDTO getCustomerDetail(String userId,String customerId) {
		//查询用户详情
		UserVo userDetail = getUserDetail(userId);
		return assetCustomerTransactionService.getCustomerDetail(userDetail,customerId);
	}

	//新增客户
	@Override
	public void addCustomer(String userId,CustomerRequestDTO customerRequest) {
			UserVo userDetail = getUserDetail(userId);
		 assetCustomerTransactionService.addCustomer(userDetail,customerRequest);
	}

	//修改客户
	@Override
	public void updateCustomer(String userId,CustomerRequestDTO customerRequest) {
		UserVo userDetail = getUserDetail(userId);
		 assetCustomerTransactionService.updateCustomer(userDetail,customerRequest);
	}
	
	//校验客户名称是不是唯一
	@Override
	public boolean checkCustomernameExist(String customerName,String id) throws Exception {
		
		 return assetCustomerTransactionService.checkCustomernameExist(customerName,id);
	}

	@Override
	public String getCustomerNameById(String userId, String id) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("id不能为空");
		Customer customer = assetCustomerServiceImpl.getCustomerNameById(id);
		dataPermissionValidator.checkDataPermissions(memberQueryApi.findUserById(userId), customer.getId(), customer.getFactorId());
		return customer.getCustomerName();
	}
	
}

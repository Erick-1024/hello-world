package com.cana.asset.service.transaction.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.IAssetCustomerMapper;
import com.cana.asset.dao.mapper.gen.AssetUserPrivilegeMapper;
import com.cana.asset.dao.mapper.gen.CustomerMapper;
import com.cana.asset.dao.po.AssetUserPrivilege;
import com.cana.asset.dao.po.AssetUserPrivilegeExample;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.CustomerExample;
import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCustomer4AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryUserPrivilegeListRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.StringUtil;

@Service
public class AssetUserPrivilegeTransactionServiceImpl implements IAssetUserPrivilegeTransactionService{

	@Resource
	private IAssetCustomerMapper customizedCustomerMapper;
	
	@Resource
	private AssetUserPrivilegeMapper privilegeMapper;
	
	@Resource
	private CustomerMapper customerMapper;
	
	
	@Override
	public Set<String> allowedCustomerIdList(String masterId) {
		
		if(StringUtils.isBlank(masterId))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "masterId不能为空");
		
		Set<String> customerIds = new HashSet<>();
		
		AssetUserPrivilegeExample example = new AssetUserPrivilegeExample();
		example.createCriteria().andMasterIdEqualTo(masterId);
		
		List<AssetUserPrivilege> privileges = privilegeMapper.selectByExample(example);
		for(AssetUserPrivilege privilege : privileges){
			if(Constants.ASSET_PRIVILEGE_ALL.equals(privilege.getCustomerId())){
				customerIds.addAll(customizedCustomerMapper.getCustomerIdListByFactorId(privilege.getFactorId()));
			}else{
				customerIds.add(privilege.getCustomerId());
			}
		}
		
		return customerIds;
	}

	@Override
	public boolean allow(String masterId, String customerId) {
		
		if(StringUtils.isBlank(masterId))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "masterId不能为空");
		
		if(StringUtils.isBlank(customerId))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "customerId不能为空");
		
		return allowedCustomerIdList(masterId).contains(customerId);
		
	}

	@Override
	public void add(List<AssetUserPrivilege> privileges) {
		List<AssetUserPrivilege> oldPrivilegeList = lockByMasterId(privileges.get(0).getMasterId());
		for(AssetUserPrivilege oldPrivilege : oldPrivilegeList){ // 如果已经分配的权限中有将来+未来所有， 则不处理此次新增
			if(oldPrivilege.getCustomerId().equals(Constants.ASSET_PRIVILEGE_ALL))
				return;
		}
		for(AssetUserPrivilege privilege : privileges){
			boolean exist = false;
			for(AssetUserPrivilege oldPrivilege : oldPrivilegeList){ // 判断该权限之前是否已经存在了
				if(privilege.getCustomerId().equals(oldPrivilege.getCustomerId())){
					exist = true;
					break;
				}
			}
			if(!exist){
				privilegeMapper.insertSelective(privilege);
			}
		}
	}
	
	private List<AssetUserPrivilege> lockByMasterId(String masterId){
		AssetUserPrivilegeExample example = new AssetUserPrivilegeExample();
		example.createCriteria().andMasterIdEqualTo(masterId);
		return privilegeMapper.lockByExample(example);
	}

	@Override
	public ListResult<QueryUserPrivilegeListItem> queryList(UserVo currentLoginUserVO, QueryUserPrivilegeListRequest request) {
		check4queryList(currentLoginUserVO, request);
		
		AssetUserPrivilegeExample example = new AssetUserPrivilegeExample();
		AssetUserPrivilegeExample.Criteria criteria = example.createCriteria();
		criteria.andFactorIdEqualTo(currentLoginUserVO.getCustomerId())
				.andCompanyNameLike("%" + request.getCompanyName() + "%");
		if(request.getUserType() != null)
			criteria.andUserTypeEqualTo(request.getUserType().name());
		
		int total = privilegeMapper.countByExample(example);
		int limitStart = (request.getPage() - 1) * request.getPageSize(); 
		if(total <= limitStart)
			return ListResult.success("查询成功", new ArrayList<QueryUserPrivilegeListItem>(), total);
		
		example.setOrderByClause("create_time");
		example.setLimitStart(limitStart);
		example.setLimitEnd(request.getPageSize());
		
		List<QueryUserPrivilegeListItem> itemList = new ArrayList<>();
		for(AssetUserPrivilege raw : privilegeMapper.selectByExample(example)){
			QueryUserPrivilegeListItem item = new QueryUserPrivilegeListItem();
			BeanUtils.copyProperties(raw, item);
			item.setUserTypeDesc(UserType.valueOf(raw.getUserType()).desc());
			itemList.add(item);
		}
		
		return ListResult.success("查询成功", itemList, total);
	}

	private void check4queryList(UserVo currentLoginUserVO, QueryUserPrivilegeListRequest request) {
		StringUtil.trimObjectFields(request);
		if(currentLoginUserVO == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "未登录");
		if(currentLoginUserVO.getCustomer().getUserType() != UserType.FACTOR)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "只有保理商才能添加权限");
		if(request == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "请求参数为null");
		if(request.getPage() < 1)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "页码不正确");
		if(request.getPageSize() < 1)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "每页的条数不正确");
	}

	@Override
	public ListResult<QueryCustomer4AddUserPrivilegeListItem> queryCustomer4Add(UserVo currentLoginUserVO, QueryCustomer4AddUserPrivilegeRequest request) {
		
		check4queryCustomer4Add(currentLoginUserVO, request);
		
		CustomerExample example = new CustomerExample();
		example.createCriteria().andCustomerNameLike("%" + request.getCustomerName() + "%")
								.andFactorIdEqualTo(currentLoginUserVO.getCustomerId());
		example.setLimitStart((request.getPage() - 1) * request.getPageSize());
		example.setLimitEnd(request.getPageSize());
		example.setOrderByClause("create_time");
		
		int total = customerMapper.countByExample(example);
		int limitStart = (request.getPage() - 1) * request.getPageSize(); 
		if(total <= limitStart)
			return ListResult.success("查询成功", new ArrayList<QueryCustomer4AddUserPrivilegeListItem>(), total);
		
		List<QueryCustomer4AddUserPrivilegeListItem> itemList = new ArrayList<>();
		for(Customer customer : customerMapper.selectByExample(example)){
			QueryCustomer4AddUserPrivilegeListItem item = new QueryCustomer4AddUserPrivilegeListItem();
			item.setCustomerId(customer.getId());
			item.setCustomerName(customer.getCustomerName());
			itemList.add(item);
		}
		
		return ListResult.success("查询成功", itemList, total);
		
	}

	private void check4queryCustomer4Add(UserVo currentLoginUserVO, QueryCustomer4AddUserPrivilegeRequest request) {
		StringUtil.trimObjectFields(request);
		if(currentLoginUserVO == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "未登录");
		if(currentLoginUserVO.getCustomer().getUserType() != UserType.FACTOR)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "只有保理商才能添加权限");
		if(request == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "请求参数为null");
		if(request.getPage() < 1)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "页码不正确");
		if(request.getPageSize() < 1)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "每页的条数不正确");
	}

	@Override
	public void delete(UserVo currentLoginUserVO, String id) {
		AssetUserPrivilege privilege = privilegeMapper.selectByPrimaryKey(id);
		if(privilege == null)
			return;
		if(!StringUtils.equals(privilege.getFactorId(), currentLoginUserVO.getCustomerId()))
			throw WebException.instance(ReturnCode.ERROR, "无权限");
		privilegeMapper.deleteByPrimaryKey(id);
	}

}

package com.cana.asset.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.CustomerMapper;
import com.cana.asset.dao.po.AssetUserPrivilege;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.CustomerExample;
import com.cana.asset.service.IAssetUserPrivilegeService;
import com.cana.asset.service.transaction.IAssetUserPrivilegeTransactionService;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.asset.dto.AddUserPrivilegeRequest;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeListItem;
import com.cana.vbam.common.asset.dto.QueryCompany4AddUserPrivilegeRequest;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchCriteriaDTO;
import com.cana.vbam.common.member.dto.user.CustomerSearchResultDTO;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class AssetUserPrivilegeServiceImpl implements IAssetUserPrivilegeService{
	
	@Resource
	private IAssetUserPrivilegeTransactionService transactionService;
	@Resource
	private IUserApi userApi;
	@Resource
	private SequenceGenerator seqGen;
	@Resource
	private CustomerMapper customerMapper;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void add(UserVo currentLoginUserVO, AddUserPrivilegeRequest request) {
		check4Add(currentLoginUserVO, request);
		
		CustomerDetailDTO platformUser = userApi.queryCustomerDetail(request.getMasterId());
		if(platformUser == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "未获取到企业信息");
		
		List<AssetUserPrivilege> privileges = new ArrayList<>();
		if(request.isAll()){
			privileges.add(add4All(platformUser, currentLoginUserVO, request));
		}else if(request.isCurrentAll()){
			privileges.addAll(add4CustomerList(platformUser, currentLoginUserVO, request, queryCustomerList4CurrentAll(currentLoginUserVO, request)));
		}else{
			privileges.addAll(add4CustomerList(platformUser, currentLoginUserVO, request, queryCustomerListByCustomerIdList(currentLoginUserVO, request)));
		}
		transactionService.add(privileges);
	}

	private List<Customer> queryCustomerListByCustomerIdList(UserVo currentLoginUserVO, AddUserPrivilegeRequest request) {
		CustomerExample example = new CustomerExample();
		example.createCriteria().andFactorIdEqualTo(currentLoginUserVO.getCustomerId())
								.andIdIn(request.getCustomerIdList());
		return customerMapper.selectByExample(example);
	}

	private List<AssetUserPrivilege> add4CustomerList(CustomerDetailDTO platformUser, UserVo currentLoginUserVO, AddUserPrivilegeRequest request, List<Customer> customerList){
		List<AssetUserPrivilege> privileges = new ArrayList<>();
		for(Customer customer : customerList){
			AssetUserPrivilege privilege = assetUserPrivilegeTemplate(platformUser, currentLoginUserVO, request);
			privilege.setCustomerId(customer.getId());
			privilege.setCustomerName(customer.getCustomerName());
			privileges.add(privilege);
		}
		return privileges;
	}
	
	private List<Customer> queryCustomerList4CurrentAll(UserVo currentLoginUserVO, AddUserPrivilegeRequest request){
		CustomerExample example = new CustomerExample();
		example.createCriteria().andFactorIdEqualTo(currentLoginUserVO.getCustomerId())
								.andCustomerNameLike("%" + request.getCustomerNameQuery() + "%");
		List<Customer> customerList = customerMapper.selectByExample(example);
		Iterator<Customer> iter = customerList.iterator();
		if(CollectionUtils.isNotEmpty(request.getCustomerIdList())){
			while(iter.hasNext()){
				Customer customer = iter.next();
				boolean deselected = false;
				for(String deselectedCustomerId : request.getCustomerIdList()){
					deselectedCustomerId = StringUtils.trimToEmpty(deselectedCustomerId);
					if(deselectedCustomerId.equals(customer.getId())){
						deselected = true;
						break;
					}
				}
				if(deselected)
					iter.remove();
			}
		}
		return customerList;
	}

	private AssetUserPrivilege add4All(CustomerDetailDTO platformUser, UserVo currentLoginUserVO, AddUserPrivilegeRequest request) {
		AssetUserPrivilege privilege = assetUserPrivilegeTemplate(platformUser, currentLoginUserVO, request);
		privilege.setCustomerId(Constants.ASSET_PRIVILEGE_ALL);
		privilege.setCustomerName("所有客户");
		return privilege;
	}
	
	private AssetUserPrivilege assetUserPrivilegeTemplate(CustomerDetailDTO platformUser, UserVo currentLoginUserVO, AddUserPrivilegeRequest request) {
		AssetUserPrivilege privilege = new AssetUserPrivilege();
		privilege.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_USER_PRIVILEGE_ID, 4));
		privilege.setUserType(platformUser.getUserType().name());
		privilege.setMasterId(request.getMasterId());
		privilege.setCompanyName(platformUser.getCompanyName());
		privilege.setFactorId(currentLoginUserVO.getCustomerId());
		privilege.setFactorName(currentLoginUserVO.getCustomer().getCustomerName());
		privilege.setCreatorId(currentLoginUserVO.getUserId());
		return privilege;
	}

	private void check4Add(UserVo currentLoginUserVO, AddUserPrivilegeRequest request) {
		StringUtil.trimObjectFields(request);
		if(currentLoginUserVO == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "未登录");
		if(currentLoginUserVO.getCustomer().getUserType() != UserType.FACTOR)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "只有保理商才能添加权限");
		if(request == null)
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "请求参数为null");
		if(StringUtils.isBlank(request.getMasterId()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "未指定平台用户");
		if(!request.isAll() && !request.isCurrentAll() && CollectionUtils.isEmpty(request.getCustomerIdList()))
			throw WebException.instance(ReturnCode.REQ_PARAMS_ERROR, "未指定客户");
		
	}

	@Override
	public ListResult<QueryCompany4AddUserPrivilegeListItem> queryCompany4Add(UserVo currentLoginUserVO, QueryCompany4AddUserPrivilegeRequest request) {
		check4queryCompany4Add(currentLoginUserVO, request);
		
		CustomerSearchCriteriaDTO customerSearchCriteriaDTO = new CustomerSearchCriteriaDTO();
		customerSearchCriteriaDTO.setUserType(request.getUserType());
		customerSearchCriteriaDTO.setUserStatusWithInList(Arrays.asList(UserStatus.PENDINGACTIVATE.name(), UserStatus.ACTIVATED.name()));
		customerSearchCriteriaDTO.setCompanyName(request.getCompanyName());
		customerSearchCriteriaDTO.setPage(request.getPage());
		customerSearchCriteriaDTO.setPageSize(request.getPageSize());
		ListResult<CustomerSearchResultDTO> resultFromUser = null;
		try{
			resultFromUser = userApi.queryCustomerList(customerSearchCriteriaDTO);
		}catch(Exception e){
			logger.error("", e);
			throw WebException.instance(ReturnCode.ERROR, "查询企业信息失败"); 
		}
		if(resultFromUser == null || resultFromUser.getStatus() != AjaxResponseStatus.SUCCESS)
			throw WebException.instance(ReturnCode.ERROR, "查询企业信息失败");
		
		List<QueryCompany4AddUserPrivilegeListItem> itemList = new ArrayList<>();
		for(CustomerSearchResultDTO rawData : resultFromUser.getData()){
			QueryCompany4AddUserPrivilegeListItem item = new QueryCompany4AddUserPrivilegeListItem();
			item.setMasterId(rawData.getId());
			item.setCompanyName(rawData.getCompanyName());
			item.setUserTypeDesc(rawData.getUserType().desc());
			itemList.add(item);
		}
		
		return ListResult.success("查询成功", itemList, resultFromUser.getTotalNum());
	}

	private void check4queryCompany4Add(UserVo currentLoginUserVO, QueryCompany4AddUserPrivilegeRequest request) {
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

	
}

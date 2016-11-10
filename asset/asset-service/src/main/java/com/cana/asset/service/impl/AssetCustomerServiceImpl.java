package com.cana.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.asset.dao.mapper.gen.CustomerMapper;
import com.cana.asset.dao.po.Customer;
import com.cana.asset.dao.po.CustomerExample;
import com.cana.asset.service.IAssetCustomerService;
import com.cana.asset.service.convertors.CustomerCovertor;
import com.cana.vbam.common.asset.dto.CounterpartyListSearchDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.travelzen.framework.core.exception.WebException;

@Service
public class AssetCustomerServiceImpl implements IAssetCustomerService {

	@Resource
	private CustomerMapper customerMapper;
	
	@Override
	public Customer getCustomerNameById(String id) {
		Customer customer = customerMapper.selectByPrimaryKey(id);
		if(customer == null)
			throw WebException.instance("不存在该客户");
		return customer;
	}

	@Override
	public ListResult<CustomerListResponseDTO> queryCustomerListByCondition(UserVo userDetail, CounterpartyListSearchDTO searchDTO) {
		CustomerExample customerExample = new CustomerExample();
		CustomerExample.Criteria criteria = customerExample.createCriteria();
		// 如果是保理商查询保理商创建的
		if (userDetail.getCustomer().getUserType().equals(UserType.FACTOR)) {
			criteria.andFactorIdEqualTo(userDetail.getCustomerId());
		}
		// 排除已选中的客户信息
		if(!CollectionUtils.isEmpty(searchDTO.getExceptList())){
			for(String customerId : searchDTO.getExceptList()){
				criteria.andIdNotEqualTo(customerId);
			}
		}
		// 判断查询条件和sql条件拼接
		if (StringUtils.isNotBlank(searchDTO.getCustomerName())) {// 客户名称
			criteria.andCustomerNameLike("%" + searchDTO.getCustomerName() + "%");
		}
		customerExample.setOrderByClause("create_time desc");
		// 分页参数
		customerExample.setLimitStart((searchDTO.getPage() - 1) * searchDTO.getPageSize());
		customerExample.setLimitEnd(searchDTO.getPageSize());
		List<Customer> customers = customerMapper.selectByExample(customerExample);
		int count = customerMapper.countByExample(customerExample);
		List<CustomerListResponseDTO> customerDTOs = CustomerCovertor.convertCustomertDao2ResDTO(customers);
		return ListResult.success(customerDTOs, count);
	}
}

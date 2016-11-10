package com.cana.member.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.cana.member.api.IMemberQueryApi;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.member.dao.po.UserExample;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.CustomerVo;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

public class MemberQueryApiImpl implements IMemberQueryApi {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public UserVo findUserById(String userId) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}

		User user = userMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return null;
		}
		User customer = user;
		if (StringUtils.isNotBlank(user.getMasterId())) {
			customer = userMapper.selectByPrimaryKey(user.getMasterId());
		}
		
		UserVo userVo = new UserVo();
		userVo.setUserId(user.getId());
		userVo.setUsername(user.getUsername());
		userVo.setRealname(user.getRealName());
		
		if (customer != null) {
			userVo.setCustomer(convertUser2CustomerVo(customer));
		}
		
		return userVo;
	}

	@Override
	public CustomerVo findCustomerByName(UserType userType, String customerName) {
		if (userType == null) {
			throw WebException.instance("用户类型不能为空");
		}
		if (StringUtils.isEmpty(customerName)) {
			throw WebException.instance("客户名称不能为空");
		}
		UserExample example = new UserExample();
		example.createCriteria().andUserTypeEqualTo(userType.name())
			.andCompanyNameEqualTo(customerName)
			.andUserStatusIn(validUserStatus);
		List<User> users = userMapper.selectByExample(example);
		if (users.size() == 0) {
			return null;
		}
		if (users.size() > 1) {
			throw WebException.instance("存在重复的客户");
		}
		
		return convertUser2CustomerVo(users.get(0));
	}

	private CustomerVo convertUser2CustomerVo(User customer) {
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustomerId(customer.getId());
		customerVo.setCustomerName(customer.getCompanyName());
		customerVo.setUserType(UserType.valueOf(customer.getUserType()));
		customerVo.setOrganizationCode(customer.getOrganizationCode());
		customerVo.setBusinessLicenceCode(customer.getBusinessLicenceCode());
		customerVo.setTaxRegistrationCertificateCode(customer.getTaxRegistrationCertificateCode());
		return customerVo;
	}
	
	private static List<String> validUserStatus = Lists.newArrayList(UserStatus.PENDINGACTIVATE.name(), UserStatus.ACTIVATED.name());
}

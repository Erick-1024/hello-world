package com.cana.account.service.transaction.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.member.dao.po.UserExample;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

@Service
public class CustomerTransactionServiceImpl implements ICustomerTransactionService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findCustomerByCompanyNameAndUserType(String companyName,UserType userType) {
        List<String> userStatus = Lists.newArrayList(
                UserStatus.ACTIVATED.name(),
                UserStatus.PENDINGACTIVATE.name(),
                UserStatus.PENDINGAUDIT.name()
                );
        UserExample example = new UserExample();
        example.createCriteria().andCompanyNameEqualTo(companyName).andUserTypeEqualTo(userType.name())
                .andUserStatusIn(userStatus);
        List<User> users = userMapper.selectByExample(example);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    @Override
    public User checkCustomerIsValid(String customerId) {
        if (StringUtils.isBlank(customerId))
            throw WebException.instance("客户Id不能为空");
        User customer = userMapper.selectByPrimaryKey(customerId);
        checkCustomerIsValid(customer);
        return customer;
    }

    @Override
    public User checkCustomerIsValid(User customer) {
        if (customer == null)
            throw WebException.instance("客户不存在");
        if (!EnumUtils.isValidEnum(UserType.class, customer.getUserType()))
            throw WebException.instance(customer.getId()+"的客户类型为"+customer.getUserType()+"客户类型无效");
        if (!EnumUtils.isValidEnum(UserStatus.class, customer.getUserStatus()))
            throw WebException.instance("客户状态无效");
        if (UserStatus.DELETED.name().equals(customer.getUserStatus())
                || UserStatus.REJECTED.name().equals(customer.getUserStatus())
                || UserStatus.PENDINGAUDIT.name().equals(customer.getUserStatus()))
            throw WebException.instance("客户状态无效");
        return customer;
    }

    @Override
    public String getCustomerIdByUserId(String userId) {
        User user = findUserById(userId);
        String customerId = user.getMasterId();
        if (StringUtils.isBlank(customerId))
            customerId = user.getId();
        return customerId;
    }

	@Override
	public User findUserById(String userId) {
		if (StringUtils.isBlank(userId))
            throw WebException.instance("用户ID不能为空");
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null)
            throw WebException.instance("用户不存在");
        return user;
	}
}

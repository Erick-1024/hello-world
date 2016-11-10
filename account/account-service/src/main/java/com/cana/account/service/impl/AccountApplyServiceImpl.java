package com.cana.account.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.cana.account.service.IAccountApplyService;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.member.dao.po.UserExample;
import com.cana.vbam.common.member.enums.user.UserStatus;
import com.cana.vbam.common.member.enums.user.UserType;

@Service
public class AccountApplyServiceImpl implements IAccountApplyService {

//    private static int CUSTOMER_DATA_VALID_MONTHS = 1;

    @Resource
    private UserMapper userMapper;

//    @Override
//    public boolean validCustomerDataDeadTime(String customerId) {
//        if (StringUtils.isBlank(customerId)) {
//            return false;
//        }
//        User customer = userMapper.selectByPrimaryKey(customerId);
//        return validCustomerDataDeadTime(customer);
//    }
//
//    @Override
//    public boolean validCustomerDataDeadTime(User customer) {
//        if (customer == null)
//            return false;
//        if (UserType.CANA.name().equals(customer.getUserType()))
//            return true;
//        if (customer.getAuditTime() == null)
//            return false;
//
//        Date auditTime = customer.getAuditTime();
//        DateTime deadTime = new DateTime(auditTime).plusMonths(CUSTOMER_DATA_VALID_MONTHS);
//        if (deadTime.isAfterNow()) {
//            return true;
//        }
//        return false;
//    }

    @Deprecated
    @Override
	public User findUserByCompanyName(String companyName) {
        UserExample example = new UserExample();
        example.createCriteria().andCompanyNameEqualTo(companyName)
                .andUserStatusNotIn(Arrays.asList(UserStatus.REJECTED.name(), UserStatus.DELETED.name()));
        List<User> users = userMapper.selectByExample(example);
        if (users == null || users.size() < 1)
            return null;
        else
            return users.get(0);
    }
}

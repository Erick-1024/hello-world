package com.cana.account.service.transaction;

import com.cana.member.dao.po.User;
import com.cana.vbam.common.member.enums.user.UserType;

/**
 * 客户transactionService，仅供账户管理server内部使用
 * @author XuMeng
 *
 */
public interface ICustomerTransactionService {

    /**
     * 通过公司名称查询客户
     */
    public User findCustomerByCompanyNameAndUserType(String companyName,UserType userType);
    
    public User findUserById(String userId);
    
    /**
     * 检查客户是否有效
     */
    public User checkCustomerIsValid(String customerId);
    public User checkCustomerIsValid(User customer);

    public String getCustomerIdByUserId(String userId);
}

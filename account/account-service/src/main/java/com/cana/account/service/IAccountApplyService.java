package com.cana.account.service;

import com.cana.member.dao.po.User;

/**
 * 开户申请相关业务处理接口
 * @author XuMeng
 *
 */
public interface IAccountApplyService {

    /**
     * 根据客户Id判断当前客户资料有效期有没有过了账户申请的允许有效期，
     * 此接口会调用validCustomerDataDeadTime(User customer)接口。
     */
//    public boolean validCustomerDataDeadTime(String customerId);

    /**
     * 根据客户判断当前客户资料有效期有没有过了账户申请的允许有效期，
     * 如果是CANA用户的话，则直接返回true
     */
//    public boolean validCustomerDataDeadTime(User customer);

    /**
     * 通过企业名字获取客户
     * @deprecated 请使用 ICustomerTxService
     */
    public User findUserByCompanyName(String finaceName);
}

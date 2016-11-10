package com.cana.account.service;

import java.util.List;

import com.cana.account.dao.po.Account;
import com.cana.member.dao.po.User;

/**
 * 账户管理发送邮件等信息给客户业务处理类
 * @author XuMeng
 *
 */
public interface IAccountMessageService {

    /**
     * 主动开户发送开户成功邮件
     * @param customer，主动开户的客户
     * @param accounts，开通的银行账户
     */
    public void sendMailForCreateAccountBySelf(User customer, List<Account> accounts);

    /**
     * 代开户审核发送邮件
     * @param accountApplyId，代开户申请ID
     * @param needActiveUrl，审核通过时，是否向融资商发送激活链接
     */
    public void sendMailForCreateAccountByAgent(String accountApplyId, boolean needActiveUrl);

    /**
     * 发送通知消息
     * @param tradeApplyId 交易申请ID
     */
    public void sendNotificationForTradeApply(String sendUserId, String tradeApplyId);

    /**
     * 发送通知消息
     * @param tradeApplyId 交易申请ID
     */
    public void sendNotificationForAccountApply(String sendUserId, String accountApplyId);
}

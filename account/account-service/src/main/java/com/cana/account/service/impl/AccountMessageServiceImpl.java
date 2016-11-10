package com.cana.account.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.account.dao.mapper.gen.AccountApplyMapper;
import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.mapper.gen.AccountTradeApplyMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountApply;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.dao.po.AccountTradeApply;
import com.cana.account.service.IAccountMessageService;
import com.cana.account.service.transaction.ICustomerTransactionService;
import com.cana.member.api.IUserApi;
import com.cana.member.dao.po.User;
import com.cana.message.client.message.MessageClient;
import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.NotificationMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.NotificationType;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.WebEnv;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

@Service
public class AccountMessageServiceImpl implements IAccountMessageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MessageClient messageClient;
    @Resource
    private ICustomerTransactionService customerTransactionService;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountApplyMapper accountApplyMapper;
    @Resource
    private IUserApi userApi;
    @Resource
    private AccountTradeApplyMapper accountTradeApplyMapper;

    private static final String mail_login_url_suffix = "登录链接：" + WebEnv.getVBAMPlatformLoginUrl();
    private static final String cana_phone = "021-53866655";

    private static final String self_create_general_account_template =
            "尊敬的${customerName}用户：<br/>"
            + "您已开通银行账号为${accountNos}，详情请登录CANA平台查询【CANA金融】<br/>"
            + mail_login_url_suffix;

    private static final String self_create_special_account_template =
            "尊敬的${customerName}用户：<br/>"
            + "您已成功申请专用账户，银行账号明细如下：<br/>"
            + "${accountInfo}"
            + "如有异常，请尽快与CANA金融联系，电话："
            + cana_phone + "。点击以下链接登录【CANA金融】<br/>"
            + mail_login_url_suffix;

    private static final String agent_create_account_reject_template =
            "尊敬的${factorName}用户：<br/>"
            + "${finaceName}公司暂不符合我司开户要求，您的代开户申请未通过审批，请核实资料后重新申请【CANA金融】";

    private static final String agent_create_account_factor_template =
            "尊敬的${factorName}用户：<br/>"
            + "您已为${finaceName}成功申请银行账号，明细如下：<br/>"
            + "${accountInfo}"
            + "请知悉！【CANA金融】<br/>"
            + mail_login_url_suffix;

    private static final String agent_create_general_account_finace_template =
            "尊敬的${finaceName}用户：<br/>"
            + "${factorName}已为您成功代申请银行账号，明细如下：<br/>"
            + "${accountInfo}<br/>"
            + "该账户将用于${factorName}扣款绑定，如有异常，请尽快与CANA金融联系，电话："
            + cana_phone + "。请知悉！【CANA金融】<br/>"
            + mail_login_url_suffix;

    private static final String agent_create_general_account_finace_active_template =
            "尊敬的${finaceName}用户：<br/>"
            + "${factorName}已为您成功代申请银行账号，明细如下：<br/>"
            + "${accountInfo}<br/>"
            + "该账户将用于${factorName}扣款绑定，如有异常，请尽快与CANA金融联系，电话："
            + cana_phone + "。点击以下链接激活【CANA金融】<br/>"
            + "激活链接${activeUrl}";

    private static final String agent_create_special_account_finace_template =
            "尊敬的${finaceName}用户：<br/>"
            + "${factorName}已为您成功代申请银行账号，明细如下：<br/>"
            + "${accountInfo}<br/>"
            + "账户${supervisionAccountNo}将作为资金归集账户，用于${factorName}公司扣款绑定，如有异常，请尽快与CANA金融联系，电话："
            + cana_phone + "【CANA金融】<br/>"
            + mail_login_url_suffix;

    private static final String agent_create_special_account_finace_active_template =
            "尊敬的${finaceName}用户：<br/>"
            + "${factorName}已为您成功代申请银行账号，明细如下：<br/>"
            + "${accountInfo}<br/>"
            + "账户${supervisionAccountNo}将作为资金归集账户，用于${factorName}公司扣款绑定，如有异常，请尽快与CANA金融联系，电话："
            + cana_phone + "。点击以下链接激活CANA平台账户【CANA金融】<br/>"
            + "激活链接${activeUrl}";

    @Override
    public void sendMailForCreateAccountBySelf(User customer,
            List<Account> accounts) {
    	try {
	        if (AccountType.GENERAL.name().equals(accounts.get(0).getAccountType())) {
	            String content = self_create_general_account_template;
	            content = content.replace("${customerName}", customer.getCompanyName());
	            List<String> accountNos = Lists.newArrayList();
	            for (Account account : accounts) {
	                accountNos.add(account.getAccountNo());
	            }
	            content = content.replace("${accountNos}", StringUtils.join(accountNos, "，"));
	            sendMail(customer.getContactMail(), content);
	        } else {
	            String content = self_create_special_account_template;
	            content = content.replace("${customerName}", customer.getCompanyName());
	            content = content.replace("${accountInfo}", getAccountInfoTable(accounts, new Date(), true));
	            sendMail(customer.getContactMail(), content);
	        }
    	} catch (Exception e) {
    		logger.error("发送主动开户邮件异常", e);
    	}
    }

    @Override
    public void sendMailForCreateAccountByAgent(String accountApplyId,
            boolean needActiveUrl) {
    	try {
	        AccountApply apply = accountApplyMapper.selectByPrimaryKey(accountApplyId);
	        User agentCustomer = customerTransactionService.checkCustomerIsValid(apply.getAgentCompanyId());
	        if (AccountApplyStatus.REJECTED.name().equals(apply.getApplyStatus())) {
	            String content = agent_create_account_reject_template;
	            content = content.replace("${factorName}", agentCustomer.getCompanyName());
	            content = content.replace("${finaceName}", apply.getCompanyName());
	            sendMail(agentCustomer.getContactMail(), content);
	        } else if (AccountApplyStatus.ACCEPTED.name().equals(apply.getApplyStatus())) {
	            AccountExample example = new AccountExample();
	            example.createCriteria().andAccountApplyIdEqualTo(accountApplyId);
	            List<Account> accounts = accountMapper.selectByExample(example);
	            
	            String factorContent = agent_create_account_factor_template;
	            factorContent = factorContent.replace("${factorName}", agentCustomer.getCompanyName());
	            factorContent = factorContent.replace("${finaceName}", apply.getCompanyName());
	            factorContent = factorContent.replace("${accountInfo}", getAccountInfoTable(accounts, apply.getCreateTime(), false));
	            sendMail(agentCustomer.getContactMail(), factorContent);
	
	            String supervisionAccountNo = "";
	            if (StringUtils.isNotBlank(apply.getSupervisionAccountId())) {
	                Account supervisionAccount = accountMapper.selectByPrimaryKey(apply.getSupervisionAccountId());
	                supervisionAccountNo = supervisionAccount.getAccountNo();
	            } else {
	                for (int i = 0; i < accounts.size(); ++i) {
	                    if (AccountType.GENERAL.name().equals(accounts.get(i).getAccountType())) {
	                        supervisionAccountNo = accounts.get(i).getAccountNo();
	                        break;
	                    }
	                }
	            }
	            boolean isSpecialAccountApply = AccountType.SPECIAL.name().equals(apply.getAccountType());
	            String accountInfo = getAccountInfoTable(accounts, apply.getCreateTime(), isSpecialAccountApply);
	            String finaceContent = null;
	            if (!isSpecialAccountApply) {
	                if (!needActiveUrl) {
	                    finaceContent = agent_create_general_account_finace_template;
	                } else {
	                    finaceContent = agent_create_general_account_finace_active_template;
	                }
	            } else {
	                if (!needActiveUrl) {
	                    finaceContent = agent_create_special_account_finace_template;
	                } else {
	                    finaceContent = agent_create_special_account_finace_active_template;
	                }
	                finaceContent = finaceContent.replace("${supervisionAccountNo}", supervisionAccountNo);
	            }
	            finaceContent = finaceContent.replace("${factorName}", agentCustomer.getCompanyName());
	            finaceContent = finaceContent.replace("${factorName}", agentCustomer.getCompanyName());
	            finaceContent = finaceContent.replace("${finaceName}", apply.getCompanyName());
	            finaceContent = finaceContent.replace("${accountInfo}", accountInfo);
	            if (needActiveUrl) {
	                String activeUrl = "";
	                try {
	                    activeUrl = userApi.generateActivacationURL(apply.getCompanyId());
	                } catch (Exception e) {
	                    logger.warn("获取激活链接失败", e);
	                }
	                finaceContent = finaceContent.replace("${activeUrl}", StringUtils.trimToEmpty(activeUrl));
	            }
	            sendMail(apply.getContactMail(), finaceContent);
	        }
    	} catch (Exception e) {
    		logger.error("发送代开户邮件异常", e);
    	}
    }
    
    /**
     * 拼装开户成功邮件
     */
    private void sendMail(String receiver, String content) {
        MailMessageDTO mail = new MailMessageDTO();
        mail.setContentType(MailContentType.HTML);
        mail.setReceiver(receiver);
        mail.setSubject("CANA开户通知");
        mail.setContent(content);
        messageClient.sendMail(mail);
    }

    @Override
    public void sendNotificationForTradeApply(String sendUserId,
            String tradeApplyId) {

    	try {
	        NotificationMessageDTO notification = new NotificationMessageDTO();
	        AccountTradeApply apply = accountTradeApplyMapper.selectByPrimaryKey(tradeApplyId);
	        switch (AccountTradeApplyType.valueOf(apply.getTradeType())) {
	        case CREATE_SUPERVISION:
	            notification.setContent(apply.getApplyCompanyName() + "发起账户监管申请。");
	            notification.setType(NotificationType.CREATE_SUPERVISION);
	            break;
	        case REMOVE_SUPERVISION:
	            notification.setContent(apply.getApplyCompanyName() + "申请解除银行账号为"
	                    + apply.getAccountNo() + "的监管帐户。");
	            notification.setType(NotificationType.REMOVE_SUPERVISION);
	            break;
	        case TRANSFER_FUND:
	            notification.setContent(apply.getApplyCompanyName() + "申请从监管账号为"
	                    + apply.getAccountNo() + "的账户中转出"
	                    + MoneyUtil.cent2Yuan(apply.getAmount()) + "元。");
	            notification.setType(NotificationType.TRANSFER_FUND);
	            break;
	        case WITHDRAW_FUND:
	            notification.setContent(apply.getApplyCompanyName() + "申请从监管账号为"
	                    + apply.getAccountNo() + "的账户中提现"
	                    + MoneyUtil.cent2Yuan(apply.getAmount()) + "元。");
	            notification.setType(NotificationType.WITHDRAW_FUND);
	            break;
	        }
	        notification.setDetailURL("/account/audit/redirect?tradeApplyId=" + apply.getId());
	        notification.setSendUserId(sendUserId);
	        notification.setReceiveCustomerId(apply.getAuditCompanyId());
	        messageClient.sendNotification(notification);
    	} catch (Exception e) {
    		logger.error("发送交易、监管通知消息异常", e);
    	}
    }

    @Override
    public void sendNotificationForAccountApply(String sendUserId,
            String accountApplyId) {
    	try {
	        NotificationMessageDTO notification = new NotificationMessageDTO();
	        AccountApply apply = accountApplyMapper.selectByPrimaryKey(accountApplyId);
	        notification.setContent(apply.getAgentCompanyName() + "发起代开户申请，代办企业"
	                + apply.getCompanyName() + "。");
	        notification.setType(NotificationType.CREATE_ACCOUNT);
	        notification.setDetailURL("/account/apply/redirect?applyId=" + apply.getId());
	        notification.setSendUserId(sendUserId);
	        notification.setReceiveCustomerId(Constants.CANA_CUSTOMER_ID);
	        messageClient.sendNotification(notification);
    	} catch (Exception e) {
    		logger.error("发送开户申请通知异常", e);
    	}
    }


    private String getAccountInfoTable(List<Account> accounts, Date applyTime, boolean showSpecialAccount) {
        StringBuilder accountInfo = new StringBuilder();
        accountInfo.append("<table><tr>");
        if (showSpecialAccount)
            accountInfo.append("<th>账户性质</th>");
        accountInfo.append("<th>账号名称</th>");
        accountInfo.append("<th>银行账号</th>");
        if (showSpecialAccount)
            accountInfo.append("<th>买方企业</th>");
        accountInfo.append("<th>申请时间</th></tr>");
        for (Account account : accounts) {
            accountInfo.append("<tr><td>");
            if (showSpecialAccount) {
                accountInfo.append(AccountType.valueOf(account.getAccountType()).desc());
                accountInfo.append("</td><td>");
            }
            accountInfo.append(account.getCompanyName());
            accountInfo.append("</td><td>");
            accountInfo.append(account.getAccountNo());
            accountInfo.append("</td><td>");
            if (showSpecialAccount) {
                accountInfo.append(StringUtils.trimToEmpty(account.getBuyerName()));
                accountInfo.append("</td><td>");
            }
            accountInfo.append(DateTimeUtil.formatDate(applyTime));
            accountInfo.append("</td></tr>");
        }
        accountInfo.append("</table>");
        return accountInfo.toString();
    }
}

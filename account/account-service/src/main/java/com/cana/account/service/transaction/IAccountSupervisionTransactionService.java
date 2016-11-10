package com.cana.account.service.transaction;

import java.util.List;

import com.cana.account.dao.po.Account;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountSupervisionCreateDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;

/**
 * 账户监管相关业务处理
 * @author XuMeng
 *
 */
public interface IAccountSupervisionTransactionService {

    public boolean createSupervisionWithoutAudit(String applyUserId, String accountNo, String supervisionCustomerId);
    public String createSupervision(String userId, AccountSupervisionCreateDTO supervisionCreateDTO);

    public String removeSupervision(String userId, List<String> accountIds);
    
    public boolean auditSupervision(String auditUserId,
            String accountSupervisionApplyId, boolean isAgree, String message);

    /**
     * 此接口仅供账户代开户审核通过后、新建监管关系审核通过后、createSupervisionWithoutAudit接口三处调用
     * 新建监管关系，以下参数合法校验由调用方检查，账户的数据库锁也是调用方负责
     * @param applyCustomer，新建申请客户，必须是保理商或者融资商
     * @param oppositeCustomer，对方客户，必须是保理商或者融资商
     * @param majorAccount，需要监管的一般账户，不可为空
     * @param subAccounts，需要监管的专用账户，可以为空
     */
    public void createSupervision(User applyCustomer, User oppositeCustomer,
            Account majorAccount, List<Account> subAccounts);

    public AccountTradeApplyDTO getSupervisionApply(String userId, String supervisionApplyId);
}

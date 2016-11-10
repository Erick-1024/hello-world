package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.account.enums.AccountType;

/**
 * 账户交易申请，包括新建监管申请、解除监管申请、转账申请、提现申请
 * @author XuMeng
 *
 */
public class AccountTradeApplyDTO implements Serializable {
    private static final long serialVersionUID = -1828755545595777751L;

    //所有申请种类的共有字段
    private String id;              //申请ID
    private String accountId;       //账户ID，转出账户、提现账户、新建/解除监管关系时的一般账户
    private String accountNo;       //账号，同上
    private String accountName;     //账户名称，同上
    private AccountTradeApplyType applyType;    //申请类型
    private AccountTradeApplyStatus applyStatus;//申请状态
    private String applyCompanyId;  //申请客户ID
    private String applyCompanyName;//申请客户名称
    private String auditCompanyId;  //需要审核的客户ID
    private String auditUserId;     //审核员工ID
    private String auditMessage;    //审核后的审核意见
    private Date auditTime;         //审核时间
    private Date createTime;        //创建时间，即申请时间
    private AccountType accountType;        //账户类型
    private AccountAccumulationStatus accumulationStatus;   //归集状态
    private AccountSupervisionStatus supervisionStatus;     //监管状态
    private String accountSupervisionId;  //监管关系id
    private String finaceBalance;  //融资余额

    //转账、提现使用的字段
    private String oppositeAccountId;   //转入账户id
    private String oppositeAccountNo;   //转入/提现账号
    private String oppositeAccountName; //转入/提现账户名称
    private String withdraw_bank;       //提现银行
    private String withdraw_bank_address;   //提现账户开户行地址
    private String amount;              //转账/提现金额
    private String charge;              //提现手续费
    //转帐专用
    private String remark;              //转帐备注

    //新建监管、解除监管使用的字段
    private String factorName;
    private String finaceName;
    private boolean defaultRepayment;        //新建监管申请中表示是否设置为默认还款账户，解除监管中表示主账户是不是默认还款账户
    private List<AccountDTO> specialAccounts;   //专用账户列表
    private List<AccountDTO> removeSupervisionAccounts; //解除监管时的解除账户列表

    //页面专用字段
    private String applyTypeDesc;
    private String applyStatusDesc;
    //以下是申请的审核页用到的
    private String accountTypeDesc;         //账户类型
    private String accountStatusDesc;       //账户状态
    private String accumulationStatusDesc;  //归集状态
    private String supervisionStatusDesc;   //监管状态
    private boolean allowAudit;             //是否允许当前客户审核

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public AccountTradeApplyType getApplyType() {
        return applyType;
    }
    public void setApplyType(AccountTradeApplyType applyType) {
        this.applyType = applyType;
    }
    public AccountTradeApplyStatus getApplyStatus() {
        return applyStatus;
    }
    public void setApplyStatus(AccountTradeApplyStatus applyStatus) {
        this.applyStatus = applyStatus;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getAuditMessage() {
        return auditMessage;
    }
    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }
    public String getOppositeAccountId() {
        return oppositeAccountId;
    }
    public void setOppositeAccountId(String oppositeAccountId) {
        this.oppositeAccountId = oppositeAccountId;
    }
    public String getOppositeAccountNo() {
        return oppositeAccountNo;
    }
    public void setOppositeAccountNo(String oppositeAccountNo) {
        this.oppositeAccountNo = oppositeAccountNo;
    }
    public String getOppositeAccountName() {
        return oppositeAccountName;
    }
    public void setOppositeAccountName(String oppositeAccountName) {
        this.oppositeAccountName = oppositeAccountName;
    }
    public String getWithdraw_bank() {
        return withdraw_bank;
    }
    public void setWithdraw_bank(String withdraw_bank) {
        this.withdraw_bank = withdraw_bank;
    }
    public String getWithdraw_bank_address() {
        return withdraw_bank_address;
    }
    public void setWithdraw_bank_address(String withdraw_bank_address) {
        this.withdraw_bank_address = withdraw_bank_address;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCharge() {
        return charge;
    }
    public void setCharge(String charge) {
        this.charge = charge;
    }
    public boolean isAllowAudit() {
        return allowAudit;
    }
    public void setAllowAudit(boolean allowAudit) {
        this.allowAudit = allowAudit;
    }
    public String getApplyTypeDesc() {
        return applyTypeDesc;
    }
    public void setApplyTypeDesc(String applyTypeDesc) {
        this.applyTypeDesc = applyTypeDesc;
    }
    public String getApplyStatusDesc() {
        return applyStatusDesc;
    }
    public void setApplyStatusDesc(String applyStatusDesc) {
        this.applyStatusDesc = applyStatusDesc;
    }
    public String getApplyCompanyId() {
        return applyCompanyId;
    }
    public void setApplyCompanyId(String applyCompanyId) {
        this.applyCompanyId = applyCompanyId;
    }
    public String getApplyCompanyName() {
        return applyCompanyName;
    }
    public void setApplyCompanyName(String applyCompanyName) {
        this.applyCompanyName = applyCompanyName;
    }
    public String getAuditCompanyId() {
        return auditCompanyId;
    }
    public void setAuditCompanyId(String auditCompanyId) {
        this.auditCompanyId = auditCompanyId;
    }
    public String getAuditUserId() {
        return auditUserId;
    }
    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }
    public Date getAuditTime() {
        return auditTime;
    }
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    public String getAccountTypeDesc() {
        return accountTypeDesc;
    }
    public void setAccountTypeDesc(String accountTypeDesc) {
        this.accountTypeDesc = accountTypeDesc;
    }
    public String getAccountStatusDesc() {
        return accountStatusDesc;
    }
    public void setAccountStatusDesc(String accountStatusDesc) {
        this.accountStatusDesc = accountStatusDesc;
    }
    public String getAccumulationStatusDesc() {
        return accumulationStatusDesc;
    }
    public void setAccumulationStatusDesc(String accumulationStatusDesc) {
        this.accumulationStatusDesc = accumulationStatusDesc;
    }
    public String getSupervisionStatusDesc() {
        return supervisionStatusDesc;
    }
    public void setSupervisionStatusDesc(String supervisionStatusDesc) {
        this.supervisionStatusDesc = supervisionStatusDesc;
    }
    public String getFactorName() {
        return factorName;
    }
    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }
    public String getFinaceName() {
        return finaceName;
    }
    public void setFinaceName(String finaceName) {
        this.finaceName = finaceName;
    }
    public List<AccountDTO> getSpecialAccounts() {
        return specialAccounts;
    }
    public void setSpecialAccounts(List<AccountDTO> specialAccounts) {
        this.specialAccounts = specialAccounts;
    }
    public List<AccountDTO> getRemoveSupervisionAccounts() {
        return removeSupervisionAccounts;
    }
    public void setRemoveSupervisionAccounts(
            List<AccountDTO> removeSupervisionAccounts) {
        this.removeSupervisionAccounts = removeSupervisionAccounts;
    }
    public boolean isDefaultRepayment() {
        return defaultRepayment;
    }
    public void setDefaultRepayment(boolean defaultRepayment) {
        this.defaultRepayment = defaultRepayment;
    }
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public AccountAccumulationStatus getAccumulationStatus() {
		return accumulationStatus;
	}
	public void setAccumulationStatus(AccountAccumulationStatus accumulationStatus) {
		this.accumulationStatus = accumulationStatus;
	}
	public AccountSupervisionStatus getSupervisionStatus() {
		return supervisionStatus;
	}
	public void setSupervisionStatus(AccountSupervisionStatus supervisionStatus) {
		this.supervisionStatus = supervisionStatus;
	}
	public String getAccountSupervisionId() {
		return accountSupervisionId;
	}
	public void setAccountSupervisionId(String accountSupervisionId) {
		this.accountSupervisionId = accountSupervisionId;
	}
	public String getFinaceBalance() {
		return finaceBalance;
	}
	public void setFinaceBalance(String finaceBalance) {
		this.finaceBalance = finaceBalance;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

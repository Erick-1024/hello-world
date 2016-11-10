package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserType;

/**
 * 账户信息
 * @author XuMeng
 *
 */
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 5144722262990673644L;

    //基本信息
    private String accountId;
    private String accountNo;               //账号
    private String accumulationAccountId;   //归集账户Id
    private String accumulationAccountNo;   //归集账号
    private String accumulationAccountName; //归集账户名称
    private String accountName;             //账户名称
    private UserType userType;              //账户所属客户类型
    private AccountType accountType;        //账户类型
    private String companyId;				//账户所属企业ID
    private String factorId;				//资金方客户ID
    private String factorName;              //保理商企业名称
    private String finaceId;				//融资商客户ID
    private String finaceName;              //融资商企业名称
    private String coreCompanyName;         //核心企业名称
    private AccountStatus accountStatus;    //账户状态
    private AccountAccumulationStatus accumulationStatus;   //归集状态
    private AccountSupervisionStatus supervisionStatus;     //监管状态
    private String buyerName;                   //买方企业名称
    private boolean defaultRepayment;         //是否是默认还款账户
    private boolean transferInAccount;        //是否是保理商的回款账户
    private String accountSupervisionId;      //当前监管关系Id，对应account_supervision表

    //账户详情接口有值的字段：
    private List<AccountDTO> specialAccounts;   //专有账户

    //=====页面专用字段
    private String accountTypeDesc;
    private String accountStatusDesc;
    private String accumulationStatusDesc;  //归集状态
    private String supervisionStatusDesc;   //监管状态

    //=====当前账户可以进行的操作
    private boolean allowFreeze;    //冻结
    private boolean allowUnfreeze;  //解冻
    private boolean allowSetDefault;    //设为默认账户，非默认的监管账户，CANA/保理商可以进行此操作
    private boolean allowRemoveSupervision; //解除监管，点击解除监管，应跳至accumulationAccountId页面下

    public String getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getAccumulationAccountNo() {
        return accumulationAccountNo;
    }
    public void setAccumulationAccountNo(String accumulationAccountNo) {
        this.accumulationAccountNo = accumulationAccountNo;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
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
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }
    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
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
    public String getAccumulationAccountId() {
        return accumulationAccountId;
    }
    public void setAccumulationAccountId(String accumulationAccountId) {
        this.accumulationAccountId = accumulationAccountId;
    }
    public List<AccountDTO> getSpecialAccounts() {
        return specialAccounts;
    }
    public void setSpecialAccounts(List<AccountDTO> specialAccounts) {
        this.specialAccounts = specialAccounts;
    }
    public boolean isAllowFreeze() {
        return allowFreeze;
    }
    public void setAllowFreeze(boolean allowFreeze) {
        this.allowFreeze = allowFreeze;
    }
    public boolean isAllowUnfreeze() {
        return allowUnfreeze;
    }
    public void setAllowUnfreeze(boolean allowUnfreeze) {
        this.allowUnfreeze = allowUnfreeze;
    }
    public boolean isAllowSetDefault() {
        return allowSetDefault;
    }
    public void setAllowSetDefault(boolean allowSetDefault) {
        this.allowSetDefault = allowSetDefault;
    }
    public boolean isAllowRemoveSupervision() {
        return allowRemoveSupervision;
    }
    public void setAllowRemoveSupervision(boolean allowRemoveSupervision) {
        this.allowRemoveSupervision = allowRemoveSupervision;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public AccountAccumulationStatus getAccumulationStatus() {
        return accumulationStatus;
    }
    public void setAccumulationStatus(
            AccountAccumulationStatus accumulationStatus) {
        this.accumulationStatus = accumulationStatus;
    }
    public AccountSupervisionStatus getSupervisionStatus() {
        return supervisionStatus;
    }
    public void setSupervisionStatus(AccountSupervisionStatus supervisionStatus) {
        this.supervisionStatus = supervisionStatus;
    }
	public boolean isDefaultRepayment() {
		return defaultRepayment;
	}
	public void setDefaultRepayment(boolean defaultRepayment) {
		this.defaultRepayment = defaultRepayment;
	}
    public String getAccumulationAccountName() {
        return accumulationAccountName;
    }
    public void setAccumulationAccountName(String accumulationAccountName) {
        this.accumulationAccountName = accumulationAccountName;
    }
    public String getAccountSupervisionId() {
        return accountSupervisionId;
    }
    public void setAccountSupervisionId(String accountSupervisionId) {
        this.accountSupervisionId = accountSupervisionId;
    }
    public boolean isTransferInAccount() {
        return transferInAccount;
    }
    public void setTransferInAccount(boolean transferInAccount) {
        this.transferInAccount = transferInAccount;
    }
	public String getCoreCompanyName() {
		return coreCompanyName;
	}
	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getFactorId() {
		return factorId;
	}
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	public String getFinaceId() {
		return finaceId;
	}
	public void setFinaceId(String finaceId) {
		this.finaceId = finaceId;
	}
}

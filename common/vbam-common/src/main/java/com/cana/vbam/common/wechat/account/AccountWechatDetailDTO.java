package com.cana.vbam.common.wechat.account;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;

public class AccountWechatDetailDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//基本信息
    private String accountId;
    private String accountNo;               //账号
    private String accountBalance;			 //账户余额
//    private String accumulationAccountId;   //归集账户Id
//    private String accumulationAccountNo;   //归集账号
//    private String accumulationAccountName; //归集账户名称
    private String accountName;             //账户名称
//    private UserType userType;              //账户所属客户类型
    private AccountType accountType;        //账户类型
    private String companyId;				//账户所属企业ID
    private String factorId;				//资金方客户ID
    private String factorName;              //保理商企业名称
    private String finaceId;				//融资商客户ID
    private String finaceName;              //融资商企业名称
//    private String coreCompanyName;         //核心企业名称
    private AccountStatus accountStatus;    //账户状态
//    private AccountAccumulationStatus accumulationStatus;   //归集状态
    private AccountSupervisionStatus supervisionStatus;     //监管状态
//    private String buyerName;                   //买方企业名称
//    private boolean defaultRepayment;         //是否是默认还款账户
//    private boolean transferInAccount;        //是否是保理商的回款账户
//    private String accountSupervisionId;      //当前监管关系Id，对应account_supervision表

    //账户详情接口有值的字段：
//    private List<AccountDTO> specialAccounts;   //专有账户

    //=====页面专用字段
    private String accountTypeDesc;
    private String accountStatusDesc;
//    private String accumulationStatusDesc;  //归集状态
    private String supervisionStatusDesc;   //监管状态

    //=====当前账户可以进行的操作
//    private boolean allowFreeze;    //冻结
//    private boolean allowUnfreeze;  //解冻
//    private boolean allowSetDefault;    //设为默认账户，非默认的监管账户，CANA/保理商可以进行此操作
//    private boolean allowRemoveSupervision; //解除监管，点击解除监管，应跳至accumulationAccountId页面下
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
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
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
	public String getFactorName() {
		return factorName;
	}
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	public String getFinaceId() {
		return finaceId;
	}
	public void setFinaceId(String finaceId) {
		this.finaceId = finaceId;
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
	
	public AccountSupervisionStatus getSupervisionStatus() {
		return supervisionStatus;
	}
	public void setSupervisionStatus(AccountSupervisionStatus supervisionStatus) {
		this.supervisionStatus = supervisionStatus;
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
	public String getSupervisionStatusDesc() {
		return supervisionStatusDesc;
	}
	public void setSupervisionStatusDesc(String supervisionStatusDesc) {
		this.supervisionStatusDesc = supervisionStatusDesc;
	}
    
}

package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserType;

/**
 * 账户交易记录（收支明细/流水明细）
 * @author TangYihong
 *
 */
public class AccountTradeRecordDTO implements Serializable{

	private static final long serialVersionUID = 1903747100270261729L;
	
	private String id;
	private String accountId;
	private String businessSeq;
	private AccountTradeType tradeType;
	private String operateType; //操作方式
	private String remark;//备注
	private String oppositeAccountName;
	private String oppositeAccountNo;
	private String amount;  //转入 233.00 , 转出 -233.00
	private Date tradeStartTime;
	private Date tradeEndTime;
	private AccountTradeStatus status;
	//流水明细need
	private String accountName;
	private String accountNo;
	private String factorName;
    private String finaceName;
    private AccountType accountType;
    private AccountSupervisionStatus accountSupervisionStatus;
	//页面字段专用
	private String tradeTypeDesc;
	private String statusDesc;
	private String accountTypeDesc;
	private String accountSupervisionStatusDesc;
	private String userTypeDesc;
	//后台need
	private String accountSupervisionId;
	private UserType userType;
	private String companyName;
	private String supervisorName;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBusinessSeq() {
		return businessSeq;
	}
	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}
	public AccountTradeType getTradeType() {
		return tradeType;
	}
	public void setTradeType(AccountTradeType tradeType) {
		this.tradeType = tradeType;
	}
	public String getOppositeAccountName() {
		return oppositeAccountName;
	}
	public void setOppositeAccountName(String oppositeAccountName) {
		this.oppositeAccountName = oppositeAccountName;
	}
	public String getOppositeAccountNo() {
		return oppositeAccountNo;
	}
	public void setOppositeAccountNo(String oppositeAccountNo) {
		this.oppositeAccountNo = oppositeAccountNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getTradeStartTime() {
		return tradeStartTime;
	}
	public void setTradeStartTime(Date tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}
	public Date getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(Date tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	public AccountTradeStatus getStatus() {
		return status;
	}
	public void setStatus(AccountTradeStatus status) {
		this.status = status;
	}
	public String getTradeTypeDesc() {
		return tradeTypeDesc;
	}
	public void setTradeTypeDesc(String tradeTypeDesc) {
		this.tradeTypeDesc = tradeTypeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public AccountSupervisionStatus getAccountSupervisionStatus() {
		return accountSupervisionStatus;
	}
	public void setAccountSupervisionStatus(AccountSupervisionStatus accountSupervisionStatus) {
		this.accountSupervisionStatus = accountSupervisionStatus;
	}
	public String getAccountTypeDesc() {
		return accountTypeDesc;
	}
	public void setAccountTypeDesc(String accountTypeDesc) {
		this.accountTypeDesc = accountTypeDesc;
	}
	public String getAccountSupervisionStatusDesc() {
		return accountSupervisionStatusDesc;
	}
	public void setAccountSupervisionStatusDesc(String accountSupervisionStatusDesc) {
		this.accountSupervisionStatusDesc = accountSupervisionStatusDesc;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getAccountSupervisionId() {
		return accountSupervisionId;
	}
	public void setAccountSupervisionId(String accountSupervisionId) {
		this.accountSupervisionId = accountSupervisionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getUserTypeDesc() {
		return userTypeDesc;
	}
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}
}

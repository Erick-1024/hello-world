package com.cana.account.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 流水明细PO
 * @author TangYihong
 *
 */
public class AccountTradeRecordPO implements Serializable {

	private static final long serialVersionUID = 7744433389629376891L;
	
	private String id;//交易记录id
	private String accountId;
	private String businessSeq;
	private String tradeType;
	private String remark; //交易备注
	private String operateType;//操作方式
	private String oppositeAccountName;
	private String oppositeAccountNo;
	private Long amount;
	private Long fee;
	private Date tradeStartTime;
	private Date tradeEndTime;
	private String status;
	private String accountName;
	private String accountNo;
    private String accountType;
    private String accountSupervisionStatus;
	private String accountSupervisionId;
	private String userType;
	private String companyName;
	private String supervisorName;
	
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
	public String getBusinessSeq() {
		return businessSeq;
	}
	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
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
	public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountSupervisionStatus() {
		return accountSupervisionStatus;
	}
	public void setAccountSupervisionStatus(String accountSupervisionStatus) {
		this.accountSupervisionStatus = accountSupervisionStatus;
	}
	public String getAccountSupervisionId() {
		return accountSupervisionId;
	}
	public void setAccountSupervisionId(String accountSupervisionId) {
		this.accountSupervisionId = accountSupervisionId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
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
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
}
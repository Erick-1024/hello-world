package com.cana.vbam.common.wechat.account;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;

/**
 * 微信平台——流水明细返回
 * @author yihong.tang
 */
public class TradeRecordResponse implements Serializable{

	private static final long serialVersionUID = 1903747100270261729L;
	
	private String id;//主键
	private String businessSeq;//业务流水号
	private String operateType; //操作方式
	private AccountTradeType tradeType;//交易类型
	private String tradeTypeDesc;//交易类型描述
	private Long amount;  //交易金额 转入 23300 , 转出 -23300
	private String amountStr;  //交易金额 转入 233.00 , 转出 -233.00
	private String accountName;//账户名称
	private String accountNo;//银行账号
	private String oppositeAccountName;//交易户名
	private String oppositeAccountNo;//交易账号
	private Date tradeEndTime;//交易时间
	private AccountTradeStatus status;//交易状态
	private String statusDesc;//交易状态描述
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getAmountStr() {
		return amountStr;
	}
	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getAmount() {
		return amount;
	}
}


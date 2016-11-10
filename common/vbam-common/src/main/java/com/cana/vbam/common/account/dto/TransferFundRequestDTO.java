package com.cana.vbam.common.account.dto;

import java.io.Serializable;

public class TransferFundRequestDTO implements Serializable {
    private static final long serialVersionUID = 7754269369191113333L;

    private String userId;  //当前操作员ID
    private String accountNo;   //转出账号
    private String receiveAccountNo;    //收款账号
    private String receiveAccountName;  //收款户名
    private String payPassword;  //支付密码
    private String amount;    //转账金额，前端传入为人民币元，操作时需要转化成Long 分
    private String remark;    //备注
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getReceiveAccountNo() {
		return receiveAccountNo;
	}
	public void setReceiveAccountNo(String receiveAccountNo) {
		this.receiveAccountNo = receiveAccountNo;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReceiveAccountName() {
		return receiveAccountName;
	}
	public void setReceiveAccountName(String receiveAccountName) {
		this.receiveAccountName = receiveAccountName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

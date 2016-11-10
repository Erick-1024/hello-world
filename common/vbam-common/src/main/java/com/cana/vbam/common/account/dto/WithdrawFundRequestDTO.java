package com.cana.vbam.common.account.dto;

import java.io.Serializable;

public class WithdrawFundRequestDTO implements Serializable {

    private static final long serialVersionUID = -4862033430411969155L;

    private String userId;  //当前操作员ID
    private String businessSeq;
    private String accountNo;   //提现帐号
    private String amount;    //提现金额，单位为人民币元
    private String receiveAccountName;  //收款账户户名，必需等于当前登录客户的公司名
    private String receiveAccountNo;    //收款账号
    private String receiveBankName;     //收款银行名称
    private String lianHangNo;
    private String receiveBankAddress;  //收款银行开户行地址
    private String payPassword; //支付密码
    //private String commissionCharge; //提现手续费，单位为人民币元 //改为后台重新计算
    private String remark; //备注
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
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReceiveAccountNo() {
		return receiveAccountNo;
	}
	public void setReceiveAccountNo(String receiveAccountNo) {
		this.receiveAccountNo = receiveAccountNo;
	}
	public String getReceiveBankName() {
		return receiveBankName;
	}
	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReceiveAccountName() {
		return receiveAccountName;
	}
	public void setReceiveAccountName(String receiveAccountName) {
		this.receiveAccountName = receiveAccountName;
	}
	public String getLianHangNo() {
		return lianHangNo;
	}
	public void setLianHangNo(String lianHangNo) {
		this.lianHangNo = lianHangNo;
	}
	public String getReceiveBankAddress() {
		return receiveBankAddress;
	}
	public void setReceiveBankAddress(String receiveBankAddress) {
		this.receiveBankAddress = receiveBankAddress;
	}
	public String getBusinessSeq() {
		return businessSeq;
	}
	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}
}

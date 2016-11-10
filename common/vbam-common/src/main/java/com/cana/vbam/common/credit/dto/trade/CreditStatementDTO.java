package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;

public class CreditStatementDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String outTradeNo;//真旅订单编号
	
	private String outCustomerName;//真旅客户名称
	
	private String businessSeq;//账户业务流水号
	
	private String tradeType;//交易类型
	
	private String tradeStatus;//交易状态

	private String transferStatus;//转账状态
    
    private String fee;//金额
    
    private String tradeStartTime;//交易时间
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getBusinessSeq() {
		return businessSeq;
	}

	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}

	public String getTradeStartTime() {
		return tradeStartTime;
	}

	public void setTradeStartTime(String tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}
    

}

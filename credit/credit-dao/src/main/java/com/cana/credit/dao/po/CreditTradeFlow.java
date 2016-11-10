package com.cana.credit.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CreditTradeFlow implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String outTradeNo;
		
	private Date tradeStartTime;
	
	private String outOriginTradeNo;
	
	private String transferStatus;
	
	private Long fee;
	
	private Date transferStartTime;
	
	private String summaryId;
	
	private String transferType;
	
	private String fromAccountNo;
	
	private String finaceCustomerId;
	
	private String finaceCustomerName;

	//对账单下载新加的
	private String creditTradeId;

	private String tradeType;
	
	private String tradeStatus;
	
	private String businessSeq;
	
	private String outCustomerName;
	
	public String getCreditTradeId() {
		return creditTradeId;
	}
	
	public void setCreditTradeId(String creditTradeId) {
		this.creditTradeId = creditTradeId;
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
	
	public String getBusinessSeq() {
		return businessSeq;
	}
	
	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Date getTradeStartTime() {
		return tradeStartTime;
	}

	public void setTradeStartTime(Date tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}

	public String getOutOriginTradeNo() {
		return outOriginTradeNo;
	}

	public void setOutOriginTradeNo(String outOriginTradeNo) {
		this.outOriginTradeNo = outOriginTradeNo;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Date getTransferStartTime() {
		return transferStartTime;
	}

	public void setTransferStartTime(Date transferStartTime) {
		this.transferStartTime = transferStartTime;
	}

	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public String getFinaceCustomerId() {
		return finaceCustomerId;
	}

	public void setFinaceCustomerId(String finaceCustomerId) {
		this.finaceCustomerId = finaceCustomerId;
	}

	public String getFinaceCustomerName() {
		return finaceCustomerName;
	}

	public void setFinaceCustomerName(String finaceCustomerName) {
		this.finaceCustomerName = finaceCustomerName;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	
	
	

}

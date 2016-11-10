package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;
import java.util.Date;

public class CreditTradeFlowDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//转账ID
	
	private Date transferStartTime;//支付时间
    
    private String transferStatus;//支付状态
    
    private String fromAccountNo;//放款账户账号
    
    private String financeCustomerId;
    
    private String financeCustomerName;
    
    private String summaryId; //放款编号
    
    private String fee;
    
    private String outTradeNo;//外部交易编号
    
    private String transferType; //转账类型
    
    private Date tradeStartTime;//交易时间
    
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

	public Date getTransferStartTime() {
		return transferStartTime;
	}

	public void setTransferStartTime(Date transferStartTime) {
		this.transferStartTime = transferStartTime;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public String getFinanceCustomerId() {
		return financeCustomerId;
	}

	public void setFinanceCustomerId(String financeCustomerId) {
		this.financeCustomerId = financeCustomerId;
	}

	public String getFinanceCustomerName() {
		return financeCustomerName;
	}

	public void setFinanceCustomerName(String financeCustomerName) {
		this.financeCustomerName = financeCustomerName;
	}

	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public Date getTradeStartTime() {
		return tradeStartTime;
	}

	public void setTradeStartTime(Date tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}
    
    

}

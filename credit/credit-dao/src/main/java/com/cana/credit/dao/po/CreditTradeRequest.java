package com.cana.credit.dao.po;

import java.io.Serializable;

public class CreditTradeRequest implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int page = 1; //当前页数
	
	private int pageSize = 10; //每页记录数
	
	private String tradeType;

	private String tradeStartDate; //交易开始日期
	
	private String tradeEndDate; //交易结束日期
	
	private String summaryId; //汇总编号（支付时为放款编号，退款时为退款编号（即原单的放款编号））
	
	private String customerName; //采购商名称
	
	//退款新增
	private String refundStartDate; //退款开始日期
	
	
	private String orderNum; //订单编号
	
	private String tradeStatus;//交易状态
	
	private String transferType;//转账类型（用来确定退款时退款方名称）

	//对账单下载新增
	private boolean statementSpecificField;//对账单专用字段（因为对账单下载只查“放款”，“退款给资金方”）
		
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeStartDate() {
		return tradeStartDate;
	}

	public void setTradeStartDate(String tradeStartDate) {
		this.tradeStartDate = tradeStartDate;
	}

	public String getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRefundStartDate() {
		return refundStartDate;
	}

	public void setRefundStartDate(String refundStartDate) {
		this.refundStartDate = refundStartDate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getTradeEndDate() {
		return tradeEndDate;
	}

	public void setTradeEndDate(String tradeEndDate) {
		this.tradeEndDate = tradeEndDate;
	}

	public boolean isStatementSpecificField() {
		return statementSpecificField;
	}

	public void setStatementSpecificField(boolean statementSpecificField) {
		this.statementSpecificField = statementSpecificField;
	}
	
	

}

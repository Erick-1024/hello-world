package com.cana.yundaex.common.dto;

import java.io.Serializable;

public class YundaexRepaymentRecordDTO implements Serializable {

	private static final long serialVersionUID = -207239126185713612L;
	
	/**
	 * 站点编号
	 */
	private String stationNo;
	
	/**
	 * 站点名称
	 */
	private String stationName;
	
	/**
	 * 借款人姓名
	 */
	private String custName;
	
	/**
	 * 借款人身份证号
	 */
	private String custIdNo;
	
	/**
	 * 借据号
	 */
	private String putOutNo;
	
	/**
	 * 出账金额
	 */
	private long putOutAmt;
	
	/**
	 * 还款方式
	 */
	private String repayMode;
	
	/**
	 * 还款金额
	 */
	private long repayAmt;
	
	/**
	 * 还款日期
	 */
	private String repayDate;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustIdNo() {
		return custIdNo;
	}

	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}

	public String getPutOutNo() {
		return putOutNo;
	}

	public void setPutOutNo(String putOutNo) {
		this.putOutNo = putOutNo;
	}

	public String getRepayMode() {
		return repayMode;
	}

	public void setRepayMode(String repayMode) {
		this.repayMode = repayMode;
	}

	public long getPutOutAmt() {
		return putOutAmt;
	}

	public void setPutOutAmt(long putOutAmt) {
		this.putOutAmt = putOutAmt;
	}

	public long getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(long repayAmt) {
		this.repayAmt = repayAmt;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
}

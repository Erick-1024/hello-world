package com.cana.vbam.common.yundaex.dto.loanInfo;

import java.io.Serializable;

public class YundaexLoanInfoDTO implements Serializable{

	private static final long serialVersionUID = 2277434530389902538L;
	
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
	private String putOutAmt;
	
	/**
	 * 开始日期
	 */
	private String beginDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;

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

	public String getPutOutAmt() {
		return putOutAmt;
	}

	public void setPutOutAmt(String putOutAmt) {
		this.putOutAmt = putOutAmt;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}

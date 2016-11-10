package com.cana.yundaex.common.dto.monitor;

import java.io.Serializable;

public class YundaexMonitorImportDTO implements Serializable {

	private static final long serialVersionUID = -7179246256892439206L;

	/**
	 * 序号
	 */
	private String sequecnce;
	
	/**
	 * 站点编号
	 */
	private String stationNo;

	/**
	 * 站点负责人/公司名称
	 */
	private String stationName;

	/**
	 * 站点名称
	 */
	private String stationMgr;

	/**
	 * 站点经营地址-详细地址
	 */
	private String address;

	/**
	 * 加盟年限
	 */
	private String busiLimit;

	/**
	 * 保证金账户余额
	 */
	private String bailBalance;

	/**
	 * 韵达评价
	 */
	private String yundaexJudge;
	
	/**
	 * 网点数量
	 */
	private String stationAmount;
	
	/**
	 * 短期借款
	 */
	private String shortLoan;
	
	/**
	 * 借款期限
	 */
	private String loanLimit;
	
	/**
	 * 期限单位
	 */
	private String limitUnit;
	
	/**
	 * 借款类型
	 */
	private String loanType;
	
	/**
	 * 未通过原有
	 * @return
	 */
	private String notPassReason;

	public String getSequecnce() {
		return sequecnce;
	}

	public void setSequecnce(String sequecnce) {
		this.sequecnce = sequecnce;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStationMgr() {
		return stationMgr;
	}

	public void setStationMgr(String stationMgr) {
		this.stationMgr = stationMgr;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusiLimit() {
		return busiLimit;
	}

	public void setBusiLimit(String busiLimit) {
		this.busiLimit = busiLimit;
	}

	public String getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(String bailBalance) {
		this.bailBalance = bailBalance;
	}

	public String getYundaexJudge() {
		return yundaexJudge;
	}

	public void setYundaexJudge(String yundaexJudge) {
		this.yundaexJudge = yundaexJudge;
	}

	public String getStationAmount() {
		return stationAmount;
	}

	public void setStationAmount(String stationAmount) {
		this.stationAmount = stationAmount;
	}

	public String getShortLoan() {
		return shortLoan;
	}

	public void setShortLoan(String shortLoan) {
		this.shortLoan = shortLoan;
	}

	public String getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(String loanLimit) {
		this.loanLimit = loanLimit;
	}

	public String getLimitUnit() {
		return limitUnit;
	}

	public void setLimitUnit(String limitUnit) {
		this.limitUnit = limitUnit;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getNotPassReason() {
		return notPassReason;
	}

	public void setNotPassReason(String notPassReason) {
		this.notPassReason = notPassReason;
	}

	
}

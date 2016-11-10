package com.cana.yundaex.common.dto.apply;

import java.io.Serializable;

public class YdCustomerApplyRequestDTO implements Serializable {

	/**
	 * 客户额度申请的请求信息
	 */
	private static final long serialVersionUID = 1L;

	private String stationNo;// 站点编号
	private String stationName; // 站点名称
	private String stationMgr; // 站点负责人/公司名称
	private String custName; // 借款人姓名
	private String custIdno; // 借款人身份证号
	private String telephone; // 借款人手机号
	private String province; // 站点经营地址-省
	private String city; // 站点经营地址-市
	private String address; // 站点经营地址-详细地址
	private Long busiLimit; // 加盟年限
	private String regioncode; // 区域代码
	private Long applyCreditLimit;// 意向额度
	private String loanLimit; // 意向期限
	private String addCredit; // 增信方式
	private String repaymentType;// 还款方式
	private String explain; // 其它说明

	/**
	 * 签名
	 * 
	 */
	private String sign;
	
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

	public String getStationMgr() {
		return stationMgr;
	}

	public void setStationMgr(String stationMgr) {
		this.stationMgr = stationMgr;
	}


	public String getCustIdno() {
		return custIdno;
	}

	public void setCustIdno(String custIdno) {
		this.custIdno = custIdno;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getBusiLimit() {
		return busiLimit;
	}

	public void setBusiLimit(Long busiLimit) {
		this.busiLimit = busiLimit;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public Long getApplyCreditLimit() {
		return applyCreditLimit;
	}

	public void setApplyCreditLimit(Long applyCreditLimit) {
		this.applyCreditLimit = applyCreditLimit;
	}

	public String getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(String loanLimit) {
		this.loanLimit = loanLimit;
	}

	public String getAddCredit() {
		return addCredit;
	}

	public void setAddCredit(String addCredit) {
		this.addCredit = addCredit;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	
}

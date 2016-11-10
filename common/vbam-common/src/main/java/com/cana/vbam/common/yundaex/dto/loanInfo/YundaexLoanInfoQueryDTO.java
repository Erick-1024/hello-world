package com.cana.vbam.common.yundaex.dto.loanInfo;

import java.io.Serializable;

public class YundaexLoanInfoQueryDTO implements Serializable {

	private static final long serialVersionUID = -8316513955049067518L;
	
	/**
	 * 站点编号
	 */
	private String stationNo;
	
	/**
	 * 查询开始日期起日
	 */
	private String startBeginDate;
	
	/**
	 * 查询开始日期止日
	 */
	private String endBeginDate;
	
	/**
	 * 查询到期日期起日
	 */
	private String startExpireDate;
	
	/**
	 * 查询到期日期止日
	 */
	private String endExpireDate;
	
	/**
	 * 签名
	 */
	private String sign;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStartBeginDate() {
		return startBeginDate;
	}

	public void setStartBeginDate(String startBeginDate) {
		this.startBeginDate = startBeginDate;
	}

	public String getEndBeginDate() {
		return endBeginDate;
	}

	public void setEndBeginDate(String endBeginDate) {
		this.endBeginDate = endBeginDate;
	}

	public String getStartExpireDate() {
		return startExpireDate;
	}

	public void setStartExpireDate(String startExpireDate) {
		this.startExpireDate = startExpireDate;
	}

	public String getEndExpireDate() {
		return endExpireDate;
	}

	public void setEndExpireDate(String endExpireDate) {
		this.endExpireDate = endExpireDate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}

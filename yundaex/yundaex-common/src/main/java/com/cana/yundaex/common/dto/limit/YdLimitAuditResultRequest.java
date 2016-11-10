package com.cana.yundaex.common.dto.limit;

import java.io.Serializable;

public class YdLimitAuditResultRequest implements Serializable {

	/**
	 * 额度申请结果接口－请求参数
	 */
	private static final long serialVersionUID = 3327290756014179230L;

	private String stationNo;      //韵达站点编号(可以当做唯一标识)
	private String applyResult;    //审核结果
	private Long totalLimit;       //授信额度
	private String canaUrl;        //cana平台的链接地址
	
	private String sign;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getApplyResult() {
		return applyResult;
	}

	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}

	public Long getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Long totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getCanaUrl() {
		return canaUrl;
	}

	public void setCanaUrl(String canaUrl) {
		this.canaUrl = canaUrl;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}

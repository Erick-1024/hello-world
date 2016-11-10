package com.cana.yundaex.common.dto.limit;

import java.io.Serializable;

/**
 * 额度查询接口－请求参数
 * @author xiaoyu
 *
 */
public class YdQueryCreditLimitDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stationNo; //站点编号
	
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}

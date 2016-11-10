/**
 * 
 */
package com.cana.yundaex.common.dto;

import java.io.Serializable;

/**
 * 放款信息查询接口 接收韵达请求
 * 
 * @author guguanggong
 *
 */
public class YundaexLoanInfoListRequest implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * 
	 * @return
	 */
	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

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
}

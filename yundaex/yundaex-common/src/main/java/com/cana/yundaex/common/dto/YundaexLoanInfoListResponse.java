/**
 * 
 */
package com.cana.yundaex.common.dto;

import java.util.List;

/**
 * 放款信息查询接口 返回
 * 
 * @author guguanggong
 *
 */
public class YundaexLoanInfoListResponse extends YundaexBaseResponse {

	private static final long serialVersionUID = -7777552188921007725L;

	/**
	 * 站点编号
	 */
	private String stationNo;

	/**
	 * 记录数
	 */
	private String count;

	private List<YundaexLoanInfoResponse> loanInfo;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<YundaexLoanInfoResponse> getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(List<YundaexLoanInfoResponse> loanInfo) {
		this.loanInfo = loanInfo;
	}

}

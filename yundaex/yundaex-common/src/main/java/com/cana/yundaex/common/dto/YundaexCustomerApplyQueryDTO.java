/**
 * 
 */
package com.cana.yundaex.common.dto;

/**
 * 韵达项目 客户申请查询DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexCustomerApplyQueryDTO {

	/**
	 * 站点编号
	 */
	private String stationNo;

	/**
	 * 是否有网点信息（Y:有 N:没有）
	 */
	private String whetherStationInfo;

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getWhetherStationInfo() {
		return whetherStationInfo;
	}

	public void setWhetherStationInfo(String whetherStationInfo) {
		this.whetherStationInfo = whetherStationInfo;
	}

}

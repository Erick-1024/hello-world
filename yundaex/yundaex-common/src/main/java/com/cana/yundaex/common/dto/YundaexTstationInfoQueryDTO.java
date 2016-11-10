/**
 * 
 */
package com.cana.yundaex.common.dto;

import java.io.Serializable;

/**
 * 网点数据 查询DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexTstationInfoQueryDTO implements Serializable {

	private static final long serialVersionUID = 1137505064143055385L;

	private String id;

	/**
	 * 网点代码
	 */
	private String stationNo;

	/**
	 * 客户申请 创建时间
	 */
	private String createTime;

	/**
	 * 查询起始日 YYYY-MM
	 */
	private String startDate;

	/**
	 * 查询终止日 YYYY-MM
	 */
	private String endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

}

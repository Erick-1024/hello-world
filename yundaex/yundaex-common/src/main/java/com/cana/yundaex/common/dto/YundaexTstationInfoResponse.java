/**
 * 
 */
package com.cana.yundaex.common.dto;

import java.util.List;

/**
 * 韵达项目 网点数据返回结果
 * 
 * @author guguanggong
 *
 */
public class YundaexTstationInfoResponse extends YundaexBaseResponse {

	private static final long serialVersionUID = -1927084730214603624L;

	/**
	 * 记录数
	 */
	private Integer count;

	/**
	 * 一段时间内的网点信息
	 */
	private List<YundaexTstationInfoDTO> operataDatas;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<YundaexTstationInfoDTO> getOperataDatas() {
		return operataDatas;
	}

	public void setOperataDatas(List<YundaexTstationInfoDTO> operataDatas) {
		this.operataDatas = operataDatas;
	}
}

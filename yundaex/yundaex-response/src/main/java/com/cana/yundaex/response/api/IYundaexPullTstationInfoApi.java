/**
 * 
 */
package com.cana.yundaex.response.api;

import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoResponse;

/**
 * 韵达项目-网点信息同步API
 * @author guguanggong
 *
 */
public interface IYundaexPullTstationInfoApi {

	/**
	 * 获取网点信息
	 * @param stationNo 网点编号
	 * @param startDate 查询起始日 YYYY-MM
	 * @param endDate 查询终止日 YYYY-MM
	 * @return
	 */
	public YundaexTstationInfoResponse getTstationInfoByParam(YundaexTstationInfoQueryDTO tstationInfoDTO); 
}

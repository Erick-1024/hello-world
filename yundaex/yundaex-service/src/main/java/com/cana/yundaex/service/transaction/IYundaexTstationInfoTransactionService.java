/**
 * 
 */
package com.cana.yundaex.service.transaction;

import java.util.List;

import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoResponse;
import com.cana.yundaex.common.enums.StationInfoType;
import com.cana.yundaex.common.enums.YundaexPullStationType;
import com.cana.yundaex.common.enums.YundaexStationDataStatus;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.travelzen.framework.core.common.ReturnClass;

/**
 * 操作 韵达信息网点信息
 * @author guguanggong
 */
public interface IYundaexTstationInfoTransactionService {
	
	/**
	 * 增加拉取数据网点经营数据任务
	 */
	public void stationPullTask(YundaexCustomerApply yundaexCustomerApply);
	
	/**
	 * 保存拉取到的所有数据
	 * @param infoDTOs
	 */
	public ReturnClass saveTstationInfo(YundaexTstationInfoQueryDTO infoQueryDTO, YundaexTstationInfoResponse returnData);
	
	/**
	 * 修改拉取数据方式
	 * @param stationNo
	 * @param returnMap
	 */
	public void updateCustomerApplyByStationNo(String stationNo, StationInfoType stationInfoType, String returnMsg);
	
	/**
	 * 生成同步网点数据任务
	 * @param queryDTOs
	 */
	public void createStationPull(List<YundaexTstationInfoQueryDTO> queryDTOs, YundaexPullStationType type);
	
	/**
	 * 保存同步网点数据
	 * @param infoQueryDTO
	 * @param returnData
	 * @return
	 */
	public ReturnClass synTstationInfo(YundaexTstationInfoQueryDTO infoQueryDTO, YundaexTstationInfoResponse returnData);
	
	/**
	 * 更新网点数据状态
	 * @param batchDate 拉取数据时间 YYYY-MM
	 * @param stationNo 站点编号
	 * @param stationDataStatus 拉取数据状态
	 * @param extraData 扩展数据 json串
	 */
	public void updateStationRecord(String batchDate, String stationNo, YundaexStationDataStatus stationDataStatus, String extraData);

	/**
	 * 
	 * @param batchDate
	 * @param stationNo
	 * @param stationDataStatus
	 * @param extraData
	 * @param stationInfoType
	 * @param returnMsg
	 */
	public void updateCustomerApplyAndStationRecord(String batchDate, String stationNo, YundaexStationDataStatus stationDataStatus, String extraData, StationInfoType stationInfoType, String returnMsg);
}

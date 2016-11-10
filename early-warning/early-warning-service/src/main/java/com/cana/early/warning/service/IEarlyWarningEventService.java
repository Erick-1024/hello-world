package com.cana.early.warning.service;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

public interface IEarlyWarningEventService {

	/**
	 * 新增人工生成的事件
	 * @param earlyWarningManualEventDTO
	 */
	public void addEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO);

	/**
	 * 查询某个事件的详情
	 * @param earlywarningEventId 预警事件ID
	 * @return
	 */
	public EarlyWarningEventDetailDTO queryEarlyWarningEventDetail(String earlywarningEventId);
	
	/**
	 * 查询某个事件的详情（韵达）
	 * @param earlywarningEventId 预警事件ID
	 * @return
	 */
	public EarlyWarningEventDetailDTO queryYundaexEarlyWarningEventDetail(String earlywarningEventId);
	
	/**
	 * 查询预警种类汇总列表
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	public EarlyWaringEventTypeListResponse queryEarlyWarningTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest);

	/**
	 * 查询预警事件历史
	 * @param earlyWarningEventHistoryRequest
	 * @return
	 */
	public ListResult<EarlyWarningEventHistoryResponse> queryEarlyWarningEventHistory(EarlyWarningEventHistoryRequest earlyWarningEventHistoryRequest);
	
	/**
	 * 获取预计的预警等级
	 * @param earlyWarningEventDetailDTO 预警事件ID
	 * @param type 类型（增加 or 解除）
	 * @param companyName
	 * @param outCustomerName
	 * @return
	 */
	public Map<EarlywarningLevel, String> getPredictEarlyWarningLevel(EarlyWarningEventDetailDTO earlyWarningEventDetailDTO, EarlywarningEventAction earlywarningEventAction, String companyName, String outCustomerName);
	
	/**
	 * 将多个法院被执行金额或其他、负面信息转化为一个预警事件（其他和负面信息归为一类）,系统事件根据子类型分开
	 * @param state 事件状态
	 * @param productId 产品ID
	 * @param memberId 用户ID
	 * @param outCustomerId 外部平台客户ID
	 * @return
	 */
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId);
	
}

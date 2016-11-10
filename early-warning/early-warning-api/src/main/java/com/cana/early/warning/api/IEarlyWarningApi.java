package com.cana.early.warning.api;

import java.util.List;
import java.util.Map;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.AuditEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.CanelEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerExcelDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningLevelChangeHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;

public interface IEarlyWarningApi {

	/**
	 * 新增人工生成的事件
	 * @param earlyWarningManualEventDTO
	 * @param institution
	 */
	public void addEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO);
	
	/**
	 * 取消预警事件
	 * @param canelEarlyWarningEventRequest
	 */
	public void canaelEarlyWarningEvent(CanelEarlyWarningEventRequest canelEarlyWarningEventRequest);
	
	/**
	 * 对审核记录进行审核
	 * @param auditEarlyWarningEventRequest
	 */
	public void auditEarlyWarningEventReview(AuditEarlyWarningEventRequest auditEarlyWarningEventRequest);
	
	public ListResult<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCustomerRequest);
	
	public ListResult<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
	/**
	 * 获取当前最新的预警等级标准
	 * @param earlywarningLevelStr 预警等级
	 * @return
	 */
	public Map<String, Long> queryEarlyWarningStandard(String earlywarningLevelStr);
	
	/**
	 * 获取预计的预警等级
	 * @param earlyWarningEventDetailDTO 预警事件
	 * @param type 类型（增加 or 解除）
	 * @param companyName 公司名称
	 * @param outCustomerName 外部客户名称
	 * @return
	 */
	public Map<EarlywarningLevel, String> getPredictEarlyWarningLevel(EarlyWarningEventDetailDTO earlyWarningEventDetailDTO, String type, String companyName, String outCustomerName);
	
	/**
	 * 查询某个事件的详情
	 * @param earlywarningEventId 预警事件ID
	 * @return
	 */
	public EarlyWarningEventDetailDTO queryEarlyWarningEventDetail(String earlywarningEventId);
	
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
	 * 获取预警事件审核列表
	 * @param earlyWarningEventReviewListRequest
	 * @return
	 */
	public ListResult<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReview(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest);
	
	/**
	 * 查询预警审核信息
	 * @param earlywarningEventReviewId 预警审核ID
	 * @return 预警审核信息
	 */
	public EarlyWarningEventReviewDTO queryEarlyWarningEventReview(String earlywarningEventReviewId);
	
	/**
	 * 查询预警流水列表
	 * @param earlyWarningLevelChangeHistoryRequest
	 * @return
	 */
	public ListResult<EarlyWarningLevelChangeHistoryDTO> queryEarlyWarningLevelChangeHistory(EarlyWarningLevelChangeHistoryRequest earlyWarningLevelChangeHistoryRequest);
	
	/**
	 * 将多个法院被执行金额或其他、负面信息转化为一个预警事件（其他和负面信息归为一类）,系统事件根据子类型分开
	 * @param state 事件状态
	 * @param productId 产品ID
	 * @param memberId 用户ID
	 * @param outCustomerId 外部平台客户ID
	 * @return
	 */
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId);
	
	public List<EarlyWarningCustomerExcelDTO> getEarlyWarningCustomerExcel(EarlyWarningCommonRequest earlyWarningCustomerRequest);
	
}

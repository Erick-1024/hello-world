package com.cana.early.warning.api;

import java.math.BigDecimal;
import java.util.List;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.dto.EarlyWaringEventTypeListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCommonRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerInformationResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningCustomerResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventHistoryResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventReviewListResponse;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.dto.YundaexEarlyWarningExcelDTO;

/**
 * 韵达预警api
 * @author sugar
 *
 */
public interface IYundaexEarlyWarningApi {
	
	/**
	 *  查询【贷后预警】列表页
	 * @param earlyWarningCustomerRequest
	 * @return
	 */
	public ListResult<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCustomerRequest);
	
	/**
	 * 导出预警信息
	 * @param earlyWarningCustomerRequest
	 * @return
	 */
	public List<YundaexEarlyWarningExcelDTO> getEarlyWarningExcel(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
	/**
	 * 查询【预警信息】列表页
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	public ListResult<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
	/**
	 * 查询 【预警信息】->【调整预警信息】列表页 && 【新增预警事件】列表页
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	public EarlyWaringEventTypeListResponse queryEarlyWarningTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest);
	
	/**
	 * 一个事件类型一行，将系统事件再根据子类型分开
	 * @param state 事件状态
	 * @param productId 产品ID
	 * @param memberId 用户ID
	 * @param outCustomerId 外部平台客户ID
	 * @return
	 */
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId);
	
	/**
	 * 获取预警事件审核列表
	 * @param earlyWarningEventReviewListRequest
	 * @return
	 */
	public ListResult<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReview(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest);
	
	/**
	 * 查询某个事件的详情/审核
	 * @param earlywarningEventId 预警事件ID
	 * @return
	 */
	public EarlyWarningEventDetailDTO queryEarlyWarningEventDetail(String earlywarningEventId);
	
	/**
	 * 查询预警审核信息里的“附加数据”
	 * @param earlywarningEventReviewId 预警审核ID
	 * @return 预警审核信息
	 */
	public EarlyWarningEventReviewDTO queryEarlyWarningEventReview(String earlywarningEventReviewId);
	
	/**
	 * 新增人工生成的事件
	 * @param earlyWarningManualEventDTO
	 * @param institution
	 */
	public void addEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO);
	
	/**
	 * 查询预警事件历史
	 * @param earlyWarningEventHistoryRequest
	 * @return
	 */
	public ListResult<EarlyWarningEventHistoryResponse> queryEarlyWarningEventHistory(EarlyWarningEventHistoryRequest earlyWarningEventHistoryRequest);

	/**
	 * 计算韵达系统预警事件
	 * @param recandsendGrowthRate  揽派件增长率
	 * @param dayBail		日资金需求
	 * @param score			韵达评分
	 * @param bailBalance	保证金余额
	 * @param shortLoan		短期借款
	 * @param netCashflow	净现金流
	 * @param finalLimit	最大授信金额
	 * @param overDues		逾期次数
	 * @param userId		cana用户id
	 * @param stationNo		网点编号（外部客户id）
	 * @param stationName	网点名称
	 */
	public void reckonYundaexFirstApplyEarlyWarning(BigDecimal recandsendGrowthRate, BigDecimal dayBail,
			BigDecimal score, Long bailBalance, Long shortLoan, BigDecimal netCashflow, Long finalLimit, int overDues,
			String userId, String stationNo, String stationName);
	
	
}

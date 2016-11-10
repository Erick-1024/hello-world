package com.cana.early.warning.server.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.early.warning.api.IYundaexEarlyWarningApi;
import com.cana.early.warning.service.IEarlyWarningCustomerService;
import com.cana.early.warning.service.IEarlyWarningEventReviewService;
import com.cana.early.warning.service.IEarlyWarningEventService;
import com.cana.early.warning.service.IEarlyWarningLevelChangeHistoryService;
import com.cana.early.warning.service.IYundaexEarlyWarningService;
import com.cana.early.warning.service.transaction.IEarlyWarningEventReviewTransactionService;
import com.cana.early.warning.service.transaction.IEarlyWarningEventTransactionService;
import com.cana.early.warning.service.transaction.IYdEarlyWarningEventTransactionService;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.early.warning.consts.EarlyWarningConsts;
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
import com.cana.vbam.common.early.warning.dto.EarlyWarningSystemEventRepresent;
import com.cana.vbam.common.early.warning.dto.YundaexEarlyWarningExcelDTO;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.EarlywarningReviewState;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventCategory;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventSubCategory;
import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;
import com.cana.yundaex.common.util.Constants;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

public class YundaexEarlyWarningApiImpl implements IYundaexEarlyWarningApi {

	@Resource
	private IEarlyWarningEventService earlyWarningEventServiceImpl;
	
	@Resource
	private IYundaexEarlyWarningService yundaexEarlyWarningServiceImpl;
	
	@Resource
	private IEarlyWarningEventReviewTransactionService earlyWarningEventReviewTransactionServiceImpl;
	
	@Resource
	private IEarlyWarningEventTransactionService earlyWarningEventTransactionServiceImpl;
	
	@Resource
	private IEarlyWarningCustomerService earlywarningCustomerServiceImpl;
	
	@Resource
	private IEarlyWarningEventReviewService earlyWarningEventReviewServiceImpl;
	
	@Resource
	private IEarlyWarningLevelChangeHistoryService earlyWarningLevelChangeHistoryServiceImpl;
	
	@Resource
	private IYdEarlyWarningEventTransactionService ydEarlyWarningEventTransactionService;
	
	/**
	 * 查询【贷后预警】列表页
	 * @param earlyWarningCustomerRequest
	 * @return
	 */
	@Override
	public ListResult<EarlyWarningCustomerResponse> queryEarlyWarningCustomer(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		if(StringUtils.isNotBlank(earlyWarningCommonRequest.getEarlyWarningLevel()) && !EnumUtils.isValidEnum(EarlywarningLevel.class, StringUtils.trimToNull(earlyWarningCommonRequest.getEarlyWarningLevel())))
			throw WebException.instance(ReturnCode.TP4010);
		earlyWarningCommonRequest.setEarlyWarningLevel(StringUtils.trimToNull(earlyWarningCommonRequest.getEarlyWarningLevel()));
		return earlywarningCustomerServiceImpl.queryEarlyWarningCustomer(earlyWarningCommonRequest);
	}
	
	@Override
	public List<YundaexEarlyWarningExcelDTO> getEarlyWarningExcel(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		if(StringUtils.isNotBlank(earlyWarningCommonRequest.getEarlyWarningLevel()) && !EnumUtils.isValidEnum(EarlywarningLevel.class, StringUtils.trimToNull(earlyWarningCommonRequest.getEarlyWarningLevel())))
			throw WebException.instance(ReturnCode.TP4010);
		earlyWarningCommonRequest.setEarlyWarningLevel(StringUtils.trimToNull(earlyWarningCommonRequest.getEarlyWarningLevel()));
		earlyWarningCommonRequest.setPage(0);
		List<EarlyWarningCustomerResponse> earlyWarningCustomerResponses = earlywarningCustomerServiceImpl.queryEarlyWarningCustomer(earlyWarningCommonRequest).getData();
		List<YundaexEarlyWarningExcelDTO> returnValue = new ArrayList<YundaexEarlyWarningExcelDTO>();
		for (EarlyWarningCustomerResponse earlyWarningCustomerResponse : earlyWarningCustomerResponses) {
			YundaexEarlyWarningExcelDTO excelDTO = new YundaexEarlyWarningExcelDTO();
			BeanUtils.copyProperties(earlyWarningCustomerResponse, excelDTO);
			excelDTO.setLimit(earlyWarningCustomerResponse.getLimit() == null ? null : MoneyUtil.formatMoney(earlyWarningCustomerResponse.getLimit()));
			excelDTO.setResidualLimit(earlyWarningCustomerResponse.getResidualLimit() == null ? null : MoneyUtil.formatMoney(earlyWarningCustomerResponse.getResidualLimit()));
			List<EarlyWarningEventDetailDTO> earlyWarningEventDetailDTOs = getSingleEarlyWarningEventCollect(Arrays.asList(new String[]{EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()}), earlyWarningCommonRequest.getProductId(), earlyWarningCustomerResponse.getMemberId(), earlyWarningCustomerResponse.getOutCustomerId());
			for (EarlyWarningEventDetailDTO earlyWarningEventDetailDTO : earlyWarningEventDetailDTOs) {
				YundaexEarlywarningEventCategory category = YundaexEarlywarningEventCategory.valueOf(earlyWarningEventDetailDTO.getType());
				switch (category) {
				case DEPARTMENTS_PUNISH:
					excelDTO.setActualDepartmentsPunish((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardDepartmentsPunish((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case BUSINESS_NATURE_CHANGE:
					excelDTO.setActualBusinessNatureChange((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardBusinessNatureChange((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case OWNERSHIP_STRUCTURE_CHANGE:
					excelDTO.setActualOwnershipStructureChange((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardOwnershipStructureChange((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case NEGATIVE_NEWS:
					excelDTO.setActualNegativeNews((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardNegativeNews((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case LITIGATION_DISPUTE:
					excelDTO.setActualLitigationDispute((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardLitigationDispute((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case SHORT_TERM_LOAN:
					excelDTO.setActualShortTermLoan((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardShortTermLoan((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case CUSTOMER_ATTITUDE:
					excelDTO.setActualCustomerAttitude((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardCustomerAttitude((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case OTHER:
					excelDTO.setActualOther((String)earlyWarningEventDetailDTO.getRepresent());
					excelDTO.setStandardOther((String)earlyWarningEventDetailDTO.getRepresent() + "(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
					break;
				case SYSTEM:
					YundaexEarlywarningEventSubCategory subCategory = YundaexEarlywarningEventSubCategory.valueOf(earlyWarningEventDetailDTO.getSubType());
					switch (subCategory) {
					case RECANDSEND_GROWTHRATE:
						excelDTO.setActualRecandsendGrowthrate(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardRecandsendGrowthrate(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case BAILBALANCE_DAY_REQUIREMENTS:
						excelDTO.setActualBailbalanceDayRequirements(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardBailbalanceDayRequirements(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case YUNDAEXGRADE:
						excelDTO.setActualYundaexgrade(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardYundaexgrade(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case BAILBALANCE:
						excelDTO.setActualBailbalance(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardBailbalance(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case NET_CASHFLOW_GROWTHRATE:
						excelDTO.setActualNetCashflowGrowthrate(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardNetCashflowGrowthrate(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case NET_CASHFLOW:
						excelDTO.setActualNetCashflow(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardNetCashflow(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case CREDIT_LIMIT_GROWTH:
						excelDTO.setActualCreditLimitGrowth(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardCreditLimitGrowth(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					case OVERDUES:
						excelDTO.setActualOverdues(earlyWarningEventDetailDTO.getExtraData().toString());
						excelDTO.setStandardOverdues(((EarlyWarningSystemEventRepresent)earlyWarningEventDetailDTO.getRepresent()).getStandard()+"(" + EarlywarningLevel.valueOf(earlyWarningEventDetailDTO.getLevel()).desc() + ")");
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
			returnValue.add(excelDTO);
		}
		return returnValue;
	}
	
	/**
	 * 【贷后预警】明细
	 */
	@Override
	public List<EarlyWarningEventDetailDTO> getSingleEarlyWarningEventCollect(List<String> state, String productId, String memberId, String outCustomerId) {
		if(StringUtils.isBlank(memberId) || StringUtils.isBlank(outCustomerId))
			throw WebException.instance(ReturnCode.TP4011);
		if(state != null)
			for (String s : state)
				if(!EnumUtils.isValidEnum(EarlywarningEventState.class, s))
					throw WebException.instance(ReturnCode.TP4012);
		return yundaexEarlyWarningServiceImpl.getSingleEarlyWarningEventCollect(state, productId, memberId, outCustomerId);
	}
	
	/**
	 * 查询【预警信息】列表页
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	@Override
	public ListResult<EarlyWarningCustomerInformationResponse> queryEarlyWarningCustomerInformation(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		if(StringUtils.isNotBlank(earlyWarningCommonRequest.getEarlyWarningLevel()) && !EnumUtils.isValidEnum(EarlywarningLevel.class, StringUtils.trimToNull(earlyWarningCommonRequest.getEarlyWarningLevel())))
			throw WebException.instance(ReturnCode.TP4010);
		earlyWarningCommonRequest.setEarlyWarningLevel(StringUtils.trimToNull(earlyWarningCommonRequest.getEarlyWarningLevel()));
		return earlywarningCustomerServiceImpl.queryEarlyWarningCustomerInformation(earlyWarningCommonRequest);
	}
	
	/**
	 * 查询 【预警信息】->【调整预警信息】列表页 && 【新增预警事件】列表页
	 * @param earlyWarningCommonRequest
	 * @return
	 */
	@Override
	public EarlyWaringEventTypeListResponse queryEarlyWarningTypeList(EarlyWarningCommonRequest earlyWarningCommonRequest) {
		if((StringUtils.isBlank(earlyWarningCommonRequest.getCompanyName()) || StringUtils.isBlank(earlyWarningCommonRequest.getOutCustomerName())) && (StringUtils.isBlank(earlyWarningCommonRequest.getMemberId()) || StringUtils.isBlank(earlyWarningCommonRequest.getOutCustomerId())))
			throw WebException.instance(ReturnCode.TP4013);
		return earlyWarningEventServiceImpl.queryEarlyWarningTypeList(earlyWarningCommonRequest);
	}
	
	/**
	 * 获取预警事件审核列表
	 * @param earlyWarningEventReviewListRequest
	 * @return
	 */
	@Override
	public ListResult<EarlyWarningEventReviewListResponse> queryEarlyWarningEventReview(EarlyWarningEventReviewListRequest earlyWarningEventReviewListRequest) {
		earlyWarningEventReviewListRequest.setCompanyName(StringUtils.trimToNull(earlyWarningEventReviewListRequest.getCompanyName()));
		checkDate(earlyWarningEventReviewListRequest.getAuditTimeStart(), earlyWarningEventReviewListRequest.getAuditTimeEnd());
		String earlywarningLevel = StringUtils.trimToNull(earlyWarningEventReviewListRequest.getEarlywarningLevel());
		if(earlywarningLevel != null && !earlywarningLevel.equals("normal") && !EnumUtils.isValidEnum(EarlywarningLevel.class, earlywarningLevel))
				throw WebException.instance(ReturnCode.TP4010);
		earlyWarningEventReviewListRequest.setEarlywarningLevel(earlywarningLevel);
		String earlywarningEventAction = StringUtils.trimToNull(earlyWarningEventReviewListRequest.getEarlywarningEventAction());
		if(earlywarningEventAction != null && !EnumUtils.isValidEnum(EarlywarningEventAction.class, earlywarningEventAction))
			throw WebException.instance(ReturnCode.TP4019);
		earlyWarningEventReviewListRequest.setEarlywarningEventAction(earlywarningEventAction);
		String earlywarningReviewState = StringUtils.trimToNull(earlyWarningEventReviewListRequest.getEarlywarningReviewState());
		if(earlywarningReviewState != null && !EnumUtils.isValidEnum(EarlywarningReviewState.class, earlywarningReviewState))
			throw WebException.instance(ReturnCode.TP4022);
		earlyWarningEventReviewListRequest.setEarlywarningReviewState(earlywarningReviewState);
		return earlyWarningEventReviewServiceImpl.queryEarlyWarningEventReview(earlyWarningEventReviewListRequest);
	}
	
	/**
	 * 查询某个事件的详情/审核
	 * @param earlywarningEventId 预警事件ID
	 * @return
	 */
	@Override
	public EarlyWarningEventDetailDTO queryEarlyWarningEventDetail(String earlywarningEventId) {
		if(StringUtils.isBlank(earlywarningEventId))
			throw WebException.instance(ReturnCode.TP4008);
		return earlyWarningEventServiceImpl.queryYundaexEarlyWarningEventDetail(earlywarningEventId);
	}
	
	/**
	 * 查询预警审核信息里的“附加数据”
	 * @param earlywarningEventReviewId 预警审核ID
	 * @return 预警审核信息
	 */
	@Override
	public EarlyWarningEventReviewDTO queryEarlyWarningEventReview(String earlywarningEventReviewId) {
		if(StringUtils.isBlank(earlywarningEventReviewId))
			throw WebException.instance(ReturnCode.TP4008);
		return earlyWarningEventReviewServiceImpl.queryEarlyWarningEventReview(earlywarningEventReviewId);
	}

	/**
	 * 新增人工生成的事件
	 * @param earlyWarningManualEventDTO
	 * @param institution
	 */
	@Override
	public void addEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		checkManualEvent(earlyWarningManualEventDTO);
		earlyWarningEventServiceImpl.addEarlyWarningManualEvent(earlyWarningManualEventDTO);
	}
	
	/**
	 * 查询预警事件历史
	 * @param earlyWarningEventHistoryRequest
	 * @return
	 */
	@Override
	public ListResult<EarlyWarningEventHistoryResponse> queryEarlyWarningEventHistory(EarlyWarningEventHistoryRequest earlyWarningEventHistoryRequest) {
		if(StringUtils.isBlank(earlyWarningEventHistoryRequest.getMemberId()))
			throw WebException.instance(ReturnCode.TP4011);
		
		if(StringUtils.isNotBlank(earlyWarningEventHistoryRequest.getEarlywarningType()) && !EnumUtils.isValidEnum(YundaexEarlywarningEventCategory.class, earlyWarningEventHistoryRequest.getEarlywarningType()))
			throw WebException.instance(ReturnCode.TP4003);
		
		checkDate(earlyWarningEventHistoryRequest.getEntryReviewTimeStart(), earlyWarningEventHistoryRequest.getEntryReviewTimeEnd());
		checkDate(earlyWarningEventHistoryRequest.getOccurTimeStart(), earlyWarningEventHistoryRequest.getOccurTimeEnd());
		return earlyWarningEventServiceImpl.queryEarlyWarningEventHistory(earlyWarningEventHistoryRequest);
	}
	
	private void checkManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		if(!EnumUtils.isValidEnum(YundaexEarlywarningEventCategory.class, earlyWarningManualEventDTO.getEarlywarningEventCategory()))
			throw WebException.instance(ReturnCode.TP4003);
		if(!EnumUtils.isValidEnum(YundaexEarlywarningEventSubCategory.class, earlyWarningManualEventDTO.getEarlywarningEventSubCategory()))
			throw WebException.instance(ReturnCode.TP4026);
		
		if(!EarlyWarningConsts.isSubCategoryBindLevel(YundaexEarlywarningEventSubCategory.valueOf(earlyWarningManualEventDTO.getEarlywarningEventSubCategory())))
			try {
				EarlywarningLevel.valueOf(earlyWarningManualEventDTO.getEarlywarningLevel());
			} catch (Exception e) {
				throw WebException.instance(ReturnCode.TP4005);
			}
		if(StringUtils.isBlank(earlyWarningManualEventDTO.getCompanyName()) || StringUtils.isBlank(earlyWarningManualEventDTO.getOutCustomerName()))
			throw WebException.instance(ReturnCode.TP4025);
	}

	private void checkDate(Date start, Date end) {
		if(start != null) {
			if(start.after(new Date()))
				throw WebException.instance(ReturnCode.TP4020);
			if(end != null && start.after(end))
				throw WebException.instance(ReturnCode.TP4021);
		}
	}

	@Override
	public void reckonYundaexFirstApplyEarlyWarning(BigDecimal recandsendGrowthRate, BigDecimal dayBail,
			BigDecimal score, Long bailBalance, Long shortLoan, BigDecimal netCashflow, Long finalLimit, int overDues,
			String userId, String stationNo, String stationName) {
		MonitorMetricYundaDTO monitorMetricData = new MonitorMetricYundaDTO();
		monitorMetricData.setYundaexGrade(score);
		monitorMetricData.setRecandsendGrowthrate(recandsendGrowthRate);
		monitorMetricData.setBailBalance(new BigDecimal(bailBalance));
		monitorMetricData.setShortLoan(new BigDecimal(shortLoan));
		monitorMetricData.setDayRequirements(dayBail.multiply(new BigDecimal(100)));
		monitorMetricData.setNetCashflow(netCashflow.multiply(new BigDecimal(100)));
		monitorMetricData.setCreditLimit(new BigDecimal(finalLimit));
		monitorMetricData.setOverdues(new BigDecimal(overDues));
		ydEarlyWarningEventTransactionService.reckonYdEarlyWarningSystemEvent(overDues, monitorMetricData, null, userId, stationNo, Constants.YUNDAEX_ASSET_PROJECT_ID, stationName, true);
	}

}

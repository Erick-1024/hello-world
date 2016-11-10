package com.cana.early.warning.service.transaction.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.gen.EarlywarningCustomerMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventReviewMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningSystemEventGenerateRecordMapper;
import com.cana.early.warning.dao.po.EarlywarningCustomerExample;
import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.early.warning.dao.po.EarlywarningEventExample;
import com.cana.early.warning.dao.po.EarlywarningEventReview;
import com.cana.early.warning.dao.po.EarlywarningSystemEventGenerateRecord;
import com.cana.early.warning.service.transaction.IEarlyWarningEventTransactionService;
import com.cana.early.warning.service.utils.EarlyWarningProperties;
import com.cana.early.warning.service.utils.IEarlyWarningServiceHelper;
import com.cana.vbam.common.early.warning.dto.CanelEarlyWarningEventRequest;
import com.cana.vbam.common.early.warning.dto.EarlyWarningManualEventDTO;
import com.cana.vbam.common.early.warning.dto.EarlyWarningSystemEventRepresent;
import com.cana.vbam.common.early.warning.dto.EarlywarningLockCustomerRequest;
import com.cana.vbam.common.early.warning.dto.EarlywarningSystemEventHandleResult;
import com.cana.vbam.common.early.warning.dto.MonitorDataEarlyWarningExtra;
import com.cana.vbam.common.early.warning.dto.OverdueDataEarlyWarningExtra;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventAction;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventSubCategory;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.EarlywarningReviewState;
import com.cana.vbam.common.report.dto.MonitorMetricData;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class EarlyWarningEventTransactionServiceImpl implements IEarlyWarningEventTransactionService {

	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private IEarlyWarningServiceHelper earlywarningServiceHelper;
	
	@Resource
	private EarlywarningEventMapper earlywarningEventMapper;
	
	@Resource
	private EarlywarningSystemEventGenerateRecordMapper earlywarningSystemEventGenerateRecordMapper;
	
	@Resource
	private EarlywarningEventReviewMapper earlywarningEvnetReviewMapper;
	
	@Resource
	private EarlywarningCustomerMapper earlywarningCustomerMapper;
	
	private Gson gson = new Gson();
	
	@Override
	public void reckonEarlyWarningSystemEvent(int overduePlans, int continueOverduePlans, List<MonitorMetricData> monitorMetricDatas, String memberId, String outCustomerId, String productId, String companyName) {
		EarlywarningLockCustomerRequest earlywarningLockCustomerRequest = earlywarningServiceHelper.generateEarlywarningLockCustomerRequest(productId, memberId, companyName, outCustomerId);
		// 预警汇总表判断
		earlywarningServiceHelper.addOrlockCustomer(earlywarningLockCustomerRequest);
		
		// 处理本次的系统预警事件（判断预警等级，并更新系统预警记录）
		handleEarlyWarningSystemEvent(overduePlans, continueOverduePlans, monitorMetricDatas, memberId, outCustomerId, productId, companyName);
		
		// 触发预警等级重新计算
		earlywarningServiceHelper.updateCustomerEarlywarningLevel(earlywarningLockCustomerRequest);
		
		// 新增系统预警生成记录
		addearlywarningSystemEventGenerateRecord(productId, memberId, outCustomerId, companyName);
	}

	@Override
	public void submitEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		String productId = earlyWarningManualEventDTO.getProductId();
		String financeId = earlyWarningManualEventDTO.getFinanceId();
		String outCustomerId = earlyWarningManualEventDTO.getOutCustomerId();
		if(!isExistEarlyWarningCustomer(productId, financeId, outCustomerId))
			earlywarningServiceHelper.addCustomer(earlywarningServiceHelper.generateEarlywarningLockCustomerRequest(productId, financeId, earlyWarningManualEventDTO.getCompanyName(), outCustomerId));
		// 保存新增待审核记录
		addEarlyWarningEventReview(addNewEarlyWarningManualEvent(earlyWarningManualEventDTO), EarlywarningEventAction.add, earlyWarningManualEventDTO.getReviewExtra());
		
	}

	@Override
	public void cancelEarlyWarningEvent(CanelEarlyWarningEventRequest canelEarlyWarningEventRequest) {
		// 对事件加锁
		EarlywarningEvent earlywarningEvent = lockEarlyWarningEvent(canelEarlyWarningEventRequest.getEarlywarningEventId());
		// 状态判断
		if(!earlywarningEvent.getState().equals(EarlywarningEventState.effective.name()))
			throw WebException.instance(ReturnCode.TP4007);
		// 更新事件记录
		updateCanelEarlyWarningEvent(canelEarlyWarningEventRequest.getUserId(), canelEarlyWarningEventRequest.getRealName(), earlywarningEvent);
		//保存取消待审核记录
		addEarlyWarningEventReview(earlywarningEvent, EarlywarningEventAction.cancel, canelEarlyWarningEventRequest.getCancelExtra());
	}
	
	private void handleEarlyWarningSystemEvent(int overduePlans, int continueOverduePlans, List<MonitorMetricData> monitorMetricDatas, String memberId, String outCustomerId, String productId, String companyName) {
		updateEarlyWarningSystemEvent(handleMonitorData(monitorMetricDatas, EarlywarningEventSubCategory.COUNTER_GUARANTEE_RATE), memberId, outCustomerId, productId, companyName, EarlywarningEventSubCategory.COUNTER_GUARANTEE_RATE);
		updateEarlyWarningSystemEvent(handleMonitorData(monitorMetricDatas, EarlywarningEventSubCategory.SALES_CHANGE_RATE), memberId, outCustomerId, productId, companyName, EarlywarningEventSubCategory.SALES_CHANGE_RATE);
		updateEarlyWarningSystemEvent(handleMonitorData(monitorMetricDatas, EarlywarningEventSubCategory.SALES_REPAYMENT_RATE), memberId, outCustomerId, productId, companyName, EarlywarningEventSubCategory.SALES_REPAYMENT_RATE);
		updateEarlyWarningSystemEvent(handleOverdueData(overduePlans, EarlywarningEventSubCategory.TOTAL_OVERDUE_NUM), memberId, outCustomerId, productId, companyName, EarlywarningEventSubCategory.TOTAL_OVERDUE_NUM);
		updateEarlyWarningSystemEvent(handleOverdueData(continueOverduePlans, EarlywarningEventSubCategory.CONTINUE_OVERDUE_NUM), memberId, outCustomerId, productId, companyName, EarlywarningEventSubCategory.CONTINUE_OVERDUE_NUM);
	}
	
	/**
	 * 更新系统预警事件
	 * @param earlywarningSystemEventHandleResult 系统预警生成的数据
	 * @param memberId 客户ID
	 * @param outCustomerId 外部平台客户ID
	 * @param productId 产品ID
	 * @param companyName 客户的公司名称
	 */
	private void updateEarlyWarningSystemEvent(EarlywarningSystemEventHandleResult earlywarningSystemEventHandleResult, String memberId, String outCustomerId, String productId, String companyName, EarlywarningEventSubCategory earlywarningEventSubCategory) {
		canelOldEarlyWarningSystemEvent(memberId, outCustomerId, productId, earlywarningEventSubCategory.name());
		if(earlywarningSystemEventHandleResult != null)
			addNewEarlyWarningSystemEvent(earlywarningSystemEventHandleResult, memberId, outCustomerId, productId, companyName);
	}
	
	/**
	 * 为监控数据产生系统预警
	 * @param monitorMetricDatas 监控数据
	 * @param earlywarningEventSubCategory 系统预警类型
	 * @return 如果没有达到预警指标则返回null
	 */
	private EarlywarningSystemEventHandleResult handleMonitorData(List<MonitorMetricData> monitorMetricDatas, EarlywarningEventSubCategory earlywarningEventSubCategory) {
		String redStatisticalDays = null;
		String redThreshold = null;
		String orangeStatisticalDays = null;
		String orangeThreshold = null;
		String yellowStatisticalDays = null;
		String yellowThreshold = null;
		switch (earlywarningEventSubCategory) {
		case COUNTER_GUARANTEE_RATE:
			redStatisticalDays = EarlyWarningProperties.RED_COUNTERGURANTEERATE_DAY;
			redThreshold = EarlyWarningProperties.RED_COUNTERGURANTEERATE_THRESHOLD;
			orangeStatisticalDays = EarlyWarningProperties.ORANGE_COUNTERGURANTEERATE_DAY;
			orangeThreshold = EarlyWarningProperties.ORANGE_COUNTERGURANTEERATE_THRESHOLD;
			yellowStatisticalDays = EarlyWarningProperties.YELLOW_COUNTERGURANTEERATE_DAY;
			yellowThreshold = EarlyWarningProperties.YELLOW_COUNTERGURANTEERATE_THRESHOLD;
			break;
		case SALES_CHANGE_RATE:
			redStatisticalDays = EarlyWarningProperties.RED_SALESCHANGERATE_DAY;
			redThreshold = EarlyWarningProperties.RED_SALESCHANGERATE_THRESHOLD;
			orangeStatisticalDays = EarlyWarningProperties.ORANGE_SALESCHANGERATE_DAY;
			orangeThreshold = EarlyWarningProperties.ORANGE_SALESCHANGERATE_THRESHOLD;
			yellowStatisticalDays = EarlyWarningProperties.ORANGE_SALESCHANGERATE_DAY;
			yellowThreshold = EarlyWarningProperties.ORANGE_SALESCHANGERATE_THRESHOLD;
			break;
		case SALES_REPAYMENT_RATE:
			redStatisticalDays = EarlyWarningProperties.RED_SALESREPAYMENTRATE_DAY;
			redThreshold = EarlyWarningProperties.RED_SALESREPAYMENTRATE_THRESHOLD;
			orangeStatisticalDays = EarlyWarningProperties.ORANGE_SALESREPAYMENTRATE_DAY;
			orangeThreshold = EarlyWarningProperties.ORANGE_SALESREPAYMENTRATE_THRESHOLD;
			yellowStatisticalDays = EarlyWarningProperties.YELLOW_SALESREPAYMENTRATE_DAY;
			yellowThreshold = EarlyWarningProperties.YELLOW_SALESREPAYMENTRATE_THRESHOLD;
			break;
		default:
			break;
		}
		EarlywarningSystemEventHandleResult returnValue = getMonitorDataEarlywarningExtra(redStatisticalDays, redThreshold, monitorMetricDatas, earlywarningEventSubCategory);
		if(returnValue != null)
			returnValue.setEarlywarningLevel(EarlywarningLevel.red);
		else {
			returnValue = getMonitorDataEarlywarningExtra(orangeStatisticalDays, orangeThreshold, monitorMetricDatas, earlywarningEventSubCategory);
			if(returnValue != null)
				returnValue.setEarlywarningLevel(EarlywarningLevel.orange);
			else {
				returnValue = getMonitorDataEarlywarningExtra(yellowStatisticalDays, yellowThreshold, monitorMetricDatas, earlywarningEventSubCategory);
				if(returnValue != null)
					returnValue.setEarlywarningLevel(EarlywarningLevel.yellow);
			}
		}
		return returnValue;
	}

	/**
	 * 为逾期数据产生系统预警
	 * @param overdueData 逾期数据
	 * @param earlywarningEventSubCategory 系统预警类型
	 * @return 如果没有达到预警指标则返回null
	 */
	private EarlywarningSystemEventHandleResult handleOverdueData(int overdueData, EarlywarningEventSubCategory earlywarningEventSubCategory) {
		Integer overdueStatisticalMonthValue = null;
		Integer redLowerLimitValue = null;
		Integer orangeLowerLimitValue = null;
		Integer orangeUpperLimitValue = null;
		Integer yellowLowerLimitValue = null;
		Integer yellowUpperLimitValue = null;
		switch (earlywarningEventSubCategory) {
		case TOTAL_OVERDUE_NUM:
			overdueStatisticalMonthValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.OVERDUEPLANS_MONTH);
			redLowerLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.RED_OVERDUEPLANS_LOWERLIMIT);
			orangeLowerLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.ORANGE_OVERDUEPLANS_LOWERLIMIT);
			orangeUpperLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.ORANGE_OVERDUEPLANS_UPPERLIMIT);
			yellowLowerLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.YELLOW_OVERDUEPLANS_LOWERLIMIT);
			yellowUpperLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.YELLOW_OVERDUEPLANS_UPPERLIMIT);
			break;
		case CONTINUE_OVERDUE_NUM:
			overdueStatisticalMonthValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.CONTINUEOVERDUEPLANS_MONTH);
			redLowerLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.RED_CONTINUEOVERDUEPLANS_LOWERLIMIT);
			orangeLowerLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.ORANGE_CONTINUEOVERDUEPLANS_LOWERLIMIT);
			orangeUpperLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.ORANGE_CONTINUEOVERDUEPLANS_UPPERLIMIT);
			yellowLowerLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.YELLOW_CONTINUEOVERDUEPLANS_LOWERLIMIT);
			yellowUpperLimitValue = EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.YELLOW_CONTINUEOVERDUEPLANS_UPPERLIMIT);
			break;
		default:
			break;
		}
		EarlywarningSystemEventHandleResult returnValue = new EarlywarningSystemEventHandleResult();
		EarlyWarningSystemEventRepresent earlyWarningSystemEventRepresent = new EarlyWarningSystemEventRepresent();
		if(overdueData > redLowerLimitValue) {
			returnValue.setEarlywarningLevel(EarlywarningLevel.red);
			earlyWarningSystemEventRepresent.setStandard(earlywarningEventSubCategory.desc() + ">" + redLowerLimitValue + "次");
		} else if (orangeLowerLimitValue < overdueData && overdueData <= orangeUpperLimitValue) {
			returnValue.setEarlywarningLevel(EarlywarningLevel.orange);
			earlyWarningSystemEventRepresent.setStandard(orangeLowerLimitValue + "<" + earlywarningEventSubCategory.desc() + "≤" + orangeUpperLimitValue + "次");
		} else if(yellowLowerLimitValue < overdueData && overdueData <= yellowUpperLimitValue) {
			returnValue.setEarlywarningLevel(EarlywarningLevel.yellow);
			earlyWarningSystemEventRepresent.setStandard(earlywarningEventSubCategory.desc() + "≤" + yellowUpperLimitValue + "次");
		} else
			return null;
		earlyWarningSystemEventRepresent.setRepresent("近" + overdueStatisticalMonthValue + "个月" + earlywarningEventSubCategory.desc());
		OverdueDataEarlyWarningExtra overdueDataEarlyWarningExtra = new OverdueDataEarlyWarningExtra();
		overdueDataEarlyWarningExtra.setMonth(overdueStatisticalMonthValue);
		overdueDataEarlyWarningExtra.setNumber(overdueData);
		returnValue.setEarlywarningEventSubCategory(earlywarningEventSubCategory);
		returnValue.setRepresent(gson.toJson(earlyWarningSystemEventRepresent));
		returnValue.setExtra(gson.toJson(overdueDataEarlyWarningExtra));
		return returnValue;
	}
	
	/**
	 * 获取监控数据产生的系统预警时间的附加字段
	 * @param dayNumberProperties 统计天数配置
	 * @param thresholdProperties 阀值配置
	 * @param monitorMetricDatas 监控数据
	 * @param earlywarningEventSubCategory 系统预警类型
	 * @return 如果没有达到预警指标则返回null
	 */
	private EarlywarningSystemEventHandleResult getMonitorDataEarlywarningExtra(String dayNumberProperties, String thresholdProperties, List<MonitorMetricData> monitorMetricDatas, EarlywarningEventSubCategory earlywarningEventSubCategory) {
		int dayNumber = EarlyWarningProperties.getIntFromEarlyWarningProperties(dayNumberProperties);
		int monitorMetricDatasSize = monitorMetricDatas.size();
		int startDateLocation = monitorMetricDatasSize - dayNumber;
		if(monitorMetricDatasSize < dayNumber || !monitorMetricDatas.get(startDateLocation).getDate().equals(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDays(new Date(), -dayNumber))))
			return null;
		BigDecimal rate = null;
		BigDecimal thresholdBigDecimal = new BigDecimal(EarlyWarningProperties.getIntFromEarlyWarningProperties(thresholdProperties));
		BigDecimal transformBigDecimal = new BigDecimal(100);
		MonitorMetricData monitorMetircData = null;
		List<MonitorDataEarlyWarningExtra> monitorDataEarlyWarningExtras = new ArrayList<>();
		for(int i = startDateLocation; i < monitorMetricDatasSize; i++) {
			monitorMetircData = monitorMetricDatas.get(i);
			switch (earlywarningEventSubCategory) {
			case COUNTER_GUARANTEE_RATE:
				rate = monitorMetircData.getCounterGuaranteeRate();
				break;
			case SALES_CHANGE_RATE:
				rate = monitorMetircData.getSalesChangeRate();
				break;
			case SALES_REPAYMENT_RATE:
				rate = monitorMetircData.getSalesRepaymentRate();
				break;
			default:
				break;
			}
			if(rate == null || thresholdBigDecimal.compareTo(rate.multiply(transformBigDecimal)) < 1)
				return null;
			MonitorDataEarlyWarningExtra monitorDataEarlyWarningExtra = new MonitorDataEarlyWarningExtra();
			monitorDataEarlyWarningExtra.setDate(monitorMetircData.getDate());
			monitorDataEarlyWarningExtra.setMetirc(rate);
			monitorDataEarlyWarningExtras.add(monitorDataEarlyWarningExtra);
		}
		EarlyWarningSystemEventRepresent earlyWarningSystemEventRepresent = new EarlyWarningSystemEventRepresent();
		earlyWarningSystemEventRepresent.setRepresent(earlywarningEventSubCategory.desc());
		earlyWarningSystemEventRepresent.setStandard("连续" + dayNumber + "天<" + thresholdBigDecimal + "%");
		EarlywarningSystemEventHandleResult returnValue = new EarlywarningSystemEventHandleResult();
		returnValue.setEarlywarningEventSubCategory(earlywarningEventSubCategory);
		returnValue.setRepresent(gson.toJson(earlyWarningSystemEventRepresent));
		returnValue.setExtra(gson.toJson(monitorDataEarlyWarningExtras));
		return returnValue;
	}
	
	/**
	 * 取消之前的同类型系统预警
	 * @param memberId 客户ID
	 * @param outCustomerId 外部平台客户ID
	 * @param productId 产品ID
	 * @param subType 预警子类型
	 */
	private void canelOldEarlyWarningSystemEvent(String memberId, String outCustomerId, String productId, String subType) {
		EarlywarningEventExample earlywarningEventExample = new EarlywarningEventExample();
		earlywarningEventExample.createCriteria().andProductIdEqualTo(productId).andFinanceIdEqualTo(memberId).andOutCustomerIdEqualTo(outCustomerId).andSubTypeEqualTo(subType).andStateIn(Arrays.asList(new String[]{EarlywarningEventState.effective.name(), EarlywarningEventState.cancel_wait_for_review.name()}));
		List<EarlywarningEvent> earlywarningEvents = earlywarningEventMapper.selectByExample(earlywarningEventExample);
		if(CollectionUtils.isNotEmpty(earlywarningEvents)) {
			EarlywarningEvent earlywarningEvent = earlywarningEvents.get(0);
			earlywarningEvent.setCancelReviewTime(new Date());
			earlywarningEvent.setState(EarlywarningEventState.cancel.name());
			earlywarningEvent.setUpdateTime(earlywarningEvent.getCancelReviewTime());
			earlywarningEventMapper.updateByPrimaryKeySelective(earlywarningEvent);
		}
	}
	
	/**
	 * 增加今日的预警信息
	 * @param earlywarningSystemEventHandleResult 系统预警生成的数据
	 * @param memberId 客户ID
	 * @param outCustomerId 外部平台客户ID
	 * @param productId 产品ID
	 * @param companyName 客户的公司名称
	 */
	private void addNewEarlyWarningSystemEvent(EarlywarningSystemEventHandleResult earlywarningSystemEventHandleResult, String memberId, String outCustomerId, String productId, String companyName) {
		EarlywarningEventSubCategory earlywarningEventSubCategory = earlywarningSystemEventHandleResult.getEarlywarningEventSubCategory();
		EarlywarningEvent earlywarningEvent = new EarlywarningEvent();
		earlywarningEvent.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EARLYWARNING_EVENT_ID, 4));
		earlywarningEvent.setProductId(productId);
		earlywarningEvent.setFinanceId(memberId);
		earlywarningEvent.setFinanceCompany(companyName);
		earlywarningEvent.setOutCustomerId(outCustomerId);
		earlywarningEvent.setType(earlywarningEventSubCategory.parentCategory().name());
		earlywarningEvent.setSubType(earlywarningEventSubCategory.name());
		earlywarningEvent.setLevel(earlywarningSystemEventHandleResult.getEarlywarningLevel().name());
		earlywarningEvent.setEntryReviewTime(new Date());
		earlywarningEvent.setOccurTime(earlywarningEvent.getEntryReviewTime());
		earlywarningEvent.setRepresent(earlywarningSystemEventHandleResult.getRepresent());
		earlywarningEvent.setExtraData(earlywarningSystemEventHandleResult.getExtra());
		earlywarningEvent.setState(EarlywarningEventState.effective.name());
		earlywarningEvent.setCreateTime(earlywarningEvent.getOccurTime());
		earlywarningEvent.setUpdateTime(earlywarningEvent.getCreateTime());
		earlywarningEventMapper.insertSelective(earlywarningEvent);
	}
	
	/**
	 * 保存人工提交的预警信息
	 * @param earlyWarningManualEventDTO
	 * @return
	 */
	private EarlywarningEvent addNewEarlyWarningManualEvent(EarlyWarningManualEventDTO earlyWarningManualEventDTO) {
		EarlywarningEvent earlywarningEvent = new EarlywarningEvent();
		String earlywarningEventCategory = earlyWarningManualEventDTO.getEarlywarningEventCategory();
//		if(earlywarningEventCategory.equals(EarlywarningEventCategory.COURT_EXECUTION_COMPANY.name()) || earlywarningEventCategory.equals(EarlywarningEventCategory.COURT_EXECUTION_INDIVIDUAL.name()))
		earlywarningEvent.setAmount(earlyWarningManualEventDTO.getAmount());
//		else if(earlywarningEventCategory.equals(EarlywarningEventCategory.OTHER.name()) || earlywarningEventCategory.equals(EarlywarningEventCategory.NEGATIVE_REPORT.name()))
		earlywarningEvent.setSubType(earlyWarningManualEventDTO.getEarlywarningEventSubCategory());
		earlywarningEvent.setLevel(earlyWarningManualEventDTO.getEarlywarningLevel());
		earlywarningEvent.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EARLYWARNING_EVENT_ID, 4));
		earlywarningEvent.setProductId(earlyWarningManualEventDTO.getProductId());
		earlywarningEvent.setFinanceId(earlyWarningManualEventDTO.getFinanceId());
		earlywarningEvent.setFinanceCompany(earlyWarningManualEventDTO.getCompanyName());
		earlywarningEvent.setOutCustomerId(earlyWarningManualEventDTO.getOutCustomerId());
		earlywarningEvent.setType(earlywarningEventCategory);
		earlywarningEvent.setEntryUserId(earlyWarningManualEventDTO.getUserId());
		earlywarningEvent.setEntryRealName(earlyWarningManualEventDTO.getRealName());
		earlywarningEvent.setOccurTime(earlyWarningManualEventDTO.getDate());
		earlywarningEvent.setRepresent(new Gson().toJson(earlyWarningManualEventDTO.getRepresent()));
		earlywarningEvent.setExtraData(new Gson().toJson(earlyWarningManualEventDTO.getExtra()));
		earlywarningEvent.setState(EarlywarningEventState.add_wait_for_review.name());
		earlywarningEvent.setCreateTime(new Date());
		earlywarningEvent.setUpdateTime(earlywarningEvent.getCreateTime());
		earlywarningEventMapper.insertSelective(earlywarningEvent);
		return earlywarningEvent;
	}
	
	/**
	 * 添加系统预警生成记录
	 * @param productId 产品ID
	 * @param financeId 融资商ID
	 * @param outCustomerId 外部平台客户ID
	 * @param companyName 公司名称
	 */
	private void addearlywarningSystemEventGenerateRecord(String productId, String financeId, String outCustomerId, String companyName) {
		Date now = new Date();
		EarlywarningSystemEventGenerateRecord earlywarningSystemEventGenerateRecord = new EarlywarningSystemEventGenerateRecord();
		earlywarningSystemEventGenerateRecord.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EARLYWARNING_SYSTEM_EVENT_GENERATE_RECORD_ID, 4));
		earlywarningSystemEventGenerateRecord.setProductId(productId);
		earlywarningSystemEventGenerateRecord.setFinanceId(financeId);
		earlywarningSystemEventGenerateRecord.setFinanceCompany(companyName);
		earlywarningSystemEventGenerateRecord.setOutCustomerId(outCustomerId);
		earlywarningSystemEventGenerateRecord.setDate(new SimpleDateFormat("yyyy-MM-dd").format(now));
		earlywarningSystemEventGenerateRecord.setCreateTime(now);
		earlywarningSystemEventGenerateRecord.setUpdateTime(now);
		earlywarningSystemEventGenerateRecordMapper.insertSelective(earlywarningSystemEventGenerateRecord);
	}
	
	private boolean isExistEarlyWarningCustomer(String productId, String financeId, String outCustomerId) {
		EarlywarningCustomerExample earlywarningCustomerExample = new EarlywarningCustomerExample();
		earlywarningCustomerExample.createCriteria().andProductIdEqualTo(productId).andFinanceIdEqualTo(financeId).andOutCustomerIdEqualTo(outCustomerId);
		return earlywarningCustomerMapper.selectByExample(earlywarningCustomerExample).size() != 0;
	}
	
	private EarlywarningEvent lockEarlyWarningEvent(String id) {
		EarlywarningEvent earlywarningEvent = earlywarningEventMapper.lockByPrimaryKey(id);
		if(earlywarningEvent == null)
			throw WebException.instance(ReturnCode.TP4006);
		return earlywarningEvent;
	}
	
	private void updateCanelEarlyWarningEvent(String cancelUserId, String cancelRealName, EarlywarningEvent earlywarningEvent) {
		earlywarningEvent.setCancelUserId(cancelUserId);
		earlywarningEvent.setCancelRealName(cancelRealName);
		earlywarningEvent.setState(EarlywarningEventState.cancel_wait_for_review.name());
		earlywarningEvent.setUpdateTime(new Date());
		earlywarningEventMapper.updateByPrimaryKeySelective(earlywarningEvent);
	}
	
	/**
	 * 保存待审核信息
	 * @param earlywarningEvent
	 * @param earlywarningEventAction 审核动作（解除or新增）
	 * @param extra 新增的预警事件审核的附加字段 
	 */
	public void addEarlyWarningEventReview(EarlywarningEvent earlywarningEvent, EarlywarningEventAction earlywarningEventAction, String extra) {
		EarlywarningEventReview earlywarningEventReview = new EarlywarningEventReview();
		earlywarningEventReview.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SWQUENCE_NAME_EARLYWARNING_EVENT_REVIEW_ID, 4));
		earlywarningEventReview.setProductId(earlywarningEvent.getProductId());
		earlywarningEventReview.setFinanceId(earlywarningEvent.getFinanceId());
		earlywarningEventReview.setFinanceCompany(earlywarningEvent.getFinanceCompany());
		earlywarningEventReview.setOutCustomerId(earlywarningEvent.getOutCustomerId());
		earlywarningEventReview.setEventId(earlywarningEvent.getId());
		earlywarningEventReview.setEventType(earlywarningEvent.getType());
		earlywarningEventReview.setEventSubType(earlywarningEvent.getSubType());
		earlywarningEventReview.setApplyType(earlywarningEventAction.name());
		earlywarningEventReview.setState(EarlywarningReviewState.wait_for_review.name());
		earlywarningEventReview.setExtraData(extra);
		earlywarningEventReview.setCreateTime(new Date());
		earlywarningEventReview.setUpdateTime(earlywarningEventReview.getUpdateTime());
		earlywarningEvnetReviewMapper.insertSelective(earlywarningEventReview);
	}

}

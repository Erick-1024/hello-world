package com.cana.early.warning.service.transaction.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.cana.early.warning.dao.mapper.gen.EarlywarningCustomerMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningEventReviewMapper;
import com.cana.early.warning.dao.mapper.gen.EarlywarningSystemEventGenerateRecordMapper;
import com.cana.early.warning.dao.po.EarlywarningEvent;
import com.cana.early.warning.dao.po.EarlywarningEventExample;
import com.cana.early.warning.dao.po.EarlywarningSystemEventGenerateRecord;
import com.cana.early.warning.service.transaction.IYdEarlyWarningEventTransactionService;
import com.cana.early.warning.service.utils.EarlyWarningProperties;
import com.cana.early.warning.service.utils.IEarlyWarningServiceHelper;
import com.cana.early.warning.service.utils.YundaexEarlyWarningProperties;
import com.cana.vbam.common.early.warning.dto.EarlyWarningSystemEventRepresent;
import com.cana.vbam.common.early.warning.dto.EarlywarningLockCustomerRequest;
import com.cana.vbam.common.early.warning.dto.YundaexEarlywarningSystemEventHandleResult;
import com.cana.vbam.common.early.warning.enums.EarlywarningEventState;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventSubCategory;
import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.yundaex.api.IYundaexCreditApi;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class YdEarlyWarningEventTransactionServiceImpl implements IYdEarlyWarningEventTransactionService {

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
	
	@Resource
	private IYundaexCreditApi ydCreditApi;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	private Gson gson = new Gson();
	
	@Override
	public void reckonYdEarlyWarningSystemEvent(int overduePlans, MonitorMetricYundaDTO monitorMetricData, MonitorMetricYundaDTO lastMonitorMetricData, String memberId, String outCustomerId, String productId, String companyName, boolean isByMonth) {
		EarlywarningLockCustomerRequest earlywarningLockCustomerRequest = earlywarningServiceHelper.generateEarlywarningLockCustomerRequest(productId, memberId, companyName, outCustomerId);
		// 预警汇总表判断
		earlywarningServiceHelper.addOrlockCustomer(earlywarningLockCustomerRequest);
		
		// 处理本次的系统预警事件（判断预警等级，并更新系统预警记录）
		handleEarlyWarningSystemEvent(overduePlans, monitorMetricData, lastMonitorMetricData, memberId, outCustomerId, productId, companyName, isByMonth);
		
		// 触发预警等级重新计算
		earlywarningServiceHelper.updateCustomerEarlywarningLevel(earlywarningLockCustomerRequest);
		
		// 新增系统预警生成记录
		addearlywarningSystemEventGenerateRecord(productId, memberId, outCustomerId, companyName, isByMonth ? new SimpleDateFormat("yyyy-MM").format(getCurrentDate()) : new SimpleDateFormat("yyyy-MM-dd").format(getCurrentDate()));
	}
	
	private void handleEarlyWarningSystemEvent(int overduePlans, MonitorMetricYundaDTO monitorMetricData, MonitorMetricYundaDTO lastMonitorMetricData, String memberId, String outCustomerId, String productId, String companyName, boolean isByMonth) {
		if(isByMonth){
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.RECANDSEND_GROWTHRATE), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.RECANDSEND_GROWTHRATE);
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.BAILBALANCE_DAY_REQUIREMENTS), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.BAILBALANCE_DAY_REQUIREMENTS);
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.YUNDAEXGRADE), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.YUNDAEXGRADE);
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.BAILBALANCE), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.BAILBALANCE);
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.NET_CASHFLOW_GROWTHRATE), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.NET_CASHFLOW_GROWTHRATE);
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.NET_CASHFLOW), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.NET_CASHFLOW);
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.CREDIT_LIMIT_GROWTH), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.CREDIT_LIMIT_GROWTH);
		}else
			updateEarlyWarningSystemEvent(handleMonitorData(overduePlans, monitorMetricData, lastMonitorMetricData, YundaexEarlywarningEventSubCategory.OVERDUES), memberId, outCustomerId, productId, companyName, YundaexEarlywarningEventSubCategory.OVERDUES);
	}
	
	/**
	 * 更新系统预警事件
	 * @param earlywarningSystemEventHandleResult 系统预警生成的数据
	 * @param memberId 客户ID
	 * @param outCustomerId 外部平台客户ID
	 * @param productId 产品ID
	 * @param companyName 客户的公司名称
	 */
	private void updateEarlyWarningSystemEvent(YundaexEarlywarningSystemEventHandleResult earlywarningSystemEventHandleResult, String memberId, String outCustomerId, String productId, String companyName, YundaexEarlywarningEventSubCategory earlywarningEventSubCategory) {
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
	private YundaexEarlywarningSystemEventHandleResult handleMonitorData(int overduePlans, MonitorMetricYundaDTO monitorMetricDatas, MonitorMetricYundaDTO lastMonitorMetricData, YundaexEarlywarningEventSubCategory earlywarningEventSubCategory) {
		BigDecimal rate = null;
		BigDecimal thresholdBigDecimal = null;
		BigDecimal transformBigDecimal = new BigDecimal(100);
		EarlywarningLevel earlywarningLevel = EarlywarningLevel.yellow;
		String standard = null;
		String extra = null;
		switch (earlywarningEventSubCategory) {
		case RECANDSEND_GROWTHRATE:
			rate = monitorMetricDatas.getRecandsendGrowthrate();
			thresholdBigDecimal = new BigDecimal(YundaexEarlyWarningProperties.getIntFromEarlyWarningProperties(YundaexEarlyWarningProperties.YELLOW_RECANDSEND_GROWTHRATE));
			if(rate == null || thresholdBigDecimal.compareTo(rate.multiply(transformBigDecimal)) < 1)
				return null;
			standard = earlywarningEventSubCategory.desc() + "<" + thresholdBigDecimal.toString() +"%";
			extra = rate.multiply(transformBigDecimal).stripTrailingZeros().toString() + "%";
			break;
		case BAILBALANCE_DAY_REQUIREMENTS:
			if(null == monitorMetricDatas.getBailBalance() || null == monitorMetricDatas.getDayRequirements())
				return null;
			rate = MoneyArithUtil.divide(monitorMetricDatas.getBailBalance(), monitorMetricDatas.getDayRequirements(), 5);
			thresholdBigDecimal = new BigDecimal(YundaexEarlyWarningProperties.getIntFromEarlyWarningProperties(YundaexEarlyWarningProperties.YELLOW_BAILBALANCE_DAY_REQUIREMENTS));
			if(thresholdBigDecimal.compareTo(rate.multiply(transformBigDecimal)) < 1)
				return null;
			standard = earlywarningEventSubCategory.desc() + "<" + thresholdBigDecimal.toString() +"%";
			extra = rate.multiply(transformBigDecimal).stripTrailingZeros().toString() + "%";
			break;
		case YUNDAEXGRADE:
			rate = monitorMetricDatas.getYundaexGrade();
			thresholdBigDecimal = new BigDecimal(YundaexEarlyWarningProperties.getIntFromEarlyWarningProperties(YundaexEarlyWarningProperties.YELLOW_YUNDAEXGRADE));
			if(rate == null || thresholdBigDecimal.compareTo(rate) < 0)
				return null;
			standard = earlywarningEventSubCategory.desc() + "<BBB";
			extra = ydCreditApi.getYundaexGradeByScore(rate);
			break;
		case BAILBALANCE:
			rate = monitorMetricDatas.getBailBalance();
			if(null == rate)
				return null;
			if(rate.compareTo(BigDecimal.ZERO) >= 0 && (null == monitorMetricDatas.getDayRequirements() 
					|| rate.abs().compareTo(new BigDecimal(7).multiply(monitorMetricDatas.getDayRequirements())) < 1))
				return null;
			standard = earlywarningEventSubCategory.desc() + "<0 或 |" + earlywarningEventSubCategory.desc() + "| > 保证金需求/4";
			extra = rate.divide(transformBigDecimal, 2).stripTrailingZeros().toString();
			break;
		case NET_CASHFLOW_GROWTHRATE:
			if(null == lastMonitorMetricData || null == lastMonitorMetricData.getNetCashflow() || 
				null == monitorMetricDatas.getNetCashflow() || null == monitorMetricDatas.getShortLoan() ||
					lastMonitorMetricData.getNetCashflow().compareTo(monitorMetricDatas.getNetCashflow()) < 1 ||
					monitorMetricDatas.getShortLoan().compareTo(BigDecimal.ZERO) != 0)
				return null;
			rate = MoneyArithUtil.divide(MoneyArithUtil.sub(monitorMetricDatas.getNetCashflow(), lastMonitorMetricData.getNetCashflow()), lastMonitorMetricData.getNetCashflow(), 5);
			standard = "短期借款=0，" + earlywarningEventSubCategory.desc() + "<0%";
			extra = rate.multiply(transformBigDecimal).stripTrailingZeros().toString() + "%";
			break;
		case NET_CASHFLOW:
			if(null == monitorMetricDatas.getNetCashflow() || null == monitorMetricDatas.getShortLoan() ||
					monitorMetricDatas.getShortLoan().compareTo(BigDecimal.ZERO) <= 0 || monitorMetricDatas.getNetCashflow().compareTo(BigDecimal.ZERO) > 0)
			return null;
			rate = monitorMetricDatas.getNetCashflow();
			standard = "短期借款>0，" + earlywarningEventSubCategory.desc() + "<0";
			extra = rate.divide(transformBigDecimal, 2).stripTrailingZeros().toString();
			break;
		case CREDIT_LIMIT_GROWTH:
			if(null == lastMonitorMetricData || null == lastMonitorMetricData.getCreditLimit() || 
				null == monitorMetricDatas.getCreditLimit())
			return null;
			rate = MoneyArithUtil.divide(MoneyArithUtil.sub(monitorMetricDatas.getCreditLimit(), lastMonitorMetricData.getCreditLimit()), lastMonitorMetricData.getCreditLimit(), 5);
			thresholdBigDecimal = new BigDecimal(YundaexEarlyWarningProperties.getIntFromEarlyWarningProperties(YundaexEarlyWarningProperties.YELLOW_CREDIT_LIMIT_GROWTH));
			if(thresholdBigDecimal.compareTo(rate.abs().multiply(transformBigDecimal)) >= 0)
				return null;
			standard = "最大授信金额低于上次授信贷后幅度超过" + thresholdBigDecimal.toString() + "%";
			extra = rate.multiply(transformBigDecimal).stripTrailingZeros().toString() + "%";
			break;
		case OVERDUES:
			Integer redLowerLimitValue = YundaexEarlyWarningProperties.getIntFromEarlyWarningProperties(YundaexEarlyWarningProperties.RED_OVERDUEPLANS_LOWERLIMIT);
			Integer yellowLowerLimitValue = YundaexEarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.YELLOW_OVERDUEPLANS_LOWERLIMIT);
			Integer yellowUpperLimitValue = YundaexEarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.YELLOW_OVERDUEPLANS_UPPERLIMIT);
			if(overduePlans >= redLowerLimitValue) {
				earlywarningLevel = EarlywarningLevel.red;
				standard = earlywarningEventSubCategory.desc() + "≥" + redLowerLimitValue + "次";
			} else if(yellowLowerLimitValue < overduePlans && overduePlans < yellowUpperLimitValue) {
				standard = yellowLowerLimitValue + "<" + earlywarningEventSubCategory.desc() + "<" + yellowUpperLimitValue + "次";
			} else
				return null;
			extra = overduePlans+"次";
			break;
		default:
			break;
		}
		YundaexEarlywarningSystemEventHandleResult returnValue = new YundaexEarlywarningSystemEventHandleResult();
		EarlyWarningSystemEventRepresent earlyWarningSystemEventRepresent = new EarlyWarningSystemEventRepresent();
		earlyWarningSystemEventRepresent.setRepresent(earlywarningEventSubCategory.desc());
		earlyWarningSystemEventRepresent.setStandard(standard);
		returnValue.setEarlywarningEventSubCategory(earlywarningEventSubCategory);
		returnValue.setEarlywarningLevel(earlywarningLevel);
		returnValue.setRepresent(gson.toJson(earlyWarningSystemEventRepresent));
		returnValue.setExtra(extra);
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
	private void addNewEarlyWarningSystemEvent(YundaexEarlywarningSystemEventHandleResult earlywarningSystemEventHandleResult, String memberId, String outCustomerId, String productId, String companyName) {
		YundaexEarlywarningEventSubCategory earlywarningEventSubCategory = earlywarningSystemEventHandleResult.getEarlywarningEventSubCategory();
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
	 * 添加系统预警生成记录
	 * @param productId 产品ID
	 * @param financeId 融资商ID
	 * @param outCustomerId 外部平台客户ID
	 * @param companyName 公司名称
	 */
	private void addearlywarningSystemEventGenerateRecord(String productId, String financeId, String outCustomerId, String companyName, String date) {
		Date now = new Date();
		EarlywarningSystemEventGenerateRecord earlywarningSystemEventGenerateRecord = new EarlywarningSystemEventGenerateRecord();
		earlywarningSystemEventGenerateRecord.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EARLYWARNING_SYSTEM_EVENT_GENERATE_RECORD_ID, 4));
		earlywarningSystemEventGenerateRecord.setProductId(productId);
		earlywarningSystemEventGenerateRecord.setFinanceId(financeId);
		earlywarningSystemEventGenerateRecord.setFinanceCompany(companyName);
		earlywarningSystemEventGenerateRecord.setOutCustomerId(outCustomerId);
		earlywarningSystemEventGenerateRecord.setDate(date);
		earlywarningSystemEventGenerateRecord.setCreateTime(now);
		earlywarningSystemEventGenerateRecord.setUpdateTime(now);
		earlywarningSystemEventGenerateRecordMapper.insertSelective(earlywarningSystemEventGenerateRecord);
	}
	
	private Date getCurrentDate(){
		String currentDate = vbamCommonServiceImpl.getCurrentDate();
		return new DateTime(currentDate).toDate();
	}
}

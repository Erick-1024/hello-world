package com.cana.repayment.scheduler.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.asset.api.IAssetProjectManageApi;
import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.repayment.service.transaction.IHolidayPolicyTransactionService;
import com.cana.setting.service.transaction.ICanaCalendarTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.setting.dto.ChangedCalendar;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Maps;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 节假日变化触发定时任务
 * @author XuMeng
 *
 */
@Service
public class HolidayChangedScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 保存上一次更新时间的key
	private final String HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_KEY = "repayment-scheduler:holiday-changed-scheduler-last-update-time";
	// 上一次更新时间 格式
	private final String HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_VALUE_PATTERN = DateTimeUtil.DATE_TIME_PATTERN;
	// 如果没有找到上一次更新时间，则初始化一个时间
	private final String HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_DEFAULT_VALUE = "2016-09-21 00:00:00";
	// 查询变化的节假日时，为避免机器间的时间误差，取当前时间前推若干秒
	private final int MINUS_SECONDS = 1;

	@Resource
	private PropertiesMapper propertiesMapper;
	@Resource
	private ICanaCalendarTransactionService holidayCalendarTransactionService;
	@Resource
	private IVbamCommonService commonService;
	@Resource
	private IHolidayPolicyTransactionService holidayPolicyTransactionService;
	@Resource
	private IAssetProjectManageApi assetProjectManageApi;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_SECOND * 10)
	public void doTask() {
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));

		Pair<Date, Date> holidayChangedUpdateTimeScope = getLastUpdateTime();
		if (holidayChangedUpdateTimeScope == null)
			return;

		Map<String, Integer> effectDateAndExtensionDaysMap = getAllEffectedRepaymentDates(holidayChangedUpdateTimeScope);
		if (effectDateAndExtensionDaysMap.isEmpty()) {
			logger.info("没有查询到节假日变更信息");
			saveUpdateTime(holidayChangedUpdateTimeScope.getRight());
			return;
		} else {
			logger.info("节假日变更影响到的还款日以及还款日对应的节假日因素展期天数：{}", effectDateAndExtensionDaysMap);
		}

		List<String> useHolidayPolicyProjectIds = assetProjectManageApi.getUseHolidayPolicyProjectIds();
		logger.info("使用节假日政策的项目列表为：{}", useHolidayPolicyProjectIds);
		if (CollectionUtils.isEmpty(useHolidayPolicyProjectIds)) {
			saveUpdateTime(holidayChangedUpdateTimeScope.getRight());
			return;
		}

		List<String> effectedLoanInfoIds = holidayPolicyTransactionService.getAllEffectedLoanInfoIds(
				effectDateAndExtensionDaysMap.keySet(), useHolidayPolicyProjectIds);
		logger.info("节假日变更影响到的放款ID为：{}", effectedLoanInfoIds);

		for (String loanInfoId : effectedLoanInfoIds) {
			try {
				holidayPolicyTransactionService.updateExtensionDays(loanInfoId, effectDateAndExtensionDaysMap);
			} catch (Exception e) {
				logger.error("更新放款[{}]节假日展期失败", e);
				return;
			}
		}

		saveUpdateTime(holidayChangedUpdateTimeScope.getRight());
	}

	private Pair<Date, Date> getLastUpdateTime() {
		Properties properties = propertiesMapper.selectByPrimaryKey(HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_KEY);
		String lastUpdateTime = HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_DEFAULT_VALUE;
		if (properties != null && StringUtils.isNotBlank(properties.getValue()))
			lastUpdateTime = properties.getValue();
		DateTime startDate = DateTimeUtil.parseDate(HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_VALUE_PATTERN, lastUpdateTime);
		String now = DateTime.now().minusSeconds(MINUS_SECONDS)
				.toString(HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_VALUE_PATTERN);
		DateTime endDate = DateTimeUtil.parseDate(HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_VALUE_PATTERN, now);
		if (!endDate.isAfter(startDate)) {
			logger.warn("节假日定时器上一次更新时间晚于当前时间");
			return null;
		}
		logger.info("节假日定时器更新时间范围为: {}, {}", lastUpdateTime, now);
		return Pair.of(startDate.toDate(), endDate.toDate());
	}

	private void saveUpdateTime(Date endTime) {
		String endTimeStr = DateTimeUtil.format(endTime, HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_VALUE_PATTERN);
		Properties properties = propertiesMapper.selectByPrimaryKey(HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_KEY);
		if (properties == null) {
			properties = new Properties();
			properties.setName(HOLIDAY_CHANGED_SCHEDULER_LAST_UPDATE_TIME_KEY);
			properties.setValue(endTimeStr);
			propertiesMapper.insertSelective(properties);
		} else {
			properties.setValue(endTimeStr);
			propertiesMapper.updateByPrimaryKeySelective(properties);
		}
	}

	/**
	 * 获取一段时间内所有受到节假日变更影响到的还款日，key为 yyyy-MM-dd, value 为该日期作为还款计划时的展期天数
	 */
	private Map<String, Integer> getAllEffectedRepaymentDates(Pair<Date, Date> holidayChangedUpdateTimeScope) {
		try {
			List<ChangedCalendar> changedCalendars = holidayCalendarTransactionService.getChangedDate(
					holidayChangedUpdateTimeScope.getLeft(), holidayChangedUpdateTimeScope.getRight());
			if (CollectionUtils.isEmpty(changedCalendars))
				return Maps.newHashMap();

			String today = commonService.getCurrentDate();
			Map<String, Integer> effectDates = Maps.newHashMap();
			for (ChangedCalendar changedCalendar : changedCalendars) {
				logger.info("日期 {} 发生节假日变更，BeforeFirstWeekday: {}, NotBeforeFirstWeekday: {}", changedCalendar.getDate(),
						changedCalendar.getBeforeFirstWeekday(), changedCalendar.getNotBeforeFirstWeekday());

				if (today.compareTo(changedCalendar.getDate()) > 0)
					continue;
				effectDates.put(changedCalendar.getDate(), changedCalendar.getNotBeforeFirstWeekday());

				if (changedCalendar.getBeforeFirstWeekday() <= 1)
					continue;
				DateTime changedDate = DateTimeUtil.parseDate(DateTimeUtil.DATE_PATTERN, changedCalendar.getDate());
				for (Integer i = 1; i < changedCalendar.getBeforeFirstWeekday(); ++i) {
					String effectDateStr = DateTimeUtil.format(changedDate.minusDays(i), DateTimeUtil.DATE_PATTERN);
					if (today.compareTo(effectDateStr) > 0)
						break;
					effectDates.put(effectDateStr, i + changedCalendar.getNotBeforeFirstWeekday());
				}
			}
			return effectDates;
		} catch (Exception e) {
			logger.error("查询变化的节假日出错", e);
			throw new RuntimeException("查询变化的节假日出错", e);
		}
	}
}

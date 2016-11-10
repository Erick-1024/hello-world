package com.cana.setting.service.transaction.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.cana.setting.dao.mapper.gen.CanaCalendarMapper;
import com.cana.setting.dao.po.CanaCalendar;
import com.cana.setting.dao.po.CanaCalendarExample;
import com.cana.setting.dao.po.CanaCalendarExample.Criteria;
import com.cana.setting.service.transaction.ICanaCalendarTransactionService;
import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.ChangedCalendar;
import com.google.common.collect.Maps;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.util.DateUtils;

@Service
public class CanaCalendarTransactionServiceImpl implements ICanaCalendarTransactionService {

	@Resource
	private CanaCalendarMapper canaCalendarMapper;
	
	@Override
	public List<ChangedCalendar> getChangedDate(Date startDate, Date endDate) {
		List<ChangedCalendar> returnValue = new ArrayList<>();
		CanaCalendarExample canaCalendarExample = new CanaCalendarExample();
		canaCalendarExample.setOrderByClause("date");
		Criteria criteria = canaCalendarExample.createCriteria();
		criteria.andDateGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if(startDate != null)
			criteria.andHolidayUpateTimeGreaterThanOrEqualTo(startDate);
		if(endDate != null)
			criteria.andHolidayUpateTimeLessThan(endDate);
		List<CanaCalendar> canaCalendars = canaCalendarMapper.selectByExample(canaCalendarExample);
		if(canaCalendars.size() > 0) {
			int index = 0;
			List<ChangedCalendar> changedCalendarTemp = new ArrayList<>();
			String earlyDate = DateTimeUtil.addDay10(canaCalendars.get(0).getDate(), -15);
			String lastDate = DateTimeUtil.addDay10(canaCalendars.get(canaCalendars.size() -1).getDate(), 15);
			String weekDay = null;
			canaCalendarExample.clear();
			canaCalendarExample.setOrderByClause("date");
			canaCalendarExample.createCriteria().andDateGreaterThanOrEqualTo(earlyDate).andDateLessThanOrEqualTo(lastDate);
			List<CanaCalendar> canaCalendarList = canaCalendarMapper.selectByExample(canaCalendarExample);
			int forLength = DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(earlyDate), DateTimeUtil.parseDate10(lastDate)) + 1;
			for (int i = 0; i < forLength; i++) {
				if(canaCalendarList.size() == index || !canaCalendarList.get(index).getDate().equals(earlyDate)){
					int week = DateUtils.getWeek(DateTimeUtil.parseDate10(earlyDate).toDate());
					CanaCalendar newCanaCalendar = new CanaCalendar();
					newCanaCalendar.setDate(earlyDate);
					newCanaCalendar.setIsHoliday((week == 1 || week ==7));
					canaCalendarList.add(i, newCanaCalendar);
				}
				++index;
				earlyDate = DateTimeUtil.addDay10(earlyDate, 1);
			}
			index = 0;
			for (CanaCalendar canaCalendar : canaCalendarList) {
				if(index < canaCalendars.size() && canaCalendars.get(index).getDate().equals(canaCalendar.getDate())) {
					ChangedCalendar changedCalendar = new ChangedCalendar();
					changedCalendar.setDate(canaCalendar.getDate());
					changedCalendar.setBeforeFirstWeekday(DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(canaCalendar.getDate()), DateTimeUtil.parseDate10(weekDay)));
					changedCalendarTemp.add(changedCalendar);
					++index;
				}
				if(!canaCalendar.getIsHoliday()) {
					weekDay = canaCalendar.getDate();
					for (ChangedCalendar temp : changedCalendarTemp) {
						temp.setNotBeforeFirstWeekday(DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(temp.getDate()), DateTimeUtil.parseDate10(weekDay)));
						returnValue.add(temp);
					}
					changedCalendarTemp.clear();
				}
			}
		}
		return returnValue;
	}

	@Override
	public List<ChangedCalendar> getChangedDateForTest(Date startDate, Date endDate) {
		List<ChangedCalendar> returnValue = new ArrayList<>();
		CanaCalendarExample canaCalendarExample = new CanaCalendarExample();
		canaCalendarExample.setOrderByClause("date");
		Criteria criteria = canaCalendarExample.createCriteria();
		criteria.andDateGreaterThanOrEqualTo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if(startDate != null)
			criteria.andHolidayUpateTimeGreaterThanOrEqualTo(startDate);
		if(endDate != null)
			criteria.andHolidayUpateTimeLessThan(endDate);
		List<CanaCalendar> changedCalendars = canaCalendarMapper.selectByExample(canaCalendarExample);
		if (CollectionUtils.isEmpty(changedCalendars))
			return returnValue;

		DateTime date = DateTime.now();
		canaCalendarExample.clear();
		canaCalendarExample.setOrderByClause("date");
		canaCalendarExample.createCriteria().andDateGreaterThanOrEqualTo(DateTimeUtil.format(date, DateTimeUtil.DATE_PATTERN));
		List<CanaCalendar> dbCalendarList = canaCalendarMapper.selectByExample(canaCalendarExample);
		Map<String, CanaCalendar> dbCalendarMap = Maps.newHashMap();
		for (CanaCalendar canaCalendar : dbCalendarList) {
			dbCalendarMap.put(canaCalendar.getDate(), canaCalendar);
		}

		String preWeekDay = DateTimeUtil.format(date.minusDays(1), DateTimeUtil.DATE_PATTERN);
		String nextWeekDay = null;
		for (CanaCalendar changedCalendar : changedCalendars) {
			DateTime changedDate = DateTimeUtil.parseDate(DateTimeUtil.DATE_PATTERN, changedCalendar.getDate());

			int beforeFirstWeekday = 1;
			while (true) {
				DateTime preDateTime = changedDate.minusDays(beforeFirstWeekday);
				String preDateStr = DateTimeUtil.format(preDateTime, DateTimeUtil.DATE_PATTERN);
				if (StringUtils.equals(preWeekDay, preDateStr))
					break;

				CanaCalendar dbCalendar = dbCalendarMap.get(preDateStr);
				if (dbCalendar == null) {
					dbCalendar = new CanaCalendar();
					dbCalendar.setDate(preDateStr);
					dbCalendar.setIsHoliday(preDateTime.getDayOfWeek() == 6 || preDateTime.getDayOfWeek() == 7);
					dbCalendarMap.put(preDateStr, dbCalendar);
				}
				if (!dbCalendar.getIsHoliday()) {
					preWeekDay = dbCalendar.getDate();
					break;
				}
				beforeFirstWeekday++;
			}

			int notBeforeFirstWeekday = 0;
			while (true) {
				DateTime nextDateTime = changedDate.plusDays(notBeforeFirstWeekday);
				String nextDateStr = DateTimeUtil.format(nextDateTime, DateTimeUtil.DATE_PATTERN);
				if (nextWeekDay != null && nextDateStr.equals(nextWeekDay))
					break;

				CanaCalendar dbCalendar = dbCalendarMap.get(nextDateStr);
				if (dbCalendar == null) {
					dbCalendar = new CanaCalendar();
					dbCalendar.setDate(nextWeekDay);
					dbCalendar.setIsHoliday(nextDateTime.getDayOfWeek() == 6 || nextDateTime.getDayOfWeek() == 7);
					dbCalendarMap.put(nextDateStr, dbCalendar);
				}
				if (!dbCalendar.getIsHoliday()) {
					nextDateStr = dbCalendar.getDate();
					break;
				}
				notBeforeFirstWeekday++;
			}

			ChangedCalendar e = new ChangedCalendar();
			e.setDate(changedCalendar.getDate());
			e.setBeforeFirstWeekday(beforeFirstWeekday);
			e.setNotBeforeFirstWeekday(notBeforeFirstWeekday);
			returnValue.add(e);
		}
		return returnValue;
	}

	@Override
	public int getNotBeforeFirstWeekday(String dateStr) {
		if(!DateTimeUtil.validateDate10(dateStr))
			throw new RuntimeException("格式错误");
		CanaCalendarExample canaCalendarExample = new CanaCalendarExample();
		canaCalendarExample.createCriteria().andDateGreaterThanOrEqualTo(dateStr);
		canaCalendarExample.setOrderByClause("date");
		return getFirstWeekDay(canaCalendarExample, dateStr, 0, 1);
	}

	@Override
	public int getBeforeFirstWeekday(String dateStr) {
		if(!DateTimeUtil.validateDate10(dateStr))
			throw new RuntimeException("格式错误");
		CanaCalendarExample canaCalendarExample = new CanaCalendarExample();
		canaCalendarExample.createCriteria().andDateLessThan(dateStr);
		canaCalendarExample.setOrderByClause("date desc");
		return getFirstWeekDay(canaCalendarExample, dateStr, 1, -1);
	}

	@Override
	public void saveCalendar(List<List<String>> calendarData, String userId) {
		for (List<String> calendar : calendarData) {
			CanaCalendar canaCalendar = new CanaCalendar();
			canaCalendar.setDate(calendar.get(0) + "-" + (calendar.get(1).length() < 2 ? "0" + calendar.get(1) : calendar.get(1))  + "-" + (calendar.get(2).length() < 2 ? "0" + calendar.get(2) : calendar.get(2)));
			canaCalendar.setYear(Integer.valueOf(calendar.get(0)));
			canaCalendar.setMonth(Integer.valueOf(calendar.get(1)));
			canaCalendar.setDay(Integer.valueOf(calendar.get(2)));
			canaCalendar.setDayOfWeek(calendar.get(3));
			canaCalendar.setIsHoliday("是".equals(calendar.get(4)));
			canaCalendar.setDescription(calendar.get(5));
			canaCalendar.setCreateTime(new Date());
			canaCalendar.setHolidayUpateTime(canaCalendar.getCreateTime());
			canaCalendar.setOperatorId(userId);
			canaCalendar.setUpdateTime(canaCalendar.getCreateTime());
			if(canaCalendar.getYear() == null || canaCalendar.getMonth() == null || canaCalendar.getDay() == null)
				continue;
			CanaCalendar oldCanaCalendar = canaCalendarMapper.selectByPrimaryKey(canaCalendar.getDate());
			if(oldCanaCalendar == null)
				canaCalendarMapper.insertSelective(canaCalendar);
			else {
				canaCalendar.setCreateTime(oldCanaCalendar.getCreateTime());
				if(!canaCalendar.getIsHoliday().equals(oldCanaCalendar.getIsHoliday()))
					canaCalendarMapper.updateByPrimaryKey(canaCalendar);
				else if(!canaCalendar.getYear().equals(oldCanaCalendar.getYear()) || !canaCalendar.getMonth().equals(oldCanaCalendar.getMonth()) || !canaCalendar.getDay().equals(oldCanaCalendar.getDay()) || (canaCalendar.getDayOfWeek() != null && !canaCalendar.getDayOfWeek().equals(oldCanaCalendar.getDayOfWeek())) || !canaCalendar.getDescription().equals(oldCanaCalendar.getDescription())) {
					canaCalendar.setHolidayUpateTime(oldCanaCalendar.getHolidayUpateTime());
					canaCalendarMapper.updateByPrimaryKey(canaCalendar);
				}
			}
		}
	}

	@Override
	public void modify(CanaCalendarDTO canaCalendarDTO, String uesrId) {
		CanaCalendar canaCalendar = canaCalendarMapper.lockByPrimaryKey(canaCalendarDTO.getDate());
		if(canaCalendar == null)
			throw WebException.instance("不存在该日期");
		boolean save = false;
		if((canaCalendarDTO.getDescription() != null || canaCalendar.getDescription() != null) && (canaCalendarDTO.getDescription() == null || canaCalendar.getDescription() == null || !canaCalendarDTO.getDescription().equals(canaCalendar.getDescription()))) {
			canaCalendar.setDescription(canaCalendarDTO.getDescription());
			canaCalendar.setUpdateTime(new Date());
			save = true;
		}
		if(!canaCalendarDTO.getIsHoliday().equals(canaCalendar.getIsHoliday())) {
			canaCalendar.setIsHoliday(canaCalendarDTO.getIsHoliday());
			canaCalendar.setUpdateTime(new Date());
			canaCalendar.setHolidayUpateTime(canaCalendar.getUpdateTime());
			save = true;
		}
		if(save)
			canaCalendarMapper.updateByPrimaryKeySelective(canaCalendar);
	}

	@Override
	public boolean isWeekday(String dateStr) {
		if(!DateTimeUtil.validateDate10(dateStr))
			throw new RuntimeException("格式错误");
		CanaCalendar canaCalendar = canaCalendarMapper.selectByPrimaryKey(dateStr);
		if(canaCalendar == null) {
			int week = DateUtils.getWeek(DateTimeUtil.parseDate10(dateStr).toDate());
			return week != 7 && week != 1;
		} else
			return !canaCalendar.getIsHoliday();
	}
	
	private int getFirstWeekDay(CanaCalendarExample canaCalendarExample, String dateStr, int diffInDay, int coefficient) {
		canaCalendarExample.setLimitStart(0);
		canaCalendarExample.setLimitEnd(15);
		List<CanaCalendar> canaCalendars = canaCalendarMapper.selectByExample(canaCalendarExample);
		short index = 0;
		do {
			if(canaCalendars.size() > index) {
				CanaCalendar canaCalendar = canaCalendars.get(index);
				if(DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(dateStr), DateTimeUtil.parseDate10(canaCalendar.getDate())) == diffInDay) {
					if(canaCalendar.getIsHoliday()) {
						++diffInDay;
						++index;
						continue;
					}
					return diffInDay;
				}
			}
			int week = DateUtils.getWeek(DateTimeUtil.parseDate10(DateTimeUtil.addDay10(dateStr, diffInDay * coefficient)).toDate());
			if(week == 7 || week == 1) {
				++diffInDay;
				continue;
			}
			return diffInDay;
		} while (true);
	}

}

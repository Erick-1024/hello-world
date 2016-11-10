package com.cana.setting.service.transaction;

import java.util.Date;
import java.util.List;

import com.cana.vbam.common.setting.dto.CanaCalendarDTO;
import com.cana.vbam.common.setting.dto.ChangedCalendar;

public interface ICanaCalendarTransactionService {

	/**
	 * 根据时间段获取该时间段内的所有节假日属性发生变化的日期以及相关值
	 * @param startDate 开始时间点(包括)
	 * @param endDate 结束时间点（不包括）
	 * @return
	 */
	public List<ChangedCalendar> getChangedDate(Date startDate, Date endDate);

	/**
	 * 仅用于测试使用
	 */
	public List<ChangedCalendar> getChangedDateForTest(Date startDate, Date endDate);
	
	/**
	 * 获取某个日期之后的第一个工作日（包括当天）
	 * @param dateStr yyyy-MM-dd
	 * @return 返回距离dateStr的天数差(绝对值)
	 */
	public int getNotBeforeFirstWeekday(String dateStr);
	
	/**
	 * 获取某个日期之前的第一个工作日（不包括当天）
	 * @param dateStr yyyy-MM-dd
	 * @return 返回距离dateStr的天数差（绝对值）
	 */
	public int getBeforeFirstWeekday(String dateStr);
	
	public void saveCalendar(List<List<String>> calendarData, String userId);
	
	public void modify(CanaCalendarDTO canaCalendarDTO, String uesrId);
	
	/**
	 * 判断是否是工作日
	 * @param dateStr　yyyy-MM-dd
	 * @return
	 */
	public boolean isWeekday(String dateStr);
	
}

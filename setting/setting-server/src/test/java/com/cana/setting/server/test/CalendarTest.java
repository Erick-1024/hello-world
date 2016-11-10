package com.cana.setting.server.test;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.setting.service.transaction.ICanaCalendarTransactionService;
import com.cana.vbam.common.setting.dto.ChangedCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml"})
public class CalendarTest {

	@Resource
	private ICanaCalendarTransactionService transactionServiceImpl;
	
	@Test
	public void after() throws Exception {
		System.out.println(transactionServiceImpl.getNotBeforeFirstWeekday("2016-12-31"));
	}
	
	@Test
	public void before() throws Exception {
		System.out.println(transactionServiceImpl.getBeforeFirstWeekday("2016-09-18"));
	}
	
	@Test
	public void test() {
		Calendar startDate = Calendar.getInstance();
		startDate.set(2016, 8, 21, 0, 0, 27);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2016, 8, 21, 16, 0, 30);
		List<ChangedCalendar> list = transactionServiceImpl.getChangedDate(startDate.getTime(), endDate.getTime());
		List<ChangedCalendar> list2 = transactionServiceImpl.getChangedDateForTest(startDate.getTime(), endDate.getTime());
		if (list.size() != list2.size())
			System.out.println("数量不一致");
		for (int index = 0; index < list.size(); ++index) {
			ChangedCalendar changed1 = list.get(index);
			System.out.println(changed1.getDate());
			System.out.println(changed1.getBeforeFirstWeekday());
			System.out.println(changed1.getNotBeforeFirstWeekday());

			ChangedCalendar changed2 = list2.get(index);
			if (!changed1.getDate().equals(changed2.getDate())
					|| changed1.getBeforeFirstWeekday().intValue() != changed2.getBeforeFirstWeekday().intValue()
					|| changed1.getNotBeforeFirstWeekday().intValue() != changed2.getNotBeforeFirstWeekday().intValue())
				System.out.println("可能有误");
			System.out.println("----------------------------");
		}
	}
	
	@Test
	public void testIsWeekday() {
		System.out.println(transactionServiceImpl.isWeekday("2016-10-26"));
	}
}

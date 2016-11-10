package com.cana.report.scheduler.test.fund;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.vbam.common.consts.ReportConstant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/report-scheduler-*.xml")
public class TestSomething {
	
	@Resource
	private PropertiesMapper propertiesMapper;

	@Test
	public void test() {
		Properties properties = propertiesMapper.selectByPrimaryKey(ReportConstant.MONITOR_UPDATE_TIME);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date today = calendar.getTime();
		updateProperties(properties, today);
	}

	public void updateProperties(Properties properties, Date today) {
		properties.setValue("2016-05-02");
		properties.setUpdateTime(new Date());
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}
	
}

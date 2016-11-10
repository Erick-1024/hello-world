package com.travelzen.framework.retry.dao.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskBackOffPolicy;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;
import com.travelzen.framework.retry.dict.RetryTaskType;


@ContextConfiguration(locations = { "classpath*:spring/retry-*.xml"})
public class RetryTaskDaoTest extends AbstractJUnit4SpringContextTests {
	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	@BeforeClass
	public static void setSystemProps() {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
	}
	@Test
	public void insert() throws SQLException {
		RetryTask task = new RetryTask();
		task.setTaskType(RetryTaskType.CREDIT_LIMIT_EFFECT.name());
		task.setTaskId(RandomStringUtils.randomNumeric(10));
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setMaxAttempts(10L);
		retryTaskMapper.insertSelective(task);
	}
}

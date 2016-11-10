package com.cana.repayment.service.transaction.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskBackOffPolicy;
import com.travelzen.framework.retry.dict.RetryTaskPolicy;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-repayment-service-*.xml")
public class RetryTaskDaoTest {
	@Resource
	private RetryTaskMapper retryTaskMapper;
	
	
	@Test
	public void insert() throws SQLException {
		RetryTask task = new RetryTask();
		task.setTaskType("TEST");
		task.setTaskId(RandomStringUtils.randomNumeric(10));
		task.setBackoffPolicy(RetryTaskBackOffPolicy.fixed.name());
		task.setRetryPolicy(RetryTaskPolicy.simple.name());
		task.setMaxAttempts(10L);
		retryTaskMapper.insertSelective(task);
	}
}

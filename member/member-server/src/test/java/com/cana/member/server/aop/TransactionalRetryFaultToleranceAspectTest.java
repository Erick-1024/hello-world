package com.cana.member.server.aop;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.member.service.transaction.IMockTransactionService;
import com.cana.member.service.transaction.impl.MockTransactionServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/member-server-*.xml")
public class TransactionalRetryFaultToleranceAspectTest {

	@Resource
	private IMockTransactionService service;
	
	@Resource
	private PropertiesMapper mapper;
	
	@Test
	public void addNewProperty() {
		String propertyName = RandomStringUtils.randomNumeric(10);
		String propertyValue = "1";
		try{
			service.addNewProperty(propertyName, propertyValue);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("重试次数:" + MockTransactionServiceImpl.retryNum);
		assertTrue("事务重试", MockTransactionServiceImpl.retryNum.get() > 1);;
	}


}

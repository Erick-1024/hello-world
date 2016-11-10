package com.cana.test.transaction1.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.test.service1.transaction.ITransactionService1;
import com.cana.test.service1.util.SpringApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test-service1-*.xml","classpath:spring/test-service1-context.xml"})
public class Transaction1ServiceImplTest {

	@Resource
	private ITransactionService1 service;
	
	@Test
	public void addNewProperty() throws Exception{
		System.out.println(SpringApplicationContext.getApplicationContext().getBeanNamesForType(ITransactionService1.class)[0]);
		String propertyName = RandomStringUtils.randomNumeric(10);
        String propertyValue = "1";
        service.addNewProperty(propertyName, propertyValue);
	}

}

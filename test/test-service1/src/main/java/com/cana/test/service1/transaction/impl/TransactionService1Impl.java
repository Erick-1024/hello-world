package com.cana.test.service1.transaction.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.cana.test.dao1.mapper.gen.Properties1Mapper;
import com.cana.test.dao1.po.Properties1;
import com.cana.test.service1.transaction.ITransactionService1;

@Service
public class TransactionService1Impl implements ITransactionService1{

	@Resource
	private Properties1Mapper mapper;
	
//	@Resource
//	private ITransactionService2 service2;
	
	@Override
	public void addNewProperty(String propertyName, String propertyValue) throws Exception {
		Properties1 properties1 = new Properties1();
		properties1.setId(RandomStringUtils.randomAlphanumeric(10));
		properties1.setName(propertyName);
		properties1.setValue(propertyValue);
		mapper.insert(properties1);
		System.out.println("here");
//		service2.addNewProperty(propertyName, propertyValue);
//		mapper.insert(properties1);
	}

}

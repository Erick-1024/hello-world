package com.cana.test.service2.transaction.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.cana.test.dao2.mapper.gen.Properties2Mapper;
import com.cana.test.dao2.po.Properties2;
import com.cana.test.service2.transaction.ITransactionService2;

@Service
public class TransactionService2Impl implements ITransactionService2{

	@Resource
	private Properties2Mapper mapper;
	
	@Override
	public void addNewProperty(String propertyName, String propertyValue) throws Exception {
		Properties2 properties2 = new Properties2();
		properties2.setId(RandomStringUtils.randomAlphanumeric(10));
		properties2.setName(propertyName);
		properties2.setValue(propertyValue);
		mapper.insert(properties2);
		System.out.println("here22");
	}

}

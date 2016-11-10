/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.flight.finance.scheduler.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.flight.finance.dao.utils.IDGenerator;

/**
 * @author ducer
 *
 */
@ContextConfiguration("classpath:spring/flight-finance-*.xml")
public class TestIDGenerator extends AbstractJUnit4SpringContextTests{
	@Resource
	private PropertiesMapper p;
	@Test
	public void generator(){
		Properties pp = new Properties();
		pp.setName("test-generate-id");
		pp.setValue("0");
		try{
			p.insertSelective(pp);
		}catch(DuplicateKeyException e){
			System.out.println("======================");
		}
		System.out.println(IDGenerator.generateTest());
	}

}

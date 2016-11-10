package com.cana.member.server.service.transaction.impl;

import static org.junit.Assert.assertNull;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.member.service.transaction.IUserTransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/member-server-*.xml")
public class UserTransactionServiceImplTest {

	@Resource
	private IUserTransactionService service;
	
	@Test
	public void addNewProperty() {
		service.updateCustomerCertDN("201605090049", "test");
	}
	

}

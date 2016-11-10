package com.cana.account.server.test.transaction;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.account.service.transaction.IAccountTransactionService;

@ContextConfiguration(locations = "classpath:META-INF/spring/account-server-*.xml")
public class AccountTransactionTest extends AbstractJUnit4SpringContextTests{

	@Resource
	IAccountTransactionService accountTransactionServiceImpl;
	@Test
	public void test() {
		try {
//			accountTransactionServiceImpl.createAccountByOwner("201511131101");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

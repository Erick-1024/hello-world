package com.cana.account.dao.mapper.gen;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.dao.po.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/account-dao-*.xml")
public class AccountMapperTest {
	
	@Resource
	private AccountMapper mapper;
	
	@Test
	public void insert() {
		Account p = new Account();
		p.setId(RandomStringUtils.randomNumeric(10));
		mapper.insertSelective(p);
	}
	
//	@Test
	public void pagination(){
//		PermissionExample example = new PermissionExample();
//		example.setOrderByClause("id");
//		example.setLimitStart(0);
//		example.setLimitEnd(1);
//		for(Permission p : mapper.selectByExample(example)){
//			System.out.println(p.getId());
//		}
	}

}

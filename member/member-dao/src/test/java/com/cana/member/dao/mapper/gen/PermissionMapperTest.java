package com.cana.member.dao.mapper.gen;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.member.dao.po.Permission;
import com.cana.member.dao.po.PermissionExample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/member-dao-*.xml")
public class PermissionMapperTest {
	
	@Resource
	private PermissionMapper mapper;
	
	@Test
	public void insert() {
		Permission p = new Permission();
		p.setId(RandomStringUtils.randomNumeric(10));
		p.setName(p.getId());
		p.setType("MODULE");
		p.setOrd(100);
		mapper.insertSelective(p);
	}
	
	@Test
	public void pagination(){
		PermissionExample example = new PermissionExample();
		example.setOrderByClause("id");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		for(Permission p : mapper.selectByExample(example)){
			System.out.println(p.getId());
		}
	}

}

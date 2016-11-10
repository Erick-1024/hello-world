package com.cana.common.dao.mapper.gen;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.common.dao.po.Properties;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/common-dao-*.xml")
public class PropertiesTest {
	
	@Resource
	private PropertiesMapper mapper;
	
	@Test
	public void insert() {
		Properties p = new Properties();
		p.setName(RandomStringUtils.randomNumeric(10));
		p.setValue("1");
		mapper.insertSelective(p);
	}

}

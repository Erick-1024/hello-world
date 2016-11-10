package com.cana.credit.server.test;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.credit.dao.mapper.gen.OutCustomerMapper;
import com.cana.credit.dao.po.OutCustomerExample;
import com.cana.vbam.common.credit.enums.Institution;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class OutCustomerTest {

	@Resource
	OutCustomerMapper outCustomerMapper;
	
	@Test
	public void test(){
		OutCustomerExample outCustomerExample = new OutCustomerExample();
		outCustomerExample.createCriteria().andOutCustomerIdEqualTo("52a8139845ce8c36d879974e").andInstitutionIdEqualTo(Institution.travelzen.name());

		outCustomerExample.or().andCompanyNameEqualTo("欧瑟亚集团").andInstitutionIdEqualTo(Institution.travelzen.name());
		System.out.println(new Gson().toJson(outCustomerMapper.selectByExample(outCustomerExample)));
		System.out.println(UUID.randomUUID().toString());
	}
}

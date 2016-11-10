package com.cana.asset.dao.mapper;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.vbam.common.member.enums.user.UserType;
import com.google.gson.Gson;

@ContextConfiguration("classpath:spring/test-asset-dao-datasource.xml")
public class ABSDataPrivilegeMapperTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private ABSDataPrivilegeMapper mapper;
	
	@Test
	public void allowedProjectList() throws Exception{
		System.out.println(new Gson().toJson(mapper.allowedProjectList(UserType.OTHERORG, "上海凯拿")));
	}
	
}

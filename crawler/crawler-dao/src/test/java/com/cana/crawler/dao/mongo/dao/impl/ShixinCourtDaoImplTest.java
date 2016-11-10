package com.cana.crawler.dao.mongo.dao.impl;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.crawler.dao.mongo.dao.IShixinCourtDao;
import com.cana.crawler.dao.mongo.entity.ShixinCourt;
import com.cana.vbam.common.crawler.dto.GetShixinCourtRequest;
import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;
import com.google.gson.Gson;

@ContextConfiguration(locations = { "classpath*:spring/crawler-dao*.xml" })
public class ShixinCourtDaoImplTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IShixinCourtDao<ShixinCourt, ObjectId> dao;
	
	@Test
	public void getShixinCourt() throws Exception{
		GetShixinCourtRequest request = new GetShixinCourtRequest();
		System.out.println(new Gson().toJson(dao.getShixinCourt(request)));
	}
	
	@Test
	public void create() throws Exception{
		ShixinCourt shixinCourt = new ShixinCourt();
		shixinCourt.setSubject(CourtExecutionSubject.individual);
		shixinCourt.setName("任水");
		shixinCourt.setCode("370983198311065830");
		shixinCourt.setCreateDate(new DateTime());
		dao.create(shixinCourt);
	}

}

package com.cana.crawler.dao.mongo.dao.impl;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.crawler.dao.mongo.dao.IShixinCourtBlackListDao;
import com.cana.crawler.dao.mongo.entity.ShixinCourtBlackList;
import com.cana.vbam.common.crawler.enums.CourtExecutionSubject;

@ContextConfiguration(locations = { "classpath*:spring/crawler-dao*.xml" })
public class ShixinCourtBlackListDaoImplTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IShixinCourtBlackListDao<ShixinCourtBlackList, ObjectId> dao;
	
	@Test
	public void create() throws Exception{
		ShixinCourtBlackList shixinCourt = new ShixinCourtBlackList();
		shixinCourt.setSubject(CourtExecutionSubject.individual);
		shixinCourt.setName("任水");
		shixinCourt.setCode("370983198311065830");
		shixinCourt.setCreateDate(new DateTime());
		dao.create(shixinCourt);
	}

}

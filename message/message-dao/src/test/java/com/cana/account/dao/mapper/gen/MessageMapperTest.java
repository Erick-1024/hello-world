package com.cana.account.dao.mapper.gen;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.message.dao.mapper.gen.NotificationMapper;
import com.cana.message.dao.po.Notification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/message-dao-*.xml")
public class MessageMapperTest {
	
	@Resource
	private NotificationMapper mapper;
	
	@Test
	public void insert() {
	    Notification m = new Notification();
		m.setId("asdfa");
		m.setType("a");
		m.setSendUserId("a");
		m.setContent("s");
		m.setReceiveCustomerId("a");
		mapper.insertSelective(m);
	}

}

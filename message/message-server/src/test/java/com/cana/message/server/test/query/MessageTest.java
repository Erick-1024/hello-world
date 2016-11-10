package com.cana.message.server.test.query;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.message.dao.po.Notification;
import com.cana.message.server.service.transaction.INotificationTransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/message-server-*.xml")
public class MessageTest {
	
	@Resource
	private INotificationTransactionService notificationService;
	
	@Test
	public void queryTest() {

	}
	

}

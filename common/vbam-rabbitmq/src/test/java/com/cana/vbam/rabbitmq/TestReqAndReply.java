package com.cana.vbam.rabbitmq;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/vbam-rabbitmq-*.xml" })
public class TestReqAndReply {

	@Resource(name = "testRequestAndReplyRabbitTemplate")
	private RabbitTemplate template;

	@Test
	public void test() throws Exception {
		System.out.println(template.convertSendAndReceive("foo"));
		Thread.sleep(3 * 1000);
	}

}

package com.cana.vbam.rabbitmq.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.vbam.rabbitmq.PojoListener;

@Configuration
public class TestRabbitMQConfiguration extends CommonConfiguration {
	
	private static final String ROUTING_KEY_TEST_REQ_AND_REPLY = "test.req_and_reply";
	
	@Bean
	public RabbitTemplate testRequestAndReplyRabbitTemplate() throws Exception{
		RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory());
		template.setExchange(testExchange().getName());
		template.setRoutingKey(ROUTING_KEY_TEST_REQ_AND_REPLY);
		template.setReplyQueue(testReplyQueue());
		template.setReplyTimeout(TimeUnit.MINUTES.toMillis(3));
		return template;
	}
	
	@Bean
	public DirectExchange testExchange() {
		return new DirectExchange("test", true, false);
	}
	
	@Bean
	public DirectExchange testDeadletterExchange() {
		return new DirectExchange("test-deadletter", true, false);
	}
	
	@Bean
	public Queue testRequestQueue() {
		Queue queue = new Queue("test.requestQueue", true, false, false);
		return queue;
	}
	
	@Bean
	public Queue testReplyQueue() {
		Queue queue = new Queue("test.replyQueue", true, false, false);
		return queue;
	}
	
	@Bean
	public SimpleMessageListenerContainer testReplyListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(rabbitConnectionFactory());
		container.setQueues(testReplyQueue());
		container.setMessageListener(testRequestAndReplyRabbitTemplate());
		return container;
	}
	
	

	
	@Bean
	public SimpleMessageListenerContainer testServiceListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(rabbitConnectionFactory());
		container.setQueues(testRequestQueue());
		container.setMessageListener(new MessageListenerAdapter(new PojoListener()));
		return container;
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(testRequestQueue()).to(testExchange()).with(ROUTING_KEY_TEST_REQ_AND_REPLY);
	}
	
	
}

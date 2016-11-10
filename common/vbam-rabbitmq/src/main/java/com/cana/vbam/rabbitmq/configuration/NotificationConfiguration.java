package com.cana.vbam.rabbitmq.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class NotificationConfiguration extends CommonConfiguration{
	
    public static final String ROUTING_KEY_NOTIFICATION = "message.notification";
	
	@Bean
	public DirectExchange messageExchange() {
		return new DirectExchange("message", true, false);
	}
	
	@Bean
	public DirectExchange messageDeadLetterExchange() {
		return new DirectExchange(messageExchange().getName() + "-deadletter", true, false);
	}
	
	@Bean
	public Queue notificationQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", messageDeadLetterExchange().getName());
		Queue queue = new Queue("message.notification", true, false, false, queueArguments);
		return queue;
	}
	
	@Bean
	public Queue notificationDeadLetterQueue(){
		Queue queue = new Queue(notificationQueue().getName()+"-deadletter");
		return queue;
	}
	
	@Bean
	public Binding binding1() {
		return BindingBuilder.bind(notificationQueue()).to(messageExchange()).with(ROUTING_KEY_NOTIFICATION);
	}

	@Bean
	public Binding binding2() {
		return BindingBuilder.bind(notificationDeadLetterQueue()).to(messageDeadLetterExchange()).with(ROUTING_KEY_NOTIFICATION);
	}

	
}

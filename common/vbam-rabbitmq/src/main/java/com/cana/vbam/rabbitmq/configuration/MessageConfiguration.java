package com.cana.vbam.rabbitmq.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class MessageConfiguration extends CommonConfiguration{
	
	public static final String ROUTING_KEY_MAIL = "message.mail";
	
	@Bean
	public DirectExchange messageExchange() {
		return new DirectExchange("message", true, false);
	}
	
	@Bean
	public DirectExchange messageDeadLetterExchange() {
		return new DirectExchange(messageExchange().getName() + "-deadletter", true, false);
	}
	
	@Bean
	public Queue mailQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", messageDeadLetterExchange().getName());
		Queue queue = new Queue("message.mail", true, false, false, queueArguments);
		return queue;
	}
	
	@Bean
	public Queue mailDeadLetterQueue(){
		Queue queue = new Queue(mailQueue().getName()+"-deadletter");
		return queue;
	}
	
	@Bean
	public Binding messageConfigurationBinding1() {
		return BindingBuilder.bind(mailQueue()).to(messageExchange()).with(ROUTING_KEY_MAIL);
	}

	@Bean
	public Binding messageConfigurationBinding2() {
		return BindingBuilder.bind(mailDeadLetterQueue()).to(messageDeadLetterExchange()).with(ROUTING_KEY_MAIL);
	}

	
}

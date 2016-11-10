package com.cana.message.client.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.vbam.rabbitmq.configuration.MessageConfiguration;

@Configuration
public class MessageClientConfiguration extends MessageConfiguration{

	@Bean
	public RabbitTemplate mailRabbitTemplate() throws Exception{
		RabbitTemplate template = super.rabbitTemplate();
		template.setExchange(messageExchange().getName());
		template.setRoutingKey(ROUTING_KEY_MAIL);
		return template;
	}
	
}

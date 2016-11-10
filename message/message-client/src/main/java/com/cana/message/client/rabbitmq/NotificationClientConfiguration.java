package com.cana.message.client.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.vbam.rabbitmq.configuration.NotificationConfiguration;

@Configuration
public class NotificationClientConfiguration extends NotificationConfiguration{

	@Bean
	public RabbitTemplate notificationRabbitTemplate() throws Exception{
		RabbitTemplate template = super.rabbitTemplate();
		template.setExchange(messageExchange().getName());
		template.setRoutingKey(ROUTING_KEY_NOTIFICATION);
		return template;
	}
	
}

package com.cana.message.client.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.vbam.rabbitmq.configuration.CommonConfiguration;

@Configuration
public class SmsMessageMQProducerConfiguration extends CommonConfiguration{

	@Bean
	public RabbitTemplate smsMessageRabbitTemplate() throws Exception{
		RabbitTemplate template = super.rabbitTemplate();
		template.setExchange(messageExchange().getName());
		template.setRoutingKey(CommonConfiguration.ROUTING_KEY_SMS_MESSAGE);
		return template;
	}
	
}

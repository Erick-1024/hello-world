package com.cana.repayment.service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.vbam.rabbitmq.configuration.CommonConfiguration;

@Configuration
public class RepaymentMQProducerConfiguration extends CommonConfiguration{
	

	@Bean
	public RabbitTemplate repaymentRabbitTemplate() throws Exception{
		RabbitTemplate template = super.rabbitTemplate();
		template.setExchange(repaymentExchange().getName());
		return template;
	}
	
}

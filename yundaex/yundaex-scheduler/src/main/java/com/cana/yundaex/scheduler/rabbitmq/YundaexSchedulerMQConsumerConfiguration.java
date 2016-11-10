package com.cana.yundaex.scheduler.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.vbam.rabbitmq.configuration.CommonConfiguration;
import com.cana.yundaex.scheduler.rabbitmq.handler.MQMessageDispatcher;

@Configuration
public class YundaexSchedulerMQConsumerConfiguration extends CommonConfiguration{
	
	@Bean
	protected SimpleMessageListenerContainer reportListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = super.messageListenerContainer();
		container.setAutoStartup(true);
		container.setQueues(yundaexQueue());
		container.setMessageListener(yundaexListenerAdapter());
		container.setDefaultRequeueRejected(false);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(3);
		container.setTaskExecutor(listenerContainerTaskExecutor());
		return container;
	}

	@Bean
	public Queue yundaexQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", yundaexDeadLetterExchange().getName());
		Queue queue = new Queue("yundaex", true, false, false, queueArguments);
		return queue;
	}
	
	@Bean
	public Queue yundaexDeadLetterQueue(){
		Queue queue = new Queue(yundaexQueue().getName()+"-deadletter");
		return queue;
	}
	
	public MessageListenerAdapter yundaexListenerAdapter() throws Exception{
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(yundaexHandler(), jsonMessageConverter());
		listenerAdapter.setDefaultListenerMethod("handleMessage");
		return listenerAdapter;
	}

	private Object yundaexHandler() {
		return new MQMessageDispatcher();
	}

	@Bean
	public Binding yundaexSchedulerBinding1() {
		return BindingBuilder.bind(yundaexQueue()).to(repaymentExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}


}

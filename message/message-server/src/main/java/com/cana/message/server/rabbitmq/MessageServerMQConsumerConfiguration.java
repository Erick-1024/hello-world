package com.cana.message.server.rabbitmq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.message.server.handler.MQMessageDispatcher;
import com.cana.vbam.rabbitmq.configuration.CommonConfiguration;

@Configuration
public class MessageServerMQConsumerConfiguration extends CommonConfiguration{
	
	@Bean
	protected SimpleMessageListenerContainer defaultMessageListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = super.messageListenerContainer();
		container.setAutoStartup(true);
		container.setQueues(messageServerDefaultQueue());
		container.setMessageListener(reportListenerAdapter());
		container.setDefaultRequeueRejected(false);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(3);
		container.setTaskExecutor(listenerContainerTaskExecutor());
		return container;
	}

	@Bean
	public Queue messageServerDefaultQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", messageServerDeadLetterExchange().getName());
		Queue queue = new Queue("message-server-default", true, false, false, queueArguments);
		return queue;
	}
	
	@Bean
	public Queue messageServerDefaultDeadLetterQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", messageServerDeadLetterRecoveryExchange().getName());
		queueArguments.put("x-message-ttl", TimeUnit.SECONDS.toMillis(10));
		Queue queue = new Queue(messageServerDefaultQueue().getName()+"-deadletter", true, false, false, queueArguments);
		return queue;
	}
	
	public MessageListenerAdapter reportListenerAdapter() throws Exception{
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(messageHandler(), jsonMessageConverter());
		listenerAdapter.setDefaultListenerMethod("handleMessage");
		return listenerAdapter;
	}

	private Object messageHandler() {
		return new MQMessageDispatcher();
	}
	
	@Bean
	public Binding  messageServerBinding1() {
		return BindingBuilder.bind(messageServerDefaultDeadLetterQueue()).to(reportDeadLetterExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}
	
	@Bean
	public Binding messageServerBinding2() {
		return BindingBuilder.bind(messageServerDefaultQueue()).to(repaymentExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}
	
	@Bean
	public Binding messageServerBinding3() {
		return BindingBuilder.bind(messageServerDefaultQueue()).to(messageServerDeadLetterRecoveryExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}

}

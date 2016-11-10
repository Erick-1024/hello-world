package com.cana.repayment.service.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.repayment.scheduler.handler.MQMessageDispatcher;
import com.cana.vbam.rabbitmq.configuration.CommonConfiguration;

@Configuration
public class RepaymentSchedulerMQConsumerConfiguration extends CommonConfiguration{
	
	@Bean
	protected SimpleMessageListenerContainer defaultMessageListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = super.messageListenerContainer();
		container.setAutoStartup(true);
		container.setQueues(repaymentSuccessNotifyQueue());
		container.setMessageListener(repaymentSchedulerListenerAdapter());
		container.setDefaultRequeueRejected(false);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(3);
		container.setTaskExecutor(listenerContainerTaskExecutor());
		return container;
	}

	@Bean
	public Queue repaymentSuccessNotifyQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", messageServerDeadLetterExchange().getName());
		Queue queue = new Queue("repayment-success-notify", true, false, false, queueArguments);
		return queue;
	}
	
	@Bean
	public Queue repaymentSchedulerDefaultDeadLetterQueue(){
		Queue queue = new Queue(repaymentSuccessNotifyQueue().getName()+"-deadletter");
		return queue;
	}
	
	public MessageListenerAdapter repaymentSchedulerListenerAdapter() throws Exception{
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(messageHandler(), jsonMessageConverter());
		listenerAdapter.setDefaultListenerMethod("handleMessage");
		return listenerAdapter;
	}

	private Object messageHandler() {
		return new MQMessageDispatcher();
	}
	
	@Bean
	public Binding repaymentSuccessNotifyBinding1() {
		return BindingBuilder.bind(repaymentSuccessNotifyQueue()).to(repaymentExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}
}

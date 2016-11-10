package com.cana.report.scheduler.rabbitmq;

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

import com.cana.report.scheduler.rabbitmq.handler.MQMessageDispatcher;
import com.cana.vbam.rabbitmq.configuration.CommonConfiguration;

@Configuration
public class ReportSchedulerMQConsumerConfiguration extends CommonConfiguration{
	
	@Bean
	protected SimpleMessageListenerContainer reportListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = super.messageListenerContainer();
		container.setAutoStartup(true);
		container.setQueues(reportQueue());
		container.setMessageListener(reportListenerAdapter());
		container.setDefaultRequeueRejected(false);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(3);
		container.setTaskExecutor(listenerContainerTaskExecutor());
		return container;
	}

	@Bean
	public Queue reportQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", reportDeadLetterExchange().getName());
		Queue queue = new Queue("report", true, false, false, queueArguments);
		return queue;
	}
	
	@Bean
	public Queue reportDeadLetterQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", reportDeadLetterRecoveryExchange().getName());
		queueArguments.put("x-message-ttl", TimeUnit.SECONDS.toMillis(10));
		Queue queue = new Queue(reportQueue().getName()+"-deadletter", true, false, false, queueArguments);
		return queue;
	}
	
	public MessageListenerAdapter reportListenerAdapter() throws Exception{
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(reportHandler(), jsonMessageConverter());
		listenerAdapter.setDefaultListenerMethod("handleMessage");
		return listenerAdapter;
	}

	private Object reportHandler() {
		return new MQMessageDispatcher();
	}
	
	@Bean
	public Binding reportSchedulerBinding1() {
		return BindingBuilder.bind(reportDeadLetterQueue()).to(reportDeadLetterExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}
	
	@Bean
	public Binding reportSchedulerBinding2() {
		return BindingBuilder.bind(reportQueue()).to(repaymentExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}
	
	@Bean
	public Binding reportSchedulerBinding3() {
		return BindingBuilder.bind(reportQueue()).to(reportDeadLetterRecoveryExchange()).with(CommonConfiguration.ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS);
	}

}

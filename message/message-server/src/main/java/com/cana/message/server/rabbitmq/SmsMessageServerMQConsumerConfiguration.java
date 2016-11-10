package com.cana.message.server.rabbitmq;

import java.util.HashMap;
import java.util.Map;

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
public class SmsMessageServerMQConsumerConfiguration extends CommonConfiguration {
	
	@Bean
	protected SimpleMessageListenerContainer smsMessageListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = super.messageListenerContainer();
		container.setAutoStartup(true);
		container.setQueues(smsMessageQueue());
		container.setMessageListener(smsMessageListenerAdapter());
		container.setDefaultRequeueRejected(false);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(3);
		container.setTaskExecutor(listenerContainerTaskExecutor());
		return container;
	}

	public MessageListenerAdapter smsMessageListenerAdapter() throws Exception{
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(smsMessageHandler(), jsonMessageConverter());
		listenerAdapter.setDefaultListenerMethod("handleMessage");
		return listenerAdapter;
	}

	private Object smsMessageHandler() {
		return new MQMessageDispatcher();
	}
	
	@Bean
	public Queue smsMessageQueue(){
		Map<java.lang.String, java.lang.Object> queueArguments = new HashMap<>();
		queueArguments.put("x-dead-letter-exchange", messageDeadLetterExchange().getName());
		Queue queue = new Queue("message.sms", true, false, false, queueArguments);
		return queue;
	}
	
	@Bean
	public Queue smsMessageDeadLetterQueue(){
		Queue queue = new Queue(smsMessageQueue().getName()+"-deadletter");
		return queue;
	}
	
	@Bean
	public Binding smsMessageBinding1() {
		return BindingBuilder.bind(smsMessageQueue()).to(messageExchange()).with(ROUTING_KEY_SMS_MESSAGE);
	}

	@Bean
	public Binding smsMessageBinding2() {
		return BindingBuilder.bind(smsMessageDeadLetterQueue()).to(messageDeadLetterExchange()).with(ROUTING_KEY_SMS_MESSAGE);
	}

}

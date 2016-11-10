package com.cana.message.server.rabbitmq;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.message.server.handler.NotificationHandler;
import com.cana.message.server.service.transaction.INotificationTransactionService;
import com.cana.vbam.rabbitmq.configuration.NotificationConfiguration;

@Configuration
public class NotificationServerConfiguration extends NotificationConfiguration{
	
	@Resource
	private INotificationTransactionService notificationService;
	
	@Bean
	protected SimpleMessageListenerContainer notificationListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = super.messageListenerContainer();
		container.setAutoStartup(true);
		container.setQueues(notificationQueue());
		container.setMessageListener(notificationListenerAdapter());
		container.setDefaultRequeueRejected(false);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(3);
		container.setTaskExecutor(listenerContainerTaskExecutor());
		return container;
	}

	public MessageListenerAdapter notificationListenerAdapter() throws Exception{
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(notificationHandler(), jsonMessageConverter());
		listenerAdapter.setDefaultListenerMethod("handleMessage");
		return listenerAdapter;
	}

	private Object notificationHandler() {
		return new NotificationHandler(notificationService);
	}

}

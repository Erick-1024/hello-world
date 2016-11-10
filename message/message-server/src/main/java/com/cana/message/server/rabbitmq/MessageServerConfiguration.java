package com.cana.message.server.rabbitmq;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cana.message.server.handler.MailHandler;
import com.cana.message.server.service.IMessageHistoryService;
import com.cana.vbam.rabbitmq.configuration.MessageConfiguration;

@Configuration
public class MessageServerConfiguration extends MessageConfiguration{
	
	@Resource(name = "messageHistoryService")
	private IMessageHistoryService messageHistoryService;
	
	@Bean
	protected SimpleMessageListenerContainer mailListenerContainer() throws Exception{
		SimpleMessageListenerContainer container = super.messageListenerContainer();
		container.setAutoStartup(true);
		container.setQueues(mailQueue());
		container.setMessageListener(mailListenerAdapter());
		container.setDefaultRequeueRejected(false);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(3);
		container.setTaskExecutor(listenerContainerTaskExecutor());
		return container;
	}

	public MessageListenerAdapter mailListenerAdapter() throws Exception{
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(mailHandler(), jsonMessageConverter());
		listenerAdapter.setDefaultListenerMethod("handleMessage");
		return listenerAdapter;
	}

	private Object mailHandler() {
		return new MailHandler(messageHistoryService);
	}

}

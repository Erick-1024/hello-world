package com.cana.vbam.rabbitmq.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.DefaultJavaTypeMapper;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.cana.vbam.common.member.dto.permission.PermissionDTO;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

public abstract class CommonConfiguration {
	
	public static final String ROUTING_KEY_PRODUCER_REPAYMENT_SUCCESS = "producer_repayment_success";
	
	public static final String ROUTING_KEY_SMS_MESSAGE = "sms_message";
	
	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		String hostname = TopsConfReader.getConfContent("properties/rabbitmq.properties", "vbam.rabbitmq.server", ConfScope.R);
		String vhost = TopsConfReader.getConfContent("properties/rabbitmq.properties", "vbam.rabbitmq.vhost", ConfScope.R);
		String userName = TopsConfReader.getConfContent("properties/rabbitmq.properties", "vbam.rabbitmq.user", ConfScope.R);
		String password = TopsConfReader.getConfContent("properties/rabbitmq.properties", "vbam.rabbitmq.password", ConfScope.R);
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(hostname);
		connectionFactory.setVirtualHost(vhost);
		connectionFactory.setUsername(userName);
		connectionFactory.setPassword(password);
		connectionFactory.setChannelCacheSize(getChannelCacheSize());
		connectionFactory.setExecutor(connectionFactoryExecutor());
		return connectionFactory;
	}

	protected Executor listenerContainerTaskExecutor(){
		return new SimpleAsyncTaskExecutor("listener-container-task-executor");
	}
	protected Executor connectionFactoryExecutor() {
		ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
		exec.setCorePoolSize(5);
		exec.setQueueCapacity(1000);
		exec.setThreadFactory(new CustomizableThreadFactory("connection-factory-executor"));
		exec.afterPropertiesSet();
		return exec;
	}

	protected int getChannelCacheSize() {
		return 10;
	}

	@Bean
	public RabbitAdmin rabbitAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitConnectionFactory());
		rabbitAdmin.afterPropertiesSet();
		return rabbitAdmin;
	}

	protected RabbitTemplate rabbitTemplate() throws Exception{
		RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory());
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}
	
	protected MessageConverter jsonMessageConverter() throws Exception{
		JsonMessageConverter converter = new JsonMessageConverter();
		DefaultJavaTypeMapper javaTypeMapper = new DefaultJavaTypeMapper();
		Map<String, Class<?>> idClassMapping = new HashMap<>();
		for(Class<?> dtoClass : getAllDTOClass())
			idClassMapping.put(dtoClass.getName(), dtoClass);
		javaTypeMapper.setIdClassMapping(idClassMapping);
		converter.setCreateMessageIds(true);
		converter.setJavaTypeMapper(javaTypeMapper);
		return converter;
	}
	
	public List<Class<?>> getAllDTOClass() throws Exception{
		List<Class<?>> dtoClasses = new ArrayList<>();
		for(ClassInfo classInfo : ClassPath.from(PermissionDTO.class.getClassLoader()).getTopLevelClassesRecursive("com.cana.vbam.common"))
			dtoClasses.add(classInfo.load());
		return dtoClasses;
	} 
	
	protected SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitConnectionFactory());
        return container;
    }
	
	public int getQueueCount(final String name) {
	    DeclareOk declareOk = rabbitAdmin().getRabbitTemplate().execute(new ChannelCallback<DeclareOk>() {
	        public DeclareOk doInRabbit(Channel channel) throws Exception {
	            return channel.queueDeclarePassive(name);
	        }
	    });
	    return declareOk.getMessageCount();
	}
	
	@Bean
	public DirectExchange scheduledExchange() {
		return new DirectExchange("scheduled", true, false);
	}
	
	@Bean
	public TopicExchange reportDeadLetterExchange() {
		return new TopicExchange("report-deadletter-exchange", true, false);
	}

	@Bean
	public TopicExchange yundaexDeadLetterExchange() {
		return new TopicExchange("yundaex-deadletter-exchange", true, false);
	}
	
	@Bean
	public TopicExchange repaymentExchange() {
		return new TopicExchange("repayment", true, false);
	}
	
	@Bean
	public DirectExchange reportDeadLetterRecoveryExchange() {
		return new DirectExchange("report-deadletter-recovery", true, false);
	}

	@Bean
	public DirectExchange yundaexDeadLetterRecoveryExchange() {
		return new DirectExchange("yundaex-deadletter-recovery", true, false);
	}
	
	@Bean
	public DirectExchange messageServerDeadLetterExchange() {
		return new DirectExchange("message-server-deadletter", true, false);
	}
	
	@Bean
	public DirectExchange messageServerDeadLetterRecoveryExchange() {
		return new DirectExchange("message-server-deadletter-recovery", true, false);
	}
	
	@Bean
	public DirectExchange messageExchange() {
		return new DirectExchange("message", true, false);
	}
	
	@Bean
	public DirectExchange messageDeadLetterExchange() {
		return new DirectExchange(messageExchange().getName() + "-deadletter", true, false);
	}
	
}

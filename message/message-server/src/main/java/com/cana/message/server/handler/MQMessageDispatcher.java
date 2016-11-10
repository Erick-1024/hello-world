package com.cana.message.server.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.cana.vbam.common.annotation.MQConsumer;
import com.cana.vbam.common.utils.Constants;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.gson.Gson;

public class MQMessageDispatcher {
	
	private static Map<Class<?>, Class<?>>  handlerClasses;
	
	private static Logger logger = LoggerFactory.getLogger(MQMessageDispatcher.class);
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void handleMessage(Object message) throws Exception {
		try{
			MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
			logger.info("消息数据：" + new Gson().toJson(message));
			if(handlerClasses == null)
				handlerClasses = findAllHandlerClasses();
			Class<?> handlerClass = handlerClasses.get(message.getClass());
			if(handlerClass == null)
				throw new Exception();
			AbstractMQMessageHandler handler = (AbstractMQMessageHandler)handlerClass.getConstructor().newInstance();
			handler.handleMessage(message);
		}catch(Exception e){
			logger.error("处理消息异常", e);
			throw e;
		}finally{
			MDC.clear();
		}
	}
	
	
	private static Map<Class<?>, Class<?>> findAllHandlerClasses() throws Exception{
		Map<Class<?>, Class<?>> classes = new HashMap<>();
		for(ClassInfo classInfo : ClassPath.from(MQMessageDispatcher.class.getClassLoader()).getTopLevelClassesRecursive(MQMessageDispatcher.class.getPackage().getName())){
			Class<?> cls = classInfo.load();
			if(cls.isAnnotationPresent(MQConsumer.class)){
				MQConsumer annotation = cls.getAnnotation(MQConsumer.class);
				classes.put(annotation.value(), classInfo.load());
			}
		}
		return classes;
	}
}

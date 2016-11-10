package com.travelzen.framework.aop;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.travelzen.framework.service.inter.IHelloworld;
@ContextConfiguration(locations = {
		"classpath*:spring/framework-bean.xml",
		"classpath*:spring/test-framework-bean.xml"
		 })
public class TestProfileAspect extends AbstractJUnit4SpringContextTests{
	@Resource
	private IHelloworld helloWorld;
	@Test
	public void test() throws Exception{
		helloWorld.sayHello();
	}

}

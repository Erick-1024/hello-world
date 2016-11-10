package com.cana.member.api;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.MDC;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class TestDubbo extends AbstractJUnit4SpringContextTests {
	
	@Resource(name="sayHello")
	private ISayHello proxy;

	
	@Test
	public void sayHello() throws Exception{
		MDC.put("rpid", "rpid111");
		System.out.println(proxy.sayHello("shui.ren"));
	    Thread.sleep(1000 * 100);
	}
	
}

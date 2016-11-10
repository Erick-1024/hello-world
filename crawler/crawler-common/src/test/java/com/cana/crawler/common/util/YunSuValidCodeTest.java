package com.cana.crawler.common.util;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:spring/crawler-common*.xml" })
public class YunSuValidCodeTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private IYunSuValidCode yunsuValidCode;

	@Test
	public void createByPost(){
		System.out.println(yunsuValidCode.createByPost(YunSuValidCode.TYPEID_DIGITS_4, "/tmp/captchaImg.jpg"));
	}
	
}

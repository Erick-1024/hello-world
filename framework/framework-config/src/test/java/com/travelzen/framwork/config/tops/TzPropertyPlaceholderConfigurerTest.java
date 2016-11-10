package com.travelzen.framwork.config.tops;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.travelzen.framwork.config.tops.bean.ConfigBean;
import com.travelzen.framwork.config.tops.bean.UtilBean;


public class TzPropertyPlaceholderConfigurerTest {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/spring/applicationContext-tops-config.xml");

		ConfigBean configBean = context.getBean(ConfigBean.class);

		System.out.println(configBean);

		UtilBean utilBean = context.getBean(UtilBean.class);
		System.out.println(utilBean);
		context.close();

	}

}

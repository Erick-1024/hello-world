package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:springboot-dubbo.xml")
public class DemoApplication /*implements EmbeddedServletContainerCustomizer*/ {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("项目启动!");

//		CountDownLatch closeLatch = new CountDownLatch(1);
//		closeLatch.await();
	}

//	@Override
//	public void customize(ConfigurableEmbeddedServletContainer container) {
//		container.setPort(8082);
//	}
}

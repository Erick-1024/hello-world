package com.cana.vbam.front.biz.controller.hystrix;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface ExecuteByHystrix {

	String groupKey() default "";
    String commandKey();
    String threadPoolKey() default "";
    String fallbackMethod() default "";
    int threadPoolSize() default 10;
	
}

package com.travelzen.framework.dao.annotation;

/**
 * @author yujunfeng
 * @function 用于标记数据库只读操作
 * @className ReadOnly.java
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnly {
	boolean value () default true;
}

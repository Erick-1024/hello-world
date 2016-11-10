package com.cana.wechat.openapi.controller.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class WechatOpenApiControllerCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.wechat.openapi.controller..*.*(..))")
	public void catMonitorOperation() {
		
	}

	@Override
	public String getType() {
		return AbstractCatMonitorAspect.Type.controller.name();
	}

}

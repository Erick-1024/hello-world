package com.cana.yundaex.openapi.controller.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class YundaexOpenApiControllerCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.yundaex.openapi.controller..*.*(..))")
	public void catMonitorOperation() {
		
	}

	@Override
	public String getType() {
		return AbstractCatMonitorAspect.Type.controller.name();
	}

}

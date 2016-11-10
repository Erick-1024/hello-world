package com.cana.yundaex.service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class YundaexServiceCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.yundaex.service.impl..*.*(..)) || execution(* com.cana.yundaex.service.transaction.impl..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return Type.service.name();
	}

}

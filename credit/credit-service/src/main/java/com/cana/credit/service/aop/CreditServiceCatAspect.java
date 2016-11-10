package com.cana.credit.service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class CreditServiceCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.credit.service.impl..*.*(..)) || execution(* com.cana.credit.service.transaction.impl..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return Type.service.name();
	}

}

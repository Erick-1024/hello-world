package com.cana.credit.scheduler.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class CreditSchedulerCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.credit.scheduler.schedulers..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return Type.task.name();
	}

}

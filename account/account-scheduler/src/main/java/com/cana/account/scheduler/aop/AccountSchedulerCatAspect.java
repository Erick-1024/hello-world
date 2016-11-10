package com.cana.account.scheduler.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class AccountSchedulerCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.account.scheduler.task..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return AbstractCatMonitorAspect.Type.task.name();
	}

}

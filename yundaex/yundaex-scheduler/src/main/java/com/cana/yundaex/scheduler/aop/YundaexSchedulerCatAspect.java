package com.cana.yundaex.scheduler.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class YundaexSchedulerCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.yundaex.scheduler.schedulers..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return Type.task.name();
	}

}

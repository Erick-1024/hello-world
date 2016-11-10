package com.cana.report.scheduler.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class ReportSchedulerCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.report.scheduler.task..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return AbstractCatMonitorAspect.Type.task.name();
	}

}

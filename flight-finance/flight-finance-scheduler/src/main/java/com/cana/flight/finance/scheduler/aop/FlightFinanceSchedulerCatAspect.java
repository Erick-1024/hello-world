package com.cana.flight.finance.scheduler.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class FlightFinanceSchedulerCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.flight.finance.scheduler.schedulers..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return Type.task.name();
	}

}

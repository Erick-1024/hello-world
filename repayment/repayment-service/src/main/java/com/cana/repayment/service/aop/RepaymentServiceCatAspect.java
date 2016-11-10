package com.cana.repayment.service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class RepaymentServiceCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.repayment.service.impl..*.*(..)) || execution(* com.cana.repayment.service.transaction.impl..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return AbstractCatMonitorAspect.Type.service.name();
	}

}

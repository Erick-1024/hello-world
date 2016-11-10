package com.cana.member.service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class MemberServiceCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.member.service.transaction.impl..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return Type.service.name();
	}

}

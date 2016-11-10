package com.travelzen.framework.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ProfileAspectImpl extends ProfileAspect{

	@Override
	@Pointcut("execution(* com.travelzen.framework.service.impl.*.*(..))")
	public void profileOperation() {
		// TODO Auto-generated method stub
		
	}

}

package com.travelzen.framework.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;

@Aspect
public abstract class ProfileAspect {
	
	@Pointcut
	public abstract void profileOperation();

	@Around("profileOperation()")
	public Object profile(final ProceedingJoinPoint pjp) throws Throwable {
		String tagName = pjp.getSignature().getDeclaringType().getSimpleName() + "." + pjp.getSignature().getName();
    	StopWatch stopWatch = new Slf4JStopWatch(tagName);
        Object retVal = pjp.proceed();
        stopWatch.stop();
        return retVal;
		
	}
}

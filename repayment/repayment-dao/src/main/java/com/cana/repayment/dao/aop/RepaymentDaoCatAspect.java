package com.cana.repayment.dao.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class RepaymentDaoCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.repayment.dao.mapper..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return AbstractCatMonitorAspect.Type.dao.name();
	}
	
	protected String getName(final ProceedingJoinPoint pjp){
		Class<?> targetClass = pjp.getTarget().getClass();
		Class<?>[] interfaceClasses = targetClass.getInterfaces();
		if(ArrayUtils.isNotEmpty(interfaceClasses)){
			targetClass = interfaceClasses[0];
		}
		return new StringBuilder().append(targetClass.getSimpleName()).append("->").append(pjp.getSignature().getName()).toString();
	}

}

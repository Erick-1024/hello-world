package com.cana.early.warning.dao.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class DaoCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.early.warning.dao.mapper..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return AbstractCatMonitorAspect.Type.dao.name();
	}

}

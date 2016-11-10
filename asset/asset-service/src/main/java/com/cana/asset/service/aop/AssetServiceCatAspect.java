package com.cana.asset.service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class AssetServiceCatAspect extends AbstractCatMonitorAspect {

	@Override
	@Pointcut("execution(* com.cana.asset.service.impl..*.*(..)) || execution(* com.cana.asset.service.transaction.impl..*.*(..))")
	public void catMonitorOperation() {

	}

	@Override
	public String getType() {
		return Type.service.name();
	}

}

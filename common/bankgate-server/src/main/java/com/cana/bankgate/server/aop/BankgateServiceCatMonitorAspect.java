/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.AbstractCatMonitorAspect;

@Aspect
public class BankgateServiceCatMonitorAspect extends AbstractCatMonitorAspect {

  @Override
  @Pointcut("execution(* com.cana.bankgate.server.impl.*.*(..)) || execution(* com.cana.bankgate.server.transaction.*.*(..))")
  public void catMonitorOperation() {

  }

  @Override
  public String getType() {
    return AbstractCatMonitorAspect.Type.service.name();
  }

}

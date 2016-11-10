/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.travelzen.framework.aop.ProfileAspect;

@Aspect
public class BankgateProfileAspect extends ProfileAspect {

  @Override
  @Pointcut("execution(* com.cana.bankgate.server.impl.*.*(..)) || execution(* com.cana.bankgate.server.mapper.*.*(..))")
  public void profileOperation() {
    // TODO Auto-generated method stub

  }

}

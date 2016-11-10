/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ducer
 */
@Aspect
public class ArgsValidatorAspect extends AbstractArgsValidatorAspect {

  @Override
  @Pointcut("execution(* com.cana.bankgate.server.impl.*.*(..))")
  public void fieldValidate() {
    // TODO Auto-generated method stub

  }

}

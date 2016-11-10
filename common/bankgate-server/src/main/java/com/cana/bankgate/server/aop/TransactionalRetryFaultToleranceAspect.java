/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import com.travelzen.framework.aop.RetryFaultToleranceAspect;

/**
 * 可重复读事务
 * 
 * @author ducer
 *
 */
@Order(50)
@Aspect
public class TransactionalRetryFaultToleranceAspect extends RetryFaultToleranceAspect {
  @Pointcut("execution(* com.cana.bankgate.server.transaction.*.*(..))") // ..*为包含子目录
  private void transactionalRetryOp() {}

  @Override
  @Pointcut("transactionalRetryOp()")
  public void retryOperation() {}
}

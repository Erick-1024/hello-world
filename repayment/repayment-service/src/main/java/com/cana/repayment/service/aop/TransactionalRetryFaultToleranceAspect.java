package com.cana.repayment.service.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import com.travelzen.framework.aop.RetryFaultToleranceAspect;

@Order(50)
@Aspect
public class TransactionalRetryFaultToleranceAspect extends RetryFaultToleranceAspect{
	@Pointcut("execution(* com.cana.repayment.service.transaction..*.*(..))")
	private void transactionalRetryOp() {}
	@Override
	@Pointcut("transactionalRetryOp()")
	public void retryOperation(){}
}
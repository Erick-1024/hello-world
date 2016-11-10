package com.travelzen.framework.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;

import com.travelzen.framework.core.util.TZUtil;
import com.travelzen.framework.monitor.CallInfo;
import com.travelzen.framework.monitor.RequestIdentityHolder;

/**
 * 在MDC中增加参数
 *
 * @author renshui
 */
@Aspect
public abstract class MDCInsertingAspect {

    @Pointcut
    public abstract void mdcInsertingOperation();

    @Around("mdcInsertingOperation()")
    public Object insert(final ProceedingJoinPoint pjp) throws Throwable {

        try {
            CallInfo callInfo = RequestIdentityHolder.get();
            if (TZUtil.isEmpty(callInfo))
                RequestIdentityHolder.init();
            return pjp.proceed();
        } finally {
            MDC.clear();
        }
    }
}

/*package com.cana.vbam.front.biz.controller.hystrix;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

@Aspect
public class HystrixAround {

	@Around("@annotation(com.cana.vbam.front.biz.controller.hystrix.ExecuteByHystrix)")
    public Object circuitBreakerAround(final ProceedingJoinPoint aJoinPoint) throws Throwable {
        final Method method = ((MethodSignature)aJoinPoint.getSignature()).getMethod();
        Assert.notNull(method, "failed to get method from joinPoint:"+ aJoinPoint);

        ExecuteByHystrix annotation = method.getAnnotation(ExecuteByHystrix.class);
        final String fallbackMethod = annotation.fallbackMethod().trim();

        String commandKey=annotation.commandKey().trim();
        if(commandKey.length()==0){
            throw new RuntimeException("Please provide Command attributes for @ExecuteByHystrix at "+ method.getClass());
        }
        String commandGroupKey=annotation.groupKey();
        if(null==commandGroupKey|| commandGroupKey.trim().length()==0){
            commandGroupKey= aJoinPoint.getSignature().toShortString();
        }

        HystrixCommand.Setter theSetter =
                HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        theSetter = theSetter.andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));

        String hystrixPropertyName="hystrix.command."+commandKey+".circuitBreaker.forceOpen";
        boolean circuitClose =
                DynamicPropertyFactory.getInstance().getBooleanProperty(hystrixPropertyName, false).get();


        ConfigurationManager.getConfigInstance().setProperty(hystrixPropertyName, circuitClose);


        HystrixCommand theCommand = new HystrixCommand(theSetter) {
            @Override
            protected Object run() throws Exception {
                try {
                    return aJoinPoint.proceed();
                } catch (Exception e) {
                    throw e;
                } catch (Throwable e) {
                    throw new Exception(e);
                }
            }
            @Override
            protected Object getFallback() {
                try {
                    if(null==fallbackMethod || fallbackMethod.length()==0){
                        System.out.println("There is no fallback method provided, Unable to get Data from "+method.getName());
                        return null;
                    }
                    //String accountId=(String) aJoinPoint.getArgs()[0];
                    Method method = aJoinPoint.getTarget().getClass().getMethod(fallbackMethod, String.class);
                    return method.invoke(aJoinPoint.getThis(), aJoinPoint.getArgs());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
        return theCommand.execute();
    }
	
}
*/
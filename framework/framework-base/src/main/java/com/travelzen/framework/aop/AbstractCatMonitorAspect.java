package com.travelzen.framework.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.cat.CatUtil;

@Aspect
public abstract class AbstractCatMonitorAspect {
	
	
	public static enum Type{
		controller,
		service,
		dao,
		rpc,
		task
	}
	
	@Pointcut
	public abstract void catMonitorOperation();
	
	/**
	 * 获取Transaction, Event的type
	 * @return
	 */
	public abstract String getType();

	@Around("catMonitorOperation()")
	public Object catMonitor(final ProceedingJoinPoint pjp) throws Throwable {
        String type = getType();
		String name = getName(pjp);
		Transaction t = Cat.newTransaction(type, name);
		afterTransactionCreated();
		String args = getArgsAsString(pjp);
		t.addData("arguments", args);
		try {
			Object ret = pjp.proceed();
			t.setStatus(Transaction.SUCCESS);
			return ret;

		} catch (Exception e) {
			Cat.logError(e);
			t.setStatus(e);
			throw e;

		} finally {
			t.complete();
		}
	}
	
	protected void afterTransactionCreated() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 将方法参数转变为字符串
	 * @param pjp
	 * @return
	 */
	private String getArgsAsString(ProceedingJoinPoint pjp) {
		return CatUtil.getArgsAsString(pjp.getArgs());
	}
	

	/**
	 * 获取Transaction, Event的name
	 * @return
	 */
	protected String getName(final ProceedingJoinPoint pjp){
		return new StringBuilder().append(pjp.getTarget().getClass().getSimpleName()).append("->").append(pjp.getSignature().getName()).toString();
	}
}

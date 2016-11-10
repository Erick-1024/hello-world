package com.travelzen.framework.aop;

import java.util.Random;

public class TransactionalBean {
	@TransactionalRetryAnnotation
	public void transactionalOp() throws com.ibatis.common.jdbc.exception.NestedSQLException, InterruptedException{
		System.out.println("begin");
		Thread.sleep(6 * 1000);
		Random rand = new Random();
		if(rand.nextInt(5) < 5)
			throw new com.ibatis.common.jdbc.exception.NestedSQLException("exception");
		System.out.println("end");
	}
}

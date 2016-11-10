package com.travelzen.framework.util;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;

import com.travelzen.framework.core.exception.BizException;


public class ExceptionUtilsTest {

	@Test
	public void test() {
		Throwable e = new Exception(new SocketTimeoutException());
		System.out.println(ExceptionUtils.indexOfThrowable(e, SocketTimeoutException.class));
		assertNotSame(-1, ExceptionUtils.indexOfThrowable(e, SocketTimeoutException.class));
		e = new Exception(BizException.instance(""));
		assertSame(-1, ExceptionUtils.indexOfThrowable(e, SocketTimeoutException.class));
	}

}

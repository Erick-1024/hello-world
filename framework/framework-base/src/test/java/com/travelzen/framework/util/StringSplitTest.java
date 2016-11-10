package com.travelzen.framework.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringSplitTest {

	@Test
	public void test() {
		assertEquals(1, "abc,".split(",").length);
		assertEquals(2, "abc,1".split(",").length);
	}

}

package com.travelzen.framework;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReExTest {

	@Test
	public void test() {
		String[] tokens = "a b c,d".split("\\s+|,");
		assertEquals(4, tokens.length);
	}

}

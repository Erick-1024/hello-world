package com.travelzen.framework.core.util;

import org.junit.Test;

public class TestPostgresUtil {

	@Test
	public void escapeStringValue() {
		System.out.println(PostgresUtil.escapeStringValue("ABCDEF"));
	}

}

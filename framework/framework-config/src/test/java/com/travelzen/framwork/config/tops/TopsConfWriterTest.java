package com.travelzen.framwork.config.tops;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfWriter;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

public class TopsConfWriterTest {
	private static Logger logger = LoggerFactory.getLogger(TopsConfWriterTest.class);
	
	@Test
	public void writeConfContentMap() throws Exception{
		Map<String,String> params = new HashMap<>();
		params.put("k1", "v1");
		params.put("k2", "v2");
		params.put("k3", "v3");
		TopsConfWriter.writeConfContent("properties/test.properties", params, ConfScope.R);
	}
}

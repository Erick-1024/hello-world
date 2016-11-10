package com.travelzen.framework.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapTest {

	@Test
	public void test() {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.put("k3", "v3");
		for(Map.Entry<String, String> entry : map.entrySet()){
			map.remove(entry.getKey());
		}
	}

}

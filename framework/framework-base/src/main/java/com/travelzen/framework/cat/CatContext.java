package com.travelzen.framework.cat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.dianping.cat.Cat.Context;

public class CatContext implements Context, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1365658935203728367L;
	
	public Map<String, String> maps = new HashMap<String, String>();
	@Override
	public void addProperty(String key, String value) {
		maps.put(key, value);
	}

	@Override
	public String getProperty(String key) {
		return maps.get(key);
	}

}

package com.travelzen.framwork.config.app;

import com.travelzen.framework.config.tops.loadconfig.TopsLoadConfig;

public class AppMain {
	private static void appStart(String[] args) {
		String common = args[0];
		if (common.equalsIgnoreCase("clear")) {
			TopsLoadConfig config = new TopsLoadConfig();
			config.clear();
		}
		if (common.equalsIgnoreCase("loadAll")) {
			TopsLoadConfig config = new TopsLoadConfig();
			config.loadALLConfigFile();
		}
		if (common.equalsIgnoreCase("loadUpdate")) {
			TopsLoadConfig config = new TopsLoadConfig();
			config.loadingConfigFile();
		}
	}

	public static void main(String[] args) {
		appStart(args);
	}
}

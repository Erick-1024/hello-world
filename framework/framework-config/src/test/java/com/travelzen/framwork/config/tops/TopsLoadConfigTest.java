package com.travelzen.framwork.config.tops;

import java.io.File;

import org.junit.Test;

import com.travelzen.framework.config.tops.loadconfig.TopsLoadConfig;

public class TopsLoadConfigTest {

	@Test
	public void testLoadConfig() {
		TopsLoadConfig config = new TopsLoadConfig();
		config.loadALLConfigFile();
	}

	@Test
	public void testLoadSingleFileConfig() {
		TopsLoadConfig config = new TopsLoadConfig();
		config.updateToZookeeper(new File("/opt/conf/tz-data/global/properties/crawler-autoissue.properties"));;
	}

}

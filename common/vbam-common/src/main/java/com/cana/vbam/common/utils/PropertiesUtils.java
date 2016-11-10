package com.cana.vbam.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

public class PropertiesUtils {

    private static final Logger LGR = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

    public static void register(String namespace, String path, ConfScope scope) {
    	try {
    		Properties ppt = TopsConfReader.getConfProperties(path, scope);
    		propertiesMap.put(namespace, ppt);
        } catch (Exception e) {
            LGR.error("load properties(" + path + ") failed." ,e);
        }
    }

    public static String get(String namespace, String key) {
    	Properties properties = propertiesMap.get(namespace);
    	return properties == null ? null : properties.getProperty(key);
    }

    public static String get(String namespace, String key, String defaultValue) {
    	String value = get(namespace, key);
    	return value == null ? defaultValue : value;
    }

}

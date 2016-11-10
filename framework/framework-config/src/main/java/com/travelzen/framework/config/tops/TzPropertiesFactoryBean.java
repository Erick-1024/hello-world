package com.travelzen.framework.config.tops;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

/**
 * @Author : simon
 * @version : Jun 9, 2014 9:20:25 PM
 *
 **/
public class TzPropertiesFactoryBean extends PropertiesLoaderSupport implements FactoryBean<Properties>, InitializingBean {

	private static final String ERROR_MSG = "文件路径必须是{scope:path}的形式。"
			+ "如：'R:properties/mongo-database.properties'代表获取'R'作用下的'properties/mongo-database.properties'";

	private String tzProperty;

	private boolean singleton = true;

	private Properties singletonInstance;

	public void setTzProperty(String tzProperty) {
		this.tzProperty = tzProperty;
	}

	public String getTzProperty() {
		return tzProperty;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.singleton) {
			this.singletonInstance = createProperties();
		}
	}

	public final Properties getObject() throws IOException {
		if (this.singleton) {
			return this.singletonInstance;
		} else {
			return createProperties();
		}
	}

	private final Properties createProperties() {
		String[] strs = this.tzProperty.split(":");
		if (strs.length != 2) {
			throw new RuntimeException(ERROR_MSG);
		}
		return TopsConfReader.getConfProperties(strs[1], ConfScope.valueOf(strs[0]));
	}

	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	/**
	 * Set whether a shared 'singleton' Properties instance should be created,
	 * or rather a new Properties instance on each request.
	 * <p>
	 * Default is "true" (a shared singleton).
	 */
	public final void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public final boolean isSingleton() {
		return this.singleton;
	}

}

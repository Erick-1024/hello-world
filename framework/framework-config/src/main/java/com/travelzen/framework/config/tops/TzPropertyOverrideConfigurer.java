package com.travelzen.framework.config.tops;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;
import org.springframework.util.PropertiesPersister;

/**
 * Created with IntelliJ IDEA.
 * User: xian.zeng
 * Date: 6/12/2014
 * Time: 11:08 AM
 */
public class TzPropertyOverrideConfigurer extends PropertyOverrideConfigurer {
	private static final String ERROR_MSG = "文件路径必须是{scope:path}的形式。"
			+ "如：'R:properties/mongo-database.properties'代表获取'R'作用下的'properties/mongo-database.properties'";

	private List<String> locations;
	private String tzProperties;

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getTzProperties() {
		return tzProperties;
	}

	public void setTzProperties(String tzProperties) {
		this.tzProperties = tzProperties;
	}

	@Override
	protected void loadProperties(Properties props) throws IOException {
		if (this.tzProperties != null) {
			saveProp2Persister(props, this.tzProperties);
		}
		if (this.locations != null && this.locations.size() > 0) {
			for (String propPath : this.locations) {
				saveProp2Persister(props, propPath);
			}
		}
	}

	private void saveProp2Persister(Properties props, String propPath) throws IOException {
		String[] strs = propPath.split(":");
		if (strs.length != 2) {
			throw new RuntimeException(ERROR_MSG);
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		TopsConfReader.getConfProperties(strs[1], TopsConfEnum.ConfScope.valueOf(strs[0])).store(byteArrayOutputStream,"");
		byte[] propsRead = byteArrayOutputStream.toByteArray();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(propsRead);
		try {
			PropertiesPersister propertiesPersister = (PropertiesPersister) FieldUtils.readField(this, "propertiesPersister", true);
			propertiesPersister.load(props, byteArrayInputStream);
		} catch (IllegalAccessException e) {
			throw new IOException("error writing override props");
		}
	}
}

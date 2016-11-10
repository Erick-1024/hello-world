package com.travelzen.framework.config.tops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.util.TopsAppRegistry;


/**
 * 使用真旅配置文件机制加载的spring{@link PropertyPlaceholderConfigurer}
 * @author hehaijiang
 */
public class TzPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static final String ERROR_MSG = "文件路径必须是{scope:path}的形式。"
			+ "如：'R:properties/mongo-database.properties'代表获取'R'作用下的'properties/mongo-database.properties'";

	private static final Map<String, String> GLOBAL_PLACEHOLDERS = new HashMap<>();

	static{
		GLOBAL_PLACEHOLDERS.put("APP_NAME", TopsAppRegistry.getApplicationName());
		GLOBAL_PLACEHOLDERS.put("LOCAL_IP", TopsAppRegistry.getLocalIP());
	}

	/**
	 * 设置需要加载的properties文件路径列表
	 * <br/>
	 * 文件路径遵循"{scope:path}"的约定
	 * <br/>
	 * 如："R:properties/mongo-database.properties"代表获取“R”作用下的“properties/mongo-database.properties”
	 * @param tzProperties
	 */
	public void setTzProperties(List<String> tzProperties){

		if( null == tzProperties || tzProperties.size() <=0) throw new RuntimeException("必须设置文件路径列表");

		List<Properties> propertiesArray = new ArrayList<>();
		for(String value : tzProperties){

			String[] strs = value.split(":");

			if(strs.length != 2) throw new RuntimeException(ERROR_MSG);

			propertiesArray.add(TopsConfReader.getConfProperties(strs[1], ConfScope.valueOf(strs[0])));

		}

		setPropertiesArray(propertiesArray.toArray(new Properties[propertiesArray.size()]));

		// 如果找不到指定key的值则跳过，交由系统其他placeholder来处理
		setIgnoreUnresolvablePlaceholders(true);
	}

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		// TODO Auto-generated method stub
		String value = super.resolvePlaceholder(placeholder, props);
		if(value == null)
			return GLOBAL_PLACEHOLDERS.get(placeholder);
		return value;

	}



}

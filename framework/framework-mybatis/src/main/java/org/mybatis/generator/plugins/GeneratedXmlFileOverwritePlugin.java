package org.mybatis.generator.plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: dingguangxian
 * Date: 14-5-26
 * Time: 下午7:59
 */
public class GeneratedXmlFileOverwritePlugin extends PluginAdapter {
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMapFile, IntrospectedTable introspectedTable) {
		String overwrite = context.getProperty("overwrite");
		if ("true".equals(overwrite)) {
//			System.out.println("设置xml为overwrite:" + sqlMapFile.getFileName());
			try {
				Field mergedField = GeneratedXmlFile.class.getDeclaredField("isMergeable");
				mergedField.setAccessible(true);
				mergedField.setBoolean(sqlMapFile, false);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return super.sqlMapGenerated(sqlMapFile, introspectedTable);
	}
}

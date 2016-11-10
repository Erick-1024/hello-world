/**
 * Copyright (c) 2015, travelzen and/or its affiliates. All rights reserved.
 */
package mybatis.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author cheng.zeng@travelzen.com
 */
public class Generate {

	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		try {
			boolean overwrite = true;
			File configFile = new File("./src/test/java/mybatis/generator/generate_zhaobinjie.xml");
			if (configFile.exists()) {
				ConfigurationParser cp = new ConfigurationParser(warnings);
				Configuration config = cp.parseConfiguration(configFile);
				for (Context context : config.getContexts()) {
					context.addProperty("overwrite", String.valueOf(overwrite));
				}
				DefaultShellCallback callback = new DefaultShellCallback(overwrite);
				MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
				myBatisGenerator.generate(null);
				System.out.println("Success");
			} else
				System.out.println("沒有找到配置文件");// 配置文件中的项目地址和包引用地址要用绝对地址，否则不能生成
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

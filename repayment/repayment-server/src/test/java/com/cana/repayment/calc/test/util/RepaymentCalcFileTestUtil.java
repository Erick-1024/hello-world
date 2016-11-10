package com.cana.repayment.calc.test.util;

import java.io.FileWriter;
import java.util.Set;

import com.google.common.collect.Sets;

public class RepaymentCalcFileTestUtil {

	private static Set<String> filenames = Sets.newHashSet();

	public static synchronized FileWriter getFileWriter(String filename) throws Exception {
		if (filenames.contains(filename))
			throw new Exception("重复使用文件：" + filename);
		filenames.add(filename);

		return new FileWriter(testResultFileRootPath + filename);
	}

	private static String testResultFileRootPath;
	static {
		String filePath = RepaymentCalcFileTestUtil.class.getClass().getResource("/").getFile();
		String appName = "repayment-server";
		testResultFileRootPath = filePath.substring(0, filePath.indexOf(appName) + appName.length()) + "/src/test/resources/repayment/calc/results/";
	}
}

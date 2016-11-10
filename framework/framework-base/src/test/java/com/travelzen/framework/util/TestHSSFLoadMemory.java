package com.travelzen.framework.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;


public class TestHSSFLoadMemory {

	@Test
	public void test() throws Exception{
		Thread.sleep(1000 * 20);
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File("/home/renshui/1.xls")));
		while(true){
			System.out.println(wb.toString());
			Thread.sleep(1000 * 3);
		}
	}

}

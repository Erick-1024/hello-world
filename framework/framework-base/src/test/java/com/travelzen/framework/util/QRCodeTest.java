package com.travelzen.framework.util;

import org.junit.Test;


public class QRCodeTest {

	@Test
	public void test() {
		 String imgPath = "/tmp/Michael_QRCode.png";  
	        String encoderContent = "Hello 大大、小小,welcome to QRCode!" + "\nMyblog [ http://sjsky.iteye.com ]" + "\nEMail [ sjsky007@gmail.com ]";  
	        TwoDimensionCode handler = new TwoDimensionCode();  
	        handler.encoderQRCode(encoderContent, imgPath, "png");  
	        System.out.println("========encoder success");  
	        String decoderContent = handler.decoderQRCode(imgPath);  
	        System.out.println("解析结果如下：");  
	        System.out.println(decoderContent);  
	        System.out.println("========decoder success!!!");  
	}

}

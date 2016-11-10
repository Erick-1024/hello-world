package com.travelzen.framework.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MailUtilTest {

	MailUtil mailUtil;
	
	@Test
	public void test() {
		MailUtil mailUtil = new MailUtil();
		mailUtil.setHost("shmail2.travelzen.com");
		mailUtil.setUsername("biz.b2b");
		mailUtil.setPassword("123456");
		mailUtil.setFrom("biz.b2b@travelzen.com");
		mailUtil.setTo("simon.li@travelzen.com,siwei.lu@travelzen.com");
		mailUtil.setSubject("IBE+航班最低价抓取结果");
		mailUtil.setContentType("text/plain;charset=utf-8");
		mailUtil.setContent("请查收附件");
		List<String> attachments = new ArrayList<>();
		attachments.add("/data/programdata/flight-crawler/淘宝数据抓取-2014-11-30AM-(6:00-12:00).xlsx");
		attachments.add("/data/programdata/flight-crawler/淘宝数据抓取-2014-11-30AM-(12:01-18:00).xlsx");
		attachments.add("/data/programdata/flight-crawler/淘宝数据抓取-2014-11-30AM-(18:01-23:59).xlsx");
		mailUtil.setAttachments(attachments);
		mailUtil.sendMail();
		
		
	}

	@Test
	public void testSend() throws IOException {
		mailUtil = new MailUtil();
		mailUtil.setHost("shmail1.travelzen.com");
		mailUtil.setUsername("biz.b2b");
		mailUtil.setPassword("123456");
//		mailUtil.setUsername("tops.test");
//		mailUtil.setPassword("Abc12345");
		mailUtil.setFrom("tops.test@travelzen.com");
		mailUtil.setTo("xiangjiang.bao@travelzen.com,guangxian.ding@travelzen.com,shui.ren@travelzen.com,tongli.wang@travelzen.com,xian.zeng@travelzen.com");
		mailUtil.setSubject("测试邮件");

//		String content = FileUtils.readFileToString(new File("/home/simon/酒店入住单.html"));
//		Map<String, String> images = new HashMap<> ();
//		images.put("logo", "/home/simon/travelzen_logo.gif");
//		mail.setImages(images);


		String html = "<html><head><title>测试一下！</title></head>"
				+ "<body><center>good morning</center>"
				+ "<img src=\"cid:img001\"/>"
				+ "<img src=\"cid:img002\"/>"
				+ "</body></html>";
		mailUtil.setContent(html);
//		mailUtil.setContent(content);
		mailUtil.setContentType("text/plain;charset=utf-8");
		mailUtil.setContentType("text/html;charset=utf-8");
		Map<String, String> images = new HashMap<String, String>();
		images.put("img001", "/home/simon/Pictures/phone.jpg");
		images.put("img002", "/home/simon/Pictures/美女.jpg");
//		mailUtil.setImages(images);
//		List<String> attachments = new ArrayList<String>();
//		attachments.add("/home/simon/Pictures/bycle.jpg");
//		mailUtil.setAttachments(attachments);
		mailUtil.sendMail();

	}

}

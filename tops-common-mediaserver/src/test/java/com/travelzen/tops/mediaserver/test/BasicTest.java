package com.travelzen.tops.mediaserver.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.framework.util.HashUtils;
import com.travelzen.tops.mediaserver.consts.Consts;
import com.travelzen.tops.mediaserver.db.projo.Image;
import com.travelzen.tops.mediaserver.db.projo.Media;

public class BasicTest {

	@Test
	public void DateTest() throws ParseException {
		String str1 = new String("1986-02-4");
		String str2 = new String("1986-2-8");

		java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date d1 = simpleDateFormat.parse(str1);
		Date d2 = simpleDateFormat.parse(str2);

		long dmm = d2.getTime() - d1.getTime();
		int d = (int) dmm / 1000 / 60 / 60 / 24;

		Assert.assertEquals(4, d);

		String str3 = "20070101000000";
		SimpleDateFormat f2 = new SimpleDateFormat("yyyyMMddHHmmss");

		Date d3 = f2.parse(str3);

		System.out.println(d3);
	}

	@Test
	public void ConstsTest() throws ParseException {

		Media result = new Image();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result.setCreateTime(dateFormat.parse("2013-11-11 16:26:39"));
		long interval = new Date().getTime() / 1000 - result.getCreateTime().getTime() / 1000;
		if (result == null || interval > Consts.UPDATE_FREQUENCY) {
			System.out.println("init");
		}
		System.out.println(60 * 60 * 24);

	}

	@Test
	public void regexTest() {

		Pattern pattern = Pattern.compile("a{1,5}");
		String s = "ccaaab";

		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			Assert.assertEquals("aaa", matcher.group());
		}

	}

	@Test
	public void mediaIdTest() {
		Assert.assertEquals("6627438440150328678", getMediaId("pIYBAFIl_MaAJ4VxAAK9gHSFFg8890.png"));
	}

	private String getMediaId(String fileName) {
		String mediaStringId = fileName.replaceAll("/", "");
		if (mediaStringId.length() < 7) {
			return null;
		}
		mediaStringId = mediaStringId.substring(mediaStringId.length() - 7, mediaStringId.length());
		String mediaId = String.valueOf(HashUtils.murmurHash2(mediaStringId));
		return mediaId;

	}

}

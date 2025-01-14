package com.travelzen.framework.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.junit.Test;

import com.travelzen.framework.security.MD5;

public class MD5UtilTest {
	@Test
	public void encode() throws Exception{
		String str = "abc";
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(str.getBytes("UTF-8"));
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        String sign = new BigInteger(1, md.digest()).toString(16);
        System.out.println(sign);
        
        System.out.println(MD5.encode("abc"));
	}
}

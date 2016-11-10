package com.cana.vbam.test.password;

import java.io.IOException;

import org.junit.Test;

import com.cana.vbam.common.utils.PasswordEncoderUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;

public class TestPassword {

	@Test
	public void test() {
		System.out.println(PasswordEncoderUtil.encrypt("123"));
		
	}
	
	public static void main(String[] args) throws IOException, Exception{
		System.out.println(MediaClientUtil.upload("/home/ducer/Desktop/1.xls"));;
	}
}

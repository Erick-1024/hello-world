package com.cana.vbam.common.utils.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cana.vbam.common.utils.PasswordEncoderUtil;

public class PasswordGenerateTest {

	@Test
	public void test() {
		String pass = PasswordEncoderUtil.encrypt("123456");
		System.out.println(pass);
	}

}
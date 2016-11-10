package com.cana.vbam.common.utils;

import java.util.regex.Pattern;

import org.junit.Test;

public class RegexRule {

	public static final String EMAIL = "^[0-9|a-z|A-Z|-|_]+([-+.][0-9|a-z|A-Z|-|_]+)*@[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*\\.[0-9|a-z|A-Z|-|_]+([-.][0-9|a-z|A-Z|-|_]+)*$";

	public static final String TEL = "^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$";
	
	public static final String PASSWORD="^[0-9a-zA-Z-_]{6,20}$";

	@Test
	public void f() {
		boolean b=Pattern.matches(PASSWORD, "Ddfsdf");
		System.out.println(b);
	}

}

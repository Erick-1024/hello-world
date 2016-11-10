package com.travelzen.framework.util;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;

public class BeanUtilsTest {

	@Test
	public void copyProperties() {
		
		Source source = new Source();
		source.setA("a");
		source.setB("c");
		source.setC(1.0);
		
		Dest dest = new Dest();
		
		BeanUtils.copyProperties(source, dest, new String[]{"c"});
		
		System.out.println(new Gson().toJson(dest));
	}

}

class Source{
	private String a;
	private String b;
	private Double c;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public Double getC() {
		return c;
	}
	public void setC(Double c) {
		this.c = c;
	}
}

class Dest{
	private String a;
	private Long c;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public Long getC() {
		return c;
	}
	public void setC(Long c) {
		this.c = c;
	}
}
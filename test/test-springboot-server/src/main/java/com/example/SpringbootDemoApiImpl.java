package com.example;

import com.example.springboot.demo.api.SpringbootDemoApi;

public class SpringbootDemoApiImpl implements SpringbootDemoApi {

	@Override
	public String hello(String name) {
		return "hello " + name;
	}

}

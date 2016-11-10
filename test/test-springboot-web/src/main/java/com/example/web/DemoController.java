package com.example.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springboot.demo.api.SpringbootDemoApi;

@Controller
public class DemoController {

	@Resource
	private SpringbootDemoApi SpringbootDemoApi;

	@RequestMapping("index")
	@ResponseBody
	public String hello() {
		return SpringbootDemoApi.hello("xiaomeng");
	}
}

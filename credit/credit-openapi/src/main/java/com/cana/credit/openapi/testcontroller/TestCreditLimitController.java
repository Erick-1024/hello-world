package com.cana.credit.openapi.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/test/limit")
public class TestCreditLimitController {
	
	@RequestMapping(value="/balance")
	public String getBalance(){
		return "/page/test/limitBalance";
	}
	
	
	

}

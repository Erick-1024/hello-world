package com.cana.credit.openapi.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestFinanceInfoController {
	
	@RequestMapping(value="/test/getCustomerFinanceInfo")
	public String getBalance(){
		return "/page/test/customerFinanceInfo";
	}
	
}

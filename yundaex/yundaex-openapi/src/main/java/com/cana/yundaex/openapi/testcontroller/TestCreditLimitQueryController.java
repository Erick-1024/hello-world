package com.cana.yundaex.openapi.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/test/limit")
public class TestCreditLimitQueryController {

		@RequestMapping(value="/balance")
		public String getBalance(){
			return "/page/test/limitBalance";
		}
		
		
		
}

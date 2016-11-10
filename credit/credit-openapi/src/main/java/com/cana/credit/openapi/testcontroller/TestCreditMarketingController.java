package com.cana.credit.openapi.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/test/marketing")
public class TestCreditMarketingController {

	@RequestMapping(value = "/currentActivity", method=RequestMethod.GET)
	public String gotoCurrentActivityPage() {
		return "page/test/currentAcitivity";
	}
	
	@RequestMapping(value = "/prepayProduct", method=RequestMethod.GET)
	public String gotoPrepayProductPage() {
		return "page/test/prepayProduct";
	}
	
}

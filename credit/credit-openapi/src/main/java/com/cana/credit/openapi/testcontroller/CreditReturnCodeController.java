package com.cana.credit.openapi.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelzen.framework.core.common.ReturnCode;

@Controller
@RequestMapping(value = "/test/retcodes")
public class CreditReturnCodeController {

	@RequestMapping(method=RequestMethod.GET)
	public String testTrade(Model model) {
		model.addAttribute("retcodes", ReturnCode.values());
		return "page/test/retcodes";
	}

}

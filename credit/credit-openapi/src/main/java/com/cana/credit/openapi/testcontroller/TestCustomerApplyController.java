package com.cana.credit.openapi.testcontroller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cana.credit.api.ICreditSignApi;
import com.travelzen.framework.core.time.DateTimeUtil;

@Controller
@RequestMapping(value = "/test/customerApply")
public class TestCustomerApplyController {

	@Resource
	private ICreditSignApi creditSignApi;

	@RequestMapping(method=RequestMethod.GET)
	public String testTrade(Model model) {
		model.addAttribute("applyTime", DateTimeUtil.format(new Date(), DateTimeUtil.DATE_TIME_PATTERN));
		return "page/test/testcustomerapply";
	}

}

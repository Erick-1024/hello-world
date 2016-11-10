package com.cana.credit.openapi.testcontroller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditSignApi;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.RandomUtil;

@Controller
@RequestMapping(value = "/test/trade")
public class TestCreditTradeController {

	@Resource
	private ICreditSignApi creditSignApi;

	@RequestMapping(method=RequestMethod.GET)
	public String testTrade(Model model, HttpServletRequest request) {
		model.addAttribute("customerId", "526f488745ce9ad7b9caaa35");
		model.addAttribute("tradeNo", DateTimeUtil.format(new Date(), "yyyyMMddHHmmss") + RandomUtil.getRandomStr(5));
		model.addAttribute("refundTradeNo", DateTimeUtil.format(new Date(), "yyyyMMddHHmmss") + RandomUtil.getRandomStr(5));
		model.addAttribute("agentRepaymentTradeNo", DateTimeUtil.format(new Date(), "yyyyMMddHHmmss") + RandomUtil.getRandomStr(5));
		model.addAttribute("tradeTime", DateTimeUtil.format(new Date(), DateTimeUtil.DATE_TIME_PATTERN));
		try {
			model.addAttribute("serverUrlPrefix", "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort());
		} catch (UnknownHostException e) {
		}
		return "page/test/testtrade";
	}

	@RequestMapping(value="generateSign", method=RequestMethod.POST)
	@ResponseBody
	public String generateSign(String institution, String plain) {
		return creditSignApi.sign(plain, institution, false);
	}
	@RequestMapping(value="/status")
	public String getTradeStatus(Model  model){
		return "page/test/tradeStatus";
	}
	@RequestMapping(value="/loan/detail")
	public String getLoanDetail(){
		return "page/test/loanDetail";
	}
	@RequestMapping(value="/loan/list")
	public String getLoanList(){
		return "page/test/loanList";
	}
}

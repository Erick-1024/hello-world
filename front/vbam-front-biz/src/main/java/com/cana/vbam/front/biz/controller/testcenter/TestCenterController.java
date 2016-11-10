package com.cana.vbam.front.biz.controller.testcenter;


import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cana.member.api.ITestCenterApi;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * 测试中心。
 * 
 * 主要是给测试使用，设置虚拟时间，设置银行账号余额等。
 * 
 * 需要注意生产环境不允许操作。
 */
@Controller
@RequestMapping(value="/testcenter")
public class TestCenterController {
	
	@Resource
	private ITestCenterApi testCenterApi;
	
	@RequestMapping(value = "/datetime", method = RequestMethod.GET)
	public String gotoVirtualDateTimeSettingPage(Model model){
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		Pair<String, String> dateTime = testCenterApi.getVirtualDateTime();
		model.addAttribute("virtualDate", dateTime.getLeft());
		model.addAttribute("hourOffset", dateTime.getRight());
		return "page/testcenter/virtualDateTimeSettingPage";
	}
	
	@RequestMapping(value = "/saveDatetime", method = RequestMethod.POST)
	public String saveVirtualDateTime(@RequestParam(required=false) String virtualDate, @RequestParam(required=false) String hourOffset){
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		testCenterApi.saveVirtualDateTime(virtualDate, hourOffset);
		return "page/success";
	}
	
	@RequestMapping(value = "/saveBalance", method = RequestMethod.POST)
	public String saveVirtualBalance(@RequestParam(required = true) String accountNo, @RequestParam(required = true)String balance,Integer expireTime){
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		testCenterApi.saveVirtualBalance(accountNo.replaceAll("\\s*", ""), MoneyUtil.yuan2Cent(balance),(expireTime == null || expireTime > 72)?72:expireTime);
		return "page/success";
	}
	
	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public String gotoVirtualBalanceSettingPage(String accountNo, Model model) {
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		if(StringUtils.isNotBlank(accountNo)) {
			Long balance = testCenterApi.getVirtualBalance(accountNo);
			model.addAttribute("balance", MoneyUtil.cent2Yuan(balance == null ? 0L : balance));
		}
		model.addAttribute("accountNo", accountNo);
		model.addAttribute("expireTime", null);
		return "page/testcenter/virtualBalanceSettingPage";
	}

	@RequestMapping(value = "/withdrawState", method = RequestMethod.GET)
	public String withdrawState(String bizSeq,String state, Model model) {
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		return "page/testcenter/withdrawStateSettingPage";
	}
	
	@RequestMapping(value = "/saveWithdrawState", method = RequestMethod.POST)
	public String saveWithdrawState(@RequestParam(required = true) String businessSeq, @RequestParam(required = true)String state){
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		testCenterApi.saveWithdrawState(businessSeq, state);
		return "page/success";
	}
	
	@RequestMapping(value = "/virtualBalanceForAllAccount", method = RequestMethod.GET)
	public String virtualBalanceForAllAccount(Model model) {
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		return "page/testcenter/virtualBalanceForAllAccountPage";
	}

	@RequestMapping(value = "/saveVirtualBalanceForAllAccount", method = RequestMethod.POST)
	public String saveVirtualBalanceForAllAccount(Integer expireTime){
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		testCenterApi.saveVirtualBalanceForAllAccount(expireTime);
		return "page/success";
	}

	@RequestMapping(value = "/cancelVirtualBalanceForAllAccount", method = RequestMethod.POST)
	public String cancelVirtualBalanceForAllAccount(){
		if(testCenterApi.isProdEnv())
			throw WebException.instance("生产环境不支持此操作");
		testCenterApi.cancelVirtualBalanceForAllAccount();
		return "page/success";
	}
}

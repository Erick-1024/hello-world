package com.cana.credit.openapi.testcontroller;

import javax.annotation.Resource;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditSignApi;
import com.cana.flight.finance.common.dto.CreditTradeResult;
import com.cana.flight.finance.common.dto.TravelzenBaseResponse;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
public class TestAsyncNotifyController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static final String testAsyncNotifyTradeStatusUrl = "/test/asyncnotify/tradeStatus";

	@Resource
	private ICreditSignApi creditSignApi;

	@RequestMapping(value=testAsyncNotifyTradeStatusUrl, method=RequestMethod.POST)
	@ResponseBody
	public TravelzenBaseResponse testTrade(@RequestBody CreditTradeResult tradeResult) {
		logger.info(testAsyncNotifyTradeStatusUrl + " 收到请求：{}", new Gson().toJson(tradeResult));
		TravelzenBaseResponse response = new TravelzenBaseResponse();
		ReturnCode retCode = ReturnCode.SUCCESS;
		if (RandomUtils.nextInt(100) >= 20) {
			retCode = ReturnCode.ERROR;
		}
		response.setRetCode(retCode.getRetCode());
		response.setRetMsg(retCode.getRetMsg());
		return response;
	}

}

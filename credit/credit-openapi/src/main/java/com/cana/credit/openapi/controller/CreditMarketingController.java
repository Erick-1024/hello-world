package com.cana.credit.openapi.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.credit.openapi.utils.ExceptionHandler;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityRequest;
import com.cana.vbam.common.credit.dto.marketing.CurrentActivityResponse;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductRequest;
import com.cana.vbam.common.credit.dto.marketing.PrepayProductResponse;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
@RequestMapping(value="/credit/marketing")
public class CreditMarketingController {

	@Resource
	private ICreditApi creditApiImpl;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	
	@RequestMapping(value = "/currentActivity", method=RequestMethod.POST)
	@ResponseBody
	public CurrentActivityResponse getCurrentActivityResponse(@RequestBody CurrentActivityRequest currentActivityRequest) {
		logger.info("请求currentActivity接口参数：{}", gson.toJson(currentActivityRequest));
		CurrentActivityResponse currentActivityResponse = new CurrentActivityResponse();
		try {
			currentActivityResponse = creditApiImpl.getCurrentActivity(currentActivityRequest);
			ReturnCode returnCode = ReturnCode.SUCCESS;
			currentActivityResponse.setRetCode(returnCode.getRetCode());
			currentActivityResponse.setRetMsg(returnCode.getRetMsg());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "获取当前活动异常", currentActivityResponse);
		}
		return currentActivityResponse;
	}

	@RequestMapping(value = "/prepayProduct", method=RequestMethod.POST)
	@ResponseBody
	public PrepayProductResponse queryPrepayProduct(@RequestBody PrepayProductRequest prepayProductRequest) {
		logger.info("请求prepayProduct接口参数：{}", gson.toJson(prepayProductRequest));
		PrepayProductResponse prepayProductResponse = new PrepayProductResponse();
		try {
			prepayProductResponse = creditApiImpl.getPrepayProduct(prepayProductRequest);
			ReturnCode returnCode = ReturnCode.SUCCESS;
			prepayProductResponse.setRetCode(returnCode.getRetCode());
			prepayProductResponse.setRetMsg(returnCode.getRetMsg());
		} catch (Exception e) {
			ExceptionHandler.handleException(e, "获取促销产品信息错误", prepayProductResponse);
		}
		return prepayProductResponse;
	}
	
}
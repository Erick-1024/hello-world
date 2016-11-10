package com.cana.yundaex.openapi.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.yundaex.api.IYundaexCreditApi;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitDTO;
import com.cana.yundaex.common.dto.limit.YdQueryCreditLimitResponse;
import com.cana.yundaex.openapi.utils.ExceptionHandler;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
@RequestMapping(value = "cana")
public class YdCreditLimitController {

	@Resource
	private IYundaexCreditApi ydCreditApi;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	
	@RequestMapping(value="/customerLimitQuery",method = RequestMethod.POST)
	@ResponseBody
	public YdQueryCreditLimitResponse queryCreditLimit(@RequestBody YdQueryCreditLimitDTO ydQueryCreditLimitDTO ){
		logger.info("收到授信余额查询请求：{}", gson.toJson(ydQueryCreditLimitDTO));
		YdQueryCreditLimitResponse ydQueryCreditLimitResponse = new YdQueryCreditLimitResponse();
		try {
			ydQueryCreditLimitResponse = ydCreditApi.queryCreditLimit(ydQueryCreditLimitDTO);
			ydQueryCreditLimitResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
			ydQueryCreditLimitResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch (Exception e) {
			ExceptionHandler.handleException(e, "服务器异常", ydQueryCreditLimitResponse);
		}
		logger.info("授信余额查询返回数据：{}", gson.toJson(ydQueryCreditLimitResponse));
		return ydQueryCreditLimitResponse;
	}
}

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
import com.cana.vbam.common.credit.dto.limit.PreGenerateCreditLimitResponse;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitDTO;
import com.cana.vbam.common.credit.dto.limit.QueryCreditLimitResponse;
import com.cana.vbam.common.credit.dto.limit.preGenerateCreditLimitRequest;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
@RequestMapping(value = "/credit/limit")
public class CreditLimitController {

	@Resource
	private ICreditApi creditApi;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	
	@RequestMapping(value = "/preGenerate" ,method=RequestMethod.POST)
	@ResponseBody
	public PreGenerateCreditLimitResponse preGenerateCreditLimit(@RequestBody preGenerateCreditLimitRequest request) {
		logger.info("收到【预生成额度查询】请求：客户id为{}", request.getCustomerId());
		PreGenerateCreditLimitResponse preGenerateCreditLimitResponse = new PreGenerateCreditLimitResponse();
		try {
            preGenerateCreditLimitResponse = creditApi.preGenerateCreditLimit(request.getCustomerId());
//			preGenerateCreditLimitResponse.setExistLimit(true);
//			preGenerateCreditLimitResponse.setTotalLimit(1000000l);
			preGenerateCreditLimitResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
			preGenerateCreditLimitResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
        } catch(Exception e){
			ExceptionHandler.handleException(e, "服务器异常", preGenerateCreditLimitResponse);
		}
		return preGenerateCreditLimitResponse;
	}
	
	@RequestMapping(value = "/balance" ,method=RequestMethod.POST)
	@ResponseBody
	public QueryCreditLimitResponse queryCreditLimit(@RequestBody QueryCreditLimitDTO queryCreditLimitDTO) {
		logger.info("收到【授信余额查询】请求：{}", gson.toJson(queryCreditLimitDTO));
		QueryCreditLimitResponse queryCreditLimitResponse = new QueryCreditLimitResponse();
		try {
            queryCreditLimitResponse = creditApi.queryCreditLimitBalance(queryCreditLimitDTO);
            queryCreditLimitResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
            queryCreditLimitResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
        } catch(Exception e){
			ExceptionHandler.handleException(e, "服务器异常", queryCreditLimitResponse);
		}
		return queryCreditLimitResponse;
	}
	
}

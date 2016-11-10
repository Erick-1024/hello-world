/**
 * 
 */
package com.cana.yundaex.openapi.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.yundaex.api.IYundaexLoanApplyApi;
import com.cana.yundaex.common.dto.YundaexLoanInfoListRequest;
import com.cana.yundaex.common.dto.YundaexLoanInfoListResponse;
import com.cana.yundaex.openapi.utils.ExceptionHandler;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

/**
 * 放款信息查询接口
 * @author guguanggong
 *
 */
@Controller
@RequestMapping(value = "cana")
public class YdLoanApplyController {

	@Resource
	private IYundaexLoanApplyApi yundaexLoanApplyApi;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	
	@RequestMapping(value="/loanInfoQuery",method = RequestMethod.POST)
	@ResponseBody
	public YundaexLoanInfoListResponse queryLoanInfo(@RequestBody YundaexLoanInfoListRequest yundaexLoanInfoListRequest){
		logger.info("接收韵达查询放款信息请求：{}", gson.toJson(yundaexLoanInfoListRequest));
		YundaexLoanInfoListResponse yundaexLoanInfoListResponse = new YundaexLoanInfoListResponse();
		try {
			yundaexLoanInfoListResponse = yundaexLoanApplyApi.getYundaexLoanInfoList(yundaexLoanInfoListRequest);
			yundaexLoanInfoListResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
			yundaexLoanInfoListResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
			logger.info("接收韵达查询放款信息返回：{}", gson.toJson(yundaexLoanInfoListResponse));
		} catch (Exception e) {
			ExceptionHandler.handleException(e, "服务器异常", yundaexLoanInfoListResponse);
		}
		return yundaexLoanInfoListResponse;
	}
}

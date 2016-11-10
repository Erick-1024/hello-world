package com.cana.yundaex.openapi.testcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.YundaexLoanInfoResponse;
import com.cana.yundaex.common.dto.YundaexReturnData;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
public class TestLoanResultController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String yundaexLoanResultUrl = "/cana/test/loanResult";
	
	@RequestMapping(value=yundaexLoanResultUrl,method=RequestMethod.POST)
	@ResponseBody
	public YundaexReturnData<YundaexBaseResponse> testPushAuditResult(@RequestBody YundaexLoanInfoResponse yundaexLoanInfoResponse){
		logger.info(yundaexLoanResultUrl + " 收到请求：{}", new Gson().toJson(yundaexLoanInfoResponse));
		System.err.println(new Gson().toJson(yundaexLoanInfoResponse));
		YundaexReturnData<YundaexBaseResponse> returnData = new YundaexReturnData<YundaexBaseResponse>();
		returnData.setErrorCode(ReturnCode.YD_SUCCESS_CODE.getRetMsg());
		returnData.setMsg("成功");
		
		YundaexBaseResponse yundaexBaseResponse = new YundaexBaseResponse();
		yundaexBaseResponse.setRetCode(ReturnCode.YD_SUCCESS_CODE.getRetMsg());
		yundaexBaseResponse.setRetMsg(ReturnCode.YD_SUCCESS_MESSAGE.getRetMsg());
		
		returnData.setData(yundaexBaseResponse);
		return returnData;
	}
}

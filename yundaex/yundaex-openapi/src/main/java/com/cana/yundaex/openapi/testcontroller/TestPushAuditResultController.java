package com.cana.yundaex.openapi.testcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.YundaexReturnData;
import com.cana.yundaex.common.dto.limit.YdLimitAuditResultRequest;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
public class TestPushAuditResultController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="cana/test/auditResultPush",method=RequestMethod.POST)
	@ResponseBody
	public YundaexReturnData<YundaexBaseResponse> testPushAuditResult(@RequestBody YdLimitAuditResultRequest ydLimitAuditResultRequest){
		logger.info("cana/test/auditResultPush" + " 收到请求：{}", new Gson().toJson(ydLimitAuditResultRequest));
		YundaexReturnData<YundaexBaseResponse> yundaexReturnData = new YundaexReturnData<>();
		yundaexReturnData.setErrorCode(ReturnCode.YD_SUCCESS_CODE.getRetMsg());
		yundaexReturnData.setMsg("接受成功");
		
		YundaexBaseResponse base = new YundaexBaseResponse();
		base.setRetCode(ReturnCode.YD_SUCCESS_CODE.getRetMsg());
		base.setRetMsg(ReturnCode.YD_SUCCESS_MESSAGE.getRetMsg());
		
		yundaexReturnData.setData(base);
		return yundaexReturnData;
	}
	
}

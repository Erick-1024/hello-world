package com.cana.yundaex.openapi.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.yundaex.openapi.utils.ExceptionHandler;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.cana.yundaex.api.IYundaexCreditApi;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;
import com.cana.yundaex.common.dto.YundaexBaseResponse;

/**
 * 客户申请资料接口
 * @author xiaoyu
 *
 */
@Controller
public class YdCustomerController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	
	@Resource
	private IYundaexCreditApi ydCreditApi;
	
	/**
	 * 接受韵达客户的申请数据，校验并保存在yundaex_customer_apply表中
	 * @param jsonAuditResult
	 * @return
	 */
	@RequestMapping(value = "/cana/ydCustomerApply",method=RequestMethod.POST)
	@ResponseBody
	public YundaexBaseResponse pushYundaexAuditResult(@RequestBody YdCustomerApplyRequestDTO jsonAuditResult) {
		logger.info("cana接收到韵达预审核结果推送:{}", gson.toJson(jsonAuditResult));
		YundaexBaseResponse response = new YundaexBaseResponse();
		try {
			ydCreditApi.saveYundaexAuditResult(jsonAuditResult);
			response.setRetCode(ReturnCode.SUCCESS.getRetCode());
			response.setRetMsg("操作成功");
			logger.info("接收成功");
		} catch(Exception e ) {
			ExceptionHandler.handleException(e, "韵达额度申请推送异常", response);
		}
		return response;
	}
}

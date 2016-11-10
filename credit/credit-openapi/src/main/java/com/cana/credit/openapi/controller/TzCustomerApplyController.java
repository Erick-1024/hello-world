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
import com.cana.vbam.common.credit.dto.apply.CustomerApplyRequestDTO;
import com.cana.vbam.common.credit.openapi.TravelzenBaseResponse;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
public class TzCustomerApplyController {
	@Resource
	private ICreditApi creditApi;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/tzCustomerApply", method=RequestMethod.POST)
	@ResponseBody
	public TravelzenBaseResponse pushTravelzenAuditResult(@RequestBody CustomerApplyRequestDTO jsonAuditResult) {
		
		logger.info("cana接收到真旅预审核结果推送:{}", jsonAuditResult);
		logger.info("实际控制人身份证照片:{}",jsonAuditResult.getRealControlPersonIdHandheldFrontCode());
		TravelzenBaseResponse response = new TravelzenBaseResponse();
		try {
			creditApi.saveTravelzenAuditResult(jsonAuditResult);
			response.setRetCode(ReturnCode.SUCCESS.getRetCode());
			response.setRetMsg("操作成功");
			logger.info("接收成功:{}", response);
		} catch(Exception e) {
			logger.error("真旅申请授信异常,tz_customer_id为{}",jsonAuditResult.getCustomerId());
			ExceptionHandler.handleException(e, "真旅预审核结果推送异常", response);
		}
		return response;
	}

}

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
import com.cana.vbam.common.credit.openapi.CreditLoanDetailRequest;
import com.cana.vbam.common.credit.openapi.CreditLoanDetailResponse;
import com.cana.vbam.common.credit.openapi.LoanInfoDetailRequest;
import com.cana.vbam.common.credit.openapi.LoanListResponse;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
@Controller
@RequestMapping(value="/credit/loan")
public class CreditLoanController {
	
	@Resource
	private ICreditApi creditApi;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	
	@RequestMapping(value = "/list" ,method=RequestMethod.POST)
	@ResponseBody
	public LoanListResponse getCreditLoanList(@RequestBody LoanInfoDetailRequest loanInfoDetailRequest) {
		logger.info("获取放款列表接口请求参数：{}", gson.toJson(loanInfoDetailRequest));
		LoanListResponse loanListResponse = new LoanListResponse(); 
		try{
			loanListResponse = creditApi.queryLoanInfoList(loanInfoDetailRequest);
			loanListResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
			loanListResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "查询放款信息列表发生异常", loanListResponse);
		}
		return loanListResponse;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public CreditLoanDetailResponse getCreditLoanDetail(@RequestBody CreditLoanDetailRequest request){
		logger.info("获取放款详情接口请求参数：{}", gson.toJson(request));
		CreditLoanDetailResponse response=new CreditLoanDetailResponse();
		try {
			response=creditApi.getCreditLoanDetail(request);
			response.setRetCode(ReturnCode.SUCCESS.name());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "获取账单明细详情异常", response);
		}
		return response;
	}
}

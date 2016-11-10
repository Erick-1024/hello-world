package com.cana.yundaex.openapi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.yundaex.dto.loanInfo.YundaexLoanInfoQueryDTO;
import com.cana.yundaex.api.IYundaexLoanInfoApi;
import com.cana.yundaex.common.dto.YundaexRepaymentRecordDTO;
import com.cana.yundaex.common.dto.YundaexRepaymentRecordResultDTO;
import com.cana.yundaex.openapi.utils.ExceptionHandler;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/cana")
public class YdLoanInfoController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IYundaexLoanInfoApi yundaexLoanInfoApiImpl;
	
	private static final Gson gson = new Gson();

	@RequestMapping(value = "/paymentsInfoQuery", method = RequestMethod.POST)
	@ResponseBody
	public YundaexRepaymentRecordResultDTO paymentsInfoQuery(@RequestBody YundaexLoanInfoQueryDTO loanInfoQueryDTO){
		logger.info("接收韵达查询还款信息请求：{}", gson.toJson(loanInfoQueryDTO));
		YundaexRepaymentRecordResultDTO response = new YundaexRepaymentRecordResultDTO();
		try {
			List<YundaexRepaymentRecordDTO> yundaexRepaymentRecordDTOList = yundaexLoanInfoApiImpl.repaymentRecord(loanInfoQueryDTO);
			response.setRetCode(AjaxResponseStatus.SUCCESS.name());
			response.setRetMsg(AjaxResponseStatus.SUCCESS.name());
			if(yundaexRepaymentRecordDTOList == null || yundaexRepaymentRecordDTOList.size() < 1){
				response.setCount(0);
			}else{
				response.setCount(yundaexRepaymentRecordDTOList.size());
				response.setStationNo(loanInfoQueryDTO.getStationNo());
				response.setLoanInfo(yundaexRepaymentRecordDTOList);
			}
			logger.info("韵达查询还款信息响应：{}", gson.toJson(response));
		} catch(Exception e ) {
			ExceptionHandler.handleException(e, "韵达查询还款信息异常", response);
		}
		return response;
	}
}

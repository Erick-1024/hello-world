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
import com.cana.flight.finance.common.dto.CreditAgentRepaymentDTO;
import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.flight.finance.common.dto.QueryCreditTradeStateDTO;
import com.cana.vbam.common.credit.dto.trade.CreditTradeResponse;
import com.cana.vbam.common.credit.dto.trade.CreditTradeStateResultDTO;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
@RequestMapping(value = "/credit/trade")
public class CreditTradeController {

	@Resource
	private ICreditApi creditApi;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Gson gson = new Gson();
	
	@RequestMapping(value = "/pay" ,method=RequestMethod.POST)
	@ResponseBody
	public CreditTradeResponse creditPay(@RequestBody CreditPayDTO creditPayDTO) {
		logger.info("收到支付请求：{}", gson.toJson(creditPayDTO));
		CreditTradeResponse creditTradeResponse = new CreditTradeResponse();
		try{
			String tranSeq = creditApi.creditPay(creditPayDTO);
			creditTradeResponse.setTranSeq(tranSeq);
			creditTradeResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
			creditTradeResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "授信支付发生异常", creditTradeResponse);
		}
		return creditTradeResponse;
	}
	
	@RequestMapping(value = "/refund" ,method=RequestMethod.POST)
	@ResponseBody
	public CreditTradeResponse creditRefund(@RequestBody CreditRefundDTO creditRefundDTO) {
		logger.info("收到退款请求：{}", gson.toJson(creditRefundDTO));
		CreditTradeResponse creditTradeResponse = new CreditTradeResponse();
		try {
			String tranSeq = creditApi.creditRefund(creditRefundDTO);
			creditTradeResponse.setTranSeq(tranSeq);
			creditTradeResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
			creditTradeResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "退款发生异常", creditTradeResponse);
		}
		return creditTradeResponse;
	}
	
	@RequestMapping(value = "/queryStatus" ,method=RequestMethod.POST)
	@ResponseBody
	public CreditTradeStateResultDTO queryTradeStatus(@RequestBody QueryCreditTradeStateDTO queryCreditTradeStateDTO) {
		logger.info("收到交易状态查询：{}", gson.toJson(queryCreditTradeStateDTO));
		CreditTradeStateResultDTO creditTradeStateResultDTO = new CreditTradeStateResultDTO();
		try{
		    creditTradeStateResultDTO = creditApi.queryCreditTradeState(queryCreditTradeStateDTO);
		    creditTradeStateResultDTO.setRetCode(ReturnCode.SUCCESS.getRetCode());
		    creditTradeStateResultDTO.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "服务器异常", creditTradeStateResultDTO);
		}
		return creditTradeStateResultDTO;
	}
	
	@RequestMapping(value = "/tzAccountRepayment" ,method=RequestMethod.POST)
	@ResponseBody
	public CreditTradeResponse creditAgentRepayment(@RequestBody CreditAgentRepaymentDTO creditAgentRepaymentDTO) {
		logger.info("收到账户还款请求：{}", gson.toJson(creditAgentRepaymentDTO));
		CreditTradeResponse creditTradeResponse = new CreditTradeResponse();
		try {
			String tranSeq = creditApi.creditAgentRepayment(creditAgentRepaymentDTO);
			creditTradeResponse.setTranSeq(tranSeq);
			creditTradeResponse.setRetCode(ReturnCode.SUCCESS.getRetCode());
			creditTradeResponse.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "账户还款发生异常", creditTradeResponse);
		}
		return creditTradeResponse;
	}
	
}

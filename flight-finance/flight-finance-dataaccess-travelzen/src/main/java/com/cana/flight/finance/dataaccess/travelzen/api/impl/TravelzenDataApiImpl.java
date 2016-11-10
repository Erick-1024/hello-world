package com.cana.flight.finance.dataaccess.travelzen.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.flight.finance.common.dto.AsyncNotifyTzCustomerApplyResult;
import com.cana.flight.finance.common.dto.AsyncNotifyTzCustomerCreditLimitResult;
import com.cana.flight.finance.common.dto.CreditTradeResult;
import com.cana.flight.finance.common.dto.TravelzenBaseResponse;
import com.cana.flight.finance.common.dto.TravelzenFlightTicketResponse;
import com.cana.flight.finance.common.dto.TravelzenRepaymentResponse;
import com.cana.flight.finance.common.util.Constants;
import com.cana.flight.finance.dataaccess.travelzen.api.ITravelzenDataApi;
import com.google.gson.Gson;
import com.travelzen.framework.net.http.HttpTookit;

@Component
public class TravelzenDataApiImpl implements ITravelzenDataApi {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Gson gson = new Gson();
	
	@Override
	public TravelzenFlightTicketResponse getFlightTickets(String lastRecordId, int num) {
		StringBuilder sb = new StringBuilder();
		if(lastRecordId != null)
			sb.append("lastRecordId=" + lastRecordId + "&");
		sb.append("num=" + num);
		return postFormAndLog(Constants.TRAVELZEN_FLIGHT_TICKET_URL_PREFIX + sb.toString(), null, TravelzenFlightTicketResponse.class);
	}
	
	@Override
	public TravelzenRepaymentResponse getRepayments(String lastRecordId, int num) {
		String url = Constants.TRAVELZEN_REPAYMENT_URL_PREFIX + (lastRecordId == null ? 0 : lastRecordId) + "/" + num;
		return postFormAndLog(url, null, TravelzenRepaymentResponse.class);
	}

	@Override
	public TravelzenBaseResponse sendAuditResult(AsyncNotifyTzCustomerApplyResult canaAuditResult, String url) {
		if(StringUtils.isBlank(url))
			return postJsonAndLog(Constants.TRAVELZEN_AUDIT_RESULT_URL_PREFIX, canaAuditResult, "发送审核结果结果：{}", TravelzenBaseResponse.class);
		else
			return postJsonAndLog(url, canaAuditResult, "发送审核结果结果：{}", TravelzenBaseResponse.class);
	}

	@Override
	public TravelzenBaseResponse sendCreditLimitFeedback(AsyncNotifyTzCustomerCreditLimitResult creditLimitResult, String url) {
		if(StringUtils.isBlank(url))
			return postJsonAndLog(Constants.TRAVELZEN_CREDIT_LIMIT_FEEDBACK_URL_PREFIX, creditLimitResult, "发送额度反馈结果：{}", TravelzenBaseResponse.class);
		else
			return postJsonAndLog(url, creditLimitResult, "发送额度反馈结果：{}", TravelzenBaseResponse.class);
	}
	
	@Override
	public TravelzenBaseResponse sendCreditTradeResult(String notifyUrl, CreditTradeResult creditTradeResult) {
		return postJsonAndLog(notifyUrl, creditTradeResult, "异步通知授信交易状态，返回结果：{}", TravelzenBaseResponse.class);
	}

	private <T> T postJsonAndLog(String url, Object body, String log, Class<T> classOfT) {
		String returnStr = HttpTookit.doPostJson(url, body);
		logger.info("url:{}", url);
		logger.info(log, returnStr);
		return gson.fromJson(returnStr, classOfT);
	}
	
	private <T> T postFormAndLog(String url, String log, Class<T> classOfT) {
		String returnStr = HttpTookit.doPostForm(url, null);
		if (log != null) {
			logger.info(log, returnStr);
		}
		return gson.fromJson(returnStr, classOfT);
	}
}

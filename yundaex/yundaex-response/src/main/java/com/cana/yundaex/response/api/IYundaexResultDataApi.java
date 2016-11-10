package com.cana.yundaex.response.api;

import org.codehaus.jettison.json.JSONException;

import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.YundaexLoanInfoResponse;

public interface IYundaexResultDataApi {

	/**
	 * 完成放款通知韵达
	 * @throws JSONException 
	 */
	public YundaexBaseResponse sendLoanInfoResult(String notifyUrl, YundaexLoanInfoResponse loanInfoResponse);

}


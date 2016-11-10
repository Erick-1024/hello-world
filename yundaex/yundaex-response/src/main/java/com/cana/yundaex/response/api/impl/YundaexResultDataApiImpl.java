/**
 * 
 */
package com.cana.yundaex.response.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.YundaexLoanInfoResponse;
import com.cana.yundaex.common.util.YundaexFromJson;
import com.cana.yundaex.response.api.IYundaexResultDataApi;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.net.http.HttpTookit;

/**
 * 通知韵达接口
 * 
 * @author guguanggong
 *
 */
@Component
public class YundaexResultDataApiImpl implements IYundaexResultDataApi {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Gson gson = new Gson();

	/**
	 * 完成放款通知韵达
	 * 
	 * @throws JSONException
	 */
	@Override
	public YundaexBaseResponse sendLoanInfoResult(String notifyUrl, YundaexLoanInfoResponse loanInfoResponse) {
		logger.info("放款通知韵达报文url{},内容{}", notifyUrl, gson.toJson(loanInfoResponse));
		String returnStr = HttpTookit.doPostJson(notifyUrl, loanInfoResponse);
		YundaexBaseResponse response = new YundaexBaseResponse();
		try {
			logger.info("放款通知韵达返回报文：{}", returnStr);
			String data = YundaexFromJson.getData(returnStr);
			if (StringUtils.isBlank(data)) {
				logger.error("放款通知接口返回报文为空,{}",data);
				throw WebException.instance("放款通知接口返回报文为空");
			}
			response = gson.fromJson(data, YundaexBaseResponse.class);
		} catch (JSONException e) {
			logger.error("放款通知接口，data转换错误", e);
		}
		return response;
	}
}

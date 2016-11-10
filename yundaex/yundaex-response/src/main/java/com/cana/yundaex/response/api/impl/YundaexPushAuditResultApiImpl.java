package com.cana.yundaex.response.api.impl;

import javax.annotation.Resource;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.service.impl.VbamCommonService;
import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.limit.YdLimitAuditResultRequest;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.common.util.YundaexFromJson;
import com.cana.yundaex.response.api.IYundaexPushAuditResultApi;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.net.http.HttpTookit;

@Component
public class YundaexPushAuditResultApiImpl implements IYundaexPushAuditResultApi{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Gson gson = new Gson();
	
	@Resource
	private VbamCommonService vbamCommonService;
	
	/**
	 * 推送审核结果信息给韵达
	 */
	@Override
	public YundaexBaseResponse sendAuditResultToYD(YdLimitAuditResultRequest ydLimitAuditResultRequest) {
		//加签名
		String stationNo = ydLimitAuditResultRequest.getStationNo(); 
		try {
			String sign = new String(vbamCommonService.sign(stationNo.getBytes(), Institution.yd.name(), false));
			ydLimitAuditResultRequest.setSign(sign);
		} catch (Exception e) {
			logger.error("推送审核结果，签名失败。");
			throw WebException.instance(ReturnCode.TP3022);
		}
		
		
		YundaexBaseResponse postJsonAndLog = postJsonAndLog(Constants.YUNDAEX_AUDIT_RESULT_URL_PREFIX, ydLimitAuditResultRequest);
		return postJsonAndLog;
	}

	private YundaexBaseResponse postJsonAndLog(String url, Object body) {
		logger.info("推送审核结果，推送url:{}", url);
		logger.info("推送审核结果，推送数据{}", gson.toJson(body));
		String returnStr = HttpTookit.doPostJson(url, body);
		logger.info("推送审核结果，韵达返回数据{}", returnStr);
		YundaexBaseResponse ydBaseResponse = new YundaexBaseResponse();
		try {
			String data = YundaexFromJson.getData(returnStr);
//			logger.info("推送审核结果，韵达返回数据{}", data);
			ydBaseResponse = gson.fromJson(data, YundaexBaseResponse.class);
		} catch (JSONException e) {
			logger.error("data转换错误", e);
		}
		return ydBaseResponse;
	}


}

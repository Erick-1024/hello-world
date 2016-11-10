/**
 * 
 */
package com.cana.yundaex.response.api.impl;

import javax.annotation.Resource;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoRequest;
import com.cana.yundaex.common.dto.YundaexTstationInfoResponse;
import com.cana.yundaex.common.util.Constants;
import com.cana.yundaex.common.util.YundaexFromJson;
import com.cana.yundaex.response.api.IYundaexPullTstationInfoApi;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.net.http.HttpTookit;

/**
 * 韵达项目 授信额度API实现类
 * 
 * @author guguanggong
 *
 */
@Component
public class YundaexPullTstationInfoApiImpl implements IYundaexPullTstationInfoApi {

	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Gson gson = new Gson();

	/**
	 * 拉取网点信息 网点编号和查询日期不能为空
	 */
	@Override
	public YundaexTstationInfoResponse getTstationInfoByParam(YundaexTstationInfoQueryDTO tstationInfoDTO) {
		String startDate = tstationInfoDTO.getStartDate();
		String endDate = tstationInfoDTO.getEndDate();
		String tstation = tstationInfoDTO.getStationNo();
		YundaexTstationInfoRequest req = new YundaexTstationInfoRequest();
		req.setStationNo(tstation);
		req.setEndDate(endDate);
		req.setStartDate(startDate);
		String sign = "";
		StringBuffer signStrBuf = new StringBuffer();
		signStrBuf.append(req.getStationNo()).append(req.getStartDate()).append(req.getEndDate());
		try {
			sign = new String(vbamCommonServiceImpl.sign(signStrBuf.toString().getBytes(), Institution.yd.name(), false));
		} catch (Exception e) {
			logger.error("拉网点的签名操作失败！", e);
			throw WebException.instance(ReturnCode.TP3022);
		}
		req.setSign(sign);
		logger.info("请求拉网点接口：{}", gson.toJson(req));
		return returnData(Constants.YUNDAEX_STATION_INFO_URL_PREFIX, req);
	}

	private YundaexTstationInfoResponse returnData(String url, Object body) {
		String returnStr = HttpTookit.doPostJson(url, body);
		YundaexTstationInfoResponse infoResponse = new YundaexTstationInfoResponse();
		try {
			logger.info("拉取网点数据：{}", returnStr);
			String data = YundaexFromJson.getData(returnStr);
			infoResponse = gson.fromJson(data, YundaexTstationInfoResponse.class);
		} catch (JSONException e) {
			logger.error("拉取网点数据接口，data转换错误", e);
			throw WebException.instance("data转换错误");
		}
		return infoResponse;
	}

}

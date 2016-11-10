package com.cana.yundaex.openapi.testcontroller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.yundaex.common.dto.YundaexReturnData;
import com.cana.yundaex.common.dto.YundaexTstationInfoDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoRequest;
import com.cana.yundaex.common.dto.YundaexTstationInfoResponse;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.util.DateUtils;

@Controller
public class TestStationPullController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String yundaexTstationInfoUrl = "/cana/test/stationPull";

	@RequestMapping(value=yundaexTstationInfoUrl, method=RequestMethod.POST)
	@ResponseBody
	public YundaexReturnData<YundaexTstationInfoResponse> testTstationInfo(@RequestBody YundaexTstationInfoRequest tstationInfoRequest) throws Exception {
		logger.info(yundaexTstationInfoUrl + " 收到请求：{}", new Gson().toJson(tstationInfoRequest));
		YundaexTstationInfoResponse response = new YundaexTstationInfoResponse();
		ReturnCode retCode = ReturnCode.YD_SUCCESS_CODE;
		ReturnCode retMes = ReturnCode.YD_SUCCESS_MESSAGE;
//		if (RandomUtils.nextInt(100) >= 10) {
//			retCode = ReturnCode.ERROR;
//		}
		StringBuffer loanBuf = new StringBuffer();
		loanBuf.append(tstationInfoRequest.getStationNo())
				.append(StringUtils.isBlank(tstationInfoRequest.getStartDate()) ? "" : tstationInfoRequest.getStartDate())
				.append(StringUtils.isBlank(tstationInfoRequest.getEndDate()) ? "" : tstationInfoRequest.getEndDate());
//		if (!RSAUtil.verify(loanBuf.toString().getBytes(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUxSOd6FKhZsUIm1k92pcb7PQe44CE2ynJXEC8aVA6+mowriKr0lndxD57g0XJ5FxRG4Dg3GSIhIYNX4AUKALJepE8FEibYUc7c+2fsFnodVY+QNbsPaY9Y2ckphMamNBIozCL66UuXlOMDxM2PsXwMhzVsY1qWG9ltRVojQ/OQwIDAQAB", tstationInfoRequest.getSign().getBytes()))
//			throw WebException.instance(ReturnCode.TP3006);
		
		List<YundaexTstationInfoDTO> dtos = new ArrayList<YundaexTstationInfoDTO>();
		
		int month = DateUtils.getMonths(DateUtils.formatDate(tstationInfoRequest.getStartDate(), 19),
				DateUtils.formatDate(tstationInfoRequest.getEndDate(), 19));
		
		for (int i = 0; i < month; i++) {
			YundaexTstationInfoDTO dto = new YundaexTstationInfoDTO();
			dto.setStationNo(tstationInfoRequest.getStationNo());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtils.formatDate(tstationInfoRequest.getEndDate(), 19));
			calendar.add(Calendar.MONTH, -(i+1));
			Date startTime = calendar.getTime();
			dto.setStatmonth(DateUtils.formatDate(startTime, "yyyy-MM"));
			dto.setReceiveTotal((10000*(24-i)));
			dto.setAvgReceiveNum(new BigDecimal(12.12));
			dto.setReceiveSumSigned(123123);
			dto.setReceiveSumUnsign(123123);
			dto.setSendTotal((5000*(24-i)));
			dto.setAvgSendNum(new BigDecimal(100.01));
			dto.setSendSumSigned(2000);
			dto.setSendSumUnsign(1000);
			dto.setRecSendDif(1000);
			dto.setRecSendRatio(new BigDecimal(1000.01));
			dtos.add(dto);
		}
		response.setOperataDatas(dtos);
		response.setCount(month);
		response.setRetCode(retCode.getRetMsg());
		response.setRetMsg(retMes.getRetMsg());
		
		YundaexReturnData<YundaexTstationInfoResponse> returnData = new YundaexReturnData<YundaexTstationInfoResponse>();
		returnData.setErrorCode(retCode.getRetMsg());
		returnData.setMsg(retMes.getRetMsg());
		returnData.setData(response);
		return returnData;
	}

}

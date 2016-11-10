package com.cana.vbam.front.biz.controller.early.warning;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.early.warning.api.IEarlyWarningApi;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.early.warning.dto.EarlyWarningEventDetailDTO;
import com.cana.vbam.common.early.warning.enums.EarlywarningLevel;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.FrontExceptionHandler;

@Controller
@RequestMapping("/earlywarning")
public class EarlyWarningController {
	
	@Resource
	private IEarlyWarningApi earlyWarningApiImpl;
	
	@RequestMapping(value = "/earlyWarningStandard", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> queryEarlyWarningStandard(String earlywarningLevelStr) {
		try {
			return ObjectResult.success("获取最新预警标准成功", earlyWarningApiImpl.queryEarlyWarningStandard(earlywarningLevelStr));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/getPredictEarlyWarningLevel/{action}", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> getPredictEarlyWarningLevel(EarlyWarningEventDetailDTO earlyWarningEventDetailDTO, @PathVariable String action, String companyName, String outCustomerName) {
		try {
			earlyWarningEventDetailDTO.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			Map<EarlywarningLevel, String> returnValue = earlyWarningApiImpl.getPredictEarlyWarningLevel(earlyWarningEventDetailDTO, action, companyName, outCustomerName);
			if(returnValue.containsKey(null))
				returnValue = null;
			return ObjectResult.success("获取预计预警等级成功", returnValue);
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
}

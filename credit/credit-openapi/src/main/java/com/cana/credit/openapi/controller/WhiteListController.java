package com.cana.credit.openapi.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.credit.openapi.utils.ExceptionHandler;
import com.cana.vbam.common.credit.dto.white.WhiteListResponseDTO;
import com.travelzen.framework.core.common.ReturnCode;

@Controller
@RequestMapping(value = "/whitelist")
public class WhiteListController {

	private static final Logger logger = LoggerFactory.getLogger(WhiteListController.class);
	
	@Resource
	private ICreditApi creditApi;
	
	@RequestMapping(value = "check/{customerId}")
	@ResponseBody
	public WhiteListResponseDTO isWhite(@PathVariable String customerId) {
		logger.info("客户申请初步验证请求，验证客户：{}", customerId);
		WhiteListResponseDTO whiteListResponseDTO = new WhiteListResponseDTO();
		try {
//			whiteListResponseDTO.setInWhiteList(creditApi.precheck(customerId));
			whiteListResponseDTO.setInWhiteList(Boolean.TRUE);
			whiteListResponseDTO.setRetCode(ReturnCode.SUCCESS.getRetCode());
			whiteListResponseDTO.setRetMsg(ReturnCode.SUCCESS.getRetMsg());
		} catch(Exception e) {
			ExceptionHandler.handleException(e, "客户申请初步验证失败", whiteListResponseDTO);
		}
		return whiteListResponseDTO;
	}
	
}

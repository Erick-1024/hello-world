package com.cana.vbam.front.biz.controller.vj;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.vbam.common.credit.enums.CreditLimitStatus;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.vj.dto.VJLimitListQueryDTO;
import com.cana.vbam.common.vj.dto.VJLimitListResponseDTO;
import com.cana.vj.api.IVJApi;

@Controller
@RequestMapping(value = "/vj/limit")
public class LimitController {

	@Resource
	private IVJApi vjApi;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String showCustomerLimitList(Model model) {
		model.addAttribute("limitStatusList", Arrays.asList(CreditLimitStatus.values()));
		return "page/vj/limit/list";
	}

	@RequestMapping(value = "/searchList", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<VJLimitListResponseDTO> searchLimitList(VJLimitListQueryDTO queryDTO) {
		try {
			ListResult<VJLimitListResponseDTO> response = vjApi.getCustomerLimitList(queryDTO);
			return response;
		} catch (Exception e) {
			logger.info("获取额度列表异常:" + e);
			return ListResult.fail("获取额度列表异常");
		}
	}

}

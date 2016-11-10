/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.vbam.front.biz.controller.credit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerParamDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleDTO;
import com.cana.vbam.common.credit.dto.white.WhiteCustomerRuleParamDTO;
import com.cana.vbam.common.dto.ListResult;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author ducer
 *
 */
@Controller
@RequestMapping("/credit/white")
public class WhiteListController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ICreditApi creditApi;

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String whiteListPage() {
		return "page/credit/white/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<WhiteCustomerRuleDTO> getWhiteCustomers(WhiteCustomerRuleParamDTO param) {
		try {
			PageList<WhiteCustomerRuleDTO> page = creditApi.getWhiteCustomerRules(param);

			return ListResult.success(page.getRecords(), page.getTotalRecords());
		} catch (WebException e) {
			logger.error("授信-白名单列表-自定义异常", e);
			return ListResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("授信-白名单列表-未知异常", e);
			return ListResult.fail("系统异常");
		}
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.GET })
	public String whiteListDetail(Integer batchNo, Model model) {
		if (batchNo == null) {
			logger.error("授信-白名单列表跳转白名单详情没有传入批次号");
			throw WebException.instance("系统错误，请联系管理员");
		}
		model.addAttribute("batchNo", batchNo);
		return "page/credit/white/detail";
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<WhiteCustomerDTO> getWhiteCustomerRules(WhiteCustomerParamDTO param) {
		try {
			PageList<WhiteCustomerDTO> page = creditApi.getWhiteCustomers(param);
			return ListResult.success(page.getRecords(), page.getTotalRecords());
		} catch (WebException e) {
			logger.error("授信-白名单详情-自定义异常", e);
			return ListResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("授信-白名单详情-未知异常", e);
			return ListResult.fail("系统异常");
		}
	}
}

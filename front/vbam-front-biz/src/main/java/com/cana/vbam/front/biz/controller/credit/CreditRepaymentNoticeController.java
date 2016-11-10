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

import com.cana.asset.api.IAssetApi;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;

@Controller
@RequestMapping("/credit")
public class CreditRepaymentNoticeController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private IAssetApi assetApi;
	@Resource
	private IUserApi userApi;

	@RequestMapping(value = "/repaymentNotice")
	public String getWhiteCustomers(Model model, String accountNo, String customerId) {
		try {
			CustomerDetailDTO company = userApi.queryCustomerDetail(customerId);
			model.addAttribute("accountName", company.getCompanyName());
			model.addAttribute("accountNo", accountNo);
		} catch (Exception e) {
			logger.error("", e);
			return "redirect:/welcome";
		}
		return "page/credit/repaymentNotice";
	}

}

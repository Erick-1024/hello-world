/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.front.biz.controller.account;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.vbam.common.account.dto.AccountPrintCodeDTO;
import com.cana.vbam.common.account.dto.AccountPrintCodeResultDTO;
import com.travelzen.framework.core.exception.WebException;

/**
 * @author guguanggong
 *
 */
@Controller
@RequestMapping(value = "/account/printcode")
public class AccountPrintCodeController {

	@Resource
	private IAccountApi accountApi;

	/**
	 * 打印码
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/printCodeList")
	public String listTradeRecord(Model model) throws Exception {
		return "page/account/printcode/printCodeList";
	}

	/**
	 * 打印列表
	 * @param codeDTO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryAccountPrintCode", method = { RequestMethod.POST })
	@ResponseBody
	public List<AccountPrintCodeResultDTO> queryAccountPrintCode(AccountPrintCodeDTO codeDTO) throws Exception {
		vaildate(codeDTO);
		return accountApi.queryAccountPrintCode(codeDTO);
	}

	private void vaildate(AccountPrintCodeDTO codeDTO) {
		if (StringUtils.isBlank(codeDTO.getAccountNo())) {
			throw WebException.instance("查询账号不能为空");
		}
		if (codeDTO.getStartIndex() < 1) {
			throw WebException.instance("起始记录号不能小于0");
		}
		if (StringUtils.isBlank(codeDTO.getStartDate())) {
			throw WebException.instance("交易日期起日不能为空");
		}
		if (StringUtils.isBlank(codeDTO.getEndDate())) {
			throw WebException.instance("交易日期止不能为空");
		}
	}
	
	

}

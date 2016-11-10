package com.cana.vbam.front.biz.controller.account;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IAccountApi accountApi;
	
	/**
	 * 账户列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list")
	public String listAccount(Model model) throws Exception{
		List<AccountStatus> accountStatusList = Arrays.asList(AccountStatus.values());
		model.addAttribute("accountStatusList", accountStatusList);
		List<AccountType> accountTypeList = Arrays.asList(AccountType.values());
		model.addAttribute("accountTypeList",accountTypeList);
		List<AccountSupervisionStatus> accountSupervisionStatusList = Arrays.asList(AccountSupervisionStatus.values());
		model.addAttribute("accountSupervisionStatusList",accountSupervisionStatusList);
		List<AccountAccumulationStatus> accountAccumulationStatusList = Arrays.asList(AccountAccumulationStatus.values());
		model.addAttribute("accountAccumulationStatusList",accountAccumulationStatusList);
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		model.addAttribute("userType",userType);
		return "page/account/list";
	}
	
	/**
	 * 我的账户（针对CANA用户）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listSelf")
	public String listSelfAccount(Model model) {
		List<AccountStatus> accountStatusList = Lists.newArrayList();
		for(AccountStatus accountStatus : AccountStatus.values()){
			if(accountStatus == AccountStatus.HANDLING)
				continue;
			accountStatusList.add(accountStatus);
		}
		model.addAttribute("accountStatusList", accountStatusList);
		CustomerDetailDTO customerDTO= userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
		String companyId = customerDTO.getId();
		model.addAttribute("companyId",companyId);
		return "page/account/listSelf";
	}
	
	/**
	 * 根据查询条件 查询账户列表
	 * @param accountQueryCriteria
	 * @return
	 */
	@RequestMapping(value="/searchList")
	@ResponseBody
	public PageResult<AccountDTO> searchAccountList(AccountQueryCriteria accountQueryCriteria){
		String customerId = SecurityContextUtils.getCustomerId();
		PageResult<AccountDTO> result = accountApi.queryAccounts(customerId, accountQueryCriteria);
		return result;
	}
	
	
	/**
	 * 账户详情
	 * @param accountId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail")
	public String accountDetail(@RequestParam String accountId, Model model) throws Exception{
		String customerId = SecurityContextUtils.getCustomerId();
		CustomerDetailDTO customerDTO= userApi.queryCustomerDetail(customerId);
		UserType userType = customerDTO.getUserType();
		model.addAttribute("currentUserType",userType);
		AccountDTO accountDTO = accountApi.getAccount(customerId, accountId);
		model.addAttribute("accountDTO",accountDTO);
		model.addAttribute("pageType", "pageDetail");
		return "page/account/detail";
	}
	
	/**
	 * 账户冻结
	 * @param accountId
	 * @param model
	 * @return boolean
	 */
	@RequestMapping(value="/freeze")
	@ResponseBody
	public String accountFreezeFund(@RequestParam String accountId) {
		String result = "success";
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		String customerId = SecurityContextUtils.getCustomerId();
		try{
			accountApi.accountFreeze(userId, customerId, accountId);
		}catch(WebException e){
		    logger.error("冻结账户错误", e);
			return e.getMessage();
		}catch(Exception e){
		    logger.error("冻结账户错误", e);
		    return "冻结账户错误";
        }
		return result;
	}
	
	/**
	 * 账户解冻
	 * @param accountId
	 * @param model
	 * @return boolean
	 */
	@RequestMapping(value="/unfreeze")
	@ResponseBody
	public String accountUnfreezeFund(@RequestParam String accountId) {
		String result = "success";
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		String customerId = SecurityContextUtils.getCustomerId();
		try{
			accountApi.accountUnfreeze(userId, customerId, accountId);
		}catch(WebException e){
			result = e.getMessage();
			logger.error(result, e);
		}catch(Exception e){
            result = e.getMessage();
            logger.error(result, e);
            return "解冻账户错误";
        }
		return result;
	}
	
	/**
	 * 设为默认账户
	 * @param accountId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/setDefault")
	@ResponseBody
	public String accountSetDefaultRepayment(@RequestParam String accountId) {
		String result = "success";
		try{
			accountApi.accountSetDefaultRepayment(SecurityContextUtils.getCustomerId(), accountId);
		}catch(WebException e){
			result = e.getMessage();
			logger.error(result, e);
		}catch(Exception e){
            result = e.getMessage();
            logger.error(result, e);
            return "设置默认账户出错";
        }
		return result;
	}
}

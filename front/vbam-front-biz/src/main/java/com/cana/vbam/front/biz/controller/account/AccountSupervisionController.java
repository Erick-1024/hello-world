/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.front.biz.controller.account;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IRoleApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.model.UserDetailsDTO;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.dto.AccountSupervisionCreateDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;
import com.cana.vbam.common.account.enums.AccountTradeApplyType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.dto.AjaxResponse;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.front.biz.vo.account.CheckAccountCreateSupervisionVo;
import com.cana.vbam.front.biz.vo.account.CreateSupervisionResult;
import com.google.common.collect.Maps;
import com.travelzen.framework.core.exception.WebException;

/**
 * 监管关系
 * @author XuMeng
 *
 */
@Controller
@RequestMapping(value = "/account/supervision")
public class AccountSupervisionController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IUserApi userApi;
	@Resource
	private IRoleApi roleApi;

	/**
	 * 新建监管页面
	 */
	@RequestMapping(value = "create", method=RequestMethod.GET)
    public String createView(Model model) throws Exception {
	    String customerId = SecurityContextUtils.getCustomerId();
	    CustomerDetailDTO customer = userApi.queryCustomerDetail(customerId);
	    model.addAttribute("customer", customer);
	    model.addAttribute("userType", customer.getUserType());
	    if (UserType.FINACE.equals(customer.getUserType())) {
	        AccountQueryCriteria criteria = new AccountQueryCriteria();
	        criteria.setPageSize(Integer.MAX_VALUE);
	        criteria.setAccountStatus(AccountStatus.NORMAL);
	        criteria.setAccountType(AccountType.SPECIAL);
	        criteria.setSupervisoryStatus(AccountSupervisionStatus.NO_SUPERVISION);
	        criteria.setAccumulationStatus(AccountAccumulationStatus.NO_ACCUMULATION);
	        PageResult<AccountDTO> accountDTOs = accountApi.queryAccounts(customerId, criteria);
	        model.addAttribute("specialAccounts", formatAccountNo(accountDTOs.getData()));
	        model.addAttribute("supervisorUserType", UserType.FACTOR);
	    } else if (UserType.FACTOR.equals(customer.getUserType())) {
	        model.addAttribute("supervisorUserType", UserType.FINACE);
	    } else {
	        throw WebException.instance("无权访问该页面");
	    }
        return "page/account/supervision/create";
    }

	private List<AccountDTO> formatAccountNo(List<AccountDTO> accounts) {
	    if (CollectionUtils.isNotEmpty(accounts)) {
	        for (AccountDTO account : accounts) {
	            account.setAccountNo(AccountNoUtil.formatBankAccountNo(account.getAccountNo()));
	        }
	    }
	    return accounts;
	}
	/**
	 * 提交新建监管
	 */
	@RequestMapping(value = "create", method=RequestMethod.POST)
	@ResponseBody
    public CreateSupervisionResult createSupervision(AccountSupervisionCreateDTO createDTO) {
        String userId = SecurityContextUtils.getUserDTOFromSession().getId();
        CreateSupervisionResult result = new CreateSupervisionResult();
        try {
            String applyId = accountApi.createSupervision(userId, createDTO);
            result.setResult(true);
            result.setApplyId(applyId);
            return result;
        } catch (WebException e) {
            logger.error(e.getMessage(), e);
            result.setReason(e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("提交新建监管出错", e);
            result.setReason("未知错误");
            return result;
        }
    }
	@RequestMapping(value = "create_success", method=RequestMethod.GET)
    public String createSuccess(Model model) {
	    return "page/account/supervision/create_success";
	}

	/**
	 * 账户解除监管关系
	 * @param accountId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public String gotoRemoveSupervision(@RequestParam String accountId, Model model) throws Exception{
		String customerId = SecurityContextUtils.getCustomerId();
		CustomerDetailDTO customerDTO= userApi.queryCustomerDetail(customerId);
		UserType userType = customerDTO.getUserType();
		model.addAttribute("currentUserType",userType);
		AccountDTO accountDTO = accountApi.getAccount(customerId, accountId);
		model.addAttribute("accountDTO",accountDTO);
		model.addAttribute("pageType", "pageRemoveSupervision");
		return "page/account/detail";
	}
	
	/**
	 * 确认 解除监管关系
	 * @param accountIds
	 * @return
	 */
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse comfirmRemoveSupervision(@RequestParam String accountIdString){
		String[] accountIds = accountIdString.split(";");
		UserDetailsDTO userDetail = SecurityContextUtils.getUserFromSession();
    	UserSessionDTO userDTO= userDetail.getUserData();
    	String applyId = "";
    	Map<String, Object> data = Maps.newHashMap();
    	try{
    		applyId = accountApi.removeSupervision(userDTO.getId(), Arrays.asList(accountIds));
    		data.put("result", applyId);
			return AjaxResponse.success("解除监管关系成功", data);
    	} catch (WebException e) {
    		logger.error(e.getMessage(),e);
			return AjaxResponse.fail(e.getMessage());
		}
	}

	@RequestMapping(value="audit", method=RequestMethod.POST)
	@ResponseBody
    public String auditPost(String applyId, boolean isAgree, String message) {
	    String userId = SecurityContextUtils.getUserDTOFromSession().getId();
        try {
            boolean result = accountApi.auditSupervision(userId, applyId, isAgree, message);
            if (result) {
                return "success";
            } else {
                return "审核失败";
            }
        } catch (WebException e) {
            logger.error(e.getMessage(), e);
            return e.getMessage();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "未知错误";
        }
    }

    @RequestMapping(value="audit", method=RequestMethod.GET)
    public String auditView(String applyId, Model model) throws Exception {
        setSupervisionPageModelDatas(applyId, model);
        model.addAttribute("showAuditButtons", true);
        return "page/account/supervision/applyDetail";
    }

	@RequestMapping(value="detail", method=RequestMethod.GET)
    public String detail(String applyId, Model model) throws Exception {
	    setSupervisionPageModelDatas(applyId, model);
	    model.addAttribute("showAuditButtons", false);
        return "page/account/supervision/applyDetail";
    }

	private void setSupervisionPageModelDatas(String applyId, Model model) throws Exception {
	    String userId = SecurityContextUtils.getUserDTOFromSession().getId();
        CustomerDetailDTO customer = userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
        AccountTradeApplyDTO applyDTO = accountApi.getSupervisionApply(userId, applyId);
        boolean isPendingAudit = AccountTradeApplyStatus.PENDINGAUDIT.equals(applyDTO.getApplyStatus());
        boolean isRemoveApply = AccountTradeApplyType.REMOVE_SUPERVISION.equals(applyDTO.getApplyType());
        boolean isAccountOwnedByCurrentUser = customer.getCompanyName().equals(applyDTO.getAccountName());
        boolean isSpecialAccountOwnedByCurrentUser = UserType.FINACE.equals(customer.getUserType());
        boolean showAccountBalance = isPendingAudit && (isRemoveApply || isAccountOwnedByCurrentUser);
        boolean haveSpecialAccount = CollectionUtils.isNotEmpty(applyDTO.getSpecialAccounts());
        boolean showSpecialAccountBalance = haveSpecialAccount && isPendingAudit && (isRemoveApply || isSpecialAccountOwnedByCurrentUser);
        boolean showTotalBalance = showAccountBalance && showSpecialAccountBalance;
        boolean showFinaceBalance = isPendingAudit && isRemoveApply;
        boolean showDefaultRepaymentItem = !isRemoveApply || isPendingAudit;
        model.addAttribute("applyDTO", formatAccountNos(applyDTO));
        model.addAttribute("isRemoveApply", isRemoveApply);
        model.addAttribute("isPendingAudit", isPendingAudit);
        model.addAttribute("userType", customer.getUserType());
        model.addAttribute("applyByCurrentCustomer", applyDTO.getApplyCompanyId().equals(customer.getId()));
        model.addAttribute("showAccountBalance", showAccountBalance);
        model.addAttribute("showSpecialAccountBalance", showSpecialAccountBalance);
        model.addAttribute("showTotalBalance", showTotalBalance);
        model.addAttribute("showFinaceBalance", showFinaceBalance);
        model.addAttribute("showDefaultRepaymentItem", showDefaultRepaymentItem);
	}

	private AccountTradeApplyDTO formatAccountNos(AccountTradeApplyDTO applyDTO) {
	    applyDTO.setAccountNo(AccountNoUtil.formatBankAccountNo(applyDTO.getAccountNo()));
	    if (CollectionUtils.isNotEmpty(applyDTO.getSpecialAccounts())) {
	        for (AccountDTO acc : applyDTO.getSpecialAccounts()) {
	            acc.setAccountNo(AccountNoUtil.formatBankAccountNo(acc.getAccountNo()));
	        }
	    }
	    if (CollectionUtils.isNotEmpty(applyDTO.getRemoveSupervisionAccounts())) {
            for (AccountDTO acc : applyDTO.getRemoveSupervisionAccounts()) {
                acc.setAccountNo(AccountNoUtil.formatBankAccountNo(acc.getAccountNo()));
            }
        }
	    return applyDTO;
	}

	/**
	 * 校验一般账户
	 * @param accountNo
	 * @param supervisorName
	 */
	@RequestMapping(value="checkGeneralAccountNo")
	@ResponseBody
	public CheckAccountCreateSupervisionVo checkGeneralAccountNo(String accountNo, String supervisorName) {
	    return checkAccount(accountNo, supervisorName, AccountType.GENERAL);
	}
	
	@RequestMapping(value="checkSpecialAccountNo")
    @ResponseBody
    public CheckAccountCreateSupervisionVo checkSpecialAccountNo(String accountNo, String supervisorName) {
        return checkAccount(accountNo, supervisorName, AccountType.SPECIAL);
    }

	@RequestMapping(value="checkSupervisorName")
    @ResponseBody
    public String checkSupervisorName(String supervisorName) {
	    
        try {
            CustomerDetailDTO customer = userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
            UserType supervisorUserType = UserType.FACTOR.equals(customer.getUserType()) ? UserType.FINACE : UserType.FACTOR;
            CustomerDetailDTO supervisor = userApi.queryCustomerByCompanyName(supervisorName, supervisorUserType);
//            if (!supervisorUserType.equals(supervisor.getUserType())) {
//                return "请填入" + supervisorUserType.desc() + "类型的客户";
//            }
            if (null == supervisor) {
                return "请填入有效的"+supervisorUserType.desc()+"";
            }
            return "";
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            return "未知错误";
        }
    }

	private CheckAccountCreateSupervisionVo checkAccount(String accountNo,
            String supervisorName, AccountType shouldBeType) {
		String customerId = SecurityContextUtils.getCustomerId();
        CheckAccountCreateSupervisionVo result = new CheckAccountCreateSupervisionVo();
        try {
            List<AccountDTO> accounts = accountApi.getAccountByNos(customerId,accountNo);
            if (CollectionUtils.isEmpty(accounts)) {
                result.setReason("账户不存在");
                return result;
            }
            AccountDTO account = accounts.get(0);
            if (AccountType.GENERAL.equals(shouldBeType)) {
                CustomerDetailDTO customer = userApi.queryCustomerDetail(customerId);
                if (!account.getAccountName().equals(supervisorName)
                        && !account.getAccountName().equals(customer.getCompanyName())) {
                    result.setReason("请输入资金方或融资客户的一般账户");
                    return result;
                }
                //判断是不是回款账户
                if (BooleanUtils.isTrue(account.isTransferInAccount())) {
                    result.setReason("该账户是资金方的回款账户");
                    return result;
                }
            } else {
                if (!account.getAccountName().equals(supervisorName)) {
                    result.setReason("请输入融资客户的账户");
                    return result;
                }
            }
            if (!shouldBeType.equals(account.getAccountType())) {
                result.setReason("该账户不是" + shouldBeType.desc());
                return result;
            }
            if (!AccountStatus.NORMAL.equals(account.getAccountStatus())) {
                result.setReason("该账户状态为" + account.getAccountStatus().desc());
                return result;
            }
            if (!AccountSupervisionStatus.NO_SUPERVISION.equals(account.getSupervisionStatus())) {
                result.setReason("该账户已经有存在的监管关系");
                return result;
            }
            result.setResult(true);
            result.setAccountId(account.getAccountId());
            result.setAccountName(account.getAccountName());
            result.setBuyerName(account.getBuyerName());
            return result;
	    } catch (WebException e) {
            logger.error(e.getMessage(), e);
            result.setReason(e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setReason("未知错误");
            return result;
        }
    }
}

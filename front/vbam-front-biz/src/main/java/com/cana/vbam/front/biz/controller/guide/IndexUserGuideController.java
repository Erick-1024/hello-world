package com.cana.vbam.front.biz.controller.guide;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.member.authorization.service.MemberGuideService;
import com.cana.repayment.api.IRepaymentRuleApi;
import com.cana.signature.api.ICertApplyApi;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.signature.dto.CertResultDTO;
import com.cana.yundaex.api.IYundaexAuditApi;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping(value = "/guide")
public class IndexUserGuideController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IUserApi userApi;
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IRepaymentRuleApi repaymentRuleApiImpl;
	@Resource
	private MemberGuideService memberGuideService;
	@Resource
	private ICertApplyApi certApplyApiImpl;
	@Resource
	private IYundaexAuditApi ydAuditApi;

	@RequestMapping(value = "setup")
	public String setup(Model model) throws Exception {
		if (memberGuideService.needRedirect2GuidePage()) {
			CustomerDetailDTO customerDTO = userApi.queryCustomerDetail(SecurityContextUtils.getCustomerId());
			if (UserType.FACTOR.equals(customerDTO.getUserType())) {
				return getFactorGuideView(model, customerDTO);
			} else if (UserType.FINACE.equals(customerDTO.getUserType())) {
				return getFinaceGuideView(model, customerDTO);
			}
		}
		return "redirect:/welcome";
	}

	/**
	 * 绑定真旅客户的融资商进入引导页
	 */
	private String getFinaceGuideView(Model model, CustomerDetailDTO customerDTO) {
		model.addAttribute("accounts", getAllGeneralAndNoSupervisionAccounts(customerDTO.getId()));
		model.addAttribute("customerDTO", customerDTO);
		if (customerDTO.getGuideStatus().contains(UserGuideStatus.NEED_GENERATE_CONTRACT))
			return "/page/guide/travelzen/comfirmInfomation";
		else if (customerDTO.getGuideStatus().contains(UserGuideStatus.NEED_GENERATE_CONTRACT_YUNDAEX)) {
			model.addAttribute("customerAppplyDTO", ydAuditApi.getUserBaseInfo(customerDTO.getId()));
			return "/page/guide/yundaex/comfirmInfomation";
		} else
			throw WebException.instance("未知错误");
	}

	/**
	 * 进入资金方的引导页
	 */
	private String getFactorGuideView(Model model, CustomerDetailDTO customerDTO) {
		if (customerDTO.getGuideStatus() == null) {
			model.addAttribute("customerDTO", customerDTO);
			return "/page/guide/factor/createAccount";
		} else {
			model.addAttribute("accounts", getAllGeneralAndNoSupervisionAccounts(customerDTO.getId()));
			model.addAttribute("ruleId", repaymentRuleApiImpl.generateRuleId());
			model.addAttribute("defaultRepaymentRule",
					repaymentRuleApiImpl.generateDefaultRepaymentRule(SecurityContextUtils.getCustomerId()));
			return "/page/guide/factor/comfirmRule";
		}
	}

	/**
	 * 获取所有的一般非监管账户
	 */
	private List<AccountDTO> getAllGeneralAndNoSupervisionAccounts(String customerId) {
		AccountQueryCriteria criteria = new AccountQueryCriteria();
		criteria.setPageSize(Integer.MAX_VALUE);
		criteria.setAccountStatus(AccountStatus.NORMAL);
		criteria.setAccountType(AccountType.GENERAL);
		criteria.setSupervisoryStatus(AccountSupervisionStatus.NO_SUPERVISION);
		PageResult<AccountDTO> accounts = accountApi.queryAccounts(customerId, criteria);
		return accounts.getData();
	}

	@RequestMapping("/apply/cert")
	@ResponseBody
	public ObjectResult<String> applyCert(String p10) {
		if (StringUtils.isBlank(p10)) {
			logger.info("p10为空");
			return ObjectResult.fail("申请书生成失败,请联系管理员。联系电话：021-53866655!");
		}
		try {
			CertResultDTO certResult = certApplyApiImpl.applyOrReissueCert(SecurityContextUtils.getOperatorId(),
					p10);
			return ObjectResult.success("申请证书成功", certResult.getSignatureCert());
		} catch (Exception e) {
			logger.error("申请证书或补发失败!", e);
			return ObjectResult.fail("安装证书错误，请联系管理员。联系电话：021-53866655!");
		}
	}
}

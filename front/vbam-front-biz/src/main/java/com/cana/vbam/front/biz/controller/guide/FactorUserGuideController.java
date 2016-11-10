package com.cana.vbam.front.biz.controller.guide;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.account.api.IAccountApi;
import com.cana.member.api.IUserApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.IRepaymentRuleApi;
import com.cana.vbam.common.account.dto.AccountSelfCreateDTO;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.enums.user.UserGuideStatus;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleDTO;
import com.travelzen.framework.core.exception.WebException;

/**
 * 保理商引导页面
 */
@Controller
@RequestMapping(value = "/guide")
public class FactorUserGuideController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IUserApi userApi;
	@Resource
	private IAccountApi accountApi;
	@Resource
	private IRepaymentRuleApi repaymentRuleApiImpl;

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<Void> createAccount() {
		String customerId = SecurityContextUtils.getCustomerId();
		AccountSelfCreateDTO info = new AccountSelfCreateDTO();
		info.setCustomerId(customerId);
		info.setAccountNumber(1);
		try {
			accountApi.createAccountBySelf(info);
			userApi.updateUserGuideStatus(customerId, null, UserGuideStatus.APPLIED_ACCOUNT);
			return ObjectResult.success("创建账户成功");
		} catch (WebException e) {
			logger.info(e.getMessage(), e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("", e);
			return ObjectResult.fail("创建账户失败");
		}
	}

	@RequestMapping(value = "/addRule")
	@ResponseBody
	public ObjectResult<Void> addRule(RepaymentRuleDTO repaymentRuleDTO) {
		try {
			repaymentRuleDTO.setFactorId(SecurityContextUtils.getCustomerId());
			repaymentRuleApiImpl.addRepaymentRule(repaymentRuleDTO);
			userApi.updateUserGuideStatus(SecurityContextUtils.getCustomerId(), UserGuideStatus.APPLIED_ACCOUNT,
					UserGuideStatus.COMFIRMED_RULE);
			return ObjectResult.success("创建默认还款规则成功");
		} catch (WebException we) {
			logger.error("创建还款规则失败", we);
			return ObjectResult.fail(we.getMessage());
		} catch (Exception e) {
			logger.error("创建还款规则失败", e);
			return ObjectResult.fail("创建还款规则失败");
		}
	}
}

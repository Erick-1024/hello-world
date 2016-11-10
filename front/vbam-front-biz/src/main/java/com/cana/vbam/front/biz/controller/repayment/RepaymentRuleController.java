package com.cana.vbam.front.biz.controller.repayment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.repayment.api.IRepaymentRuleApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleSearchCriteriaDTO;
import com.cana.vbam.common.repayment.rule.dto.RepaymentRuleSearchResult;
import com.cana.vbam.common.repayment.rule.enums.ScopeOfApplication;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping(value="/repayment/rule")
public class RepaymentRuleController {

	@Resource
	private IRepaymentRuleApi repaymentRuleApiImpl;
	
	@RequestMapping(value="/list")
	public String showListPage(Model model){
		model.addAttribute("scopeOfApplications", ScopeOfApplication.values());
		return "page/repayment/rule/repaymentRuleList";
	}
	
	@RequestMapping(value="/query")
	@ResponseBody
	public ListResult<RepaymentRuleSearchResult> queryRepaymentRuleList(RepaymentRuleSearchCriteriaDTO repaymentRuleSearchCriteriaDTO){
		ListResult<RepaymentRuleSearchResult> result = new ListResult<RepaymentRuleSearchResult>();
		try {
			result = repaymentRuleApiImpl.queryRepaymentRuleList(SecurityContextUtils.getCustomerId(), repaymentRuleSearchCriteriaDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		}catch (WebException e) {
			result.setStatus(AjaxResponseStatus.FAILED);
			result.setMessage("查询失败");
		}
		return result;
	}
	
	@RequestMapping(value="/showAddRule")
	public String showAddRulePage(Model model){
		model.addAttribute("ruleId", repaymentRuleApiImpl.generateRuleId());
		model.addAttribute("account", repaymentRuleApiImpl.queryDefaultRepaymentRule(SecurityContextUtils.getCustomerId()).getFactorTransferInAccountNo());
		model.addAttribute("supervisors", repaymentRuleApiImpl.querySupervisorsByFactorId(SecurityContextUtils.getCustomerId()));
		return "page/repayment/rule/repaymentRuleAdd";
	}
	
	@RequestMapping(value="/addRule")
	@ResponseBody
	public ObjectResult<Void> addRule(RepaymentRuleDTO repaymentRuleDTO){
		ObjectResult<Void> result = new ObjectResult<>();
		try {
			repaymentRuleDTO.setFactorId(SecurityContextUtils.getCustomerId());
			repaymentRuleApiImpl.addRepaymentRule(repaymentRuleDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (WebException we){
			result.setMessage(we.getMessage());
			result.setStatus(AjaxResponseStatus.FAILED);
		} catch (Exception e) {
			result.setMessage("新增还款计划规则失败");
			result.setStatus(AjaxResponseStatus.FAILED);
		}
		return result;
	}
	
	@RequestMapping(value="/showDetail")
	public String showDetailPage(@RequestParam String ruleId, Model model){
		model.addAttribute("repaymentRule", repaymentRuleApiImpl.queryRepaymentRule(SecurityContextUtils.getCustomerId(), ruleId));
		return "page/repayment/rule/repaymentRuleDetail";
	}
	
	@RequestMapping(value="/showModify")
	public String showModifyPage(@RequestParam String ruleId, Model model){
		RepaymentRuleDTO repaymentRuleDTO = repaymentRuleApiImpl.queryRepaymentRule(SecurityContextUtils.getCustomerId(), ruleId);
		if(null != repaymentRuleDTO.getFianceCustomerIds()){
			List<CustomerDetailDTO> supervisors = repaymentRuleApiImpl.querySupervisorsByFactorId(SecurityContextUtils.getCustomerId());
			String[] ids = repaymentRuleDTO.getFianceCustomerIds().split(",");
			String[] companyNames = repaymentRuleDTO.getFianceCustomerCompanys().split(",");
			for (int i = 0; i < ids.length; i++) {
				CustomerDetailDTO supervisor = new CustomerDetailDTO();
				supervisor.setId(ids[i]);
				supervisor.setCompanyName(companyNames[i]);
				if(supervisors.contains(supervisor))
					continue;
				supervisors.add(supervisor);
			}
			model.addAttribute("supervisors", supervisors);
			model.addAttribute("account", repaymentRuleApiImpl.queryDefaultRepaymentRule(SecurityContextUtils.getCustomerId()).getFactorTransferInAccountNo());
		}
		model.addAttribute("repaymentRule", repaymentRuleDTO);
		model.addAttribute("accounts", repaymentRuleApiImpl.queryReceivedPaymentsAccounts(SecurityContextUtils.getCustomerId()));
		return "page/repayment/rule/repaymentRuleModify";
	}
	
	@RequestMapping(value="/modifyRule")
	@ResponseBody
	public ObjectResult<Void> modifyRule(RepaymentRuleDTO repaymentRuleDTO){
		ObjectResult<Void> result = new ObjectResult<>();
		try {
			repaymentRuleDTO.setFactorId(SecurityContextUtils.getCustomerId());
			repaymentRuleApiImpl.modifyRepaymentRule(repaymentRuleDTO);
			result.setStatus(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			result.setMessage("修改还款计划规则失败");
			result.setStatus(AjaxResponseStatus.FAILED);
		}
		return result;
	}
	
	@RequestMapping(value="/generateRuleId")
	@ResponseBody
	public String generateRuleId(){
		return repaymentRuleApiImpl.generateRuleId();
	}
	
	@RequestMapping(value="")
	public String showGenerateDefaultRepaymentRulePage(Model model){
		model.addAttribute("ruleId", repaymentRuleApiImpl.generateRuleId());
		model.addAttribute("accounts", repaymentRuleApiImpl.queryReceivedPaymentsAccounts(SecurityContextUtils.getCustomerId()));
		model.addAttribute("defaultRepaymentRule", repaymentRuleApiImpl.generateDefaultRepaymentRule(SecurityContextUtils.getCustomerId()));
		return "";
	}
}

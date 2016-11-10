package com.cana.vbam.front.biz.controller.asset;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetCreditApi;
import com.cana.asset.api.IAssetCustomerApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.CreditAuditDTO;
import com.cana.vbam.common.asset.dto.CreditAuditQueryCriteria;
import com.cana.vbam.common.asset.dto.CreditCheckModifyResultDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CreditQueryCriteria;
import com.cana.vbam.common.asset.dto.CreditRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;
import com.cana.vbam.common.asset.enums.CreditOperateType;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.cana.vbam.common.utils.MoneyArithUtil;

/**
 * @author hu
 *
 */
@Controller
@RequestMapping("/asset/credit")
public class AssetCreditController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetCustomerApi assetCustomerApi;
	
	@Resource
	private IAssetCreditApi assetCreditApi;

	@RequestMapping(value = "/customerList", method = RequestMethod.GET )
	public String creditCustomerListPage(Model model) {
		model.addAttribute("customerTypes", CustomerTypeEnum.values());
		return "page/asset/credit/creditList";
	}

	@RequestMapping(value = "/search/customer", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<CustomerCreditDTO> searchCreditCustomer(CustomerCreditQueryCriteria queryCriteria) {
		try {
			queryCriteria.setUserId(SecurityContextUtils.getOperatorId());
			return assetCreditApi.searchCreditCustomerByCondition(queryCriteria);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/listpage", method = RequestMethod.GET)
	public String gotoCreditListPage(Model model, @RequestParam String customerId){
		CustomerDTO customerDTO = assetCustomerApi.getCustomerDetail(SecurityContextUtils.getOperatorId(), customerId);
		model.addAttribute("customerId", customerDTO.getId());
		model.addAttribute("customerName", customerDTO.getCustomerName());
		model.addAttribute("customerType", customerDTO.getCustomerType().desc());
		
		Long[] limit = assetCreditApi.getCustomerLimit(customerId);
		model.addAttribute("totalLimit", MoneyArithUtil.convertMoneyToString(limit[0]));
		model.addAttribute("availableLimit", MoneyArithUtil.convertMoneyToString(limit[1]));
		
		model.addAttribute("creditModes", CreditMode.values());
		model.addAttribute("currencys", Currency.values());
		return "page/asset/credit/creditManage";
	}
	
	@RequestMapping(value = "/search/credit", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<CreditDTO> searchCreditByCustomerId(CreditQueryCriteria queryCriteria) {
		try {
			queryCriteria.setUserId(SecurityContextUtils.getOperatorId());
			return assetCreditApi.getCreditsByCustomerId(queryCriteria);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/get/creditById", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<CreditDTO> getCreditById(@RequestParam String creditId) {
		try {
			return ObjectResult.success("授信额度获取成功", assetCreditApi.getCreditById(creditId, SecurityContextUtils.getOperatorId()));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/get/creditDTO/{businessContractNo}/{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public ObjectResult<?> getCreditByBusinessContractNoAndCustomerId(@PathVariable("businessContractNo") String businessContractNo, @PathVariable("customerId") String customerId) {
		try {
			return ObjectResult.success("授信额度获取成功", assetCreditApi.getCreditByBusinessContractNo(businessContractNo, customerId, SecurityContextUtils.getOperatorId()));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/checkBusinessContactNo", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkBusinessContactNoExist(@RequestParam String businessContractNo, String creditId) {
		try {
			return assetCreditApi.checkBusinessContactNoExist(businessContractNo, creditId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> addCredit(CreditRequestDTO request) {
		try {
			request.setUserId(SecurityContextUtils.getOperatorId());
			assetCreditApi.applyCredit(request);
			return ObjectResult.success("申请额度成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/checkForModify", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<CreditCheckModifyResultDTO> checkForModify(@RequestParam String creditId) {
		try {
			return ObjectResult.success("检验可修改成功", assetCreditApi.checkCreditForModify(creditId));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> updateCredit(CreditRequestDTO request) {
		try {
			request.setUserId(SecurityContextUtils.getOperatorId());
			assetCreditApi.modifyCredit(request);
			return ObjectResult.success("修改额度成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/freeze", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> freezeCredit(@RequestParam String creditId) {
		try {
			assetCreditApi.freezeCredit(creditId, SecurityContextUtils.getOperatorId());
			return ObjectResult.success("冻结额度成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/unfreeze", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> unfreezeCredit(@RequestParam String creditId) {
		try {
			assetCreditApi.unfreezeCredit(creditId, SecurityContextUtils.getOperatorId());
			return ObjectResult.success("解冻额度成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/revoke", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> revokeCredit(@RequestParam String creditId) {
		try {
			assetCreditApi.revokeCredit(creditId, SecurityContextUtils.getOperatorId());
			return ObjectResult.success("撤销额度成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	public ObjectResult<String> cancelCredit(@RequestParam String creditId) {
		try {
			assetCreditApi.cancelCredit(creditId, SecurityContextUtils.getOperatorId());
			return ObjectResult.success("作废额度成功");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/audit/listpage", method = RequestMethod.GET)
	public String gotoCreditAuditListPage(Model model, @RequestParam String customerId, String creditId){
		CustomerDTO customerDTO = assetCustomerApi.getCustomerDetail(SecurityContextUtils.getOperatorId(), customerId);
		model.addAttribute("customerId", customerDTO.getId());
		model.addAttribute("customerName", customerDTO.getCustomerName());
		model.addAttribute("customerType", customerDTO.getCustomerType().desc());
		model.addAttribute("creditId", creditId);
		model.addAttribute("creditModes", CreditMode.values());
		model.addAttribute("creditOperateTypes", CreditOperateType.values());
		
		
		Long[] limit = assetCreditApi.getCustomerLimit(customerId);
		model.addAttribute("totalLimit", MoneyArithUtil.convertMoneyToString(limit[0]));
		model.addAttribute("availableLimit", MoneyArithUtil.convertMoneyToString(limit[1]));
		
		return "page/asset/credit/creditAuditList";
	}
	
	@RequestMapping(value = "/search/creditAudit", method = RequestMethod.POST)
	@ResponseBody
	public ListResult<CreditAuditDTO> searchCreditAuditByCustomerId(CreditAuditQueryCriteria queryCriteria) {
		try {
			queryCriteria.setUserId(SecurityContextUtils.getOperatorId());
			return assetCreditApi.searchCreditAuditByCondition(queryCriteria);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ListResult.fail(e.getMessage());
		}
	}
}

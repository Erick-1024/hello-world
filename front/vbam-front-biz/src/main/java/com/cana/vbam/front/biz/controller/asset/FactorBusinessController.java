package com.cana.vbam.front.biz.controller.asset;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetCreditApi;
import com.cana.asset.api.IAssetCustomerApi;
import com.cana.asset.api.IAssetFactorBusinessApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.CounterpartyListSearchDTO;
import com.cana.vbam.common.asset.dto.CreditDTO;
import com.cana.vbam.common.asset.dto.CustomerCreditQueryCriteria;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessSearchDTO;
import com.cana.vbam.common.asset.enums.BusinessMode;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.asset.enums.FactoringType;
import com.cana.vbam.common.asset.enums.ReceiptType;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;

@Controller
@RequestMapping("/asset/factorBusiness")
public class FactorBusinessController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetCreditApi assetCreditApiImpl;

	@Resource
	private IAssetFactorBusinessApi assetFactorBusinessApiImpl;
	
	@Resource
	private IAssetCustomerApi assetCustomerApi;
	
	@RequestMapping(value = "/goto/list", method = { RequestMethod.GET })
	public String gotoList() {
		logger.info("进入业务合同列表页面");
		return "page/asset/factorbusiness/list";
	}
	
	@RequestMapping(value = "/get/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getList(FactorBusinessSearchDTO factorBusinessSearchDTO) {
		try {
			factorBusinessSearchDTO.setUserId(SecurityContextUtils.getOperatorId());
			return assetFactorBusinessApiImpl.getFactorBusinessList(factorBusinessSearchDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/get/customers", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> getCustomers(CustomerCreditQueryCriteria crieria) {
		try {
			crieria.setUserId(SecurityContextUtils.getOperatorId());
			return assetCreditApiImpl.getCustomers(crieria);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
	
	@RequestMapping(value = "/goto/detail/{id}", method = { RequestMethod.GET })
	public String gotoDetail(Model model, @PathVariable("id") String id) {
		logger.info("进入业务合同详情页面");
		if(!SecurityContextUtils.authorizePermKey("BAI_FB_DETAIL"))
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		String userId = SecurityContextUtils.getOperatorId();
		FactorBusinessDTO factorBusinessDTO = assetFactorBusinessApiImpl.queryFactorBusinessInfo(id, userId);
		model.addAttribute("factorBusinessDTO", factorBusinessDTO);
		model.addAttribute("creditDTO", assetCreditApiImpl.getCreditByBusinessContractNo(factorBusinessDTO.getBusinessContractNo(), factorBusinessDTO.getCustomerId(), userId));
		return "page/asset/factorbusiness/detail";
	}
	
	@RequestMapping(value = "/goto/edit/{id}", method = { RequestMethod.GET })
	public String gotoEdit(@PathVariable("id") String id, Model model) {
		logger.info("进入保理业务信息修改页面");
		if(!SecurityContextUtils.authorizePermKey("BAI_FB_EDIT"))
			throw WebException.instance(ReturnCode.NO_PERMISSION);
		FactorBusinessDTO factorBusinessDTO = assetFactorBusinessApiImpl.queryFactorBusinessInfo(id, SecurityContextUtils.getOperatorId());
		model.addAttribute("currencys", Currency.values());
		model.addAttribute("businessProducts", BusinessProduct.values());
		model.addAttribute("businessModes", BusinessMode.values());
		model.addAttribute("interestTypes", RepaymentType.factorBusinessInterestType());
		model.addAttribute("receiptTypes", ReceiptType.values());
		model.addAttribute("factoringTypes", FactoringType.values());
		model.addAttribute("factorBusinessDTO", factorBusinessDTO);
		return "page/asset/factorbusiness/factorBusinessEdit";
	}

	@RequestMapping(value = "/goto/new/{customerId}", method = { RequestMethod.GET })
	public String gotoNew(@PathVariable("customerId") String customerId, Model model) {
		logger.info("进入保理业务信息新建页面");
		CustomerDTO customerDTO = assetCustomerApi.getCustomerDetail(SecurityContextUtils.getCustomerId(), customerId);
		model.addAttribute("customer", customerDTO);
		model.addAttribute("currencys", Currency.values());
		model.addAttribute("businessProducts", BusinessProduct.values());
		model.addAttribute("businessModes", BusinessMode.values());
		model.addAttribute("interestTypes", RepaymentType.factorBusinessInterestType());
		model.addAttribute("receiptTypes", ReceiptType.values());
		model.addAttribute("factoringTypes", FactoringType.values());
		return "page/asset/factorbusiness/factorBusinessEdit";
	}
	
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<?> delete(@PathVariable("id") String id) {
		try {
			if(!SecurityContextUtils.authorizePermKey("BAI_FB_DELETE"))
				throw WebException.instance(ReturnCode.NO_PERMISSION);
			assetFactorBusinessApiImpl.deleteFactorBusiness(id, SecurityContextUtils.getOperatorId());
			return ObjectResult.success("删除成功");
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
	@RequestMapping(value = "/queryCredit", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> queryCredit(String businessContractNo, String customerId, String id) {
		try {
			CreditDTO creditDTO = assetFactorBusinessApiImpl.getCreditDTO(businessContractNo, customerId, id);
			if(creditDTO != null){
				return ObjectResult.success("获取额度信息成功", creditDTO);
			}else{
				return ObjectResult.fail("额度信息为空");
			}
		} catch (WebException e) {
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			return ObjectResult.fail("未知异常");
		}
	}
	
	@RequestMapping(value = "/queryCreditVersion", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> queryCreditVersion(String creditId) {
		try {
			String creditVersion = assetFactorBusinessApiImpl.getCreditVersion(creditId);
			if(StringUtils.isNoneBlank(creditVersion)){
				return ObjectResult.success("获取额度信息成功", creditVersion);
			}else{
				return ObjectResult.fail("获取额度版本失败");
			}
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}

	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public String saveFactorBusinessInfo(FactorBusinessDTO factorBusinessDTO) {
		factorBusinessDTO.setFactorId(SecurityContextUtils.getCustomerId());
		factorBusinessDTO.setFactorName(SecurityContextUtils.getUserDTOFromSession().getCompanyName());
		return "redirect:goto/detail/"+assetFactorBusinessApiImpl.saveFactorBusinessInfo(factorBusinessDTO);
	}
	
	@RequestMapping(value = "/queryCounterpartyList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<?> queryCounterpartyList(@RequestBody CounterpartyListSearchDTO counterpartyListSearchDTO) {
		try {
			return assetFactorBusinessApiImpl.getCustomerList(SecurityContextUtils.getCustomerId(), counterpartyListSearchDTO);
		} catch (Exception e) {
			return FrontExceptionHandler.handleListResultException(e);
		}
	}
}

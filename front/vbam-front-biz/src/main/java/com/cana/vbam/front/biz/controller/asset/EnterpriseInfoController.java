package com.cana.vbam.front.biz.controller.asset;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cana.asset.api.IAssetCustomerApi;
import com.cana.asset.api.IAssetEnterpriseInfoApi;
import com.cana.front.common.util.ImageLegitimacyUtil;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerListRequestDTO;
import com.cana.vbam.common.asset.dto.CustomerListResponseDTO;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.customer.dto.EnterpriseInfoDTO;
import com.cana.vbam.common.customer.dto.EnterpriseInfoListDTO;
import com.cana.vbam.common.customer.enums.BasicTransaction;
import com.cana.vbam.common.customer.enums.BusinessMaterial;
import com.cana.vbam.common.customer.enums.CreditInquire;
import com.cana.vbam.common.customer.enums.CustomerMaterialSubmitState;
import com.cana.vbam.common.customer.enums.EnterpriseInfoType;
import com.cana.vbam.common.customer.enums.FactoringFlow;
import com.cana.vbam.common.customer.enums.FinanceMaterial;
import com.cana.vbam.common.customer.enums.LedgerInquire;
import com.cana.vbam.common.customer.enums.LegalInquire;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.enums.AjaxResponseStatus;
import com.esotericsoftware.minlog.Log;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.tops.mediaserver.client.MediaClientUtil;
import com.travelzen.tops.mediaserver.client.MediaClientUtil.MediaType;

@Controller
@RequestMapping("/asset/enterpriseInfo")
public class EnterpriseInfoController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetEnterpriseInfoApi enterpriseInfoApi;
	
	@Resource
	private IAssetCustomerApi assetCustomerApi;

	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String enterpriseMaterialAdd(Model model, @RequestParam String customerId) {
		CustomerDTO customerDTO = assetCustomerApi.getCustomerDetail(SecurityContextUtils.getOperatorId(), customerId);
		model.addAttribute("customerId", customerDTO.getId());
		model.addAttribute("customerName", customerDTO.getCustomerName());
		model.addAttribute("customerType", customerDTO.getCustomerType().desc());
		model.addAttribute("enterpriseInfoType", EnterpriseInfoType.values());
		model.addAttribute("businessMaterial", BusinessMaterial.values());
		model.addAttribute("financeMaterial", FinanceMaterial.values());
		model.addAttribute("legalInquire", LegalInquire.values());
		model.addAttribute("creditInquire", CreditInquire.values());
		model.addAttribute("basicTransaction", BasicTransaction.values());
		model.addAttribute("factoringFlow", FactoringFlow.values());
		model.addAttribute("ledgerInquire", LedgerInquire.values());
		return "page/asset/enterpriseInfo/enterpriseInfoEdit";
	}

	@RequestMapping(value = "/modify", method = { RequestMethod.GET })
	public String enterpriseMaterialmodify(Model model, @RequestParam String customerId) {
		CustomerDTO customerDTO = assetCustomerApi.getCustomerDetail(SecurityContextUtils.getOperatorId(), customerId);
		Map<String, Map<String, List<EnterpriseInfoDTO>>> enterpriseMaterialMap = enterpriseInfoApi.queryEnterpriseMaterialByUserId(customerId);
		model.addAttribute("customerId", customerDTO.getId());
		model.addAttribute("customerName", customerDTO.getCustomerName());
		model.addAttribute("customerType", customerDTO.getCustomerType().desc());
		model.addAttribute("enterpriseInfoType", EnterpriseInfoType.values());
		model.addAttribute("businessMaterial", BusinessMaterial.values());
		model.addAttribute("financeMaterial", FinanceMaterial.values());
		model.addAttribute("legalInquire", LegalInquire.values());
		model.addAttribute("creditInquire", CreditInquire.values());
		model.addAttribute("basicTransaction", BasicTransaction.values());
		model.addAttribute("factoringFlow", FactoringFlow.values());
		model.addAttribute("ledgerInquire", LedgerInquire.values());
		model.addAttribute("materialMap", enterpriseMaterialMap);
		return "page/asset/enterpriseInfo/enterpriseInfoEdit";
	}
	
	@RequestMapping(value="/saveFile",method=RequestMethod.POST)
	@ResponseBody
	public void uploadImage(MultipartFile image, HttpServletResponse httpServletResponse) throws Exception{
		StringBuilder sb = new StringBuilder();
		try {
			image = ImageLegitimacyUtil.verifyImageForEnterpriseInfo(image);
			String originalFilename = image.getOriginalFilename();
			String imageId = MediaClientUtil.upload(image.getBytes(), MediaType.IMAGE, originalFilename);
			sb.append(AjaxResponseStatus.SUCCESS.name() + ":");
			sb.append(originalFilename + ":");
			sb.append(imageId);
		} catch(WebException we){
			sb.append(AjaxResponseStatus.FAILED.name() + ":");
			sb.append(we.getMessage());
			logger.error(we.getMessage(), we);
		} catch (Exception e) {
			sb.append(AjaxResponseStatus.FAILED.name() + ":");
			sb.append("图片上传失败");
			logger.error("图片上传失败", e);
		}finally{
			httpServletResponse.setContentType("text/html");
			httpServletResponse.getWriter().write(sb.toString());
		}
	}
	
	@RequestMapping(value="/saveTempInfo")
	public String saveTempInfo(Model model, EnterpriseInfoListDTO enterpriseInfoDTOList) throws Exception{
		enterpriseInfoApi.saveEnterpriseInfoTemp(enterpriseInfoDTOList);
		return "redirect:queryEnterpisrInfo?customerId=" + enterpriseInfoDTOList.getCustomerId();
	}

	@RequestMapping(value="/submitInfo")
	public String submitInfo(Model model, EnterpriseInfoListDTO enterpriseInfoDTOList) throws Exception{
		enterpriseInfoApi.saveEnterpriseInfo(enterpriseInfoDTOList);
		return "redirect:queryEnterpisrInfo?customerId=" + enterpriseInfoDTOList.getCustomerId();
	}

	@RequestMapping(value="/queryEnterpisrInfo")
	public String queryEnterpisrInfo(Model model, @RequestParam String customerId) throws IOException{
		CustomerDTO customerDTO = assetCustomerApi.getCustomerDetail(SecurityContextUtils.getOperatorId(), customerId);
		model.addAttribute("customerId", customerDTO.getId());
		model.addAttribute("customerName", customerDTO.getCustomerName());
		model.addAttribute("customerType", customerDTO.getCustomerType().desc());
		Map<String, Map<String, List<EnterpriseInfoDTO>>> enterpriseMaterialMap = enterpriseInfoApi.queryEnterpriseMaterialByUserId(customerId);
		model.addAttribute("enterpriseInfoType", EnterpriseInfoType.values());
		model.addAttribute("businessMaterial", BusinessMaterial.values());
		model.addAttribute("financeMaterial", FinanceMaterial.values());
		model.addAttribute("legalInquire", LegalInquire.values());
		model.addAttribute("creditInquire", CreditInquire.values());
		model.addAttribute("basicTransaction", BasicTransaction.values());
		model.addAttribute("factoringFlow", FactoringFlow.values());
		model.addAttribute("ledgerInquire", LedgerInquire.values());
		model.addAttribute("materialMap", enterpriseMaterialMap);
		return "page/asset/enterpriseInfo/enterpriseInfoDetail";
	}
	
	@RequestMapping(value="/listPage")
	public String gotoEnterpriseInfoListPage(Model model){
		model.addAttribute("customerTypes", CustomerTypeEnum.values());
		model.addAttribute("submitState", CustomerMaterialSubmitState.getSearchCondition());
		return "/page/asset/enterpriseInfo/enterpriseInfoList";
	}
	
	@RequestMapping(value="/customerListPage")
	public String gotoCustomerListPage(Model model){
		model.addAttribute("customerTypes", CustomerTypeEnum.values());
		return "/page/asset/enterpriseInfo/customerList";
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ListResult<CustomerListResponseDTO> searchEnterproseInfoList(@RequestBody CustomerListRequestDTO requestDTO){
		
		try {
			requestDTO.setUserId(SecurityContextUtils.getOperatorId());
			return assetCustomerApi.getCustomerList(requestDTO);
		} catch (Exception e) {
			Log.error(e.getMessage(),e);
			return ListResult.fail(e.getMessage());
		}
	}
	
}

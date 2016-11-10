package com.cana.vbam.front.biz.controller.asset;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IAssetCustomerApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.asset.dto.CustomerDTO;
import com.cana.vbam.common.asset.dto.CustomerRequestDTO;
import com.cana.vbam.common.asset.enums.CustomerCityTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerManEducationEnum;
import com.cana.vbam.common.asset.enums.CustomerSettleTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerStkPayamtTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerStkTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.enums.EconomicCategoryEnum;
import com.cana.vbam.common.asset.enums.IndustryTypeEnum;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.utils.FrontExceptionHandler;
import com.travelzen.framework.core.exception.WebException;

/**
 * 客户信息controller
 * @author jiangzhou.Ren
 * @time 2016年7月28日下午1:33:02
 */


@Controller
@RequestMapping("/asset/customer")
public class AssetCustomerController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IAssetCustomerApi assetCustomerApi;
	
	//客户列表
	@RequestMapping(value="/customerListPage")
	public String gotoCustomerListPage(Model model){
		model.addAttribute("customerTypes", CustomerTypeEnum.values());
		return "/page/asset/enterpriseInfo/customerList";
	}

	// 客户详情页面
	@RequestMapping(value ="/customerDetail", method = { RequestMethod.GET })
	public String detail(@RequestParam String customerId,Model model) {

		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		CustomerDTO customerDetailDTO = assetCustomerApi.getCustomerDetail(userId, customerId);
		model.addAttribute("customerDetailDTO", customerDetailDTO);
		logger.info("进入客户查询详情页面");
		return "page/asset/customer/customerDetail";
	}

	// 客户返回新增客户页面
	@RequestMapping(value ="/customerCreate")
	public String projectCreate(Model model) throws Exception {
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		if (userType != UserType.FACTOR) {
			throw WebException.instance("只有保理商才有权限新建");
		} 
		// 返回需要的下拉列表枚举值
		model.addAttribute("customerTypeEnums",CustomerTypeEnum.values());
		model.addAttribute("economicCategoryEnums",EconomicCategoryEnum.values());
		model.addAttribute("industryTypeEnums",IndustryTypeEnum.values());
		model.addAttribute("customerStkTypeEnums",CustomerStkTypeEnum.values());
		model.addAttribute("customerStkPayamtTypeEnums",CustomerStkPayamtTypeEnum.values());
		model.addAttribute("customerSettleTypeEnums",CustomerSettleTypeEnum.values());
		model.addAttribute("customerManEducationEnums",CustomerManEducationEnum.values());
		model.addAttribute("customerCityTypeEnums",CustomerCityTypeEnum.values());
		logger.info("进入客户信息新增页面");
		return "page/asset/customer/customerCreate";
	}
	
	//客户新增form数据提交
	@RequestMapping(value = "customerCreateFormDate", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<String> customerCreateFormDate(@RequestBody CustomerRequestDTO customerRequest)
			throws IOException {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			customerRequest.setEnterpriseMaterialState("UNSUBMIT");
			assetCustomerApi.addCustomer(userId, customerRequest);
			return ObjectResult.success("客户信息新增成功");
		} catch (Exception e) {
			logger.error("客户信息新增错误", e.getMessage());
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
	}

	// 客户修改数据回填页面
	@RequestMapping(value = "/customerUpdate", method = { RequestMethod.GET })
	public String projectUpdate(@RequestParam String customerId, Model model) {
		String userId = SecurityContextUtils.getUserDTOFromSession().getId();
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		//只有资金方可以跳转到新建页面
		if (userType != UserType.FACTOR) {
			throw WebException.instance("只有保理商才有权限修改");
		} 	model.addAttribute("customerTypeEnums", CustomerTypeEnum.values());
		model.addAttribute("economicCategoryEnums", EconomicCategoryEnum.values());
		model.addAttribute("industryTypeEnums", IndustryTypeEnum.values());
		model.addAttribute("customerStkTypeEnums", CustomerStkTypeEnum.values());
		model.addAttribute("customerStkPayamtTypeEnums", CustomerStkPayamtTypeEnum.values());
		model.addAttribute("customerSettleTypeEnums", CustomerSettleTypeEnum.values());
		model.addAttribute("customerManEducationEnums", CustomerManEducationEnum.values());
		model.addAttribute("customerCityTypeEnums",CustomerCityTypeEnum.values());
		CustomerDTO customerDetailDTO = assetCustomerApi.getCustomerDetail(userId, customerId);
		model.addAttribute("customerDetailDTO", customerDetailDTO);
		logger.info("进入客户修改页面");
		return "page/asset/customer/customerUpdate";
	}
		
		
	// 客户修改form数据提交
	@RequestMapping(value = "customerUpdateFormDate", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<String> customerUpdateFormDate(@RequestBody CustomerRequestDTO customerRequest)
			throws IOException {
		try {
			String userId = SecurityContextUtils.getUserDTOFromSession().getId();
			assetCustomerApi.updateCustomer(userId, customerRequest);
			return ObjectResult.success("客户信息修改成功");
		} catch (Exception e) {
			logger.error("客户信息修改错误", e.getMessage());
			e.printStackTrace();
			return ObjectResult.fail(e.getMessage());
		}
	}
	
	//校验客户名称是否唯一
	@RequestMapping(value="/verifyCustomerName")
	@ResponseBody
	public boolean verifyCustomerName(String value,String id) {
		boolean customernameExist = false;
		try {
			customernameExist = assetCustomerApi.checkCustomernameExist(value,id);
		} catch (Exception e) { 
			logger.error("检查客户名称唯一性失败，用客户名称为："+value, e);
		}
		return customernameExist;
	}
	
	@RequestMapping(value = "/getCustomerName/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public ObjectResult<?> delete(@PathVariable("id") String id) {
		try {
			return ObjectResult.success("获取客户名称成功", assetCustomerApi.getCustomerNameById(SecurityContextUtils.getOperatorId(), id));
		} catch (Exception e) {
			return FrontExceptionHandler.handleObjectResultException(e);
		}
	}
	
}

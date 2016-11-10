package com.cana.vbam.front.biz.controller.credit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import com.cana.credit.api.ICreditApi;
import com.cana.flight.finance.api.IFlightFinanceApi;
import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.vbam.common.credit.dto.apply.AccessRuleDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyDetailDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyListQueryDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerApplyMinDTO;
import com.cana.vbam.common.credit.dto.apply.CustomerSaleDataDTO;
import com.cana.vbam.common.credit.dto.apply.TravelzenCustomerAuditResultDTO;
import com.cana.vbam.common.credit.enums.AccessAutomaticState;
import com.cana.vbam.common.credit.enums.AccessManualState;
import com.cana.vbam.common.credit.enums.AccessRuleFitObject;
import com.cana.vbam.common.credit.enums.ApplyApplicantType;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping("/credit/audit")
public class CreditLimitAuditController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ICreditApi creditApi;
	
	@Resource
	private IFlightFinanceApi flightFinanceApiImpl;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String gotoList(Model model) {
		model.addAttribute("applicantTypes", Arrays.asList(ApplyApplicantType.values()));
		model.addAttribute("auditStateList", Arrays.asList(AccessManualState.values()));
		model.addAttribute("automaticStateList",Arrays.asList(AccessAutomaticState.NOTACCESS));
		logger.info("进入真旅额度审核列表页面");
		return "page/credit/audit/list";
	}
	
	@RequestMapping(value = "/searchList", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<CustomerApplyMinDTO> searchList(CustomerApplyListQueryDTO customerApplyListQueryDTO) {
		try {
			PageList<CustomerApplyMinDTO> pageLists = creditApi.getCustomerApplyList(customerApplyListQueryDTO);
			return ListResult.success(pageLists.getRecords(), pageLists.getTotalRecords());
		} catch (Exception e) {
			logger.error("获取客户额度审核列表错误", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/audit", method = { RequestMethod.GET })
	public String gotoAuditPage(@RequestParam String id, Model model) {
		CustomerApplyDetailDTO customerApplyDetailDTO = creditApi.getCustomerApplyInfo(id);
		AccessRuleDTO accessRuleDTO = null;
//		if(customerApplyDetailDTO.getInWhitelist()!=null && !customerApplyDetailDTO.getInWhitelist())
//			accessRuleDTO = creditApi.queryAccessRule(AccessRuleFitObject.NON_WHITE_CUSTOMER).get(0);
//		else
			accessRuleDTO = creditApi.queryAccessRule(AccessRuleFitObject.WHITE_CUSTOMER).get(0);
		model.addAttribute("customerApplyDetailDTO", customerApplyDetailDTO);
		model.addAttribute("courtExecuteCompanyAmount", accessRuleDTO.getCourtExecuteCompanyAmount());
		model.addAttribute("courtExecuteCompanyTimes", accessRuleDTO.getCourtExecuteCompanyTimes());
		model.addAttribute("courtExecuteIndividualAmount", accessRuleDTO.getCourtExecuteIndividualAmount());
		model.addAttribute("courtExecuteIndividualTimes", accessRuleDTO.getCourtExecuteIndividualTimes());
		logger.info("进入真旅额度【审核】页面");
		return "page/credit/audit/audit";
	}
	
	@RequestMapping(value = "/approve", method = { RequestMethod.GET })
	public String gotoApprovePage(@RequestParam String id, Model model) {
		CustomerApplyDetailDTO customerApplyDetailDTO = creditApi.getCustomerApplyInfo(id);
		AccessRuleDTO accessRuleDTO = null;
//		if(customerApplyDetailDTO.getInWhitelist()!=null && !customerApplyDetailDTO.getInWhitelist())
//			accessRuleDTO = creditApi.queryAccessRule(AccessRuleFitObject.NON_WHITE_CUSTOMER).get(0);
//		else
			accessRuleDTO = creditApi.queryAccessRule(AccessRuleFitObject.WHITE_CUSTOMER).get(0);
		model.addAttribute("customerApplyDetailDTO", customerApplyDetailDTO);
		model.addAttribute("courtExecuteCompanyAmount", accessRuleDTO.getCourtExecuteCompanyAmount());
		model.addAttribute("courtExecuteCompanyTimes", accessRuleDTO.getCourtExecuteCompanyTimes());
		model.addAttribute("courtExecuteIndividualAmount", accessRuleDTO.getCourtExecuteIndividualAmount());
		model.addAttribute("courtExecuteIndividualTimes", accessRuleDTO.getCourtExecuteIndividualTimes());
		logger.info("进入真旅额度【审批】页面");
		return "page/credit/audit/approve";
	}
	
	@RequestMapping(value = "/detail", method = { RequestMethod.GET })
	public String detail(@RequestParam String id, Model model) {
		CustomerApplyDetailDTO customerApplyDetailDTO = creditApi.getCustomerApplyInfo(id);
		model.addAttribute("customerApplyDetailDTO", customerApplyDetailDTO);
		logger.info("进入真旅额度审核【详情】页面");
		return "page/credit/audit/detail";
	}
	
	@RequestMapping(value = "/audit", method = { RequestMethod.POST })
	@ResponseBody
	public ObjectResult<?> audit(@RequestBody TravelzenCustomerAuditResultDTO resultDTO) {
		try {
			resultDTO.setAuditorId(SecurityContextUtils.getUserDTOFromSession().getId());
			creditApi.auditTravelzenCustomer(resultDTO);
		} catch (WebException e) {
			logger.info("提交额度人工审核结果错误：", e);
			return ObjectResult.fail(e.getMessage());
		} 
		catch (Exception e) {
			logger.info("提交额度人工审核结果错误：", e);
			return ObjectResult.fail("提交审核异常，请联系管理员");
		}
		return ObjectResult.success();
	}
	
	@RequestMapping(value = "/accessRule")
	public String gotoAccessRule(Model model){
		List<AccessRuleDTO> accessRuleDTOs = creditApi.queryAccessRule(AccessRuleFitObject.WHITE_CUSTOMER);
		model.addAttribute("accessRule", accessRuleDTOs.get(0));
		return "page/credit/audit/accessRule";
	}
	
	@RequestMapping(value="/exportSaleData")
	public void exportSaleData(String tzCustomerId,String applyDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");                
		List<CustomerSaleDataDTO> saleDatas = flightFinanceApiImpl.getMonthAverageSales(tzCustomerId, sdf.parse(applyDate),18);
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String excelTitle = "销售数据";
		String fileName = excelTitle+".xls";
		if (userAgent.contains("MSIE")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
		}else{
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
		response.setCharacterEncoding("utf-8");
		
		try {
			OutputStream out = response.getOutputStream();
			String[] excelHeaders = {"序号","年份", "月份", "销售额（元）"};
			ExcelUtils.exportExcel(excelTitle, excelHeaders, saleDatas, out, true);
		} catch (IOException e) {
			logger.error("导出销售数据失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
	
}

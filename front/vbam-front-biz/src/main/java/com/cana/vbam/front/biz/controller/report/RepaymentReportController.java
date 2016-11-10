package com.cana.vbam.front.biz.controller.report;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.report.consts.ReportConsts;
import com.cana.vbam.common.report.dto.ReportFactorFinanceCountDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceDayDTO;
import com.cana.vbam.common.report.dto.ReportFactorFinanceSearchCriteria;
import com.cana.vbam.common.report.dto.ReportFactorFinanceYearDTO;
import com.cana.vbam.common.report.enums.BusinessProduct;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

@Controller
@RequestMapping(value = "/report/repayment")
public class RepaymentReportController {

	private static final Logger LGR = LoggerFactory.getLogger(RepaymentReportController.class);
	
	@Resource
	private IReportApi reportApi;
	
	@RequestMapping(value="/repaymentDailyReportPage")
	public String gotoRepaymentDailyReportPage(Model model) {
		Map<String,String> businessProductMap = new HashMap<>();
		for(BusinessProduct businessProduct:BusinessProduct.values()){
			businessProductMap.put(businessProduct.name(), businessProduct.desc());
		}
		model.addAttribute("businessProductMap", businessProductMap);
		return "page/report/repayment/dailyReport";
	}
	
	@RequestMapping(value="/queryRepaymentDailyReport")
	@ResponseBody
	public ListResult<ReportFactorFinanceDayDTO> queryRepaymentDailyReport(ReportFactorFinanceSearchCriteria searchCriteria) {
		searchCriteria.setMasterId(SecurityContextUtils.getCustomerId());
		return reportApi.queryRepaymentDailyReport(searchCriteria);
	}
	
	@RequestMapping(value="/repaymentAnnualReportPage")
	public String gotoRepaymentAnnualReportPage(Model model) {
		Map<String,String> businessProductMap = new HashMap<>();
		for(BusinessProduct businessProduct:BusinessProduct.values()){
			businessProductMap.put(businessProduct.name(), businessProduct.desc());
		}
		model.addAttribute("businessProductMap", businessProductMap);
		return "page/report/repayment/annualReport";
	}
	
	@RequestMapping(value="/queryRepaymentAnnualReport")
	@ResponseBody
	public ListResult<ReportFactorFinanceYearDTO> queryRepaymentAnnualReport(ReportFactorFinanceSearchCriteria searchCriteria) {
		searchCriteria.setMasterId(SecurityContextUtils.getCustomerId());
		return reportApi.queryRepaymentAnnualReport(searchCriteria);
	}
	
	@RequestMapping(value="/repaymentCountReportPage")
	public String gotoRepaymentCountReportPage(Model model) {
		Map<String,String> businessProductMap = new HashMap<>();
		for(BusinessProduct businessProduct:BusinessProduct.values()){
			businessProductMap.put(businessProduct.name(), businessProduct.desc());
		}
		model.addAttribute("businessProductMap", businessProductMap);
		return "page/report/repayment/countReport";
	}
	
	@RequestMapping(value="/queryRepaymentCountReport")
	@ResponseBody
	public ListResult<ReportFactorFinanceCountDTO> queryRepaymentCountReport(ReportFactorFinanceSearchCriteria searchCriteria) {
		searchCriteria.setMasterId(SecurityContextUtils.getCustomerId());
		return reportApi.queryRepaymentCountReport(searchCriteria);
	}
	
	@RequestMapping(value="/exportRepaymentDailyReport")
	public void exportRepaymentDailyReport(ReportFactorFinanceSearchCriteria searchCriteria, HttpServletRequest request, HttpServletResponse response) throws Exception {
		searchCriteria.setMasterId(SecurityContextUtils.getCustomerId());
		List<ReportFactorFinanceDayDTO> dataset = reportApi.queryRepaymentDailyReportNoPaging(searchCriteria);
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String fileName = ReportConsts.repaymentDailyReportFileName;
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
			ExcelUtils.exportExcel(ReportConsts.repaymentDailyReportSheetTitle, ReportConsts.repaymentDailyReportHeaders, dataset, out, true);
		} catch (IOException e) {
			LGR.error("生成文件失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
	
	@RequestMapping(value="/exportRepaymentAnnualReport")
	public void exportRepaymentAnnualReport(ReportFactorFinanceSearchCriteria searchCriteria, HttpServletRequest request, HttpServletResponse response) throws Exception {
		searchCriteria.setMasterId(SecurityContextUtils.getCustomerId());
		List<ReportFactorFinanceYearDTO> dataset = reportApi.queryRepaymentAnnualReportNoPaging(searchCriteria);
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String fileName = ReportConsts.repaymentAnnualReportFileName;
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
			ExcelUtils.exportExcel(ReportConsts.repaymentAnnualReportSheetTitle, ReportConsts.repaymentAnnualReportHeaders, dataset, out, true);
		} catch (IOException e) {
			LGR.error("生成文件失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
	
	@RequestMapping(value="/exportRepaymentCountReport")
	public void exportRepaymentCountReport(ReportFactorFinanceSearchCriteria searchCriteria, HttpServletRequest request, HttpServletResponse response) throws Exception{
		searchCriteria.setMasterId(SecurityContextUtils.getCustomerId());
		List<ReportFactorFinanceCountDTO> dataset = reportApi.queryRepaymentCountReportNoPaging(searchCriteria);
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String fileName = ReportConsts.repaymentCountReportFileName;
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
			ExcelUtils.exportExcel(ReportConsts.repaymentCountReportSheetTitle, ReportConsts.repaymentCountReportHeaders, dataset, out, true);
		} catch (IOException e) {
			LGR.error("生成文件失败", e);
			throw WebException.instance("您请求的文件不存在");
		}
	}
}

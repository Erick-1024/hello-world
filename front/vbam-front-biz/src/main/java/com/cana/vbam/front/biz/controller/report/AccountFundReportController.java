/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.front.biz.controller.report;

import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cana.member.authorization.common.SecurityContextUtils;
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.dto.ObjectResult;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.report.consts.ReportConsts;
import com.cana.vbam.common.report.dto.AccountFundDailyReportQueryDTO;
import com.cana.vbam.common.report.dto.AccountFundYearReportQueryDTO;
import com.cana.vbam.common.report.dto.CanaReportAccountFundCountDTO;
import com.cana.vbam.common.report.dto.CanaReportAccountFundDTO;
import com.cana.vbam.common.report.dto.FactorReportAccountFundDTO;
import com.cana.vbam.common.report.dto.FinanceReportAccountFundDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundCountDTO;
import com.cana.vbam.common.report.dto.ReportAccountFundDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlyResultDTO;
import com.cana.vbam.common.report.dto.ReportFundMonthlySearchCriteria;
import com.cana.vbam.common.report.enums.FundBalanceGetState;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.ExcelUtils;

/**
 * @author ducer
 *
 */
@Controller
@RequestMapping(value = "/report/account")
public class AccountFundReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private IReportApi reportApi;

	@RequestMapping(value = "/fundDailyReport", method = RequestMethod.GET)
	public ModelAndView getDailyAccountFundReport() {
		ModelAndView mv = new ModelAndView("page/report/account/fundDailyReport");
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		mv.addObject("userType", userType);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/fundDailyReport", method = RequestMethod.POST)
	public ListResult<Serializable> queryDailyAccountFundReport(AccountFundDailyReportQueryDTO query) {
		try {
			query.setCustomerId(SecurityContextUtils.getCustomerId());
			ListResult<ReportAccountFundDTO> result = reportApi.queryAccountFundDailyReport(query);
			List<Serializable> targets = filterField(result.getData());
			return ListResult.success(targets, result.getTotalNum());
		} catch (WebException e) {
			logger.error("资金日报查询异常", e);
			return ListResult.fail("资金日报查询异常");
		} catch (Exception e) {
			logger.error("资金日报查询异常", e);
			return ListResult.fail("资金日报查询异常");
		}
	}

	@RequestMapping(value = "/fundYearReport", method = RequestMethod.GET)
	public ModelAndView getAccountFundYearReport() {
		ModelAndView mv = new ModelAndView("page/report/account/fundYearReport");
		List<Integer> years = Lists.newArrayList();
		int currentYear = DateTime.now().getYear();
		for (int year = currentYear; year >= 2015; year--) {
			years.add(year);
		}
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		mv.addObject("years", years);
		mv.addObject("userType", userType);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/fundYearReport", method = RequestMethod.POST)
	public ListResult<Serializable> queryAccountFundYearReport(AccountFundYearReportQueryDTO query) {
		try {
			query.setCustomerId(SecurityContextUtils.getCustomerId());
			ListResult<ReportAccountFundDTO> result = reportApi.queryAccountFundYearReport(query);
			List<Serializable> targets = filterField(result.getData());
			return ListResult.success(targets, result.getTotalNum());
		} catch (WebException e) {
			logger.error("资金年报查询异常", e);
			return ListResult.fail("资金年报查询异常");
		} catch (Exception e) {
			logger.error("资金年报查询异常", e);
			return ListResult.fail("资金年报查询异常");
		}
	}

	@RequestMapping(value = "/fundDailyCountReport", method = RequestMethod.GET)
	public ModelAndView getDailyAccountFundCountReport() {
		ModelAndView mv = new ModelAndView("page/report/account/fundDailyCountReport");
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		mv.addObject("userType", userType);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/fundDailyCountReport", method = RequestMethod.POST)
	public ListResult<CanaReportAccountFundCountDTO> queryAccountFundDailyCountReport(
			AccountFundDailyReportQueryDTO query) {
		try {
			query.setCustomerId(SecurityContextUtils.getCustomerId());
			ListResult<ReportAccountFundCountDTO> result = reportApi.queryAccountFundDailyCountReport(query);
			List<CanaReportAccountFundCountDTO> targets = filterCountField(result.getData());
			return ListResult.success(targets, result.getTotalNum());
		} catch (WebException e) {
			logger.error("资金统计日报查询异常", e);
			return ListResult.fail("资金统计日报查询异常");
		} catch (Exception e) {
			logger.error("资金统计日报查询异常", e);
			return ListResult.fail("资金统计日报查询异常");
		}
	}

	@RequestMapping(value = "/fundMonthlyReportPage", method = RequestMethod.GET)
	public String fundMonthlyReportPage(Model model) {
		model.addAttribute("lastMonthDate", reportApi.getLastMonthDate());
		model.addAttribute("accountTypeList", AccountType.values());
		model.addAttribute("accountSupervisionStatusList", AccountSupervisionStatus.values());
		model.addAttribute("accountStatusList", AccountStatus.values());
		model.addAttribute("fundBalanceGetStateList", FundBalanceGetState.values());
		model.addAttribute("mainAccountBalance", reportApi.getMainAccountBalance());
		return "page/report/account/fundMonthlyReport";
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryAccountFundMonthlyReport", method = RequestMethod.POST)
	public ListResult<ReportFundMonthlyResultDTO> queryAccountFundMonthlyReport(ReportFundMonthlySearchCriteria searchCriteria) {
		try {
			ListResult<ReportFundMonthlyResultDTO> result = reportApi.queryFundMonthlyReportData(searchCriteria);
			return result;
		} catch (WebException e) {
			logger.error("资金月报表查询异常", e);
			return ListResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("资金月报表查询异常", e);
			return ListResult.fail("资金月报表查询异常");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getMonthlyBalanceSum", method = RequestMethod.POST)
	public ObjectResult<String> getMonthlyBalanceSum(ReportFundMonthlySearchCriteria searchCriteria) {
		try {
			ObjectResult<String> result = reportApi.getMonthlyBalanceSum(searchCriteria);
			return result;
		} catch (WebException e) {
			logger.error("资金月报表查询异常", e);
			return ObjectResult.fail(e.getMessage());
		} catch (Exception e) {
			logger.error("资金月报表查询异常", e);
			return ObjectResult.fail("资金月报表查询异常");
		}
	}

//	@ResponseBody
//	@RequestMapping(value = "/getMainAccountBalance", method = RequestMethod.POST)
//	public ObjectResult<String> getMainAccountBalance(String reportDate) {
//		try {
//			ObjectResult<String> result = reportApi.getMainAccountBalance(reportDate);
//			return result;
//		} catch (WebException e) {
//			logger.error("资金月报表查询异常", e);
//			return ObjectResult.fail(e.getMessage());
//		} catch (Exception e) {
//			logger.error("资金月报表查询异常", e);
//			return ObjectResult.fail("资金月报表查询异常");
//		}
//	}
	
	@RequestMapping(value = "/exportFundMonthlyReport")
	public void exportFundMonthlyReport(ReportFundMonthlySearchCriteria searchCriteria, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<ReportFundMonthlyResultDTO> data = reportApi.queryFundMonthlyReportDataWithoutPage(searchCriteria);
			ReportFundMonthlyResultDTO result = new ReportFundMonthlyResultDTO();
			result.setBalance(reportApi.getMonthlyBalanceSum(searchCriteria).getData());
			data.add(result);
			String userAgent = request.getHeader("User-Agent").toUpperCase();
			String fileName = ReportConsts.fund_monthly_report;
			if (userAgent.contains("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			}else{
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
			response.setCharacterEncoding("utf-8");

			OutputStream out = response.getOutputStream();
			
			ExcelUtils.exportExcel(
					ReportConsts.fund_monthly_report_title,
					ReportConsts.fund_monthly_report_header, data, out, true);
		} catch (WebException e) {
			logger.error("报表导出-资金月报表导出异常", e);
			throw e;
		} catch (Exception e) {
			logger.error("报表导出-资金月报表导出异常", e);
			Throwables.propagate(e);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/exportFundDailyReport")
	public void exportDailyAcountFundReport(AccountFundDailyReportQueryDTO query, HttpServletRequest request, HttpServletResponse response) {
		try {
			query.setCustomerId(SecurityContextUtils.getCustomerId());
			List<ReportAccountFundDTO> data = reportApi.exportAcountFundDailyReport(query);
			
			String userAgent = request.getHeader("User-Agent").toUpperCase();
			String fileName = ReportConsts.fund_daily_report;
			if (userAgent.contains("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			}else{
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
			response.setCharacterEncoding("utf-8");
			OutputStream out = response.getOutputStream();
			UserType userType=  SecurityContextUtils.getUserDTOFromSession().getUserType();
			if(UserType.FINACE.equals(userType)){
				ExcelUtils.exportExcel(
						ReportConsts.fund_daily_report_title, 
						ReportConsts.finance_fund_daily_report_header, filterField(data), out, true);
			}else if(UserType.FACTOR.equals(userType)){
				ExcelUtils.exportExcel(
						ReportConsts.fund_daily_report_title, 
						ReportConsts.factor_fund_daily_report_header, filterField(data), out, true);
			}else if(UserType.CANA.equals(userType)){
				ExcelUtils.exportExcel(
						ReportConsts.fund_daily_report_title, 
						ReportConsts.cana_fund_daily_report_header, filterField(data), out, true);
			}
		} catch (WebException e) {
			logger.error("报表导出-资金日报表导出异常", e);
			throw e;
		} catch (Exception e) {
			logger.error("报表导出-资金日报表导出异常", e);
			Throwables.propagate(e);
		}
	}

	@RequestMapping(value = "/exportFundYearReport")
	public void exportAcountFundYearReport(AccountFundYearReportQueryDTO query, HttpServletRequest request, HttpServletResponse response) {
		try {
			query.setCustomerId(SecurityContextUtils.getCustomerId());
			List<ReportAccountFundDTO> data = reportApi.exportAcountFundYearReport(query);
			String userAgent = request.getHeader("User-Agent").toUpperCase();
			String fileName = ReportConsts.fund_year_report;
			if (userAgent.contains("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			}else{
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
			response.setCharacterEncoding("utf-8");

			OutputStream out = response.getOutputStream();
			UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
			if (UserType.FINACE.equals(userType)) {
				ExcelUtils.exportExcel(
						ReportConsts.fund_year_report_title,
						ReportConsts.finance_fund_year_report_header, filterField(data), out, true);
			} else if (UserType.FACTOR.equals(userType)) {
				ExcelUtils.exportExcel(
						ReportConsts.fund_year_report_title,
						ReportConsts.factor_fund_year_report_header, filterField(data), out, true);
			} else if (UserType.CANA.equals(userType)) {
				ExcelUtils.exportExcel(
						ReportConsts.fund_year_report_title,
						ReportConsts.cana_fund_year_report_header, filterField(data), out, true);
			}
		} catch (WebException e) {
			logger.error("报表导出-资金汇总报表导出异常", e);
			throw e;
		} catch (Exception e) {
			logger.error("报表导出-资金汇总报表导出异常", e);
			Throwables.propagate(e);
		}
	}

	@RequestMapping(value = "/exportFundDailyCountReport")
	public void exportAcountFundDailyCountReport(AccountFundDailyReportQueryDTO query, HttpServletRequest request, HttpServletResponse response) {
		try {
			query.setCustomerId(SecurityContextUtils.getCustomerId());
			List<ReportAccountFundCountDTO> data = reportApi.exportAcountFundDailyCountReport(query);
			String userAgent = request.getHeader("User-Agent").toUpperCase();
			String fileName = ReportConsts.fund_count_report;
			if (userAgent.contains("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			}else{
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.addHeader("Content-Disposition", String.format(" attachment; filename=\"%s\"", fileName));
			response.setCharacterEncoding("utf-8");

			OutputStream out = response.getOutputStream();
			ExcelUtils.exportExcel(
					ReportConsts.fund_count_report_title, 
					ReportConsts.fund_count_report_header, 
					filterCountField(data), 
					out, true);
		} catch (WebException e) {
			logger.error("报表导出-资金计数报表导出异常", e);
			throw e;
		} catch (Exception e) {
			logger.error("报表导出-资金计数报表导出异常", e);
			Throwables.propagate(e);
		}
	}
	
	/**
	 * 引用请用Serializable
	 */
	@SuppressWarnings("unchecked")
	private <T extends Serializable> List<T> filterField(List<ReportAccountFundDTO> origins) {
		UserType userType = SecurityContextUtils.getUserDTOFromSession().getUserType();
		List<T> targets = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(origins)) {
			for (ReportAccountFundDTO origin : origins) {
				T target = null;
				if (UserType.FINACE.equals(userType)) {
					target = (T) new FinanceReportAccountFundDTO();
				} else if (UserType.FACTOR.equals(userType)) {
					target = (T) new FactorReportAccountFundDTO();
				} else if (UserType.CANA.equals(userType)) {
					target = (T) new CanaReportAccountFundDTO();
				}
				BeanUtils.copyProperties(origin, target);
				targets.add(target);
			}
		}
		return targets;
	}
	
	private List<CanaReportAccountFundCountDTO> filterCountField(List<ReportAccountFundCountDTO> origins) {
		List<CanaReportAccountFundCountDTO> targets = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(origins)) {
			for (ReportAccountFundCountDTO origin : origins) {
				CanaReportAccountFundCountDTO target = new CanaReportAccountFundCountDTO();
				BeanUtils.copyProperties(origin, target);
				targets.add(target);
			}
		}
		return targets;
	}
}

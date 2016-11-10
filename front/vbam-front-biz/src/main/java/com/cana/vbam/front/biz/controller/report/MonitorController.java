package com.cana.vbam.front.biz.controller.report;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.credit.api.ICreditApi;
import com.cana.flight.finance.api.IFlightFinanceApi;
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.report.dto.MonitorDataDataDTO;
import com.cana.vbam.common.report.dto.MonitorMetricAndDataQueryDTO;
import com.cana.vbam.common.report.dto.MonitorMetricDataDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryDTO;
import com.cana.vbam.common.report.dto.MonitorSummaryQueryDTO;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.util.MoneyUtil;

/**
 * @author yihong.tang
 *
 */
@Controller
@RequestMapping("/report/monitor")
public class MonitorController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private IReportApi reportApiImpl;
	
	@Resource
	private IFlightFinanceApi flightFinanceApiImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String monitorListPage() {
		return "page/report/monitor/list";
	}

	@RequestMapping(value = "/search/list", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<MonitorSummaryDTO> getMonitorList(MonitorSummaryQueryDTO monitorSummaryQueryDTO) {
		logger.info("查询{}的监控概要信息", monitorSummaryQueryDTO.getCustomerName());
		try {
			monitorSummaryQueryDTO.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			return reportApiImpl.queryMonitorSummary(monitorSummaryQueryDTO);
		} catch (Exception e) {
			logger.error("监控概要信息获取失败", e);
			return ListResult.fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.GET })
	public String monitorDetail(@RequestParam String memberId, @RequestParam String outCustomerId, @RequestParam String customerName, @RequestParam String outCustomerName, @RequestParam String usedLimit, @RequestParam String pledgeRage, Model model) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date yesterday = DateUtils.addDays(calendar.getTime(), -1);
		model.addAttribute("memberId", memberId);
		model.addAttribute("outCustomerId", outCustomerId);
		model.addAttribute("customerName", customerName);
		model.addAttribute("outCustomerName", outCustomerName);
		model.addAttribute("usedLimit", usedLimit);
		model.addAttribute("pledgeRage", pledgeRage);
		model.addAttribute("dailySales", MoneyUtil.cent2Yuan(flightFinanceApiImpl.getDailySales(formatDateToString(DateUtils.addMonths(yesterday, -12), "yyyy-MM"), formatDateToString(yesterday,"yyyy-MM"), Arrays.asList(outCustomerId), 360).get(0).getDailySales()));
		return "page/report/monitor/detail";	
	}

	@RequestMapping(value = "/detail/metric", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<MonitorMetricDataDTO> getMonitorMetricDetail(MonitorMetricAndDataQueryDTO monitorMetricAndDataQueryDTO) {
		try {
			monitorMetricAndDataQueryDTO.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			List<MonitorMetricDataDTO> monitorMetricDataDTOs = reportApiImpl.queryMonitorMetricDataDTO(monitorMetricAndDataQueryDTO);
			return ListResult.success(monitorMetricDataDTOs, monitorMetricDataDTOs.size());
		} catch (Exception e) {
			logger.error("监控指标信息获取失败", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/detail/data", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<MonitorDataDataDTO> getMonitorDataDetail(MonitorMetricAndDataQueryDTO monitorMetricAndDataQueryDTO) {
		try {
			monitorMetricAndDataQueryDTO.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
			List<MonitorDataDataDTO> monitorDataDataDTOs = reportApiImpl.queryMonitorDataDataDTO(monitorMetricAndDataQueryDTO);
			return ListResult.success(monitorDataDataDTOs, monitorDataDataDTOs.size());
		} catch (Exception e) {
			logger.error("监控数据信息获取失败", e);
			return ListResult.fail(e.getMessage());
		}
	}
	
	private String formatDateToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
}

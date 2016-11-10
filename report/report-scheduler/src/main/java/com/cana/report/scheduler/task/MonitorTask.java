package com.cana.report.scheduler.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.common.dao.po.Properties;
import com.cana.credit.api.ICreditApi;
import com.cana.flight.finance.api.IFlightFinanceApi;
import com.cana.report.service.transaction.IMonitorReportTransactionService;
import com.cana.vbam.common.consts.ReportConstant;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;
import com.cana.vbam.common.report.enums.ReportMonitorDataType;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;

/**
 * 监控任务需要依赖真旅日报表的生成、客票标记更新这两个前提任务。同时它也是系统预警数据生成的前提
 * @author cap
 *
 */
@Service
public class MonitorTask {

	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Resource
	private IMonitorReportTransactionService monitorReportTransactionServiceImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	@Resource
	private IFlightFinanceApi flightFinanceApiImpl;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<Institution, List<ReportMonitorDataType>> enableReportMonitorDataType = new HashMap<Institution, List<ReportMonitorDataType>>();
	
	{
		Institution[] institustions= Institution.values();
		for (Institution institution : institustions) {
			List<ReportMonitorDataType> reportMonitorDataTypes = new ArrayList<ReportMonitorDataType>();
			switch (institution) {
			case travelzen:
				reportMonitorDataTypes.add(ReportMonitorDataType.QUALIFIED_AR);
				reportMonitorDataTypes.add(ReportMonitorDataType.TICKET_TAKE_OFF_SALE);
				reportMonitorDataTypes.add(ReportMonitorDataType.TOTAL_SALES);
				break;
			default:
				break;
			}
			enableReportMonitorDataType.put(institution, reportMonitorDataTypes);
		}
	}
	
	@Scheduled(cron = "0 40 1-5 * * ?")
	public void doTask() {
		// 获取今日零时和昨日零时
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date today = calendar.getTime();
		Date yesterday = DateUtils.addDays(today, -1);
		String yesterdayStr10 = formatDateToString(yesterday, "yyyy-MM-dd");
		
		Properties properties = vbamCommonServiceImpl.getProperties(ReportConstant.MONITOR_UPDATE_TIME);
		if(properties.getUpdateTime().before(today)) {
			
			// 获取所有通过额度审核的所有客户
			Map<String, Map<String, String>> outCustomers = creditApiImpl.getAllOutCustomer();
			// 不同外部平台客户和数据类型生成不同的数据
			Set<String> institutions = outCustomers.keySet();
			for (String institution : institutions) {
				Institution institutionEnum = Institution.valueOf(institution);
				Map<String, String> outCustomerId2MemberIdMap = outCustomers.get(institution);
				switch (institutionEnum) {
					case travelzen:
						logger.info("开始生成{}监控数据", institution);
						long startTime = System.currentTimeMillis();
						handleTravelzenData(today, institutionEnum, institution, yesterdayStr10, yesterday, outCustomerId2MemberIdMap, Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
						logger.info("结束生成{}监控数据，耗时{}ms", institution, System.currentTimeMillis() - startTime);
						break;
					default:
						break;
				}
			}
			
			handleMonitorUpdateTime(today, properties);
		}
	}
	
	private void handleTravelzenData(Date today, Institution institutionEnum, String institution, String yesterdayStr10, Date yesterday, Map<String, String> outCustomerId2MemberIdMap, String productId) {
		Properties ticketFlagProperties = vbamCommonServiceImpl.getProperties(ReportConstant.LAST_ALTERALBE_LAST_RECORD_ID);
		if(ticketFlagProperties.getUpdateTime().before(today)) {
			logger.warn("今日的客票标记更新程序没有运行。无法生成真旅监控数据");
			return;
		}
		String startRecordId = ticketFlagProperties.getValue();
		List<MonitorMoneyDTO> monitorMoneyDTOs = null;
		List<ReportMonitorDataType> reportMonitorDataTypes = enableReportMonitorDataType.get(institutionEnum);
		for (ReportMonitorDataType reportMonitorDataType : reportMonitorDataTypes) {
			logger.info("开始{}", reportMonitorDataType.name());
			long startTime = System.currentTimeMillis();
			switch (reportMonitorDataType) {
			case QUALIFIED_AR:
				Properties travelzenQualifiedARProperties = vbamCommonServiceImpl.getProperties(institution + ReportConstant.QUALIFIED_AR_UPDDATE_TIME);
				if(travelzenQualifiedARProperties.getUpdateTime().before(today)) {
					monitorMoneyDTOs = creditApiImpl.getQualifiedAR(startRecordId);
					List<CreditUsedLimit> creditUsedLimits = creditApiImpl.queryUsedLimit(today, institution, productId);
					monitorReportTransactionServiceImpl.generateQualifiedARData(monitorMoneyDTOs, creditUsedLimits, yesterdayStr10, travelzenQualifiedARProperties, productId);
				}
				break;
			case TICKET_TAKE_OFF_SALE:
				Properties travelzenTicketTakeOffProperties = vbamCommonServiceImpl.getProperties(institution + ReportConstant.TICKET_TAKE_OFF_SALE);
				if(travelzenTicketTakeOffProperties.getUpdateTime().before(today)) {
					monitorMoneyDTOs = flightFinanceApiImpl.getTakeOffSales(outCustomerId2MemberIdMap, yesterdayStr10, startRecordId);
					monitorReportTransactionServiceImpl.generateTicketTakeOffSalesData(monitorMoneyDTOs, yesterdayStr10, travelzenTicketTakeOffProperties, productId);
				}
				break;
			case TOTAL_SALES:
				Properties travelzenTotalSalesProperties = vbamCommonServiceImpl.getProperties(institution + ReportConstant.TOTAL_SALES_UPDATE_TIME);
				if(travelzenTotalSalesProperties.getUpdateTime().before(today)) {
					monitorMoneyDTOs = creditApiImpl.getFlightTicketSales(yesterdayStr10);
					List<MonitorMoneyDTO> dailySales = creditApiImpl.getDailySales(formatDateToString(DateUtils.addMonths(yesterday, -12), "yyyy-MM"), formatDateToString(yesterday, "yyyy-MM"), 360);
					monitorReportTransactionServiceImpl.generateTotalSalesData(monitorMoneyDTOs, dailySales, yesterdayStr10, outCustomerId2MemberIdMap, travelzenTotalSalesProperties, productId);
				}
				break;
			default:
				break;
			}
			logger.info("结束{}，耗时{}ms", reportMonitorDataType.name(), System.currentTimeMillis() - startTime);
		}
	}
	
	private void handleMonitorUpdateTime(Date today, Properties properties) {
		ReportMonitorDataType[] allReportMonitorDataTypes = ReportMonitorDataType.values();
		List<Properties> allProperties = new ArrayList<>();
		for (ReportMonitorDataType reportMonitorDataType : allReportMonitorDataTypes)
			allProperties.addAll(vbamCommonServiceImpl.getPropertiesByNameLike(reportMonitorDataType.name()));
		for (Properties p : allProperties)
			if(p.getUpdateTime().before(today))
				return;
		vbamCommonServiceImpl.updateProperties(properties, formatDateToString(today, "yyyy-MM-dd"));
	}
	
	private String formatDateToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
}

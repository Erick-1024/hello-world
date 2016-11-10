package com.cana.report.service.transaction.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.common.dao.po.Properties;
import com.cana.report.dao.po.ReportMonitorMetric;
import com.cana.report.service.IReportMonitorService;
import com.cana.report.service.transaction.IMonitorReportTransactionService;
import com.cana.vbam.common.credit.dto.trade.CreditUsedLimit;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;
import com.cana.vbam.common.report.enums.ReportMonitorDataType;
import com.cana.vbam.common.report.enums.ReportMonitorMetricType;
import com.cana.vbam.common.service.IVbamCommonService;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.util.DateUtils;

@Service
public class MonitorReportTransactionServiceImpl implements IMonitorReportTransactionService {

	@Resource
	private IReportMonitorService reportMonitorServiceImpl;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Override
	public boolean generateTotalSalesData(List<MonitorMoneyDTO> monitorMoneyDTOs, List<MonitorMoneyDTO> dailySales, String yesterdayStr10, Map<String, String> outCustomerId2MemberIdMap, Properties properties, String productId) {
		Map<String, Long> outCusomterId2Money = getOutCustomerId2MoneyAndSaveData(monitorMoneyDTOs, ReportMonitorDataType.TOTAL_SALES, yesterdayStr10, productId);
		Map<String, Long> repaymentSales = reportMonitorServiceImpl.getMonitorData(new ArrayList<>(outCustomerId2MemberIdMap.keySet()), ReportMonitorDataType.REPAYMENT_SALES, yesterdayStr10, productId);
		for (MonitorMoneyDTO dailySale : dailySales) {
			String memberId = dailySale.getMemberId();
			String outCustomerId = dailySale.getOutCustomerId();
			Long price = dailySale.getPrice();
			Long ticketAllSales = outCusomterId2Money.get(outCustomerId);
			Long repaymentSale = repaymentSales.get(outCustomerId);
			
			// 保存销售变化率
			reportMonitorServiceImpl.saveMonitorMetric(memberId, outCustomerId, (price == null || ticketAllSales == null) ? null : new BigDecimal(ticketAllSales).divide(new BigDecimal(price), 5, RoundingMode.HALF_UP), ReportMonitorMetricType.SALES_CHANGE_RATE, yesterdayStr10, productId);
			
			// 保存销售回款率
			reportMonitorServiceImpl.saveMonitorMetric(memberId, outCustomerId, (price == null || repaymentSale == null) ? null : new BigDecimal(repaymentSale).divide(new BigDecimal(price), 5, RoundingMode.HALF_UP), ReportMonitorMetricType.SALES_REPAYMENT_RATE, yesterdayStr10, productId);
		}
		vbamCommonServiceImpl.updateProperties(properties, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return true;
	}

	@Override
	public boolean generateQualifiedARData(List<MonitorMoneyDTO> monitorMoneyDTOs, List<CreditUsedLimit> creditUsedLimits, String yesterdayStr10, Properties properties, String productId) {
		Map<String, Long> outCustomerId2Money = getOutCustomerId2MoneyAndSaveData(monitorMoneyDTOs, ReportMonitorDataType.QUALIFIED_AR, yesterdayStr10, productId);
		for (CreditUsedLimit creditUsedLimit : creditUsedLimits) {
			String memberId = creditUsedLimit.getMemberId();
			String outCustomerId = creditUsedLimit.getOutCustomerId();
			Long usedLimit = creditUsedLimit.getUsedLimit();
			Long AR = outCustomerId2Money.get(outCustomerId);
			BigDecimal pledgeRage = new BigDecimal("0.8");
			reportMonitorServiceImpl.saveMonitorData(memberId, outCustomerId, usedLimit, ReportMonitorDataType.USED_LIMIT_THE_DAY, yesterdayStr10, productId);
			reportMonitorServiceImpl.saveMonitorMetric(memberId, outCustomerId, (usedLimit == null || usedLimit == 0 || AR == null || pledgeRage == null) ? null : new BigDecimal(AR).multiply(pledgeRage).divide(new BigDecimal(usedLimit), 5, RoundingMode.HALF_UP), ReportMonitorMetricType.COUNTER_GUARANTEE_RATE, yesterdayStr10, productId);
		}
		vbamCommonServiceImpl.updateProperties(properties, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return true;
	}

	@Override
	public boolean generateTicketTakeOffSalesData(List<MonitorMoneyDTO> monitorMoneyDTOs,  String yesterdayStr10, Properties properties, String productId) {
		for (MonitorMoneyDTO monitorMoneyDTO : monitorMoneyDTOs)			
			reportMonitorServiceImpl.saveMonitorData(monitorMoneyDTO.getMemberId(), monitorMoneyDTO.getOutCustomerId(), monitorMoneyDTO.getPrice(), ReportMonitorDataType.TICKET_TAKE_OFF_SALE, yesterdayStr10, productId);
		vbamCommonServiceImpl.updateProperties(properties, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return true;
	}

	private Map<String, Long> getOutCustomerId2MoneyAndSaveData(List<MonitorMoneyDTO> monitorMoneyDTOs, ReportMonitorDataType reportMonitorDataType, String yesterdayStr10, String productId) {
		Map<String, Long> outCustomerId2Money = new HashMap<>();
		for (MonitorMoneyDTO monitorMoneyDTO : monitorMoneyDTOs) {
			String outCustomerId = monitorMoneyDTO.getOutCustomerId();
			Long price = monitorMoneyDTO.getPrice();
			reportMonitorServiceImpl.saveMonitorData(monitorMoneyDTO.getMemberId(), outCustomerId, price, reportMonitorDataType, yesterdayStr10, productId);
			outCustomerId2Money.put(outCustomerId, price);
		}
		return outCustomerId2Money;
	}

	@Override
	public void save(BigDecimal param, String memberId, String stationNo, String yundaexAssetProjectId, String type, String currentDate) {
		ReportMonitorMetric reportMonitorMetric = new ReportMonitorMetric();
		reportMonitorMetric.setMemberId(memberId);
		reportMonitorMetric.setOutCustomerId(stationNo);
		reportMonitorMetric.setProductId(yundaexAssetProjectId);
		reportMonitorMetric.setType(type);
		reportMonitorMetric.setProportion(param);
		reportMonitorMetric.setDate(DateTimeUtil.month7(DateTimeUtil.addMonth(DateTimeUtil.getDate10(currentDate), -1)));
		reportMonitorMetric.setCreateTime(new Date());
		reportMonitorServiceImpl.saveMonitorMetric(reportMonitorMetric);
		}
	
}

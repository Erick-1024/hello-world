package com.cana.report.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.report.dao.mapper.gen.ReportMonitorDataMapper;
import com.cana.report.dao.mapper.gen.ReportMonitorMetricMapper;
import com.cana.report.dao.po.ReportMonitorData;
import com.cana.report.dao.po.ReportMonitorDataExample;
import com.cana.report.dao.po.ReportMonitorMetric;
import com.cana.report.dao.po.ReportMonitorMetricExample;
import com.cana.report.service.IReportMonitorService;
import com.cana.report.service.util.IReportServiceHelper;
import com.cana.vbam.common.report.dto.MonitorDataData;
import com.cana.vbam.common.report.dto.MonitorMetricData;
import com.cana.vbam.common.report.dto.MonitorMetricDataYunda;
import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;
import com.cana.vbam.common.report.enums.ReportMonitorDataType;
import com.cana.vbam.common.report.enums.ReportMonitorMetricType;
import com.cana.vbam.common.utils.Constants;
import com.cana.yundaex.common.enums.YundaexMonitor;
import com.travelzen.framework.core.exception.WebException;

@Service
public class ReportMonitorService implements IReportMonitorService {

	@Resource
	private ReportMonitorDataMapper reportMonitorDataMapper;
	
	@Resource
	private ReportMonitorMetricMapper reportMonitorMetricMapper;
	
	@Resource
	private IReportServiceHelper serviceHelper;
	

	@Override
	public void saveMonitorData(String memberId, String outCustomerId, Long price, ReportMonitorDataType reportMonitorDataType, String date10, String productId) {
		ReportMonitorData reportMonitorData = new ReportMonitorData();
		reportMonitorData.setId(serviceHelper.generateReportMonitorDataId());
		reportMonitorData.setMemberId(memberId);
		reportMonitorData.setOutCustomerId(outCustomerId);
		reportMonitorData.setProductId(productId);
		reportMonitorData.setType(reportMonitorDataType.name());
		reportMonitorData.setAmount(price);
		reportMonitorData.setDate(date10);
		reportMonitorData.setCreateTime(new Date());
		reportMonitorDataMapper.insertSelective(reportMonitorData);
	}
	
	@Override
	public void saveMonitorMetric(String memberId, String outCustomerId, BigDecimal proportion, ReportMonitorMetricType reportMonitorMetricType, String date10, String productId) {
		ReportMonitorMetric reportMonitorMetric = new ReportMonitorMetric();
		reportMonitorMetric.setId(serviceHelper.generateReportMonitorMetricId());
		reportMonitorMetric.setMemberId(memberId);
		reportMonitorMetric.setOutCustomerId(outCustomerId);
		reportMonitorMetric.setProductId(productId);
		reportMonitorMetric.setType(reportMonitorMetricType.name());
		reportMonitorMetric.setProportion(proportion);
		reportMonitorMetric.setDate(date10);
		reportMonitorMetric.setCreateTime(new Date());
		reportMonitorMetricMapper.insertSelective(reportMonitorMetric);
	}

	@Override
	public Map<String, Long> getMonitorData(List<String> outCustomerIds, ReportMonitorDataType reportMonitorDataType, String date, String productId) {
		ReportMonitorDataExample reportMonitorDataExample = new ReportMonitorDataExample();
		reportMonitorDataExample.createCriteria().andOutCustomerIdIn(outCustomerIds).andTypeLike("%" + reportMonitorDataType.name()).andDateEqualTo(date).andProductIdEqualTo(productId);
		List<ReportMonitorData> reportMonitorDatas = reportMonitorDataMapper.selectByExample(reportMonitorDataExample);
		Map<String, Long> returnValue = new HashMap<>();
		for (ReportMonitorData reportMonitorData : reportMonitorDatas)
			returnValue.put(reportMonitorData.getOutCustomerId(), reportMonitorData.getAmount());
		return returnValue;
	}

	@Override
	public List<MonitorMetricData> queryMonitorMetric(String memberId, String outCustomerId, String productId, String startDate, String endDate) {
		ReportMonitorMetricExample reportMonitorMetricExample = new ReportMonitorMetricExample();
		reportMonitorMetricExample.createCriteria().andMemberIdEqualTo(memberId).andOutCustomerIdEqualTo(outCustomerId).andProductIdEqualTo(productId).andDateBetween(startDate, endDate);
		reportMonitorMetricExample.setOrderByClause("date");
		List<ReportMonitorMetric> reportMonitorMetrics = reportMonitorMetricMapper.selectByExample(reportMonitorMetricExample);
		List<MonitorMetricData> returnValues = new ArrayList<>();
		Map<String, MonitorMetricData> tempMap = new HashMap<>();
		for (ReportMonitorMetric reportMonitorMetric : reportMonitorMetrics) {
			String date = reportMonitorMetric.getDate();
			MonitorMetricData monitorMetricData;
			if(tempMap.containsKey(date))
				monitorMetricData =  tempMap.get(date);
			else
				monitorMetricData = new MonitorMetricData();
			ReportMonitorMetricType reportMonitorMetricType = ReportMonitorMetricType.valueOf(reportMonitorMetric.getType());
			BigDecimal proportion = reportMonitorMetric.getProportion();
			switch (reportMonitorMetricType) {
			case COUNTER_GUARANTEE_RATE:
				monitorMetricData.setCounterGuaranteeRate(proportion);
				break;
			case SALES_CHANGE_RATE:
				monitorMetricData.setSalesChangeRate(proportion);
				break;
			case SALES_REPAYMENT_RATE:
				monitorMetricData.setSalesRepaymentRate(proportion);
				break;
			default:
				break;
			}
			if(!tempMap.containsKey(date)) {
				monitorMetricData.setDate(date);
				tempMap.put(date, monitorMetricData);
				returnValues.add(monitorMetricData);
			}
		}
		return returnValues;
	}

	@Override
	public List<MonitorDataData> queryMonitorData(String memberId, String outCustomerId, String productId, String startDate, String endDate) {
		ReportMonitorDataExample reportMonitorDataExample = new ReportMonitorDataExample();
		reportMonitorDataExample.createCriteria().andMemberIdEqualTo(memberId).andOutCustomerIdEqualTo(outCustomerId).andProductIdEqualTo(productId).andDateBetween(startDate, endDate);
		reportMonitorDataExample.setOrderByClause("date");
		List<ReportMonitorData> reportMonitorDatas= reportMonitorDataMapper.selectByExample(reportMonitorDataExample);
		List<MonitorDataData> returnValue = new ArrayList<>();
		Map<String, MonitorDataData> tempMap = new HashMap<>();
		int travelzenFinanceProductStringLength = Constants.TRAVELZEN_FINANCE_PRODUCT_ID.length();
		for (ReportMonitorData reportMonitorData : reportMonitorDatas) {
			String date = reportMonitorData.getDate();
			MonitorDataData monitorDataData;
			if(tempMap.containsKey(date))
				monitorDataData = tempMap.get(date);
			else
				monitorDataData = new MonitorDataData();
			String type = reportMonitorData.getType();
			if(type.contains(Constants.TRAVELZEN_FINANCE_PRODUCT_ID))
				type = type.substring(travelzenFinanceProductStringLength);
			ReportMonitorDataType reportMonitorDataType = ReportMonitorDataType.valueOf(type);
			Long amount = reportMonitorData.getAmount();
			switch (reportMonitorDataType) {
			case QUALIFIED_AR:
				monitorDataData.setQualifiedAR(amount);
				break;
			case TOTAL_SALES:
				monitorDataData.setTicketAllSales(amount);
				break;
			case REPAYMENT_SALES:
				monitorDataData.setTicketRepayment(amount);
				break;
			case TICKET_TAKE_OFF_SALE:
				monitorDataData.setTicketTakeOffSale(amount);
				break;
			case USED_LIMIT_THE_DAY:
				monitorDataData.setUsedLimitTheDay(amount);
			default:
				break;
			}
			if(!tempMap.containsKey(date)) {
				monitorDataData.setDate(date);
				tempMap.put(date, monitorDataData);
				returnValue.add(monitorDataData);
			}
		}
		return returnValue;
	}

	@Override
	public void saveMonitorMetric(ReportMonitorMetric reportMonitorMetric) {
		reportMonitorMetric.setId(serviceHelper.generateReportMonitorMetricId());
		reportMonitorMetricMapper.insert(reportMonitorMetric);
		
	}
	
	
	/**
	 * 韵达监控详情指标
	 */
	@Override
	public List<MonitorMetricDataYunda> queryYundaMonitorMetric(String memberId,String productId, String startDate, String endDate) {
		ReportMonitorMetricExample reportMonitorMetricExample = new ReportMonitorMetricExample();
		reportMonitorMetricExample.createCriteria().andMemberIdEqualTo(memberId).
		andProductIdEqualTo(productId).andDateBetween(startDate, endDate);
		reportMonitorMetricExample.setOrderByClause("date");
		List<ReportMonitorMetric> reportMonitorMetrics = reportMonitorMetricMapper.selectByExample(reportMonitorMetricExample);
		List<MonitorMetricDataYunda> returnValues = new ArrayList<>();
		Map<String, MonitorMetricDataYunda> tempMap = new HashMap<>();
		for (ReportMonitorMetric reportMonitorMetric : reportMonitorMetrics){
			String date = reportMonitorMetric.getDate();
			MonitorMetricDataYunda monitorMetricDataYunda;
			if(tempMap.containsKey(date))
				monitorMetricDataYunda =  tempMap.get(date);
			else
				monitorMetricDataYunda = new MonitorMetricDataYunda();
			YundaexMonitor yundaexMonitor= YundaexMonitor.valueOf(reportMonitorMetric.getType());
			BigDecimal proportion = reportMonitorMetric.getProportion();
			switch (yundaexMonitor) {
			case RECANDSEND_GROWTHRATE:
				monitorMetricDataYunda.setRecandsendGrowthrate(proportion);
				break;
			case BAILBALANCE:
				monitorMetricDataYunda.setBailBalance(proportion);
				break;
			case YUNDAEXGRADE:
				monitorMetricDataYunda.setYundaexGrade(proportion);
				break;
			case NET_CASHFLOW:
				monitorMetricDataYunda.setNetCashflowGrowth(proportion);
				break;
			case CREDIT_LIMIT:
				monitorMetricDataYunda.setCreditLimit(proportion);
				break;
			case OVERDUES:
				monitorMetricDataYunda.setOverdues(proportion);
				break;
			case DAY_REQUIREMENTS:
				monitorMetricDataYunda.setDayRequirements(proportion);
				break;
			default:
				break;
			}
			if(!tempMap.containsKey(date)) {
				monitorMetricDataYunda.setDate(date);
				tempMap.put(date, monitorMetricDataYunda);
				returnValues.add(monitorMetricDataYunda);
			}
		}
		return returnValues;
	}
	
	
	/**
	 * 查询监控表信息
	 * ＠param
	 */
	@Override
	public List<MonitorMetricDataYunda> queryMonitorMetric(String productId) {
		if(StringUtils.isBlank(productId))
			throw WebException.instance("项目id为空");
		ReportMonitorMetricExample reportMonitorMetricExample = new ReportMonitorMetricExample();
		reportMonitorMetricExample.createCriteria().andProductIdEqualTo(productId);
		List<ReportMonitorMetric> reportMonitorMetrics = reportMonitorMetricMapper.selectByExample(reportMonitorMetricExample);
		List<MonitorMetricDataYunda> returnValues = new ArrayList<>();
		for(ReportMonitorMetric reportMonitorMetric : reportMonitorMetrics){
			MonitorMetricDataYunda monitorMetricDataYunda = new	MonitorMetricDataYunda();
			monitorMetricDataYunda.setProjectId(reportMonitorMetric.getProductId());
			returnValues.add(monitorMetricDataYunda);
		}
		return returnValues;
	}

	/**
	 * 查询监控列表数据
	 */
	@Override
	public List<MonitorMetricDataYunda> queryYundaexMonitorMetric(String memberId, String productId, String date) {
		requestParam(memberId, productId, date);
		ReportMonitorMetricExample reportMonitorMetricExample = new ReportMonitorMetricExample();
		reportMonitorMetricExample.createCriteria().andProductIdEqualTo(productId).andMemberIdEqualTo(memberId).andDateEqualTo(date);
		reportMonitorMetricExample.setOrderByClause("date");
		List<ReportMonitorMetric> reportMonitorMetrics = reportMonitorMetricMapper.selectByExample(reportMonitorMetricExample);
		List<MonitorMetricDataYunda> returnValues = new ArrayList<>();
		Map<String, MonitorMetricDataYunda> tempMap = new HashMap<>();
		for(ReportMonitorMetric reportMonitorMetric : reportMonitorMetrics){
			String date2 = reportMonitorMetric.getDate();
			MonitorMetricDataYunda monitorMetricDataYunda;
			if(tempMap.containsKey(date))
				monitorMetricDataYunda =  tempMap.get(date);
			else 
				monitorMetricDataYunda	= new MonitorMetricDataYunda();
			YundaexMonitor yundaexMonitor= YundaexMonitor.valueOf(reportMonitorMetric.getType());
			BigDecimal proportion = reportMonitorMetric.getProportion();
			switch (yundaexMonitor) {
			case RECANDSEND_GROWTHRATE:
				monitorMetricDataYunda.setRecandsendGrowthrate(proportion);
				break;
			case BAILBALANCE:
				monitorMetricDataYunda.setBailBalance(proportion);
				break;
			case YUNDAEXGRADE:
				monitorMetricDataYunda.setYundaexGrade(proportion);
				break;
			case YUNDAEXJUDGE:
				monitorMetricDataYunda.setYundaexJudge(proportion);
				break;
			case DAY_REQUIREMENTS:
				monitorMetricDataYunda.setDayRequirements(proportion);
				break;
			case SHORTLOAN:
				monitorMetricDataYunda.setShortLoan(proportion);
				break;
			default:
				break;
			}
			if(!tempMap.containsKey(date)) {
				monitorMetricDataYunda.setDate(date2);
				tempMap.put(date2, monitorMetricDataYunda);
				returnValues.add(monitorMetricDataYunda);
			}
		}
		return returnValues;
	}
	

	/**
	 * 监控列表条件判空
	 * @param memberId
	 * @param productId
	 * @param date
	 * @throws WebException
	 */
	private void requestParam(String memberId, String productId, String date) throws WebException {
		if(StringUtils.isBlank(productId))
			throw WebException.instance("项目id为空");
		if(StringUtils.isBlank(memberId))
			throw WebException.instance("用户memberId为空");
		if(StringUtils.isBlank(date))
			throw WebException.instance("当月时间date为空");
	}

	
	/**
	 * 给韵达预警提供监控数据接口
	 * @param productId
	 * @param startDate
	 * @param endDate
	 */
	@Override
	public List<MonitorMetricYundaDTO> queryYundaMonitorMetricEarlyWarning(String memberId,String productId, String startDate,String endDate) {
		
		ReportMonitorMetricExample reportMonitorMetricExample = new ReportMonitorMetricExample();
		reportMonitorMetricExample.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(productId).andDateBetween(startDate, endDate);
		reportMonitorMetricExample.setOrderByClause("date");
		List<ReportMonitorMetric> reportMonitorMetrics = reportMonitorMetricMapper.selectByExample(reportMonitorMetricExample);
		List<MonitorMetricYundaDTO> returnValues = new ArrayList<>();
		Map<String, MonitorMetricYundaDTO> tempMap = new HashMap<>();
		for (ReportMonitorMetric reportMonitorMetric : reportMonitorMetrics){
			String date = reportMonitorMetric.getDate();
			MonitorMetricYundaDTO monitorMetricYundaDTO;
			if(tempMap.containsKey(date))
				monitorMetricYundaDTO =  tempMap.get(date);
			else
				monitorMetricYundaDTO = new MonitorMetricYundaDTO();
			YundaexMonitor yundaexMonitor= YundaexMonitor.valueOf(reportMonitorMetric.getType());
			BigDecimal proportion = reportMonitorMetric.getProportion();
			switch (yundaexMonitor) {
			case RECANDSEND_GROWTHRATE:
				monitorMetricYundaDTO.setRecandsendGrowthrate(proportion);
				break;
			case BAILBALANCE:
				monitorMetricYundaDTO.setBailBalance(proportion);
				break;
			case YUNDAEXGRADE:
				monitorMetricYundaDTO.setYundaexGrade(proportion);
				break;
			case NET_CASHFLOW:
				monitorMetricYundaDTO.setNetCashflow(proportion);
				break;
			case CREDIT_LIMIT:
				monitorMetricYundaDTO.setCreditLimit(proportion);
				break;
			case OVERDUES:
				monitorMetricYundaDTO.setOverdues(proportion);
				break;
			case DAY_REQUIREMENTS:
				monitorMetricYundaDTO.setDayRequirements(proportion);
				break;
			case SHORTLOAN:
				monitorMetricYundaDTO.setShortLoan(proportion);
				break;
			default:
				break;
			}
			if(!tempMap.containsKey(date)) {
				monitorMetricYundaDTO.setDate(date);
				tempMap.put(date, monitorMetricYundaDTO);
				returnValues.add(monitorMetricYundaDTO);
			}
		}
		return returnValues;
	}
	

}





















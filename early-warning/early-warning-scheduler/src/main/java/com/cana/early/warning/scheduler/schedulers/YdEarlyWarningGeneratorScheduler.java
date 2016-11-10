package com.cana.early.warning.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.early.warning.service.IEarlywarningSystemEventGenerateRecordService;
import com.cana.early.warning.service.transaction.IYdEarlyWarningEventTransactionService;
import com.cana.repayment.api.IFinanceReportApi;
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.consts.ReportConstant;
import com.cana.vbam.common.repayment.dto.QueryPlanListRequest;
import com.cana.vbam.common.report.dto.MonitorMetricYundaDTO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.yundaex.dto.creditLimit.YundaexAccessCreditLimitCustomerInfoDTO;
import com.cana.yundaex.api.IYundaexCreditApi;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;

@Service
public class YdEarlyWarningGeneratorScheduler {

	@Resource
	private IReportApi reportApiImpl;
	
	@Resource
	private IFinanceReportApi financeReportApiImpl;
	
	@Resource
	private IYundaexCreditApi ydCreditApiImpl;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Resource
	private IEarlywarningSystemEventGenerateRecordService earlywarningSystemEventGenerateRecordServiceImpl;
	
	@Resource
	private IYdEarlyWarningEventTransactionService ydEarlyWarningEventTransactionServiceImpl;
	
	private SimpleDateFormat monthDateFormat = new SimpleDateFormat("yyyy-MM");
	
//	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private ThreadPoolExecutor transferThreadPool;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	{
		int processorsOfCPU = Runtime.getRuntime().availableProcessors();
		transferThreadPool = new ThreadPoolExecutor(processorsOfCPU, processorsOfCPU * 2, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
	}
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30)
	public void systemEarlyWarningGenerator() throws Exception {
		
		logger.info("系统预警生成定时任务开始");
		if (isGenerateMontorDate()) {
			List<YundaexAccessCreditLimitCustomerInfoDTO> enableCustomers = getEnableCustomers(false);
			logger.info("系统预警生成每月任务处理客户数量[{}]", enableCustomers.size());
			for (YundaexAccessCreditLimitCustomerInfoDTO customerInfoDTO : enableCustomers)
				generateEarlyWarningSystemEvent(customerInfoDTO.getMemberId(), customerInfoDTO.getStationNo(), customerInfoDTO.getStationName(), true);
		}else{
			logger.info("系统预警生成监控数据未生成");
		}
		
		List<YundaexAccessCreditLimitCustomerInfoDTO> enableCustomers = getEnableCustomers(true);
		logger.info("系统预警生成每日任务处理客户数量[{}]", enableCustomers.size());
		for (YundaexAccessCreditLimitCustomerInfoDTO customerInfoDTO : enableCustomers)
			generateEarlyWarningSystemEvent(customerInfoDTO.getMemberId(), customerInfoDTO.getStationNo(), customerInfoDTO.getStationName(), false);
	}
	
	/**
	 * 当前月的监控数据是否生成
	 * @return
	 */
	private boolean isGenerateMontorDate() {
		
		return vbamCommonServiceImpl.getProperties(ReportConstant.YUNDAEX_MONITOR_DATE).getValue().equals(monthDateFormat.format(getCurrentDate()));
//		return vbamCommonServiceImpl.getProperties("yundaex_virtual_date").getValue().compareTo(monthDateFormat.format(new Date())) >= 0;
	}
	
	/**
	 * 获取未生成系统预警的客户名单
	 * @return Map&lt;productId, List&lt;OutCustomerDTO>>
	 */
	private List<YundaexAccessCreditLimitCustomerInfoDTO> getEnableCustomers(boolean isByMonth) {
		List<YundaexAccessCreditLimitCustomerInfoDTO> returnValue = Lists.newArrayList();
		Date now = getCurrentDate();
		// 获取所有通过额度审核的所有客户
		List<YundaexAccessCreditLimitCustomerInfoDTO> customerInfoDTOList = ydCreditApiImpl.getYundaexAccessCreditLimitCustomerInfoDTO();
		for (YundaexAccessCreditLimitCustomerInfoDTO outCustomerDTO : customerInfoDTOList) {
			if(isByMonth){
				if(!earlywarningSystemEventGenerateRecordServiceImpl.isExistRecord(Constants.YUNDAEX_FINANCE_PRODUCT_ID, outCustomerDTO.getMemberId(), outCustomerDTO.getStationNo(), now))
					returnValue.add(outCustomerDTO);
			}else{
				if(!earlywarningSystemEventGenerateRecordServiceImpl.isExistRecordWithMonth(Constants.YUNDAEX_FINANCE_PRODUCT_ID, outCustomerDTO.getMemberId(), outCustomerDTO.getStationNo(), now))
					returnValue.add(outCustomerDTO);
			}
		}
		return returnValue;
	}
	
	/**
	 * 为每个未生成系统预警的客户生成预警信息
	 * @param memberId 融资商在cana平台的ID
	 * @param productId 所属的产品ID
	 * @param companyName 公司名称
	 */
	private void generateEarlyWarningSystemEvent(final String memberId, final String outCustomerId, final String companyName, final boolean isByMonth) {
		transferThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Date now = getCurrentDate();
					// 获取逾期数据
					QueryPlanListRequest queryPlanListRequest = new QueryPlanListRequest();
					queryPlanListRequest.setBusinessProductId(Constants.YUNDAEX_FINANCE_PRODUCT_ID);
					queryPlanListRequest.setFinanceId(memberId);
//					queryPlanListRequest.setOutCustomerId(outCustomerId);
//					queryPlanListRequest.setStartDate(simpleDateFormat.format(DateUtils.addMonths(now, -EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.OVERDUEPLANS_MONTH))));
//					queryPlanListRequest.setEndDate(simpleDateFormat.format(now));
					int overduePlans = financeReportApiImpl.countOverduePlans(queryPlanListRequest);
//					int continueOverduePlans = financeReportApiImpl.countContinueOverduePlans(queryPlanListRequest);
					
					MonitorMetricYundaDTO monitorMetricData = null ;
					MonitorMetricYundaDTO lastMonitorMetricData = null;
					if(isByMonth){
						// 获取监控指标
						String lastMonth = monthDateFormat.format(DateUtils.addMonths(now, -1));
						String lastLastMonth = monthDateFormat.format(DateUtils.addMonths(now, -2));
						List<MonitorMetricYundaDTO> monitorMetricDatas = reportApiImpl.queryYundaMonitorMetricEarlyWarning(memberId, Constants.YUNDAEX_FINANCE_PRODUCT_ID, lastLastMonth, lastMonth);
						if(CollectionUtils.isEmpty(monitorMetricDatas))
							throw WebException.instance("监控数据不存在！");
						
						for(MonitorMetricYundaDTO  monitorMetricYundaDTO : monitorMetricDatas){
							if(lastMonth.equals(monitorMetricYundaDTO.getDate()))
								monitorMetricData = monitorMetricYundaDTO;
							else if(lastLastMonth.equals(monitorMetricYundaDTO.getDate()))
								lastMonitorMetricData = monitorMetricYundaDTO;
						}
						if(null == monitorMetricData)
							throw WebException.instance("上个月监控数据不存在！");
					}
					// 调用事务Service
					ydEarlyWarningEventTransactionServiceImpl.reckonYdEarlyWarningSystemEvent(overduePlans, monitorMetricData, lastMonitorMetricData, memberId, outCustomerId, Constants.YUNDAEX_FINANCE_PRODUCT_ID, companyName, isByMonth);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
	
	private Date getCurrentDate(){
		String currentDate = vbamCommonServiceImpl.getCurrentDate();
		return new DateTime(currentDate).toDate();
	}
}

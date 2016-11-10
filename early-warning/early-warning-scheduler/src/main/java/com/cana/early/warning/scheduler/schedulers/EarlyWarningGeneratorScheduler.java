package com.cana.early.warning.scheduler.schedulers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.credit.api.ICreditApi;
import com.cana.early.warning.service.IEarlywarningSystemEventGenerateRecordService;
import com.cana.early.warning.service.transaction.IEarlyWarningEventTransactionService;
import com.cana.early.warning.service.utils.EarlyWarningProperties;
import com.cana.repayment.api.IFinanceReportApi;
import com.cana.report.api.IReportApi;
import com.cana.vbam.common.consts.ReportConstant;
import com.cana.vbam.common.credit.dto.outcustomer.OutCustomerDTO;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.repayment.dto.QueryPlanListRequest;
import com.cana.vbam.common.report.dto.MonitorMetricData;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;

@Service
public class EarlyWarningGeneratorScheduler {

	@Resource
	private IReportApi reportApiImpl;
	
	@Resource
	private IFinanceReportApi financeReportApiImpl;
	
	@Resource
	private ICreditApi creditApiImpl;
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Resource
	private IEarlywarningSystemEventGenerateRecordService earlywarningSystemEventGenerateRecordServiceImpl;
	
	@Resource
	private IEarlyWarningEventTransactionService earlyWarningEventTransactionServiceImpl;
	
	private ThreadPoolExecutor transferThreadPool;
	
	{
		int processorsOfCPU = Runtime.getRuntime().availableProcessors();
		transferThreadPool = new ThreadPoolExecutor(processorsOfCPU, processorsOfCPU * 2, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
	}
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30)
	public void systemEarlyWarningGenerator() {
		if (isGenerateMontorDate()) {
			Map<String, List<OutCustomerDTO>> enableCustomers = getEnableCustomers();
			Set<String> productIds = enableCustomers.keySet();
			for (String productId : productIds) {
				List<OutCustomerDTO> outCustomerDTOList = enableCustomers.get(productId);
				for (OutCustomerDTO outCustomerDTO : outCustomerDTOList)
					generateEarlyWarningSystemEvent(outCustomerDTO.getMemberId(), outCustomerDTO.getOutCustomerId(), productId, outCustomerDTO.getCompanyName());
			}
		}
	}
	
	/**
	 * 昨日的监控数据是否生成
	 * @return
	 */
	private boolean isGenerateMontorDate() {
		return vbamCommonServiceImpl.getProperties(ReportConstant.MONITOR_UPDATE_TIME).getValue().equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
	
	/**
	 * 获取未生成系统预警的客户名单
	 * @return Map&lt;productId, List&lt;OutCustomerDTO>>
	 */
	private Map<String, List<OutCustomerDTO>> getEnableCustomers() {
		Map<String, List<OutCustomerDTO>> returnValue = new HashMap<>();
		String productId = null;
		Date now = new Date();
		// 获取所有通过额度审核的所有客户
		List<OutCustomerDTO> outCustomerDTOlist = creditApiImpl.getOutCustomerDTO(null);
		for (OutCustomerDTO outCustomerDTO : outCustomerDTOlist) {
			switch (Institution.valueOf(outCustomerDTO.getInstitutionId())) {
			case travelzen:
				productId = Constants.TRAVELZEN_FINANCE_PRODUCT_ID;
				break;
			default:
				break;
			}
			if(!returnValue.containsKey(productId))
				returnValue.put(productId, new ArrayList<OutCustomerDTO>());
			if(!earlywarningSystemEventGenerateRecordServiceImpl.isExistRecord(productId, outCustomerDTO.getMemberId(), outCustomerDTO.getOutCustomerId(), now))
				returnValue.get(productId).add(outCustomerDTO);
		}
		return returnValue;
	}
	
	/**
	 * 为每个未生成系统预警的客户生成预警信息
	 * @param memberId 融资商在cana平台的ID
	 * @param productId 所属的产品ID
	 * @param companyName 公司名称
	 */
	private void generateEarlyWarningSystemEvent(final String memberId, final String outCustomerId, final String productId, final String companyName) {
		transferThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date now = new Date();
					// 获取逾期数据
					QueryPlanListRequest queryPlanListRequest = new QueryPlanListRequest();
					queryPlanListRequest.setBusinessProductId(productId);
					queryPlanListRequest.setFinanceId(memberId);
					queryPlanListRequest.setOutCustomerId(outCustomerId);
					queryPlanListRequest.setStartDate(simpleDateFormat.format(DateUtils.addMonths(now, -EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.OVERDUEPLANS_MONTH))));
					queryPlanListRequest.setEndDate(simpleDateFormat.format(now));
					int overduePlans = financeReportApiImpl.countOverduePlans(queryPlanListRequest);
					int continueOverduePlans = financeReportApiImpl.countContinueOverduePlans(queryPlanListRequest);
					// 获取监控指标
					List<MonitorMetricData> monitorMetricDatas = reportApiImpl.queryMonitorMetricData(memberId, outCustomerId, productId, simpleDateFormat.format(DateUtils.addDays(now, -EarlyWarningProperties.getIntFromEarlyWarningProperties(EarlyWarningProperties.RED_SALESCHANGERATE_DAY))), simpleDateFormat.format(DateUtils.addDays(now, -1)));
					// 调用事务Service
					earlyWarningEventTransactionServiceImpl.reckonEarlyWarningSystemEvent(overduePlans, continueOverduePlans, monitorMetricDatas, memberId, outCustomerId, productId, companyName);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
	
}

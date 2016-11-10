package com.cana.flight.finance.scheduler.schedulers;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.common.dao.po.Properties;
import com.cana.flight.finance.service.constants.FlightFinanceConstant;
import com.cana.flight.finance.service.transaction.IFlightTicketTransactionService;
import com.cana.vbam.common.consts.ReportConstant;
import com.cana.vbam.common.service.IVbamCommonService;
import com.dianping.cat.Cat;

@Service
public class UpdateFlagScheduler {
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Resource
	private IFlightTicketTransactionService flightTicketTransactionServiceImpl;
	
	private  Logger logger = LoggerFactory.getLogger(getClass());
	
	@Scheduled(cron = "0 30 1-5 * * ?")
	public void doTask() {
		try {
			// 获取今日零时和昨日零时
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date today = calendar.getTime();
			
			Properties properties = vbamCommonServiceImpl.getProperties(FlightFinanceConstant.alterable_last_record_id);
			if(properties.getUpdateTime().before(today)) {
				
				long time = System.currentTimeMillis();
				
				String startRecordId = properties.getValue();
				vbamCommonServiceImpl.updateProperties(properties, flightTicketTransactionServiceImpl.updateFlag(startRecordId));
				
				// 更新监控开始位置
				Properties oldProperties = new Properties();
				oldProperties.setName(ReportConstant.LAST_ALTERALBE_LAST_RECORD_ID);
				vbamCommonServiceImpl.updateProperties(oldProperties, startRecordId);
				
				logger.info("完成客票标记更新，共耗时{}ms", System.currentTimeMillis() - time);
				Cat.logMetricForCount("更新客票标记成功");
			}
		} catch (Exception e) {
			Cat.logMetricForCount("更新客票标记失败");
			logger.error("更新客票标记失败", e);
		}
	}
	
}

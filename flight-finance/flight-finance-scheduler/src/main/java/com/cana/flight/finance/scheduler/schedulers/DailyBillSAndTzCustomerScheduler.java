package com.cana.flight.finance.scheduler.schedulers;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.common.dao.po.Properties;
import com.cana.flight.finance.common.dto.FlightTicket4DailyBillQueryCriteria;
import com.cana.flight.finance.dao.mapper.FlightTicketCustomMapper;
import com.cana.flight.finance.dao.po.FlightTicket4DailyBillPo;
import com.cana.flight.finance.dao.po.FlightTicket4TzCustomerInfoPo;
import com.cana.flight.finance.service.constants.FlightFinanceConstant;
import com.cana.flight.finance.service.transaction.IDailyBillAndTzCustomerService;
import com.cana.vbam.common.service.IVbamCommonService;

/**
 * 日报表和真旅客户更新依赖客票拉取这个定时任务，同时它也是计算真旅的监控数据的前提
 * @author cap
 *
 */
@Service
public class DailyBillSAndTzCustomerScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IVbamCommonService vbamCommonServiceImpl;
	
	@Resource
	private IDailyBillAndTzCustomerService dailyBillAndTzCustomerServiceImpl;
	
	@Resource
	private FlightTicketCustomMapper flightTicketCustomMapper;
		
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 10)
	public void produce() {
		logger.info("开始执行日报表、真旅客户更新器");
		long time = System.currentTimeMillis();
		Properties properties = vbamCommonServiceImpl.getProperties(FlightFinanceConstant.travelzen_bill_last_record_id);
		String lastRecordId = properties.getValue();
		FlightTicket4DailyBillQueryCriteria flightTicket4DailyBillQueryCriteria = new FlightTicket4DailyBillQueryCriteria();
		flightTicket4DailyBillQueryCriteria.setLimitSize(FlightFinanceConstant.travelzen_bill_record_size_once);
		List<FlightTicket4DailyBillPo> flightTicket4DailyBillPos;
		List<FlightTicket4TzCustomerInfoPo> flightTicket4TzCustomerInfoPos;
		do {
			long timePart = System.currentTimeMillis();
			flightTicket4DailyBillQueryCriteria.setStartRecordId(lastRecordId);
			lastRecordId = flightTicketCustomMapper.getCurrentLastRecordId(flightTicket4DailyBillQueryCriteria);
			if(lastRecordId == null)
				break;
			properties.setValue(lastRecordId);
			flightTicket4DailyBillQueryCriteria.setEndRecordId(lastRecordId);
			flightTicket4DailyBillPos = flightTicketCustomMapper.getBill(flightTicket4DailyBillQueryCriteria);
			flightTicket4TzCustomerInfoPos = flightTicketCustomMapper.getTzCustomer(flightTicket4DailyBillQueryCriteria);
			dailyBillAndTzCustomerServiceImpl.updateDailyBillAndTzCustomerInfo(flightTicket4DailyBillPos, flightTicket4TzCustomerInfoPos, properties);
			logger.info("本批耗时:{}ms", System.currentTimeMillis() - timePart);
		} while (flightTicket4DailyBillPos.size() != 0);
		logger.info("结束执行日报表、真旅客户更新器，共耗时:{}ms", System.currentTimeMillis() - time);
	}

}

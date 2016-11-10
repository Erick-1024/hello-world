package com.cana.flight.finance.service.transaction.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.flight.finance.common.util.Constants;
import com.cana.flight.finance.dao.mapper.FlightTicketCustomMapper;
import com.cana.flight.finance.dao.mapper.gen.DailyBillMapper;
import com.cana.flight.finance.dao.mapper.gen.FlightTicketMapper;
import com.cana.flight.finance.dao.mapper.gen.TzCustomerInfoMapper;
import com.cana.flight.finance.dao.po.DailyBill;
import com.cana.flight.finance.dao.po.FlightTicket4DailyBillPo;
import com.cana.flight.finance.dao.po.FlightTicket4TzCustomerInfoPo;
import com.cana.flight.finance.dao.po.FlightTicketExample;
import com.cana.flight.finance.dao.po.TzCustomerInfo;
import com.cana.flight.finance.dao.po.TzCustomerInfoExample;
import com.cana.flight.finance.dao.utils.IDGenerator;
import com.cana.flight.finance.service.transaction.IDailyBillAndTzCustomerService;

@Service
public class DailyBillAndTzCustomerServiceImpl implements IDailyBillAndTzCustomerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Resource
	private DailyBillMapper dailyBillMapper;
	
	@Resource
	private FlightTicketCustomMapper flightTicketCustomMapper;
	
	@Resource
	private TzCustomerInfoMapper tzCustomerInfoMapper;
	
	@Resource
	private FlightTicketMapper flightTicketMapper;
	

	@Override
	public void updateDailyBillAndTzCustomerInfo(List<FlightTicket4DailyBillPo> flightTicket4DailyBillPos, List<FlightTicket4TzCustomerInfoPo> flightTicket4TzCustomerInfoPos, Properties properties) {
		updateDailyBill(flightTicket4DailyBillPos);
		updateTzCustomerInfo(flightTicket4TzCustomerInfoPos);
		properties.setUpdateTime(new Date());
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

	private void updateDailyBill(List<FlightTicket4DailyBillPo> flightTicket4DailyBillPos) {
		logger.info("开始更新日报表");
		long time = System.currentTimeMillis();
		Iterator<FlightTicket4DailyBillPo> flightTicket4DailyBillPoIterator = flightTicket4DailyBillPos.iterator();
		String idPrefix = IDGenerator.generateDailyBillIdPrefix();
		int i = 0;
		while (flightTicket4DailyBillPoIterator.hasNext()) {
			FlightTicket4DailyBillPo flightTicket4DailyBillPo = flightTicket4DailyBillPoIterator.next();
			String id = flightTicket4DailyBillPo.getId();
			String date = flightTicket4DailyBillPo.getDate();
			Long newPrice = flightTicket4DailyBillPo.getNewPrice();
			Long oldPrice = flightTicket4DailyBillPo.getOldPrice();
			String customerId = flightTicket4DailyBillPo.getCustomerId();
			DailyBill dailyBill = new DailyBill();;
			if(id == null) {
				dailyBill.setId(IDGenerator.generateDailyBillId(idPrefix, ++i));
				dailyBill.setPrice(newPrice);
				dailyBill.setCustomerId(customerId);
				dailyBill.setDate(date);
				dailyBill.setCreateTime(new Date());
				dailyBill.setUpdateTime(dailyBill.getCreateTime());
				dailyBillMapper.insert(dailyBill);
			} else {
				dailyBill.setId(id);
				dailyBill.setPrice(oldPrice + newPrice);
				dailyBill.setUpdateTime(new Date());
				dailyBillMapper.updateByPrimaryKeySelective(dailyBill);
			}
		}
		logger.info("日报表耗时:{}ms", System.currentTimeMillis() - time);
	}
	
	private void updateTzCustomerInfo(List<FlightTicket4TzCustomerInfoPo> flightTicket4TzCustomerInfoPos) {
		logger.info("开始更新真旅客户表");
		long time = System.currentTimeMillis();
		Iterator<FlightTicket4TzCustomerInfoPo> flightTicket4TzCustomerInfoPoIterator = flightTicket4TzCustomerInfoPos.iterator();
		Map<String, String> newcustomerIds = new HashMap<>();
		while (flightTicket4TzCustomerInfoPoIterator.hasNext()) {
			FlightTicket4TzCustomerInfoPo flightTicket4TzCustomerInfoPo = flightTicket4TzCustomerInfoPoIterator.next();
			String customerId = flightTicket4TzCustomerInfoPo.getTzCustomerId();
			String newName = flightTicket4TzCustomerInfoPo.getNewName();
			Integer tzShortId = flightTicket4TzCustomerInfoPo.getTzShortId();
			String oldName = flightTicket4TzCustomerInfoPo.getOldName();
			boolean isUpdate = tzShortId != null;
			if(isUpdate) {
				List<String> oldNames = new ArrayList<>(Arrays.asList(oldName.split(Constants.SEPARATOR_CUSTOMER_NAME_REGEX)));
				if(!oldNames.contains(newName)) {
					if(newcustomerIds.containsKey(customerId)) {
						oldName = oldName + Constants.SEPARATOR_CUSTOMER_NAME + newcustomerIds.get(customerId);
						newcustomerIds.put(customerId, oldName + Constants.SEPARATOR_CUSTOMER_NAME + newName);
					} else
						newcustomerIds.put(customerId, newName);
					TzCustomerInfo tzCustomerInfo = new TzCustomerInfo();
					tzCustomerInfo.setTzShortId(tzShortId);
					tzCustomerInfo.setCustomerNames(oldName + Constants.SEPARATOR_CUSTOMER_NAME + newName);
					tzCustomerInfoMapper.updateByPrimaryKeySelective(tzCustomerInfo);
				}
			} else {
				TzCustomerInfo tzCustomerInfo = new TzCustomerInfo();
				tzCustomerInfo.setTzCustomerId(customerId);
				if(newcustomerIds.containsKey(customerId)) {
					newcustomerIds.put(customerId, newcustomerIds.get(customerId) + Constants.SEPARATOR_CUSTOMER_NAME + newName);
					tzCustomerInfo.setCustomerNames(newcustomerIds.get(customerId));
					TzCustomerInfoExample tzCustomerInfoExample = new TzCustomerInfoExample();
					tzCustomerInfoExample.createCriteria().andTzCustomerIdEqualTo(customerId);
					tzCustomerInfoMapper.updateByExampleSelective(tzCustomerInfo, tzCustomerInfoExample);
				} else {
					newcustomerIds.put(customerId, newName);
					tzCustomerInfo.setCustomerNames(newName);
					FlightTicketExample flightTicketExample = new FlightTicketExample();
					flightTicketExample.createCriteria().andCustomerIdEqualTo(customerId);
					flightTicketExample.setOrderByClause("complete_Issue_time");
					flightTicketExample.setLimitStart(0);
					flightTicketExample.setLimitEnd(1);
					tzCustomerInfo.setFirstBusinessTime(flightTicketMapper.selectByExample(flightTicketExample).get(0).getCompleteIssueTime().substring(0,10));
					tzCustomerInfoMapper.insert(tzCustomerInfo);
				}
			}
		}
		logger.info("真旅客户表耗时:{}ms", System.currentTimeMillis() - time);
	}
	
}

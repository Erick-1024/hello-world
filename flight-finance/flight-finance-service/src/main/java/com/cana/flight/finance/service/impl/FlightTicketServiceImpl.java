package com.cana.flight.finance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.flight.finance.common.enums.NoValueCause;
import com.cana.flight.finance.dao.mapper.gen.FlightTicketMapper;
import com.cana.flight.finance.dao.po.FlightTicket;
import com.cana.flight.finance.dao.po.FlightTicketExample;
import com.cana.flight.finance.service.IFlightTicketService;
import com.cana.vbam.common.report.dto.MonitorMoneyDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class FlightTicketServiceImpl implements IFlightTicketService {

	@Resource
	private FlightTicketMapper flightTicketMapper;
	
	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Override
	public Map<String, Long> getQualifiedAR(List<String> customerIds, String startRecordId) {
		FlightTicketExample flightTicketExample = new FlightTicketExample();
		flightTicketExample.createCriteria().andRecordIdGreaterThanOrEqualTo(startRecordId).andCompleteIssueTimeLessThan(DateTimeUtil.date10()).andIsValuableEqualTo(Boolean.TRUE).andCustomerIdIn(customerIds);
		List<FlightTicket> flightTickets = flightTicketMapper.selectByExample(flightTicketExample);
		Map<String, Long> returnValue = new HashMap<>();
		for (FlightTicket flightTicket : flightTickets) {
			String customerId = flightTicket.getCustomerId();
			int departureDateTimes = flightTicket.getDepartureDateTime().split(",").length;
			putValue(returnValue, customerId, (flightTicket.getTicketPrice() + flightTicket.getTotalTax()) / departureDateTimes * flightTicket.getNoTakeOffNumber());
		}
		return returnValue;
	}

	@Override
	public Map<String, Long> getFlightTakeOffSales(List<String> customerIds, String date10, String startRecordId) {
		FlightTicketExample flightTicketExample = new FlightTicketExample();
		flightTicketExample.createCriteria().andRecordIdGreaterThanOrEqualTo(startRecordId).andCompleteIssueTimeLessThan(DateTimeUtil.date10()).andDepartureDateTimeLike("%" + date10 + "%").andNoValueCauseIsNull().andCustomerIdIn(customerIds);
		flightTicketExample.or(flightTicketExample.createCriteria().andRecordIdGreaterThanOrEqualTo(startRecordId).andCompleteIssueTimeLessThan(DateTimeUtil.date10()).andDepartureDateTimeLike("%" + date10 + "%").andNoValueCauseEqualTo(NoValueCause.TAKE_OFF.name()).andCustomerIdIn(customerIds));
		List<FlightTicket> flightTickets = flightTicketMapper.selectByExample(flightTicketExample);
		Map<String, Long> returnValue = new HashMap<>();
		for (FlightTicket flightTicket : flightTickets) {
			String customerId = flightTicket.getCustomerId();
			String[] departureDateTimes = flightTicket.getDepartureDateTime().split(",");
			int departureDateTimesNumber = departureDateTimes.length;
			int todayTakeOffNumber = 0;
			for (String departureDateTime : departureDateTimes)
				if(departureDateTime.contains(date10))
					++ todayTakeOffNumber;
			putValue(returnValue, customerId,  (flightTicket.getTicketPrice() + flightTicket.getTotalTax()) / departureDateTimesNumber * todayTakeOffNumber);
		}
		return returnValue;
	}

	@Override
	public List<MonitorMoneyDTO> getTakeOffSales(Map<String, String>  outCustomerId2MemberIdMap, String date10, String startRecordId) {
		List<String> customerIds = new ArrayList<>(outCustomerId2MemberIdMap.keySet());
		FlightTicketExample flightTicketExample = new FlightTicketExample();
		flightTicketExample.createCriteria().andRecordIdGreaterThanOrEqualTo(startRecordId).andCompleteIssueTimeLessThan(DateTimeUtil.date10()).andDepartureDateTimeLike("%" + date10 + "%").andNoValueCauseIsNull().andCustomerIdIn(customerIds);
		flightTicketExample.or(flightTicketExample.createCriteria().andRecordIdGreaterThanOrEqualTo(startRecordId).andCompleteIssueTimeLessThan(DateTimeUtil.date10()).andDepartureDateTimeLike("%" + date10 + "%").andNoValueCauseEqualTo(NoValueCause.TAKE_OFF.name()).andCustomerIdIn(customerIds));
		List<FlightTicket> flightTickets = flightTicketMapper.selectByExample(flightTicketExample);
		Map<String, Long> tmpMap = new HashMap<>();
		List<MonitorMoneyDTO> returnValue = new ArrayList<>();
		for (FlightTicket flightTicket : flightTickets) {
			String customerId = flightTicket.getCustomerId();
			String[] departureDateTimes = flightTicket.getDepartureDateTime().split(",");
			int todayTakeOffNumber = 0;
			for (String departureDateTime : departureDateTimes)
				if(departureDateTime.contains(date10))
					++ todayTakeOffNumber;
			putValue(tmpMap, customerId,  (flightTicket.getTicketPrice() + flightTicket.getTotalTax()) / departureDateTimes.length * todayTakeOffNumber);
		}
		for (String customerId : customerIds) {
			MonitorMoneyDTO monitorMoneyDTO = new MonitorMoneyDTO();
			monitorMoneyDTO.setOutCustomerId(customerId);
			monitorMoneyDTO.setMemberId(outCustomerId2MemberIdMap.get(customerId));
			monitorMoneyDTO.setPrice(tmpMap.get(customerId));
			returnValue.add(monitorMoneyDTO);
		}
		return returnValue;
	}
	
	private void putValue(Map<String, Long> map, String id, Long price) {
		if(map.containsKey(id))
			map.put(id, map.get(id) + price);
		else
			map.put(id, price);
	}
	
}

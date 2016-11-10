package com.cana.flight.finance.dao.mapper;

import com.cana.flight.finance.common.dto.FlightTicket4DailyBillQueryCriteria;
import com.cana.flight.finance.dao.po.FlightTicket;
import com.cana.flight.finance.dao.po.FlightTicket4DailyBillPo;
import com.cana.flight.finance.dao.po.FlightTicket4TzCustomerInfoPo;

import java.util.List;

public interface FlightTicketCustomMapper {
	
	List<FlightTicket4DailyBillPo> getBill(FlightTicket4DailyBillQueryCriteria flightTicket4DailyBillQueryCriteria);
	
	List<FlightTicket4TzCustomerInfoPo> getTzCustomer(FlightTicket4DailyBillQueryCriteria flightTicket4DailyBillQueryCriteria);
	
	String getCurrentLastRecordId(FlightTicket4DailyBillQueryCriteria flightTicket4DailyBillQueryCriteria);
	
	int insertFlightTicketByBatch(List<FlightTicket> flightTickets);
	
}
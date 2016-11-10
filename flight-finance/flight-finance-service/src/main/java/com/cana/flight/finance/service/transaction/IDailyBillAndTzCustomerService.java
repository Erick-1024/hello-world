package com.cana.flight.finance.service.transaction;

import java.util.List;

import com.cana.common.dao.po.Properties;
import com.cana.flight.finance.dao.po.FlightTicket4DailyBillPo;
import com.cana.flight.finance.dao.po.FlightTicket4TzCustomerInfoPo;

public interface IDailyBillAndTzCustomerService {

	public void updateDailyBillAndTzCustomerInfo(List<FlightTicket4DailyBillPo> flightTicket4DailyBillPos, List<FlightTicket4TzCustomerInfoPo> flightTicket4TzCustomerInfoPos, Properties properties);
	
}

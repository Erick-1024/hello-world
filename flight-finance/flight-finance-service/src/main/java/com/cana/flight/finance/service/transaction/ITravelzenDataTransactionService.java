/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.flight.finance.service.transaction;

import java.util.List;

import com.cana.flight.finance.common.dto.FlightTicketDTO;
import com.cana.flight.finance.common.dto.RepaymentDTO;

/**
 * @author ducer
 *
 */
public interface ITravelzenDataTransactionService {

	public void saveFlightTickets(List<FlightTicketDTO> ticketDTOs);
	
	public void saveRepayments(List<RepaymentDTO> repaymentDTOs);
	
	public String getLastRecordId(String key);
}

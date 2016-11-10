package com.cana.flight.finance.common.dto;

import java.util.List;

public class TravelzenFlightTicketResponse extends TravelzenBaseResponse {

	private static final long serialVersionUID = 1L;
	
	private List<FlightTicketDTO> tickets;

	public List<FlightTicketDTO> getTickets() {
		return tickets;
	}

	public void setTickets(List<FlightTicketDTO> tickets) {
		this.tickets = tickets;
	}

}

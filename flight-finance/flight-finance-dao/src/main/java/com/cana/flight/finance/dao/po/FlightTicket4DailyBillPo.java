package com.cana.flight.finance.dao.po;

import java.io.Serializable;

public class FlightTicket4DailyBillPo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerId;
	
	private String date;
	
	private Long newPrice;
	
	private Long oldPrice;
	
	private String id;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Long newPrice) {
		this.newPrice = newPrice;
	}

	public Long getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Long oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
}

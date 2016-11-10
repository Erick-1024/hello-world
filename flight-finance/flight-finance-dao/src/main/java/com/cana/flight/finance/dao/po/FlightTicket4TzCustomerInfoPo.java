package com.cana.flight.finance.dao.po;

import java.io.Serializable;

public class FlightTicket4TzCustomerInfoPo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String tzCustomerId;
	
	private String newName;
	
	private Integer tzShortId;
	
	private String oldName;

	public String getTzCustomerId() {
		return tzCustomerId;
	}

	public void setTzCustomerId(String tzCustomerId) {
		this.tzCustomerId = tzCustomerId;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public Integer getTzShortId() {
		return tzShortId;
	}

	public void setTzShortId(Integer tzShortId) {
		this.tzShortId = tzShortId;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

}

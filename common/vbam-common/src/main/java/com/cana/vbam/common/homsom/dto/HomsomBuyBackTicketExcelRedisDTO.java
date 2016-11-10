package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;
import java.util.Map;

public class HomsomBuyBackTicketExcelRedisDTO implements Serializable {

	private static final long serialVersionUID = 5687454547253262674L;

	private Map<String, HomsomBuyBackTicketExcelDTO> passMap;
	
	private Map<String, HomsomBuyBackTicketExcelDTO> unPassMap;

	public Map<String, HomsomBuyBackTicketExcelDTO> getPassMap() {
		return passMap;
	}

	public void setPassMap(Map<String, HomsomBuyBackTicketExcelDTO> passMap) {
		this.passMap = passMap;
	}

	public Map<String, HomsomBuyBackTicketExcelDTO> getUnPassMap() {
		return unPassMap;
	}

	public void setUnPassMap(Map<String, HomsomBuyBackTicketExcelDTO> unPassMap) {
		this.unPassMap = unPassMap;
	}
	
}

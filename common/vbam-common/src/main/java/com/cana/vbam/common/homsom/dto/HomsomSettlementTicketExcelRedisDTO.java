package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;
import java.util.Map;

public class HomsomSettlementTicketExcelRedisDTO implements Serializable {

	private static final long serialVersionUID = -2514266393660322266L;

	private Map<String, HomsomSettlementTicketExcelDTO> passMap;
	
	private Map<String, HomsomSettlementTicketExcelDTO> unPassMap;

	public Map<String, HomsomSettlementTicketExcelDTO> getPassMap() {
		return passMap;
	}

	public void setPassMap(Map<String, HomsomSettlementTicketExcelDTO> passMap) {
		this.passMap = passMap;
	}

	public Map<String, HomsomSettlementTicketExcelDTO> getUnPassMap() {
		return unPassMap;
	}

	public void setUnPassMap(Map<String, HomsomSettlementTicketExcelDTO> unPassMap) {
		this.unPassMap = unPassMap;
	}
	
}

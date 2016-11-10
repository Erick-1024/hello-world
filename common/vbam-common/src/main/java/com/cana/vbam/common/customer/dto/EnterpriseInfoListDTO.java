package com.cana.vbam.common.customer.dto;

import java.io.Serializable;
import java.util.List;

public class EnterpriseInfoListDTO implements Serializable{
	
	private static final long serialVersionUID = -4084553630270081510L;
	
	private String customerId;

	private List<EnterpriseInfoDTO> infoList;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<EnterpriseInfoDTO> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<EnterpriseInfoDTO> infoList) {
		this.infoList = infoList;
	}
	
	
}

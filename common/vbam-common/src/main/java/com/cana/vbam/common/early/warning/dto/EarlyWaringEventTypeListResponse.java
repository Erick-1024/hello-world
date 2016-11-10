package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.List;

public class EarlyWaringEventTypeListResponse implements Serializable {

	private static final long serialVersionUID = -6224318765118665183L;

	private String memberId;
	
	private String companyName;
	
	private String outCustomerId;
	
	private String outCustomerName;
	
	private String productId;
	
	private String earlywaringLevel;
	
	private String eralywaringLevelDesc;
	
	List<EarlyWaringEventTypeDTO> earlyWaringEventTypeDTOs;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEarlywaringLevel() {
		return earlywaringLevel;
	}

	public void setEarlywaringLevel(String earlywaringLevel) {
		this.earlywaringLevel = earlywaringLevel;
	}

	public String getEralywaringLevelDesc() {
		return eralywaringLevelDesc;
	}

	public void setEralywaringLevelDesc(String eralywaringLevelDesc) {
		this.eralywaringLevelDesc = eralywaringLevelDesc;
	}

	public List<EarlyWaringEventTypeDTO> getEarlyWaringEventTypeDTOs() {
		return earlyWaringEventTypeDTOs;
	}

	public void setEarlyWaringEventTypeDTOs(List<EarlyWaringEventTypeDTO> earlyWaringEventTypeDTOs) {
		this.earlyWaringEventTypeDTOs = earlyWaringEventTypeDTOs;
	}
	
}

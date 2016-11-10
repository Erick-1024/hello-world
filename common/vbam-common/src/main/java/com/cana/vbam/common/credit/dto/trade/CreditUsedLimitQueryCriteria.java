package com.cana.vbam.common.credit.dto.trade;

import java.io.Serializable;
import java.util.Date;

public class CreditUsedLimitQueryCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	
	private Date endDate;
	
	private String institution;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
}

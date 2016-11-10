package com.cana.yundaex.common.dto;

import java.io.Serializable;
import java.util.List;

public class InterestRateListDTO implements Serializable {

	private static final long serialVersionUID = -2575683502442131774L;

	private List<InterestRateDTO> interestRates;

	public List<InterestRateDTO> getInterestRates() {
		return interestRates;
	}

	public void setInterestRates(List<InterestRateDTO> interestRates) {
		this.interestRates = interestRates;
	}

}

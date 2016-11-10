package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

public class EarlyWarningEventReviewDTO implements Serializable {

	private static final long serialVersionUID = -2462451037637489499L;

	/**
     *附加数据
     */
    private String extraData;

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
	
}

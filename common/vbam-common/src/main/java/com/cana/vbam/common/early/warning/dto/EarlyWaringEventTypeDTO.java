package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.Date;

public class EarlyWaringEventTypeDTO implements Serializable {

	private static final long serialVersionUID = 3351642091519616240L;

	private String earlywaringEventType;
	
	private int number;
	
	private Date updateTime;

	public String getEarlywaringEventType() {
		return earlywaringEventType;
	}

	public void setEarlywaringEventType(String earlywaringEventType) {
		this.earlywaringEventType = earlywaringEventType;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

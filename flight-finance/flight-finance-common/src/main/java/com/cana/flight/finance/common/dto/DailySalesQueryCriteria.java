package com.cana.flight.finance.common.dto;

import java.io.Serializable;
import java.util.List;

public class DailySalesQueryCriteria implements Serializable{

	private static final long serialVersionUID = 2233406695617956970L;

	// 开始月份，包含此月。格式：yyyy-MM
	private String startMonth;
	
	// 结束月份，包含此月。格式：yyyy-MM
	private String endMonth;
	
	// 客户ID列表
	private List<String> customerIds;
	
	// 天数
	private int dayNumber;

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public List<String> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}
	
}

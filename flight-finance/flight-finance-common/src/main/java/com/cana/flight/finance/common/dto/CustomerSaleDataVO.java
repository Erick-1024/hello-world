package com.cana.flight.finance.common.dto;

import java.io.Serializable;

public class CustomerSaleDataVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer year;//年份
    
    private Integer month;//月份

    private long saleMoney;//销售额（单位分）

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public long getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(long saleMoney) {
		this.saleMoney = saleMoney;
	}
}

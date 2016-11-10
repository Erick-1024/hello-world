package com.cana.vbam.common.credit.dto.apply;

import java.io.Serializable;

public class CustomerSaleDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer year;//年份
    
    private Integer month;//月份

    private String saleMoneyStr;//销售额（单位元）
    
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

	public String getSaleMoneyStr() {
		return saleMoneyStr;
	}

	public void setSaleMoneyStr(String saleMoneyStr) {
		this.saleMoneyStr = saleMoneyStr;
	}


}

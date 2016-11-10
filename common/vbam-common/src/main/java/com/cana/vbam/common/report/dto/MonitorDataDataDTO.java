package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorDataDataDTO extends MonitorBaseData implements Serializable {

	private static final long serialVersionUID = 1L;

	// 合格AR余额
	private String qualifiedAR;
	
	// 机票销售总金额
	private String ticketAllSales;
	
	// 机票回款总金额
	private String ticketRepayment;
	
	// 机票已起飞金额
	private String ticketTakeOffSale;
	
	// 当日结束之前已使用额度
	private String usedLimitTheDay;

	public String getQualifiedAR() {
		return qualifiedAR;
	}

	public void setQualifiedAR(String qualifiedAR) {
		this.qualifiedAR = qualifiedAR;
	}

	public String getTicketAllSales() {
		return ticketAllSales;
	}

	public void setTicketAllSales(String ticketAllSales) {
		this.ticketAllSales = ticketAllSales;
	}

	public String getTicketRepayment() {
		return ticketRepayment;
	}

	public void setTicketRepayment(String ticketRepayment) {
		this.ticketRepayment = ticketRepayment;
	}

	public String getTicketTakeOffSale() {
		return ticketTakeOffSale;
	}

	public void setTicketTakeOffSale(String ticketTakeOffSale) {
		this.ticketTakeOffSale = ticketTakeOffSale;
	}

	public String getUsedLimitTheDay() {
		return usedLimitTheDay;
	}

	public void setUsedLimitTheDay(String usedLimitTheDay) {
		this.usedLimitTheDay = usedLimitTheDay;
	}
	
}

package com.cana.vbam.common.report.dto;

import java.io.Serializable;

public class MonitorDataData extends MonitorBaseData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// 合格AR余额
	private Long qualifiedAR;
	
	// 机票销售总金额
	private Long ticketAllSales;
	
	// 机票回款总金额
	private Long ticketRepayment;
	
	// 机票已起飞金额
	private Long ticketTakeOffSale;
	
	// 当日结束之前已使用额度
	private Long usedLimitTheDay;

	public Long getQualifiedAR() {
		return qualifiedAR;
	}

	public void setQualifiedAR(Long qualifiedAR) {
		this.qualifiedAR = qualifiedAR;
	}

	public Long getTicketAllSales() {
		return ticketAllSales;
	}

	public void setTicketAllSales(Long ticketAllSales) {
		this.ticketAllSales = ticketAllSales;
	}

	public Long getTicketRepayment() {
		return ticketRepayment;
	}

	public void setTicketRepayment(Long ticketRepayment) {
		this.ticketRepayment = ticketRepayment;
	}

	public Long getTicketTakeOffSale() {
		return ticketTakeOffSale;
	}

	public void setTicketTakeOffSale(Long ticketTakeOffSale) {
		this.ticketTakeOffSale = ticketTakeOffSale;
	}

	public Long getUsedLimitTheDay() {
		return usedLimitTheDay;
	}

	public void setUsedLimitTheDay(Long usedLimitTheDay) {
		this.usedLimitTheDay = usedLimitTheDay;
	}
	
}

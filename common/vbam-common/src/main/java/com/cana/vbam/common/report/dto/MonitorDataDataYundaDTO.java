package com.cana.vbam.common.report.dto;
import java.io.Serializable;


/**
 * 监控详情数据
 * @author jiangzhou.Ren
 * @time 2016年9月27日下午2:00:27
 */
public class MonitorDataDataYundaDTO extends MonitorBaseData implements Serializable{

	private static final long serialVersionUID = 7495843988510673628L;
	
	//保证金余额
	private String bailBalance;
	
	//净现金流增长量
	private String netCashflowGrowth;
	
	//最大授信金额
	private String creditLimit;
	
	//逾期次数
	private String overdues;

	public String getBailBalance() {
		return bailBalance;
	}

	public void setBailBalance(String bailBalance) {
		this.bailBalance = bailBalance;
	}

	public String getNetCashflowGrowth() {
		return netCashflowGrowth;
	}

	public void setNetCashflowGrowth(String netCashflowGrowth) {
		this.netCashflowGrowth = netCashflowGrowth;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getOverdues() {
		return overdues;
	}

	public void setOverdues(String overdues) {
		this.overdues = overdues;
	}
	
	
}

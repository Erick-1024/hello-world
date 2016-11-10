package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentDetailsHistoryDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7076558928744261594L;

	/**
	 * 操作时间
	 */
	private String operatingTime;
	
	/**
	 * 还款方式
	 */
	private String repaymentMethod;
	
	/**
	 * 金额明细
	 */
	private String amountDetails;
	
	/**
	 * 线下还款时间
	 */
	private String offlineTime;

	
	public String getOperatingTime()
	{
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime)
	{
		this.operatingTime = operatingTime;
	}

	public String getRepaymentMethod()
	{
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod)
	{
		this.repaymentMethod = repaymentMethod;
	}

	public String getAmountDetails()
	{
		return amountDetails;
	}

	public void setAmountDetails(String amountDetails)
	{
		this.amountDetails = amountDetails;
	}

	public String getOfflineTime()
	{
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime)
	{
		this.offlineTime = offlineTime;
	}
}

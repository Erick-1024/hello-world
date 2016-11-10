/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentExpenseDBDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5513664843009159139L;
	
	/**
	 * 主键
	 */
	private String id;

	/**
    * 放款编号
    */
    private String loanNo;
    
    /**
    *费用明目
    */
    private String expenseSubject;

    /**
    *计费方式
    */
    private String chargeMethod;
    
    /**
    *计费基准（融资余额、保理余额、其他）
    */
    private String chargeStandard = "";
    
    /**
    *计费值
    */
    private String chargeValue = "";
    
    /**
    *还款日
    */
    private String repaymentDate;
    
    /**
    *已还正常金额
    */
    private String paidNormalAmount;
    
    /**
    *应还金额
    */
    private String repaymentAmount;
    
    /**
    *结清状态（已结清、未结清、部分结清）
    */
    private String settleStatus;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getLoanNo()
	{
		return loanNo;
	}

	public void setLoanNo(String loanNo)
	{
		this.loanNo = loanNo;
	}

	public String getExpenseSubject()
	{
		return expenseSubject;
	}

	public void setExpenseSubject(String expenseSubject)
	{
		this.expenseSubject = expenseSubject;
	}

	public String getChargeMethod()
	{
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod)
	{
		this.chargeMethod = chargeMethod;
	}

	public String getChargeStandard()
	{
		return chargeStandard;
	}

	public void setChargeStandard(String chargeStandard)
	{
		this.chargeStandard = chargeStandard;
	}

	public String getChargeValue()
	{
		return chargeValue;
	}

	public void setChargeValue(String chargeValue)
	{
		this.chargeValue = chargeValue;
	}

	public String getRepaymentDate()
	{
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate)
	{
		this.repaymentDate = repaymentDate;
	}

	public String getPaidNormalAmount()
	{
		return paidNormalAmount;
	}

	public void setPaidNormalAmount(String paidNormalAmount)
	{
		this.paidNormalAmount = paidNormalAmount;
	}

	public String getRepaymentAmount()
	{
		return repaymentAmount;
	}

	public void setRepaymentAmount(String repaymentAmount)
	{
		this.repaymentAmount = repaymentAmount;
	}

	public String getSettleStatus()
	{
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus)
	{
		this.settleStatus = settleStatus;
	}
}
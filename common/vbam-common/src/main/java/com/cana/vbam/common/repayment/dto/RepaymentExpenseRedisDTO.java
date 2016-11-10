/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentExpenseRedisDTO implements Serializable {
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
     * 放款信息Id
     */
    private String loanInfoId;
    
    /**
    *还款信息Id
    */
    private String repaymentInfoId;

    /**
    *融资客户Id
    */
    private String financeId;

    /**
    *融资客户公司名称
    */
    private String financeCompany;

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
    private String chargeStandard;

    /**
    *计费比率（计费方式选择比率情况使用）
    */
    private String chargeRatio;

    /**
    *计费定额（当选择计费方式是定额情况使用）
    */
    private String chargeAmount;

    /**
    *应还金额
    */
    private String repaymentAmount;

    /**
    *还款日
    */
    private String repaymentDate;

    /**
    *结清状态（已结清、未结清、部分结清）
    */
    private String settleStatus;

    /**
    *校验状态
    */
    private String verifyStatus;

    /**
    *检验未通过原因
    */
    private String verifyFailReason;

    /**
    *业务模式（保理商+融资商等）
    */
    private String businessMode;
    
    /**
     * 是否可编辑
     */
    private String editAble;

    /**
    *录入方式（excal、手动）
    */
    private String inputMethod;
    
    /**
     * 核心企业Id
     */
    private String coreCompanyId;
    /**
     * 核心企业
     */
    private String coreCompanyName;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
     * 放款编号
     */
    public String getLoanNo() {
		return loanNo;
	}
    
    /**
     * 放款编号
     */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	/**
    *还款信息Id
    */
    public String getRepaymentInfoId() {
        return repaymentInfoId;
    }

    /**
    *还款信息Id
    */
    public void setRepaymentInfoId(String repaymentInfoId) {
        this.repaymentInfoId = repaymentInfoId == null ? null : repaymentInfoId.trim();
    }

    /**
    *融资客户Id
    */
    public String getFinanceId() {
        return financeId;
    }

    /**
    *融资客户Id
    */
    public void setFinanceId(String financeId) {
        this.financeId = financeId == null ? null : financeId.trim();
    }

    /**
    *融资客户公司名称
    */
    public String getFinanceCompany() {
        return financeCompany;
    }

    /**
    *融资客户公司名称
    */
    public void setFinanceCompany(String financeCompany) {
        this.financeCompany = financeCompany == null ? null : financeCompany.trim();
    }

    /**
    *费用明目
    */
    public String getExpenseSubject() {
        return expenseSubject;
    }

    /**
    *费用明目
    */
    public void setExpenseSubject(String expenseSubject) {
        this.expenseSubject = expenseSubject == null ? null : expenseSubject.trim();
    }

    /**
    *计费方式
    */
    public String getChargeMethod() {
        return chargeMethod;
    }

    /**
    *计费方式
    */
    public void setChargeMethod(String chargeMethod) {
        this.chargeMethod = chargeMethod == null ? null : chargeMethod.trim();
    }

    /**
    *计费基准（融资余额、保理余额、其他）
    */
    public String getChargeStandard() {
        return chargeStandard;
    }

    /**
    *计费基准（融资余额、保理余额、其他）
    */
    public void setChargeStandard(String chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    /**
    *计费比率（计费方式选择比率情况使用）
    */
    public String getChargeRatio() {
        return chargeRatio;
    }

    /**
    *计费比率（计费方式选择比率情况使用）
    */
    public void setChargeRatio(String chargeRatio) {
        this.chargeRatio = chargeRatio;
    }

    /**
    *计费定额（当选择计费方式是定额情况使用）
    */
    public String getChargeAmount() {
        return chargeAmount;
    }

    /**
    *计费定额（当选择计费方式是定额情况使用）
    */
    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    /**
    *应还金额
    */
    public String getRepaymentAmount() {
        return repaymentAmount;
    }

    /**
    *应还金额
    */
    public void setRepaymentAmount(String repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    /**
    *还款日
    */
    public String getRepaymentDate() {
        return repaymentDate;
    }

    /**
    *还款日
    */
    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate == null ? null : repaymentDate.trim();
    }

    /**
    *结清状态（已结清、未结清）
    */
    public String getSettleStatus() {
        return settleStatus;
    }

    /**
    *结清状态（已结清、未结清）
    */
    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus == null ? null : settleStatus.trim();
    }

    /**
    *校验状态
    */
    public String getVerifyStatus() {
        return verifyStatus;
    }

    /**
    *校验状态
    */
    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus == null ? null : verifyStatus.trim();
    }

    /**
    *检验未通过原因
    */
    public String getVerifyFailReason() {
        return verifyFailReason;
    }

    /**
    *检验未通过原因
    */
    public void setVerifyFailReason(String verifyFailReason) {
        this.verifyFailReason = verifyFailReason == null ? null : verifyFailReason.trim();
    }

    /**
    *业务模式（保理商+融资商等）
    */
    public String getBusinessMode() {
        return businessMode;
    }

    /**
    *业务模式（保理商+融资商等）
    */
    public void setBusinessMode(String businessMode) {
        this.businessMode = businessMode == null ? null : businessMode.trim();
    }

    /**
    *录入方式（excal、手动）
    */
    public String getInputMethod() {
        return inputMethod;
    }

    /**
    *录入方式（excal、手动）
    */
    public void setInputMethod(String inputMethod) {
        this.inputMethod = inputMethod == null ? null : inputMethod.trim();
    }

	public String getEditAble() {
		return editAble;
	}

	public void setEditAble(String editAble) {
		this.editAble = editAble;
	}

	public String getCoreCompanyName() {
		return coreCompanyName;
	}

	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
	}

	public String getCoreCompanyId() {
		return coreCompanyId;
	}

	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepaymentExpenseRedisDTO other = (RepaymentExpenseRedisDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
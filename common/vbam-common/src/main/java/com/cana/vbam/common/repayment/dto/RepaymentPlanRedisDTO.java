/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;

public class RepaymentPlanRedisDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4112898975222095222L;

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
    *业务模型（保理商+融资客户监管等）
    */
    private String businessModel;
    
    /**
    * 业务合同号
    */
    private String businessContractNo;

    /**
    *信息录入方式
    */
    private String inputMethod;

    /**
    *期数
    */
    private String repaymentPeriod;

    /**
    *融资客户Id
    */
    private String financeId;

    /**
    *融资客户公司名称
    */
    private String financeCompany;

    /**
    *融资金额
    */
    private String financeAmount;

    /**
    *融资余额
    */
    private String financeBalance;
    
    /**
     * 
     */
    private String loanDate;
    
    /**
    *起息日
    */
    private String valueDate;

    /**
    *到期日
    */
    private String dueDate;

    /**
    *结息日
    */
    private String settleInterestDate;

    /**
    *还款日
    */
    private String repaymentDate;

    /**
    *应还本金
    */
    private String accountRepaymentPrincipal;

    /**
    *应还利息
    */
    private String accountRepaymentInterest;

    /**
    *应还服务费
    */
    private String accountRepaymentServiceCharge;
    
    /**
     * 应还总金额
     */
    private String accountRepaymentTotal;

    /**
    *结清状态（已结清、未结清）
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
    * 还款信息Id
    */
    private String repaymentInfoId;

    /**
    *创建时间
    */
    private String createTime;

    /**
    *更新时间
    */
    private String upateTime;
    
    /**
     * 是否可编辑
     */
    private String editAble;
    
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
	public String getBusinessContractNo() {
		return businessContractNo;
	}
	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
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

	/**
    *业务模型（保理商+融资客户监管等）
    */
    public String getBusinessModel() {
        return businessModel;
    }

    /**
    *业务模型（保理商+融资客户监管等）
    */
    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel == null ? null : businessModel.trim();
    }

    public String getLoanInfoId() {
		return loanInfoId;
	}
	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}
	/**
    *信息录入方式
    */
    public String getInputMethod() {
        return inputMethod;
    }

    /**
    *信息录入方式
    */
    public void setInputMethod(String inputMethod) {
        this.inputMethod = inputMethod == null ? null : inputMethod.trim();
    }
    
    /**
     * 放款日
     */
    public String getLoanDate() {
		return loanDate;
	}

    /**
     * 放款日
     */
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	/**
    *期数（int 还是 String？）
    */
    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    /**
    *期数（int 还是 String？）
    */
    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
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
    *融资金额
    */
    public String getFinanceAmount() {
        return financeAmount;
    }

    /**
    *融资金额
    */
    public void setFinanceAmount(String financeAmount) {
        this.financeAmount = financeAmount;
    }

    /**
    *融资余额
    */
    public String getFinanceBalance() {
        return financeBalance;
    }

    /**
    *融资余额
    */
    public void setFinanceBalance(String financeBalance) {
        this.financeBalance = financeBalance;
    }

    /**
    *起息日
    */
    public String getValueDate() {
        return valueDate;
    }

    /**
    *起息日
    */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate == null ? null : valueDate.trim();
    }

    /**
    *到期日
    */
    public String getDueDate() {
        return dueDate;
    }

    /**
    *到期日
    */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    /**
    *结息日
    */
    public String getSettleInterestDate() {
        return settleInterestDate;
    }

    /**
    *结息日
    */
    public void setSettleInterestDate(String settleInterestDate) {
        this.settleInterestDate = settleInterestDate == null ? null : settleInterestDate.trim();
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
    *应还本金
    */
    public String getAccountRepaymentPrincipal() {
        return accountRepaymentPrincipal;
    }

    /**
    *应还本金
    */
    public void setAccountRepaymentPrincipal(String accountRepaymentPrincipal) {
        this.accountRepaymentPrincipal = accountRepaymentPrincipal;
    }

    /**
    *应还利息
    */
    public String getAccountRepaymentInterest() {
        return accountRepaymentInterest;
    }

    /**
    *应还利息
    */
    public void setAccountRepaymentInterest(String accountRepaymentInterest) {
        this.accountRepaymentInterest = accountRepaymentInterest;
    }

    /**
    *应还服务费
    */
    public String getAccountRepaymentServiceCharge() {
        return accountRepaymentServiceCharge;
    }

    /**
    *应还服务费
    */
    public void setAccountRepaymentServiceCharge(String accountRepaymentServiceCharge) {
        this.accountRepaymentServiceCharge = accountRepaymentServiceCharge;
    }
    
    /**
     * 应还总金额
     */
    public String getAccountRepaymentTotal() {
		return accountRepaymentTotal;
	}
    
    /**
     * 应还总金额
     */
	public void setAccountRepaymentTotal(String accountRepaymentTotal) {
		this.accountRepaymentTotal = accountRepaymentTotal;
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
    *创建时间
    */
    public String getCreateTime() {
        return createTime;
    }

    /**
    *创建时间
    */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
    *更新时间
    */
    public String getUpateTime() {
        return upateTime;
    }

    public String getEditAble() {
		return editAble;
	}
	public void setEditAble(String editAble) {
		this.editAble = editAble;
	}
	/**
    *更新时间
    */
    public void setUpateTime(String upateTime) {
        this.upateTime = upateTime;
    }
    
	public String getCoreCompanyId() {
		return coreCompanyId;
	}
	public void setCoreCompanyId(String coreCompanyId) {
		this.coreCompanyId = coreCompanyId;
	}
	public String getCoreCompanyName() {
		return coreCompanyName;
	}
	public void setCoreCompanyName(String coreCompanyName) {
		this.coreCompanyName = coreCompanyName;
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
		RepaymentPlanRedisDTO other = (RepaymentPlanRedisDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
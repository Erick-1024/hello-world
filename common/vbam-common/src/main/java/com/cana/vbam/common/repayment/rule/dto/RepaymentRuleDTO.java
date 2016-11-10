package com.cana.vbam.common.repayment.rule.dto;

import java.io.Serializable;

import com.cana.vbam.common.repayment.rule.enums.DeductionRule;

public class RepaymentRuleDTO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2228278934276480580L;

	/**
    *主键
    */
    private String id;

    /**
    *保理商id
    */
    private String factorId;

    /**
    *融资客户id，以，号隔开（当部分融资客户有用）
    */
    private String fianceCustomerIds;

    /**
    *融资客户公司名称，以，号隔开（部分融资客户有用，列表显示用）
    */
    private String fianceCustomerCompanys;
    
    /**
    *保理商回款账户账号
    */
    private String factorTransferInAccountNo;
    
    /**
    *扣款时点
    */
    private String deductionTime;

    /**
    *扣款规则（足额扣款，部分扣款）
    */
    private DeductionRule deductionRule;
    
    /**
    *展期天数
    */
    private int extensionDays;

    /**
    *展期利率
    */
    private String extensionRatio;
    
    /**
    *罚息率
    */
    private String penaltyRate;

    /**
    *提前还款手续费率
    */
    private String earlyRepaymentChargeRatio;
    
    /**
    *账户归集时间
    */
    private String accountAccumulationTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFianceCustomerIds() {
		return fianceCustomerIds;
	}

	public void setFianceCustomerIds(String fianceCustomerIds) {
		this.fianceCustomerIds = fianceCustomerIds;
	}

	public String getFianceCustomerCompanys() {
		return fianceCustomerCompanys;
	}

	public void setFianceCustomerCompanys(String fianceCustomerCompanys) {
		this.fianceCustomerCompanys = fianceCustomerCompanys;
	}

	public String getFactorTransferInAccountNo() {
		return factorTransferInAccountNo;
	}

	public void setFactorTransferInAccountNo(String factorTransferInAccountNo) {
		this.factorTransferInAccountNo = factorTransferInAccountNo;
	}

	public String getDeductionTime() {
		return deductionTime;
	}

	public void setDeductionTime(String deductionTime) {
		this.deductionTime = deductionTime;
	}

	public DeductionRule getDeductionRule() {
		return deductionRule;
	}

	public void setDeductionRule(DeductionRule deductionRule) {
		this.deductionRule = deductionRule;
	}

	public int getExtensionDays() {
		return extensionDays;
	}

	public void setExtensionDays(int extensionDays) {
		this.extensionDays = extensionDays;
	}

	public String getExtensionRatio() {
		return extensionRatio;
	}

	public void setExtensionRatio(String extensionRatio) {
		this.extensionRatio = extensionRatio;
	}

	public String getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(String penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public String getEarlyRepaymentChargeRatio() {
		return earlyRepaymentChargeRatio;
	}

	public void setEarlyRepaymentChargeRatio(String earlyRepaymentChargeRatio) {
		this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
	}

	public String getAccountAccumulationTime() {
		return accountAccumulationTime;
	}

	public void setAccountAccumulationTime(String accountAccumulationTime) {
		this.accountAccumulationTime = accountAccumulationTime;
	}

}

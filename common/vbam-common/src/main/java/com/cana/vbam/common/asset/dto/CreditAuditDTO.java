package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hu
 *
 */
public class CreditAuditDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029425122869645808L;

	/**
     *主键
     */
    private String id;

    /**
     *操作类型
     */
    private String type;

    /**
     *操作类型(描述)
     */
    private String typeDesc;
    
    /**
     *业务合同号
     */
    private String creditId;

    /**
     *授信类型(综合,单笔)
     */
    private String creditMode;

    /**
     *授信类型(综合,单笔)描述
     */
    private String creditModeDesc;
    
    /**
     *币种
     */
    private String currency;

    /**
     *变化前申请金额
     */
    private Long preTotalLimit;

    private String preTotalLimitStr;
    /**
     *申请金额
     */
    private Long totalLimit;

    private String totalLimitStr;
    
    /**
     *变化前可用金额
     */
    private Long preAvailableLimit;

    private String preAvailableLimitStr;
    /**
     *可用金额
     */
    private Long availableLimit;

    private String availableLimitStr;
    
    /**
     *变化前已用金额
     */
    private Long preUsedLimit;

    private String preUsedLimitStr;
    /**
     *已用金额
     */
    private Long usedLimit;

    private String usedLimitStr;
    /**
     *客户id
     */
    private String customerId;

    /**
     *变化前生效日期
     */
    private String preEffectiveDate;

    /**
     *生效日
     */
    private String effectiveDate;

    /**
     *变化前到期日
     */
    private String preDueDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *变化前授信状态
     */
    private String preCreditStatus;

    private String preCreditStatusDesc;
    /**
     *授信状态
     */
    private String creditStatus;

    private String creditStatusDesc;
    /**
     *操作人id
     */
    private String operatorId;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *保理商id
     */
    private String factorId;

    /**
     *业务合同号
     */
    private String bussinessContractNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public String getCreditMode() {
		return creditMode;
	}

	public void setCreditMode(String creditMode) {
		this.creditMode = creditMode;
	}

	public String getCreditModeDesc() {
		return creditModeDesc;
	}

	public void setCreditModeDesc(String creditModeDesc) {
		this.creditModeDesc = creditModeDesc;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getPreAvailableLimit() {
		return preAvailableLimit;
	}

	public void setPreAvailableLimit(Long preAvailableLimit) {
		this.preAvailableLimit = preAvailableLimit;
	}

	public Long getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(Long availableLimit) {
		this.availableLimit = availableLimit;
	}

	public Long getPreUsedLimit() {
		return preUsedLimit;
	}

	public void setPreUsedLimit(Long preUsedLimit) {
		this.preUsedLimit = preUsedLimit;
	}

	public Long getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(Long usedLimit) {
		this.usedLimit = usedLimit;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPreEffectiveDate() {
		return preEffectiveDate;
	}

	public void setPreEffectiveDate(String preEffectiveDate) {
		this.preEffectiveDate = preEffectiveDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getPreDueDate() {
		return preDueDate;
	}

	public void setPreDueDate(String preDueDate) {
		this.preDueDate = preDueDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPreCreditStatus() {
		return preCreditStatus;
	}

	public void setPreCreditStatus(String preCreditStatus) {
		this.preCreditStatus = preCreditStatus;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getBussinessContractNo() {
		return bussinessContractNo;
	}

	public void setBussinessContractNo(String bussinessContractNo) {
		this.bussinessContractNo = bussinessContractNo;
	}

	public Long getPreTotalLimit() {
		return preTotalLimit;
	}

	public void setPreTotalLimit(Long preTotalLimit) {
		this.preTotalLimit = preTotalLimit;
	}

	public Long getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Long totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getPreTotalLimitStr() {
		return preTotalLimitStr;
	}

	public void setPreTotalLimitStr(String preTotalLimitStr) {
		this.preTotalLimitStr = preTotalLimitStr;
	}

	public String getTotalLimitStr() {
		return totalLimitStr;
	}

	public void setTotalLimitStr(String totalLimitStr) {
		this.totalLimitStr = totalLimitStr;
	}

	public String getPreAvailableLimitStr() {
		return preAvailableLimitStr;
	}

	public void setPreAvailableLimitStr(String preAvailableLimitStr) {
		this.preAvailableLimitStr = preAvailableLimitStr;
	}

	public String getAvailableLimitStr() {
		return availableLimitStr;
	}

	public void setAvailableLimitStr(String availableLimitStr) {
		this.availableLimitStr = availableLimitStr;
	}

	public String getPreUsedLimitStr() {
		return preUsedLimitStr;
	}

	public void setPreUsedLimitStr(String preUsedLimitStr) {
		this.preUsedLimitStr = preUsedLimitStr;
	}

	public String getUsedLimitStr() {
		return usedLimitStr;
	}

	public void setUsedLimitStr(String usedLimitStr) {
		this.usedLimitStr = usedLimitStr;
	}

	public String getPreCreditStatusDesc() {
		return preCreditStatusDesc;
	}

	public void setPreCreditStatusDesc(String preCreditStatusDesc) {
		this.preCreditStatusDesc = preCreditStatusDesc;
	}

	public String getCreditStatusDesc() {
		return creditStatusDesc;
	}

	public void setCreditStatusDesc(String creditStatusDesc) {
		this.creditStatusDesc = creditStatusDesc;
	}

}

package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hu
 *
 */
public class CreditDTO implements Serializable{
	
	private static final long serialVersionUID = -4970238465106845597L;

	/**
     * 主键
     */
    private String id;

    /**
     *授信类型(综合,单笔)
     */
    private String creditMode;

    private String creditModeDesc;
    
    /**
     *币种
     */
    private String currency;
    
    private String currencyDesc;
    
    /**
     *申请金额
     */
    private Long totalLimit;
    
    private String totalLimitStr;

    /**
     *已用金额
     */
    private Long usedLimit;

    private String usedLimitStr;
    
    /**
     * 可用金额
     */
    private Long availableLimit = 0L;
    
    private String availableLimitStr = "0";
    
    /**
     * 总费用
     */
    private Long totalExpense;
    
    private String totalExpenseStr;
    /**
     *客户id
     */
    private String customerId;

    /**
     *
     */
    private String factorId;

    /**
     *生效日
     */
    private String effectiveDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *授信状态
     */
    private String status;

    private String statusDesc;
    
    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

    /**
     *业务合同号
     */
    private String businessContractNo;
    
    private boolean allowModify = true;
    
    private boolean allowFreeze = true;
    
    private boolean allowUnFreeze = true;
    
    private boolean allowRevoke = true;
    
    private boolean allowCancel = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}

	public Long getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Long totalLimit) {
		this.totalLimit = totalLimit;
	}

	public Long getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(Long usedLimit) {
		this.usedLimit = usedLimit;
	}

	public Long getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(Long availableLimit) {
		this.availableLimit = availableLimit;
	}

	public Long getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(Long totalExpense) {
		this.totalExpense = totalExpense;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpateTime() {
		return upateTime;
	}

	public void setUpateTime(Date upateTime) {
		this.upateTime = upateTime;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getTotalLimitStr() {
		return totalLimitStr;
	}

	public void setTotalLimitStr(String totalLimitStr) {
		this.totalLimitStr = totalLimitStr;
	}

	public String getUsedLimitStr() {
		return usedLimitStr;
	}

	public void setUsedLimitStr(String usedLimitStr) {
		this.usedLimitStr = usedLimitStr;
	}

	public String getAvailableLimitStr() {
		return availableLimitStr;
	}

	public void setAvailableLimitStr(String availableLimitStr) {
		this.availableLimitStr = availableLimitStr;
	}

	public String getTotalExpenseStr() {
		return totalExpenseStr;
	}

	public void setTotalExpenseStr(String totalExpenseStr) {
		this.totalExpenseStr = totalExpenseStr;
	}

	public boolean isAllowModify() {
		return allowModify;
	}

	public void setAllowModify(boolean allowModify) {
		this.allowModify = allowModify;
	}

	public boolean isAllowFreeze() {
		return allowFreeze;
	}

	public void setAllowFreeze(boolean allowFreeze) {
		this.allowFreeze = allowFreeze;
	}

	public boolean isAllowUnFreeze() {
		return allowUnFreeze;
	}

	public void setAllowUnFreeze(boolean allowUnFreeze) {
		this.allowUnFreeze = allowUnFreeze;
	}

	public boolean isAllowRevoke() {
		return allowRevoke;
	}

	public void setAllowRevoke(boolean allowRevoke) {
		this.allowRevoke = allowRevoke;
	}

	public boolean isAllowCancel() {
		return allowCancel;
	}

	public void setAllowCancel(boolean allowCancel) {
		this.allowCancel = allowCancel;
	}
}

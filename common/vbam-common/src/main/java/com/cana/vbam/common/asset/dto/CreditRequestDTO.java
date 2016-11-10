package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hu
 *
 */
public class CreditRequestDTO implements Serializable {

	
	private static final long serialVersionUID = 1937024174298908943L;

	/**
	 * 额度Id
	 */
	private String id;
	
    /**
     *授信类型(综合,单笔)
     */
    private String creditMode;

    /**
     *币种
     */
    private String currency;

    /**
     *申请金额
     */
    private String totalLimit;

    /**
     *申请费用
     */
    private String expense;

    /**
     *客户id
     */
    private String customerId;

    /**
     * 当前用户Id
     */
    private String userId;

    /**
     *生效日
     */
    private String effectiveDate;

    /**
     *到期日
     */
    private String dueDate;

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

	public String getCreditMode() {
		return creditMode;
	}

	public void setCreditMode(String creditMode) {
		this.creditMode = creditMode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

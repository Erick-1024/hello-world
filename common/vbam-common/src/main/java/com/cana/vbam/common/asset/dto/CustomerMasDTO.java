/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:26:30
 */
public class CustomerMasDTO implements Serializable {
	
	private static final long serialVersionUID = 9112660521737090593L;

	/**
     *融资ｉｄ
     */
    private String id;

    /**
     *关联客户ｉｄ
     */
    private String customerId;

    /**
     *金融机构名称
     */
    private String financialInstitutionName;

    /**
     *金额（万元）
     */
    private BigDecimal amount;

    /**
     *开始时间
     */
    private String startDate;

    /**
     *到期日
     */
    private String endDate;

    /**
     *担保方式
     */
    private String guaranteeType;

    /**
     *备注
     */
    private String remark;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
     */
    private Date updateTime;


    /**
     *融资ｉｄ
     */
    public String getId() {
        return id;
    }

    /**
     *融资ｉｄ
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *关联客户ｉｄ
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *关联客户ｉｄ
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *金融机构名称
     */
    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    /**
     *金融机构名称
     */
    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName == null ? null : financialInstitutionName.trim();
    }

    /**
     *金额（万元）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *金额（万元）
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     *开始时间
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *开始时间
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    /**
     *到期日
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *到期日
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    /**
     *担保方式
     */
    public String getGuaranteeType() {
        return guaranteeType;
    }

    /**
     *担保方式
     */
    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType == null ? null : guaranteeType.trim();
    }

    /**
     *备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     *备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /**
     *记录更新时间
     */

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
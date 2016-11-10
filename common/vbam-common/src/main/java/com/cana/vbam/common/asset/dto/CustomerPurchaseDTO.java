/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cana.vbam.common.asset.enums.CustomerSettleTypeEnum;


/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:30:14
 */
public class CustomerPurchaseDTO implements Serializable {
	
	private static final long serialVersionUID = -1002361848115241596L;

	/**
     *采购ｉｄ
     */
    private String id;

    /**
     *关联客户ｉｄ
     */
    private String customerId;

    /**
     *供应商名称
     */
    private String supplierName;

    /**
     *上年采购量（万元）
     */
    private BigDecimal purchaseLastYear;

    /**
     *占总采购量%
     */
    private BigDecimal percent;

    /**
     *结算方式
     */
    private CustomerSettleTypeEnum settleType;

    /**
     *应付账款余额（万元）
     */
    private BigDecimal accountPayableBalance;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
     */
    private Date updateTime;


    /**
     *采购ｉｄ
     */
    public String getId() {
        return id;
    }

    /**
     *采购ｉｄ
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
     *供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     *供应商名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     *上年采购量（万元）
     */
    public BigDecimal getPurchaseLastYear() {
        return purchaseLastYear;
    }

    /**
     *上年采购量（万元）
     */
    public void setPurchaseLastYear(BigDecimal purchaseLastYear) {
        this.purchaseLastYear = purchaseLastYear;
    }

    /**
     *占总采购量%
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     *占总采购量%
     */
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    /**
     *结算方式
     */


    /**
     *应付账款余额（万元）
     */
    public BigDecimal getAccountPayableBalance() {
        return accountPayableBalance;
    }

    public CustomerSettleTypeEnum getSettleType() {
		return settleType;
	}

	public void setSettleType(CustomerSettleTypeEnum settleType) {
		this.settleType = settleType;
	}

	/**
     *应付账款余额（万元）
     */
    public void setAccountPayableBalance(BigDecimal accountPayableBalance) {
        this.accountPayableBalance = accountPayableBalance;
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

    /**
     *记录更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
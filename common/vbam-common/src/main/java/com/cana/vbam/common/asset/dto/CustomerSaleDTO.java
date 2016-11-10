/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:27:18
 */
public class CustomerSaleDTO implements Serializable {
	
	
	private static final long serialVersionUID = 2995844868880176579L;

	/**
     *销售id
     */
    private String id;

    /**
     *关联客户ｉｄ
     */
    private String customerId;

    /**
     *客户名称
     */
    private String saleCustomerName;

    /**
     *上年销货额（万元）
     */
    private BigDecimal saleLastYear;

    /**
     * 占总销售额%
     */
    private BigDecimal percent;

    /**
     *合作年限
     */
    private BigDecimal cooperationPeriod;

    /**
     *应收账款余额（万元）
     */
    private BigDecimal accountReceivableBalance;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
     */
    private String updateTime;


    /**
     *销售id
     */
    public String getId() {
        return id;
    }

    /**
     *销售id
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
     *客户名称
     */
    public String getSaleCustomerName() {
        return saleCustomerName;
    }

    /**
     *客户名称
     */
    public void setSaleCustomerName(String saleCustomerName) {
        this.saleCustomerName = saleCustomerName == null ? null : saleCustomerName.trim();
    }

    /**
     *上年销货额（万元）
     */
    public BigDecimal getSaleLastYear() {
        return saleLastYear;
    }

    /**
     *上年销货额（万元）
     */
    public void setSaleLastYear(BigDecimal saleLastYear) {
        this.saleLastYear = saleLastYear;
    }

    /**
     * 占总销售额%
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * 占总销售额%
     */
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    /**
     *合作年限
     */
    public BigDecimal getCooperationPeriod() {
        return cooperationPeriod;
    }

    /**
     *合作年限
     */
    public void setCooperationPeriod(BigDecimal cooperationPeriod) {
        this.cooperationPeriod = cooperationPeriod;
    }

    /**
     *应收账款余额（万元）
     */
    public BigDecimal getAccountReceivableBalance() {
        return accountReceivableBalance;
    }

    /**
     *应收账款余额（万元）
     */
    public void setAccountReceivableBalance(BigDecimal accountReceivableBalance) {
        this.accountReceivableBalance = accountReceivableBalance;
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
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     *记录更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

}
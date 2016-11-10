/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.flight.finance.dao.po;

import java.io.Serializable;

public class TzCustomerInfo implements Serializable {
    /**
     *真旅ID的压缩版，也用于展示
     */
    private Integer tzShortId;

    /**
     *真旅客户ID
     */
    private String tzCustomerId;

    /**
     *客户名称列表（如果多个则用“||”分隔，新的名称在后面，旧的名称在前面）
     */
    private String customerNames;

    /**
     *最早交易时间(格式：yyyy-MM-dd)
     */
    private String firstBusinessTime;

    private static final long serialVersionUID = 1L;

    /**
     *真旅ID的压缩版，也用于展示
     */
    public Integer getTzShortId() {
        return tzShortId;
    }

    /**
     *真旅ID的压缩版，也用于展示
     */
    public void setTzShortId(Integer tzShortId) {
        this.tzShortId = tzShortId;
    }

    /**
     *真旅客户ID
     */
    public String getTzCustomerId() {
        return tzCustomerId;
    }

    /**
     *真旅客户ID
     */
    public void setTzCustomerId(String tzCustomerId) {
        this.tzCustomerId = tzCustomerId == null ? null : tzCustomerId.trim();
    }

    /**
     *客户名称列表（如果多个则用“||”分隔，新的名称在后面，旧的名称在前面）
     */
    public String getCustomerNames() {
        return customerNames;
    }

    /**
     *客户名称列表（如果多个则用“||”分隔，新的名称在后面，旧的名称在前面）
     */
    public void setCustomerNames(String customerNames) {
        this.customerNames = customerNames == null ? null : customerNames.trim();
    }

    /**
     *最早交易时间(格式：yyyy-MM-dd)
     */
    public String getFirstBusinessTime() {
        return firstBusinessTime;
    }

    /**
     *最早交易时间(格式：yyyy-MM-dd)
     */
    public void setFirstBusinessTime(String firstBusinessTime) {
        this.firstBusinessTime = firstBusinessTime == null ? null : firstBusinessTime.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TzCustomerInfo other = (TzCustomerInfo) that;
        return (this.getTzShortId() == null ? other.getTzShortId() == null : this.getTzShortId().equals(other.getTzShortId()))
            && (this.getTzCustomerId() == null ? other.getTzCustomerId() == null : this.getTzCustomerId().equals(other.getTzCustomerId()))
            && (this.getCustomerNames() == null ? other.getCustomerNames() == null : this.getCustomerNames().equals(other.getCustomerNames()))
            && (this.getFirstBusinessTime() == null ? other.getFirstBusinessTime() == null : this.getFirstBusinessTime().equals(other.getFirstBusinessTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTzShortId() == null) ? 0 : getTzShortId().hashCode());
        result = prime * result + ((getTzCustomerId() == null) ? 0 : getTzCustomerId().hashCode());
        result = prime * result + ((getCustomerNames() == null) ? 0 : getCustomerNames().hashCode());
        result = prime * result + ((getFirstBusinessTime() == null) ? 0 : getFirstBusinessTime().hashCode());
        return result;
    }
}
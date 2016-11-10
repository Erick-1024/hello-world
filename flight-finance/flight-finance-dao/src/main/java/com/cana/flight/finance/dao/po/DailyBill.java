/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.flight.finance.dao.po;

import java.io.Serializable;
import java.util.Date;

public class DailyBill implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *日期（2016-01-02）
    */
    private String date;

    /**
    *真旅的客户ID
    */
    private String customerId;

    /**
    *订单总价
    */
    private Long price;

    /**
    *创建时间
    */
    private Date createTime;

    /**
    *更新时间
    */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    public String getId() {
        return id;
    }

    /**
    *主键
    */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
    *日期（2016-01-02）
    */
    public String getDate() {
        return date;
    }

    /**
    *日期（2016-01-02）
    */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
    *真旅的客户ID
    */
    public String getCustomerId() {
        return customerId;
    }

    /**
    *真旅的客户ID
    */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
    *订单总价
    */
    public Long getPrice() {
        return price;
    }

    /**
    *订单总价
    */
    public void setPrice(Long price) {
        this.price = price;
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
    *更新时间
    */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
    *更新时间
    */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        DailyBill other = (DailyBill) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}
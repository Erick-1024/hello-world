/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.credit.dao.po;

import java.io.Serializable;
import java.util.Date;

public class WhiteCustomerRule implements Serializable {
    /**
     *批次号
     */
    private Integer batchNo;

    /**
     *规则生成类型（ALL:全量，PART:增量）
     */
    private String produceType;

    /**
     *本批次产生的白名单客户数量
     */
    private Integer whiteCustomerNumber;

    /**
     *是否可用
     */
    private Boolean enable;

    /**
     *规则，cooperation_period:与客户来源公司的合作月份的最小值、purchase_order_growth_rate:订单采购增长率、overdue_rate:逾期率、overdue_times:逾期次数。组成的json串
     */
    private String rule;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     *批次号
     */
    public Integer getBatchNo() {
        return batchNo;
    }

    /**
     *批次号
     */
    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
    }

    /**
     *规则生成类型（ALL:全量，PART:增量）
     */
    public String getProduceType() {
        return produceType;
    }

    /**
     *规则生成类型（ALL:全量，PART:增量）
     */
    public void setProduceType(String produceType) {
        this.produceType = produceType == null ? null : produceType.trim();
    }

    /**
     *本批次产生的白名单客户数量
     */
    public Integer getWhiteCustomerNumber() {
        return whiteCustomerNumber;
    }

    /**
     *本批次产生的白名单客户数量
     */
    public void setWhiteCustomerNumber(Integer whiteCustomerNumber) {
        this.whiteCustomerNumber = whiteCustomerNumber;
    }

    /**
     *是否可用
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     *是否可用
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     *规则，cooperation_period:与客户来源公司的合作月份的最小值、purchase_order_growth_rate:订单采购增长率、overdue_rate:逾期率、overdue_times:逾期次数。组成的json串
     */
    public String getRule() {
        return rule;
    }

    /**
     *规则，cooperation_period:与客户来源公司的合作月份的最小值、purchase_order_growth_rate:订单采购增长率、overdue_rate:逾期率、overdue_times:逾期次数。组成的json串
     */
    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
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
        WhiteCustomerRule other = (WhiteCustomerRule) that;
        return (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getProduceType() == null ? other.getProduceType() == null : this.getProduceType().equals(other.getProduceType()))
            && (this.getWhiteCustomerNumber() == null ? other.getWhiteCustomerNumber() == null : this.getWhiteCustomerNumber().equals(other.getWhiteCustomerNumber()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getRule() == null ? other.getRule() == null : this.getRule().equals(other.getRule()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getProduceType() == null) ? 0 : getProduceType().hashCode());
        result = prime * result + ((getWhiteCustomerNumber() == null) ? 0 : getWhiteCustomerNumber().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getRule() == null) ? 0 : getRule().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}
/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.white;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ducer
 *
 */
public class WhiteCustomerDTO implements Serializable {

	private static final long serialVersionUID = -827635884087587665L;
	/**
	 * 主键
	 */
	private String id;
	/**
     *Cana的真旅网客户ID
     */
    private Integer tzShortId;
    /**
     *采购商ID。唯一
     */
    private String tzCustomerId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 与客户来源公司的合作月份
	 */
	private Integer cooperationPeriod;
	/**
	 * 订单采购增长率
	 */
	private BigDecimal purchaseOrderGrowthRate;
	/**
	 * 逾期率
	 */
	private BigDecimal overdueRate;
	/**
	 * 逾期次数
	 */
	private Integer overdueTimes;
	/**
	 * 规则批次号
	 */
	private Integer ruleBatchNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getTzShortId() {
        return tzShortId;
    }

    public void setTzShortId(Integer tzShortId) {
        this.tzShortId = tzShortId;
    }

    public String getTzCustomerId() {
        return tzCustomerId;
    }

    public void setTzCustomerId(String tzCustomerId) {
        this.tzCustomerId = tzCustomerId;
    }

    /**
	 * 客户名称
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();
	}

	/**
	 * 与客户来源公司的合作月份
	 */
	public Integer getCooperationPeriod() {
		return cooperationPeriod;
	}

	/**
	 * 与客户来源公司的合作月份
	 */
	public void setCooperationPeriod(Integer cooperationPeriod) {
		this.cooperationPeriod = cooperationPeriod;
	}

	/**
	 * 订单采购增长率
	 */
	public BigDecimal getPurchaseOrderGrowthRate() {
		return purchaseOrderGrowthRate;
	}

	/**
	 * 订单采购增长率
	 */
	public void setPurchaseOrderGrowthRate(BigDecimal purchaseOrderGrowthRate) {
		this.purchaseOrderGrowthRate = purchaseOrderGrowthRate;
	}

	/**
	 * 逾期率
	 */
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}

	/**
	 * 逾期率
	 */
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

	/**
	 * 逾期次数
	 */
	public Integer getOverdueTimes() {
		return overdueTimes;
	}

	/**
	 * 逾期次数
	 */
	public void setOverdueTimes(Integer overdueTimes) {
		this.overdueTimes = overdueTimes;
	}

	/**
	 * 规则批次号
	 */
	public Integer getRuleBatchNo() {
		return ruleBatchNo;
	}

	/**
	 * 规则批次号
	 */
	public void setRuleBatchNo(Integer ruleBatchNo) {
		this.ruleBatchNo = ruleBatchNo;
	}

	/**
	 * 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

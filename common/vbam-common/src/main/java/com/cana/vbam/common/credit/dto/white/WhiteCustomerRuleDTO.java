/**
 * Copyright © 2016-2019 Cana. All rights reserved.
 */
package com.cana.vbam.common.credit.dto.white;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ducer
 *
 */
public class WhiteCustomerRuleDTO implements Serializable {
	private static final long serialVersionUID = -1641736767579038634L;
	/**
	 * 批次号
	 */
	private Integer batchNo;
	/**
	 * 与客户来源公司的合作月份的最小值
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
	 * 规则生成类型（ALL:全量，PART:增量）
	 */
	private String produceType;
	/**
	 * 本批次产生的白名单客户数量
	 */
	private Integer whiteCustomerNumber;
	/**
	 * 是否可用
	 */
	private Boolean enable;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 批次号
	 */
	public Integer getBatchNo() {
		return batchNo;
	}

	/**
	 * 批次号
	 */
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * 与客户来源公司的合作月份的最小值
	 */
	public Integer getCooperationPeriod() {
		return cooperationPeriod;
	}

	/**
	 * 与客户来源公司的合作月份的最小值
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
	 * 规则生成类型（ALL:全量，PART:增量）
	 */
	public String getProduceType() {
		return produceType;
	}

	/**
	 * 规则生成类型（ALL:全量，PART:增量）
	 */
	public void setProduceType(String produceType) {
		this.produceType = produceType == null ? null : produceType.trim();
	}

	/**
	 * 本批次产生的白名单客户数量
	 */
	public Integer getWhiteCustomerNumber() {
		return whiteCustomerNumber;
	}

	/**
	 * 本批次产生的白名单客户数量
	 */
	public void setWhiteCustomerNumber(Integer whiteCustomerNumber) {
		this.whiteCustomerNumber = whiteCustomerNumber;
	}

	/**
	 * 是否可用
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * 是否可用
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	/**
	 * 创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}

package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

import com.cana.vbam.common.asset.enums.CustomerTypeEnum;
import com.cana.vbam.common.asset.enums.FactoringType;

public class BusinessCounterpartyDTO implements Serializable{

	private static final long serialVersionUID = 6472222042803163781L;

	/**
     * 主键
     */
    private String id;

    /**
     * 业务资料Id
     */
    private String businessInfoId;

    /**
     * 交易对手Id
     */
    private String counterpartyId;

    /**
     * 交易对手类型
     */
    private CustomerTypeEnum counterpartyType;

    /**
     * 交易对手
     */
    private String counterparty;

    /**
     * 融资比例
     */
    private String financingRatio;

    /**
     * 保理类型
     */
    private FactoringType factoringType;

    /**
     * 是否查询子额度
     */
    private Boolean querySubLimitFlag;

    /**
     * 子额度
     */
    private String subLimit;

    /**
     * 资料顺序
     */
    private String sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public FactoringType getFactoringType() {
		return factoringType;
	}

	public void setFactoringType(FactoringType factoringType) {
		this.factoringType = factoringType;
	}

	public Boolean getQuerySubLimitFlag() {
		return querySubLimitFlag;
	}

	public void setQuerySubLimitFlag(Boolean querySubLimitFlag) {
		this.querySubLimitFlag = querySubLimitFlag;
	}

	public String getFinancingRatio() {
		return financingRatio;
	}

	public void setFinancingRatio(String financingRatio) {
		this.financingRatio = financingRatio;
	}

	public String getSubLimit() {
		return subLimit;
	}

	public void setSubLimit(String subLimit) {
		this.subLimit = subLimit;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public CustomerTypeEnum getCounterpartyType() {
		return counterpartyType;
	}

	public void setCounterpartyType(CustomerTypeEnum counterpartyType) {
		this.counterpartyType = counterpartyType;
	}
	
}

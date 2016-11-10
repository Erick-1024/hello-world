/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cana.vbam.common.asset.enums.CustomerStkPayamtTypeEnum;
import com.cana.vbam.common.asset.enums.CustomerStkTypeEnum;


/**
 * @author jiangzhou.Ren
 * @time 2016年7月22日上午10:29:01
 */
public class CustomerStkDTO implements Serializable {
	
	private static final long serialVersionUID = 8012368721415433027L;

	/**
     *股东id 
     */
    private String id;

    /**
     *客户ｉｄ
     */
    private String customerId;

    /**
     *股东类型
     */
    private CustomerStkTypeEnum stkType;

    /**
     *股东名称
     */
    private String stkName;

    /**
     *出资额（万元)
     */
    private BigDecimal stkPayamt;

    /**
     *出资比例
     */
    private BigDecimal stkPcnt;

    /**
     *出资方式
     */
    private CustomerStkPayamtTypeEnum stkPayamtType;

    /**
     *是否到位
     */
    private String stkStatus;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *记录更新时间
     */
    private Date updateTime;


    /**
     *股东id 
     */
    public String getId() {
        return id;
    }

    /**
     *股东id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *客户ｉｄ
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     *客户ｉｄ
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     *股东类型
     */
 
    /**
     *股东名称
     */
    public String getStkName() {
        return stkName;
    }

    public CustomerStkTypeEnum getStkType() {
		return stkType;
	}

	public void setStkType(CustomerStkTypeEnum stkType) {
		this.stkType = stkType;
	}

	/**
     *股东名称
     */
    public void setStkName(String stkName) {
        this.stkName = stkName == null ? null : stkName.trim();
    }

    /**
     *出资额（万元)
     */
    public BigDecimal getStkPayamt() {
        return stkPayamt;
    }

    /**
     *出资额（万元)
     */
    public void setStkPayamt(BigDecimal stkPayamt) {
        this.stkPayamt = stkPayamt;
    }

    /**
     *出资比例
     */
    public BigDecimal getStkPcnt() {
        return stkPcnt;
    }

    /**
     *出资比例
     */
    public void setStkPcnt(BigDecimal stkPcnt) {
        this.stkPcnt = stkPcnt;
    }

    /**
     *出资方式
     */
 
    /**
     *是否到位
     */
    public String getStkStatus() {
        return stkStatus;
    }

    public CustomerStkPayamtTypeEnum getStkPayamtType() {
		return stkPayamtType;
	}

	public void setStkPayamtType(CustomerStkPayamtTypeEnum stkPayamtType) {
		this.stkPayamtType = stkPayamtType;
	}

	/**
     *是否到位
     */
    public void setStkStatus(String stkStatus) {
        this.stkStatus = stkStatus == null ? null : stkStatus.trim();
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

   

}
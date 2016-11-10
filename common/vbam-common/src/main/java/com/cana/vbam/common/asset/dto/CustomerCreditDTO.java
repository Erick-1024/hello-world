 package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.asset.enums.CreditStatus;
import com.cana.vbam.common.asset.enums.CustomerTypeEnum;

/**
 * @author hu
 *
 */
public class CustomerCreditDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -496760375206985034L;

	/**
     *客户ｉｄ
     */
    private String id;
    
    /**
     *客户名称			
     */
    private String customerName;

    /**
     *客户类型(融资申请人，交易对手，担保方)
     */
    private CustomerTypeEnum customerType;
    
    private CreditStatus creditStatus;
    //客户类型页面列表展示
    private String customerTypeDesc;
    
    /**
     *记录更新时间
     */
    private Date updateTime;
    
    /**
     * 可用余额
     */
    private Long availableLimit;
    
    private String availableLimitDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public CustomerTypeEnum getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerTypeEnum customerType) {
		this.customerType = customerType;
	}

	public CreditStatus getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(CreditStatus creditStatus) {
		this.creditStatus = creditStatus;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(Long availableLimit) {
		this.availableLimit = availableLimit;
	}

	public String getAvailableLimitDesc() {
		return availableLimitDesc;
	}

	public void setAvailableLimitDesc(String availableLimitDesc) {
		this.availableLimitDesc = availableLimitDesc;
	}

	public String getCustomerTypeDesc() {
		return customerTypeDesc;
	}

	public void setCustomerTypeDesc(String customerTypeDesc) {
		this.customerTypeDesc = customerTypeDesc;
	}
	
	
}

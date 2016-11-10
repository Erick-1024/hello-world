package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面额度列表返回值
 * @author sugar
 *
 */
public class CustomerLimitListResponseDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String limitId;

    private String memberId;

    private String outCustomerName;
    
    private String companyName;
    
    private String customerType;//客户类型（个人，企业）
    
    private String creditMode;
    
    private long totalLimitLongValue;
    
    private long usedLimitLongValue;
    
    private Date effectiveDate;
    
    private String limitStatus;

    public String getLimitId() {
		return limitId;
	}

	public void setLimitId(String limitId) {
		this.limitId = limitId;
	}

	public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreditMode() {
        return creditMode;
    }

    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode;
    }

    public String getLimitStatus() {
        return limitStatus;
    }

    public void setLimitStatus(String limitStatus) {
        this.limitStatus = limitStatus;
    }

	public long getTotalLimitLongValue() {
		return totalLimitLongValue;
	}

	public void setTotalLimitLongValue(long totalLimitLongValue) {
		this.totalLimitLongValue = totalLimitLongValue;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public long getUsedLimitLongValue() {
		return usedLimitLongValue;
	}

	public void setUsedLimitLongValue(long usedLimitLongValue) {
		this.usedLimitLongValue = usedLimitLongValue;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

}

package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

/**
 * vj额度列表返回值
 * @author sugar
 *
 */
public class VJLimitListResponseDTO implements Serializable{
    
    
    private static final long serialVersionUID = 1L;

    private String limitId;

    private String memberId;

    private String customerName;
    
    private String identityCardNo;
    
    private String creditMode;
    
    private String creditModeDesc;
    
    private String totalLimitStr;
    
    private String usedLimitStr;
    
    private String effectiveDateStr;
    
    private String limitStatus;
    
    private String limitStatusDesc;
    
    private String mobileNo;

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

    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
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

	public String getCreditModeDesc() {
		return creditModeDesc;
	}

	public void setCreditModeDesc(String creditModeDesc) {
		this.creditModeDesc = creditModeDesc;
	}

	public String getTotalLimitStr() {
		return totalLimitStr;
	}

	public void setTotalLimitStr(String totalLimitStr) {
		this.totalLimitStr = totalLimitStr;
	}

	public String getUsedLimitStr() {
		return usedLimitStr;
	}

	public void setUsedLimitStr(String usedLimitStr) {
		this.usedLimitStr = usedLimitStr;
	}

	public String getEffectiveDateStr() {
		return effectiveDateStr;
	}

	public void setEffectiveDateStr(String effectiveDateStr) {
		this.effectiveDateStr = effectiveDateStr;
	}

	public String getLimitStatusDesc() {
		return limitStatusDesc;
	}

	public void setLimitStatusDesc(String limitStatusDesc) {
		this.limitStatusDesc = limitStatusDesc;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
    
    

}

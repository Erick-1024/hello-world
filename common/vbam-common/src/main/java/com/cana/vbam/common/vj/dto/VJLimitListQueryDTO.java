package com.cana.vbam.common.vj.dto;

import com.cana.vbam.common.dto.Pagination;

/**
 * vj额度列表查询条件
 * @author sugar
 *
 */
public class VJLimitListQueryDTO extends Pagination {

	private static final long serialVersionUID = -4316806004305532882L;

	private String customerName;//客户名称
	
	private String identityCardNo;//身份证号
    
	private String limitMin;//额度最小值
	
	private String limitMax;//额度最大值
	
	private String effectiveDateStart;//额度生效日开始值
    
	private String effectiveDateEnd;//额度生效日结束值
	
	private String limitStatus;
    
	private String memberId;

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

	public String getLimitMin() {
		return limitMin;
	}

	public void setLimitMin(String limitMin) {
		this.limitMin = limitMin;
	}

	public String getLimitMax() {
		return limitMax;
	}

	public void setLimitMax(String limitMax) {
		this.limitMax = limitMax;
	}

	public String getEffectiveDateStart() {
        return effectiveDateStart;
    }

    public void setEffectiveDateStart(String effectiveDateStart) {
        this.effectiveDateStart = effectiveDateStart;
    }

    public String getEffectiveDateEnd() {
        return effectiveDateEnd;
    }

    public void setEffectiveDateEnd(String effectiveDateEnd) {
        this.effectiveDateEnd = effectiveDateEnd;
    }

    public String getLimitStatus() {
        return limitStatus;
    }

    public void setLimitStatus(String limitStatus) {
        this.limitStatus = limitStatus;
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}

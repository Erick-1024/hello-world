package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;

import com.cana.vbam.common.credit.enums.CreditLimitAuditType;
import com.cana.vbam.common.credit.enums.CreditMode;

/**
 * 恢复用户授信额度请求参数
 * @author XuMeng
 *
 */
public class CreditLimitRecoveryRequstDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String memberId;	// 客户ID
	private String projectId;   // 产品id
	private String outCustomerId; // 外部客户ID，允许为空
	private CreditMode creditMode; // 授信模式
	private long limit; 		// 需要恢复的额度，以分为单位
	private CreditLimitAuditType auditType; //变化类型

	public long getLimit() {
		return limit;
	}
	public void setLimit(long limit) {
		this.limit = limit;
	}
	public String getOutCustomerId() {
		return outCustomerId;
	}
	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public CreditMode getCreditMode() {
		return creditMode;
	}
	public void setCreditMode(CreditMode creditMode) {
		this.creditMode = creditMode;
	}
	public CreditLimitAuditType getAuditType() {
		return auditType;
	}
	public void setAuditType(CreditLimitAuditType auditType) {
		this.auditType = auditType;
	}
	
}

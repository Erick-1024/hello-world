package com.cana.vbam.common.account.enums;

/**
 * 
 * @author XuMeng
 * 账户审核状态
 */
public enum AccountApplyStatus {
	REJECTED("审核未通过", "未通过", "已审核"),
	PENDINGAUDIT("待审核", null, "待审核"),
	ACCEPTED("审核已通过", "通过", "已审核");
	
	private String desc;
	private String auditResult;
	private String auditStatus;
	
	private AccountApplyStatus(String desc, String auditResult, String auditStatus) {
		this.desc = desc;
		this.auditResult = auditResult;
		this.auditStatus = auditStatus;
	}
	
	public String desc() {
		return desc;
	}

    public String getAuditResult() {
        return auditResult;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

}

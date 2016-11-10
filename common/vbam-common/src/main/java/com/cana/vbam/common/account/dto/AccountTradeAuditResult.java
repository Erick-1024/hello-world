/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.account.dto;

import java.io.Serializable;

import com.cana.vbam.common.account.enums.AccountTradeApplyStatus;

/**
 * @author ducer
 *
 */
public class AccountTradeAuditResult implements Serializable {

	private static final long serialVersionUID = 3839402015130931467L;

	private AccountTradeApplyStatus auditStatus;// 是否审核通过
	private String accountTradeApplyId;// 交易申请ID
	private String auditUserId;//审核员工id
	private String auditMessage;// 审核意见
	
	public AccountTradeApplyStatus getAuditStatus() {
        return auditStatus;
    }
    public void setAuditStatus(AccountTradeApplyStatus auditStatus) {
        this.auditStatus = auditStatus;
    }
    public String getAccountTradeApplyId() {
		return accountTradeApplyId;
	}
	public void setAccountTradeApplyId(String accountTradeApplyId) {
		this.accountTradeApplyId = accountTradeApplyId;
	}
	public String getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}
	public String getAuditMessage() {
		return auditMessage;
	}
	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}
}

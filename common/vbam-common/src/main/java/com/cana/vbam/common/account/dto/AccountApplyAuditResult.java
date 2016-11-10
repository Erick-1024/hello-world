package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 开户申请审核结果
 * 
 * @author XuMeng
 *
 */
public class AccountApplyAuditResult implements Serializable {
	private static final long serialVersionUID = -8601798954709843893L;

	private boolean needSendUserActiveMail; // 是否需要发送用户激活邮件
	private String auditMessage = ""; //审核意见
	private Boolean isNeedNewSupervision; // 是否需要创建监管关系
	private String accountApplyId; // 代开户申请Id
	private String companyId; // 客户Id
	private int accountNumber; // 需要申请的账户数量
	private List<String> buyerNames; // 买方企业列表

	public boolean isNeedSendUserActiveMail() {
		return needSendUserActiveMail;
	}

	public Boolean getIsNeedNewSupervision() {
		return isNeedNewSupervision;
	}

	public String getAuditMessage() {
		return auditMessage;
	}

	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}

	public void setIsNeedNewSupervision(Boolean isNeedNewSupervision) {
		this.isNeedNewSupervision = isNeedNewSupervision;
	}

	public void setNeedSendUserActiveMail(boolean needSendUserActiveMail) {
		this.needSendUserActiveMail = needSendUserActiveMail;
	}

	public String getAccountApplyId() {
		return accountApplyId;
	}

	public void setAccountApplyId(String accountApplyId) {
		this.accountApplyId = accountApplyId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<String> getBuyerNames() {
		return buyerNames;
	}

	public void setBuyerNames(List<String> buyerNames) {
		this.buyerNames = buyerNames;
	}
}

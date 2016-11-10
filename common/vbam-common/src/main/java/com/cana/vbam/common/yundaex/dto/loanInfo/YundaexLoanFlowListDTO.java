/**
 * 
 */
package com.cana.vbam.common.yundaex.dto.loanInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请用款流水 DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexLoanFlowListDTO implements Serializable {

	private static final long serialVersionUID = 3455361428798739821L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 业务流水号
	 */
	private String businessSeq;

	/**
	 * 放款ID
	 */
	private String loanId;

	/**
	 * 转账状态。"SUCCESS"：转账成功；"HANDLING"：转账中；"FAIL"：转账失败
	 */
	private String transferStatus;

	/**
	 * 转账金额。精确到分
	 */
	private Long fee;

	private String loanAmt;

	/**
	 * 转账类型。LOAN：放款
	 */
	private String transferType;

	/**
	 * 转出账号
	 */
	private String fromAccountNo;

	/**
	 * 转出账户名
	 */
	private String fromAccountName;

	/**
	 * 转入账号
	 */
	private String toAccountNo;

	/**
	 * 转入账户名
	 */
	private String toAccountName;

	/**
	 * 转入行地址
	 */
	private String toAccountAddress;

	/**
	 * 操作员ID
	 */
	private String operatorId;

	/**
	 * 转账开始时间
	 */
	private Date transferStartTime;

	/**
	 * 转账结束时间
	 */
	private Date transferEndTime;

	/**
	 * 融资客户
	 */
	private String financeCompany;

	private String createTime;

	public String getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessSeq() {
		return businessSeq;
	}

	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public String getFromAccountName() {
		return fromAccountName;
	}

	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}

	public String getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(String toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public String getToAccountName() {
		return toAccountName;
	}

	public void setToAccountName(String toAccountName) {
		this.toAccountName = toAccountName;
	}

	public String getToAccountAddress() {
		return toAccountAddress;
	}

	public void setToAccountAddress(String toAccountAddress) {
		this.toAccountAddress = toAccountAddress;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Date getTransferStartTime() {
		return transferStartTime;
	}

	public void setTransferStartTime(Date transferStartTime) {
		this.transferStartTime = transferStartTime;
	}

	public Date getTransferEndTime() {
		return transferEndTime;
	}

	public void setTransferEndTime(Date transferEndTime) {
		this.transferEndTime = transferEndTime;
	}

}

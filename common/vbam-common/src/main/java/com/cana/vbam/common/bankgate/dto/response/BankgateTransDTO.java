package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ducer
 */
public class BankgateTransDTO implements Serializable {

	private static final long serialVersionUID = -6262124955372698984L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 原流水记录ID，关联操作，Ex：解冻和冻结相关联
	 */
	private String originId;

	/**
	 * 交易时间
	 */
	private String transDate;

	/**
	 * 交易类型，BangateServer
	 */
	private String transType;

	/**
	 * 业务类型，业务端.业务类型必须明确给出.Ex：在BangateServer，转账、解冻、解冻支付、冻结、支付冻结都是强制转账的交易类型
	 */
	private String businessType;

	/**
	 * 业务流水号，业务端
	 */
	private String businessSeq;

	/**
	 * 网关流水号，BangateServer
	 */
	private String gateSeq;

	/**
	 * 银行流水号，银行返回，可能没有
	 */
	private String bankSeq;

	/**
	 * 银行对帐时间
	 */
	private String bankCheckDate;

	/**
	 * 交易状态
	 */
	private String status;

	/**
	 * 业务发起帐号，发起该交易的帐号
	 */
	private String accountNo;

	/**
	 * 第三方帐号，Ex：对应于发起帐号，Ex:转账时的收款帐号
	 */
	private String receiveAccountNo;

	/**
	 * 最后一次更新该记录的时间，同一条记录因为状态变更可能会多次更新
	 */
	private Date updateTime;

	/**
	 * 流水创建时间
	 */
	private Date createTime;

	/**
	 * 交易金额，分为单位
	 */
	private String amount;

	/**
	 * 主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * 原流水记录ID，关联操作，Ex：解冻和冻结相关联
	 */
	public String getOriginId() {
		return originId;
	}

	/**
	 * 原流水记录ID，关联操作，Ex：解冻和冻结相关联
	 */
	public void setOriginId(String originId) {
		this.originId = originId == null ? null : originId.trim();
	}

	/**
	 * 交易发起时间，业务端
	 */
	public String getTransDate() {
		return transDate;
	}

	/**
	 * 交易发起时间，业务端
	 */
	public void setTransDate(String transDate) {
		this.transDate = transDate == null ? null : transDate.trim();
	}

	/**
	 * 交易类型，BangateServer
	 */
	public String getTransType() {
		return transType;
	}

	/**
	 * 交易类型，BangateServer
	 */
	public void setTransType(String transType) {
		this.transType = transType == null ? null : transType.trim();
	}

	/**
	 * 业务类型，业务端.业务类型必须明确给出.Ex：在BangateServer，转账、解冻、解冻支付、冻结、支付冻结都是强制转账的交易类型
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * 业务类型，业务端.业务类型必须明确给出.Ex：在BangateServer，转账、解冻、解冻支付、冻结、支付冻结都是强制转账的交易类型
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType == null ? null : businessType.trim();
	}

	/**
	 * 业务流水号，业务端
	 */
	public String getBusinessSeq() {
		return businessSeq;
	}

	/**
	 * 业务流水号，业务端
	 */
	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq == null ? null : businessSeq.trim();
	}

	/**
	 * 网关流水号，BangateServer
	 */
	public String getGateSeq() {
		return gateSeq;
	}

	/**
	 * 网关流水号，BangateServer
	 */
	public void setGateSeq(String gateSeq) {
		this.gateSeq = gateSeq == null ? null : gateSeq.trim();
	}

	/**
	 * 银行流水号，银行返回，可能没有
	 */
	public String getBankSeq() {
		return bankSeq;
	}

	/**
	 * 银行流水号，银行返回，可能没有
	 */
	public void setBankSeq(String bankSeq) {
		this.bankSeq = bankSeq == null ? null : bankSeq.trim();
	}

	/**
	 * 银行对帐时间
	 */
	public String getBankCheckDate() {
		return bankCheckDate;
	}

	/**
	 * 银行对帐时间
	 */
	public void setBankCheckDate(String bankCheckDate) {
		this.bankCheckDate = bankCheckDate == null ? null : bankCheckDate.trim();
	}

	/**
	 * 交易状态
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 交易状态
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * 业务发起帐号，发起该交易的帐号
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * 业务发起帐号，发起该交易的帐号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo == null ? null : accountNo.trim();
	}

	/**
	 * 第三方帐号，Ex：对应于发起帐号，Ex:转账时的收款帐号
	 */
	public String getReceiveAccountNo() {
		return receiveAccountNo;
	}

	/**
	 * 第三方帐号，Ex：对应于发起帐号，Ex:转账时的收款帐号
	 */
	public void setReceiveAccountNo(String receiveAccountNo) {
		this.receiveAccountNo = receiveAccountNo == null ? null : receiveAccountNo.trim();
	}

	/**
	 * 最后一次更新该记录的时间，同一条记录因为状态变更可能会多次更新
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 最后一次更新该记录的时间，同一条记录因为状态变更可能会多次更新
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 流水创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 流水创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 交易金额，分为单位
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * 交易金额，分为单位
	 */
	public void setAmount(String amount) {
		this.amount = amount == null ? null : amount.trim();
	}
}

package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvoiceInfoDTO implements Serializable {

	private static final long serialVersionUID = 4190631130206500971L;

	/**
	 * 业务合同编号
	 */
	private String businessContractNo;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 账款基本信息id
	 */
	private String invoiceBaseId;

	/**
	 * 交易对手Id
	 */
	private String counterpartyId;

	/**
	 * 交易对手
	 */
	private String counterparty;

	/**
	 * 单证号码
	 */
	private String invoiceNo;

	/**
	 * 单证面额
	 */
	private String nominvoiceAmt;

	/**
	 * 应收金额
	 */
	private String invoiceAmt;

	/**
	 * 融资比例
	 */
	private BigDecimal financingRatio;

	/**
	 * 开票日
	 */
	private String invoiceDate;

	/**
	 * 到期日
	 */
	private String dueDate;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 关联的放款信息ID
	 */
	private String loanInfoId;

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getNominvoiceAmt() {
		return nominvoiceAmt;
	}

	public void setNominvoiceAmt(String nominvoiceAmt) {
		this.nominvoiceAmt = nominvoiceAmt;
	}

	public String getInvoiceAmt() {
		return invoiceAmt;
	}

	public void setInvoiceAmt(String invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}

	public BigDecimal getFinancingRatio() {
		return financingRatio;
	}

	public void setFinancingRatio(BigDecimal financingRatio) {
		this.financingRatio = financingRatio;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getInvoiceBaseId() {
		return invoiceBaseId;
	}

	public void setInvoiceBaseId(String invoiceBaseId) {
		this.invoiceBaseId = invoiceBaseId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

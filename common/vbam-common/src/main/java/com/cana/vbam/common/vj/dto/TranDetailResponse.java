package com.cana.vbam.common.vj.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TranDetailResponse implements Serializable {

	private static final long serialVersionUID = -8379997451525938024L;

	private String canaTranSeq;
	
	private String vjTranSeq;
	
	private String loanId;
	
	private String customerName;
	
	private String identityCardNo;
	
	private String loanDate;
	
	private String dueDate;
	
	private BigDecimal interestRate;;
	
	private Long amount;
	
	private String state;
	
	private Date createTime;
	
	private String contractMeidaId;
	
	private String stateDesc;
	
	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

	public String getVjTranSeq() {
		return vjTranSeq;
	}

	public void setVjTranSeq(String vjTranSeq) {
		this.vjTranSeq = vjTranSeq;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
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

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public String getContractMeidaId() {
		return contractMeidaId;
	}
	
	public void setContractMeidaId(String contractMeidaId) {
		this.contractMeidaId = contractMeidaId;
	}
	
	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
}

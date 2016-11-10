package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class BankBillListResponse implements Serializable {

	private static final long serialVersionUID = -3426515135272281330L;

	/**
	 *CANA端生成的交易流水
	 */
	private String canaTranSeq;

	/**
     *客户名称
     */
    private String customerName;

    /**
     *身份证号
     */
    private String identityCardNo;

    /**
     *交易类型描述
     */
    private String tranTypeDesc;

    /**
     *交易状态描述
     */
    private String stateDesc;

    /**
     *交易金额(转换后的)
     */
    private String amountStr;

    /**
     *对账日期, 格式: yyyy-MM-dd
     */
    private String bankCheckDate;

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

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}

	public String getBankCheckDate() {
		return bankCheckDate;
	}

	public void setBankCheckDate(String bankCheckDate) {
		this.bankCheckDate = bankCheckDate;
	}

	public String getTranTypeDesc() {
		return tranTypeDesc;
	}

	public void setTranTypeDesc(String tranTypeDesc) {
		this.tranTypeDesc = tranTypeDesc;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}
	
}

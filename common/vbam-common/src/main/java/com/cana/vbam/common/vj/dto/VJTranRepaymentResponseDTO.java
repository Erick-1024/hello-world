package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

/**
 * 交易明细－－主动还款明细
 * @author sugar
 *
 */
public class VJTranRepaymentResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     *主键
     */
    private String id;

    /**
     *客户名称
     */
    private String customerName;

    /**
     *身份证号
     */
    private String identityCardNo;

    /**
     *CANA端生成的交易流水
     */
    private String canaTranSeq;
    
    /**
     *VJ端生成的交易流水
     */
    private String vjTranSeq;

    /**
     *放款信息id
     */
    private String loanId;

    /**
     *交易类型
     */
    private String tranType;
    
    /**
     *交易类型描述
     */
    private String tranTypeDesc;

    /**
     *交易金额
     */
    private String amount;

    /**
     *交易状态
     */
    private String state;

    /**
     *交易状态描述
     */
    private String stateDesc;
    
    /**
     *创建时间
     */
    private String createTime;
    
    /**
     * 还款汇总记录id
     */
    private String repaymentSingleCollectId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getTranTypeDesc() {
		return tranTypeDesc;
	}

	public void setTranTypeDesc(String tranTypeDesc) {
		this.tranTypeDesc = tranTypeDesc;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRepaymentSingleCollectId() {
		return repaymentSingleCollectId;
	}

	public void setRepaymentSingleCollectId(String repaymentSingleCollectId) {
		this.repaymentSingleCollectId = repaymentSingleCollectId;
	}

	public String getCanaTranSeq() {
		return canaTranSeq;
	}

	public void setCanaTranSeq(String canaTranSeq) {
		this.canaTranSeq = canaTranSeq;
	}
}
package com.cana.vbam.common.account.dto;

import java.io.Serializable;

/**
 * 授信转账请求参数
 * @author XuMeng
 *
 */
public class TransferFundForCreditRequestDTO implements Serializable {
    private static final long serialVersionUID = 5108877603458167228L;

    private String businessSeq;             //交易流水号
    private String transferOutAccountNo;    //转出账号
    private String transferInAccountNo;     //转入账号
    private long amount;                    //转入金额，单位人民币分，必需为正数
    private String bankRemark; 				//电子回单摘要，用于在银行转账记录备注中加上真旅订单号

    public String getBusinessSeq() {
        return businessSeq;
    }
    public void setBusinessSeq(String businessSeq) {
        this.businessSeq = businessSeq;
    }
    public String getTransferOutAccountNo() {
        return transferOutAccountNo;
    }
    public void setTransferOutAccountNo(String transferOutAccountNo) {
        this.transferOutAccountNo = transferOutAccountNo;
    }
    public String getTransferInAccountNo() {
        return transferInAccountNo;
    }
    public void setTransferInAccountNo(String transferInAccountNo) {
        this.transferInAccountNo = transferInAccountNo;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
	public String getBankRemark() {
		return bankRemark;
	}
	public void setBankRemark(String bankRemark) {
		this.bankRemark = bankRemark;
	}
    
}

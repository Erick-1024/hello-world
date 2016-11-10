package com.cana.yundaex.common.dto.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hu
 *
 */
public class ContractSituationResultDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2057269142616589128L;

	/**
    *
    */
   private String id;

   /**
    *网点客户名称
    */
   private String stationName;

   /**
    *完成时间
    */
   private Date signCompleteTime;

   /**
    *协议编号
    */
   private String protocolNo;

   /**
	 * 
	 */
	private String payAccountName;
   /**
    *还款账号
    */
   private String payAccountNo;

   /**
    *还款账户行
    */
   private String payAccountBank;

   /**
    *创建时间
    */
   private Date createTime;

   /**
    *更新时间
    */
   private Date updateTime;

   /**
    *融资合同签署状态（无，未签署，已签署）
    */
   private String financeContractSignState;

   /**
    *授权合同签署状态（无，未签署，已签署）
    */
   private String creditContractSignState;

   /**
    *个人连带保证责任合同签署状态（无，未签署，已签署）
    */
   private String dutyContractSignState;
   
	   private String completeState;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStationName() {
		return stationName;
	}
	
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public Date getSignCompleteTime() {
		return signCompleteTime;
	}
	
	public void setSignCompleteTime(Date signCompleteTime) {
		this.signCompleteTime = signCompleteTime;
	}
	
	public String getProtocolNo() {
		return protocolNo;
	}
	
	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
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

	public String getFinanceContractSignState() {
		return financeContractSignState;
	}
	
	public void setFinanceContractSignState(String financeContractSignState) {
		this.financeContractSignState = financeContractSignState;
	}
	
	public String getCreditContractSignState() {
		return creditContractSignState;
	}
	
	public void setCreditContractSignState(String creditContractSignState) {
		this.creditContractSignState = creditContractSignState;
	}
	
	public String getDutyContractSignState() {
		return dutyContractSignState;
	}
	
	public void setDutyContractSignState(String dutyContractSignState) {
		this.dutyContractSignState = dutyContractSignState;
	}
	
	public String getCompleteState() {
		return completeState;
	}
	
	public void setCompleteState(String completeState) {
		this.completeState = completeState;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

	public String getPayAccountNo() {
		return payAccountNo;
	}

	public void setPayAccountNo(String payAccountNo) {
		this.payAccountNo = payAccountNo;
	}

	public String getPayAccountBank() {
		return payAccountBank;
	}

	public void setPayAccountBank(String payAccountBank) {
		this.payAccountBank = payAccountBank;
	}
}

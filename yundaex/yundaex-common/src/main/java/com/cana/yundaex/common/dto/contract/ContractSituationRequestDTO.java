package com.cana.yundaex.common.dto.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hu
 *
 */
public class ContractSituationRequestDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -2411840278438147796L;

	/**
    *
    */
	private String id;

   /**
	*网点客户名称
	*/
    private String stationName;

   /**
	*协议编号
	*/
    private String protocolNo;

    /**
	*收款账号
	*/
    private String payAccountName;
    
   /**
	*收款账号
	*/
    private String payAccountNo;

	   /**
	*收款账户行
	*/
    private String payAccountBank;
	
    /**
    *
    */
    private String payLianHangNo;
	   /**
	*客户Id
	*/
    private String customerId;
	
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
	
    private Date signCompleteTime;
   
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
	
	public String getProtocolNo() {
		return protocolNo;
	}
	
	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
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

	public Date getSignCompleteTime() {
		return signCompleteTime;
	}

	public void setSignCompleteTime(Date signCompleteTime) {
		this.signCompleteTime = signCompleteTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getPayLianHangNo() {
		return payLianHangNo;
	}

	public void setPayLianHangNo(String payLianHangNo) {
		this.payLianHangNo = payLianHangNo;
	}
}

package com.cana.bankgate.server.response.account;

import java.io.Serializable;

import com.cana.bankgate.server.enums.BankBizStatus;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class BankAccountParamAlterResult implements Serializable{

	private static final long serialVersionUID = -7546215883436367888L;
	
	@XStreamAlias("status")
	@XStreamConverter(BankEnumConverter.class)
	private BankBizStatus status;// 本次请求执行状态
	  
	@XStreamAlias("statusText")
	private String statusText;// 本次请求执行状态说明

	public BankBizStatus getStatus() {
		return status;
	}

	public void setStatus(BankBizStatus status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
}

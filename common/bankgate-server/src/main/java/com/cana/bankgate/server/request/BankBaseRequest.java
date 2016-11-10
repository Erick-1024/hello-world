package com.cana.bankgate.server.request;

import java.io.Serializable;

import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class BankBaseRequest implements Serializable {

	private static final long serialVersionUID = -2632368634228068687L;
	
	@XStreamAlias("action")
	@XStreamConverter(BankEnumConverter.class)
	private BankBizType bankBizType;
	
	public BankBizType getBankBizType() {
		return bankBizType;
	}
	
	public void setBankBizType(BankBizType bankBizType) {
		this.bankBizType = bankBizType;
	}

	public BankBaseRequest(BankBizType bankBizType) {
		super();
		this.bankBizType = bankBizType;
	}
}

/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.account;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("row")
public class BankInfoData implements Serializable {

	private static final long serialVersionUID = 4487000408305592671L;

	@XStreamAlias("tgfi")
	private String bankPaymentNo;  //支付联行号，可空，支付联行号和支付联行名称必填1个 char(12)
	
	@XStreamAlias("tgname")
	private String bankPaymentName;// 支付联行名称，可空， 支付联行号和支付联行名称必填1个，varchar(62)
	
	public String getBankPaymentNo() {
		return bankPaymentNo;
	}
	public void setBankPaymentNo(String bankPaymentNo) {
		this.bankPaymentNo = bankPaymentNo;
	}
	public String getBankPaymentName() {
		return bankPaymentName;
	}
	public void setBankPaymentName(String bankPaymentName) {
		this.bankPaymentName = bankPaymentName;
	}
}

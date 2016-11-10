/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class BankInfoDataDTO implements Serializable {

	private static final long serialVersionUID = -3033243122370588066L;

	private String bankPaymentNo;  //支付联行号，可空，支付联行号和支付联行名称必填1个 char(12)
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

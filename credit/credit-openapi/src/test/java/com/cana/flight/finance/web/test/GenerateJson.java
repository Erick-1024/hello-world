package com.cana.flight.finance.web.test;

import java.util.Date;

import org.junit.Test;

import com.cana.flight.finance.common.dto.CreditPayDTO;
import com.cana.flight.finance.common.dto.CreditRefundDTO;
import com.cana.vbam.common.credit.enums.Institution;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.security.RSAUtil;

public class GenerateJson {

	private String customerId 		= "526f488745ce9ad7b9caaa35";
	private String payTradeNo 		= "20160323105503" + "10000001";
	private String refundTradeNo 	= "20160323105503" + "20000002";

	@Test
	public void generatePayData() throws Exception {
		CreditPayDTO pay = new CreditPayDTO();
		pay.setInstitution(Institution.travelzen.name());
		pay.setCustomerId(customerId);
		pay.setTradeNo(payTradeNo);
		pay.setPaymentFee(1000000l);
		pay.setNotifyURL("http://www.baidu.com");
		String plain = pay.getInstitution() 
				+ pay.getCustomerId()
				+ pay.getTradeNo()
				+ pay.getPaymentFee()
				+ pay.getNotifyURL();
		byte[] sigh = RSAUtil.sign(plain.getBytes(), "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJjjtKEH2iJ5hNGOa4sXnM6+lZy7N3Kj3naKpNFZZuvgBjL8JDacrHn6a3UvWJ7LIY5PWYDxzPGlv02119s/w63XKcGZy7DVY89rEMKvgsqQiXH0WH1XKDLOszsb6/IJh9jdtcH9ll7TsOcWfDJSKaOIXrsQqVX82v6WUcKNBdQTAgMBAAECgYEAl3KgYA9eJl5HkinJIBxAyY+CtqHBmD75RGWsRHCSAj+FBIY9/RcPxzinr4o59/px//1cRN3BTPIJ4Zmq/FVaqFFghboxcZKA/P6PbnuBB/zoZlep8knve1CNu/1WnAeRzWLbGMEQNAL4rwJ0wAPKp1KdRfKlUvaXa7pht42TQKkCQQDox7zlV+ZD83GasKxFTz/UVJaBsfTfeRS0kuKA1GtY9oHoEDRQ/7QuE9qsTYCC3JlPMKH9f0u7gw9Y8LsUj9DlAkEAqCPh17aa1XG1jUn5EovD3/uybrvNx9uUAkPJexC89jFQkZXoWvwH4CJl04ncD5dSIzydgGoT/OKOBEDnGypZlwJAcV2dHCBzzxIHeKukKlkPH+xWJDGzrQX91HdYgh9xbDEZURhbMjtl167pp1JTTOf8bfmkWp2dF2QbnFgY9EmaFQJBAJsByXjWjVNtKtSAd7CTTRa2sb7IQGOZmI+l8p8TxUCqIf61VqpaYuBMldc45rkw5bY6cXErWhXuvsJJG0W9+y0CQQC3vOMhqtn7mm+VAgUNCAMqO/yVnNk7ciPjpYOETDVq7ab3dTGh1tJUSbJpv7oD+k6vNFvS7fNhNxGdVJvhcVpj");
		pay.setSign(new String(sigh));
		pay.setTradeTime(DateTimeUtil.format(new Date(), DateTimeUtil.DATE_TIME_PATTERN));
		pay.setCustomerName("通州环宇");
		pay.setOrderInfo("机票");
		System.out.println(new Gson().toJson(pay));
	}
	
	@Test
	public void generateRefundData() throws Exception {
		CreditRefundDTO bean = new CreditRefundDTO();
		bean.setInstitution(Institution.travelzen.name());
		bean.setCustomerId(customerId);
		bean.setTradeNo(refundTradeNo);
		bean.setOriginTradeNo(payTradeNo);
		bean.setRefundFee(100000l);
		bean.setNotifyURL("http://www.baidu.com");
		String plain = bean.getInstitution() 
				+ bean.getCustomerId()
				+ bean.getTradeNo()
				+ bean.getOriginTradeNo()
				+ bean.getRefundFee()
				+ bean.getNotifyURL();
		byte[] sigh = RSAUtil.sign(plain.getBytes(), "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJjjtKEH2iJ5hNGOa4sXnM6+lZy7N3Kj3naKpNFZZuvgBjL8JDacrHn6a3UvWJ7LIY5PWYDxzPGlv02119s/w63XKcGZy7DVY89rEMKvgsqQiXH0WH1XKDLOszsb6/IJh9jdtcH9ll7TsOcWfDJSKaOIXrsQqVX82v6WUcKNBdQTAgMBAAECgYEAl3KgYA9eJl5HkinJIBxAyY+CtqHBmD75RGWsRHCSAj+FBIY9/RcPxzinr4o59/px//1cRN3BTPIJ4Zmq/FVaqFFghboxcZKA/P6PbnuBB/zoZlep8knve1CNu/1WnAeRzWLbGMEQNAL4rwJ0wAPKp1KdRfKlUvaXa7pht42TQKkCQQDox7zlV+ZD83GasKxFTz/UVJaBsfTfeRS0kuKA1GtY9oHoEDRQ/7QuE9qsTYCC3JlPMKH9f0u7gw9Y8LsUj9DlAkEAqCPh17aa1XG1jUn5EovD3/uybrvNx9uUAkPJexC89jFQkZXoWvwH4CJl04ncD5dSIzydgGoT/OKOBEDnGypZlwJAcV2dHCBzzxIHeKukKlkPH+xWJDGzrQX91HdYgh9xbDEZURhbMjtl167pp1JTTOf8bfmkWp2dF2QbnFgY9EmaFQJBAJsByXjWjVNtKtSAd7CTTRa2sb7IQGOZmI+l8p8TxUCqIf61VqpaYuBMldc45rkw5bY6cXErWhXuvsJJG0W9+y0CQQC3vOMhqtn7mm+VAgUNCAMqO/yVnNk7ciPjpYOETDVq7ab3dTGh1tJUSbJpv7oD+k6vNFvS7fNhNxGdVJvhcVpj");
		bean.setSign(new String(sigh));
		bean.setTradeTime(DateTimeUtil.format(new Date(), DateTimeUtil.DATE_TIME_PATTERN));
		System.out.println(new Gson().toJson(bean));
	}

}

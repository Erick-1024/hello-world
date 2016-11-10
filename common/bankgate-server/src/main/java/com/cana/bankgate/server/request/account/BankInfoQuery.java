/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.account;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankInfoQuery extends BankBaseRequest{

  private static final long serialVersionUID = -9149694943982255587L;
  
  @XStreamAlias("userName")
  private String bankUserName; // 登录名 varchar(30)
  
  @XStreamAlias("provinceName")
  private String provinceName; // 省名称，可空 varchar(42)
  
  @XStreamAlias("cityName")
  private String cityName; // 城市名称，可空 varchar(42)
  
  @XStreamAlias("bankName")
  private String bankName; // 银行名称，可空 varchar(62)
  
  @XStreamAlias("tgfi")
  private String bankPaymentNo; // 支付联行号，可空，支付联行号和支付联行名称必填1个 char(12)
  
  @XStreamAlias("tgname")
  private String bankPaymentName; // 支付联行名称，可空， 支付联行号和支付联行名称必填1个，varchar(62)
  
  public BankInfoQuery(BankBizType bankBizType) {
	super(bankBizType);
  }

  public String getBankUserName() {
    return bankUserName;
  }
  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }
  public String getProvinceName() {
    return provinceName;
  }
  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }
  public String getCityName() {
    return cityName;
  }
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
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

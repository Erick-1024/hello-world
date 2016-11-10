/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class BankInfoQueryDTO implements Serializable {

  private static final long serialVersionUID = 899110544389462129L;

  private String provinceName; // 省名称，可空 varchar(42)
  private String cityName; // 城市名称，可空 varchar(42)
  private String bankName; // 银行名称，可空 varchar(62)
  private String bankPaymentNo; // 支付联行号，可空，支付联行号和支付联行名称必填1个 char(12)
  private String bankPaymentName; // 支付联行名称，可空， 支付联行号和支付联行名称必填1个，varchar(62)

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

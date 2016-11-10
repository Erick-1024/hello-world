/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.fund;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.FundBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class TradeStatusQuery extends BankBaseRequest{

  private static final long serialVersionUID = 10505225583076679L;

  @XStreamAlias("userName")
  private String bankUserName;// 登录名 char(30)

  @XStreamAlias("clientID")
  private String gateSeq;// 客户流水号char(20)

  // 原请求代码char(8)，可空，若客户能保证各交易类型的流水号唯一，则可空，否则需上送原请求代码。 资金初始化：DLFNDINI
  // 调账入款：DLTRSFIN 错账调回：DLWFDRTN 入金：DLFONDIN 出金：DLFNDOUT 保证金退还：DLGTYRTN 转账：DLSUBTRN；
  // 强制转账：DLMDETRN；平台出金：DLFCSOUT；手工结息：DLIRTSTL
  @XStreamAlias("type")
  @XStreamConverter(value = BankEnumConverter.class)
  private FundBizType fundBizType;

  public TradeStatusQuery(BankBizType bankBizType) {
	super(bankBizType);
  }
  
  public String getBankUserName() {
    return bankUserName;
  }
  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }
  public String getGateSeq() {
    return gateSeq;
  }
  public void setGateSeq(String gateSeq) {
    this.gateSeq = gateSeq;
  }
  public FundBizType getFundBizType() {
    return fundBizType;
  }
  public void setFundBizType(FundBizType fundBizType) {
    this.fundBizType = fundBizType;
  }
}

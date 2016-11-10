/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.server.response.fund;

import java.io.Serializable;

import com.cana.bankgate.server.enums.BankBizStatus;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.TradeStatusFlag;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("row")
public class TradeStatusData implements Serializable {

  private static final long serialVersionUID = -8930987321986937095L;
  
  @XStreamAlias("stt")
  @XStreamConverter(value = BankEnumConverter.class)
  private TradeStatusFlag tradeStatusFlag;//状态标志 char(1) 0 成功 1 失败 2未知 3审核拒绝 4 用户撤销
  
  @XStreamAlias("status")
  @XStreamConverter(value = BankEnumConverter.class)
  private BankBizStatus status;//状态代码 char(7)
  
  @XStreamAlias("statusText")
  private String statusText;//状态信息varchar(254)
  
  public TradeStatusFlag getTradeStatusFlag() {
    return tradeStatusFlag;
  }
  public void setTradeStatusFlag(TradeStatusFlag tradeStatusFlag) {
    this.tradeStatusFlag = tradeStatusFlag;
  }
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

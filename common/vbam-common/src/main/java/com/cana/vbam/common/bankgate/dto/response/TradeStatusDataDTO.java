/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;

import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.bankgate.enums.TradeStatusFlag;

/**
 * @author ducer
 *
 */
public class TradeStatusDataDTO implements Serializable {

  private static final long serialVersionUID = 3298419251759612190L;
  private TradeStatusFlag tradeStatusFlag;//状态标志 char(1) 0 成功 1 失败 2未知 3审核拒绝 4 用户撤销
  private BankTranStatus status;//状态代码 char(7)
  private String statusText;//状态信息varchar(254)
  
  public TradeStatusFlag getTradeStatusFlag() {
    return tradeStatusFlag;
  }
  public void setTradeStatusFlag(TradeStatusFlag tradeStatusFlag) {
    this.tradeStatusFlag = tradeStatusFlag;
  }
  public BankTranStatus getStatus() {
    return status;
  }
  public void setStatus(BankTranStatus status) {
    this.status = status;
  }
  public String getStatusText() {
    return statusText;
  }
  public void setStatusText(String statusText) {
    this.statusText = statusText;
  }
}

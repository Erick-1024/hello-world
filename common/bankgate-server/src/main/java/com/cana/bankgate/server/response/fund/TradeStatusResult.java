/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.fund;

import java.io.Serializable;
import java.util.List;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class TradeStatusResult extends BankBaseResult implements Serializable {

  private static final long serialVersionUID = -8080861593293028308L;
  
  @XStreamAlias("list")
  private List<TradeStatusData> tradeStatusDatas;
  
  public List<TradeStatusData> getTradeStatusDatas() {
    return tradeStatusDatas;
  }
  public void setTradeStatusDatas(List<TradeStatusData> tradeStatusDatas) {
    this.tradeStatusDatas = tradeStatusDatas;
  }
}

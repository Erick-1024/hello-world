/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducer
 *
 */
public class TradeStatusResultDTO extends BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = -6946631012544382480L;
  private List<TradeStatusDataDTO> tradeStatusDatas;
  
  public List<TradeStatusDataDTO> getTradeStatusDatas() {
    return tradeStatusDatas;
  }
  public void setTradeStatusDatas(List<TradeStatusDataDTO> tradeStatusDatas) {
    this.tradeStatusDatas = tradeStatusDatas;
  }
}

/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducer
 *
 */
public class BankAccountTradeFlowResultDTO extends BankBaseResultDTO implements Serializable {
  private static final long serialVersionUID = -4948600164812320635L;
  private int size;
  private List<BankAccountTradeFlowDataDTO> bankAccountTradeFlowDatas;
  
  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }
  public List<BankAccountTradeFlowDataDTO> getBankAccountTradeFlowDatas() {
    return bankAccountTradeFlowDatas;
  }
  public void setBankAccountTradeFlowDatas(
      List<BankAccountTradeFlowDataDTO> bankAccountTradeFlowDatas) {
    this.bankAccountTradeFlowDatas = bankAccountTradeFlowDatas;
  }
}

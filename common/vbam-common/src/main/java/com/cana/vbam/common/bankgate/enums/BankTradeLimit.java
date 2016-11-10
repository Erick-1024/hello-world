/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 银行支持的最大交易额度
 * 
 * @author ducer
 *
 */
public enum BankTradeLimit {

  max(999999999999999L), 
  min(0L),
  ;

  private Long limit;

  BankTradeLimit(Long limit) {
    this.limit = limit;
  }

  public Long getLimit() {
    return limit;
  }

}

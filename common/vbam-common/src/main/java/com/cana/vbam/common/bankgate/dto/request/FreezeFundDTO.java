/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * 冻结
 * 
 * @author ducer
 *
 */
public class FreezeFundDTO extends BankAccountBaseDTO {

  private static final long serialVersionUID = 4585817695491528459L;

  // 交易金额decimal(15,2)
  @NotBlank
  private Long amount;
  // 摘要varchar(22) 可空
  private String memo;
  
  public Long getAmount() {
    return amount;
  }
  public void setAmount(Long amount) {
    this.amount = amount;
  }
  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }
}

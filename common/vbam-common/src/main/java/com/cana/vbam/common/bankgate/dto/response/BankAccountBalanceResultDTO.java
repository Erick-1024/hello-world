/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * 所有的附属帐号都叫accountNo，主帐号叫mainAccountNo
 * 
 * @author ducer
 *
 */
public class BankAccountBalanceResultDTO extends BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = -2081792502092693459L;

  private List<BankAccountBalanceDataDTO> bankAccountBalanceDatas;

  public List<BankAccountBalanceDataDTO> getBankAccountBalanceDatas() {
    return bankAccountBalanceDatas;
  }

  public void setBankAccountBalanceDatas(List<BankAccountBalanceDataDTO> bankAccountBalanceDatas) {
    this.bankAccountBalanceDatas = bankAccountBalanceDatas;
  }
}

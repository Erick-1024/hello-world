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
public class BankMainAccountBalanceResultDTO extends BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = 977496305089916848L;

  private List<BankMainAccountBalanceDataDTO> bankMainAccountBalanceDatas; // 主账号余额数据

  public List<BankMainAccountBalanceDataDTO> getBankMainAccountBalanceDatas() {
    return bankMainAccountBalanceDatas;
  }

  public void setBankMainAccountBalanceDatas(List<BankMainAccountBalanceDataDTO> bankMainAccountBalanceDatas) {
    this.bankMainAccountBalanceDatas = bankMainAccountBalanceDatas;
  }
}

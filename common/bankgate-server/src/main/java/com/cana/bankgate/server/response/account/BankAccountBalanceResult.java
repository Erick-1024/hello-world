/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.account;

import java.io.Serializable;
import java.util.List;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankAccountBalanceResult extends BankBaseResult implements Serializable {

  private static final long serialVersionUID = 8735486661019728349L;
  @XStreamAlias("list")
  private List<BankAccountBalanceData> accountBalanceDatas;

  public List<BankAccountBalanceData> getAccountBalanceDatas() {
    return accountBalanceDatas;
  }

  public void setAccountBalanceDatas(List<BankAccountBalanceData> accountBalanceDatas) {
    this.accountBalanceDatas = accountBalanceDatas;
  }
}

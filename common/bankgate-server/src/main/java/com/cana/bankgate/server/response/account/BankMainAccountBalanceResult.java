/**
 * Copyright © 2016-2029 Cana. All rights reserved.
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
public class BankMainAccountBalanceResult extends BankBaseResult implements Serializable {

  private static final long serialVersionUID = 2300347750771220331L;

  @XStreamAlias("list")
  private List<BankMainAccountBalanceData> bankMainAccountBalanceDatas; //主账号余额数据

  public List<BankMainAccountBalanceData> getBankMainAccountBalanceDatas() {
    return bankMainAccountBalanceDatas;
  }

  public void setBankMainAccountBalanceDatas(
      List<BankMainAccountBalanceData> bankMainAccountBalanceDatas) {
    this.bankMainAccountBalanceDatas = bankMainAccountBalanceDatas;
  }
}

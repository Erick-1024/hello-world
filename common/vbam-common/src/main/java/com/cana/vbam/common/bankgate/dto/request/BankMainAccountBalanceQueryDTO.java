/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducer
 *
 */
public class BankMainAccountBalanceQueryDTO implements Serializable {

  private static final long serialVersionUID = 8204184384064108105L;

  private String bankUserName; // 登录名
  private List<String> mainAccountNos; // 主账号集合

  public String getBankUserName() {
    return bankUserName;
  }

  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }

  public List<String> getMainAccountNos() {
    return mainAccountNos;
  }

  public void setMainAccountNos(List<String> mainAccountNos) {
    this.mainAccountNos = mainAccountNos;
  }
}

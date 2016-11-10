/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class BankAccountDTO extends BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = 2511060791405738029L;
  // 附属账号 char(19)
  private String accountNo;
  // 附属账户名称 varchar(100)
  private String accountName;

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
}

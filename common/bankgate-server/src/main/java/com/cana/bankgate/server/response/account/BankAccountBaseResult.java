/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.account;

import java.io.Serializable;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankAccountBaseResult extends BankBaseResult implements Serializable {

  @XStreamOmitField
  private static final long serialVersionUID = -3632602265480235907L;
  
  @XStreamAlias("subAccNo")
  private String accountNo; // 附属账号 char(19)
  
  @XStreamAlias("subAccNm")
  private String accountName; // 附属账户名称 varchar(100)

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

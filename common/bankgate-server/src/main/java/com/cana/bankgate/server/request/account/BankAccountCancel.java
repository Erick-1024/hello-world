/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.account;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankAccountCancel extends BankBaseRequest{

  private static final long serialVersionUID = 3745387618521266238L;
  
  @XStreamAlias("userName")
  private String bankUserName; // 登录名 varchar(30
  
  @XStreamAlias("mainAccNo")
  private String mainAccountNo; // 主体账号varchar(19)。银行开发的命名真蛋疼
  
  @XStreamAlias("subAccNo")
  private String accountNo; // 附属账号varchar(19)
  
  public BankAccountCancel(BankBizType bankBizType) {
	super(bankBizType);
  }
  
  public String getBankUserName() {
    return bankUserName;
  }
  public void setBankUserName(String bankUserName) {
    this.bankUserName = bankUserName;
  }
  public String getMainAccountNo() {
    return mainAccountNo;
  }
  public void setMainAccountNo(String mainAccountNo) {
    this.mainAccountNo = mainAccountNo;
  }
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
}

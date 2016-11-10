/**
 * 
 */
package com.cana.bankgate.server.request.account;

import java.io.Serializable;

/**
 * @author ducer
 *
 */
public class BankAccountGroup implements Serializable {

  private static final long serialVersionUID = -4468460127224730987L;

  private String bankUserName; // 银行主账号用户名

  private String mainAccountNo; // 银行主账号

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
}

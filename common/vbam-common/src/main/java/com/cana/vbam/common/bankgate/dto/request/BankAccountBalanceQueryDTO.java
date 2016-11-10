/**
 *  Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * 余额查询
 * 
 * @author ducer
 *
 */
public class BankAccountBalanceQueryDTO implements Serializable {

  private static final long serialVersionUID = 8936127041107881110L;
  /**
   * 银行帐号
   */
  @NotBlank
  private String accountNo;

  public String getAccountNo() {
	return accountNo;
  }
  public void setAccountNo(String accountNo) {
	this.accountNo = accountNo;
  }
}

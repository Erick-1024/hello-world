/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;

import com.cana.vbam.common.bankgate.enums.BankTranStatus;

/**
 * 只返回状态和状态说明
 * 
 * @author ducer
 *
 */
public class BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = -2280658957697781789L;
  private BankTranStatus status;// 状态
  private String statusText;// 状态说明

  public BankTranStatus getStatus() {
    return status;
  }

  public void setStatus(BankTranStatus status) {
    this.status = status;
  }

  public String getStatusText() {
    return statusText;
  }

  public void setStatusText(String statusText) {
    this.statusText = statusText;
  }
}

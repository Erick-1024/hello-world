/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;
import java.util.Date;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * @author ducer
 *
 */
public class FreezeInfoQueryDTO implements Serializable {

  private static final long serialVersionUID = 1591008235080724303L;

  @NotBlank
  private String accountNo;//账号varchar(19)
  @NotBlank
  private Date startDate;//起始日期
  @NotBlank
  private Date endDate;//终止日期
  
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}

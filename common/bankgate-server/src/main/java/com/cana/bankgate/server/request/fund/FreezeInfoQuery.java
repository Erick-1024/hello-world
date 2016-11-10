/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.server.request.fund;

import java.util.Date;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.bankgate.server.xstream.BankDateConverter;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class FreezeInfoQuery extends BankBaseRequest{
  
  private static final long serialVersionUID = -4241695438434787661L;

  @XStreamAlias("userName")
  private String bankUserName;
  
  @XStreamAlias("accountNo")
  private String mainAccountNo;//主体账号varchar(19)
  
  @XStreamAlias("subAccNo")
  private String accountNo;//账号varchar(19)
  
  @XStreamAlias("startDate")
  @XStreamConverter(value = BankDateConverter.class,strings={"yyyyMMdd"})
  private Date startDate;//起始日期char(8)
  
  @XStreamAlias("endDate")
  @XStreamConverter(value = BankDateConverter.class,strings={"yyyyMMdd"})
  private Date endDate;//终止日期char(8)
  
  public FreezeInfoQuery(BankBizType bankBizType) {
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

/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.trade;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("stream")
public class BankTradeDetailQuery extends BankBaseRequest{

  private static final long serialVersionUID = -1061731433475572233L;
  
  @XStreamAlias("userName")
  private String bankUserName; // 登录用户
  
  @XStreamAlias("mainAccNo")
  private String mainAccountNo; // 主账号
  
  @XStreamAlias("subAccNo")
  private String accountNo; // 附属账号
  
  @XStreamAlias("startDate")
  private String startDate; // 起始日期char(8)
  
  @XStreamAlias("endDate")
  private String endDate; // 终止日期char(8)
  
  @XStreamAlias("startRecord")
  private Integer pageIndex; // 起始记录号char(4)
  
  @XStreamAlias("pageNumber")
  private Integer pageSize; // 请求记录条数 char(2) 最大为10
  
  public BankTradeDetailQuery(BankBizType bankBizType) {
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
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public Integer getPageIndex() {
    return pageIndex;
  }
  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }
  public Integer getPageSize() {
    return pageSize;
  }
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}

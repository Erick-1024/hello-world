/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.flow;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.bankgate.server.xstream.BankAmountConverter;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankMainAccountTradeFlowQuery extends BankBaseRequest{

  private static final long serialVersionUID = 2162150807603321060L;

  @XStreamAlias("userName")
  private String bankUserName;// 登录名 char(30)

  @XStreamAlias("accountNo")
  private String mainAccountNo;// 账户 char(19)

  @XStreamAlias("lowAmount")
  @XStreamConverter(BankAmountConverter.class)
  private Long minAmount;// 最小金额 decimal(15,2)

  @XStreamAlias("upAmount")
  @XStreamConverter(BankAmountConverter.class)
  private Long maxAmount; // 最大金额 decimal(15,2)

  @XStreamAlias("startDate")
  private String startDate; // 起始日期char(8) 格式YYYYMMDD

  @XStreamAlias("endDate")
  private String endDate; // 终止日期char(8) 格式YYYYMMDD

  @XStreamAlias("pageNumber")
  @XStreamConverter(IntConverter.class)
  private Integer pageSize; // 请求记录条数，最大为20

  @XStreamAlias("startRecord")
  private Integer pageIndex; // 起始记录号 char(4)
  
  public BankMainAccountTradeFlowQuery(BankBizType bankBizType) {
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
  public Long getMinAmount() {
    return minAmount;
  }
  public void setMinAmount(Long minAmount) {
    this.minAmount = minAmount;
  }
  public Long getMaxAmount() {
    return maxAmount;
  }
  public void setMaxAmount(Long maxAmount) {
    this.maxAmount = maxAmount;
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
  public Integer getPageSize() {
    return pageSize;
  }
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
  public Integer getPageIndex() {
    return pageIndex;
  }
  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }
}

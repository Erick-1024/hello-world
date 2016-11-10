/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

import com.cana.vbam.common.annotations.NotBlank;

/**
 * @author ducer
 *
 */
public class BankMainAccountTradeFlowQueryDTO implements Serializable {

  private static final long serialVersionUID = -2513510736809314724L;
  private String bankUserName;// 登录名 char(30) 为空默认使用平台银行服务器登录名
  private String mainAccountNo;// 账户 char(19) 为空默认使用平台主账号
  @NotBlank
  private Long minAmount;// 最小金额 decimal(15,2)
  @NotBlank
  private Long maxAmount; // 最大金额 decimal(15,2)
  @NotBlank
  private String startDate; // 起始日期char(8) 格式YYYYMMDD
  @NotBlank
  private String endDate; // 终止日期char(8) 格式YYYYMMDD
  @NotBlank
  private Integer pageSize; // 请求记录条数，最大为20
  @NotBlank
  private Integer pageIndex; // 起始记录号 char(4)

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

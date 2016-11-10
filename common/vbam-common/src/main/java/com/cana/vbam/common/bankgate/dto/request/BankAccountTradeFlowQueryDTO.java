/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

import com.cana.vbam.common.annotations.NotBlank;
import com.cana.vbam.common.bankgate.enums.BankTradeType;

/**
 * 商户查询附属账户体系内附属账户的交易明细，支持分页查询。<br/>
 * 当输入分页查询条件（起始记录号、查询记录条数）时，每页最多返回10条记录。<br/>
 * 当分页查询条件为空时，查询符合查询条件的全部交易明细。<br/>
 * 
 * @author ducer
 *
 */
public class BankAccountTradeFlowQueryDTO implements Serializable {

  private static final long serialVersionUID = -6481515086099123576L;
  @NotBlank
  private String accountNo; // 附属账号
  private String queryType; // 查询类型，字符 1：查询待调账交易明细 空：查询全部交易明细
  @NotBlank
  private String startDate; // 起始日期char(8)
  @NotBlank
  private String endDate; // 终止日期char(8)
  private BankTradeType bankTradeType; // 交易类型char(2) 可空 该字段是银行交易流水查询接口参数，无法再进行细化
  private Integer pageIndex; // 起始记录号char(4)
  private Integer pageSize; // 请求记录条数 char(2) 最大为10

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getQueryType() {
    return queryType;
  }

  public void setQueryType(String queryType) {
    this.queryType = queryType;
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

  public BankTradeType getBankTradeType() {
    return bankTradeType;
  }

  public void setBankTradeType(BankTradeType bankTradeType) {
    this.bankTradeType = bankTradeType;
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

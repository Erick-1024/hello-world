/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.request.flow;

import com.cana.bankgate.server.request.BankBaseRequest;
import com.cana.bankgate.server.xstream.BankEnumConverter;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * 商户查询附属账户体系内附属账户的交易明细，支持分页查询。<br/>
 * 当输入分页查询条件（起始记录号、查询记录条数）时，每页最多返回10条记录。<br/>
 * 当分页查询条件为空时，查询符合查询条件的全部交易明细。<br/>
 * 
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankAccountTradeFlowQuery extends BankBaseRequest{

  private static final long serialVersionUID = -1061731433475572233L;
  
  @XStreamAlias("userName")
  private String bankUserName; // 登录用户
  
  @XStreamAlias("accountNo")
  private String mainAccountNo; // 主账号
  
  @XStreamAlias("subAccNo")
  private String accountNo; // 附属账号
  
  @XStreamAlias("queryType")
  private String queryType; // 查询类型 varchar(1) 1：查询待调账交易明细 空：查询全部交易明细
  
  @XStreamAlias("startDate")
  private String startDate; // 起始日期char(8)
  
  @XStreamAlias("endDate")
  private String endDate; // 终止日期char(8)
  
  @XStreamAlias("tranType")
  @XStreamConverter(BankEnumConverter.class)
  private BankTradeType bankTradeType; // 交易类型char(2) 可空 值域参见附录4.8
  
  @XStreamAlias("startRecord")
  private Integer pageIndex; // 起始记录号char(4)
  
  @XStreamAlias("pageNumber")
  private Integer pageSize; // 请求记录条数 char(2) 最大为10
  
  public BankAccountTradeFlowQuery(BankBizType bankBizType) {
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

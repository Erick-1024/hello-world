/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.flow;

import java.io.Serializable;
import java.util.List;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankMainAccountTradeFlowResult extends BankBaseResult implements Serializable {

  private static final long serialVersionUID = 770416732353244017L;

  @XStreamAlias("accountNo")
  private String accountNo; // 账号 char(19)

  @XStreamAlias("accountName")
  private String accountName; // 账户名称 varchar(60)

  @XStreamAlias("openBankName")
  private String bankName; // 开户行名称 varchar(62)

  @XStreamAlias("totalRecords")
  @XStreamConverter(IntConverter.class)
  private int totalSize; // 总记录条数 int

  @XStreamAlias("returnRecords")
  @XStreamConverter(IntConverter.class)
  private int pageSize; // 返回记录条数 int

  @XStreamAlias("list")
  private List<BankMainAccountTradeFlowData> bankMainAccountTradeFlowDatas; // 交易流水数据

  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
  public String getAccountName() {
    return accountName;
  }
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
  public int getTotalSize() {
    return totalSize;
  }
  public void setTotalSize(int totalSize) {
    this.totalSize = totalSize;
  }
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public List<BankMainAccountTradeFlowData> getBankMainAccountTradeFlowDatas() {
    return bankMainAccountTradeFlowDatas;
  }
  public void setBankMainAccountTradeFlowDatas(
      List<BankMainAccountTradeFlowData> bankMainAccountTradeFlowDatas) {
    this.bankMainAccountTradeFlowDatas = bankMainAccountTradeFlowDatas;
  }
}

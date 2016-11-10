/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducer
 *
 */
public class BankMainAccountTradeFlowResultDTO extends BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = -8345280901389630326L;

  private String mainAccountNo; // 账号 char(19)

  private String accountName; // 账户名称 varchar(60)

  private String bankName; // 开户行名称 varchar(62)

  private int totalSize; // 总记录条数 int

  private int pageSize; // 返回记录条数 int

  private List<BankMainAccountTradeFlowDataDTO> bankMainAccountTradeFlowDatas; // 交易流水数据
 
  public String getMainAccountNo() {
	return mainAccountNo;
  }
  public void setMainAccountNo(String mainAccountNo) {
	this.mainAccountNo = mainAccountNo;
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
  public List<BankMainAccountTradeFlowDataDTO> getBankMainAccountTradeFlowDatas() {
    return bankMainAccountTradeFlowDatas;
  }
  public void setBankMainAccountTradeFlowDatas(
      List<BankMainAccountTradeFlowDataDTO> bankMainAccountTradeFlowDatas) {
    this.bankMainAccountTradeFlowDatas = bankMainAccountTradeFlowDatas;
  }
}

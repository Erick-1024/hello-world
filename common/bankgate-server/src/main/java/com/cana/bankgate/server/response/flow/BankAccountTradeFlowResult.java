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
public class BankAccountTradeFlowResult extends BankBaseResult implements Serializable {

  private static final long serialVersionUID = -29920535230749194L;
  
  @XStreamAlias("returnRecords")
  @XStreamConverter(IntConverter.class)
  private int size;     // 本次返回的记录数
  
  @XStreamAlias("list")
  private List<BankAccountTradeFlowData> bankAccountTradeFlowDatas; //查询结果
  
  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }
  public List<BankAccountTradeFlowData> getBankAccountTradeFlowDatas() {
    return bankAccountTradeFlowDatas;
  }
  public void setBankAccountTradeFlowDatas(List<BankAccountTradeFlowData> bankAccountTradeFlowDatas) {
    this.bankAccountTradeFlowDatas = bankAccountTradeFlowDatas;
  }
}

/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.account;

import java.io.Serializable;
import java.util.List;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class BankInfoResult extends BankBaseResult implements Serializable {

  private static final long serialVersionUID = -8823148157364244638L;

  @XStreamAlias("list")
  // @XStreamImplicit(itemFieldName="row")
  private List<BankInfoData> bankInfoDatas;

  public List<BankInfoData> getBankInfoDatas() {
    return bankInfoDatas;
  }
  public void setBankInfoDatas(List<BankInfoData> bankInfoDatas) {
    this.bankInfoDatas = bankInfoDatas;
  }
}

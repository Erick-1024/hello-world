/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.server.response.fund;

import java.io.Serializable;
import java.util.List;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author ducer
 *
 */
@XStreamAlias("stream")
public class FreezeInfoResult extends BankBaseResult implements Serializable {

  private static final long serialVersionUID = 6857195220708207996L;

  @XStreamAlias("list")
  private List<FreezeInfoData> freezeInfoDatas;//冻结信息数据
  
  public List<FreezeInfoData> getFreezeInfoDatas() {
    return freezeInfoDatas;
  }
  public void setFreezeInfoDatas(List<FreezeInfoData> freezeInfoDatas) {
    this.freezeInfoDatas = freezeInfoDatas;
  }
}

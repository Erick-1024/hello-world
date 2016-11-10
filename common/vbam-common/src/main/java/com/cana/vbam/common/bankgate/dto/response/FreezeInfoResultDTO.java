/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducer
 *
 */
public class FreezeInfoResultDTO extends BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = 3300665035405246600L;

  private List<FreezeInfoDataDTO> freezeInfoDatas;//冻结信息数据
  
  public List<FreezeInfoDataDTO> getFreezeInfoDatas() {
    return freezeInfoDatas;
  }
  public void setFreezeInfoDatas(List<FreezeInfoDataDTO> freezeInfoDatas) {
    this.freezeInfoDatas = freezeInfoDatas;
  }
}

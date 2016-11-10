/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author ducer
 *
 */
public class BankInfoResultDTO extends BankBaseResultDTO implements Serializable {

  private static final long serialVersionUID = 8921675049655069231L;

  private List<BankInfoDataDTO> bankInfoDatas;

  public List<BankInfoDataDTO> getBankInfoDatas() {
    return bankInfoDatas;
  }

  public void setBankInfoDatas(List<BankInfoDataDTO> bankInfoDatas) {
    this.bankInfoDatas = bankInfoDatas;
  }
}

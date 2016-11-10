/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.dto.request;

import java.io.Serializable;

import com.cana.vbam.common.annotations.NotBlank;
import com.cana.vbam.common.bankgate.enums.FundBizType;


/**
 * @author ducer
 *
 */
public class TradeStatusQueryDTO implements Serializable {

  private static final long serialVersionUID = -7223501542247904824L;
  // 业务流水号，不能为空，下面的资金交易类型为业务流水的辅助查询条件，以防止不同业务类型有相同的流水号.
  // 如果能保证所有模块所有流水都不相同，则可以只用业务流水查询
  @NotBlank
  private String businessSeq;
  // 原请求代码char(8)，可空，若客户能保证各交易类型的流水号唯一，则可空，否则需上送原请求代码。 资金初始化：DLFNDINI
  // 调账入款：DLTRSFIN 错账调回：DLWFDRTN 入金：DLFONDIN 出金：DLFNDOUT 保证金退还：DLGTYRTN 转账：DLSUBTRN；
  // 强制转账：DLMDETRN；平台出金：DLFCSOUT；手工结息：DLIRTSTL
  private FundBizType fundBizType;

  public String getBusinessSeq() {
    return businessSeq;
  }
  public void setBusinessSeq(String businessSeq) {
    this.businessSeq = businessSeq;
  }
  public FundBizType getFundBizType() {
    return fundBizType;
  }
  public void setFundBizType(FundBizType fundBizType) {
    this.fundBizType = fundBizType;
  }
}

/**
 *  Copyright © 2016 Cana. All rights reserved.
 */
package com.cana.vbam.common.bankgate.enums;

/**
 * 如果调用端只关心成功、失败、未知三种状态，可使用{@link BankTranStatus#toBaseStatus()}获取值.<br>
 * 如果需要使用更详细的状态标志，请使用{@link BankTranStatus}
 * @author ducer
 *
 */
public enum BaseTranStatus {
  
  success,
  fail,
  handling;
  
}

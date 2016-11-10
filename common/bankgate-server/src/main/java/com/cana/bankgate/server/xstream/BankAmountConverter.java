/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.xstream;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.converters.basic.LongConverter;

/**
 * 网关端会对所有的资金进行Long<->String （分<->元）的转换
 * 
 * @author ducer
 *
 */
public class BankAmountConverter extends LongConverter {


  @SuppressWarnings("rawtypes")
  @Override
  public boolean canConvert(Class type) {
    return super.canConvert(type);
  }

  @Override
  public Object fromString(String str) {
    if (StringUtils.isBlank(str)) return 0L;
    BigDecimal bd = new BigDecimal(str);
    return bd.multiply(new BigDecimal(100)).longValue();
  }

  @Override
  public String toString(Object obj) {
    if (obj == null) return "";
    BigDecimal bd = new BigDecimal(String.valueOf(obj));
    return bd.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
  }
}

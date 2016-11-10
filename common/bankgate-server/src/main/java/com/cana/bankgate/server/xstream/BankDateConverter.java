/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.xstream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Throwables;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author ducer
 *
 */
public class BankDateConverter extends AbstractSingleValueConverter {

  private SimpleDateFormat formatter;

  public BankDateConverter() {
    this("yyyyMMddHHmmss");
  }

  public BankDateConverter(String dateFormat) {
    formatter = new SimpleDateFormat(dateFormat);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean canConvert(Class type) {
    return Date.class.isAssignableFrom(type) || Date.class == type;
  }

  @Override
  public Object fromString(String str) {
    if(StringUtils.isBlank(str)) return null;
    try {
      return formatter.parse(str);
    } catch (ParseException e) {
      Throwables.propagate(e);
    }
    return null;
  }

  @Override
  public String toString(Object obj) {
    if(obj == null) return "";
    return formatter.format(obj);
  }
}

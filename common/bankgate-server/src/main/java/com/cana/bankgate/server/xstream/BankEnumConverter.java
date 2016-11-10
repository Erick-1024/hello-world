/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.xstream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.bankgate.server.enums.BankBizStatus;
import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author ducer
 *
 */
public class BankEnumConverter extends AbstractSingleValueConverter {

  private Logger logger = LoggerFactory.getLogger(getClass());
  private Gson gson = new Gson();// thread safe
  @SuppressWarnings("rawtypes")
  private final Class<? extends Enum> enumType;
  private String getCode;
  private String parseEnum;

  @SuppressWarnings("rawtypes")
  public BankEnumConverter(Class<? extends Enum> type) {
    this(type, "getCode", "parseEnum");
  }

  @SuppressWarnings("rawtypes")
  public BankEnumConverter(Class<? extends Enum> type, String getCode, String parseEnum) {
    if (!Enum.class.isAssignableFrom(type) && type != Enum.class
        && !BankBizStatus.class.isAssignableFrom(type)) {
      throw new IllegalArgumentException("Converter can only handle defined enums");
    }
    enumType = type;
    this.getCode = getCode;
    this.parseEnum = parseEnum;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean canConvert(Class type) {
    return enumType.isAssignableFrom(type) || BankBizStatus.class.isAssignableFrom(enumType);
  }

  @Override
  public String toString(Object obj) {
    if (obj == null) return "";
    if (BankBizStatus.class.isAssignableFrom(enumType)) {
      return gson.toJson(obj);
    }
    try {
      Method method = enumType.getMethod(getCode);
      return (String) method.invoke(obj);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      logger.error("Converter enum to xml string fail.EveryEnum must has function getCode().", e);
      Throwables.propagate(e);
    }
    return "";
  }

  @Override
  public Object fromString(String code) {
    if (BankBizStatus.class.isAssignableFrom(enumType)) {
      return BankBizStatus.parseEnum(code);
    }
    try {
      Method method = enumType.getMethod(parseEnum, String.class);
      return method.invoke(null, code);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      logger.error("Converter xml string to enum fail.EveryEnum must has function parseEnum.", e);
      Throwables.propagate(e);
    }
    return null;
  }
}

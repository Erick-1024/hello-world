/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.bankgate.server.enums.BankgateError;
import com.cana.bankgate.server.throwables.ValidateException;
import com.google.common.base.Throwables;

/**
 * @author ducer
 *
 */
public class LogExceptionUtil {

  private static Logger logger = LoggerFactory.getLogger(LogExceptionUtil.class);

  public static void logAndthrow(BankgateError error, Throwable e) {
    logAndthrow(error.value(), e);
  }

  public static void logAndthrow(String msg, Throwable e) {
    logger.error(msg, e);
    Throwables.propagate(e);
  }

  public static void logAndthrow(BankgateError error) {
    logAndthrow(error.value());
  }

  public static void logAndthrow(String msg) {
    RuntimeException e = new RuntimeException(msg);
    logger.error(msg);
    throw e;
  }

  public static void log(BankgateError error, Throwable e) {
    log(error.value(), e);
  }

  public static void log(String msg, Throwable e) {
    logger.error(msg, e);
  }

  public static void log(String format,Object... msgs){
    logger.info(format, msgs);
  }
  
  public static void validateFail(String message) {
    logger.error(message);
    throw ValidateException.newInstance(message);
  }
}

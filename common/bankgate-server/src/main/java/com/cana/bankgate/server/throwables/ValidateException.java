/**
 * Copyright (c) 2016-2100 Cana, Inc. All rights reserved.
 */
package com.cana.bankgate.server.throwables;

/**
 * 参数校验异常，用以区别网关的运行时异常
 * 
 * @author ducer
 *
 */
public class ValidateException extends RuntimeException {

  private static final long serialVersionUID = 7987738175234603931L;

  public ValidateException() {
    super("参数校验失败");
  }

  public ValidateException(String message) {
    super(message);
  }

  public static ValidateException newInstance(String message) {
    return new ValidateException(message);
  }
}

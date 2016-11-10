/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.constants;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

/**
 * Bankgate常量类，对一些常量进行初始化
 * 
 * @author ducer
 *
 */
public class BankgateConstant {

  private static final Logger logger = LoggerFactory.getLogger(BankgateConstant.class);

  public static final BankgateServerConfig config;

  public static final String schema;
  
  public static final long timeOffset;
  
  static{
    logger.info("=======================获取BankgateServer启动常量============================");
    config = resolveConfig(ConfScope.G);
    timeOffset = initTimeZoneOffset();
    schema = initSchema();
    
    logger.info("============================常量初始化完毕===================================");
  }

  private static BankgateServerConfig resolveConfig(ConfScope scope) {
    BankgateServerConfig config = new BankgateServerConfig();
    try {
      BeanUtils.copyProperties(config, readeConfig(scope));
    } catch (IllegalAccessException e) {
      logger.error("", e);
      Throwables.propagate(e);
    } catch (InvocationTargetException e) {
      logger.error("", e);
      Throwables.propagate(e);
    }
    logger.info(new Gson().toJson(config));
    return config;
  }

  private static Properties readeConfig(ConfScope scope) {
    return TopsConfReader.getConfProperties("properties/bank-gate.properties", scope);
  }

  private static String initSchema() {
    return "<?xml version=\"1.0\" encoding=\""
           .concat(config.getRequestCharset())
           .concat("\"?>\r\n");
  }

  private static long initTimeZoneOffset(){
    Calendar calendar = Calendar.getInstance();
    long offset = calendar.getTimeZone().getRawOffset();
    logger.info("TimeZone Offset {}",offset);
    return offset;
  }
}

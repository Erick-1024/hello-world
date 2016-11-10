/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.bankgate.server.bank.BankClient;
import com.cana.bankgate.server.constants.BankgateConstant;
import com.cana.bankgate.server.request.BankBaseRequest;
import com.google.common.base.Throwables;

/**
 * @author ducer
 *
 */
public class HttpUtil {

  private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
  
  public static <T, B extends BankBaseRequest> T request(B xmlRequest, Class<T> rc) {
    BankClient client = new BankClient(BankgateConstant.config);
    String xml = BankgateConstant.schema.concat(XStreamUtil.toXml(xmlRequest));
    logger.info(xmlRequest.getBankBizType().getDesc() + "请求报文:" + xml);
    String response = null;
    try {
      response = client.request(xml);
    } catch (Throwable e) {
      logger.error("", e);
      Throwables.propagate(e);
    }
    logger.info(xmlRequest.getBankBizType().getDesc() + "响应报文:" + response);
    return (T) XStreamUtil.fromXml(response, rc);
  }

}

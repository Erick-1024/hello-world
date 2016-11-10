/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.bank;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.bankgate.server.constants.BankgateServerConfig;

/**
 * 银行客户端封装，用以向银行发送请求
 * 
 * @author ducer
 *
 */
public class BankClient {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final HttpClient client = new HttpClient();

  private BankgateServerConfig config;

  public BankClient(BankgateServerConfig config) {
    this.config = config;
  }

  public BankgateServerConfig getConfig() {
    return config;
  }

  /**
   * 连接超时和请求超时都会抛出异常，并在API实现端进行捕获
   * 
   * @param xml
   * @return
   * @throws HttpException
   * @throws UnsupportedEncodingException
   * @throws ConnectException
   * @throws ConnectTimeoutException
   * @throws SocketTimeoutException
   * @throws IOException
   */
  public String request(String xml) throws HttpException, UnsupportedEncodingException,
      ConnectException, ConnectTimeoutException, SocketTimeoutException, IOException {
    String contentType = getConfig().getContentType();
    String charset = getConfig().getResponseCharset();
    String url = getConfig().getUrl();
    HttpClientParams params = new HttpClientParams();
    params.setConnectionManagerTimeout(getConfig().getConnectionTimeout());
    params.setSoTimeout(getConfig().getTimeout());
    client.setParams(params);
    EntityEnclosingMethod method = new PostMethod(url);
    RequestEntity entity = new StringRequestEntity(xml, contentType, charset);
    method.setRequestEntity(entity);
    byte[] body = null;
    int status = 0;
    long start = System.currentTimeMillis();
    try {
      status = client.executeMethod(method);
      body = method.getResponseBody();
    } catch (Exception e) {
      if (ExceptionUtils.indexOfThrowable(e, ConnectTimeoutException.class) != -1) {
        logger.info("网关ConnectTimeout超时阈统计:{}", System.currentTimeMillis() - start);
      }
      else if (ExceptionUtils.indexOfThrowable(e, SocketTimeoutException.class) != -1) {
        logger.info("网关ReadTimeout超时阈统计:{}", System.currentTimeMillis() - start);
      }
      logger.info("Request fail throws Exception");
      throw e;
    } finally {
      method.releaseConnection();
    }
    if (status != HttpServletResponse.SC_OK) {
      logger.info("Request fail.Status code {}", status);
      throw new RuntimeException("Request fail.Status code " + status);
    }
    return new String(body, config.getResponseCharset());
  }
}

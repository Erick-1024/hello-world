/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.codehaus.plexus.util.ExceptionUtils;

import com.cana.bankgate.server.enums.BankgateError;
import com.cana.bankgate.server.throwables.ValidateException;
import com.cana.bankgate.server.transaction.IBankgateTransactionService;
import com.cana.bankgate.server.utils.LogExceptionUtil;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.dianping.cat.Cat;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

/**
 * @author ducer
 *
 */
public class BankgateServiceInvoker<T extends BankBaseResultDTO> implements InvocationHandler {

  private static IBankgateTransactionService bankgateTransService = SpringApplicationContext.getApplicationContext().getBean(IBankgateTransactionService.class);
  private static final String cat_prefix = "网关请求银行-";
  private static final String cat_conn_refuse = "请求拒绝";
  private static final String cat_conn_timeout = "连接超时";
  private static final String cat_read_timeout = "请求超时";
  private static final String cat_other_error = "其他异常";
  private static final String cat_success = "成功";
  private static final String cat_fail = "失败";
  private static final String cat_handling = "需要审核";
  private static final String cat_poor_balance = "账户余额不足";

  private Object context;
  private BankBizType bizType;
  private Class<T> returnType;
  private boolean update;
  private String transId;

  /**
   * 不会更新流水状态
   * @param context
   * @param bizType
   * @param returnType
   * @return
   */
  public static <K,T extends BankBaseResultDTO> K bind(Object context, BankBizType bizType, Class<T> returnType) {
   return bind(context,bizType,returnType,false,null);
  }
  
  /**
   * 当update为true时会更新流水状态，必须传入transId
   * @param context
   * @param bizType
   * @param returnType
   * @param update
   * @param transId
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <K,T extends BankBaseResultDTO> K bind(Object context, BankBizType bizType, Class<T> returnType, boolean update, String transId) {
    BankgateServiceInvoker<T> invoker = new BankgateServiceInvoker<T>();
    invoker.context = context;
    invoker.bizType = bizType;
    invoker.returnType = returnType;
    invoker.transId = transId;
    invoker.update = update;
    return (K)Proxy.newProxyInstance(context.getClass().getClassLoader(), context.getClass().getInterfaces(), invoker);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    try {
      T result = (T) method.invoke(context, args);
      BankTranStatus status = result.getStatus();
      if (BankTranStatus.success.equals(status)) {
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_success);
      } else if (BankTranStatus.fail.equals(status)) {
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_fail);
      } else if (BankTranStatus.poor_balance.equals(status)) {
    	Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_fail);
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_poor_balance);
      } else {
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_handling);
      }
      if (update) {
        bankgateTransService.updateStatusById(transId, status);
      }
      return result;
    } catch (Throwable e) {
      Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_fail);
      if (ExceptionUtils.indexOfThrowable(e, ConnectException.class) != -1) {// 请求拒绝
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_conn_refuse);
        LogExceptionUtil.log(BankgateError.conn_refuse.value(), e);
        if (update) {
          bankgateTransService.updateStatusById(transId, BankTranStatus.conn_timeout);
        }
        return wrapperBaseResult(BankTranStatus.conn_timeout, BankgateError.conn_refuse, returnType);
      }
      if (ExceptionUtils.indexOfThrowable(e, ConnectTimeoutException.class) != -1) {// 连接超时
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_conn_timeout);
        LogExceptionUtil.log(BankgateError.conn_timeout.value(), e);
        if (update) {
          bankgateTransService.updateStatusById(transId, BankTranStatus.conn_timeout);
        }
        return wrapperBaseResult(BankTranStatus.conn_timeout, BankgateError.conn_timeout, returnType);
      }
      if (ExceptionUtils.indexOfThrowable(e, SocketTimeoutException.class) != -1) {// 请求超时
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_read_timeout);
        LogExceptionUtil.log(BankgateError.read_timeout.value(), e);
        if (update) {
          bankgateTransService.updateStatusById(transId, BankTranStatus.timeout);
        }
        return wrapperBaseResult(BankTranStatus.timeout, BankgateError.read_timeout, returnType);
      }
      if (ExceptionUtils.indexOfThrowable(e, ValidateException.class) != -1) {// 参数校验失败
        Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_other_error);
        LogExceptionUtil.log(BankgateError.validate_fail.value(), e);
        if (update) {
          bankgateTransService.updateStatusById(transId, BankTranStatus.fail);
        }
        return wrapperBaseResult(BankTranStatus.fail, BankgateError.validate_fail, returnType);
      }
      Cat.logMetricForCount(cat_prefix + bizType.getDesc() + cat_other_error);
      LogExceptionUtil.log(BankgateError.gate_error.value(), e);
      if (update) {
        bankgateTransService.updateStatusById(transId, BankTranStatus.gate_error);
      }
      return wrapperBaseResult(BankTranStatus.fail, BankgateError.gate_error, returnType, e);
    }
  }

  private static <T extends BankBaseResultDTO> T wrapperBaseResult(BankTranStatus status,
      BankgateError error, Class<T> clazz) throws Exception {
    T t = clazz.newInstance();
    t.setStatus(status);
    t.setStatusText(error.value());
    return t;
  }
  
  private static <T extends BankBaseResultDTO> T wrapperBaseResult(BankTranStatus status,
      BankgateError error, Class<T> clazz, Throwable th) throws Exception {
    T t = wrapperBaseResult(status, error, clazz);
    t.setStatusText(error.value() + "-" + th.getMessage());
    return t;
  }
}

/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.test.fund;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.TransferFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml"})
public class TransferFundTest extends AbstractJUnit4SpringContextTests {

  @Resource
  private BankgateApi client;

  @Test
  public void test() throws Exception {
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转账100");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println(JSON.toJSON(result));
  }

  
  public void testAmountLimit() throws Exception { // 转账金额多余可用余额
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(999999L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转账"+999999L);
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println("测试转账金额多余可用余额：" + JSON.toJSON(result));
  }

  
  public void testAccountNotExist() throws Exception { // 转出账号不存在
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034191");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转账100分");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println("转出账号不存在：" + JSON.toJSON(result));
  }
  
 
  public void testReceiveAccountNotExist() throws Exception { // 收款账号不存在
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转账100分");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210003631007400"); // 这个账号是存在的
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println("测试收款账号不存在：" + JSON.toJSON(result));
  }

  
  public void testReceiveNameNotExist() throws Exception { // 收款户名不存在
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转账100分");
    dto.setReceiveAccountName("zengzengzeng");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println("收款户名不存在：" + JSON.toJSON(result));
  }
  
  
  public void testTransferInSameAccountNo() throws Exception { // 同一个账号内进行转账
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转账100分");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034194");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println("同一个账号内转账" + JSON.toJSON(result));
  }
 
  
  public void testReceiveAccountNotMatchName() throws Exception { // 账号和户名不匹配
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转账100分");
    dto.setReceiveAccountName("xumengrongzi");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println("测试账号和户名不匹配：" + JSON.toJSON(result));
  }

  
  public void testMemoTooLong() throws Exception { // 备注过多
    TransferFundDTO dto = new TransferFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("转ddddddddddddddddddddddddddddddddddddddddddddddddddddddddd账100");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.transferFund(dto);
    System.out.println("转账备注过长：" + JSON.toJSON(result));
  }
  
}

/**
 *  Copyright © 2015 Cana. All rights reserved. 
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
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class FreezePayTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    TransferFundDTO dto = new TransferFundDTO();
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setMemo("冻结支付100分");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.freezePay(dto);
    System.out.println(JSON.toJSON(result));
  }
}
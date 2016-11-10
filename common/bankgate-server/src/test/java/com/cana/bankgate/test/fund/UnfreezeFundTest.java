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
import com.cana.vbam.common.bankgate.dto.request.UnfreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class UnfreezeFundTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    UnfreezeFundDTO dto = new UnfreezeFundDTO();
    dto.setOriginBusinessSeq("27500680427614");
    dto.setTransDate(DateTimeUtil.date8());
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("解冻100");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034222");
    dto.setFreezeNo("2016000733972");
    BankBaseResultDTO result = client.unfreezeFund(dto);
    System.out.println(JSON.toJSON(result));
  }
}
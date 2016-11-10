/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.test.account;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBaseDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class CancelAccountTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    BankAccountBaseDTO dto = new BankAccountBaseDTO();
    dto.setAccountNo("3110210010051034797");
    dto.setTransDate(DateTimeUtil.date8());
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    BankAccountDTO result = client.cancelAccount(dto);
    System.out.println(JSON.toJSON(result));
  }
}
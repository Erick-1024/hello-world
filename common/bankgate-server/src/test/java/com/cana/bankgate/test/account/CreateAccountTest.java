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
import com.cana.vbam.common.bankgate.dto.request.BankAccountCreateDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class CreateAccountTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    BankAccountCreateDTO dto = new BankAccountCreateDTO();
    dto.setAccountName("长寿旅行社");
    dto.setTransDate(DateTimeUtil.date8());
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    BankAccountDTO result = client.createBankAccount(dto);
    System.out.println(JSON.toJSON(result));
  }
}
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
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class QueryAccountBalanceTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    BankAccountBalanceQueryDTO dto = new BankAccountBalanceQueryDTO();
    dto.setAccountNo("3110210010051034194");
    BankAccountBalanceResultDTO result = client.queryAccountBalance(dto);
    System.out.println(JSON.toJSON(result));
  }
}

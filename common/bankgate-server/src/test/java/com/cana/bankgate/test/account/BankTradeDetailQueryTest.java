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
import com.cana.vbam.common.bankgate.dto.request.BankAccountTradeFlowQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowResultDTO;


@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class BankTradeDetailQueryTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
	BankAccountTradeFlowQueryDTO dto = new BankAccountTradeFlowQueryDTO();
    dto.setAccountNo("3110210003631006431");
    dto.setStartDate("20161001");
    dto.setEndDate("20161007");
    BankAccountTradeFlowResultDTO result = client.queryBankAccountTradeFlow(dto);
    System.out.println(JSON.toJSON(result));
  }
}
/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.test.account;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.google.common.collect.Lists;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml"})
public class MainAccountBalanceQueryTest extends AbstractJUnit4SpringContextTests {

  @Resource
  private BankgateApi api;

  @Test
  public void test() throws Exception {
    BankMainAccountBalanceQueryDTO dto = new BankMainAccountBalanceQueryDTO();
    List<String> nos = Lists.newArrayList("7315910182600000908","7315910182600000908");
    //dto.setMainAccountNos(nos);
    //dto.setBankUserName("MSBLSH");
    BankMainAccountBalanceResultDTO result = api.queryBankMainAccountBalance(dto);
    System.out.println(JSON.toJSON(result));
  }
}

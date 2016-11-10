/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.test.flow;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountTradeFlowQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTradeLimit;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml"})
public class MainAccountTradeFlowQueryTest extends AbstractJUnit4SpringContextTests {

  @Resource
  private BankgateApi api;

  @Test
  public void test() throws Exception {
    BankMainAccountTradeFlowQueryDTO dto = new BankMainAccountTradeFlowQueryDTO();
    //dto.setMainAccountNo("7315910182600000908");
    //dto.setBankUserName("MSBLSH");
    dto.setStartDate(DateTimeUtil.date8());
    dto.setEndDate(DateTimeUtil.date8());
    dto.setMinAmount(BankTradeLimit.min.getLimit());
    dto.setMaxAmount(BankTradeLimit.max.getLimit());
    dto.setPageIndex(1);
    dto.setPageSize(10);
    BankMainAccountTradeFlowResultDTO result = api.queryBankMainAccountTradeFlow(dto);
    System.out.println(JSON.toJSON(result));
  }
}

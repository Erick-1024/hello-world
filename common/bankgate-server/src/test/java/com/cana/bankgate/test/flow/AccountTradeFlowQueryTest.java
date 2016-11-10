/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.test.flow;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.BankAccountTradeFlowQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTradeType;
import com.cana.vbam.common.bankgate.enums.DebitCreditTag;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml"})
public class AccountTradeFlowQueryTest extends AbstractJUnit4SpringContextTests {

  @Resource
  private BankgateApi api;
  
  @Test
  public void testQueryDeposit() throws Exception{
    BankAccountTradeFlowQueryDTO dto = new BankAccountTradeFlowQueryDTO();
    dto.setAccountNo("3110210010051034194");
    DateTime start = DateTime.now().minusDays(8);
	DateTime end = DateTime.now();
	dto.setStartDate(DateTimeUtil.date8(start));
	dto.setEndDate(DateTimeUtil.date8(end)); // 银行测试服务器的时间比实际时间提前了105天
    dto.setBankTradeType(BankTradeType.ordinary_external_transfer);
    dto.setPageIndex(1);
    dto.setPageSize(10);
    BankAccountTradeFlowResultDTO result = api.queryBankAccountTradeFlow(dto);
    System.out.println("入金流水,Size"+result.getSize());
    System.out.println(JSON.toJSON(result));
    if(CollectionUtils.isNotEmpty(result.getBankAccountTradeFlowDatas())) {
      for(BankAccountTradeFlowDataDTO o : result.getBankAccountTradeFlowDatas()) {
        if(DebitCreditTag.credit.equals(o.getDebitCreditTag()))
        System.out.println(JSON.toJSON(o));
      }
    }
  }
  
  
  public void testQueryAll() throws Exception{
    BankAccountTradeFlowQueryDTO dto = new BankAccountTradeFlowQueryDTO();
    dto.setAccountNo("3110210010051034194");
    DateTime start = DateTime.now().minus(8);
    DateTime end = DateTime.now();
    dto.setStartDate(DateTimeUtil.date8(start));
    dto.setEndDate(DateTimeUtil.date8(end)); // 银行测试服务器的时间比实际时间提前了105天
    dto.setPageIndex(1);
    dto.setPageSize(10);
    BankAccountTradeFlowResultDTO result = api.queryBankAccountTradeFlow(dto);
    System.out.println("所有流水,Size"+result.getSize());
    System.out.println(JSON.toJSON(result));
  }
}

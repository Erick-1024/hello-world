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
import com.cana.vbam.common.bankgate.dto.request.TradeStatusQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.FundBizType;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class TradeStatusQueryTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    TradeStatusQueryDTO dto = new TradeStatusQueryDTO();
    dto.setBusinessSeq("16062415062212901");
    dto.setFundBizType(FundBizType.platform_withdraw_fund);
    TradeStatusResultDTO result = client.queryTradeStatus(dto);
    System.out.println(JSON.toJSON(result));
  }
  
  
  public void testNoType() throws Exception{
    TradeStatusQueryDTO dto = new TradeStatusQueryDTO();
    dto.setBusinessSeq("29798014490432");
    TradeStatusResultDTO result = client.queryTradeStatus(dto);
    System.out.println("不指定交易类型:"+JSON.toJSON(result));
  }
}
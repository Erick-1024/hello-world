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
import com.cana.vbam.common.bankgate.dto.request.FreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class FreezeFundTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    FreezeFundDTO dto = new FreezeFundDTO();
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setAccountNo("3110210010051034222"); // 3110210010051034194
    dto.setAmount(200L);
    dto.setMemo("冻结200分");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.freezeFund(dto);
    System.out.println(JSON.toJSON(result));
  }
}
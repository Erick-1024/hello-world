/**
 *  Copyright © 2015 Cana. All rights reserved. 
 */
package com.cana.bankgate.test.fund;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.FreezeInfoQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.FreezeInfoResultDTO;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class FreezeInfoQueryTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    FreezeInfoQueryDTO dto = new FreezeInfoQueryDTO();
    dto.setAccountNo("3110210010051034222");
    dto.setEndDate(DateTime.now().toDate());
    dto.setStartDate(DateTime.now().withTime(0, 0, 0, 0).toDate());
    FreezeInfoResultDTO result = client.queryFreezeInfo(dto);
    System.out.println(JSON.toJSON(result));
  }
}
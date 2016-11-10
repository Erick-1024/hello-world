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
import com.cana.vbam.common.bankgate.dto.request.UnfreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class UnfreezePayTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  @Test
  public void test() throws Exception{
    UnfreezeFundDTO dto = new UnfreezeFundDTO();
    dto.setAccountNo("3110210010051034222");//3110210003631006943 测试环境有钱的账号
    dto.setTransDate(DateTimeUtil.date8());
    dto.setAmount(200L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));//3110210003631006964 测试环境有点点钱的账号
    dto.setOriginBusinessSeq("27724814742531");
    dto.setMemo("解冻支付200");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034194");
    dto.setFreezeNo("2016000734045");
    BankBaseResultDTO result = client.unfreezePay(dto);
    System.out.println(JSON.toJSON(result));
  }
}
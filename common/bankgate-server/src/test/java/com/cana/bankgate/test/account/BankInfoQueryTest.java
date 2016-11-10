/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.test.account;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.BankInfoQueryDTO;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml"})
public class BankInfoQueryTest extends AbstractJUnit4SpringContextTests {

  @Resource
  private BankgateApi client;

  @Test
  public void test() throws Exception {
    BankInfoQueryDTO dto = new BankInfoQueryDTO();
    dto.setBankPaymentName("工商银行");
    dto.setProvinceName("贵州");
    dto.setCityName("贵阳");
    //dto.setBankPaymentNo("103100006390");
    //BankInfoDTO result = client.queryBankInfo(dto);
    //System.out.println(JSON.toJSON(result));
  }
}

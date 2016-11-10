/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.test.generate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.bankgate.api.BankgateApi;
import com.cana.bankgate.server.bank.BankClient;
import com.cana.bankgate.server.constants.BankgateConstant;
import com.cana.bankgate.server.mapper.gen.BankgateTransMapper;
import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.po.BankgateTransExample;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankMainAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class BankGateTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  @Resource
  private BankgateTransMapper bankgateTransMapper;
  
  public void main(String[] args) throws Exception {
    // 对于发送和接收的具体流程如下：
    // System.out.println(HttpUtil.request(new AttachAccountInfo()));
    String xml = 
            "<?xml version=\"1.0\" encoding=\"GBK\"?>" + 
            "<stream>" + "<action>DLBALQRY</action>" + 
            "<userName>MSBLSH</userName>" + 
            "<list name=\"userDataList\">" + 
            "<row>" + 
            "<accountNo>7315910182600000908</accountNo>" + 
            "</row>" + 
            "</list>" + 
            "</stream>";
    BankClient client = new BankClient(BankgateConstant.config);
    System.out.println(client.request(xml));
  }
	
  
  public void getQuery() {
    BankgateTransExample ex = new BankgateTransExample();
    ex.setLimitStart(0);
    ex.setLimitEnd(1);
    ex.createCriteria().andAccountNoIsNotNull();
    List<BankgateTrans> transes = bankgateTransMapper.selectByExample(ex);
    if (CollectionUtils.isEmpty(transes)) {
      logger.info("网关定时刷新Session任务，流水表无记录");
      System.out.println("xxxxxxxxxxxxx");
    }
    String accountNo = transes.get(0).getAccountNo();
    BankAccountBalanceQueryDTO query = new BankAccountBalanceQueryDTO();
    query.setAccountNo(accountNo);
    System.out.println("xxxxxxxxxxxxx" + query.getAccountNo());
  }
  
  @Test
  public void test() {
    try {
      BankMainAccountBalanceQueryDTO query = new BankMainAccountBalanceQueryDTO();
      BankMainAccountBalanceResultDTO result = client.queryBankMainAccountBalance(query);
      if (!BankTranStatus.success.equals(result.getStatus())) {
        System.out.println("网关定时刷新Session任务,返回失败原因:" + result.getStatusText());
      }
    } catch (Exception e) {
      System.out.println("网关定时刷新Session任务");
    }
  }
}

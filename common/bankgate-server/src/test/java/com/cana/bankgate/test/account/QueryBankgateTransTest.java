package com.cana.bankgate.test.account;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.fastjson.JSON;
import com.cana.bankgate.api.BankgateApi;
import com.cana.bankgate.server.mapper.gen.BankgateTransMapper;
import com.cana.vbam.common.bankgate.dto.response.BankgateTransDTO;
import com.google.common.collect.Lists;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class QueryBankgateTransTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  @Resource
  private BankgateTransMapper bankgateMapper;
  
  @Test
  public void test() throws Exception{
    List<String> bizSeqs = Lists.newArrayList("151123135033301","151123144430501","151123144657701");
    List<BankgateTransDTO> result = client.queryBankgateTrans(bizSeqs);
    System.out.println(JSON.toJSON(result));
  }
}
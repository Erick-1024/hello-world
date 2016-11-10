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
import com.cana.vbam.common.bankgate.dto.request.PlatformWithdrawFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.enums.BankFlag;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class PlatformWithdrawFundTest extends AbstractJUnit4SpringContextTests{

  @Resource
  private BankgateApi client;
  
  //  提现都没有实时性，除了失败，返回结果都是handling，都需要调用交易查询接口
  @Test
  public void testCitic() throws Exception{ // 中信银行提现  能提现到主账号里面去，但没有手续费
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210003631006431");
    dto.setAmount(5L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金5");
    dto.setReceiveAccountName("上海新共赢信息科技有限公司");
    dto.setReceiveAccountNo("8110701013400006442");
    dto.setReceiveBankNo("302100011106");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("中信银行提现:"+JSON.toJSON(result));
  }
  
  
  public void testAttachAccount() throws Exception{ // 提现到虚拟账号  能提交请求，能提现,但多数虚拟账号会被银行卡好算法验证过滤
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210010051034222");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金100");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("3110210010051034194");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("提现到虚拟账号:"+JSON.toJSON(result));
  }
  
  
  public void testAmountLimit() throws Exception{  // 提现金额多于账户余额 处理中，通过调用查询交易状态接口会得到，账户可用资金不足
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210010051034222");
    dto.setAmount(9000L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金9000分");
    dto.setReceiveAccountName("上海凯拿资产管理有限公司");
    dto.setReceiveAccountNo("8110201013300209680");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println(JSON.toJSON(result));
  }
  
  
  public void testAccountNoNoExist() throws Exception{  // 提现账号不存在  非本平台会被平台验证过滤掉
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210003631000000");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金100");
    dto.setReceiveAccountName("上海凯拿资产管理有限公司");
    dto.setReceiveAccountNo("8110201013300209680");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("提现账号不存在："+JSON.toJSON(result));
  }
  
  
  public void testReceiveAccountNoNotExist() throws Exception{  // 收款账号不存在 获取交易网点失败
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金100");
    dto.setReceiveAccountName("上海凯拿资产管理有限公司");
    dto.setReceiveAccountNo("6214830270946022");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("收款账号不存在："+JSON.toJSON(result));
  }
  
  
  public void testReceiveAccountNameNotExist() throws Exception{  // 收款账号户名不存在 上送账户信息不正确
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金100");
    dto.setReceiveAccountName("duceng");
    dto.setReceiveAccountNo("8110201013300209680");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("收款账号户名不存在："+JSON.toJSON(result));
  }
  
  
  public void testReceiveAccountNoNotMatchName() throws Exception{  // 收款账号和名称不匹配 上送账户信息不正确
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金100");
    dto.setReceiveAccountName("coyzeng");
    dto.setReceiveAccountNo("8110201013300209680");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("收款账号和名称不匹配："+JSON.toJSON(result));
  }
  
  
  public void testMemoTooLong() throws Exception{  // 备注过长  可以提现
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210010051034222");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd金100");
    dto.setReceiveAccountName("上海凯拿资产管理有限公司");
    dto.setReceiveAccountNo("8110201013300209680");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("测试备注过长:"+JSON.toJSON(result));
  }
  
  
  public void testNotCiticBankButTagIs() throws Exception{  // 测试中信提现，但是账号不是中信账号 获取交易网点失败
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setAccountNo("3110210010051034222");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金100");
    dto.setReceiveAccountName("顾光公");
    dto.setReceiveAccountNo("6214830270946022");
    dto.setTransDate(DateTimeUtil.date8());
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("中信提现，但是账号不是中信账号:"+JSON.toJSON(result));
  }
  
  
  public void testCiticBankButTagIsNot() throws Exception{  // 测试非中信提现，但是账号是中信账号 NPC运行时序错误
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setBankFlag(BankFlag.other_bank);
    dto.setAccountNo("3110210010051034222");
    dto.setAmount(100L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金100");
    dto.setReceiveAccountName("上海凯拿资产管理有限公司");
    dto.setReceiveAccountNo("8110201013300209680");
    dto.setTransDate(DateTimeUtil.date8());
    dto.setReceiveBankName("中信银行上海静安支行");
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("非中信提现，但是账号是中信账号:"+JSON.toJSON(result));
  }
  
  @Test
  public void testOtherBank() throws Exception{  // 测试其他行提现 NPC运行时序错误
    PlatformWithdrawFundDTO dto = new PlatformWithdrawFundDTO();
    dto.setBankFlag(BankFlag.other_bank);
    dto.setAccountNo("3110210010051034194");
    dto.setAmount(1000L);
    dto.setBusinessSeq(String.valueOf(System.nanoTime()));
    dto.setMemo("出金1000");
    dto.setReceiveAccountName("顾光公");
    dto.setReceiveAccountNo("6214830270946022");
    dto.setTransDate(DateTimeUtil.date8());
    dto.setReceiveBankName("招商银行武汉光谷支行");
    BankBaseResultDTO result = client.platformWithdrawFund(dto);
    System.out.println("测试其他行提现:"+JSON.toJSON(result));
  }
}
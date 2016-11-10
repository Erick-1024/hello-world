package com.cana.account.server.test.trade;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.service.IAccountTradeService;
import com.cana.account.service.transaction.IAccountAuthorityTransactionService;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/account-server-*.xml")
public class AccountTradeRuleTest {

    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountTableLockMapper accountTableLockMapper;

    @Resource
    private IAccountTradeService accountTradeService;
    
    @Resource
    private IAccountAuthorityTransactionService accountAuthorityTransactionService;

    @Test
    public void tradeRuleTest() {
        User customer = userMapper.selectByPrimaryKey("201512211517");
        Account outAccount = accountTableLockMapper.lockAccountByAccountNo("3110210003631007672");
        Account inAccount = accountTableLockMapper.lockAccountByAccountNo("3110210003631007642");
        accountAuthorityTransactionService.checkTransferAuthority(customer, outAccount, inAccount);
    }
    
    static String factorId = "201511300000";
    static String finaceId = "201511300001";
}

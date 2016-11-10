package com.cana.account.server.test.query;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.dao.mapper.AccountTableLockMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.service.transaction.IAccountTransactionService;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.dto.PageResult;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/account-server-*.xml")
public class AccountQueryTest {

    @Resource
    private IAccountTransactionService accountTransactionService;
    @Resource
    private AccountTableLockMapper accountTableLockMapper;
    
    @Test
    public void queryTest() {
        AccountQueryCriteria accountQueryCriteria = new AccountQueryCriteria();
//        accountQueryCriteria.setAccountName("曾");
//        accountQueryCriteria.setAccountNo("151202142036801");
//        accountQueryCriteria.setFactorName("曾是国际集团");
        accountQueryCriteria.setIsTransferInAccount(false);
        PageResult<AccountDTO> accounts = accountTransactionService.queryAccounts("201511300000", accountQueryCriteria);
        System.out.println(accounts.getTotal());
    }

    @Test
    public void locksTest() {
        List<String> ids = Lists.newArrayList("151202142036801", "151202142039802");
        List<Account> accounts = accountTableLockMapper.lockAccountByIds(ids);
        System.out.println(accounts);
    }
    
    @Test
    public void lockTest() {
        List<Account> accounts = accountTableLockMapper.lockAccountByAccumulationId("151202172748901");
        System.out.println(accounts);
    }
    
    @Test
    public void query() {
        List<AccountDTO> accounts = accountTransactionService.queryRepaymentAccounts("201511300000", "许孟融资");
        System.out.println(accounts);
    }
    @Test
    public void querySupervisorsByFactorIdTest() {
        List<CustomerDetailDTO> finaces = accountTransactionService.querySupervisorsByFactorId("201511300000");
        System.out.println(finaces);
    }
}

package com.cana.account.server.test.message;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.dao.mapper.AccountCustomMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.service.IAccountMessageService;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountQueryCriteria;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/account-server-*.xml")
public class AccountMessageTest {

    @Resource
    private IAccountMessageService accountMessageService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountCustomMapper accountCustomMapper;
    
    @Test
    public void test() {
        User customer = userMapper.selectByPrimaryKey("201511300000");
        AccountQueryCriteria criteria = new AccountQueryCriteria();
        criteria.setFinaceId("201511300001");
        criteria.setAccountType(AccountType.SPECIAL);
        List<Account> accounts = accountCustomMapper.find(criteria);
        accountMessageService.sendMailForCreateAccountBySelf(customer, accounts);
    }
    
    @Test
    public void test2() {
        accountMessageService.sendMailForCreateAccountByAgent("151219142557701", true);
    }
    @Test
    public void test3() {
        accountMessageService.sendNotificationForTradeApply("201511300000", "151219141240101");
    }
    @Test
    public void test4() {
        accountMessageService.sendNotificationForAccountApply("201511300000", "151221154934203");
        System.out.println(Constants.CANA_CUSTOMER_ID);
    }
}

package com.cana.account.server.test.query;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.service.transaction.IAccountSupervisionTransactionService;
import com.cana.account.service.transaction.IAccountTradeTransactionService;
import com.cana.vbam.common.account.dto.AccountTradeApplyDTO;
import com.cana.vbam.common.account.dto.AccountTradeApplyQueryCriteria;
import com.cana.vbam.common.dto.PageResult;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/account-server-*.xml")
public class AccountTradeApplyQueryTest {

    @Resource
    private IAccountTradeTransactionService accountTradeTransactionService;
    @Resource
    private IAccountSupervisionTransactionService accountSupervisionTransactionService;

    @Test
    public void queryTest() {
        AccountTradeApplyQueryCriteria criteria = new AccountTradeApplyQueryCriteria();
        criteria.setStartTime("2015-12-09");
        criteria.setEndTime("2015-12-09");
        PageResult<AccountTradeApplyDTO> applys = accountTradeTransactionService.queryTradeApplys(xumengFinaceId, criteria);
        System.out.println(applys.getTotal());
    }
    
    String xumengFactorId = "201511300000";
    String xumengFinaceId = "201511300001";

    @Test
    public void queryDetail() {
        AccountTradeApplyDTO dto = accountSupervisionTransactionService.getSupervisionApply(xumengFactorId, "151209101643601");
        System.out.println(new Gson().toJson(dto));
    }
}

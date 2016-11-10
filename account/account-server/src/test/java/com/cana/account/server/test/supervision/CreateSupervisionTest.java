package com.cana.account.server.test.supervision;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.account.service.transaction.IAccountSupervisionTransactionService;
import com.cana.vbam.common.account.dto.AccountSupervisionCreateDTO;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/account-server-*.xml")
public class CreateSupervisionTest {

    @Resource
    private IAccountSupervisionTransactionService accountSupervisionTransactionServi;

    @Test
    public void createSupervision() {
        AccountSupervisionCreateDTO supervisionCreateDTO = new AccountSupervisionCreateDTO();
        supervisionCreateDTO.setIsDefaultRepayment(false);
        supervisionCreateDTO.setSupervisionCompanyName("许孟融资");
        supervisionCreateDTO.setSupervisionAccountId(majorAccountID);
        supervisionCreateDTO.setSpecialAccountIds(specialAccountIds);
        supervisionCreateDTO.setIsDefaultRepayment(true);
        String applyId = accountSupervisionTransactionServi.createSupervision(factorId, supervisionCreateDTO);
        System.out.println("申请ID：" + applyId);

        boolean result = accountSupervisionTransactionServi.auditSupervision(finaceId, applyId, true, "审核意见asddd");
        System.out.println("审核结果：" + result);
    }

    @Test
    public void removeSupervision() {
        String applyId = accountSupervisionTransactionServi.removeSupervision(finaceId, Lists.newArrayList(majorAccountID));
        System.out.println("解除申请ID：" + applyId);

        boolean result = accountSupervisionTransactionServi.auditSupervision(factorId, applyId, true, "审核意见sdfa");
        System.out.println("解除审核结果：" + result);
    }

    @Test
    public void createSupervisionWithoutAudit() {
        boolean result = accountSupervisionTransactionServi.createSupervisionWithoutAudit(finaceId, accountNo, factorId);
        System.out.println(result);
    }

    static String factorId = "201511300000";
    static String finaceId = "201511300001";
    static String majorAccountID = "151209113007101";
    static List<String> specialAccountIds = Lists.newArrayList("151209113031102", "151209113031103");
    static String accountNo = "3110210003631007497";
}

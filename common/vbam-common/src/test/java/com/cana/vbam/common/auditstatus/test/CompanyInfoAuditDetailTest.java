package com.cana.vbam.common.auditstatus.test;

import org.junit.Test;

import com.cana.vbam.common.account.dto.AccountApplyAuditDetail;
import com.cana.vbam.common.dto.CompanyInfoAuditDetail;

public class CompanyInfoAuditDetailTest {

    @Test
    public void test() {
        Integer.parseInt("10000000000", 2);
        int status = Integer.parseInt("11000111111", 2);
        AccountApplyAuditDetail accountAudit = new AccountApplyAuditDetail();
        CompanyInfoAuditDetail audit = new CompanyInfoAuditDetail();
        audit.setIntUserAuditStatus(status);
        accountAudit.setIntUserAuditStatus(status);
        System.out.println(accountAudit.getIntUserAuditStatus());
        test2(audit);
    }
    public void test2(CompanyInfoAuditDetail audit) {
        System.out.println(audit.getIntUserAuditStatus());
        
    }
}

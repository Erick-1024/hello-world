package com.cana.vbam.common.utils.test;

import org.junit.Test;

import com.cana.vbam.common.utils.AccountNoUtil;

public class AccountNoUtilTest {

    @Test
    public void test() {
        System.out.println(AccountNoUtil.formatBankAccountNo("311 0210003631007158"));
        System.out.println(AccountNoUtil.parseBankAccountNo("3110 2100 0363 1007 158"));
        
        System.out.println("3110210003631007158".replaceAll("(\\d{4})(?=\\d)", "$1 "));
    }
}

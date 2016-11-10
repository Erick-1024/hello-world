package com.cana.account.server.test.enumtest;

import org.junit.Test;

public class EnumTest {

    @Test
    public void test() {
        EnumExample ee = EnumExample.Name1;
        EnumExample ee2 = EnumExample.Name1;
        ee.setReason("resson");
        System.out.println(ee.getReason());
        System.out.println(ee2);
    }
    
    
}

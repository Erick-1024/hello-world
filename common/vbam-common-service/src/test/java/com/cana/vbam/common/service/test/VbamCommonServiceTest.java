package com.cana.vbam.common.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.vbam.common.service.impl.VbamCommonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/vbam-common-service-context.xml")
public class VbamCommonServiceTest {

    @Resource
    private VbamCommonService vbamCommonService;

    @Test
    public void test() {
        System.out.println("hello");
    }
}

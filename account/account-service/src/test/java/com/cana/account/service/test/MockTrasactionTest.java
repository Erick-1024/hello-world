//package com.cana.account.service.test;
//
//import static org.junit.Assert.assertNull;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.cana.account.service.transaction.IMockTransactionService;
//import com.cana.common.dao.mapper.gen.PropertiesMapper;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/account-service-*.xml")
//public class MockTrasactionTest {
//
//    @Resource
//    private IMockTransactionService service;
//    
//    @Resource
//    private PropertiesMapper mapper;
//    
//    @Test
//    public void addNewProperty() {
//        String propertyName = RandomStringUtils.randomNumeric(10);
//        String propertyValue = "1";
//        try{
//            service.addNewProperty(propertyName, propertyValue);
//        }catch(Exception e){
//            // ignore
//        }
//        assertNull(mapper.selectByPrimaryKey(propertyName));
//    }
//    
//    @Test
//    public void lock() throws Exception{
//        service.lock();
//    }
//
//}

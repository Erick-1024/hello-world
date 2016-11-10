package com.cana.payment.dao.mapper;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.repayment.dao.mapper.RepaymentTableLockMapper;
import com.google.gson.Gson;

@ContextConfiguration("classpath:spring/test-repayment-dao-datasource.xml")
public class TableLockMapperTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private RepaymentTableLockMapper mapper;
	
	@Test
	public void test() {
		System.out.println(new Gson().toJson(mapper.lockLoanInfoById("1512091904070502")));
	}

}

package com.cana.payment.dao.mapper;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.repayment.dao.mapper.IRepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.google.gson.Gson;

@ContextConfiguration("classpath:spring/test-repayment-dao-datasource.xml")
public class RepaymentDailyBatchTaskMapperTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IRepaymentDailyBatchTaskMapper mapper;
	
	@Test
	public void getAllUnGenerateDailyBatchTaskLoanInfoIds() {
		System.out.println(mapper.getAllUnGenerateDailyBatchTaskLoanInfoIds("2015-12-15"));
	}
	
	@Test
	public void getAllPendingTasks(){
		for(RepaymentDailyBatchTask task : mapper.getAllPendingTasks("2015-12-19", "19:00"))
			System.out.println(new Gson().toJson(task));
	}
	
	@Test
	public void getEarliestExecutableDeductItemId () {
		String itemId = mapper.getEarliestExecutableDeductItemId("201605260067", "2016-06-16", "20:00");
		System.out.println(itemId);
	}
	
	@Test
	public void countUnFinishedDefaultDeduct() {
		System.out.println(mapper.countUnFinishedDefaultDeduct("2016-09-12"));
	}

}

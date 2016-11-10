package com.cana.repayment.daily.batch.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cana.repayment.dao.mapper.IRepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class RepaymentDailyBatchTest {

	@Test
	@Transactional
	public void tset() throws Exception {
		RepaymentDailyBatchTask record = new RepaymentDailyBatchTask();
		record.setId("1607051545494407");
		record.setNextTaskItemId("1607051545494405");
		record.setSequence(1);
		repaymentDailyBatchTaskMapper.updateByPrimaryKeySelective(record);
		record = new RepaymentDailyBatchTask();
		record.setId("1607051545504564");
		record.setNextTaskItemId("1607051545504559");
		record.setSequence(1);
		repaymentDailyBatchTaskMapper.updateByPrimaryKeySelective(record);
		
		List<RepaymentDailyBatchTask> tasks = batchTaskMapper.getAllPendingTasks("2016-08-04", "21:00");
		System.out.println(new Gson().toJson(tasks));

		System.out.println(batchTaskMapper.getEarliestExecutableDeductItemId("201607040235", "2016-08-04", "21:00"));
	}


	@Resource
	private RepaymentDailyBatchTaskMapper repaymentDailyBatchTaskMapper;

	@Resource
	private IRepaymentDailyBatchTaskMapper batchTaskMapper;
}

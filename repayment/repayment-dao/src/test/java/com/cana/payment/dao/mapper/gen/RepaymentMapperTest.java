package com.cana.payment.dao.mapper.gen;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/repayment-dao-*.xml")
public class RepaymentMapperTest {
	@Resource
	private RepaymentExpenseMapper mapper;
	
	@Test
	public void insert() {
		RepaymentExpense p = new RepaymentExpense();
		p.setId(RandomStringUtils.randomNumeric(10));
		p.setLoanInfoId(RandomStringUtils.randomNumeric(10));
		p.setFinanceId(RandomStringUtils.randomNumeric(10));
		p.setFinanceCompany("Test");
		mapper.insertSelective(p);
	}
	
	@Test
	public void pagination(){
		RepaymentExpenseExample example = new RepaymentExpenseExample();
		example.setOrderByClause("id");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		for(RepaymentExpense p : mapper.selectByExample(example)){
			System.out.println(p.getId());
		}
	}
}

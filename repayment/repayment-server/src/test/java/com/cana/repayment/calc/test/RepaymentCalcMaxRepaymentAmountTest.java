package com.cana.repayment.calc.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.handler.IRepaymentCalc;
import com.cana.repayment.service.handler.RepaymentCalcFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class RepaymentCalcMaxRepaymentAmountTest {

	@Test
	@Transactional
	public void testCalcMaxAmount() throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO("2016071812406");

		IRepaymentCalc repaymentCalc = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO);
		long amount = repaymentCalc.calcMaximumRepaymentAmount(loanInfoBO, "2016-07-18");
		System.out.println(amount);
	}
}

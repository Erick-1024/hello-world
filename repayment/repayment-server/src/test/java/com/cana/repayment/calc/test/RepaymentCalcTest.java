package com.cana.repayment.calc.test;

import java.io.FileWriter;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cana.repayment.calc.test.util.RepaymentCalcFileTestUtil;
import com.cana.repayment.calc.test.util.RepaymentCalcTestUtil;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.handler.EqualAllRepaymentCalc;
import com.cana.repayment.service.handler.EqualPrincipalRepaymentCalc;
import com.cana.repayment.service.handler.IRepaymentCalc;
import com.cana.repayment.service.handler.MonthlyRepaymentCalc;
import com.cana.repayment.service.handler.TravelzenFinanceRepaymentCalc;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class RepaymentCalcTest {

	@Test
	@Transactional
	public void testMonthly() throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(RepaymentCalcTestUtil.generateRepaymentLoanInfo(RepaymentType.MONTHLY));
		loanInfoBO.setFinanceAmount(900000 * 100l);
		loanInfoBO.setLoanDate("2015-12-10");

		FileWriter writer = RepaymentCalcFileTestUtil.getFileWriter("monthly_1.csv");
		IRepaymentCalc repaymentCalc = new MonthlyRepaymentCalc();
		RepaymentCalcTestUtil.testGeneratePlans(writer, repaymentCalc, loanInfoBO);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-11", 1395000 + 45000);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-11", 90000001l - 2l);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-12", 1l);
	}

	@Test
	@Transactional
	public void testEqualPrincipal() throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(RepaymentCalcTestUtil.generateRepaymentLoanInfo(RepaymentType.EQUALPRINCIPAL));
		loanInfoBO.setFinanceAmount(900000 * 100l);
		loanInfoBO.setLoanDate("2015-12-10");

		FileWriter writer = RepaymentCalcFileTestUtil.getFileWriter("equalPrincipal_1.csv");
		IRepaymentCalc repaymentCalc = new EqualPrincipalRepaymentCalc();
		RepaymentCalcTestUtil.testGeneratePlans(writer, repaymentCalc, loanInfoBO);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-09", 1395000 + 45000);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-11", 89910000);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-11", 82454);
//		RepaymentCalcTestUtil.testRepaymentInAdvance(repaymentCalc, loanInfoBO, "2016-03-11", 2);
	}

	@Test
	@Transactional
	public void testEqualAll() throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(RepaymentCalcTestUtil.generateRepaymentLoanInfo(RepaymentType.EQUALALL));
		loanInfoBO.setFinanceAmount(100000000l);
		loanInfoBO.setLoanDate("2016-01-31");
		loanInfoBO.setRepaymentPeriod(12);
		loanInfoBO.setInterestRateUnit(InterestRateUnit.MONTH.name());
		loanInfoBO.setInterestRate(new BigDecimal("0.015"));
		loanInfoBO.setLoanPeriod("12");
		loanInfoBO.setLoanPeriodUnit(DateUnit.MONTH.name());

		FileWriter writer = RepaymentCalcFileTestUtil.getFileWriter("equalAll_1.csv");
		IRepaymentCalc repaymentCalc = new EqualAllRepaymentCalc();
		RepaymentCalcTestUtil.testGeneratePlans(writer, repaymentCalc, loanInfoBO);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-31", 1500000);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-02-02", 7667999);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-02-02", 1000000);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-03-31", 7698725 + 1369980);
	}

	@Test
	@Transactional
	public void testOrder() throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(RepaymentCalcTestUtil.generateRepaymentLoanInfo(RepaymentType.ORDER));
		loanInfoBO.setFinanceAmount(900000 * 100l);
		loanInfoBO.setLoanDate("2015-12-10");
		loanInfoBO.setRepaymentPeriod(1);
		loanInfoBO.setInterestRateUnit(InterestRateUnit.DAY.name());
		loanInfoBO.setInterestRate(new BigDecimal("0.0005"));
		loanInfoBO.setLoanPeriod("1");
		loanInfoBO.setLoanPeriodUnit(DateUnit.MONTH.name());
		loanInfoBO.setFinanceBalance(loanInfoBO.getFinanceAmount());

		FileWriter writer = RepaymentCalcFileTestUtil.getFileWriter("order_1.csv");
		IRepaymentCalc repaymentCalc = new TravelzenFinanceRepaymentCalc();
		RepaymentCalcTestUtil.testGeneratePlans(writer, repaymentCalc, loanInfoBO);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-11", 1395000 + 45000);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-11", 90000001l - 2l);
		RepaymentCalcTestUtil.testRepaymentInAdvance(writer, repaymentCalc, loanInfoBO, "2016-01-12", 1l);
	}
}

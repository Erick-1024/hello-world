package com.cana.repayment.travelzen;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.SettleStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-repayment-service-*.xml")
public class TravelzenTest1 extends AbstractTravelzenTest{

	@Test
	public void test() throws Exception{
		CreateLoanRequest payInfo = preparePayInfo();
		RepaymentLoanInfoBO loanInfoBO = service.addTravelzenFinanceLoan(payInfo);
		verifyAfterLoan(loanInfoBO);
		commonService.saveVirtualDateTime("2016-06-01", "");
		Thread.sleep(TimeUnit.MINUTES.toMillis(4));
		verifyAfterDeduct(loanInfoBO);
		commonService.saveVirtualDateTime("", "");
	}
	
	private void verifyAfterDeduct(RepaymentLoanInfoBO loanInfoBO) {
		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId());
		Assert.assertEquals(SettleStatus.SETTLED.name(), loanInfoBO.getSettleStatus());
	}

	/**
	 * 放款以后验证数据正确性
	 * @param loanInfoBO
	 */
	protected void verifyAfterLoan(RepaymentLoanInfoBO loanInfoBO) {
		Assert.assertEquals("2016-06-01", loanInfoBO.getDueDate());
		RepaymentPlanBO planBO = loanInfoBO.lazyLoadPlans().get(0);
		Assert.assertEquals(7750, planBO.getAccountInterest().longValue());
	}

	private CreateLoanRequest preparePayInfo() {
		CreateLoanRequest payInfo = new CreateLoanRequest();
		payInfo.setAccountNo("xxxx");
		payInfo.setAccountSupervisionId("xxxxxxx");
		payInfo.setFactorCompany("Cana资金");
		payInfo.setFactorId(FACTOR_ID);
		payInfo.setFinanceCompany("finance company");
		payInfo.setFinanceId(FINANCE_ID);
		payInfo.setFinanceAmount(500000);
		payInfo.setLoanDate("2016-05-01");
		payInfo.setInterestRateUnit(InterestRateUnit.DAY);
		payInfo.setInterestRate(new BigDecimal("0.0005"));
		return payInfo;
	}

	@Test
	public void resetVirtualDateTime(){
		commonService.saveVirtualDateTime("", "");
	}
}

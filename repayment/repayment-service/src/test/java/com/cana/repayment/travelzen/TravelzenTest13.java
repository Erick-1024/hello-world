package com.cana.repayment.travelzen;

import java.math.BigDecimal;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.repayment.service.bo.RepaymentSingleDistributeDetailBO;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundInfo;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundResult;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-repayment-service-*.xml")
public class TravelzenTest13 extends AbstractTravelzenTest{

	@Test
	public void test() throws Exception{
		CreateLoanRequest payInfo = preparePayInfo();
		RepaymentLoanInfoBO loanInfoBO = service.addTravelzenFinanceLoan(payInfo);
		verifyAfterLoan(loanInfoBO);
		commonService.saveVirtualDateTime("2016-04-03", "");
		TravelzenUserRefundInfo	refundInfo = prepareRefundInfo();
		TravelzenUserRefundResult refundResult = service.refundByTravelzenFinancier(refundInfo);
		verifyAfterRefund(refundResult);
		commonService.saveVirtualDateTime("", "");
	}
	
	private void verifyAfterRefund(TravelzenUserRefundResult refundResult) {
		LoanInfoRepaymentResult result = refundResult.getResult().get(0);
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(result.getLoanInfoId());
		RepaymentPlanBO planBO = loanInfoBO.lazyLoadPlans().get(0);
		RepaymentSingleCollectBO repaymentSummaryBO = new RepaymentSingleCollectBO(result.getRepaymentSummaryRecordId());
		RepaymentSingleDistributeDetailBO  repaymentItemBO = repaymentSummaryBO.lazyLoadItemBOs().get(0);
		Assert.assertTrue(19970 == repaymentItemBO.getPayNormalPrincipal());
		Assert.assertTrue(30 == repaymentItemBO.getPayNormalInterest());
		Assert.assertTrue(19970 == planBO.getPaidNormalPrincipal());
		Assert.assertTrue(30 == planBO.getPaidNormalInterest());
		Assert.assertTrue(580030 == planBO.getAccountPrincipal());
		Assert.assertTrue(8700 == planBO.getAccountInterest());
	}

	/**
	 * 放款以后验证数据正确性
	 * @param loanInfoBO
	 */
	protected void verifyAfterLoan(RepaymentLoanInfoBO loanInfoBO) {
		Assert.assertEquals("2016-04-30", loanInfoBO.getDueDate());
	}

	private CreateLoanRequest preparePayInfo() {
		CreateLoanRequest payInfo = new CreateLoanRequest();
		payInfo.setAccountNo("xxxx");
		payInfo.setAccountSupervisionId("xxxxxxx");
		payInfo.setFactorCompany("Cana资金");
		payInfo.setFactorId(FACTOR_ID);
		payInfo.setFinanceCompany("finance company");
		payInfo.setFinanceId(FINANCE_ID);
		payInfo.setFinanceAmount(600000);
		payInfo.setLoanDate("2016-03-31");
		payInfo.setInterestRateUnit(InterestRateUnit.DAY);
		payInfo.setInterestRate(new BigDecimal("0.0005"));
		return payInfo;
	}
	
	private TravelzenUserRefundInfo prepareRefundInfo(){
		TravelzenUserRefundInfo refundInfo = new TravelzenUserRefundInfo();
		refundInfo.setFinanceId(FINANCE_ID);
		refundInfo.setTxnId(RandomStringUtils.randomAlphanumeric(10));
		refundInfo.setRefundAmount(20000);
		return refundInfo;
	}

}

package com.cana.repayment.travelzen;

import java.math.BigDecimal;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundInfo;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundResult;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-repayment-service-*.xml")
public class TravelzenPayMultipleLoanInfosTest extends AbstractTravelzenTest{

	@Test
	public void test() throws Exception{
		CreateLoanRequest payInfo = preparePayInfo();
		commonService.saveVirtualBalance(payInfo.getAccountNo(), 0L, 24);
		payInfo.setLoanDate("2016-03-20");
		service.addTravelzenFinanceLoan(payInfo);
		payInfo.setLoanDate("2016-03-21");
		service.addTravelzenFinanceLoan(payInfo);
		payInfo.setLoanDate("2016-03-22");
		service.addTravelzenFinanceLoan(payInfo);
		TravelzenUserRefundInfo	refundInfo = prepareRefundInfo();
		commonService.saveVirtualDateTime("2016-03-22", "");
		TravelzenUserRefundResult refundResult = service.refundByTravelzenFinancier(refundInfo);
		verifyAfterRefund(refundResult);
		commonService.saveVirtualDateTime("", "");
	}
	
	private void verifyAfterRefund(TravelzenUserRefundResult refundResult) {
		Assert.assertEquals(3, refundResult.getResult().size());
		Assert.assertEquals(24800, refundResult.getRemainingAmount());
		Assert.assertEquals(300000, refundResult.getTotalPaidPrincipal());
		
		LoanInfoRepaymentResult result0 = refundResult.getResult().get(0);
		RepaymentSingleCollectBO repaymentSummary0 = new RepaymentSingleCollectBO(result0.getRepaymentSummaryRecordId());
		Assert.assertEquals(100100, repaymentSummary0.calcTotal());
		
		LoanInfoRepaymentResult result1 = refundResult.getResult().get(1);
		RepaymentSingleCollectBO repaymentSummary1 = new RepaymentSingleCollectBO(result1.getRepaymentSummaryRecordId());
		Assert.assertEquals(100050, repaymentSummary1.calcTotal());
		
		LoanInfoRepaymentResult result2 = refundResult.getResult().get(2);
		RepaymentSingleCollectBO repaymentSummary2 = new RepaymentSingleCollectBO(result2.getRepaymentSummaryRecordId());
		Assert.assertEquals(100050, repaymentSummary2.calcTotal());
	}

	private CreateLoanRequest preparePayInfo() {
		CreateLoanRequest payInfo = new CreateLoanRequest();
		payInfo.setAccountNo("31231624085242");
		payInfo.setAccountSupervisionId("160318092531102");
		payInfo.setFactorCompany("Cana资金");
		payInfo.setFactorId(FACTOR_ID);
		payInfo.setFinanceCompany("finance company");
		payInfo.setFinanceId(FINANCE_ID);
		payInfo.setFinanceAmount(100000);
		payInfo.setLoanDate("2016-03-31");
		payInfo.setInterestRateUnit(InterestRateUnit.DAY);
		payInfo.setInterestRate(new BigDecimal("0.0005"));
		return payInfo;
	}
	
	private TravelzenUserRefundInfo prepareRefundInfo(){
		TravelzenUserRefundInfo refundInfo = new TravelzenUserRefundInfo();
		refundInfo.setFinanceId(FINANCE_ID);
		refundInfo.setTxnId(RandomStringUtils.randomAlphanumeric(10));
		refundInfo.setRefundAmount(325000);
		return refundInfo;
	}

}

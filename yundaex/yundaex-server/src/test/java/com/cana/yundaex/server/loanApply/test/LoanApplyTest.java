package com.cana.yundaex.server.loanApply.test;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.yundaex.dto.loanApply.YundaexLoanApplyQueryDTO;
import com.cana.yundaex.api.IYundaexLoanApplyApi;
import com.cana.yundaex.service.transaction.IYundaexInterestRateTransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class LoanApplyTest {
	@Resource
	private IYundaexLoanApplyApi yundaexLoanApplyApi;

	@Resource
	private IYundaexInterestRateTransactionService yundaexInterestRateTransactionService;

	@Test
	public void testLoanApplyTest() throws Exception {
		YundaexLoanApplyQueryDTO dto = new YundaexLoanApplyQueryDTO();
		dto.setPaymentFee(new Double(10000.01).longValue());
		dto.setInterestRate(new BigDecimal(0.0015));
		dto.setInterestRateUnit(InterestRateUnit.MONTH.name());
		dto.setRepaymentMethod(RepaymentType.EQUALALL.name());
		dto.setLoanDate("2016-06-20");
		dto.setDueDate("2016-09-20");
		dto.setLoanPeriodUnit(DateUnit.MONTH.name());
		dto.setLoanPeriod("3");
		UserSessionDTO userSessionDTO = new UserSessionDTO();
		yundaexLoanApplyApi.creditLoanApply(dto, userSessionDTO);
	}
	
	@Test
	public void testLoanQueryTest() {
		String memberId = "201604130008";
		yundaexLoanApplyApi.getLoanApplyDetails(memberId);
	}
	
	@Test
	public void interestRateTest() {
		String interes = yundaexInterestRateTransactionService.getInterestRate("201608230523",new BigDecimal("89.9"));
		System.err.println(interes);
		yundaexInterestRateTransactionService.updateInterestRate("201608230523", interes);
	}

}

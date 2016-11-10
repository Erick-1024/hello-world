package com.cana.repayment.yundaex.test;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.enums.ChargeMethod;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class YundaexCreateLoanTest {

	@Resource
	private IRepaymentTransactionService repaymentTransactionService;
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Test
	public void testCreateYundaexLoan() throws Exception {
		CreateLoanRequest request = new CreateLoanRequest();
		request.setLoanDate("2016-06-24");
		request.setFinanceId("201604110006");
		request.setFinanceCompany("多果旅游公司");
		request.setCoreCompanyId("201603280002");
		request.setCoreCompanyName("上海韵达速递（物流）有限公司");
		request.setFactorId("cana-baoli");
		request.setFactorCompany("上海凯拿资产管理有限公司");
		request.setAccountNo("3110210003631014620");
		request.setAccountSupervisionId("160418143652215");
		request.setFinanceAmount(10000000);
		request.setLoanPeriodUnit(DateUnit.MONTH);
		request.setLoanPeriod(3);
		request.setInterestRateUnit(InterestRateUnit.MONTH);
		request.setInterestRate(new BigDecimal("0.015"));
		request.setRepaymentMethod(RepaymentType.MONTHLY);
		request.setProductId("yundaex_project_id");
		request.setProductName("信韵融");
		request.setInstitutionName("韵达");
		request.setUseHolidayPolicy(true);
		request.setDeductionRule(DeductionRule.ALL);
		request.setPenaltyRatio(new BigDecimal("0.00075"));
		request.setPenaltyChargeMethod(ChargeMethod.AMOUNT);
		RepaymentLoanInfoBO loanInfo = repaymentTransactionService.createLoan(request);

		repaymentLoanInfoMapper.selectByPrimaryKey(loanInfo.getId());
	}
}

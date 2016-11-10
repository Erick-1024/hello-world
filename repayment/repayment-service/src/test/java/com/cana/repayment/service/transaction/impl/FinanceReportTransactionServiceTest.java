package com.cana.repayment.service.transaction.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.repayment.service.transaction.IFinanceReportTransactionService;
import com.cana.vbam.common.repayment.dto.QueryPlanListRequest;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryRequestDTO;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/test-repayment-service-*.xml")
public class FinanceReportTransactionServiceTest {
	
	@Resource
	private IFinanceReportTransactionService service;
	
	@Test
	public void queryRepaymentSummary() throws Exception{
		QueryRepaymentSummaryRequestDTO request = new QueryRepaymentSummaryRequestDTO();
		request.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		request.setFactorId("cana-baoli");
		request.setFinanceId("201605060032");
		System.out.println(new Gson().toJson(service.queryRepaymentSummary(request)));
	}

	@Test
	public void countContinueOverduePlans() throws Exception{
		QueryPlanListRequest request = new QueryPlanListRequest();
		request.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		request.setFinanceId("201604280040");
		request.setStartDate("2016-04-01");
		request.setEndDate("2016-07-01");
		System.out.println(service.countContinueOverduePlans(request));
	}

}

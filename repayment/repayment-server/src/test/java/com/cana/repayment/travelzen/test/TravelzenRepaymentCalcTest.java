package com.cana.repayment.travelzen.test;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundInfo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:META-INF/spring/*.xml")
public class TravelzenRepaymentCalcTest {

	@Test
	@Transactional
	public void testOrder() throws Exception {
		TravelzenUserRefundInfo refundInfo = prepareRefundInfo();
		System.out.println(new Gson().toJson(service.refundByTravelzenFinancier(refundInfo)));
	}

	private TravelzenUserRefundInfo prepareRefundInfo() {
		TravelzenUserRefundInfo refundInfo = new TravelzenUserRefundInfo();
		refundInfo.setFinanceId("201606020081");
		refundInfo.setTxnId(RandomStringUtils.randomAlphanumeric(10));
		refundInfo.setRefundAmount(200000);
		return refundInfo;
	}

	@Resource
	private IRepaymentTransactionService service;
}

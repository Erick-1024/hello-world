package com.cana.asset.server.test;

import java.util.List;

import org.junit.Test;

import com.cana.asset.service.transaction.util.AssetLoanPlanAutoGenerator;
import com.cana.vbam.common.asset.loan.dto.GenerateLoanPlanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;

public class AssetGenerateLoanPlanTest {

	private Gson gson = new Gson();

	@Test
	public void test() throws Exception {
		GenerateLoanPlanRequest request = new GenerateLoanPlanRequest();
		request.setLoanDate("2016-10-30");
		request.setFinanceAmount("10000000");
		request.setRepaymentType(RepaymentType.MONTHLY.name());
		request.setDueDate("2016-12-31");
		request.setDayCountConvention(360);
		request.setInterestRateUnit(InterestRateUnit.DAY.name());
		request.setInterestRate("0.01%");
		List<LoanPlanDTO> plans = AssetLoanPlanAutoGenerator.generateLoanPlanDTO(request);
		System.out.println(gson.toJson(plans));
	}
	
	@Test
	public void testDate() throws Exception {
		System.out.println(MoneyUtil.formatMoney(1000000L));
	}
	
}

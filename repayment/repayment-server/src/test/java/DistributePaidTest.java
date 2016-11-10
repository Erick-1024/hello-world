import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.repayment.service.IRepaymentPlanService;
import com.cana.vbam.common.repayment.dto.AccountRepaymentPlanRedisDTO;
import com.cana.vbam.common.repayment.dto.ChargeDTO;
import com.cana.vbam.common.repayment.dto.PaidRepaymentPlanRedisDTO;

@ContextConfiguration(locations = "classpath:META-INF/spring/repayment-server-*.xml")
public class DistributePaidTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private IRepaymentPlanService repaymentPlanServiceImpl;
	
	@Test
	public void test() {
		List<PaidRepaymentPlanRedisDTO> paidPlanList = new ArrayList<>();
		List<AccountRepaymentPlanRedisDTO> accountPlanList = new ArrayList<>();
		ChargeDTO charge = new ChargeDTO();
		charge.setCharge(1000000);
		BigDecimal earlyRepaymentRatio = new BigDecimal("0.05");
		
		PaidRepaymentPlanRedisDTO piadPlan1 = new PaidRepaymentPlanRedisDTO();
		piadPlan1.setPaidOverdueServiceCharge("400.34");
		piadPlan1.setPaidOverdueManagerFee("20.33");
		piadPlan1.setPaidOverdueInterest("0.00");
		piadPlan1.setPaidOverduePrincipal("0.00");
		piadPlan1.setPaidExtensionCharge("30.24");
		piadPlan1.setPaidNormalServiceCharge("0.00");
		piadPlan1.setPaidNormalInterest("0.00");
		piadPlan1.setPaidNormalPrincipal("0.00");
		piadPlan1.setValueDate("2015-10-17");
		piadPlan1.setRepaymentDate("2015-11-16");
		paidPlanList.add(piadPlan1);
		
		PaidRepaymentPlanRedisDTO piadPlan2 = new PaidRepaymentPlanRedisDTO();
		piadPlan2.setPaidOverdueServiceCharge("0.00");
		piadPlan2.setPaidOverdueManagerFee("0.00");
		piadPlan2.setPaidOverdueInterest("0.00");
		piadPlan2.setPaidOverduePrincipal("0.00");
		piadPlan2.setPaidExtensionCharge("0.00");
		piadPlan2.setPaidNormalServiceCharge("0.00");
		piadPlan2.setPaidNormalInterest("0.00");
		piadPlan2.setPaidNormalPrincipal("0.00");
		piadPlan2.setValueDate("2015-11-17");
		piadPlan2.setRepaymentDate("2015-12-17");
		paidPlanList.add(piadPlan2);
		
		PaidRepaymentPlanRedisDTO piadPlan3 = new PaidRepaymentPlanRedisDTO();
		piadPlan3.setPaidOverdueServiceCharge("0.00");
		piadPlan3.setPaidOverdueManagerFee("0.00");
		piadPlan3.setPaidOverdueInterest("0.00");
		piadPlan3.setPaidOverduePrincipal("0.00");
		piadPlan3.setPaidExtensionCharge("0.00");
		piadPlan3.setPaidNormalServiceCharge("0.00");
		piadPlan3.setPaidNormalInterest("0.00");
		piadPlan3.setPaidNormalPrincipal("0.00");
		piadPlan3.setValueDate("2015-12-18");
		piadPlan3.setRepaymentDate("2016-01-18");
		paidPlanList.add(piadPlan3);
		
		AccountRepaymentPlanRedisDTO accountPlan1 = new AccountRepaymentPlanRedisDTO();
		accountPlan1.setOverdueManagerFee("20.33");
		accountPlan1.setExtensionCharge("30.24");
		accountPlan1.setOverdueServiceCharge("500.46");
		accountPlan1.setOverdueInterest("555.55");
		accountPlan1.setOverduePrincipal("600.00");
		accountPlan1.setAccountServiceCharge("0.00");
		accountPlan1.setAccountInterest("0.00");
		accountPlan1.setAccountPrincipal("0.00");
		accountPlan1.setValueDate("2015-10-17");
		accountPlan1.setRepaymentDate("2015-11-16");
		accountPlanList.add(accountPlan1);
		
		AccountRepaymentPlanRedisDTO accountPlan2 = new AccountRepaymentPlanRedisDTO();
		accountPlan2.setOverdueManagerFee("20.33");
		accountPlan2.setExtensionCharge("30.24");
		accountPlan2.setOverdueServiceCharge("500.46");
		accountPlan2.setOverdueInterest("555.55");
		accountPlan2.setOverduePrincipal("600.00");
		accountPlan2.setAccountServiceCharge("0.00");
		accountPlan2.setAccountInterest("0.00");
		accountPlan2.setAccountPrincipal("0.00");
		accountPlan2.setValueDate("2015-11-17");
		accountPlan2.setRepaymentDate("2015-12-17");
		accountPlanList.add(accountPlan2);
		
		AccountRepaymentPlanRedisDTO accountPlan3 = new AccountRepaymentPlanRedisDTO();
		accountPlan3.setOverdueManagerFee("0.00");
		accountPlan3.setExtensionCharge("0.00");
		accountPlan3.setOverdueServiceCharge("0.00");
		accountPlan3.setOverdueInterest("0.00");
		accountPlan3.setOverduePrincipal("0.00");
		accountPlan3.setAccountServiceCharge("500.00");
		accountPlan3.setAccountInterest("2000.00");
		accountPlan3.setAccountPrincipal("10000.00");
		accountPlan3.setValueDate("2015-12-18");
		accountPlan3.setRepaymentDate("2016-01-18");
		accountPlanList.add(accountPlan3);
		
		try {
			//repaymentPlanServiceImpl.distributePlanWithRepayment(paidPlanList, accountPlanList, charge, earlyRepaymentRatio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("失败！");
		}
	}

}

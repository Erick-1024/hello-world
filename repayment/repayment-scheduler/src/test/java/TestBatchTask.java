

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.vbam.common.repayment.enums.SettleStatus;

@ContextConfiguration("classpath:spring/repayment-scheduler-*.xml")
public class TestBatchTask extends AbstractJUnit4SpringContextTests {

	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;
	
	@Resource
	private LoanInfoConfigMapper loanInfoConfigMapper;
	
	@Resource
	private RepaymentPlanMapper planMapper;
	
	@Resource
	private RepaymentExpenseMapper expenseMapper;
	
	@Test
	public void test() {
		RepaymentLoanInfo loanInfo = generateLoanInfo();
		generateLoanInfoConfig(loanInfo);
		generatePlans(loanInfo);
//		generateExpenses(loanInfo);
	}
	
	private RepaymentLoanInfo generateLoanInfo(){
		RepaymentLoanInfo loanInfo = new RepaymentLoanInfo();
		loanInfo.setId(RandomStringUtils.randomAlphanumeric(10));
		loanInfo.setSettleStatus(SettleStatus.UNSETTLE.name());
		loanInfo.setBusinessMode("FACTORANDFINACE");
		loanInfo.setInputMethod("EXCEL");
		loanInfo.setFactorCompany("dddd");
		loanInfo.setFinanceCompany("ddddd");
		loanInfo.setAccountNo("3110210003631007672");
		loanInfo.setCreateTime(new Date());
		loanInfo.setCurrentVersion("v1");
		loanInfo.setRepaymentPeriod(1);
		loanInfoMapper.insertSelective(loanInfo);
		return loanInfo;
	}
	
	private void generateLoanInfoConfig(RepaymentLoanInfo loanInfo){
		LoanInfoConfig loanInfoConfig = new LoanInfoConfig();
		loanInfoConfig.setId(loanInfo.getId());
		loanInfoConfig.setExtensionDays(3);
		loanInfoConfig.setDeductionTime("20:00");
		loanInfoConfig.setPenaltyRate(new BigDecimal("0.1"));
		loanInfoConfig.setExtensionRatio(new BigDecimal("0.05"));
		loanInfoConfig.setFactorTransferInAccountNo("3110210003631007571");
		loanInfoConfigMapper.insertSelective(loanInfoConfig);
	}
	
	private void generatePlans(RepaymentLoanInfo loanInfo){
		
		/*
		RepaymentPlan plan1 = new RepaymentPlan();
		plan1.setId(RandomStringUtils.randomAlphanumeric(10));
		plan1.setLoanInfoId(loanInfo.getId());
		plan1.setOverduePrincipal(1000000L);
		plan1.setOverdueInterest(100000L);
		plan1.setOverdueServiceCharge(50000L);
		plan1.setOverduePrincipalPenalty(20000L);
		plan1.setOverdueInterestPenalty(10000L);
		plan1.setOverdueServiceChargePenalty(5000L);
		plan1.setAccountExtensionCharge(3000L);
		plan1.setRepaymentPeriod(1);
		plan1.setRepaymentDate("2015-12-25");
		plan1.setBusinessModel("FACTORANDFINACE");
		plan1.setInputMethod("EXCEL");
		plan1.setFinanceCompany("ddddd");
		planMapper.insertSelective(plan1); */
		
		RepaymentPlan plan2 = new RepaymentPlan();
		plan2.setId(RandomStringUtils.randomAlphanumeric(10));
		plan2.setLoanInfoId(loanInfo.getId());
		plan2.setAccountPrincipal(1000000L);
		plan2.setAccountInterest(100000L);
		plan2.setAccountServiceCharge(50000L);
		plan2.setAccountExtensionCharge(3000L);
		plan2.setRepaymentPeriod(2);
		plan2.setRepaymentDate("2016-02-22");
		plan2.setBusinessMode("FACTORANDFINACE");
		plan2.setInputMethod("EXCEL");
		plan2.setFinanceCompany("ddddd");
		planMapper.insertSelective(plan2); 
		/*
		RepaymentPlan plan3 = new RepaymentPlan();
		plan3.setId(RandomStringUtils.randomAlphanumeric(10));
		plan3.setLoanInfoId(loanInfo.getId());
		plan3.setAccountPrincipal(1000000L);
		plan3.setAccountInterest(100000L);
		plan3.setAccountServiceCharge(50000L);
		plan3.setAccountExtensionCharge(3000L);
		plan3.setRepaymentPeriod(3);
		plan3.setRepaymentDate("2015-12-25");
		plan3.setBusinessModel("FACTORANDFINACE");
		plan3.setInputMethod("EXCEL");
		plan3.setFinanceCompany("ddddd");
		planMapper.insertSelective(plan3); */
	}
	
	private void generateExpenses(RepaymentLoanInfo loanInfo){
		RepaymentExpense expense1 = new RepaymentExpense();
		expense1.setId(RandomStringUtils.randomAlphanumeric(10));
		expense1.setLoanInfoId(loanInfo.getId());
		expense1.setRepaymentAmount(15000L);
		expense1.setRepaymentDate("2015-12-23");
		expense1.setFinanceCompany("ddddd");
		expenseMapper.insertSelective(expense1);
		
		RepaymentExpense expense2 = new RepaymentExpense();
		expense2.setId(RandomStringUtils.randomAlphanumeric(10));
		expense2.setLoanInfoId(loanInfo.getId());
		expense2.setRepaymentAmount(15000L);
		expense2.setRepaymentDate("2016-01-20");
		expense2.setFinanceCompany("ddddd");
		expenseMapper.insertSelective(expense2);
	}

}

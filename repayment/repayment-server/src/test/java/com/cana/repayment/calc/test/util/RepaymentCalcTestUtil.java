package com.cana.repayment.calc.test.util;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.repayment.service.handler.IRepaymentCalc;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.enums.BusinessMode;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentCalcTestUtil {

	public static void printLoan(FileWriter writer, RepaymentLoanInfoBO loanInfoBO) throws IOException {
		writer.write("\n");
		writer.write("还款方式：" + RepaymentType.valueOf(loanInfoBO.getRepaymentMethod()).desc());
		writer.write("\n");
		writer.write("放款金额：" + MoneyUtil.cent2Yuan(loanInfoBO.getFinanceAmount()));
		writer.write("\n");
		writer.write("放款日期：" + loanInfoBO.getLoanDate());
		writer.write("\n");
		writer.write("放款期限：" + loanInfoBO.getLoanPeriod() + DateUnit.valueOf(loanInfoBO.getLoanPeriodUnit()).desc());
		writer.write("\n");
		writer.write("放款利率：" + InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()).desc() + "息" + MoneyArithUtil.convertInterestRateToString(loanInfoBO.getInterestRate()));
		writer.write("\n");
		writer.write("展期天数：" + loanInfoBO.lazyLoadRepaymentConfig().getExtensionDays());
		writer.write("\n");
		writer.flush();
	}

	public static void printPlans(FileWriter writer, List<RepaymentPlanBO> plans) throws IOException {
		writer.write("还款期数,融资金额,融资余额,起息日期,结息日期,固定还款日,"
				+ "应还正常本金,应还正常利息,应还逾期本金,应还逾期利息,"
				+ "已还正常本金,已还正常利息,已还逾期本金,已还逾期利息,结算状态,展期天数\n");
		for (RepaymentPlan plan : plans) {
			printBlank(writer, "" + plan.getRepaymentPeriod());
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getFinanceAmount()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getFinanceBalance()));
			printBlank(writer, plan.getValueDate());
			printBlank(writer, plan.getSettleInterestDate());
			printBlank(writer, plan.getRepaymentDate());
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getAccountPrincipal()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getAccountInterest()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getOverduePrincipal()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getOverdueInterest()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getPaidNormalPrincipal()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getPaidNormalInterest()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getPaidOverduePrincipal()));
			printBlank(writer, MoneyUtil.cent2Yuan(plan.getPaidOverdueInterest()));
			printBlank(writer, plan.getSettleStatus());
			printBlank(writer, String.valueOf(plan.getExtensionDays()));
			writer.write("\n");
		}
		writer.flush();
	}

	private static void printBlank(FileWriter writer, String content) throws IOException {
		writer.write(content);
		writer.write(',');
	}

	public static RepaymentLoanInfo generateRepaymentLoanInfo(RepaymentType repaymentType) {
		RepaymentLoanInfo loanInfo = new RepaymentLoanInfo();
		loanInfo.setId("loanId" + UUID.randomUUID().toString().substring(0, 10));
		loanInfo.setLoanNo("loanNo");
		loanInfo.setFactorId("factorId");
		loanInfo.setFactorCompany("factorCompany");
		loanInfo.setFinanceId("finaceId");
		loanInfo.setFinanceCompany("finCompany");
		loanInfo.setCoreCompanyId("coreCompanyId");
		loanInfo.setCoreCompanyName("coreCompanyName");
		loanInfo.setBusinessMode(BusinessMode.FACTORANDFINACE.name());
		loanInfo.setInputMethod(InputMethod.AUTO.name());

		// 默认值
		loanInfo.setFinanceAmount(100000 * 100l);
		loanInfo.setLoanDate("2016-01-01");
		if (repaymentType == RepaymentType.ORDER) {
			loanInfo.setRepaymentPeriod(1);
			loanInfo.setLoanPeriod("1");
			loanInfo.setLoanPeriodUnit(DateUnit.MONTH.name());
		} else {
			loanInfo.setRepaymentPeriod(6);
			loanInfo.setLoanPeriod("6");
			loanInfo.setLoanPeriodUnit(DateUnit.MONTH.name());
		}
		loanInfo.setRepaymentMethod(repaymentType.name());
		loanInfo.setInterestRateUnit(InterestRateUnit.DAY.name());
		loanInfo.setInterestRate(new BigDecimal("0.0005"));
		loanInfo.setFinanceBalance(loanInfo.getFinanceAmount());
		return loanInfo;
	}

	public static void testRepaymentInAdvance(FileWriter writer, IRepaymentCalc repaymentCalc, RepaymentLoanInfoBO loanInfoBO, String repaymentDate10, long amount) throws Exception {
		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setRepaymentDate(repaymentDate10);
		context.setRepaymentMethod(RepaymentMethod.ACCOUNTDEDUCTION);

		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId());
		LoanInfoRepaymentResult repaymentResult = repaymentCalc.repayment(loanInfoBO, amount, context);

		writer.write("\n");
		writer.write(repaymentDate10 + "还款" + MoneyUtil.cent2Yuan(amount)
			+ ", 本次实际还款金额为：" + MoneyUtil.cent2Yuan(repaymentResult.getActualRepaymentTotalAmount())
			+ ", 本次实际还款本金为：" + MoneyUtil.cent2Yuan(repaymentResult.getActualRepaymentPrincipal()) + "\n");
		writer.write("还款后的计划为：\n");
		writer.flush();
		RepaymentCalcTestUtil.printPlans(writer, loanInfoBO.lazyLoadPlans());
	}

	/**
	 * 生成首次还款计划
	 * @param repaymentCalc
	 * @param loanInfoBO
	 * @throws Exception
	 */
	public static void testGeneratePlans(FileWriter writer, IRepaymentCalc repaymentCalc, RepaymentLoanInfoBO loanInfoBO) throws Exception {
		String dueDate = repaymentCalc.calcLoanDueDate(loanInfoBO.getLoanDate(), DateUnit.valueOf(loanInfoBO.getLoanPeriodUnit()), Integer.valueOf(loanInfoBO.getLoanPeriod()));
		loanInfoBO.setDueDate(dueDate);

		serviceHelper.createLoanInfoConfig(loanInfoBO, new BigDecimal("0.00075"));
		repaymentLoanInfoMapper.insertSelective(loanInfoBO);
		RepaymentCalcContext context = new RepaymentCalcContext();
		context.setUseHolidayPolicy(true);
		repaymentCalc.generateRepaymentPlan(loanInfoBO, context);

		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId());
		RepaymentCalcTestUtil.printLoan(writer, loanInfoBO);
		
		writer.write("\n");
		writer.write("首次生成还款计划：\n");
		writer.flush();
		RepaymentCalcTestUtil.printPlans(writer, loanInfoBO.lazyLoadPlans());
	}

	private static RepaymentLoanInfoMapper repaymentLoanInfoMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentLoanInfoMapper.class);
	private static IRepaymentServiceHelper serviceHelper = SpringApplicationContext.getApplicationContext().getBean(IRepaymentServiceHelper.class);;
}

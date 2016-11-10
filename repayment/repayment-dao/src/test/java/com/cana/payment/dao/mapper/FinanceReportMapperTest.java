package com.cana.payment.dao.mapper;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.repayment.dao.mapper.IFinanceReportMapper;
import com.cana.repayment.dao.mapper.IRepaymentPlanAndExpenseSearchMapper;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListCriteria;
import com.cana.vbam.common.repayment.dto.QueryPlanListCriteria;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryRequestDTO;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@ContextConfiguration("classpath:spring/test-repayment-dao-datasource.xml")
public class FinanceReportMapperTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IFinanceReportMapper mapper;
	
	@Resource
	private IRepaymentPlanAndExpenseSearchMapper repaymentPlanAndExpenseSearchMapper;
	
	@Test
	public void getFinanceBalanceByFinanceCompany() {
		System.out.println(mapper.getFinanceBalanceByFinanceCompany("竹子融资客户"));
	}
	
	@Test
	public void getFinanceBalanceByFactorId() {
		System.out.println(mapper.getFinanceBalanceByFactorId("201512181509"));
	}

	@Test
	public void getUnsettlledLoanInfoNumByFinanceCompany() {
		System.out.println(mapper.getUnsettlledLoanInfoNumByFinanceCompany("竹子融资客户"));
	}
	
	@Test
	public void getUnsettlledLoanInfoNumByFactorId() {
		System.out.println(mapper.getUnsettlledLoanInfoNumByFactorId("201512181509"));
	}

	@Test
	public void getMaybeOverduePlansByFinanceCompany() {
		for(RepaymentPlan plan : mapper.getMaybeOverduePlansByFinanceCompany("竹子融资客户"))
			System.out.println(new Gson().toJson(plan));
	}
	
	@Test
	public void getMaybeOverduePlansByFactorId() {
		for(RepaymentPlan plan : mapper.getMaybeOverduePlansByFactorId("201512181509"))
			System.out.println(new Gson().toJson(plan));
	}
	
	@Test
	public void getToPayAmountOfPlansByFinanceCompany(){
		System.out.println(mapper.getToPayAmountOfPlansByFinanceCompany("竹子融资客户", "2016-12-30", "2016-12-31"));
	}
	
	@Test
	public void getToPayAmountOfExpensesByFinanceCompany(){
		System.out.println(mapper.getToPayAmountOfExpensesByFinanceCompany("竹子融资客户", "2016-12-28", "2016-12-29"));
	}
	
	@Test
	public void getRepaymentPlan(){
//		List<com.cana.repayment.dao.po.manual.ActiveRepaymentPlan> list = repaymentPlanAndExpenseSearchMapper.searchRepaymentPlan("0001-01-01", "2017-05-29", "", "", "", "","","false","","", 1, 10);
//		System.out.println(list.size());
	}
	
	@Test
	public void queryRepaymentSummary_all_record(){
		System.out.println(new Gson().toJson(mapper.queryRepaymentSummary(new QueryRepaymentSummaryRequestDTO())));
	}
	
	@Test
	public void queryRepaymentSummary_all_where(){
		QueryRepaymentSummaryRequestDTO request = new QueryRepaymentSummaryRequestDTO();
		request.setBusinessProductId("travelzen_finance");
		request.setFactorId("201602270009");
		request.setFinanceId("201602270008");
		System.out.println(new Gson().toJson(mapper.queryRepaymentSummary(request)));
	}
	
	@Test
	public void queryUnsettledPlanIdListInNormalState(){
		System.out.println(new Gson().toJson(mapper.queryUnsettledPlanIdListInNormalState(new QueryRepaymentSummaryRequestDTO())));
	}
	
	@Test
	public void queryLoanInfoList(){
		QueryLoanInfoListCriteria request = new QueryLoanInfoListCriteria();
		request.setBusinessProductId("travelzen_finance");
		request.setFactorId("201602270009");
		request.setFinanceId("201602270008");
		request.setDueDateStart("2015-12-30");
		request.setDueDateEnd("2016-12-30");
		request.setLoanDateStart("2015-12-30");
		request.setLoanDateEnd("2016-12-30");
		request.setOrderByClause("loan_date");
		request.setSettleStatus(SettleStatus.SETTLED);
		request.setOffset(0);
		request.setLimit(10);
		System.out.println(new Gson().toJson(mapper.queryLoanInfoList(request)));
	}
	
	@Test
	public void queryLoanInfoList_wildcard(){
		QueryLoanInfoListCriteria request = new QueryLoanInfoListCriteria();
		request.setFinanceCompany("%finance%");
		System.out.println(new Gson().toJson(mapper.queryLoanInfoList(request)));
	}
	
	@Test
	public void countLoanInfos_wildcard(){
		QueryLoanInfoListCriteria request = new QueryLoanInfoListCriteria();
		request.setFinanceCompany("%finance%");
		System.out.println(mapper.countLoanInfos(request));
	}
	
	@Test
	public void countOverduePlans(){
		QueryPlanListCriteria criteria = new QueryPlanListCriteria();
		criteria.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		criteria.setFinanceId("2016050500471");
		criteria.setStartTime(DateTimeUtil.parseDate10("2016-04-20").toDate());
		criteria.setEndTime(DateTimeUtil.parseDate10("2016-04-23").toDate());
		System.out.println(mapper.countOverduePlans(criteria));
	}
	
	@Test
	public void queryPlanList(){
		QueryPlanListCriteria criteria = new QueryPlanListCriteria();
		criteria.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		criteria.setRepaymentDateStart("2016-04-01");
		criteria.setRepaymentDateEnd("2016-07-01");
		criteria.setOrderByClause("plan.repayment_date, plan.create_time");
		for(RepaymentPlan plan : mapper.queryPlanList(criteria))
			System.out.println(plan.getRepaymentDate());
	}
	
	@Test
	public void queryOverduePlanIds(){
		QueryPlanListCriteria criteria = new QueryPlanListCriteria();
		criteria.setBusinessProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		criteria.setFinanceId("2016050500471");
		criteria.setStartTime(DateTimeUtil.parseDate10("2016-04-20").toDate());
		criteria.setEndTime(DateTimeUtil.parseDate10("2016-04-23").toDate());
		System.out.println(mapper.queryOverduePlanIds(criteria));
	}
}

package com.cana.asset.server.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.asset.service.transaction.IAssetInvoiceTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.util.LoanAndUnderlyingAssetIdUtils;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.CreditRequestDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.loan.dto.AssetPaidPlanRequest;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.member.dto.user.UserSessionDTO;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml", "classpath*:spring/asset-service-*.xml"})
public class AssetLoanInfoTest {

	private Gson gson = new Gson();

	private UserVo userVo;
	private String customerId = "160817133949602";
	private String factorId = "201607050033";

	@Resource
	private IMemberQueryApi memberQueryApi;

	@Resource
	private IAssetLoanInfoTransactionService loanInfoTransactionService;

	@Before
	public void before() {
		userVo = memberQueryApi.findUserById(factorId);
	}

	@Test
	public void testCreate() throws Exception {
		EditAssetLoanRequest request = prepareLoanInfo();
		request.setPlans(prepareLoanPlans());
		loanInfoTransactionService.createAssetLoan(userVo, request);

		request.setFinanceAmount("120000");
		request.getPlans().get(0).setAccountPrincipal("70000");
		request.getPlans().get(0).setFinanceBalance("120000");
		loanInfoTransactionService.updateAssetLoan(userVo, request);
	}


	@Test
//	@Transactional
	public void testImport() throws Exception {
		EditAssetLoanRequest request = prepareLoanInfo();
		request.setLoanInfoId(LoanAndUnderlyingAssetIdUtils.generateLoanInfoId(request.getContractNo()));
		loanInfoTransactionService.checkImportAssetLoanInfoRequest(userVo, request);
		loanInfoTransactionService.importAssetLoanInfo(userVo, Lists.newArrayList(request));

		request.setPlans(prepareLoanPlans());
		loanInfoTransactionService.checkImportAssetLoanPlanRequest(userVo, request);
		loanInfoTransactionService.importAssetLoanPlan(userVo, Lists.newArrayList(request));
	}

	@Test
	public void testCreateLoanForHomsom() throws Exception {
		EditAssetLoanRequest request = prepareLoanInfo();
		loanInfoTransactionService.createAssetLoanForHomsom(userVo, request);
	}

	@Test
	public void testPaid() throws Exception {
		AssetPaidPlanRequest paidRequest = new AssetPaidPlanRequest();
		paidRequest.setLoanInfoId("52b731c18ec2-1");
		AssetPaidPlanRequest.PaidSinglePlan paidPlan = new AssetPaidPlanRequest.PaidSinglePlan();
		paidPlan.setLoanPlanId("160825143905501");
		paidPlan.setPaidDate("2016-11-30");
		paidPlan.setPaidPrincipal("1");
		paidPlan.setPaidInterest("1");
		paidPlan.setPaidOverdue("1");
		paidPlan.setSettleStatus(SettleStatus.UNSETTLE.name());
		paidRequest.setPaidPlans(Lists.newArrayList(paidPlan));
		
		loanInfoTransactionService.paidAssetLoanPlan(userVo, paidRequest);
	}

	private EditAssetLoanRequest prepareLoanInfo() {
		String contractNo = UUID.randomUUID().toString().substring(24);
		prepareCredit(contractNo, customerId);
		String counterpartyId = prepareBusiness(contractNo, customerId);
		String invoiceId = prepareInvoice(contractNo, counterpartyId);

		System.out.println("合同号：" + contractNo);

		EditAssetLoanRequest request = new EditAssetLoanRequest();
		request.setContractNo(contractNo);
		request.setCounterpartyId(counterpartyId);
		request.setFinanceAmount("100000.0");
		request.setCurrency(Currency.RMB.name());
		request.setRepaymentType(RepaymentType.MONTHLY.name());
		request.setLoanPeriodUnit(DateUnit.MONTH.name());
		request.setLoanPeriod(2);
		request.setDayCountConvention(360);
		request.setInterestRateUnit(InterestRateUnit.DAY.name());
		request.setInterestRate("0.05%");
		request.setLoanDate("2016-08-17");
		request.setDueDate("2016-09-20");
		request.setPenaltyRate("0.075%");
		request.setRepaymentAccountNo("12345678910");
		request.setInvoiceInfoIds(Lists.newArrayList(invoiceId));
		request.setExpenses(new ArrayList<EditAssetLoanRequest.Expense>());
		
		return request;
	}

	private List<LoanPlanDTO> prepareLoanPlans() {
		List<LoanPlanDTO> plans = new ArrayList<LoanPlanDTO>();
		LoanPlanDTO plan = new LoanPlanDTO();
		plan.setFinanceBalance("100000");
		plan.setValueDate("2016-08-17");
		plan.setSettleInterestDate("2016-09-18");
		plan.setRepaymentDate("2016-09-18");
		plan.setAccountPrincipal("50000");
		plan.setAccountInterest("345.23");
		plan.setAccountOverdue("2345.6");
		plan.setAccountAmount("2345.6");
		plan.setSettleStatus(SettleStatus.UNSETTLE.name());
		plans.add(plan);

		plan = new LoanPlanDTO();
		plan.setFinanceBalance("50000");
		plan.setValueDate("2016-09-18");
		plan.setSettleInterestDate("2016-09-20");
		plan.setRepaymentDate("2016-09-20");
		plan.setAccountPrincipal("50000");
		plan.setAccountInterest("345.23");
		plan.setAccountOverdue("2345.6");
		plan.setAccountAmount("2345.6");
		plan.setSettleStatus(SettleStatus.UNSETTLE.name());
		plans.add(plan);
		return plans;
	}

	private void prepareCredit(String contractNo, String customerId) {
		CreditRequestDTO request = new CreditRequestDTO();
		request.setCustomerId(customerId);
		request.setBusinessContractNo(contractNo);
		request.setTotalLimit("10000000.00");
		request.setEffectiveDate(DateTimeUtil.date10(DateTime.now().minusMonths(2)));
		request.setDueDate(DateTimeUtil.date10(DateTime.now().plusMonths(12)));
		request.setCurrency(Currency.RMB.name());
		request.setCreditMode(CreditMode.SYNTHETICAL.name());
		creditTransactionService.applyCredit(request, userVo);
	}


	private String prepareBusiness(String contractNo, String customerId) {
		FactorBusinessDTO factorBusinessDTO = businessTransactionService.queryFactorBusinessInfoByBusinessContractNo("3213213123214", factorId);
		factorBusinessDTO.setId(null);
		factorBusinessDTO.setBusinessContractNo(contractNo);
		factorBusinessDTO.setCustomerId(customerId);
		businessTransactionService.saveFactorBusinessInfo(factorBusinessDTO);
		return factorBusinessDTO.getCounterpartyList().get(0).getCounterpartyId();
	}

	private String prepareInvoice(String contractNo, String counterpartyId) {
		InvoiceQueryDTO queryDTO = new InvoiceQueryDTO();
		queryDTO.setBusinessContractNo("123456789123465798");
		queryDTO.setCounterpartyId("160815110837202");
		InvoiceListDTO invoice = invoiceTrasactionService.getInvByExample(queryDTO);
		invoice.setId(null);
		invoice.setBusinessContractNo(contractNo);
		invoice.getInvoiceInfoDTOs().get(0).setId(null);
		invoice.getInvoiceInfoDTOs().get(0).setCounterpartyId(counterpartyId);

		UserSessionDTO user = new UserSessionDTO();
		user.setId(userVo.getCustomerId());
		user.setUsername("canabl");
		invoiceTrasactionService.updateInvManage(invoice, user);

		queryDTO.setBusinessContractNo(contractNo);
		queryDTO.setCounterpartyId(counterpartyId);
		invoice = invoiceTrasactionService.getInvByExample(queryDTO);
		return invoice.getInvoiceInfoDTOs().get(0).getId();
	}

	@Resource
	private IAssetCreditTransactionService creditTransactionService;
	@Resource
	private IAssetFactorBusinessTransactionService businessTransactionService;
	@Resource
	private IAssetInvoiceTransactionService invoiceTrasactionService;
}

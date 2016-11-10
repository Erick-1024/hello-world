package com.cana.repayment.service.transaction.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.IFinanceReportMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.bo.RepaymentSingleCollectBO;
import com.cana.repayment.service.bo.RepaymentSingleDistributeDetailBO;
import com.cana.repayment.service.handler.RepaymentCalcFactory;
import com.cana.repayment.service.transaction.IFinanceReportTransactionService;
import com.cana.vbam.common.repayment.dto.FinanceInfo4CoreCompanyDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FactorDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FinanceDTO;
import com.cana.vbam.common.repayment.dto.LoanInfoDetail;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoDetailRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoDetailResponseDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListCriteria;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListResponseDTO;
import com.cana.vbam.common.repayment.dto.QueryPlanListCriteria;
import com.cana.vbam.common.repayment.dto.QueryPlanListRequest;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryResponseDTO;
import com.cana.vbam.common.repayment.dto.RepaymentAmount;
import com.cana.vbam.common.repayment.dto.RepaymentDetail;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;

@Service("FinanceReportTransactionServiceImpl")
public class FinanceReportTransactionServiceImpl implements IFinanceReportTransactionService {

	@Resource
	private IFinanceReportMapper reportMapper;
	
	@Resource
	private IRepositoryService repositoryService;

	@Override
	public FinanceInfo4FinanceDTO getFinanceInfo4Finance(String financeCompany) throws Exception {
		if (StringUtils.isBlank(financeCompany))
			throw WebException.instance("参数为空");
		FinanceInfo4FinanceDTO info = new FinanceInfo4FinanceDTO();
		Long financeBalance = reportMapper.getFinanceBalanceByFinanceCompany(financeCompany);
		info.setFinanceBalance(financeBalance == null ? 0 : financeBalance);
		Integer loanInfoNum = reportMapper.getUnsettlledLoanInfoNumByFinanceCompany(financeCompany);
		info.setLoanInfoNum(loanInfoNum == null ? 0 : loanInfoNum);
		List<RepaymentPlanBO> maybeOverduePlanBOs = getMaybeOverduePlansByFinanceCompany(financeCompany);
		String curDate10 = DateTimeUtil.date10();
		info.setOverdueNum(overdueNum(maybeOverduePlanBOs, curDate10));
		info.setTotalOverdueAmount(totalOverdueAmount(maybeOverduePlanBOs, curDate10));
		info.setToPayAmount(getToPayAmount(financeCompany, 7, curDate10));
		return info;
	}
	
	@Override
	public FinanceInfo4CoreCompanyDTO getFinanceInfo4CoreCompany(String coreCompanyId) throws Exception {
		if (StringUtils.isBlank(coreCompanyId))
			throw WebException.instance("参数为空");
		FinanceInfo4CoreCompanyDTO info = new FinanceInfo4CoreCompanyDTO();
		Long financeBalance = reportMapper.getFinanceBalanceByCoreCompany(coreCompanyId);
		info.setFinanceBalance(financeBalance == null ? 0 : financeBalance);
		Integer loanInfoNum = reportMapper.getUnsettlledLoanInfoNumByCoreCompany(coreCompanyId);
		info.setLoanInfoNum(loanInfoNum == null ? 0 : loanInfoNum);
		List<RepaymentPlanBO> maybeOverduePlanBOs = getMaybeOverduePlansByCoreCompany(coreCompanyId);
		String curDate10 = DateTimeUtil.date10();
		info.setOverdueNum(overdueNum(maybeOverduePlanBOs, curDate10));
		info.setTotalOverdueAmount(totalOverdueAmount(maybeOverduePlanBOs, curDate10));
		info.setToPayAmount(getToPayAmountForCoreCompany(coreCompanyId, 7, curDate10));
		return info;
	}

	private long getToPayAmount(String financeCompany, int days, String curDate) {
		DateTime now = new DateTime();
		String startDate = DateTimeUtil.date10(now);
		String endDate = DateTimeUtil.date10(DateTimeUtil.addDay(now, days));
		Long plans = reportMapper.getToPayAmountOfPlansByFinanceCompany(financeCompany, startDate, endDate);
		Long expenses = reportMapper.getToPayAmountOfExpensesByFinanceCompany(financeCompany, startDate, endDate);
		Long toPayAmount = (plans == null ? 0 : plans) +(expenses == null ? 0 : expenses);
		return toPayAmount ;
	}
	
	private long getToPayAmountForCoreCompany(String coreCompanyId, int days, String curDate) {
		DateTime now = new DateTime();
		String startDate = DateTimeUtil.date10(now);
		String endDate = DateTimeUtil.date10(DateTimeUtil.addDay(now, days));
		Long plans = reportMapper.getToPayAmountOfPlansByCoreCompany(coreCompanyId, startDate, endDate);
		Long expenses = reportMapper.getToPayAmountOfExpensesByCoreCompany(coreCompanyId, startDate, endDate);
		Long toPayAmount = (plans == null ? 0 : plans) +(expenses == null ? 0 : expenses);
		return toPayAmount ;
	}

	private long totalOverdueAmount(List<RepaymentPlanBO> maybeOverduePlanBOs, String curDate10) {
		long total = 0;
		for (RepaymentPlanBO plan : maybeOverduePlanBOs)
			total += plan.totalOverdueAmount();
		return total;
	}

	private int overdueNum(List<RepaymentPlanBO> maybeOverduePlanBOs, String curDate10) {
		int num = 0;
		for (RepaymentPlanBO plan : maybeOverduePlanBOs)
			if (plan.inOverdueState(curDate10))
				++num;
		return num;
	}

	private List<RepaymentPlanBO> getMaybeOverduePlansByFinanceCompany(String financeCompany) {
		List<RepaymentPlanBO> planBOs = new ArrayList<>();
		for (RepaymentPlan plan : reportMapper.getMaybeOverduePlansByFinanceCompany(financeCompany))
			planBOs.add(new RepaymentPlanBO(plan));
		return planBOs;
	}

	private List<RepaymentPlanBO> getMaybeOverduePlansByCoreCompany(String coreCompanyId) {
		List<RepaymentPlanBO> planBOs = new ArrayList<>();
		for (RepaymentPlan plan : reportMapper.getMaybeOverduePlansByCoreCompany(coreCompanyId))
			planBOs.add(new RepaymentPlanBO(plan));
		return planBOs;
	}

	@Override
	public FinanceInfo4FactorDTO getFinanceInfo4Factor(String factorId) throws Exception {
		if (StringUtils.isBlank(factorId))
			throw WebException.instance("参数为空");
		FinanceInfo4FactorDTO info = new FinanceInfo4FactorDTO();
		Long financeBalance = reportMapper.getFinanceBalanceByFactorId(factorId);
		info.setFinanceBalance(financeBalance == null ? 0 : financeBalance);
		Integer loanInfoNum = reportMapper.getUnsettlledLoanInfoNumByFactorId(factorId);
		info.setLoanInfoNum(loanInfoNum == null ? 0 : loanInfoNum);
		List<RepaymentPlanBO> maybeOverduePlanBOs = getMaybeOverduePlansByFactorId(factorId);
		String curDate10 = DateTimeUtil.date10();
		info.setOverdueNum(overdueNum(maybeOverduePlanBOs, curDate10));
		info.setTotalOverdueAmount(totalOverdueAmount(maybeOverduePlanBOs, curDate10));
		return info;
	}

	private List<RepaymentPlanBO> getMaybeOverduePlansByFactorId(String factorId) {
		List<RepaymentPlanBO> planBOs = new ArrayList<>();
		for (RepaymentPlan plan : reportMapper.getMaybeOverduePlansByFactorId(factorId))
			planBOs.add(new RepaymentPlanBO(plan));
		return planBOs;
	}

	@Override
	public QueryRepaymentSummaryResponseDTO queryRepaymentSummary(QueryRepaymentSummaryRequestDTO request)
			throws Exception {
		
		QueryRepaymentSummaryResponseDTO response = new QueryRepaymentSummaryResponseDTO();
		
		RepaymentAmount repaymentAmount = reportMapper.queryRepaymentSummary(request);
		
		if(repaymentAmount == null)
			repaymentAmount = new RepaymentAmount();
		
		
		//计算当日应还利息--开始
		long totalAccountInterest = 0;
		long totalAccountServiceCharge = 0;
		
		List<RepaymentPlanBO> planBOs = repositoryService.batchLoadRepaymentPlanBOs(reportMapper.queryUnsettledPlanIdListInNormalState(request));
		repositoryService.batchLoadRepaymentLoanInfoBOsByRepaymentPlanBOs(planBOs);
		
		for(RepaymentPlanBO planBO : planBOs){
			RepaymentLoanInfoBO loanInfoBO = planBO.lazyLoadLoanInfoBO();
			RepaymentAmount accountInterestAndAccountServiceCharge = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcAccountInterestAndAccountServiceChargeUntilNow(loanInfoBO, planBO);
			totalAccountInterest += accountInterestAndAccountServiceCharge.getAccountInterest();
			totalAccountServiceCharge +=  accountInterestAndAccountServiceCharge.getAccountServiceCharge();
		}
		
		repaymentAmount.setAccountInterest(totalAccountInterest);
		repaymentAmount.setAccountServiceCharge(totalAccountServiceCharge);
		//计算当日应还利息--开始
		
		response.setRepaymentAmount(repaymentAmount);
		return response;
	}

	@Override
	public QueryLoanInfoListResponseDTO queryLoanInfoList(QueryLoanInfoListRequestDTO request) throws Exception {
		checkQueryLoanInfoListRequestDTO(request);
		QueryLoanInfoListCriteria criteria = queryLoanInfoListRequestDTO2QueryLoanInfoListCriteria(request);
		List<RepaymentLoanInfo> loanInfoList = reportMapper.queryLoanInfoList(criteria);
		List<LoanInfoDetail> loanInfoDetailList = new ArrayList<>();
		for(RepaymentLoanInfo loanInfo : loanInfoList)
			loanInfoDetailList.add(loanInfoBO2LoanInfoDetail(new RepaymentLoanInfoBO(loanInfo)));
		QueryLoanInfoListResponseDTO response = new QueryLoanInfoListResponseDTO();
		response.setTotalNum(reportMapper.countLoanInfos(criteria));
		response.setLoanInfoDetailList(loanInfoDetailList);
		return response;
	}

	private LoanInfoDetail loanInfoBO2LoanInfoDetail(RepaymentLoanInfoBO loanInfoBO) throws Exception{
		LoanInfoDetail loanInfoDetail = new LoanInfoDetail();
		loanInfoDetail.setLoanInfoId(loanInfoBO.getId());
		loanInfoDetail.setLoanNo(loanInfoBO.getLoanNo());
		loanInfoDetail.setLoanDate(loanInfoBO.getLoanDate());
		loanInfoDetail.setDueDate(loanInfoBO.getDueDate());
		loanInfoDetail.setFinanceAmount(loanInfoBO.getFinanceAmount());
		loanInfoDetail.setSettleStatus(SettleStatus.valueOf(loanInfoBO.getSettleStatus()));
		loanInfoDetail.setRepaymentAmount(loanInfoBO2LoanInfoDetailRepaymentAmount(loanInfoBO));
		loanInfoDetail.setExtensionDays(getExtensionDays(loanInfoBO));
		loanInfoDetail.setFinanceId(loanInfoBO.getFinanceId());
		loanInfoDetail.setFinanceCompany(loanInfoBO.getFinanceCompany());
		loanInfoDetail.setOutCustomerId(loanInfoBO.getOutCustomerId());
		loanInfoDetail.setOutCustomerName(loanInfoBO.getOutCustomerName());
		loanInfoDetail.setInterestRateUnit(InterestRateUnit.valueOf(loanInfoBO.getInterestRateUnit()));
		loanInfoDetail.setInterestRate(loanInfoBO.getInterestRate());
		return loanInfoDetail;
	}
	
	/**
	 * 获取放款信息对应的展期天数
	 * @param loanInfoBO
	 * @return
	 * @throws Exception
	 */
	private Integer getExtensionDays(RepaymentLoanInfoBO loanInfoBO) throws Exception{
		Integer extensionDays = null;
		for(RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()){
			extensionDays = planBO.getExtensionDays();
		}
		return extensionDays;
	}

	private RepaymentAmount loanInfoBO2LoanInfoDetailRepaymentAmount(RepaymentLoanInfoBO loanInfoBO) throws Exception{
		RepaymentAmount repaymentAmount = new RepaymentAmount();
		for(RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()){
			repaymentAmount.setAccountPrincipal(repaymentAmount.getAccountPrincipal() + planBO.getAccountPrincipal());
			RepaymentAmount accountInterestAndAccountServiceCharge = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcAccountInterestAndAccountServiceChargeUntilNow(loanInfoBO, planBO);
			repaymentAmount.setAccountInterest(repaymentAmount.getAccountInterest() + accountInterestAndAccountServiceCharge.getAccountInterest());
			repaymentAmount.setAccountServiceCharge(repaymentAmount.getAccountServiceCharge() + accountInterestAndAccountServiceCharge.getAccountServiceCharge());
			repaymentAmount.setAccountExtensionCharge(repaymentAmount.getAccountExtensionCharge() + planBO.getAccountExtensionCharge());
			repaymentAmount.setOverduePrincipal(repaymentAmount.getOverduePrincipal() + planBO.getOverduePrincipal());
			repaymentAmount.setOverdueInterest(repaymentAmount.getOverdueInterest() + planBO.getOverdueInterest());
			repaymentAmount.setOverdueServiceCharge(repaymentAmount.getOverdueServiceCharge() + planBO.getOverdueServiceCharge());
			repaymentAmount.setOverduePrincipalPenalty(repaymentAmount.getOverduePrincipalPenalty() + planBO.getOverduePrincipalPenalty());
			repaymentAmount.setOverdueInterestPenalty(repaymentAmount.getOverdueInterestPenalty() + planBO.getOverdueInterestPenalty());
			repaymentAmount.setOverdueServiceChargePenalty(repaymentAmount.getOverdueServiceChargePenalty() + planBO.getOverdueServiceChargePenalty());
			repaymentAmount.setOtherPenalty(repaymentAmount.getOtherPenalty() + planBO.getOtherPenalty());
			repaymentAmount.setPaidNormalPrincipal(repaymentAmount.getPaidNormalPrincipal() + planBO.getPaidNormalPrincipal());
			repaymentAmount.setPaidNormalInterest(repaymentAmount.getPaidNormalInterest() + planBO.getPaidNormalInterest());
			repaymentAmount.setPaidNormalServiceCharge(repaymentAmount.getPaidNormalServiceCharge() + planBO.getPaidNormalServiceCharge());
			repaymentAmount.setPaidExtensionCharge(repaymentAmount.getPaidExtensionCharge() + planBO.getPaidExtensionCharge());
			repaymentAmount.setPaidOverduePrincipal(repaymentAmount.getPaidOverduePrincipal() + planBO.getPaidOverduePrincipal());
			repaymentAmount.setPaidOverdueInterest(repaymentAmount.getPaidOverdueInterest() + planBO.getPaidOverdueInterest());
			repaymentAmount.setPaidOverdueServiceCharge(repaymentAmount.getPaidOverdueServiceCharge() + planBO.getPaidOverdueServiceCharge());
			repaymentAmount.setPaidOverduePrincipalPenalty(repaymentAmount.getPaidOverduePrincipalPenalty() + planBO.getPaidOverduePrincipalPenalty());
			repaymentAmount.setPaidOverdueInterestPenalty(repaymentAmount.getPaidOverdueInterestPenalty() + planBO.getPaidOverdueInterestPenalty());
			repaymentAmount.setPaidOverdueServiceChargePenalty(repaymentAmount.getPaidOverdueServiceChargePenalty() + planBO.getPaidOverdueServiceChargePenalty());
			repaymentAmount.setPaidOtherPenalty(repaymentAmount.getPaidOtherPenalty() + planBO.getPaidOtherPenalty());
			repaymentAmount.setPaidEarlyRepaymentCharge(repaymentAmount.getPaidEarlyRepaymentCharge() + planBO.getPaidEarlyRepaymentCharge());
		}
		return repaymentAmount;
	}

	private QueryLoanInfoListCriteria queryLoanInfoListRequestDTO2QueryLoanInfoListCriteria(
			QueryLoanInfoListRequestDTO request) {
		QueryLoanInfoListCriteria criteria = new QueryLoanInfoListCriteria();	
		criteria.setFactorId(request.getFactorId());
		criteria.setFinanceId(request.getFinanceId());
		criteria.setOutCustomerId(request.getOutCustomerId());
		if(StringUtils.isNotBlank(request.getFinanceCompany()))
			criteria.setFinanceCompany("%" + request.getFinanceCompany() + "%");
		criteria.setBusinessProductId(request.getBusinessProductId());
		criteria.setLoanInfoId(request.getLoanInfoId());
		criteria.setLoanDateStart(request.getLoanDateStart());
		criteria.setLoanDateEnd(request.getLoanDateEnd());
		criteria.setDueDateStart(request.getDueDateStart());
		criteria.setDueDateEnd(request.getDueDateEnd());
		criteria.setOrderByClause(request.getOrderByClause());
		criteria.setSettleStatus(request.getSettleStatus());
		criteria.setOffset((request.getPage() - 1) * request.getPageSize());
		criteria.setLimit(request.getPageSize());
		return criteria;
	}

	private void checkQueryLoanInfoListRequestDTO(QueryLoanInfoListRequestDTO request) throws Exception{
		StringUtil.trimObjectFields(request);
		
		if(StringUtils.isNotBlank(request.getLoanDateStart()) && !DateTimeUtil.validateDate10(request.getLoanDateStart()))
			throw WebException.instance("loanDateStart格式不正确");
		
		if(StringUtils.isNotBlank(request.getLoanDateEnd()) && !DateTimeUtil.validateDate10(request.getLoanDateEnd()))
			throw WebException.instance("loanDateEnd格式不正确");
		
		if(StringUtils.isNotBlank(request.getDueDateStart()) && !DateTimeUtil.validateDate10(request.getDueDateStart()))
			throw WebException.instance("dueDateStart格式不正确");
		
		if(StringUtils.isNotBlank(request.getDueDateEnd()) && !DateTimeUtil.validateDate10(request.getDueDateEnd()))
			throw WebException.instance("dueDateEnd格式不正确");
		
		if(request.getPage() <= 0)
			throw WebException.instance("page不能小于1");
		
		if(request.getPageSize() > 30)
			throw WebException.instance("pageSize 不能大于30");
	}

	@Override
	public QueryLoanInfoDetailResponseDTO queryLoanInfoDetail(QueryLoanInfoDetailRequestDTO request) throws Exception {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(request.getLoanInfoId());
		QueryLoanInfoDetailResponseDTO response = new QueryLoanInfoDetailResponseDTO();
		response.setRepaymentAmount(loanInfoBO2LoanInfoDetailRepaymentAmount(loanInfoBO));
		response.setExtensionDays(getExtensionDays(loanInfoBO));
		List<RepaymentDetail> repaymentDetailList = new ArrayList<>();
		for(RepaymentSingleCollectBO repaymentSummaryBO : loanInfoBO.lazyLoadRepaymentSummaryBOs()){
			repaymentDetailList.add(repaymentSummaryBO2RepaymentDetail(repaymentSummaryBO));
		}
		response.setRepaymentDetailList(repaymentDetailList);
		return response;
	}

	
	private RepaymentDetail repaymentSummaryBO2RepaymentDetail(RepaymentSingleCollectBO repaymentSummaryBO) {
		RepaymentDetail repaymentDetail = new RepaymentDetail();
		repaymentDetail.setRepaymentDate(repaymentSummaryBO.getRepaymentDate());
		repaymentDetail.setRepaymentMethod(RepaymentMethod.valueOf(repaymentSummaryBO.getRepaymentType()));
		repaymentDetail.setRepaymentAmount(repaymentSummaryBO2RepaymentDetailRepaymentAmount(repaymentSummaryBO));
		repaymentDetail.setOpTime(repaymentSummaryBO.getCreateTime());
		return repaymentDetail;
	}

	private RepaymentAmount repaymentSummaryBO2RepaymentDetailRepaymentAmount(
			RepaymentSingleCollectBO repaymentSummaryBO) {
		
		RepaymentAmount repaymentAmount = new RepaymentAmount();
		
		for(RepaymentSingleDistributeDetailBO itemBO : repaymentSummaryBO.lazyLoadItemBOs()){
			repaymentAmount.setPaidNormalPrincipal(repaymentAmount.getPaidNormalPrincipal() + itemBO.getPayNormalPrincipal());
			repaymentAmount.setPaidNormalInterest(repaymentAmount.getPaidNormalInterest() + itemBO.getPayNormalInterest());
			repaymentAmount.setPaidNormalServiceCharge(repaymentAmount.getPaidNormalServiceCharge() + itemBO.getPayNormalServiceCharge());
			repaymentAmount.setPaidExtensionCharge(repaymentAmount.getPaidExtensionCharge() + itemBO.getPayExtensionCharge());
			repaymentAmount.setPaidOverduePrincipal(repaymentAmount.getPaidOverduePrincipal() + itemBO.getPayOverduePrincipal());
			repaymentAmount.setPaidOverdueInterest(repaymentAmount.getPaidOverdueInterest() + itemBO.getPayOverdueInterest());
			repaymentAmount.setPaidOverdueServiceCharge(repaymentAmount.getPaidOverdueServiceCharge() + itemBO.getPayOverdueServiceCharge());
			repaymentAmount.setPaidOverduePrincipalPenalty(repaymentAmount.getPaidOverduePrincipalPenalty() + itemBO.getPayOverduePrincipalPenalty());
			repaymentAmount.setPaidOverdueInterestPenalty(repaymentAmount.getPaidOverdueInterestPenalty() + itemBO.getPayOverdueInterestPenalty());
			repaymentAmount.setPaidOverdueServiceChargePenalty(repaymentAmount.getPaidOverdueServiceChargePenalty() + itemBO.getPayOverdueServiceChargePenalty());
			repaymentAmount.setPaidOtherPenalty(repaymentAmount.getPaidOtherPenalty() + itemBO.getPayOtherPenalty());
			repaymentAmount.setPaidEarlyRepaymentCharge(repaymentAmount.getPaidEarlyRepaymentCharge() + itemBO.getEarlyRepaymentCharge());
		}
		return repaymentAmount;
	}

	@Override
	public int countOverduePlans(QueryPlanListRequest request) throws Exception{
		checkQueryOverduePlanListRequest(request);
		return reportMapper.countOverduePlans(queryPlanListRequest2QueryPlanListCriteria(request));
	}
	
	public void checkQueryOverduePlanListRequest(QueryPlanListRequest request) {
		StringUtil.trimObjectFields(request);
		
		if(StringUtils.isNotBlank(request.getStartDate()) && !DateTimeUtil.validateDate10(request.getStartDate()))
			throw WebException.instance("startDate格式不正确");
		
		if(StringUtils.isNotBlank(request.getEndDate()) && !DateTimeUtil.validateDate10(request.getEndDate()))
			throw WebException.instance("endDate格式不正确");
	}

	@Override
	public int countContinueOverduePlans(QueryPlanListRequest request) throws Exception {
		
		checkQueryOverduePlanListRequest(request);
		
		List<RepaymentPlanBO> allPlanBOs = new ArrayList<>();
		QueryPlanListCriteria criteria = queryPlanListRequest2QueryPlanListCriteria(request);
		criteria.setOrderByClause("plan.repayment_date, plan.create_time");
		for(RepaymentPlan plan : reportMapper.queryPlanList(criteria))
			allPlanBOs.add(new RepaymentPlanBO(plan));
		
		List<RepaymentPlanBO> overduePlanBOs = overduePlanBOs2countContinueOverduePlans(criteria);
		
		int continueOverduePlanNum = 0;
		for(RepaymentPlanBO planBO : allPlanBOs){
			boolean overdued = false;
			for(RepaymentPlanBO overduePlanBO : overduePlanBOs){
				if(overduePlanBO.getId().equals(planBO.getId())){
					overdued = true;
					break;
				}
			}
			if(overdued){
				++continueOverduePlanNum;
			}else if(planBO.isSettled()){
				continueOverduePlanNum = 0;
			}
		}
		
		return continueOverduePlanNum;
	}
	
	private List<RepaymentPlanBO> overduePlanBOs2countContinueOverduePlans(QueryPlanListCriteria criteria) throws Exception{
		List<String> overduePlanIds = reportMapper.queryOverduePlanIds(criteria);
		List<RepaymentPlanBO> overduePlanBOs = repositoryService.batchLoadRepaymentPlanBOs(overduePlanIds);
		return overduePlanBOs;
	}

	private QueryPlanListCriteria queryPlanListRequest2QueryPlanListCriteria(QueryPlanListRequest request){
		QueryPlanListCriteria criteria = new QueryPlanListCriteria();
		criteria.setBusinessProductId(request.getBusinessProductId());
		criteria.setFinanceId(request.getFinanceId());
		if(StringUtils.isNotBlank(request.getOutCustomerId())){
			criteria.setOutCustomerId(request.getOutCustomerId());
		}
		if(StringUtils.isNotBlank(request.getStartDate())){
			criteria.setStartTime(DateTimeUtil.parseDate10(request.getStartDate()).toDate());
			criteria.setRepaymentDateStart(request.getStartDate());
		}
		if(StringUtils.isNotBlank(request.getEndDate())){
			criteria.setRepaymentDateEnd(request.getEndDate());
			criteria.setEndTime(DateTimeUtil.addDay(DateTimeUtil.parseDate10(request.getEndDate()), 1).toDate());
		}
		return criteria;
	}
	

}

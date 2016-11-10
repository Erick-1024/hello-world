package com.cana.repayment.server.api.impl;

import javax.annotation.Resource;

import com.cana.repayment.api.IFinanceReportApi;
import com.cana.repayment.service.transaction.IFinanceReportTransactionService;
import com.cana.vbam.common.repayment.dto.FinanceInfo4CoreCompanyDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FactorDTO;
import com.cana.vbam.common.repayment.dto.FinanceInfo4FinanceDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoDetailRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoDetailResponseDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListResponseDTO;
import com.cana.vbam.common.repayment.dto.QueryPlanListRequest;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryRequestDTO;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryResponseDTO;

public class FinanceReportApi implements IFinanceReportApi{

	@Resource
	private IFinanceReportTransactionService reportTransactionService;
	
	@Override
	public FinanceInfo4FinanceDTO getFinanceInfo4Finance(String financeCompany) throws Exception {
		return reportTransactionService.getFinanceInfo4Finance(financeCompany);
	}

	@Override
	public FinanceInfo4FactorDTO getFinanceInfo4Factor(String factorId) throws Exception {
		return reportTransactionService.getFinanceInfo4Factor(factorId);
	}

	@Override
	public FinanceInfo4CoreCompanyDTO getFinanceInfo4CoreCompany(String coreCompanyId) throws Exception {
		return reportTransactionService.getFinanceInfo4CoreCompany(coreCompanyId);
	}

	@Override
	public QueryRepaymentSummaryResponseDTO queryRepaymentSummary(QueryRepaymentSummaryRequestDTO request)
			throws Exception {
		return reportTransactionService.queryRepaymentSummary(request);
	}

	@Override
	public QueryLoanInfoListResponseDTO queryLoanInfoList(QueryLoanInfoListRequestDTO request) throws Exception {
		return reportTransactionService.queryLoanInfoList(request);
	}

	@Override
	public QueryLoanInfoDetailResponseDTO queryLoanInfoDetail(QueryLoanInfoDetailRequestDTO request) throws Exception {
		return reportTransactionService.queryLoanInfoDetail(request);
	}

	@Override
	public int countOverduePlans(QueryPlanListRequest request) throws Exception{
		return reportTransactionService.countOverduePlans(request);
	}

	@Override
	public int countContinueOverduePlans(QueryPlanListRequest request) throws Exception {
		return reportTransactionService.countContinueOverduePlans(request);
	}

}

package com.cana.repayment.service.transaction;

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

/**
 * 融资管理报表接口
 * @author renshui
 *
 */
public interface IFinanceReportTransactionService {

	/**
	 * 获取融资客户的融资信息
	 * @param financeCompany 融资客户的企业名称
	 * @return
	 * @throws Exception
	 */
	public FinanceInfo4FinanceDTO getFinanceInfo4Finance(String financeCompany) throws Exception;

	/**
	 * 获取核心企业的融资信息
	 * @param coreCompanyId 核心企业Id
	 * @return
	 * @throws Exception
	 */
	public FinanceInfo4CoreCompanyDTO getFinanceInfo4CoreCompany(String coreCompanyId) throws Exception;
	
	/**
	 * 获取保理商的融资信息
	 * @param factorId 保理商的id
	 * @return
	 * @throws Exception
	 */
	public FinanceInfo4FactorDTO getFinanceInfo4Factor(String factorId) throws Exception;
	
	/**
	 * 查询融资汇总信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public QueryRepaymentSummaryResponseDTO queryRepaymentSummary(QueryRepaymentSummaryRequestDTO request) throws Exception;
	
	/**
	 * 查询放款信息列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public QueryLoanInfoListResponseDTO queryLoanInfoList(QueryLoanInfoListRequestDTO request) throws Exception;
	
	/**
	 * 查询放款信息详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public QueryLoanInfoDetailResponseDTO queryLoanInfoDetail(QueryLoanInfoDetailRequestDTO request) throws Exception;
	
	/**
	 * 查询符合条件的逾期还款计划条数
	 * @param request
	 * @return
	 */
	public int countOverduePlans(QueryPlanListRequest request) throws Exception;

	/**
	 * 查询连续逾期次数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int countContinueOverduePlans(QueryPlanListRequest request) throws Exception;
	
}

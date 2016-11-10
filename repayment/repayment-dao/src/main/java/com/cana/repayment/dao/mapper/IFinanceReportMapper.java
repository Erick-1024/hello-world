package com.cana.repayment.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.vbam.common.repayment.dto.QueryLoanInfoListCriteria;
import com.cana.vbam.common.repayment.dto.QueryPlanListCriteria;
import com.cana.vbam.common.repayment.dto.QueryRepaymentSummaryRequestDTO;
import com.cana.vbam.common.repayment.dto.RepaymentAmount;

public interface IFinanceReportMapper {

	/**
	 * 根据融资客户企业名称获取总的融资余额
	 * @param financeId
	 * @return
	 */
	Long getFinanceBalanceByFinanceCompany(String financeCompany);

	/**
	 * 根据融资客户企业名称获取总的融资余额
	 * @param coreCompanyId
	 * @return
	 */
	Long getFinanceBalanceByCoreCompany(String coreCompanyId);
	
	
	/**
	 * 根据保理商id获取总的融资余额
	 * @param factorId
	 * @return
	 */
	Long getFinanceBalanceByFactorId(String factorId);


	/**
	 * 根据融资客户企业名称获取融资笔数
	 * @param financeId
	 * @return
	 */
	Integer getUnsettlledLoanInfoNumByFinanceCompany(String financeCompany);

	/**
	 * 根据核心企业名称获取融资笔数
	 * @param coreCompanyId
	 * @return
	 */
	Integer getUnsettlledLoanInfoNumByCoreCompany(String coreCompanyId);

	/**
	 * 根据保理商id获取融资笔数
	 * @param factorId
	 * @return
	 */
	Integer getUnsettlledLoanInfoNumByFactorId(String factorId);


	/**
	 * 根据融资客户id获取可能逾期的还款计划, 之所以是可能逾期， 是因为还款计划表中的展期费用不是很容易区分是当期还是逾期， 所以将所有的可能读到内存中来判断
	 * @param financeId
	 * @return
	 */
	List<RepaymentPlan> getMaybeOverduePlansByFinanceCompany(String financeCompany);

	/**
	 * 根据核心企业名称获取可能逾期的还款计划, 之所以是可能逾期， 是因为还款计划表中的展期费用不是很容易区分是当期还是逾期， 所以将所有的可能读到内存中来判断
	 * @param coreCompanyId
	 * @return
	 */
	List<RepaymentPlan> getMaybeOverduePlansByCoreCompany(String coreCompanyId);
	
	/**
	 * 根据保理商id获取可能逾期的还款计划, 之所以是可能逾期， 是因为还款计划表中的展期费用不是很容易区分是当期还是逾期， 所以将所有的可能读到内存中来判断
	 * @param factorId
	 * @return
	 */
	List<RepaymentPlan> getMaybeOverduePlansByFactorId(String factorId);
	
	/**
	 * 根据融资客户企业名称获取指定时间段内还款计划待还金额
	 * @param financeCompany
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Long getToPayAmountOfPlansByFinanceCompany(@Param("financeCompany") String financeCompany, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 根据核心企业名称获取指定时间段内还款计划待还金额
	 * @param coreCompanyId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Long getToPayAmountOfPlansByCoreCompany(@Param("coreCompanyId") String financeCompany, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 根据融资客户企业名称获取指定时间段内固定费用待还金额
	 * @param financeCompany
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Long getToPayAmountOfExpensesByFinanceCompany(@Param("financeCompany") String financeCompany, @Param("startDate") String startDate, @Param("endDate") String endDate);

	/**
	 * 根据核心企业名称获取指定时间段内固定费用待还金额
	 * @param financeCompany
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Long getToPayAmountOfExpensesByCoreCompany(@Param("coreCompanyId") String financeCompany, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	/**
	 * 查询融资汇总信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public RepaymentAmount queryRepaymentSummary(QueryRepaymentSummaryRequestDTO request);	
	
	/**
	 * 查询所有位于正常状态且未还清的还款计划id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<String> queryUnsettledPlanIdListInNormalState(QueryRepaymentSummaryRequestDTO request);	
	
	/**
	 * 查询放款信息列表
	 * @param request
	 * @return
	 */
	public List<RepaymentLoanInfo> queryLoanInfoList(QueryLoanInfoListCriteria request);
	
	/**
	 * 查询符合条件的放款信息条数
	 * @param request
	 * @return
	 */
	public int countLoanInfos(QueryLoanInfoListCriteria request);
	
	/**
	 * 查询符合条件的逾期还款计划条数
	 * @param criteria
	 * @return
	 */
	public int countOverduePlans(QueryPlanListCriteria criteria);
	
	/**
	 * 查询符合条件的逾期还款计划列表
	 * @param criteria
	 * @return
	 */
	public List<String> queryOverduePlanIds(QueryPlanListCriteria criteria);
	
	/**
	 * 查询符合条件的还款计划
	 * @param criteria
	 * @return
	 */
	public List<RepaymentPlan> queryPlanList(QueryPlanListCriteria criteria);
	
	
}

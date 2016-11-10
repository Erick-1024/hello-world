package com.cana.repayment.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cana.repayment.dao.po.manual.ActiveRepaymentExpense;
import com.cana.repayment.dao.po.manual.ActiveRepaymentPlan;
import com.cana.vbam.common.repayment.dto.QueryRepaymentAndExpenseRequestDTO;

public interface IRepaymentPlanAndExpenseSearchMapper {
	
	/**
	 * 按条件连接放款信息及还款计划(CANA,保理商，融资客户共用)
	 * @param beginRepaymentDate
	 * @param endRepaymentDate
	 * @param businessProduct
	 * @param loanNo
	 * @param financeId
	 * @param factorId
	 * @param financeCompany
	 * @param overdue
	 * @param settleStatus
	 * @param factorCompany
	 * @param coreCompanyName
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<ActiveRepaymentPlan> searchRepaymentPlan(@Param("queryDTO") QueryRepaymentAndExpenseRequestDTO queryDTO);
	
	/**
	 * 计算还款计划总条数(CANA,保理商，融资客户共用)
	 * @param beginRepaymentDate
	 * @param endRepaymentDate
	 * @param businessProduct
	 * @param loanNo
	 * @param financeId
	 * @param factorId
	 * @param financeCompany
	 * @param coreCompanyName
	 * @param overdue
	 * @param settleStatus
	 * @param factorCompany
	 * @return
	 */
	public int countRepaymentPlan(@Param("queryDTO") QueryRepaymentAndExpenseRequestDTO queryDTO);
	
	/**
	 * 按条件连接放款信息及费用列表(CANA,保理商，融资客户共用)
	 * @param beginRepaymentDate
	 * @param endRepaymentDate
	 * @param businessProduct
	 * @param loanNo
	 * @param financeId
	 * @param factorId
	 * @param financeCompany
	 * @param coreCompanyName
	 * @param settleStatus
	 * @param factorCompany
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<ActiveRepaymentExpense> searchRepaymentExpense(@Param("queryDTO") QueryRepaymentAndExpenseRequestDTO queryDTO);
	
	/**
	 * 计算费用总条数(CANA,保理商，融资客户共用)
	 * @param beginRepaymentDate
	 * @param endRepaymentDate
	 * @param businessProduct
	 * @param loanNo
	 * @param financeId
	 * @param factorId
	 * @param financeCompany
	 * @param coreCompanyName
	 * @param settleStatus
	 * @param factorCompany
	 * @return
	 */
	public int countRepaymentExpense(@Param("queryDTO") QueryRepaymentAndExpenseRequestDTO queryDTO);
}

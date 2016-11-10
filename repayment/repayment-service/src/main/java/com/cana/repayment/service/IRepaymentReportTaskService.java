package com.cana.repayment.service;

import java.util.List;

import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;

public interface IRepaymentReportTaskService {

    /**
     * 通过保理商或者融资商id获取它所有放款信息
     * @param masterId
     * @return
     */
    public List<RepaymentLoanInfoBO> getLoanInfoIdsByFactorIdOrFinanceId(String masterId)throws Exception;
    
    /**
     * 根据放款信息Id和还款日期获取相应的还款计划
     * @param loanInfoId
     * @param repaymentDate
     * @return
     */
    public List<RepaymentPlan> getRepaymentPlanByLoanInfoIdAndRepaymentDate(String loanInfoId,String repaymentDate)throws Exception;
    
  
    /**
     * 根据放款信息Id获取固定还款日为当年的所有的还款计划
     * @param loanInfoId
     * @param currentYear
     * @return
     */
    public List<RepaymentPlan> getRepaymentPlanByLoanInfoIdAndCurrentYear(String loanInfoId,String currentYear)throws Exception;
    
    /**
     * 根据放款信息Id和当前日期获取
     * 固定还款日为今日或之前
     * 且未结清
     * 的费用列表
     * @param loanInfoId
     * @param currentDate
     * @return
     */
    public List<RepaymentExpense> getRepaymentExpenseBeforeOrEqualsCurrentDateAndUnsettle(String loanInfoId,String currentDate)throws Exception;
    
    /**
     * 根据放款信息Id和当前年份获取
     * 固定还款日为当年或之前
     * 且未结清
     * 的费用列表
     * @param loanInfoId
     * @param currentYear
     * @return
     * @throws Exception
     */
    public List<RepaymentExpense> getRepaymentExpenseBeforeOrEqualsCurrentYearAndUnsettle(String loanInfoId,String currentYear)throws Exception;
    
    /**
     * 根据放款信息获取所有的还款计划
     * @param loanInfoId
     * @return
     * @throws Exception
     */
    public List<RepaymentPlan> getAllRepaymentPlansByLoanInfoId(String loanInfoId)throws Exception;
    
	/**
	 * 按id查询放款信息规则的详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public LoanInfoConfig getLoanInfoConfigByLoanInfoId(String loanInfoId) throws Exception;
}

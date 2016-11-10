package com.cana.repayment.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;

public interface IRepaymentReportTaskMapper {

    /**
     * 通过保理商或者融资商id获取它所有放款信息id
     * @param masterId
     * @return
     */
    @Select("SELECT * FROM repayment_loan_info where factor_id = #{masterId} or finance_id = #{masterId} or core_company_id = #{masterId}")
    public List<RepaymentLoanInfo> getLoanInfoIdsByFactorIdOrFinanceId(@Param("masterId") String masterId);
    
    /**
     * 根据放款信息Id和还款日期获取相应的还款计划
     * @param loanInfoId
     * @param repaymentDate
     * @return
     */
    @Select("SELECT * FROM repayment_plan where loan_info_id = #{loanInfoId} and repayment_date = #{repaymentDate}")
    public List<RepaymentPlan> getRepaymentPlanByLoanInfoIdAndRepaymentDate(@Param("loanInfoId")String loanInfoId,@Param("repaymentDate") String repaymentDate);
    
    /**
     * 根据loanInfoId对还款计划进行加锁查询
     * @param loanInfoId
     * @return
     */
    @Select("SELECT * FROM vbam.repayment_plan where loan_info_id = #{loanInfoId} for update")
    public List<RepaymentPlan> getReaymentPlansByLoanInfoId(@Param("loanInfoId")String loanInfoId);
}

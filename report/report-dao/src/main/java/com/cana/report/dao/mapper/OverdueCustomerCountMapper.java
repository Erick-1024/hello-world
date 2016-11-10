package com.cana.report.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OverdueCustomerCountMapper {

    /**
     * 新增逾期的客户数
     * @return
     */
    @Select("select count(*) from repayment_plan where loan_info_id != #{loanInfoId} and finance_id = #{financeId} and factor_id = #{factorId} and "
    		+ "(overdue_principal > 0 or overdue_interest > 0 or overdue_service_charge > 0 or paid_overdue_principal > 0 or paid_overdue_interest > 0 "
    		+ "or paid_overdue_service_charge > 0)")
    public int getAllFactorAndFinanceUsers(@Param("financeId")String financeId, @Param("loanInfoId")String loanInfoId, @Param("factorId")String factorId);
}

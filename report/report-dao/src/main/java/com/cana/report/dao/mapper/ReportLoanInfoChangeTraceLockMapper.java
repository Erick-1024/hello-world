package com.cana.report.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.report.dao.po.ReportLoanInfoChangeTrace;

public interface ReportLoanInfoChangeTraceLockMapper
{
	/**
	 * 通过 loan_info_id 对 放款信息追踪记录 进行加锁
	 * @param loanInfoId
	 * @return
	 */
	@Select("select * from report_loan_info_change_trace where loan_info_id=#{loanInfoId} for update")
	public ReportLoanInfoChangeTrace lockReportLoanInfoChangeTraceById(@Param("loanInfoId")String loanInfoId);
}

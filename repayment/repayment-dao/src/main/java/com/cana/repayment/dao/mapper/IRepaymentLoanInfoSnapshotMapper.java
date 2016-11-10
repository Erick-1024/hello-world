package com.cana.repayment.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;

public interface IRepaymentLoanInfoSnapshotMapper
{
	/**
	 * 根据 loanInfoId 和 currentVeriosn 查询一条 放款信息快照
	 * @param loanInfoId
	 * @param currentVersion
	 * @return
	 */
	@Select("select * from repayment_loan_info_snapshot where loan_info_id=#{loanInfoId} and current_version=#{currentVersion}")
	public RepaymentLoanInfoSnapshot getByLoanInfoIdAndCurrentVersion(@Param("loanInfoId")String loanInfoId,@Param("currentVersion")String currentVersion);
}

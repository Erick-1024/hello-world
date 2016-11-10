package com.cana.repayment.service.transaction;
import java.util.List;

import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisDTO;

public interface ILoanInfoTransactionService
{
	/**
	 * 将redis中通过的放款信息存入数据库
	 * @param loanInfoRedisDTOs
	 * @param factorCompany
	 * @param businessMode
	 * @param inputMethod
	 * @throws Exception
	 */
	public void saveLoanInfoListToDB(List<RepaymentLoanInfo> loanInfoList) throws Exception;
	
	/**
	 * 跟新数据库中的某条放款信息的还款帐号
	 * @param loanInfoRedisDTO
	 * @throws Exception
	 */
	public void updateAccountNoToDB(LoanInfoRedisDTO loanInfoRedisDTO)throws Exception;
}

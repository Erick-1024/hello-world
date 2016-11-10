package com.cana.repayment.service;

import java.util.List;

import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.vbam.common.repayment.dto.LoanInfoRedisIntegration;
public interface ILoanInfoService {
	
	/**
	 * 按查询条件搜索放款信息
	 * @param repaymentLoanInfoExample
	 * @return
	 */
	public List<RepaymentLoanInfo> queryLoanInfoListFromDB(RepaymentLoanInfoExample repaymentLoanInfoExample);
	
	/**
	 * 按查询条件搜索放款信息的总数
	 * @param repaymentLoanInfoExample
	 * @return
	 */
	public int queryLoanInfoCountFromDB(RepaymentLoanInfoExample repaymentLoanInfoExample);
	
	/**
	 * 按id查询放款信息的详情
	 * @param id
	 * @throws Exception
	 */
	public RepaymentLoanInfo queryLoanInfodetailFromDB(String id) throws Exception;
	
	/**
	 * 按id查询放款信息规则的详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public LoanInfoConfig queryLoanInfoConfigFromDB(String id) throws Exception;
	
	/**
	 * 查询放款信息编号是否存在
	 * @param repaymentLoanInfoExample
	 * @return
	 */
	public boolean isLoanNoExist(RepaymentLoanInfoExample repaymentLoanInfoExample);
	
	/**
	 * 放款信息转化
	 * @param loanInfoRedisIntegration
	 * @param factorId
	 * @throws Exception
	 */
	public void convertLoanInfoList(LoanInfoRedisIntegration loanInfoRedisIntegration, String factorId) throws Exception;
	
	/**
	 * 
	 * @param loanInfoQueryDTO
	 * @throws Exception
	 */
	public List<RepaymentLoanInfo> queryLoanInfoByCondition(RepaymentLoanInfoExample repaymentLoanInfoExample) throws Exception;
}

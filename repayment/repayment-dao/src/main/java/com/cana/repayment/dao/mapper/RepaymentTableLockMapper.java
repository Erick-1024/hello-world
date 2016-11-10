package com.cana.repayment.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cana.repayment.dao.po.RepaymentLoanInfo;

/**	
 * 数据库表加锁接口select * for update 加锁的时候注明参数和意图	
 *	
 */	
public interface RepaymentTableLockMapper	
{	
      /**	
	    * 通过id给repayment_loan_info表加锁	
	    * @param id	
	    * @return	
	    */	
      public RepaymentLoanInfo lockRepaymentLoanInfoById(String id);	
      
      /**
  	 * 通过id对放款信息加锁
  	 * 
  	 * @param loanInfoId
  	 * @return RepaymentLoanInfo
  	 */
  	@Select("select * from repayment_loan_info where id=#{id} for update")
  	public RepaymentLoanInfo lockLoanInfoById(@Param("id")String id);
  	
  	/**
  	 * 根据保理商id和放款编号锁定放款信息
  	 * @param loanNo
  	 * @param factorId
  	 * @return
  	 */
  	@Select("select * from repayment_loan_info where loan_no=#{loanNo} and factor_id=#{factorId} for update")
  	public RepaymentLoanInfo lockLoanInfoByLoanNoAndFactorId(@Param("loanNo")String loanNo, @Param("factorId")String factorId);
} 

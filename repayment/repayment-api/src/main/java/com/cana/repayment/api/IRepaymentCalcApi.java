package com.cana.repayment.api;

import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.PrepareRepaymentMergeResult;
import com.cana.vbam.common.repayment.dto.PrepareRepaymentResult;
import com.cana.vbam.common.repayment.dto.RepaymentRequest;

/**
 * 还款计算相关
 * 
 * @author XuMeng
 *
 */
public interface IRepaymentCalcApi {

	/**
	 * 预还款接口。
	 * 
	 * 根据还款金额，计算此次还款金额可以还掉的本金、利息等情况。
	 * 
	 * 返回数据中，remainingAmount表示剩余金额
	 * 
	 * @param loanInfoId
	 *            放款ID
	 * @param repaymentAmount
	 *            预还款金额，必须大于0
	 * @return 预还款金额分配情况，参数合法且调用成功的情况下
	 */
	public PrepareRepaymentResult prepareRepayment(String loanInfoId, long repaymentAmount) throws Exception;
	
	/**
	 * 预还款接口。(合并)
	 */
	public PrepareRepaymentMergeResult prepareRepaymentMerge(String loanInfoId, long repaymentAmount) throws Exception;
	
	/**
	 * 计算一笔放款的最低还款额。
	 */
	public String calcMinimumRepaymentAmount(String loanInfoId) throws Exception;
	
	/**
	 * 计算一笔放款的最大还款额。
	 */
	public String calcMaximumRepaymentAmount(String loanInfoId) throws Exception;
	
	/**
	 * 自动生成的还款计划下的主动还款
	 * @param accountNo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public LoanInfoRepaymentResult repayment(String accountNo,String loanInfoId,String amount) throws Exception ;
}

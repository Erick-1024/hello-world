package com.cana.repayment.service;

import java.util.Map;

import com.cana.repayment.dao.po.manual.ActiveRepaymentExpense;
import com.cana.repayment.service.bo.RepaymentPlanBO;

/**
 * 主动还款service
 * @author xiong.li
 *
 */
public interface IActiveRepaymentService {

	
	/**
	 * 判断一条还款计划是否可以还款，即上一条还款计划是否已结清
	 * @param repaymentPlan
	 * @return
	 * @throws Exception
	 */
	public boolean isRepaymentPlanReadyToRepay(RepaymentPlanBO planBO)throws Exception;
	
	/**
	 * 判断当前费用是否可以还款
	 * @param activeRepaymentExpense
	 * @return
	 * @throws Exception
	 */
	public boolean isRepaymentExpenseReadyToPay(ActiveRepaymentExpense activeRepaymentExpense)throws Exception;
	
	/**
	 * 获取转账交易状态
	 * @param accountNo
	 * @param loanInfoId
	 * @param amount
	 * @return
	 */
	public Map<String,String> getAccountTradeStatusAndBusinessSeq(String accountNo, String loanInfoId, String transferInAccountNo, String amount);
}

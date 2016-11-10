package com.cana.repayment.service.transaction;

import java.util.List;
import java.util.Map;

import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentRule;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskBO;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.repayment.dto.CreateLoanRequest;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.PreCalcInterestRequest;
import com.cana.vbam.common.repayment.dto.PreCalcInterestResponse;
import com.cana.vbam.common.repayment.dto.RepaymentRequest;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundInfo;
import com.cana.vbam.common.repayment.dto.TravelzenUserRefundResult;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;

public interface IRepaymentTransactionService {

	/**
	 * 保存还款计划和费用
	 * @param repaymentPlanList
	 * @param repaymentExpenseList
	 * @throws Exception
	 */
	public void saveRepymentPlanAndExpenseInfo(List<RepaymentPlan> repaymentPlanList,List<RepaymentExpense> repaymentExpenseList, String loanInfoId,String version) throws Exception;
	
	/**
	 * 为指定的放款信息生成跑批任务
	 * @param loanInfoId
	 * @throws Exception
	 */
	public void generateDailyBatchTask(String loanInfoId, String curDate) throws Exception;
	
	/**
	 * 新增还款规则
	 * @param repaymentRule
	 */
	public void addRepaymentRule(RepaymentRule repaymentRule);
	
	/**
	 * 修改还款规则
	 * @param repaymentRule
	 */
	public void modifyRepaymentRule(RepaymentRule repaymentRule);
	
	/**
	 * 删除还款规则
	 * @param ruleId
	 */
	public void deleteRepaymentRule(String ruleId);
	

	/**
	 * 生成新的展期费用
	 * @param taskBO
	 * @param taskItemBO
	 * @throws Exception
	 */
	public void generateExtensionCharge(RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO) throws Exception;

	/**
	 * 生成新的逾期
	 * @param taskBO
	 * @param taskItemBO
	 * @throws Exception
	 */
	public void generateOverdue(RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO) throws Exception;

	/**
	 * 生成罚息
	 * @param taskBO
	 * @param taskItemBO
	 * @throws Exception
	 */
	public void generatePenalty(RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO) throws Exception;

	/**
	 * 账扣成功后更新数据
	 * @param actualDeductAmount
	 * @param taskBO
	 * @param taskItemBO
	 * @param curDate10 发生账扣的日期
	 */
	public void updateOnDeductSuccess(long actualDeductAmount, RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItemBO taskItemBO, String curDate10, final Map<Object, Object> extra) throws Exception;
	
	/**
	 * 获取银行账号列表中不是回款的银行账号列表
	 * @param accountNos
	 * @return
	 */
	public List<String> getNotTransferInAccountNos(List<String> accountNos);
	
	/**
	 * 真旅金融产品增加新的放款, 在真旅用户支付的时候调用此方法
	 * @param loan
	 * @return 新增的或者更新后的汇总放款记录
	 */
	public RepaymentLoanInfoBO addTravelzenFinanceLoan(CreateLoanRequest request) throws Exception;
	
	/**
	 * 真旅用户退款接口
	 * @return
	 * @throws Exception
	 */
	public TravelzenUserRefundResult refundByTravelzenFinancier(TravelzenUserRefundInfo refundInfo) throws Exception;
	
	/**
	 * 预算利息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public PreCalcInterestResponse preCalcInterest(PreCalcInterestRequest request) throws Exception;
	
	/*
	 * 创建放款，并生成还款计划
	 */
	public RepaymentLoanInfoBO createLoan(CreateLoanRequest request) throws Exception;
	
	/**
	 * 用户还款
	 * @return
	 * @throws Exception
	 */
	public LoanInfoRepaymentResult repayment(RepaymentRequest request,RepaymentMethod repaymentMethod) throws Exception;
}

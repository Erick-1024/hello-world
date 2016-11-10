package com.cana.repayment.service;

import java.util.List;

import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExtensionProductDetail;
import com.cana.repayment.dao.po.RepaymentPenaltyProductDetail;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.repayment.product.TravelzenFinanceProduct;
import com.cana.vbam.common.repayment.product.YundaexFinanceProduct;

public interface IRepositoryService {

	/**
	 * 根据放款信息id获取还款计划，并按照期数排序
	 * @param loanInfoId
	 * @return
	 */
	public List<RepaymentPlan> getPlansByLoanInfoId(String loanInfoId);
	
	/**
	 * 根据放款信息id和融资客户id获取放款信息
	 * @param loanInfoId
	 * @param financeId
	 * @return
	 */
	public RepaymentLoanInfoBO getLoanInfoBOByLoanInfoIdAndFinanceId(String loanInfoId, String financeId);

	/**
	 * 根据放款信息id获取固定费用列表，并按照还款日期排序
	 * @param loanInfoId
	 * @return
	 */
	public List<RepaymentExpense> getExpensesByLoanInfoId(String loanInfoId);
	
	/**
	 * 根据放款信息id获取还款配置
	 * @param loanInfoId
	 * @return
	 */
	public LoanInfoConfig getRepaymentConfigByLoanInfoId(String loanInfoId);

	/**
	 * 根据放款信息和日期获取日任务
	 * @param loanInfoId
	 * @param date
	 * @return
	 */
	public RepaymentDailyBatchTask getRepaymentDailyBatchTask(String loanInfoId, String date);

	/**
	 * 根据日任务id获取任务条目集合, 根据序列号排序
	 * @param taskId
	 * @return
	 */
	public List<RepaymentDailyBatchTaskItem> getTaskItemsByTaskId(String taskId);

	/**
	 * 根据还款记录id获取还款明细
	 * @param id
	 * @return
	 */
	public List<RepaymentSingleDistributeDetail> getRepaymentItemsByRepaymentId(String id);

	/**
	 * 获取还款计划在指定日期产生的展期费用
	 * @param id
	 * @param date
	 * @return
	 */
	public RepaymentExtensionProductDetail getExtensionChargeGenerateDetailByPlanIdAndDate(String id, String date);

	/**
	 * 获取还款计划在指定日期产生的罚息
	 * @param planId
	 * @param date
	 * @return
	 */
	public RepaymentPenaltyProductDetail getPenaltyDetailByPlanIdAndDate(String planId, String date);
	
	/**
	 * 获取真旅金融产品
	 * @deprecated
	 */
	public TravelzenFinanceProduct getTravelzenFinanceProduct();
	
	/**
	 * 获取韵达项目产品
	 * @return
	 */
	public YundaexFinanceProduct getYundaexFinanceProduct();
	
	/**
	 * 是否存在该产品
	 * @param productId 产品ID
	 * @return
	 */
	public boolean isExistProduct(String productId);

	/**
	 * 获取该放款所有的还款记录，按照还款日期升序排列
	 * @param loanInfoId
	 * @return
	 */
	public List<RepaymentSingleCollect> getRepaymentSummarysByLoanInfoId(String loanInfoId);
	
	/**
	 * 批量加载还款计划，返回的list中的顺序跟传入list中的顺序对应。
	 * @param planIds
	 * @return
	 */
	public List<RepaymentPlanBO> batchLoadRepaymentPlanBOs(List<String> planIds) throws Exception;
	
	/**
	 * 批量加载放款信息，返回的list中的顺序跟传入list中的顺序对应。
	 * @param loanInfoIds
	 * @return
	 */
	public List<RepaymentLoanInfoBO> batchLoadRepaymentLoanInfoBOsByLoanInfoIds(List<String> loanInfoIds) throws Exception;
	
	/**
	 * 批量加载放款信息，返回的list中的顺序跟传入list中的顺序对应。
	 * @param planBOs
	 * @return
	 */
	public List<RepaymentLoanInfoBO> batchLoadRepaymentLoanInfoBOsByRepaymentPlanBOs(List<RepaymentPlanBO> planBOs) throws Exception;
	
}

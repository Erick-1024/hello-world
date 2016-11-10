package com.cana.repayment.service.handler;

import java.util.List;

import com.cana.repayment.service.bo.RepaymentDailyBatchTaskBO;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.context.RepaymentCalcContext;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.PreCalcInterestRequest;
import com.cana.vbam.common.repayment.dto.PreCalcInterestResponse;
import com.cana.vbam.common.repayment.dto.RepaymentAmount;
import com.cana.vbam.common.repayment.enums.DateUnit;

/**
 * 还款计算逻辑接口
 * @author renshui
 *
 */
public interface IRepaymentCalc {

	/**
	 * 返回本次需要账扣的金额
	 * @param loanInfoBO 放款信息
	 * @param curDate10 账扣日期，格式: yyyy-MM-dd
	 * @return
	 */
	public long totalDeduct(RepaymentLoanInfoBO loanInfoBO, String curDate10);
	
	/**
	 * 账扣成功之后，修改放款与还款计划
	 * @param actualDeductAmount 实际账扣金额
	 * @param loanInfoBO 
	 * @param taskBO
	 * @param taskItemBO
	 */
	public void deduct(long actualDeductAmount, final RepaymentLoanInfoBO loanInfoBO, RepaymentDailyBatchTaskBO taskBO, 
			           RepaymentDailyBatchTaskItemBO taskItemBO, String deductDate10) throws Exception;
	
	/**
	 * 首次生成还款计划。 注意：该方法一般会运行在事务中。
	 * @param loanInfoBO。 新的放款信息
	 * @param context 附加信息
	 */
	public void generateRepaymentPlan(RepaymentLoanInfoBO loanInfoBO, RepaymentCalcContext context) throws Exception;

	/**
	 * 还款。
	 * 
	 * 还款顺序：
	 * 1.逾期的还款计划
	 * 2.展期的还款计划
	 * 3.处于固定还款日的还款计划
	 * 4.提前当期的还款计划
	 * 5.提前还未来期的还款计划
	 * 
	 * 处理完以上过程，还需要重新生成当期和未来期的还款计划。
	 * 
	 * 此接口默认进行放款、还款计划、还款明细的持久化操作，如过context中的预还款字段为true，则不进行持久化操作
	 * 
	 * @param loanInfoBO
	 * @param repaymentAmount 还款的金额
	 * @throws InvalidRepaymentPlanException, RepaymentAmountNotEnoughException
	 * @return 返回结果中的repaymentAmount为实际的还款金额，多还的金额由调用方决定如何处理
	 */
	public LoanInfoRepaymentResult repayment(RepaymentLoanInfoBO loanInfoBO,
			long repaymentAmount, RepaymentCalcContext context) throws Exception;

	/**
	 * 预还款接口，与还款接口类似，区别是此接口不做任何持久化操作和返回值只有时间还款金额
	 * @param loanInfoBO
	 * @param repaymentAmount 还款的金额
	 * @throws InvalidRepaymentPlanException, RepaymentAmountNotEnoughException
	 * @return 返回值为实际还的金额
	 */
	public long prepareRepayment(RepaymentLoanInfoBO loanInfoBO,
			long repaymentAmount, String repaymentDate) throws Exception;

	/**
	 * 计算一笔放款的最低还款额。
	 * 
	 * 此接口对于传入的放款BO无副作用。
	 * 
	 * @throws InvalidRepaymentPlanException
	 */
	public long calcMinimumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10);

	/**
	 * 计算一笔放款的最大还款额。
	 * 
	 * 此接口对于传入的放款BO无副作用。
	 * 
	 * @throws InvalidRepaymentPlanException
	 */
	public long calcMaximumRepaymentAmount(RepaymentLoanInfoBO loanInfoBO, String curDate10);

	/**
	 * 生成日跑批任务
	 * @param loanInfoBO
	 * @param curDate
	 * @return
	 * @throws Exception
	 */
	public List<RepaymentDailyBatchTaskItemBO> generateDailyBatchTask(RepaymentLoanInfoBO loanInfoBO, String curDate) throws Exception;
	
	/**
	 * 预算利息
	 * 
	 * 在产生放款之前显示给客户的到期应还利息计算，目前只有真旅项目实现了该接口
	 */
	public PreCalcInterestResponse preCalcInterest(PreCalcInterestRequest request) throws Exception;
	
	/**
	 * 计算放款的到期日, 返回的格式是：yyyy-MM-dd
	 * 
	 * @param loanDate 放款日
	 * @param loanPeriodUnit 放款期限单位
	 * @param loanPeriod 放款期限
	 */
	public String calcLoanDueDate(String loanDate, DateUnit loanPeriodUnit, int loanPeriod) throws Exception;
	
	/**
	 * 计算截止到现在，正常状态下的还款计划应还利息和服务费
	 * @param loanInfoBO
	 * @param planBO
	 * @return
	 */
	public RepaymentAmount calcAccountInterestAndAccountServiceChargeUntilNow(RepaymentLoanInfoBO loanInfoBO, RepaymentPlanBO planBO) throws Exception;
	
}

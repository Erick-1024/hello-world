package com.cana.repayment.scheduler.batch;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.scheduler.bank.BankFactory;
import com.cana.repayment.scheduler.bank.IBank;
import com.cana.repayment.service.handler.IRepaymentCalc;
import com.cana.repayment.service.handler.RepaymentCalcFactory;
import com.cana.vbam.common.repayment.rule.enums.DeductionRule;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.exception.CanRetryException;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 所有账扣
 * @author renshui
 *
 */
public class DeductTaskHandler extends AbstractBatchTaskHandler{

	public DeductTaskHandler(RepaymentDailyBatchTask task, RepaymentDailyBatchTaskItem taskItem) {
		super(task, taskItem);
	}

	@Override
	public void doExecute() throws Exception{
		long totalDeduct = 0;
		long actualDeductAmount = 0;
		long balance = 0;
		
		IRepaymentCalc repaymentCalc = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO);
		
		boolean defaultDeduct = Boolean.valueOf(taskItemBO.extraData("defaultDeduct"));
		
		String curDate10 = taskBO.getDate();
		
		if(defaultDeduct)
			curDate10 = DateTimeUtil.addDay10(taskBO.getDate(), -1);
		
		IBank bank = BankFactory.getBank(loanInfoBO);

		try{
			totalDeduct = repaymentCalc.totalDeduct(loanInfoBO, curDate10);
			if(totalDeduct == 0){
				taskBO.advanceToNextTask();
				return;
			}

			balance = bank.getAccountBalance(loanInfoBO.getAccountNo());
			if(balance == 0){
				taskBO.advanceToNextTask();
				return;
			}
			
			if(balance < totalDeduct && DeductionRule.valueOf(loanInfoBO.lazyLoadRepaymentConfig().getDeductionRule()) != DeductionRule.PART){ // 账户余额不足，不允许部分扣款
				logger.info("该笔放款不允许部分扣款，此次账扣任务取消");
				taskBO.advanceToNextTask();
				return;
			}
			
		}catch(Exception e){
			throw new CanRetryException(e.getMessage(), e);
		}
		
		actualDeductAmount = Math.min(totalDeduct, balance);
		
		if(bank.deductAmount(actualDeductAmount, loanInfoBO, taskItemBO) == 0L){ // 如果未扣到钱，终止处理
			taskBO.advanceToNextTask();
			return;
		}
		
		Map<Object, Object> extra = new HashMap<>();
		transactionService.updateOnDeductSuccess(actualDeductAmount, taskBO, taskItemBO, curDate10, extra);
		
	}
	
	@Override
	protected void checkBeforeExecute() throws Exception{
		super.checkBeforeExecute();

		if(!Constants.VJ_PRODUCT_ID.equals(loanInfoBO.getBusinessProductId()) && StringUtils.isBlank(loanInfoBO.lazyLoadRepaymentConfig().getFactorTransferInAccountNo()))
			throw CanRetryException.instance("回款账号为空");

		String itemId = taskCustomMapper.getEarliestExecutableDeductItemId(taskBO.getCustomerId(),
				taskBO.getDate(), commonService.getCurrentTime());

		String logContent = "当前任务的放款Id为：" + taskBO.getLoanInfoId() + "，taskId为：" + taskBO.getId()
				+ "，itemId为：" + taskItemBO.getId() + "，获取该客户最新可执行账扣任务itemId为:" + itemId;
		if (!StringUtils.equals(taskItemBO.getId(), itemId)) {
			retryInterval = 0;
			throw CanRetryException.instance(logContent + "，当前任务稍后执行", true);
		}
		else
			logger.info(logContent + "，即当前正在运行任务");
	}

	@Override
	protected int retryInterval() {
		return retryInterval != null ? retryInterval : super.retryInterval();
	}
	private Integer retryInterval = null;
}

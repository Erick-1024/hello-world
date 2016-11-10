package com.cana.repayment.scheduler.batch;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.cana.account.api.IAccountApi;
import com.cana.bankgate.api.BankgateApi;
import com.cana.credit.api.ICreditApi;
import com.cana.repayment.dao.mapper.IRepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskItemMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.cana.repayment.service.IMessageService;
import com.cana.repayment.service.IRepositoryService;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskBO;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.CanRetryException;
import com.travelzen.framework.core.exception.RepeatedExecuteException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public abstract class AbstractBatchTaskHandler implements IBatchTaskHandler{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected RepaymentDailyBatchTaskBO taskBO;
	
	protected RepaymentDailyBatchTaskItemBO taskItemBO;
	
	protected RepaymentLoanInfoBO loanInfoBO;
	
	protected IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);
	
	protected RepaymentDailyBatchTaskMapper taskMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentDailyBatchTaskMapper.class);
	
	protected IRepaymentDailyBatchTaskMapper taskCustomMapper = SpringApplicationContext.getApplicationContext().getBean(IRepaymentDailyBatchTaskMapper.class);;
	
	protected RepaymentDailyBatchTaskItemMapper taskItemMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentDailyBatchTaskItemMapper.class);
	
	protected IRepositoryService repositoryService = SpringApplicationContext.getApplicationContext().getBean(IRepositoryService.class);
	
	protected IAccountApi accountApi = SpringApplicationContext.getApplicationContext().getBean(IAccountApi.class);
	
	protected BankgateApi bankgateApi = SpringApplicationContext.getApplicationContext().getBean(BankgateApi.class);
	
	protected IRepaymentTransactionService transactionService = SpringApplicationContext.getApplicationContext().getBean(IRepaymentTransactionService.class);
	
	protected SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	protected IMessageService messageService = SpringApplicationContext.getApplicationContext().getBean(IMessageService.class);
	
	protected ICreditApi creditApi = SpringApplicationContext.getApplicationContext().getBean(ICreditApi.class);
	
	protected RetryTaskMapper retryTaskMapper = SpringApplicationContext.getApplicationContext().getBean(RetryTaskMapper.class);
	
	protected RabbitTemplate repaymentRabbitTemplate = SpringApplicationContext.getApplicationContext().getBean("repaymentRabbitTemplate" ,RabbitTemplate.class);

	public AbstractBatchTaskHandler(RepaymentDailyBatchTask task, RepaymentDailyBatchTaskItem taskItem) {
		this.taskBO = new RepaymentDailyBatchTaskBO(task);
		this.taskItemBO = new RepaymentDailyBatchTaskItemBO(taskItem);
		loanInfoBO = new RepaymentLoanInfoBO(task.getLoanInfoId());
	}
	
	@Override
	public void execute() {
		Transaction t = Cat.newTransaction("scheduler", "batch_task_" + taskItemBO.getTaskType());	
	    t.addData("traceId", MDC.get(Constants.TRACE_ID));
		try{
			checkBeforeExecute();
			doExecute();
			t.setStatus(Transaction.SUCCESS);
			Cat.logMetricForCount("batch_task_" + taskItemBO.getTaskType() + "_success");
		}catch(RepeatedExecuteException ree){
			logger.error("", ree);
			Cat.getProducer().logError(ree);
			t.setStatus(ree);
			Cat.logMetricForCount("batch_task_" + taskItemBO.getTaskType() + "_error");
			return;
		}catch(CanRetryException cre){
			logger.error("", cre);
			Cat.getProducer().logError(cre);
			t.setStatus(cre);
			if (cre.isWarning())
				Cat.logMetricForCount("batch_task_" + taskItemBO.getTaskType() + "_error");
			setRetry(cre);
			return;
		}catch(Exception e){
			logger.error("", e);
			Cat.getProducer().logError(e);
			t.setStatus(e);
			Cat.logMetricForCount("batch_task_" + taskItemBO.getTaskType() + "_error");
			fail(e);
			return;
		}finally{
			t.complete();
		}
		
	}
	
	public abstract void doExecute() throws Exception;

	/**
	 * 设置程序失败, 不可重新执行
	 * @param e
	 */
	private void fail(Exception e) {
		taskBO.setCanRetry(false);
		taskBO.setFailTaskItemId(taskBO.getNextTaskItemId());
		taskBO.setFailMessage(getExceptionMessage(e));
		taskBO.setUpateTime(new Date());
		taskMapper.updateByPrimaryKey(taskBO);
		
		taskItemBO.setDetail(getExceptionMessage(e));
		taskItemBO.setUpateTime(new Date());
		taskItemMapper.updateByPrimaryKey(taskItemBO);
	}

	/**
	 * 设置可以重试执行
	 * @param cre
	 */
	protected void setRetry(CanRetryException cre) {
		boolean canRetry = true;

		DateTime curTime = commonService.getCurrentDateTime();
		DateTime nextRetryTime = curTime.plusMinutes(retryInterval());
		if(curTime.getDayOfMonth() != nextRetryTime.getDayOfMonth()) // 下次重试时间跨天了，不再重试
			canRetry = false;

		taskBO.setCanRetry(canRetry);
		taskBO.setFailTaskItemId(taskBO.getNextTaskItemId());
		taskBO.setNextTaskItemExecuteTime(DateTimeFormat.forPattern("HH:mm").print(nextRetryTime));
		taskBO.setFailMessage(getExceptionMessage(cre));
		taskBO.setUpateTime(new Date());
		taskMapper.updateByPrimaryKey(taskBO);
		
		taskItemBO.setDetail(getExceptionMessage(cre));
		taskItemBO.setUpateTime(new Date());
		taskItemMapper.updateByPrimaryKey(taskItemBO);
	}
	
	/**
	 * 返回异常中的message， 根据数据库字段大小做切割
	 * @return
	 */
	protected String getExceptionMessage(Exception e){
		final int MAX_SIZE = 256;
		String message = StringUtils.trimToEmpty(e.getMessage());
		if(message.length() > MAX_SIZE)
			message = message.substring(0, MAX_SIZE);
		return message;
	}
	
	/**
	 * 返回重试间隔，以分钟为单位
	 * @return
	 */
	protected int retryInterval(){
		return 5;
	}

	/**
	 * 执行前的验证
	 * @throws Exception
	 */
	protected void checkBeforeExecute() throws Exception{
		checkTaskNotModified();
		checkPreTaskCompleted();
		checkTaskNotExecuteAcrossDay();
	}

	/**
	 * 校验任务没有跨天执行
	 */
	protected void checkTaskNotExecuteAcrossDay() throws Exception{
		if(!taskBO.getDate().equals(commonService.getCurrentDate()))
			throw new Exception("任务跨天执行了");
	}

	/**
	 * 检查前置任务是否已完成
	 */
	private void checkPreTaskCompleted() {
		if(taskItemBO.getSequence() == 0 && !previousDateTaskCompleted(taskBO))
			throw CanRetryException.instance("前一日的任务还未完成");
	}

	/**
	 * 前一日的跑批是否已经完成
	 * @param task
	 * @return
	 */
	private boolean previousDateTaskCompleted(RepaymentDailyBatchTask task) {
		RepaymentDailyBatchTask previousDateTask = repositoryService.getRepaymentDailyBatchTask(task.getLoanInfoId(), DateTimeUtil.date10(DateTimeUtil.addDay(DateTimeUtil.parseDate10(task.getDate()), -1))); 
		return previousDateTask == null || StringUtils.isBlank(previousDateTask.getNextTaskItemId());
	}

	/**
	 * 验证任务提交到执行这段时间没有被更改过
	 * @throws Exception
	 */
	protected void checkTaskNotModified() throws Exception{
		RepaymentDailyBatchTaskBO newTaskBO = new RepaymentDailyBatchTaskBO(taskBO.getId());
		if(!taskBO.equals(newTaskBO))
			throw RepeatedExecuteException.instance("任务信息被修改了");
	}

	/**
	 * 获取还款账号的余额
	 * @return
	 */
	protected long getAccountBalance() throws Exception{
		BankAccountBalanceQueryDTO requestDTO = new BankAccountBalanceQueryDTO();
		requestDTO.setAccountNo(loanInfoBO.getAccountNo());
		logger.info("查询余额请求：" + new Gson().toJson(requestDTO));
		BankAccountBalanceResultDTO resultDTO = bankgateApi.queryAccountBalance(requestDTO);
		logger.info("查询余额响应：" + new Gson().toJson(resultDTO));
		if(resultDTO == null || CollectionUtils.isEmpty(resultDTO.getBankAccountBalanceDatas()))
			throw new Exception("查询账户余额失败");
		return resultDTO.getBankAccountBalanceDatas().get(0).getAvailableBalance();
	}
	
	/**
	 * 账扣
	 * @param amount
	 * @throws Exception
	 */
	protected void deductAmount(long amount) throws Exception{
		DeductFundRequestDTO requestDTO = new DeductFundRequestDTO();
		requestDTO.setAmount(amount);
		requestDTO.setBusinessSeq(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.BUSINESS_SEQ, 5));
		requestDTO.setTransferOutAccountNo(loanInfoBO.getAccountNo());
		requestDTO.setTransferInAccountNo(loanInfoBO.lazyLoadRepaymentConfig().getFactorTransferInAccountNo());
		taskItemBO.updateExtraData("businessSeq", requestDTO.getBusinessSeq());
		taskItemMapper.updateByPrimaryKey(taskItemBO);
		logger.info("账扣请求：" + new Gson().toJson(requestDTO));
		AccountTradeStatus tradeStatus = accountApi.deductFund(requestDTO);
		logger.info("账扣响应：" + tradeStatus);
		if(tradeStatus == AccountTradeStatus.TRADE_SUCCESS)
			return;
		if(tradeStatus == AccountTradeStatus.TRADE_FAIL)
			throw CanRetryException.instance("扣款失败");
		throw new Exception("扣款状态未明");
	}
}

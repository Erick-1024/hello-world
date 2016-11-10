package com.cana.report.scheduler.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import com.cana.report.dao.mapper.IReportLoanInfoChangeTaskMapper;
import com.cana.report.service.transaction.IReportTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.core.collection.LimitedQueue;

/**
 * 生成放款信息发生变更的处理任务
 * 
 * @author dev3
 *
 */
@Service
public class ProcessLoanInfoChangeTask {
	private static Logger logger = LoggerFactory.getLogger(ProcessLoanInfoChangeTask.class);

	@Resource
	private IVbamCommonService commonService;

	@Resource
	private IReportLoanInfoChangeTaskMapper loanInfoChangeBatchTaskMapper;

	@Resource
	private IReportTransactionService reportTransactionService;

	/**
	 * 定时任务
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 1)
	public void doTask() throws Exception {
		logger.info("融资报表生成定时任务 - 状态 - 报表生成定时任务启动");
		if (!commonService.isInitReportTaskDone()) {
			logger.info("融资报表生成定时任务 - 状态 - 每日初始化报表任务未完成，本次任务结束！");
			return;
		}
		List<String> changedLoanInfoIds = loanInfoChangeBatchTaskMapper.getAllChangedLoanInfoIds();
		try {
			logger.info("融资报表生成定时任务 - 执行 - 初始化追踪记录表");
			reportTransactionService.initLoanInfoChangeTrace(changedLoanInfoIds);
		} catch (Exception e) {
			logger.error("融资报表生成定时任务 - 状态 - 初始化追踪记录表失败，失败原因", e);
			throw e;
		}
		logger.info("融资报表生成定时任务 - 执行 - 拉取发生改变的放款信息，发生改变的放款信息数量：{}", changedLoanInfoIds.size());
		if (CollectionUtils.isEmpty(changedLoanInfoIds)) {
			logger.info("融资报表生成定时任务 - 状态 - 没有放款信息发生变更，本次任务结束！");
			return;
		}
		logger.info("融资报表生成定时任务 - 执行 - 开始执行报表生成任务");
		ExecutorService executorService = getThreadPool();
		for (String loanInfoId : changedLoanInfoIds) {
//			executorService.execute(createTask(loanInfoId));
			Transaction transaction = Cat.newTransaction("scheduler", "process-loan-info-change-task");
			try {
				MDC.put(Constants.SEQUENCE_REPORT_BATCH_TASK_ID, RandomStringUtils.randomAlphanumeric(10));
				transaction.addData("report_batch_task_id", MDC.get(Constants.SEQUENCE_REPORT_BATCH_TASK_ID));
				logger.info("融资报表生成定时任务 - 执行 - 开始处理放款信息[id：{}]", loanInfoId);
				reportTransactionService.processLoanInfoChangeTask(loanInfoId);// 执行所有处理事件
				logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]处理结束", loanInfoId);
				transaction.setStatus(Transaction.SUCCESS);
				Cat.logMetricForCount("process-loan-info-change-task_success");
			} catch (Exception e) {
				logger.error("融资报表生成定时任务 - 状态 - 放款信息[id：{}]处理异常，异常原因：{}", loanInfoId, e);
				Cat.getProducer().logError(e);
				transaction.setStatus(e);
				Cat.logMetricForCount("process-loan-info-change-task_error");
			} finally {
				MDC.clear();
				transaction.complete();
			}
		}
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.MINUTES);
	}

	/**
	 * 为单个发生变更的放款信息生成任务
	 * 
	 * @param loanInfoId
	 * @return
	 */
	private Runnable createTask(final String loanInfoId) {
		return new Runnable() {
			@Override
			public void run() {
				Transaction transaction = Cat.newTransaction("scheduler", "process-loan-info-change-task");
				try {
					MDC.put(Constants.SEQUENCE_REPORT_BATCH_TASK_ID, RandomStringUtils.randomAlphanumeric(10));
					transaction.addData("report_batch_task_id", MDC.get(Constants.SEQUENCE_REPORT_BATCH_TASK_ID));
					logger.info("融资报表生成定时任务 - 执行 - 开始处理放款信息[id：{}]", loanInfoId);
					reportTransactionService.processLoanInfoChangeTask(loanInfoId);// 执行所有处理事件
					logger.info("融资报表生成定时任务 - 状态 - 放款信息[id：{}]处理结束", loanInfoId);
					transaction.setStatus(Transaction.SUCCESS);
					Cat.logMetricForCount("process-loan-info-change-task_success");
				} catch (Exception e) {
					logger.error("融资报表生成定时任务 - 状态 - 放款信息[id：{}]处理异常，异常原因：{}", loanInfoId, e);
					Cat.getProducer().logError(e);
					transaction.setStatus(e);
					Cat.logMetricForCount("process-loan-info-change-task_error");
				} finally {
					MDC.clear();
					transaction.complete();
				}
			}
		};
	}

	/**
	 * 创建连接池
	 * 
	 * @return
	 */
	private ExecutorService getThreadPool() {
		int processorsOfCPU = Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2 * processorsOfCPU, 2 * processorsOfCPU, 5L,
				TimeUnit.MINUTES, new LimitedQueue<Runnable>(1),
				new CustomizableThreadFactory("process-loan-info-change-task-thread"));
		return threadPool;
	}
}

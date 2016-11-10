package com.cana.repayment.scheduler.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.IRepaymentDailyBatchTaskMapper;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.core.collection.LimitedQueue;

/**
 * 日跑批任务生成
 * 
 * @author renshui
 *
 */
@Service
public class GenerateDailyBatchTask {

	private static Logger logger = LoggerFactory.getLogger(GenerateDailyBatchTask.class);

	@Resource
	private IRepaymentDailyBatchTaskMapper dailyBatchTaskMapper;

	@Resource
	private IVbamCommonService commonService;

	@Resource
	private IRepaymentTransactionService repaymentTransactionService;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_SECOND * 10)
	public void doTask() throws Exception {
		String curDate = commonService.getCurrentDate();

		if (!commonService.isInitReportTaskDone()) {
			logger.info("初始化报表任务尚未完成，停止生成全部日跑批任务。");
			return;
		}

		List<String> loanInfoIds = dailyBatchTaskMapper.getAllUnGenerateDailyBatchTaskLoanInfoIds(curDate);
		if (CollectionUtils.isEmpty(loanInfoIds)) {
			logger.info("全部日跑批任务已经生成");
			// done(curDate);
			return;
		}
		ExecutorService executorService = getThreadPool();
		for (String loanInfoId : loanInfoIds)
			// if("zk5AFRpB5M".equals(loanInfoId))
			executorService.execute(createTask(loanInfoId, curDate));
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.MINUTES);
	}

	/**
	 * 为单个放款信息生成跑批任务
	 * 
	 * @param loanInfoId
	 * @return
	 */
	private Runnable createTask(final String loanInfoId, final String curDate) {
		return new Runnable() {

			@Override
			public void run() {
				Transaction t = Cat.newTransaction("scheduler", "generate_daily_batch_task");
				try {
					MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
					t.addData("traceId", MDC.get(Constants.TRACE_ID));
					logger.info("开始为放款信息[id={}]生成跑批任务开始", loanInfoId);
					repaymentTransactionService.generateDailyBatchTask(loanInfoId, curDate);
					logger.info("生成跑批任务成功");
					t.setStatus(Transaction.SUCCESS);
					Cat.logMetricForCount("generate_daily_batch_task_success");
				} catch (Exception e) {
					logger.error("生成跑批任务异常", e);
					Cat.getProducer().logError(e);
					t.setStatus(e);
					Cat.logMetricForCount("generate_daily_batch_task_error");
				} finally {
					MDC.clear();
					t.complete();
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
				new CustomizableThreadFactory("generate-daily-batch-task-thread"));
		return threadPool;
	}
}

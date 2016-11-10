package com.cana.repayment.scheduler.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.repayment.dao.mapper.IRepaymentDailyBatchTaskMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTask;
import com.cana.repayment.scheduler.batch.IBatchTaskHandler;
import com.cana.repayment.scheduler.batch.IBatchTaskHandlerFactory;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.collection.LimitedQueue;

/**
 * 跑批任务调度器
 * 
 * @author renshui
 * 
 */
@Service
public class BatchTaskScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IBatchTaskHandlerFactory batchTaskHandlerFactory;

	@Resource
	private IRepaymentDailyBatchTaskMapper batchTaskMapper;

	@Resource
	private IVbamCommonService commonService;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_SECOND * 10)
	public void doTask() throws Exception {
		logger.info("处理跑批任务开始");
//		if (!commonService.isInitReportTaskDone()) {
//			logger.info("初始化报表任务尚未完成，停止处理全部日跑批任务。");
//			return;
//		}

		try {
			List<RepaymentDailyBatchTask> tasks = batchTaskMapper.getAllPendingTasks(commonService.getCurrentDate(),
					commonService.getCurrentTime());
			if (CollectionUtils.isEmpty(tasks)) {
				logger.info("当前没有可以处理的跑批任务");
				return;
			}
			ExecutorService executorService = getThreadPool();
			for (final RepaymentDailyBatchTask task : tasks) {
				// if("zk5AFRpB5M".equals(task.getLoanInfoId()))
				executorService.submit(createProcessBatchTaskRunnable(task));
			}
			executorService.shutdown();
			executorService.awaitTermination(3, TimeUnit.MINUTES);
		} catch (Exception e) {
			logger.error("处理跑批任务出现异常", e);
		}
		logger.info("处理跑批任务结束");
	}

	/**
	 * 创建Runnable
	 * 
	 * @param task
	 * @return
	 */
	private Runnable createProcessBatchTaskRunnable(final RepaymentDailyBatchTask task) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
					RepaymentDailyBatchTaskItemBO taskItemBO = new RepaymentDailyBatchTaskItemBO(
							task.getNextTaskItemId());
					logger.info("开始执行跑批任务[loanInfoId:{}, date:{}, taskType:{}, sequence:{}]", task.getLoanInfoId(),
							task.getDate(), taskItemBO.getTaskType(), taskItemBO.getSequence());
					IBatchTaskHandler handler = batchTaskHandlerFactory.getHandler(task);
					if (handler != null)
						handler.execute();
					logger.info("执行跑批任务成功");
				} catch (Exception e) {
					logger.error("执行跑批任务异常", e);
				} finally {
					MDC.clear();
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
				new CustomizableThreadFactory("batch-task-scheduler-thread"));
		return threadPool;
	}
}

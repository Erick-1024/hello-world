package com.cana.report.scheduler.task;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.member.api.IUserApi;
import com.cana.report.service.transaction.IReportTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

/**
 * 初始化报表任务
 * 
 * @author dev3
 *
 */
@Service
public class InitReportTask {

	private static Logger logger = LoggerFactory.getLogger(InitReportTask.class);

	@Resource
	private IVbamCommonService commonService;

	@Resource
	private IUserApi userApiImpl;

	@Resource
	private IReportTransactionService reportTransactionService;

	/**
	 * 执行报表初始化定时任务 每天 每隔半小时执行一次定时任务
	 */
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_SECOND * 10)
	public void doTask() {
		logger.info("融资报表初始化定时任务 - 状态 - 报表初始化定时任务启动");
		if (commonService.isInitReportTaskDone()) {
			logger.info("融资报表初始化定时任务 - 状态 - 每日初始化报表任务已经完成，本次任务结束！");
			return;
		}
		logger.info("融资报表初始化定时任务 - 执行 - 开始执行每日初始化报表任务");
		Transaction transaction = Cat.newTransaction("scheduler", "init-repayment-report-task");
		try {
			// 初始化报表
			Map<String, List<String>> allUserIds = userApiImpl.getAllUserIds();
			reportTransactionService.initReportDayAndYear(allUserIds);
			logger.info("融资报表初始化定时任务 - 状态 - 执行初始化报表任务成功。");
			transaction.setStatus(Transaction.SUCCESS);
			Cat.logMetricForCount("init-report-task_success");
		} catch (Exception e) {
			logger.error("融资报表初始化定时任务 - 状态 - 执行初始化报表任务失败，异常原因：", e);
			Cat.getProducer().logError(e);
			transaction.setStatus(e);
			Cat.logMetricForCount("init-report-task_error");
		} finally{
			transaction.complete();
		}
	}
}

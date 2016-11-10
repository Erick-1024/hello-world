/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.report.scheduler.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import com.cana.report.dao.mapper.gen.ReportAccountFundDailyMapper;
import com.cana.report.dao.po.ReportAccountFundDaily;
import com.cana.report.dao.po.ReportAccountFundDailyExample;
import com.cana.report.scheduler.batch.AccountFundReportRetryHandler;
import com.cana.report.scheduler.batch.IHandler;
import com.cana.vbam.common.report.enums.FundReportState;
import com.travelzen.framework.core.collection.LimitedQueue;

/**
 * @author ducer
 *
 */
@Component
public class AccountFundReportRetryTask {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final int pageSize = 100;
	@Resource
	private ReportAccountFundDailyMapper reportAccountFundDailyMapper;
	
	// 每一分钟一次检查一次
//	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 1)
	public void doTask() throws Exception{
		ExecutorService pool = getThreadPool();
		for (int page = 0;;) {
			page++;
			try {
				List<ReportAccountFundDaily> reports = pullFailReport(page,pageSize);
				logger.info("账户资金报表失败重试定时任务-执行-拉取需要重试的报表，拉取数量:{}", reports.size());
				if (CollectionUtils.isEmpty(reports)) {
					logger.info("账户资金报表失败重试定时任务-执行-已取完所有需要重试的报表");
					break;
				}
				pool.submit(createTask(reports));
			} catch (Throwable e) {
				logger.error("账户资金报表失败重试定时任务-异常-拉取需要重试的报表，分页条件为:page{},pageSize:{}", page, pageSize);
				logger.error("", e);
				break;
			}
		}
		pool.shutdown();
		pool.awaitTermination(3, TimeUnit.MINUTES);
	}

	private List<ReportAccountFundDaily> pullFailReport(int page,int pageSize){
		ReportAccountFundDailyExample ex = new ReportAccountFundDailyExample();
		ReportAccountFundDailyExample.Criteria creteria = ex.createCriteria();
		creteria.andFundReportStateNotEqualTo(FundReportState.success.getCode());
		ex.setOrderByClause("id asc");
		ex.setLimitStart((page - 1) * pageSize); 
		ex.setLimitEnd(pageSize);
		return reportAccountFundDailyMapper.selectByExample(ex);
	}
	
	private Runnable createTask(final List<ReportAccountFundDaily> reports) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					IHandler<ReportAccountFundDaily> handler = new AccountFundReportRetryHandler();
					logger.info("启动报表重新生成任务，本次重跑条数:{}", reports.size());
					handler.execute(reports);
				} catch (Exception e) {
					logger.error("账户资金报表失败重试定时任务-异常-执行重试处理任务失败",e);
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
				new CustomizableThreadFactory("process-account-fund-report-retry-thread"));
		return threadPool;
	}

}

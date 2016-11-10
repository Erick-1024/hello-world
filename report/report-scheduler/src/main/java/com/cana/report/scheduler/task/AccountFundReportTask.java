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
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import com.cana.member.api.IUserApi;
import com.cana.report.dao.mapper.gen.ReportAccountFundDailyMapper;
import com.cana.report.dao.po.ReportAccountFundDailyExample;
import com.cana.report.scheduler.batch.AccountFundReportHandler;
import com.cana.report.scheduler.batch.IHandler;
import com.cana.vbam.common.service.IVbamCommonService;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.collection.LimitedQueue;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * 附属账号资金报表生成任务，交易记录，银行流水
 * 
 * @author ducer
 *
 */
@Component
public class AccountFundReportTask {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final int pageSize = 100;
	@Resource
	private IUserApi userApi;
	@Resource
	private ReportAccountFundDailyMapper reportAccountFundDailyMapper;
	@Resource
	protected IVbamCommonService commonService;
	
	// @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 5)
	// 每天的凌晨0-5点每30分钟跑一次，白天不会再跑，原则只跑一次就够了，多次跑只是跑调用用户API失败而没有记录的客户
//	@Scheduled(cron = "0 */10 0-12 * * *")
	public void doTask() throws Exception{
		ExecutorService pool = getThreadPool();
		for (int page = 0;;) {
			page++;
			try {
				List<String> customerIds = userApi.getCustomerIds(page, pageSize);
				logger.info("账户资金报表定时任务-执行-拉取企业ID，拉取数量:{}", customerIds.size());
				if (CollectionUtils.isEmpty(customerIds)) {
					logger.info("账户资金报表定时任务-执行-已取完所有平台账号");
					break;
				}
				for (String customerId : customerIds) {
					if(!existReport(customerId)){
						logger.info("账户资金报表定时任务-执行-企业:{} 不存在报表记录，创建报表生成线程",customerId);
						pool.submit(createTask(customerId));
					}
				}
			} catch (Throwable e) {
				logger.error("账户资金报表定时任务-异常-拉取企业ID，分页条件为:page{},pageSize:{}", page, pageSize);
				logger.error("", e);
				break;
			}
		}
		pool.shutdown();
		pool.awaitTermination(3, TimeUnit.MINUTES);
	}

	private Runnable createTask(final String customerId) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					IHandler<String> handler = new AccountFundReportHandler();
					logger.info("启动客户:{} 的报表生成任务",customerId);
					handler.execute(Lists.newArrayList(customerId));
				} catch (Exception e) {
					logger.error("账户资金报表定时任务-异常-执行处理任务失败",e);
				}
			}
		};
	}

	/**
	 * 方便测试
	 * @param customerId
	 * @return
	 */
	private boolean existReport(String customerId){
		String date = commonService.getCurrentDate();
		if(StringUtils.isBlank(date) || DateTime.now().isBefore(DateTimeUtil.parseDate10(date).plusDays(1))){
			date = DateTimeUtil.parseDate10(date).minusDays(1).toString("yyyy-MM-dd");
		}
		ReportAccountFundDailyExample ex = new ReportAccountFundDailyExample();
		ReportAccountFundDailyExample.Criteria creteria = ex.createCriteria();
		creteria.andCustomerIdEqualTo(customerId).andReportDateEqualTo(date);
		int count = reportAccountFundDailyMapper.countByExample(ex);
		return count > 0 ;
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
				new CustomizableThreadFactory("process-account-fund-report-thread"));
		return threadPool;
	}

}

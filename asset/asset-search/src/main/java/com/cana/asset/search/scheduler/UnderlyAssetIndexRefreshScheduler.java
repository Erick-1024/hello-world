package com.cana.asset.search.scheduler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.asset.search.proxy.IndexBuildProxy;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;

@Service
public class UnderlyAssetIndexRefreshScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	IndexBuildProxy IndexBuildProxy;
	
	@PostConstruct
	public void initRefreshAllIndex(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				refreshAllIndexTask();
			}
		});
		thread.start();
	}
	
	@Scheduled(cron = "0 30 3 * * *")
//	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30)
	public void refreshAllIndexTask() {
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		logger.info("基础资产索引全量更新开始");
		try {
			IndexBuildProxy.underlyingAssetIndexCreateOrUpdate(true, null);
		} catch (Exception e) {
			logger.info("", e);
			Cat.logMetricForCount("create_index_error");
		}
		logger.info("基础资产索引全量更新结束");
	}
}

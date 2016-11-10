package com.travelzen.framework.retry.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import com.travelzen.framework.retry.dao.mapper.RetryTaskCustomMapper;
import com.travelzen.framework.retry.dao.mapper.gen.RetryTaskMapper;
import com.travelzen.framework.retry.dao.po.RetryTask;
import com.travelzen.framework.retry.dict.RetryTaskType;
import com.travelzen.framework.retry.handler.IRetryTaskHandler;
import com.travelzen.framework.retry.handler.IRetryTaskHandlerFactory;
import com.travelzen.framework.retry.template.RetryTaskTemplate;

/**
 * 重试任务调度器
 * 
 * @author renshui
 * 
 */
public class RetryTaskScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	protected RetryTaskMapper retryTaskMapper;
	
	@Resource
	protected RetryTaskCustomMapper retryTaskCustomMapper;

	private Map<RetryTaskType, ThreadPoolExecutor> threadPoolCache = new HashMap<>();

	private ThreadPoolExecutor defaultRetryTaskThreadPool = new ThreadPoolExecutor(15, 30, 5L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1000),
			new CustomizableThreadFactory("default-retry-task-thread"));
	
	@Resource
	private IRetryTaskHandlerFactory retryTaskHandlerFactory;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_SECOND * 3)
	public void doTask() throws Exception {
		logger.info("处理重试任务开始");
		try {
			List<RetryTask> tasks = getRetryTasks();
			logger.info("发现" + tasks.size() + "个任务");
			for (RetryTask task : tasks) {
				IRetryTaskHandler handler =  retryTaskHandlerFactory.getHandler(task.getTaskType());
				getTheadPool(task).execute(new RetryTaskTemplate(retryTaskMapper, task, handler));
			}
		} catch (Exception e) {
			logger.error("处理重试任务出现异常", e);
			e.printStackTrace();
		}
		logger.info("处理重试任务结束");
	}

	private synchronized ThreadPoolExecutor getTheadPool(RetryTask task) {
		if (!task.getUseIsolatedThreadPool())
			return defaultRetryTaskThreadPool;
		RetryTaskType taskType = RetryTaskType.valueOf(task.getTaskType());
		ThreadPoolExecutor pool = threadPoolCache.get(taskType);
		if (pool == null) {
			pool = new ThreadPoolExecutor(task.getThreadPoolSize().intValue(), task.getThreadPoolSize().intValue(), 5L, TimeUnit.MINUTES,
					new LinkedBlockingQueue<Runnable>(1000), new CustomizableThreadFactory(task.getTaskType() + "-retry-task-thread"));
			threadPoolCache.put(taskType, pool);
		}
		return pool;
	}

	private List<RetryTask> getRetryTasks() throws Exception{
		List<String> taskTypes = new ArrayList<>();
		for(RetryTaskType taskType : retryTaskHandlerFactory.canHandleTaskTypes())
			taskTypes.add(taskType.name());
		return retryTaskCustomMapper.getOldestRetryTask(taskTypes, 100);
	}
}

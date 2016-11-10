package com.cana.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cana.quartz.service.IEchoService;
import com.travelzen.framework.core.time.DateTimeUtil;

// 如果实例不允许并发执行，一定要加这个标签
@DisallowConcurrentExecution
public class TestJob extends QuartzJobBean{  
  
	@Autowired
	private IEchoService echoService;
  
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {  
    	System.out.println(DateTimeUtil.datetime14() + ":" + context.getTrigger().getKey().getName() + "-begin");
    	echoService.echo();
    	System.out.println(DateTimeUtil.datetime14() + ":" + context.getTrigger().getKey().getName() + "-end");
    }  
  
}  

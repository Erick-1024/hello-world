/**
 * 
 */
package com.cana.yundaex.scheduler.schedulers;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.yundaex.service.transaction.IYundaexLoanApplyService;

/**
 * 韵达项目--放款
 * 
 * @author guguanggong
 *
 */
@Service
public class YundaexLoanScheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IYundaexLoanApplyService yundaexLoanApplyService;

	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE)
	public void doTask() throws Exception {
		logger.info("开始执行融资申请！");
		long startTime = System.currentTimeMillis();
		yundaexLoanApplyService.executeLoanTask();
		logger.info("结束融资申请！");
		logger.info("本次耗时{}ms", System.currentTimeMillis() - startTime);
	}

}

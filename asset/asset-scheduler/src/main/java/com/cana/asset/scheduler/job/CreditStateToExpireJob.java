package com.cana.asset.scheduler.job;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;

@DisallowConcurrentExecution
public class CreditStateToExpireJob extends QuartzJobBean{
	
	@Resource
	private IAssetCreditTransactionService assetCreditTransactionService;
	
	@Resource
	private IVbamCommonService commonService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		String currentDate = commonService.getCurrentDate();
		logger.info("开始执行"+ currentDate + "资产管理额度过期定时器");
		try{
			if(!commonService.isSetCreditExpireTaskDone()){
				assetCreditTransactionService.updateExpireCreditState(currentDate);
				commonService.markSetCreditExpireTaskDone();
			}
			logger.info(currentDate + "资产管理额度过期定时器执行完成");
		}catch(Exception e){
			logger.error("设置额度过期失败", e);
			Cat.logMetricForCount("set_credit_expire_fail");
		}finally{
			MDC.clear();
		}
	}

}

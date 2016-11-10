package com.cana.asset.scheduler.job;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cana.asset.service.transaction.IABSSpecialProgramTransactionService;
import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;

public class SpecialProgramStatusToColseJob extends QuartzJobBean{
	
	@Resource
	private IABSSpecialProgramTransactionService aBSSpecialProgramTransactionService;
	
	@Resource
	private IVbamCommonService commonService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private PropertiesMapper propertiesMapper;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		String currentDate = commonService.getCurrentDate();
		logger.info("开始执行"+ currentDate + "专项计划法定到期日状态变为结束定时器");
		try{
			if(!isSetSpecialProgramStatusToColseTaskDone(currentDate)){
				aBSSpecialProgramTransactionService.updateSpecialProgramStatusByStatutoryDueDate(currentDate);
				markSetSpecialProgramStatusToColseTaskDone(currentDate);
			}
			logger.info(currentDate + "专项计划法定到期日状态变为结束定时器执行完成");
		}catch(Exception e){
			logger.error("设置专项计划状态失败", e);
			Cat.logMetricForCount("set_specialProgram_status_close_fail");
		}finally{
			MDC.clear();
		}
	}
	
	public boolean isSetSpecialProgramStatusToColseTaskDone(String currentDate) {
		String propertiesName = Constants.TASK_SET_SPECIALPROGRAM_STATUS_TO_CLOSE + currentDate;
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		if(null == properties){
			return false;
		} else {
			return Boolean.valueOf(properties.getValue());
		}
	}
	
	public void markSetSpecialProgramStatusToColseTaskDone(String currentDate) {
		String propertiesName = Constants.TASK_SET_SPECIALPROGRAM_STATUS_TO_CLOSE + currentDate;
		Properties properties = propertiesMapper.selectByPrimaryKey(propertiesName);
		if(properties == null){
			properties = new Properties();
			properties.setName(propertiesName);
			properties.setValue(Boolean.TRUE.toString());
		}
		propertiesMapper.updateByPrimaryKeySelective(properties);
	}

}

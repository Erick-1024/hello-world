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

import com.cana.asset.service.transaction.IHomsomTransactionService;
import com.cana.homsom.dao.mapper.gen.HomsomDailyLoanReportMapper;
import com.cana.homsom.dao.po.HomsomDailyLoanReport;
import com.cana.homsom.dao.po.HomsomDailyLoanReportKey;
import com.cana.member.api.IMemberQueryApi;
import com.cana.setting.service.transaction.ICanaCalendarTransactionService;
import com.cana.vbam.common.homsom.enums.Channel;
import com.cana.vbam.common.homsom.enums.LoanState;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.core.time.DateTimeUtil;

@DisallowConcurrentExecution
public class HomsomGenerateLoanJob extends QuartzJobBean{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IVbamCommonService commonService;
	@Resource
	private ICanaCalendarTransactionService calendarTransactionService;
	@Resource
	private HomsomDailyLoanReportMapper loanReportMapper;
	@Resource
	private IMemberQueryApi memberQueryApi;
	@Resource 
	private IHomsomTransactionService homsomTransactionService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		logger.info("开始生成恒顺国旅放款");
		try{
			String reportDate = commonService.getCurrentDate();
			if(!calendarTransactionService.isWeekday(reportDate)){
				logger.info("{}不是工作日，不生成放款", reportDate);
				return;
			}
			String issueDate = getLastWorkday(reportDate);
			logger.info("报表日期:{}, 出票日期:{}", reportDate, issueDate);
			if(alreadyGenerateLoan(reportDate)){
				logger.info("恒顺国旅已经生成过放款，不再重复处理");
				return;
			}
			homsomTransactionService.prepareGenerateLoan(Channel.HOMSOM, reportDate, issueDate);
			UserVo factorUser = memberQueryApi.findUserById(TopsConfReader.getConfContent("properties/homsom_param.properties", "factorId", ConfScope.R));
			homsomTransactionService.generateLoan(factorUser, Channel.HOMSOM, reportDate, issueDate);
			logger.info("恒顺国旅生成放款成功");
		}catch(Exception e){
			logger.error("恒顺国旅生成放款失败", e);
			Cat.logMetricForCount("homsom_generate_loan_fail");
		}finally{
			MDC.clear();
		}
	}
	
	private boolean alreadyGenerateLoan(String date) {
		HomsomDailyLoanReportKey key = new HomsomDailyLoanReportKey();
		key.setChannel(Channel.HOMSOM.name());
		key.setCounterpartyId("");
		key.setDate(date);
		HomsomDailyLoanReport summaryReport = loanReportMapper.selectByPrimaryKey(key);
		return summaryReport != null && !LoanState.UNFINISHED.name().equals(summaryReport.getLoanState());
	}

	private String getLastWorkday(String now){
		return DateTimeUtil.addDay10(now, -1 * calendarTransactionService.getBeforeFirstWeekday(now));
	}

}

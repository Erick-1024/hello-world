package com.cana.report.service.event;

import com.cana.member.api.IUserApi;
import com.cana.repayment.service.ILoanInfoService;
import com.cana.repayment.service.IRepaymentPlanSnapshotService;
import com.cana.report.dao.mapper.OverdueCustomerCountMapper;
import com.cana.report.dao.mapper.ReportFactorFinanceCountLockMapper;
import com.cana.report.dao.mapper.ReportFactorFinanceDayLockMapper;
import com.cana.report.dao.mapper.ReportFactorFinanceYearLockMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceCountMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceDayMapper;
import com.cana.report.dao.mapper.gen.ReportFactorFinanceYearMapper;
import com.cana.report.service.transaction.IReportTransactionService;
import com.cana.vbam.common.service.IVbamCommonService;
import com.dianping.cat.Cat;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public abstract class AbstractEventListener implements ILoanInfoChangeEventListener {
	
	public ReportFactorFinanceDayLockMapper reportFactorFinanceDayLockMapper = SpringApplicationContext.getApplicationContext().getBean(ReportFactorFinanceDayLockMapper.class);
	
	public ReportFactorFinanceDayMapper reportFactorFinanceDayMapper = SpringApplicationContext.getApplicationContext().getBean(ReportFactorFinanceDayMapper.class);
	
	public ReportFactorFinanceYearMapper reportFactorFinanceYearMapper = SpringApplicationContext.getApplicationContext().getBean(ReportFactorFinanceYearMapper.class);
	
	public ReportFactorFinanceYearLockMapper reportFactorFinanceYearLockMapper = SpringApplicationContext.getApplicationContext().getBean(ReportFactorFinanceYearLockMapper.class);
	
	public ReportFactorFinanceCountMapper reportFactorFinanceCountMapper = SpringApplicationContext.getApplicationContext().getBean(ReportFactorFinanceCountMapper.class);
	
	public ReportFactorFinanceCountLockMapper reportFactorFinanceCountLockMapper = SpringApplicationContext.getApplicationContext().getBean(ReportFactorFinanceCountLockMapper.class);
	
	public IReportTransactionService reportTransactionService = SpringApplicationContext.getApplicationContext().getBean(IReportTransactionService.class);
	
	public IRepaymentPlanSnapshotService repaymentPlanSnapshotService = SpringApplicationContext.getApplicationContext().getBean(IRepaymentPlanSnapshotService.class);
	
	public ILoanInfoService loanInfoService = SpringApplicationContext.getApplicationContext().getBean(ILoanInfoService.class);
	
	public IUserApi userApi = SpringApplicationContext.getApplicationContext().getBean(IUserApi.class);
	
	public IVbamCommonService commonService = SpringApplicationContext.getApplicationContext().getBean(IVbamCommonService.class);

	public OverdueCustomerCountMapper overdueCustomerCountMapper = SpringApplicationContext.getApplicationContext().getBean(OverdueCustomerCountMapper.class);
	
	@Override
	public void execute(LoanInfoChangeEvent event) throws Exception {
		long startTime = System.currentTimeMillis();
		doExecute(event);
		Cat.logMetricForDuration(event.getType().name()+"-event-trace-task_time", System.currentTimeMillis()-startTime);
		Cat.logMetricForCount(event.getType().name()+"-event-trace-task_success");
	}

	public void doExecute(LoanInfoChangeEvent event) throws Exception{
		//TODO:
	}
	
}

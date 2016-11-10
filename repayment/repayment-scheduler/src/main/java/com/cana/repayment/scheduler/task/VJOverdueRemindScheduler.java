package com.cana.repayment.scheduler.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;

import com.cana.member.api.IUserApi;
import com.cana.message.client.message.MessageClient;
import com.cana.repayment.dao.mapper.IRepaymentRemindMapper;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.message.util.SmsMessageFilterUtil;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.vj.template.VJSmsMessageTemplate;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.collection.LimitedQueue;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class VJOverdueRemindScheduler {

	private static Logger logger = LoggerFactory.getLogger(VJOverdueRemindScheduler.class);

	@Resource
	private IVbamCommonService commonService;
	
	@Resource
    private MessageClient messageClient;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IRepaymentRemindMapper repaymentRemindMapper;
	
	@Resource
	private IRepaymentServiceHelper repaymentServiceHelper;
	
	@Scheduled(cron="0 05 14 ? * *")
	public void doTask() throws Exception {
		
		logger.info("启动VJ逾期短信提醒定时器");

		// 获取逾期的放款信息
		List<RepaymentPlan> overdueRepaymentPlanList = getOverdueRepaymentPlan();
		if(CollectionUtils.isEmpty(overdueRepaymentPlanList)){
			logger.info("没有VJ逾期信息");
			return;
		}
		ExecutorService executorService = getThreadPool();
		
		logger.info("还款提醒-获取到需要提醒的逾期放款信息共计" + overdueRepaymentPlanList.size() + "条，开始处理逾期放款信息短信通知。");
		for(RepaymentPlan repaymentPlan : overdueRepaymentPlanList){
			RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO(repaymentPlan);
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentPlanBO.lazyLoadLoanInfoBO().getCreateTime())){
				executorService.execute(createOverdueRemindTask(repaymentPlanBO));
			}
		}
		logger.info("还款提醒-获取到需要提醒的逾期放款信息共计" + overdueRepaymentPlanList.size() + "条，处理逾期放款信息短信通知结束。");
		
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.MINUTES);
	}

	private List<RepaymentPlan> getOverdueRepaymentPlan(){
		// 获取逾期的放款信息
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-开始获取逾期的放款信息");
		List<RepaymentPlan> repaymentPlanList = repaymentRemindMapper.getAllOverdueRepaymentPlan(Constants.VJ_PRODUCT_ID, curDate);
		logger.info("还款提醒-获取逾期的放款信息结束");
		return repaymentPlanList;
	}
	
	private Runnable createOverdueRemindTask(final RepaymentPlanBO repaymentPlanBO) {
		return new Runnable() {
			@Override
			public void run() {
//				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
//				List<Integer> overdueList = Lists.newArrayList();
//				
//				for(RepaymentPlan repaymentPlan : loanInfoBO.lazyLoadPlans()){
//					if(StringUtils.equals(repaymentPlan.getSettleStatus(), SettleStatus.UNSETTLE.name()) && 
//							repaymentPlan.getRepaymentDate().compareTo(commonService.getCurrentDate()) < 0){
//						overdueList.add(repaymentPlan.getRepaymentPeriod() - 1);
//					}
//				}
//				if(CollectionUtils.isEmpty(overdueList)){
//					return;
//				}
				try{
					CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentPlanBO.lazyLoadLoanInfoBO().getFinanceId());
					if(null == customerDetailDTO){
						logger.info("该用户不存在");
						return;
					}
//					for(Integer repaymentPeriod:overdueList){
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					int overdueDay = DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(commonService.getCurrentDate()), DateTimeUtil.parseDate10(repaymentPlanBO.getRepaymentDate()));
					String template = "";
					//公司名称
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					if(overdueDay<=3){
						//应还本金（逾期本金）
						smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.getOverduePrincipal()));
						//到期日
						smsMessageParamList.add(repaymentPlanBO.getRepaymentDate());
						//逾期天数
						smsMessageParamList.add(overdueDay + "");
						//逾期总金额
						smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalOverdueAccountAmount()));
						template = VJSmsMessageTemplate.vj_less_overdue_repayment_remind_sms_message_content_template;
					}else{
						//逾期总金额
						smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalOverdueAccountAmount()));
						//逾期天数
						smsMessageParamList.add(overdueDay + "");
						template = VJSmsMessageTemplate.vj_more_overdue_repayment_remind_sms_message_content_template;
					}
					repaymentServiceHelper.sendVJSmsMessage(repaymentServiceHelper.generateVJMessageContent(template, smsMessageParamList), customerDetailDTO.getContactTel());
//					}
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
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
				new CustomizableThreadFactory("vj-overdue-remind-task-scheduler-thread"));
		return threadPool;
	}
}

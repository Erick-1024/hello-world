package com.cana.repayment.scheduler.task;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class VJBeforeRepaymentDateRemindScheduler {

	private static Logger logger = LoggerFactory.getLogger(VJBeforeRepaymentDateRemindScheduler.class);

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
	
	private List<Integer> beforeRepaymentDateDays = Arrays.asList(1,2,3,7); 
	
//	@Scheduled(cron="0 00 10 ? * *")
	public void doTask() throws Exception {

		logger.info("启动VJ还款前几日短信提醒");

		// 获取还款日前几天的放款信息
		List<RepaymentPlan> beforeRepaymentDatePlanList = getBeforeRepaymentDatePlan();
		if(CollectionUtils.isEmpty(beforeRepaymentDatePlanList)){
			logger.info("没有VJ还款前几日信息");
			return;
		}
		
		ExecutorService executorService = getThreadPool();
		
		logger.info("还款提醒-获取还款日前几天的放款信息共计" + beforeRepaymentDatePlanList.size() + "条，处理还款日前几天的放款信息短信通知start。");
		for(RepaymentPlan repaymentPlan : beforeRepaymentDatePlanList){
			RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO(repaymentPlan);
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentPlanBO.lazyLoadLoanInfoBO().getCreateTime())){
				executorService.execute(createBeforeRepaymentDateRemindTask(repaymentPlanBO));
			}
		}
		logger.info("还款提醒-获取还款日前几天的放款信息共计" + beforeRepaymentDatePlanList.size() + "条，处理还款日前几天的放款信息短信通知end。");
		
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.MINUTES);
	}

	/**
	 * 获取还款日前几天的放款信息
	 * @return
	 */
	private List<RepaymentPlan> getBeforeRepaymentDatePlan(){
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-获取还款日前几天的放款信息start");
		// curDate+1 <= repaymentDate <= curDate+7
		List<RepaymentPlan> repaymentPlanList = repaymentRemindMapper.getAllRepaymentPlanBeforeRepaymentDate(Constants.VJ_PRODUCT_ID, DateTimeUtil.addDay10(curDate, 1),DateTimeUtil.addDay10(curDate, 7));
		logger.info("还款提醒-获取还款日前几天的放款信息end");
		return repaymentPlanList;
	}
	
	private Runnable createBeforeRepaymentDateRemindTask(final RepaymentPlanBO repaymentPlanBO) {
		return new Runnable() {
			@Override
			public void run() {
//				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
//				List<Integer> repaymentPeriodList = Lists.newArrayList();
//				
//				for(RepaymentPlan repaymentPlan : loanInfoBO.lazyLoadPlans()){
//					if(StringUtils.equals(repaymentPlan.getSettleStatus(), SettleStatus.UNSETTLE.name()) && 
//							repaymentPlan.getRepaymentDate().compareTo(commonService.getCurrentDate()) > 0){
//						repaymentPeriodList.add(repaymentPlan.getRepaymentPeriod() - 1);
//					}
//				}
//				if(CollectionUtils.isEmpty(repaymentPeriodList)){
//					return;
//				}
				try{
					CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentPlanBO.lazyLoadLoanInfoBO().getFinanceId());
					if(null == customerDetailDTO){
						logger.info("该用户不存在");
						return;
					}
					String template = VJSmsMessageTemplate.vj_before_repayment_date_remind_sms_message_content_template;
//					for(Integer repaymentPeriod:repaymentPeriodList){
					// 获取短信相关参数并组装并发送
					int beforeRepaymentDateDay = DateTimeUtil.diffInDay(DateTimeUtil.parseDate10(commonService.getCurrentDate()), DateTimeUtil.parseDate10(repaymentPlanBO.getRepaymentDate()));
					if(!beforeRepaymentDateDays.contains(beforeRepaymentDateDay))
						return;
					List<String> smsMessageParamList = Lists.newArrayList();
					//公司名称
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					//提醒提前天数
					smsMessageParamList.add(beforeRepaymentDateDay+"");
					//应还总金额
					smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalAccountAmount()));
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
				new CustomizableThreadFactory("vj-before-repayment-date-remind-task-scheduler-thread"));
		return threadPool;
	}
}

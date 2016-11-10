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

@Service
public class VJOnRepaymentDateRemindScheduler {

	private static Logger logger = LoggerFactory.getLogger(VJOnRepaymentDateRemindScheduler.class);

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
	
	@Scheduled(cron="0 07 17 ? * *")
	public void doTask() throws Exception {

		logger.info("启动VJ还款当日短信提醒");
		
		// 获取还款日当天的放款信息
		List<RepaymentPlan> onRepaymentDatePlanList = getOnRepaymentDateLoanInfo();
		if(CollectionUtils.isEmpty(onRepaymentDatePlanList)){
			logger.info("没有VJ还款当日信息");
			return;
		}
		
		ExecutorService executorService = getThreadPool();
		
		logger.info("还款提醒-获取到需要提醒的还款日当天的放款信息共计" + onRepaymentDatePlanList.size() + "条，处理还款日当天的放款信息短信通知start。");
		for(RepaymentPlan repaymentPlan : onRepaymentDatePlanList){
			RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO(repaymentPlan);
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentPlanBO.lazyLoadLoanInfoBO().getCreateTime())){
				executorService.execute(createOnRepaymentRemindTask(repaymentPlanBO));
			}
		}
		logger.info("还款提醒-获取到需要提醒的还款日当天的放款信息共计" + onRepaymentDatePlanList.size() + "条，处理还款日当天的放款信息短信通知end。");
		
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.MINUTES);
	}

	/** 
	 * 获取还款日当天的放款信息
	 * @return
	 */
	private List<RepaymentPlan> getOnRepaymentDateLoanInfo(){
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-获取还款日当天的放款信息start");
		List<RepaymentPlan> repaymentPlanList = repaymentRemindMapper.getAllRepaymentPlanOnRepaymentDate(Constants.VJ_PRODUCT_ID, curDate);
		logger.info("还款提醒-获取还款日当天的放款信息end");
		return repaymentPlanList;
	}
	
	private Runnable createOnRepaymentRemindTask(final RepaymentPlanBO repaymentPlanBO) {
		return new Runnable() {
			@Override
			public void run() {
//				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
//				List<Integer> repaymentPeriodList = Lists.newArrayList();
//				
//				for(RepaymentPlan repaymentPlan : loanInfoBO.lazyLoadPlans()){
//					if(StringUtils.equals(repaymentPlan.getSettleStatus(), SettleStatus.UNSETTLE.name()) && 
//							repaymentPlan.getRepaymentDate().compareTo(commonService.getCurrentDate()) == 0){
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
					String template = VJSmsMessageTemplate.vj_on_repayment_date_remind_sms_message_content_template;
//					for(Integer repaymentPeriod:repaymentPeriodList){
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					//公司名称
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
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
				new CustomizableThreadFactory("vj-on-repayment-date-remind-task-scheduler-thread"));
		return threadPool;
	}
}

package com.cana.repayment.scheduler.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.SmsMessageType;
import com.cana.vbam.common.message.util.SmsMessageFilterUtil;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.WebEnv;
import com.cana.vbam.common.yundaex.templete.YundaexMailMessageTemplate;
import com.cana.vbam.common.yundaex.templete.YundaexSmsMessageTemplate;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.collection.LimitedQueue;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class YundaexRepaymentRemindScheduler {

	private static Logger logger = LoggerFactory.getLogger(YundaexRepaymentRemindScheduler.class);

	@Resource
	private IVbamCommonService commonService;
	
	@Resource
    private MessageClient messageClient;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IRepaymentRemindMapper repaymentRemindMapper;
	
	private List<RepaymentPlan> repaymentPlanBeforeRepaymentDateList;
	
	private List<RepaymentPlan> repaymentPlanOnRepaymentDateList;
	
	private List<RepaymentPlan> overdueRepaymentPlanList;

	@Scheduled(cron="0 30 10 ? * *")
	public void doTask() throws Exception {
		
		repaymentPlanBeforeRepaymentDateList = Lists.newArrayList();
		
		repaymentPlanOnRepaymentDateList = Lists.newArrayList();
		
		overdueRepaymentPlanList = Lists.newArrayList();
		
		// 获取还款前2日、1日的放款信息
		getLoanInfoBeforeRepaymentDate();

		// 获取还款日为当前日的放款信息
		getLoanInfoOnRepaymentDate();

		// 获取逾期的放款信息
		getOverdueLoanInfo();
		
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentPlanBeforeRepaymentDateList.size() + "条，开始处理放款信息短信通知。");
		ExecutorService executorService = getThreadPool();
		for (RepaymentPlan repaymentPlan : repaymentPlanBeforeRepaymentDateList){
			RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO(repaymentPlan);
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentPlanBO.lazyLoadLoanInfoBO().getCreateTime())){
				executorService.execute(createRemindTaskBeforeRepaymentDate(repaymentPlanBO));
			}
		}
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentPlanBeforeRepaymentDateList.size() + "条，处理放款信息短信通知结束。");
		
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentPlanOnRepaymentDateList.size() + "条，开始处理放款信息短信通知。");
		for(RepaymentPlan repaymentPlan : repaymentPlanOnRepaymentDateList){
			RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO(repaymentPlan);
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentPlanBO.lazyLoadLoanInfoBO().getCreateTime())){
				executorService.execute(createRemindTaskOnRepaymentDate(repaymentPlanBO));
			}
		}
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentPlanOnRepaymentDateList.size() + "条，处理放款信息短信通知结束。");
		
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
	
	private void getLoanInfoBeforeRepaymentDate(){
		// 获取还款前2日、1日的放款信息
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-开始获取还款日前1-2日的放款信息");
		repaymentPlanBeforeRepaymentDateList.addAll(repaymentRemindMapper.getAllRepaymentPlanBeforeRepaymentDate(Constants.YUNDAEX_FINANCE_PRODUCT_ID, 
				DateTimeUtil.addDay10(curDate, 1), DateTimeUtil.addDay10(curDate, 2)));
		logger.info("还款提醒-获取还款日前1-2日的放款信息结束");
	}

	private void getLoanInfoOnRepaymentDate(){
		// 获取还款日为当前日的放款信息
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-开始获取还款日为当前日的放款信息");
		repaymentPlanOnRepaymentDateList.addAll(repaymentRemindMapper.getAllRepaymentPlanOnRepaymentDate(Constants.YUNDAEX_FINANCE_PRODUCT_ID, curDate));
		logger.info("还款提醒-获取还款日为当前日的放款信息结束");
	}

	private void getOverdueLoanInfo(){
		// 获取逾期的放款信息
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-开始获取逾期的放款信息");
		List<RepaymentPlan> repaymentLoanInfoList = repaymentRemindMapper.getAllOverdueRepaymentPlan(Constants.YUNDAEX_FINANCE_PRODUCT_ID, curDate);
		for(RepaymentPlan repaymentPlan:repaymentLoanInfoList){
//			RepaymentLoanInfoBO repaymentLoanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
			RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO(repaymentPlan);
			if(repaymentPlanBO.getExtensionDays() == 0){
				overdueRepaymentPlanList.add(repaymentPlan);
				continue;
			}
			if(curDate.compareTo(DateTimeUtil.date10(DateTime.parse(repaymentPlanBO.getRepaymentDate()).plusDays(repaymentPlanBO.getExtensionDays()))) <= 0){
				repaymentPlanOnRepaymentDateList.add(repaymentPlan);
			}else{
				overdueRepaymentPlanList.add(repaymentPlan);
			}
		}
		logger.info("还款提醒-获取逾期的放款信息结束");
	}
	
	private Runnable createRemindTaskBeforeRepaymentDate(final RepaymentPlanBO repaymentPlanBO) {
		return new Runnable() {
			@Override
			public void run() {
//				RepaymentPlanBO repaymentPlanBO = new RepaymentPlanBO(repaymentPlan);
//				int repaymentPeriod = -1;
//				for(RepaymentPlan repaymentPlan : loanInfoBO.lazyLoadPlans()){
//					if(StringUtils.equals(repaymentPlan.getSettleStatus(), SettleStatus.UNSETTLE.name()) && 
//							repaymentPlan.getRepaymentDate().compareTo(commonService.getCurrentDate()) >= 0){
//						repaymentPeriod = repaymentPlan.getRepaymentPeriod() - 1;
//						break;
//					}
//				}
//				if(repaymentPeriod == -1){
//					repaymentPeriod = loanInfoBO.lazyLoadPlans().size() -1;
//				}
				CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentPlanBO.lazyLoadLoanInfoBO().getFinanceId());
				if(null == customerDetailDTO){
					logger.info("该用户不存在");
					return;
				}
				try{
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					smsMessageParamList.add(repaymentPlanBO.lazyLoadLoanInfoBO().getId());
					smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalAccountAmount()));
					smsMessageParamList.add(DateTimeUtil.convertDateToString("yyyy年MM月dd日", DateTime.parse(repaymentPlanBO.getRepaymentDate())));
					sendSmsMessage(generateMessageContent(YundaexSmsMessageTemplate.yd_repayment_remind_before_repaymentdate_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
				
				try{
					// 组装邮件相关参数并发送
					List<String> mailMessageParamList = Lists.newArrayList();
					mailMessageParamList.add(customerDetailDTO.getCompanyName());
					mailMessageParamList.add(repaymentPlanBO.lazyLoadLoanInfoBO().getId());
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalAccountAmount()));
					mailMessageParamList.add(DateTimeUtil.convertDateToString("yyyy年MM月dd日", DateTime.parse(repaymentPlanBO.getRepaymentDate())));
					mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + (repaymentPlanBO.lazyLoadLoanInfoBO().getId())));
					mailMessageParamList.add(YundaexMailMessageTemplate.cana_phone);
					sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(YundaexMailMessageTemplate.
							yd_repayment_remind_before_repaymentdate_mail_message_content_template, mailMessageParamList), 
							YundaexMailMessageTemplate.yd_repayment_remind_mail_message_subject_template);
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
			}
		};
	}
	
	private Runnable createRemindTaskOnRepaymentDate(final RepaymentPlanBO repaymentPlanBO) {
		return new Runnable() {
			@Override
			public void run() {
//				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
//				int repaymentPeriod = -1;
//				for(RepaymentPlan repaymentPlan : loanInfoBO.lazyLoadPlans()){
//					if(StringUtils.equals(repaymentPlan.getSettleStatus(), SettleStatus.UNSETTLE.name()) && 
//							repaymentPlan.getRepaymentDate().compareTo(commonService.getCurrentDate()) >= 0){
//						repaymentPeriod = repaymentPlan.getRepaymentPeriod() - 1;
//						break;
//					}
//				}
//				if(repaymentPeriod == -1){
//					repaymentPeriod = loanInfoBO.lazyLoadPlans().size() - 1;
//				}
				CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentPlanBO.lazyLoadLoanInfoBO().getFinanceId());
				if(null == customerDetailDTO){
					logger.info("该用户不存在");
					return;
				}
				try{
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					smsMessageParamList.add(DateTimeUtil.convertDateToString("yyyy年MM月dd日", DateTime.parse(commonService.getCurrentDate())));
					smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalExtensionAmount()));
					sendSmsMessage(generateMessageContent(YundaexSmsMessageTemplate.yd_repayment_remind_on_repaymentdate_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
				
				try{
					// 组装邮件相关参数并发送
					List<String> mailMessageParamList = Lists.newArrayList();
					mailMessageParamList.add(customerDetailDTO.getCompanyName());
					mailMessageParamList.add(DateTimeUtil.convertDateToString("yyyy年MM月dd日", DateTime.parse(commonService.getCurrentDate())));
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalExtensionAmount()));
					mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + repaymentPlanBO.lazyLoadLoanInfoBO().getId()));
					mailMessageParamList.add(YundaexMailMessageTemplate.cana_phone);
					sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(YundaexMailMessageTemplate.
							yd_repayment_remind_on_repaymentdate_mail_message_content_template, mailMessageParamList), 
							YundaexMailMessageTemplate.yd_repayment_remind_mail_message_subject_template);
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
			}
		};
	}
	
	private Runnable createOverdueRemindTask(final RepaymentPlanBO repaymentPlanBO) {
		return new Runnable() {
			@Override
			public void run() {
//				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
//				int repaymentPeriod = -1;
//				for(RepaymentPlan repaymentPlan : loanInfoBO.lazyLoadPlans()){
//					if(StringUtils.equals(repaymentPlan.getSettleStatus(), SettleStatus.UNSETTLE.name()) && 
//							repaymentPlan.getRepaymentDate().compareTo(commonService.getCurrentDate()) < 0){
//						repaymentPeriod = repaymentPlan.getRepaymentPeriod() - 1;
//						break;
//					}
//				}
//				if(repaymentPeriod == -1){
//					repaymentPeriod = loanInfoBO.lazyLoadPlans().size() -1;
//				}
				CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentPlanBO.lazyLoadLoanInfoBO().getFinanceId());
				if(null == customerDetailDTO){
					logger.info("该用户不存在");
					return;
				}
				try{
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					smsMessageParamList.add(repaymentPlanBO.lazyLoadLoanInfoBO().getId());
					smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalOverdueAccountAmount()));
					smsMessageParamList.add(MoneyArithUtil.convertInterestRateToString(repaymentPlanBO.lazyLoadLoanInfoBO().lazyLoadRepaymentConfig().getPenaltyRate()));
					sendSmsMessage(generateMessageContent(YundaexSmsMessageTemplate.yd_overdue_repayment_remind_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
				
				try{
					// 组装邮件相关参数并发送
					List<String> mailMessageParamList = Lists.newArrayList();
					mailMessageParamList.add(customerDetailDTO.getCompanyName());
					mailMessageParamList.add(repaymentPlanBO.lazyLoadLoanInfoBO().getId());
					mailMessageParamList.add(DateTime.parse(commonService.getCurrentDate()).toString("MM月dd日"));
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalPaid()));
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(repaymentPlanBO.totalOverdueAccountAmount()));
					mailMessageParamList.add(MoneyArithUtil.convertInterestRateToString(repaymentPlanBO.lazyLoadLoanInfoBO().lazyLoadRepaymentConfig().getPenaltyRate()));
					if(DateTime.parse(repaymentPlanBO.lazyLoadLoanInfoBO().getDueDate()).plusDays(3).toString("yyyy-MM-dd").compareTo(commonService.getCurrentDate()) < 0){
						mailMessageParamList.add(YundaexMailMessageTemplate.law_policy_v2);
					}else{
						mailMessageParamList.add(YundaexMailMessageTemplate.law_policy_v1);
					}
					mailMessageParamList.add(repaymentPlanBO.lazyLoadLoanInfoBO().getAccountNo());
					mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + repaymentPlanBO.lazyLoadLoanInfoBO().getId()));
					mailMessageParamList.add(YundaexMailMessageTemplate.cana_phone);
					sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(YundaexMailMessageTemplate.yd_overdue_repayment_remind_mail_message_content_template, mailMessageParamList), 
							YundaexMailMessageTemplate.yd_overdue_repayment_remind_mail_message_subject_template);
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
				new CustomizableThreadFactory("remind-task-scheduler-thread"));
		return threadPool;
	}
	
	private void sendSmsMessage(String content, String receivePhoneNum){
		SmsMessageDTO smsMessageDTO = new SmsMessageDTO();
		smsMessageDTO.setContent(content);
		smsMessageDTO.setPrefix("【CANA金融】");
		smsMessageDTO.setReceivePhoneNum(receivePhoneNum);
		smsMessageDTO.setSmsMessageType(SmsMessageType.NOTICE);
		messageClient.sendSmsMessage(smsMessageDTO);
	}
	
	private String generateMessageContent(String template, List<String> dataItems){
		for(String dataItem : dataItems){
			if(StringUtils.isNotBlank(dataItem)){
				template = template.replaceFirst(YundaexMailMessageTemplate.matchcharactersReg, dataItem);
			}
		}
		return template;
	}
	
	private String generateMailUrl(String url){
		return "<a href=" + url + " target='_blank'>" + url + "</a>";
	}
	
	private void sendMailMessage(String receiver, String content, String subject) {
        MailMessageDTO mailMessageDTO = new MailMessageDTO();
        mailMessageDTO.setContentType(MailContentType.HTML);
        mailMessageDTO.setReceiver(receiver);
        mailMessageDTO.setSubject(subject);
        mailMessageDTO.setContent(content);
        messageClient.sendMail(mailMessageDTO);
    }

}

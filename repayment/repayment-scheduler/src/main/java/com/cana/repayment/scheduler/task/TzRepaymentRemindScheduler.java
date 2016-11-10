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
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoExample;
import com.cana.repayment.service.ILoanInfoService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.member.dto.user.CustomerDetailDTO;
import com.cana.vbam.common.message.MailMessageTemplate;
import com.cana.vbam.common.message.SmsMessageTemplate;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.dto.SmsMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.message.enums.SmsMessageType;
import com.cana.vbam.common.message.util.SmsMessageFilterUtil;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.cana.vbam.common.utils.WebEnv;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.collection.LimitedQueue;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class TzRepaymentRemindScheduler {

	private static Logger logger = LoggerFactory.getLogger(TzRepaymentRemindScheduler.class);

	@Resource
	private IVbamCommonService commonService;
	
	@Resource
	private ILoanInfoService loanInfoService;
	
	@Resource
    private MessageClient messageClient;
	
	@Resource
	private IUserApi userApi;
	
	private List<RepaymentLoanInfo> repaymentLoanInfoBeforeRepaymentDateList;
	
	private List<RepaymentLoanInfo> repaymentLoanInfoOnRepaymentDateList;
	
	private List<RepaymentLoanInfo> overdueRepaymentLoanInfoList;
	
	@Scheduled(cron="0 30 10 ? * *")
	public void doTask() throws Exception {
		
		repaymentLoanInfoBeforeRepaymentDateList = Lists.newArrayList();
		
		repaymentLoanInfoOnRepaymentDateList = Lists.newArrayList();
		
		overdueRepaymentLoanInfoList = Lists.newArrayList();
		
		// 获取还款前2日、1日的放款信息
		getLoanInfoBeforeRepaymentDate();

		// 获取还款日为当前日的放款信息
		getLoanInfoOnRepaymentDate();

		// 获取逾期的放款信息
		getOverdueLoanInfo();
		
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentLoanInfoBeforeRepaymentDateList.size() + "条，开始处理放款信息短信通知。");
		ExecutorService executorService = getThreadPool();
		for (RepaymentLoanInfo repaymentLoanInfo : repaymentLoanInfoBeforeRepaymentDateList){
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentLoanInfo.getCreateTime())){
				executorService.execute(createRemindTaskBeforeRepaymentDate(repaymentLoanInfo));
			}
		}
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentLoanInfoBeforeRepaymentDateList.size() + "条，处理放款信息短信通知结束。");
		
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentLoanInfoOnRepaymentDateList.size() + "条，开始处理放款信息短信通知。");
		for(RepaymentLoanInfo repaymentLoanInfo : repaymentLoanInfoOnRepaymentDateList){
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentLoanInfo.getCreateTime())){
				executorService.execute(createRemindTaskOnRepaymentDate(repaymentLoanInfo));
			}
		}
		logger.info("还款提醒-获取到需要提醒的放款信息共计" + repaymentLoanInfoOnRepaymentDateList.size() + "条，处理放款信息短信通知结束。");
		
		logger.info("还款提醒-获取到需要提醒的逾期放款信息共计" + overdueRepaymentLoanInfoList.size() + "条，开始处理逾期放款信息短信通知。");
		for(RepaymentLoanInfo repaymentLoanInfo : overdueRepaymentLoanInfoList){
			if(SmsMessageFilterUtil.smsSendFlag(commonService.isProdEnv(), repaymentLoanInfo.getCreateTime())){
				executorService.execute(createOverdueRemindTask(repaymentLoanInfo));
			}
		}
		logger.info("还款提醒-获取到需要提醒的逾期放款信息共计" + overdueRepaymentLoanInfoList.size() + "条，处理逾期放款信息短信通知结束。");
		
		executorService.shutdown();
		executorService.awaitTermination(3, TimeUnit.MINUTES);
	}
	
	private void getLoanInfoBeforeRepaymentDate(){
		// 获取还款前2日、1日的放款信息
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-开始获取还款日前1-2日的放款信息");
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andDueDateBetween(DateTimeUtil.addDay10(curDate, 1), 
				DateTimeUtil.addDay10(curDate, 2)).
				andBusinessProductIdEqualTo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID).
				andSettleStatusEqualTo(SettleStatus.UNSETTLE.name());
		repaymentLoanInfoBeforeRepaymentDateList.addAll(loanInfoService.queryLoanInfoListFromDB(example));
		logger.info("还款提醒-获取还款日前1-2日的放款信息结束");
	}

	private void getLoanInfoOnRepaymentDate(){
		// 获取还款日为当前日的放款信息
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-开始获取还款日为当前日的放款信息");
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andDueDateEqualTo(curDate).
				andBusinessProductIdEqualTo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID).
				andSettleStatusEqualTo(SettleStatus.UNSETTLE.name());
		repaymentLoanInfoOnRepaymentDateList.addAll(loanInfoService.queryLoanInfoListFromDB(example));
		logger.info("还款提醒-获取还款日为当前日的放款信息结束");
	}

	private void getOverdueLoanInfo(){
		// 获取逾期的放款信息
		String curDate = commonService.getCurrentDate();
		logger.info("还款提醒-开始获取逾期的放款信息");
		RepaymentLoanInfoExample example = new RepaymentLoanInfoExample();
		example.createCriteria().andDueDateLessThan(curDate).
				andBusinessProductIdEqualTo(Constants.TRAVELZEN_FINANCE_PRODUCT_ID).
				andSettleStatusEqualTo(SettleStatus.UNSETTLE.name());
		List<RepaymentLoanInfo> repaymentLoanInfoList = loanInfoService.queryLoanInfoListFromDB(example);
		for(RepaymentLoanInfo repaymentLoanInfo:repaymentLoanInfoList){
			RepaymentLoanInfoBO repaymentLoanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
			RepaymentPlanBO repaymentPlanBO = repaymentLoanInfoBO.lazyLoadPlans().get(0);
			if(repaymentPlanBO.getExtensionDays() == 0){
				overdueRepaymentLoanInfoList.add(repaymentLoanInfo);
				continue;
			}
			if(curDate.compareTo(DateTimeUtil.date10(DateTime.parse(repaymentPlanBO.getRepaymentDate()).plusDays(repaymentPlanBO.getExtensionDays()))) <= 0){
				repaymentLoanInfoOnRepaymentDateList.add(repaymentLoanInfo);
			}else{
				overdueRepaymentLoanInfoList.add(repaymentLoanInfo);
			}
		}
		logger.info("还款提醒-获取逾期的放款信息结束");
	}
	
	private Runnable createRemindTaskBeforeRepaymentDate(final RepaymentLoanInfo repaymentLoanInfo) {
		return new Runnable() {
			@Override
			public void run() {
				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
				CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentLoanInfo.getFinanceId());
				if(null == customerDetailDTO){
					logger.info("该用户不存在");
					return;
				}
				try{
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					smsMessageParamList.add(repaymentLoanInfo.getId());
					smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(loanInfoBO.lazyLoadPlans().get(0).totalAccountAmount()));
					smsMessageParamList.add(DateTimeUtil.convertDateToString("yyyy年MM月dd日", DateTime.parse(repaymentLoanInfo.getDueDate())));
					sendSmsMessage(generateMessageContent(SmsMessageTemplate.tz_repayment_remind_before_repaymentdate_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
				
				try{
					// 组装邮件相关参数并发送
					List<String> mailMessageParamList = Lists.newArrayList();
					mailMessageParamList.add(customerDetailDTO.getCompanyName());
					mailMessageParamList.add(repaymentLoanInfo.getId());
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(loanInfoBO.lazyLoadPlans().get(0).totalAccountAmount()));
					mailMessageParamList.add(DateTimeUtil.convertDateToString("yyyy年MM月dd日", DateTime.parse(repaymentLoanInfo.getDueDate())));
					mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + repaymentLoanInfo.getId()));
					mailMessageParamList.add(MailMessageTemplate.cana_phone);
					sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(MailMessageTemplate.
							tz_repayment_remind_before_repaymentdate_mail_message_content_template, mailMessageParamList), MailMessageTemplate.tz_repayment_remind_mail_message_subject_template);
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
			}
		};
	}
	
	private Runnable createRemindTaskOnRepaymentDate(final RepaymentLoanInfo repaymentLoanInfo) {
		return new Runnable() {
			@Override
			public void run() {
				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
				CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentLoanInfo.getFinanceId());
				if(null == customerDetailDTO){
					logger.info("该用户不存在");
					return;
				}
				try{
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					smsMessageParamList.add(DateTimeUtil.convertDateToString("yyyy年MM月dd日", DateTime.parse(commonService.getCurrentDate())));
					smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(loanInfoBO.lazyLoadPlans().get(0).totalExtensionAmount()));
					sendSmsMessage(generateMessageContent(SmsMessageTemplate.tz_repayment_remind_on_repaymentdate_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
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
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(loanInfoBO.lazyLoadPlans().get(0).totalExtensionAmount()));
					mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + repaymentLoanInfo.getId()));
					mailMessageParamList.add(MailMessageTemplate.cana_phone);
					sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(MailMessageTemplate.
							tz_repayment_remind_on_repaymentdate_mail_message_content_template, mailMessageParamList), MailMessageTemplate.tz_repayment_remind_mail_message_subject_template);
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
				
			}
		};
	}
	
	private Runnable createOverdueRemindTask(final RepaymentLoanInfo repaymentLoanInfo) {
		return new Runnable() {
			@Override
			public void run() {
				RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
				CustomerDetailDTO customerDetailDTO = userApi.queryCustomerDetail(repaymentLoanInfo.getFinanceId());
				if(null == customerDetailDTO){
					logger.info("该用户不存在");
					return;
				}
				try{
					// 获取短信相关参数并组装并发送
					List<String> smsMessageParamList = Lists.newArrayList();
					smsMessageParamList.add(customerDetailDTO.getCompanyName());
					smsMessageParamList.add(repaymentLoanInfo.getId());
					smsMessageParamList.add(MoneyArithUtil.convertMoneyToString(loanInfoBO.lazyLoadPlans().get(0).totalOverdueAccountAmount()));
					smsMessageParamList.add(MoneyArithUtil.convertInterestRateToString(loanInfoBO.lazyLoadRepaymentConfig().getPenaltyRate()));
					sendSmsMessage(generateMessageContent(SmsMessageTemplate.tz_overdue_repayment_remind_sms_message_content_template, smsMessageParamList), customerDetailDTO.getContactTel());
				} catch(WebException e){
					logger.error(e.getMessage(), e);
				} catch(Exception e){
					logger.error("未知异常", e);
				}
				try{
					// 组装邮件相关参数并发送
					List<String> mailMessageParamList = Lists.newArrayList();
					mailMessageParamList.add(customerDetailDTO.getCompanyName());
					mailMessageParamList.add(DateTime.parse(commonService.getCurrentDate()).toString("MM月dd日"));
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(loanInfoBO.lazyLoadPlans().get(0).totalPaid()));
					mailMessageParamList.add(MoneyArithUtil.convertMoneyToString(loanInfoBO.lazyLoadPlans().get(0).totalOverdueAccountAmount()));
					mailMessageParamList.add(MoneyArithUtil.convertInterestRateToString(loanInfoBO.lazyLoadRepaymentConfig().getPenaltyRate()));
					if(DateTime.parse(loanInfoBO.getDueDate()).plusDays(3).toString("yyyy-MM-dd").compareTo(commonService.getCurrentDate()) <= 0){
						mailMessageParamList.add(MailMessageTemplate.law_policy_v2);
					}else{
						mailMessageParamList.add(MailMessageTemplate.law_policy_v1);
					}
					mailMessageParamList.add(generateMailUrl(WebEnv.getVBAMPlatformPath() + "/loanInfo/manage/gotoRepaymentPlanDetails?loanId=" + repaymentLoanInfo.getId()));
					mailMessageParamList.add(MailMessageTemplate.cana_phone);
					sendMailMessage(customerDetailDTO.getContactMail(), generateMessageContent(MailMessageTemplate.tz_overdue_repayment_remind_mail_message_content_template, mailMessageParamList), MailMessageTemplate.tz_overdue_repayment_remind_mail_message_subject_template);
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
				template = template.replaceFirst(MailMessageTemplate.matchcharactersReg, dataItem);
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

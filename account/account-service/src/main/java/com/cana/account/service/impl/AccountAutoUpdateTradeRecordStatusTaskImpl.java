package com.cana.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.cana.account.dao.mapper.gen.AccountTradeRecordMapper;
import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.account.dao.po.AccountTradeRecordExample;
import com.cana.account.service.IAccountAutoUpdateTradeRecordStatusTask;
import com.cana.account.service.transaction.IAccountTradeTransactionService;
import com.cana.account.service.utils.BankgateHelperUtil;
import com.cana.bankgate.api.BankgateApi;
import com.cana.message.client.message.MessageClient;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.bankgate.dto.request.TradeStatusQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusDataDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.message.dto.MailMessageDTO;
import com.cana.vbam.common.message.enums.MailContentType;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.core.collection.LimitedQueue;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class AccountAutoUpdateTradeRecordStatusTaskImpl
        implements IAccountAutoUpdateTradeRecordStatusTask {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private BankgateApi bankgateApi;
    @Resource
    private AccountTradeRecordMapper accountTradeRecordMapper;
    @Resource
    private IAccountTradeTransactionService accountTradeTransactionService;
	@Resource
    private MessageClient messageClient;

	private final Gson gson =new Gson();

    private static final String catKeyPrefix = "账户请求网关";
    private static final String catKeyTaskType = catKeyPrefix + "查询交易状态";
    
    // 提现失败通知邮件邮箱获取地址
 	private static final String WITHDRAW_FAIL_NOTIFY_MAIL_ADDRESS_PATH = "properties/withdraw-fail.properties";
	
    @Override
    public String runTask() {
        StopWatch stopWatch = new StopWatch("tradeRecordStatusUpdateTask");
        stopWatch.start("queryUncompleteTradeRecords");
        List<String> uncompleteStatus = Lists.newArrayList(
                AccountTradeStatus.BANKAPI_REQUEST.name(),
                AccountTradeStatus.TRADE_HANDLING.name());
        AccountTradeRecordExample example = new AccountTradeRecordExample();
        example.createCriteria().andStatusIn(uncompleteStatus);
        List<AccountTradeRecord> records = accountTradeRecordMapper.selectByExample(example);
        stopWatch.stop();

        stopWatch.start("updateTradeRecordStatus");
        String notifyAddress = StringUtils.trimToEmpty(TopsConfReader.getConfContent(WITHDRAW_FAIL_NOTIFY_MAIL_ADDRESS_PATH, "notifyMailAddress", ConfScope.R));
        if (CollectionUtils.isNotEmpty(records)) {

    		ExecutorService executorService = getThreadPool();
        	for (AccountTradeRecord record : records) {
        		executorService.execute(createTask(record, notifyAddress));
        	}
        	executorService.shutdown();
    		try {
				executorService.awaitTermination(3, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				logger.info("线程池中断错误", e);
			}
        }
        stopWatch.stop();
        String executeDetail = stopWatch.toString();
        logger.info(executeDetail);
        return executeDetail;
    }

    private Runnable createTask(final AccountTradeRecord record, final String notifyAddress) {
		return new Runnable() {

			@Override
			public void run() {
            	Transaction t = Cat.newTransaction("scheduler", catKeyTaskType);
            	MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
        	    t.addData("traceId", MDC.get(Constants.TRACE_ID));
				try {
					TradeStatusQueryDTO queryDTO = new TradeStatusQueryDTO();
					queryDTO.setBusinessSeq(record.getBusinessSeq());
					TradeStatusResultDTO result = bankgateApi.queryTradeStatus(queryDTO);
					
					BankTranStatus queryStatus = result.getStatus();
					List<TradeStatusDataDTO> tradeData = result.getTradeStatusDatas();
					if (BankTranStatus.success.equals(queryStatus) && CollectionUtils.isNotEmpty(tradeData)) {
						t.setStatus(Transaction.SUCCESS);
						Cat.logMetricForCount(catKeyTaskType + "成功");
						AccountTradeStatus tradeStatus = BankgateHelperUtil.parseStatus(tradeData.get(0).getStatus());
						if (!tradeStatus.name().equals(record.getStatus())) {
							logger.info("交易流水[{}]状态由[{}]变为[{}]", record.getBusinessSeq(), record.getStatus(), tradeStatus.name());
							accountTradeTransactionService.updateTradeRecordStatus(record.getBusinessSeq(), tradeStatus);
							if(StringUtils.equals(record.getTradeType(), AccountTradeType.WITHDRAW_FUND.name()) && StringUtils.equals(AccountTradeStatus.TRADE_FAIL.name(), tradeStatus.name())){
								MailMessageDTO mailMessageDTO = new MailMessageDTO();
								mailMessageDTO.setContentType(MailContentType.HTML);
								mailMessageDTO.setReceiver(notifyAddress);
								mailMessageDTO.setSubject("【提现失败提醒】");
								mailMessageDTO.setContent("您好，" + record.getAccountName() + "公司＊"+ record.getAccountNo().substring(record.getAccountNo().length() - 4) + "帐户于" + 
										DateTimeUtil.datetime(record.getTradeStartTime().getTime()) +"发起提现"+ MoneyArithUtil.convertMoneyToString(Math.abs(record.getAmount())) +"元的操作失败，流水号"+ record.getBusinessSeq() + 
										"，请重新发起操作指令，谢谢");
								messageClient.sendMail(mailMessageDTO);
							}
						}
					} else {
						Cat.logMetricForCount(catKeyTaskType + "失败");
						logger.info("查询交易流水状态失败，网关返回结果序列为：{}", gson.toJson(result));
					}

				} catch (WebException e) {
					// 距离创建交易开始超过一定时间，而网关记录不存在，则认为是交易失败
					if (e.getRetCode() == ReturnCode.NOT_FOUND) {
						if (120 < DateTimeUtil.diffInSec(new Date(), record.getTradeStartTime())) {
							logger.warn("交易流水[{}]因网关不存在，记为失败", record.getBusinessSeq());
							accountTradeTransactionService.updateTradeRecordStatus(record.getBusinessSeq(), AccountTradeStatus.TRADE_FAIL);
							if(StringUtils.equals(record.getTradeType(), AccountTradeType.WITHDRAW_FUND.name())) {
								MailMessageDTO mailMessageDTO = new MailMessageDTO();
								mailMessageDTO.setContentType(MailContentType.HTML);
								mailMessageDTO.setReceiver(notifyAddress);
								mailMessageDTO.setSubject("【提现失败提醒】");
								mailMessageDTO.setContent("您好，" + record.getAccountName() + "公司＊"+ record.getAccountNo().substring(record.getAccountNo().length() - 4) + "帐户于" + 
										DateTimeUtil.datetime(record.getTradeStartTime().getTime()) +"发起提现"+ MoneyArithUtil.convertMoneyToString(Math.abs(record.getAmount())) +"元的操作失败，流水号"+ record.getBusinessSeq() + 
										"，请重新发起操作指令，谢谢");
								messageClient.sendMail(mailMessageDTO);
							}
						}
					}
					logger.error("查询交易状态异常", e);
				} catch (Exception e) {
					Cat.getProducer().logError(e);
					t.setStatus(e);
					Cat.logMetricForCount(catKeyTaskType + "失败");
                    logger.error("更新交易流水状态失败：{}", record.getBusinessSeq(), e);
                }
            }
		};
    }

    @Deprecated
    static class TradeRecordStatusUpdateTask implements Runnable {
        private final Logger logger = LoggerFactory.getLogger(getClass());
        private final Gson gson =new Gson();
        
        private final CountDownLatch doneSignal;
        private final ConcurrentLinkedQueue<AccountTradeRecord> tradeRecordQueue;
        private final BankgateApi bankgateApi;
        private final IAccountTradeTransactionService accountTradeTransactionService;
        private final MessageClient messageClient;
        private final String notifyAddress;
        
        public TradeRecordStatusUpdateTask(CountDownLatch doneSignal,
                ConcurrentLinkedQueue<AccountTradeRecord> records,
                BankgateApi bankgateApi,
                IAccountTradeTransactionService accountTradeTransactionService,
                MessageClient messageClient,
                String notifyAddress) {
            this.doneSignal = doneSignal;
            this.tradeRecordQueue = records;
            this.bankgateApi = bankgateApi;
            this.accountTradeTransactionService = accountTradeTransactionService;
            this.messageClient = messageClient;
            this.notifyAddress = notifyAddress;
            
        }

        @Override
        public void run() {
            AccountTradeRecord record = null;
            while ((record = tradeRecordQueue.poll()) != null) {
            	Transaction t = Cat.newTransaction("scheduler", catKeyTaskType);
        	    t.addData("traceId", MDC.get(Constants.TRACE_ID));
				try {
					TradeStatusQueryDTO queryDTO = new TradeStatusQueryDTO();
					queryDTO.setBusinessSeq(record.getBusinessSeq());
					TradeStatusResultDTO result = bankgateApi.queryTradeStatus(queryDTO);
					
					BankTranStatus queryStatus = result.getStatus();
					List<TradeStatusDataDTO> tradeData = result.getTradeStatusDatas();
					if (BankTranStatus.success.equals(queryStatus) && CollectionUtils.isNotEmpty(tradeData)) {
						t.setStatus(Transaction.SUCCESS);
						Cat.logMetricForCount(catKeyTaskType + "成功");
						AccountTradeStatus tradeStatus = BankgateHelperUtil.parseStatus(tradeData.get(0).getStatus());
						if (!tradeStatus.name().equals(record.getStatus())) {
							logger.info("交易流水[{}]状态由[{}]变为[{}]", record.getBusinessSeq(), record.getStatus(), tradeStatus.name());
							accountTradeTransactionService.updateTradeRecordStatus(record.getBusinessSeq(), tradeStatus);
							if(StringUtils.equals(record.getTradeType(), AccountTradeType.WITHDRAW_FUND.name()) && StringUtils.equals(AccountTradeStatus.TRADE_FAIL.name(), tradeStatus.name())){
								MailMessageDTO mailMessageDTO = new MailMessageDTO();
								mailMessageDTO.setContentType(MailContentType.HTML);
								mailMessageDTO.setReceiver(notifyAddress);
								mailMessageDTO.setSubject("【提现失败提醒】");
								mailMessageDTO.setContent("您好，" + record.getAccountName() + "公司＊"+ record.getAccountNo().substring(record.getAccountNo().length() - 4) + "帐户于" + 
										DateTimeUtil.datetime(record.getTradeStartTime().getTime()) +"发起提现"+ MoneyArithUtil.convertMoneyToString(Math.abs(record.getAmount())) +"元的操作失败，流水号"+ record.getBusinessSeq() + 
										"，请重新发起操作指令，谢谢");
								messageClient.sendMail(mailMessageDTO);
							}
						}
					} else {
						Cat.logMetricForCount(catKeyTaskType + "失败");
						logger.info("查询交易流水状态失败，网关返回结果序列为：");
						logger.info("{}",gson.toJson(result));
					}

				} catch (WebException e) {
					// 距离创建交易开始超过一定时间，而网关记录不存在，则认为是交易失败
					if (e.getRetCode() == ReturnCode.NOT_FOUND) {
						if (120 < DateTimeUtil.diffInSec(new Date(), record.getTradeStartTime())) {
							logger.warn("交易流水[{}]因网关不存在，记为失败", record.getBusinessSeq());
							accountTradeTransactionService.updateTradeRecordStatus(record.getBusinessSeq(), AccountTradeStatus.TRADE_FAIL);
							if(StringUtils.equals(record.getTradeType(), AccountTradeType.WITHDRAW_FUND.name())) {
								MailMessageDTO mailMessageDTO = new MailMessageDTO();
								mailMessageDTO.setContentType(MailContentType.HTML);
								mailMessageDTO.setReceiver(notifyAddress);
								mailMessageDTO.setSubject("【提现失败提醒】");
								mailMessageDTO.setContent("您好，" + record.getAccountName() + "公司＊"+ record.getAccountNo().substring(record.getAccountNo().length() - 4) + "帐户于" + 
										DateTimeUtil.datetime(record.getTradeStartTime().getTime()) +"发起提现"+ MoneyArithUtil.convertMoneyToString(Math.abs(record.getAmount())) +"元的操作失败，流水号"+ record.getBusinessSeq() + 
										"，请重新发起操作指令，谢谢");
								messageClient.sendMail(mailMessageDTO);
							}
						}
					}
					logger.error("查询交易状态异常", e);
				} catch (Exception e) {
					Cat.getProducer().logError(e);
					t.setStatus(e);
					Cat.logMetricForCount(catKeyTaskType + "失败");
                    logger.error("更新交易流水状态失败：{}", record.getBusinessSeq(), e);
                }
            }
            doneSignal.countDown();
        }
    }

    /**
	 * 创建线程池
	 */
	private ExecutorService getThreadPool() {
		int processorsOfCPU = Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2 * processorsOfCPU, 2 * processorsOfCPU, 5L,
				TimeUnit.MINUTES, new LimitedQueue<Runnable>(1),
				new CustomizableThreadFactory("update-trade-record-status-thread"));
		return threadPool;
	}
}

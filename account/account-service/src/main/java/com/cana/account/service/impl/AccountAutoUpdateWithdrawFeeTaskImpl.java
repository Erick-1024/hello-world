package com.cana.account.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import com.cana.account.dao.mapper.IQueryUnupdateWithdrawFeeMapper;
import com.cana.account.dao.mapper.gen.AccountTradeRecordMapper;
import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.account.service.IAccountAutoUpdateWithdrawFeeTask;
import com.cana.account.service.transaction.IAccountTradeTransactionService;
import com.cana.bankgate.api.BankgateApi;
import com.cana.vbam.common.bankgate.dto.request.BankAccountTradeFlowQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.core.time.DateTimeUtil;

@Service
public class AccountAutoUpdateWithdrawFeeTaskImpl implements IAccountAutoUpdateWithdrawFeeTask {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
    private BankgateApi bankgateApi;
	
    @Resource
    private AccountTradeRecordMapper accountTradeRecordMapper;
    
    @Resource
    private IQueryUnupdateWithdrawFeeMapper queryUnUpdateWithdrawFeeMapper;
    
    @Resource
    private IAccountTradeTransactionService accountTradeTransactionService;
    
    private static ExecutorService executor = Executors.newCachedThreadPool();
    
    private static final String catKeyTaskType = "查询提现手续费";

	@Override
	public String runTask() {
		StopWatch stopWatch = new StopWatch("accountAutoUpdateWithdrawFeeTask");
	    stopWatch.start("queryUnupdateWithdrawFeeRecords");
	    List<AccountTradeRecord> tradeList = queryUnUpdateWithdrawFeeMapper.queryUnpdateWithdrawRecord();
	    stopWatch.stop();

        stopWatch.start("updateWithdrawFee");
        executor.execute(new AccountAutoUpdateWithdrawFeeTask(tradeList, bankgateApi, accountTradeTransactionService));
        stopWatch.stop();
        String executeDetail = stopWatch.toString();
        logger.info(executeDetail);
		return executeDetail;
	}

	
	static class AccountAutoUpdateWithdrawFeeTask implements Runnable {
		
		private final Logger logger = LoggerFactory.getLogger(getClass());
        
        private final List<AccountTradeRecord> accountTradeRecordList;
        private final BankgateApi bankgateApi;
        private final IAccountTradeTransactionService accountTradeTransactionService;

		public AccountAutoUpdateWithdrawFeeTask(List<AccountTradeRecord> accountTradeRecordList, BankgateApi bankgateApi, IAccountTradeTransactionService accountTradeTransactionService) {
			super();
			this.accountTradeRecordList = accountTradeRecordList;
			this.bankgateApi = bankgateApi;
			this.accountTradeTransactionService = accountTradeTransactionService;
		}

		@Override
		public void run() {
			AccountTradeRecord tradeRecord = new AccountTradeRecord();
			Transaction t = Cat.newTransaction("scheduler", catKeyTaskType);
    	    t.addData("traceId", MDC.get(Constants.TRACE_ID));
			try {
				if(CollectionUtils.isEmpty(accountTradeRecordList)){
					logger.info("没有可处理的提现记录...");
					return ;
				}
				for(AccountTradeRecord record : accountTradeRecordList){
					tradeRecord.setBusinessSeq(record.getBusinessSeq());
					// 生成查询DTO
					BankAccountTradeFlowQueryDTO dto = new BankAccountTradeFlowQueryDTO();
				    dto.setAccountNo(record.getAccountNo());
				    dto.setStartDate(DateTimeUtil.date8(record.getTradeStartTime()));
				    dto.setEndDate(DateTimeUtil.date8(record.getTradeEndTime()));
				    // 获取详细信息
				    BankAccountTradeFlowResultDTO bankAccountTradeFlow = bankgateApi.queryBankAccountTradeFlow(dto);
				    if(!StringUtils.equals(BankTranStatus.success.name(), bankAccountTradeFlow.getStatus().name())){
				    	logger.info("获取交易信息异常，异常原因： --> {}", bankAccountTradeFlow.getStatusText());
				    }
				    if(CollectionUtils.isEmpty(bankAccountTradeFlow.getBankAccountTradeFlowDatas())){
				    	logger.info("账号 --> {}", record.getAccountNo() , "开始时间 --> {}", DateTimeUtil.date8(record.getTradeStartTime()), "结束时间 --> ", DateTimeUtil.date8(record.getTradeEndTime()), "没有交易记录." );
				    	continue;
				    }
				    for(BankAccountTradeFlowDataDTO tradeDTO : bankAccountTradeFlow.getBankAccountTradeFlowDatas()){
				    	if(StringUtils.equals(record.getOppositeAccountNo(), tradeDTO.getOppositeAccountNo()) &&
				    				Math.abs(record.getAmount()) ==  tradeDTO.getAmount()){
				    		accountTradeTransactionService.insertWithdrawFeeRecord(record, tradeDTO);
				    	}
				    }
				}
				t.setStatus(Transaction.SUCCESS);
			} catch (Exception e) {
				Cat.getProducer().logError(e);
				t.setStatus(e);
				Cat.logMetricForCount(catKeyTaskType + "失败");
                logger.error("更新提现交易手续费失败：{}", tradeRecord.getBusinessSeq(), e);
            }
		}
		
	}
}

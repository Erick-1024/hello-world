package com.cana.credit.scheduler.schedulers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cana.account.api.IAccountApi;
import com.cana.asset.api.IAssetApi;
import com.cana.credit.dao.po.CreditTransfer;
import com.cana.credit.scheduler.constants.Constant;
import com.cana.credit.service.ICreditTradeService;
import com.cana.credit.service.transaction.ICreditTradeTransactionService;
import com.cana.credit.service.utils.CreditTransferThreadPoolExecutor;
import com.cana.repayment.service.IMessageService;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.asset.dto.ContractQueryCriteria;
import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.vbam.common.credit.enums.CreditTransferType;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.travelzen.framework.core.common.ReturnCode;

@Service
public class UpdateTransferStatusScheduler {
	
	@Resource
	private IAccountApi accountApiImpl;
	
	@Resource
	private ICreditTradeTransactionService creditTradeTransactionServiceImpl;
	
	@Resource
	private ICreditTradeService creditTradeServiceImpl;
	
	@Resource
	private CreditTransferThreadPoolExecutor creditTransferThreadPoolExecutor;
	
	@Resource
	private IMessageService messageService;
	
	@Resource
	private IAssetApi assetApiImpl;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String catSuccess = "授信转账成功";
	
	private final String catFail = "授信转账失败";
	
	@Scheduled(fixedDelay = DateUtils.MILLIS_PER_SECOND)
	public void doTask() {
		try {
			List<CreditTransfer> creditTransfers = creditTradeServiceImpl.getHandlingCreditTrade();
			for (CreditTransfer creditTransfer : creditTransfers) {
				MDC.put(Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
				String status = accountApiImpl.queryTradeStatus(creditTransfer.getBusinessSeq());
				logger.info("查询授信转账记录{}的最新转账状态为：{}", creditTransfer.getId(), status);
				if(CreditTransferType.REFUND2FACTOR.name().equals(creditTransfer.getTransferType()) || CreditTransferType.AGENT_REPAYMENT.name().equals(creditTransfer.getTransferType())) {
					if(AccountTradeStatus.TRADE_SUCCESS.name().equals(status)) {
						Map<Object, Object> extra = new HashMap<>();
						if(CreditTransferType.REFUND2FACTOR.name().equals(creditTransfer.getTransferType())){
							CreditTransfer refundMore = creditTradeTransactionServiceImpl.creditRefundSuccess(creditTransfer, extra, getToAccountNo(creditTransfer.getCreditTradeId()));
							if (refundMore != null)
								creditTransferThreadPoolExecutor.doTransfer(refundMore, null);
						}
						else if(CreditTransferType.AGENT_REPAYMENT.name().equals(creditTransfer.getTransferType())) 
							creditTradeTransactionServiceImpl.creditAgentRepaymentSuccess(creditTransfer);
						Cat.logMetricForCount(catSuccess);
					} else if (AccountTradeStatus.TRADE_FAIL.name().equals(status) || (ReturnCode.NOT_FOUND.getRetCode().equals(status) && (new Date().getTime() - creditTransfer.getTransferStartTime().getTime()) > Constant.transferTimeout)) {
						creditTradeTransactionServiceImpl.creditTransferFail(creditTransfer);
						Cat.logMetricForCount(catFail);					
					}
				}else {
					if(AccountTradeStatus.TRADE_SUCCESS.name().equals(status)) {
						creditTradeTransactionServiceImpl.updateCreditTransferStatus(creditTransfer, CreditTransferStatus.SUCCESS);
						Cat.logMetricForCount(catSuccess);					
					}
					else if (AccountTradeStatus.TRADE_FAIL.name().equals(status) || (ReturnCode.NOT_FOUND.getRetCode().equals(status) && (new Date().getTime() - creditTransfer.getTransferStartTime().getTime()) > Constant.transferTimeout)) {
						creditTradeTransactionServiceImpl.updateCreditTransferStatus(creditTransfer, CreditTransferStatus.FAIL);
						Cat.logMetricForCount(catFail);					
					}
				}
				MDC.clear();
			}
		} catch (Exception e) {
			logger.error("更新授信转账状态失败", e);
			Cat.logMetricForCount(catFail);	
		}
	}
	
	private String getToAccountNo(String creditTradeId) {
		ContractQueryCriteria contractQueryCriteria = new ContractQueryCriteria();
		contractQueryCriteria.setMemberId(creditTradeServiceImpl.getMemberIdById(creditTradeId));
		contractQueryCriteria.setProductId(Constants.TRAVELZEN_FINANCE_PRODUCT_ID);
		return assetApiImpl.getContractsWithoutPaging(contractQueryCriteria).get(0).getAccountNo();
	}
	
}

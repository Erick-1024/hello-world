package com.cana.yundaex.service.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cana.account.api.IAccountApi;
import com.cana.vbam.common.account.dto.WithdrawFundRequestDTO;
import com.cana.yundaex.dao.po.YundaexCreditTransfer;
import com.google.gson.Gson;
import com.travelzen.framework.core.util.MoneyUtil;

@Component
public class YundaexTransferThreadPoolExecutor {

	@Resource
	private IAccountApi accoutnApiImpl;

	private ThreadPoolExecutor transferThreadPool;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private final Gson gson = new Gson();

	{
		int processorsOfCPU = Runtime.getRuntime().availableProcessors();
		transferThreadPool = new ThreadPoolExecutor(processorsOfCPU, processorsOfCPU * 2, 2, TimeUnit.MINUTES,
				new LinkedBlockingQueue<Runnable>());
	}

	public void doTransfer(YundaexCreditTransfer creditTransfer) {
		transferThreadPool.execute(CreateCreditTransferTaskRunnable(creditTransfer));
	}

	private Runnable CreateCreditTransferTaskRunnable(final YundaexCreditTransfer creditTransfer) {
		return new Runnable() {

			@Override
			public void run() {
				WithdrawFundRequestDTO requestDTO = new WithdrawFundRequestDTO();
				requestDTO.setBusinessSeq(creditTransfer.getBusinessSeq());
				requestDTO.setAmount(new String(MoneyUtil.cent2Yuan(creditTransfer.getFee())));
				requestDTO.setAccountNo(creditTransfer.getFromAccountNo());
				requestDTO.setReceiveAccountName(creditTransfer.getToAccountName());
				requestDTO.setReceiveAccountNo(creditTransfer.getToAccountNo());
				requestDTO.setReceiveBankName(creditTransfer.getToAccountAddress());
				requestDTO.setLianHangNo(creditTransfer.getToLianHangNo());
				logger.info("通知网关实体卡提现："+ gson.toJson(requestDTO));
				accoutnApiImpl.withdrawFundForYundaEx(requestDTO);
			}
		};
	}

}

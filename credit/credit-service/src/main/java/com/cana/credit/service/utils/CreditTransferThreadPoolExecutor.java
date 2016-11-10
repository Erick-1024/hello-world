package com.cana.credit.service.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cana.account.api.IAccountApi;
import com.cana.credit.dao.po.CreditTransfer;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;

@Component
public class CreditTransferThreadPoolExecutor {
	
	@Resource
	private IAccountApi accoutnApiImpl;
	
	private ThreadPoolExecutor transferThreadPool;
	
	{
		int processorsOfCPU = Runtime.getRuntime().availableProcessors();
		transferThreadPool = new ThreadPoolExecutor(processorsOfCPU, processorsOfCPU * 2, 2, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
	}
	
	public void doTransfer(CreditTransfer creditTransfer, String bankRemark) {
		transferThreadPool.execute(createCreditTransferTaskRunnable(creditTransfer, bankRemark));
	}
	
	private Runnable createCreditTransferTaskRunnable(final CreditTransfer creditTransfer, final String bankRemark) {
		return new Runnable() {
			
			@Override
			public void run() {
				TransferFundForCreditRequestDTO transfeFundForCreditRequestDTO = new TransferFundForCreditRequestDTO();
				transfeFundForCreditRequestDTO.setAmount(creditTransfer.getFee());
				transfeFundForCreditRequestDTO.setBusinessSeq(creditTransfer.getBusinessSeq());
				transfeFundForCreditRequestDTO.setTransferInAccountNo(creditTransfer.getToAccountNo());
				transfeFundForCreditRequestDTO.setTransferOutAccountNo(creditTransfer.getFromAccountNo());
				transfeFundForCreditRequestDTO.setBankRemark(bankRemark);
				accoutnApiImpl.transferFundForCredit(transfeFundForCreditRequestDTO);
			}
		};
	}
	
}

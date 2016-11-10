package com.cana.repayment.scheduler.bank;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.account.api.IAccountApi;
import com.cana.bankgate.api.BankgateApi;
import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskItemMapper;
import com.cana.repayment.service.bo.RepaymentDailyBatchTaskItemBO;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.account.dto.DeductFundRequestDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.travelzen.framework.core.exception.CanRetryException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class EciticBank implements IBank{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BankgateApi bankgateApi = SpringApplicationContext.getApplicationContext().getBean(BankgateApi.class);
	
	private SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	private IAccountApi accountApi = SpringApplicationContext.getApplicationContext().getBean(IAccountApi.class);
	
	private RepaymentDailyBatchTaskItemMapper taskItemMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentDailyBatchTaskItemMapper.class);


	@Override
	public long getAccountBalance(String accountNo) throws Exception{
		BankAccountBalanceQueryDTO requestDTO = new BankAccountBalanceQueryDTO();
		requestDTO.setAccountNo(accountNo);
		logger.info("查询余额请求：" + new Gson().toJson(requestDTO));
		BankAccountBalanceResultDTO resultDTO = bankgateApi.queryAccountBalance(requestDTO);
		logger.info("查询余额响应：" + new Gson().toJson(resultDTO));
		if(resultDTO == null || CollectionUtils.isEmpty(resultDTO.getBankAccountBalanceDatas()))
			throw new Exception("查询账户余额失败");
		return resultDTO.getBankAccountBalanceDatas().get(0).getAvailableBalance();
	}

	@Override
	public long deductAmount(long amount, RepaymentLoanInfoBO loanInfoBO, RepaymentDailyBatchTaskItemBO taskItemBO) throws Exception{
		DeductFundRequestDTO requestDTO = new DeductFundRequestDTO();
		requestDTO.setAmount(amount);
		requestDTO.setBusinessSeq(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.BUSINESS_SEQ, 5));
		requestDTO.setTransferOutAccountNo(loanInfoBO.getAccountNo());
		requestDTO.setTransferInAccountNo(loanInfoBO.lazyLoadRepaymentConfig().getFactorTransferInAccountNo());
		taskItemBO.updateExtraData("businessSeq", requestDTO.getBusinessSeq());
		taskItemMapper.updateByPrimaryKey(taskItemBO);
		logger.info("账扣请求：" + new Gson().toJson(requestDTO));
		AccountTradeStatus tradeStatus = accountApi.deductFund(requestDTO);
		logger.info("账扣响应：" + tradeStatus);
		if(tradeStatus == AccountTradeStatus.TRADE_SUCCESS)
			return amount;
		if(tradeStatus == AccountTradeStatus.TRADE_FAIL)
			throw CanRetryException.instance("扣款失败");
		throw new Exception("扣款状态未明");
	}

	
}

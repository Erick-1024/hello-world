package com.cana.repayment.server.api.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.repayment.api.IRepaymentCalcApi;
import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.service.IActiveRepaymentService;
import com.cana.repayment.service.IRepaymentCalcService;
import com.cana.repayment.service.transaction.IRepaymentCalcTransactionService;
import com.cana.repayment.service.transaction.IRepaymentTransactionService;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.repayment.dto.LoanInfoRepaymentResult;
import com.cana.vbam.common.repayment.dto.PrepareRepaymentMergeResult;
import com.cana.vbam.common.repayment.dto.PrepareRepaymentResult;
import com.cana.vbam.common.repayment.dto.RepaymentRequest;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.utils.Constants;
import com.dianping.cat.Cat;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.util.MoneyUtil;

public class RepaymentCalcApi implements IRepaymentCalcApi {

	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Resource
	private IRepaymentCalcTransactionService repaymentCalcTransactionService;
	
	@Resource
	private IRepaymentCalcService repaymentCalcServiceImpl;

	@Resource
	private IRepaymentTransactionService repaymentTransactionServiceImpl;
	
	@Resource
	private IRepaymentServiceHelper serviceHelper;
	
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private RepaymentSingleCollectMapper repaymentSingleCollectMapper;
	
	@Resource
	private IActiveRepaymentService activeRepaymentService;
	
	@Resource 
	private LoanInfoConfigMapper loanInfoConfigMapper;
	
	@Override
	public PrepareRepaymentResult prepareRepayment(String loanInfoId, long repaymentAmount) throws Exception {
		return repaymentCalcTransactionService.prepareRepayment(loanInfoId, repaymentAmount);
	}

	@Override
	public PrepareRepaymentMergeResult prepareRepaymentMerge(String loanInfoId, long repaymentAmount) throws Exception {
		PrepareRepaymentResult prepareRepaymentResult = repaymentCalcTransactionService.prepareRepayment(loanInfoId, repaymentAmount);
		long paidPrincipal = prepareRepaymentResult.getPaidNormalPrincipal();
		long paidInterest = prepareRepaymentResult.getPaidNormalInterest() + prepareRepaymentResult.getPaidNormalServiceCharge();
		long paidOverdueAmount = prepareRepaymentResult.getPaidOverduePrincipal() + prepareRepaymentResult.getPaidOverdueInterest()
						+ prepareRepaymentResult.getPaidOverdueServiceCharge() + prepareRepaymentResult.getPaidOverduePrincipalPenalty()
						+ prepareRepaymentResult.getPaidOverdueInterestPenalty() + prepareRepaymentResult.getPaidOverdueServiceChargePenalty()
						+ prepareRepaymentResult.getPaidOtherPenalty();
		long paidServiceCharge = prepareRepaymentResult.getPaidEarlyRepaymentCharge() + prepareRepaymentResult.getPaidExtensionCharge();
		
		PrepareRepaymentMergeResult prepareRepaymentMergeResult = new PrepareRepaymentMergeResult();
		prepareRepaymentMergeResult.setPaidPrincipal(MoneyUtil.cent2Yuan(paidPrincipal));
		prepareRepaymentMergeResult.setPaidInterest(MoneyUtil.cent2Yuan(paidInterest));
		prepareRepaymentMergeResult.setPaidOverdueAmount(MoneyUtil.cent2Yuan(paidOverdueAmount));
		prepareRepaymentMergeResult.setPaidServiceCharge(MoneyUtil.cent2Yuan(paidServiceCharge));
		
		return prepareRepaymentMergeResult;
	}

	@Override
	public String calcMinimumRepaymentAmount(String loanInfoId) throws Exception{
		long minAmount = repaymentCalcServiceImpl.calcMinimumRepaymentAmount(loanInfoId);
		if(minAmount==0)
			minAmount+=1;//最少为0.01元
		return MoneyUtil.cent2Yuan(minAmount);
	}

	@Override
	public String calcMaximumRepaymentAmount(String loanInfoId) throws Exception {
		return MoneyUtil.cent2Yuan(repaymentCalcServiceImpl.calcMaximumRepaymentAmount(loanInfoId));
	}

	@Override
	public LoanInfoRepaymentResult repayment(String accountNo,String loanInfoId,String amount) throws Exception {
		Long amountLong = MoneyUtil.yuan2Cent(amount);
		//校验还款金额
		long miniRepaymentAmount = repaymentCalcServiceImpl.calcMinimumRepaymentAmount(loanInfoId);
		if(amountLong < miniRepaymentAmount)
			throw WebException.instance(ReturnCode.ERROR, "还款金额小于最低还款金额");
		long maxiRepaymentAmount = repaymentCalcServiceImpl.calcMaximumRepaymentAmount(loanInfoId);
		if(amountLong > maxiRepaymentAmount)
			throw WebException.instance(ReturnCode.ERROR, "还款金额大于最大还款金额");
		
		LoanInfoRepaymentResult loanInfoRepaymentResult = null;
		//转账
		try {
			String transferInAccountNo = loanInfoConfigMapper.selectByPrimaryKey(loanInfoId).getFactorTransferInAccountNo();
			Map<String, String> accountTradeStatusAndBusinessSeq = activeRepaymentService.getAccountTradeStatusAndBusinessSeq(accountNo, loanInfoId, transferInAccountNo, amount);
			if(StringUtils.equals(AccountTradeStatus.TRADE_SUCCESS.name(), accountTradeStatusAndBusinessSeq.get(Constants.ACCOUNT_TRADE_STATUS))){
				//还款逻辑
				RepaymentRequest repaymentRequest = new RepaymentRequest();
				repaymentRequest.setLoanId(loanInfoId);
				repaymentRequest.setRepaymentAmount(amountLong);
				repaymentRequest.setSendMessage(true);
				loanInfoRepaymentResult = repaymentTransactionServiceImpl.repayment(repaymentRequest,RepaymentMethod.ACTIVE);
				if(amountLong - loanInfoRepaymentResult.getActualRepaymentTotalAmount() > 0){
					Cat.logMetricForCount("platform_above_max_repayment_amount");
					logger.warn("还款金额大于最大还款金额", new Gson().toJson(loanInfoRepaymentResult));
					throw WebException.instance("还款失败，您可能多还了款，具体情况请联系cana管理员！");
				}
			}
			else if(AccountTradeStatus.TRADE_FAIL.name().equals(accountTradeStatusAndBusinessSeq.get(Constants.ACCOUNT_TRADE_STATUS)))
				throw WebException.instance("转账失败");
			else 
				throw WebException.instance("转账失败，请联系cana管理员！");
		} catch (WebException e) {
			throw WebException.instance(e.getMessage());
		} catch (Exception e) {
			throw WebException.instance("系统错误，请联系cana管理员！");
		}
		return loanInfoRepaymentResult;
	}


}

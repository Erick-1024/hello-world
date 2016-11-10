package com.cana.repayment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.account.api.IAccountApi;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanExample;
import com.cana.repayment.dao.po.RepaymentPlanExample.Criteria;
import com.cana.repayment.dao.po.manual.ActiveRepaymentExpense;
import com.cana.repayment.service.IActiveRepaymentService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.vbam.common.account.dto.TransferFundForCreditRequestDTO;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.util.MoneyUtil;

@Service
public class ActiveRepaymentServiceImpl implements IActiveRepaymentService {

	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;

	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private IAccountApi accountApi;


	@Override
	public boolean isRepaymentPlanReadyToRepay(RepaymentPlanBO planBO) throws Exception {
		if (SettleStatus.SETTLED == SettleStatus.valueOf(planBO.getSettleStatus()))
			return false;
		RepaymentLoanInfoBO repaymentLoanInfoBO = planBO.lazyLoadLoanInfoBO();
		boolean flag = repaymentLoanInfoBO.containNonAutoRepaymentPlans();
		RepaymentPlanExample example = new RepaymentPlanExample();
		Criteria criteria = example.createCriteria();
		criteria.andLoanInfoIdEqualTo(planBO.getLoanInfoId());
		criteria.andRepaymentPeriodEqualTo(planBO.getRepaymentPeriod() - 1);
		List<RepaymentPlan> lastRepaymentPlan = repaymentPlanMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(lastRepaymentPlan))
			return true && flag;
		if (SettleStatus.SETTLED == SettleStatus.valueOf(lastRepaymentPlan.get(0).getSettleStatus()))
			return true && flag;
		return false;
	}

	@Override
	public boolean isRepaymentExpenseReadyToPay(ActiveRepaymentExpense activeRepaymentExpense) throws Exception {
		if (StringUtils.equals(SettleStatus.SETTLED.name(), activeRepaymentExpense.getSettleStatus())) {
			return false;
		}
		RepaymentExpenseExample example = new RepaymentExpenseExample();
		example.createCriteria().andLoanInfoIdEqualTo(activeRepaymentExpense.getLoanInfoId());
		example.setOrderByClause("repayment_date");
		List<RepaymentExpense> repaymentExpenseList = repaymentExpenseMapper.selectByExample(example);
		for (RepaymentExpense repaymentExpense : repaymentExpenseList) {
			if (StringUtils.equals(repaymentExpense.getExpenseSubject(), activeRepaymentExpense.getExpenseSubject())
					&& StringUtils.equals(repaymentExpense.getRepaymentDate(),
							activeRepaymentExpense.getRepaymentDate())) {
				int index = repaymentExpenseList.indexOf(repaymentExpense);
				if (index <= 0) {
					return true;
				}
				RepaymentExpense repaymentExpenseLast = repaymentExpenseList.get(index - 1);
				if (StringUtils.equals(repaymentExpenseLast.getSettleStatus(), SettleStatus.UNSETTLE.name())) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Map<String,String> getAccountTradeStatusAndBusinessSeq(String accountNo, String loanInfoId, String transferInAccountNo, String amount) {
		Map<String, String> accountTradeStatusAndBusinessSeq = new HashMap<>();
		String businessSeq = accountApi.generateBusinessSeq();
		accountTradeStatusAndBusinessSeq.put(Constants.BUSINESS_SEQ, businessSeq);
		TransferFundForCreditRequestDTO transferFundRequest = new TransferFundForCreditRequestDTO();
		transferFundRequest.setBusinessSeq(businessSeq);
		transferFundRequest.setTransferOutAccountNo(AccountNoUtil.parseBankAccountNo(accountNo));
		transferFundRequest.setTransferInAccountNo(transferInAccountNo);
		transferFundRequest.setAmount(MoneyArithUtil.convertStringToMoney(MoneyUtil.parseMoney(amount)));
		accountTradeStatusAndBusinessSeq.put(Constants.ACCOUNT_TRADE_STATUS, accountApi.transferFundForCredit(transferFundRequest).name());
		return accountTradeStatusAndBusinessSeq;
	}
}

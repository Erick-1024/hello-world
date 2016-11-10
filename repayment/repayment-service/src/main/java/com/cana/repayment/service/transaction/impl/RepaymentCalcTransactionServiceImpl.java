package com.cana.repayment.service.transaction.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.bo.RepaymentPlanBO;
import com.cana.repayment.service.bo.RepaymentSingleDistributeDetailBO;
import com.cana.repayment.service.handler.RepaymentCalcFactory;
import com.cana.repayment.service.transaction.IRepaymentCalcTransactionService;
import com.cana.vbam.common.repayment.dto.PrepareRepaymentResult;
import com.cana.vbam.common.repayment.exception.RepaymentAmountNotEnoughException;
import com.cana.vbam.common.service.IVbamCommonService;
import com.travelzen.framework.core.util.NumberUtils;

@Service
public class RepaymentCalcTransactionServiceImpl implements IRepaymentCalcTransactionService {

	@Resource
	private RepaymentLoanInfoMapper loanInfoMapper;

	@Resource
	private IVbamCommonService commonService;

	@Override
	public PrepareRepaymentResult prepareRepayment(String loanInfoId, long repaymentAmount) throws Exception {
		if (StringUtils.isBlank(loanInfoId) || repaymentAmount <= 0)
			throw new IllegalArgumentException("参数不合法");

		RepaymentLoanInfo loaninfo = loanInfoMapper.selectByPrimaryKey(loanInfoId);
		if (loaninfo == null)
			throw new IllegalArgumentException("放款ID无效");
		
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(loaninfo);
		PrepareRepaymentResult result = new PrepareRepaymentResult();
		long actualRepaymentAmount;
		try {
			actualRepaymentAmount = RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).prepareRepayment(loanInfoBO, repaymentAmount,
					commonService.getCurrentDate());
		} catch (RepaymentAmountNotEnoughException e) {
			result.setRemainingAmount(-e.getDifferenceAmount());
			return result;
		}

		result.setRemainingAmount(repaymentAmount - actualRepaymentAmount);
		for (RepaymentPlanBO planBO : loanInfoBO.lazyLoadPlans()) {
			RepaymentSingleDistributeDetailBO allotBO = planBO.allotDetail();
			if (allotBO == null)
				continue;
			result.setPaidNormalPrincipal(result.getPaidNormalPrincipal() + NumberUtils.getValue(allotBO.getPayNormalPrincipal()));
			result.setPaidOverduePrincipal(result.getPaidOverduePrincipal() + NumberUtils.getValue(allotBO.getPayOverduePrincipal()));
			result.setPaidNormalInterest(result.getPaidNormalInterest() + NumberUtils.getValue(allotBO.getPayNormalInterest()));
			result.setPaidOverdueInterest(result.getPaidOverdueInterest() + NumberUtils.getValue(allotBO.getPayOverdueInterest()));
			result.setPaidNormalServiceCharge(result.getPaidNormalServiceCharge() + NumberUtils.getValue(allotBO.getPayNormalServiceCharge()));
			result.setPaidOverdueServiceCharge(result.getPaidOverdueServiceCharge() + NumberUtils.getValue(allotBO.getPayOverdueServiceCharge()));
			result.setPaidEarlyRepaymentCharge(result.getPaidEarlyRepaymentCharge() + NumberUtils.getValue(allotBO.getEarlyRepaymentCharge()));
			result.setPaidExtensionCharge(result.getPaidExtensionCharge() + NumberUtils.getValue(allotBO.getPayExtensionCharge()));
			result.setPaidOverduePrincipalPenalty(result.getPaidOverduePrincipalPenalty() + NumberUtils.getValue(allotBO.getPayOverduePrincipalPenalty()));
			result.setPaidOverdueInterestPenalty(result.getPaidOverdueInterestPenalty() + NumberUtils.getValue(allotBO.getPayOverdueInterestPenalty()));
			result.setPaidOverdueServiceChargePenalty(result.getPaidOverdueServiceChargePenalty() + NumberUtils.getValue(allotBO.getPayOverdueServiceChargePenalty()));
			result.setPaidOtherPenalty(result.getPaidOtherPenalty() + NumberUtils.getValue(allotBO.getPayOtherPenalty()));
		}
		return result;
	}
}

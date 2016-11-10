package com.cana.repayment.service.impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.repayment.service.IRepaymentCalcService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.handler.RepaymentCalcFactory;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.service.IVbamCommonService;


@Service
public class RepaymentCalcServiceImpl implements IRepaymentCalcService{

	@Resource
	private IVbamCommonService commonService;
	
	@Resource
	private IRepaymentServiceHelper serviceHelper;
	
	@Override
	public long calcMinimumRepaymentAmount(String loanInfoId) throws Exception {
		
		String repaymentDate10 = commonService.getCurrentDate();
		RepaymentLoanInfoBO loanInfoBO = serviceHelper.lockLoanInfoById(loanInfoId);
		
		return RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcMinimumRepaymentAmount(loanInfoBO,repaymentDate10);
	}

	@Override
	public long calcMaximumRepaymentAmount(String loanInfoId) throws Exception {
		String repaymentDate10 = commonService.getCurrentDate();
		RepaymentLoanInfoBO loanInfoBO = serviceHelper.lockLoanInfoById(loanInfoId);
		
		return RepaymentCalcFactory.getRepaymentCalc(loanInfoBO).calcMaximumRepaymentAmount(loanInfoBO,repaymentDate10);
	}

}

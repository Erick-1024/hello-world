package com.cana.repayment.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.IRepaymentReportTaskMapper;
import com.cana.repayment.dao.mapper.gen.LoanInfoConfigMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseExample;
import com.cana.repayment.dao.po.RepaymentExpenseExample.Criteria;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.service.IRepaymentReportTaskService;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.vbam.common.repayment.enums.SettleStatus;

@Service
public class RepaymentReportTaskServiceImpl implements IRepaymentReportTaskService {

    @Resource
    private IRepaymentReportTaskMapper repaymentReportTaskMapper;

    @Resource
    private RepaymentExpenseMapper repaymentExpenseMapper;

    @Resource
    private RepaymentPlanMapper repaymentPlanMapper;
    
	@Resource
	private LoanInfoConfigMapper loanInfoConfigMapper;

    @Override
    public List<RepaymentLoanInfoBO> getLoanInfoIdsByFactorIdOrFinanceId(String masterId) throws Exception {
    	List<RepaymentLoanInfoBO> repaymentLoanInfoBOList = new ArrayList<>(); 
    	List<RepaymentLoanInfo> repaymentLoanInfoList = repaymentReportTaskMapper.getLoanInfoIdsByFactorIdOrFinanceId(masterId);
    	if(CollectionUtils.isNotEmpty(repaymentLoanInfoList)){
    		for(RepaymentLoanInfo repaymentLoanInfo:repaymentLoanInfoList){
    			repaymentLoanInfoBOList.add(new RepaymentLoanInfoBO(repaymentLoanInfo));
    		}
    	}
    	return repaymentLoanInfoBOList;
    }

    @Override
    public List<RepaymentPlan> getRepaymentPlanByLoanInfoIdAndRepaymentDate(String loanInfoId, String repaymentDate)
	    throws Exception {
	return repaymentReportTaskMapper.getRepaymentPlanByLoanInfoIdAndRepaymentDate(loanInfoId, repaymentDate);
    }

    @Override
    public List<RepaymentExpense> getRepaymentExpenseBeforeOrEqualsCurrentDateAndUnsettle(String loanInfoId,
	    String currentDate) throws Exception {

	RepaymentExpenseExample repaymentExpenseExample = new RepaymentExpenseExample();
	Criteria criteria = repaymentExpenseExample.createCriteria();
	criteria.andLoanInfoIdEqualTo(loanInfoId);
//	criteria.andSettleStatusEqualTo(SettleStatus.UNSETTLE.name());
	List<RepaymentExpense> allRepaymentExpenses = repaymentExpenseMapper.selectByExample(repaymentExpenseExample);
	if (CollectionUtils.isEmpty(allRepaymentExpenses))
	    return null;

	List<RepaymentExpense> repaymentExpenses = new ArrayList<>();
	for (RepaymentExpense repaymentExpense : allRepaymentExpenses) {
	    if (DateTime.parse(currentDate).compareTo(DateTime.parse(repaymentExpense.getRepaymentDate())) > 0) {
	    	if(SettleStatus.UNSETTLE == SettleStatus.valueOf(repaymentExpense.getSettleStatus()))
	    		repaymentExpenses.add(repaymentExpense);
	    }
	    if (DateTime.parse(currentDate).compareTo(DateTime.parse(repaymentExpense.getRepaymentDate())) == 0)
	    	repaymentExpenses.add(repaymentExpense);
	}
	return repaymentExpenses;
    }

    @Override
    public List<RepaymentPlan> getRepaymentPlanByLoanInfoIdAndCurrentYear(String loanInfoId, String currentYear)
	    throws Exception {

	List<RepaymentPlan> repaymentPlans = repaymentReportTaskMapper.getReaymentPlansByLoanInfoId(loanInfoId);
	if (CollectionUtils.isEmpty(repaymentPlans))
	    return null;
	List<RepaymentPlan> repaymentPlansOfCurrentYear = new ArrayList<>();
	for (RepaymentPlan repaymentPlan : repaymentPlans) {
	    if (currentYear.equals(repaymentPlan.getRepaymentDate().substring(0, 4)))
		repaymentPlansOfCurrentYear.add(repaymentPlan);
	}
	return repaymentPlansOfCurrentYear;
    }

    @Override
    public List<RepaymentExpense> getRepaymentExpenseBeforeOrEqualsCurrentYearAndUnsettle(String loanInfoId,
	    String currentYear) throws Exception {
	RepaymentExpenseExample repaymentExpenseExample = new RepaymentExpenseExample();
	Criteria criteria = repaymentExpenseExample.createCriteria();
	criteria.andLoanInfoIdEqualTo(loanInfoId);
//	criteria.andSettleStatusEqualTo(SettleStatus.UNSETTLE.name());
	List<RepaymentExpense> allRepaymentExpenses = repaymentExpenseMapper.selectByExample(repaymentExpenseExample);
	if (CollectionUtils.isEmpty(allRepaymentExpenses))
	    return null;
	List<RepaymentExpense> repaymentExpenseBeforeOrEqualCurrentYear = new ArrayList<>();
	for (RepaymentExpense repaymentExpense : allRepaymentExpenses) {
	    if(Integer.parseInt(currentYear) > Integer.parseInt(repaymentExpense.getRepaymentDate().substring(0, 4))){
	    	if(SettleStatus.UNSETTLE == SettleStatus.valueOf(repaymentExpense.getSettleStatus()))
	    		repaymentExpenseBeforeOrEqualCurrentYear.add(repaymentExpense);
	    }
	    if(Integer.parseInt(currentYear) == Integer.parseInt(repaymentExpense.getRepaymentDate().substring(0, 4))){
	    		repaymentExpenseBeforeOrEqualCurrentYear.add(repaymentExpense);
	    }
	}
	return repaymentExpenseBeforeOrEqualCurrentYear;
    }

	@Override
	public List<RepaymentPlan> getAllRepaymentPlansByLoanInfoId(String loanInfoId) throws Exception {
		
		List<RepaymentPlan> repaymentPlans = repaymentReportTaskMapper.getReaymentPlansByLoanInfoId(loanInfoId);
		if (CollectionUtils.isEmpty(repaymentPlans))
		    return null;
		return repaymentPlans;
	}

	@Override
	public LoanInfoConfig getLoanInfoConfigByLoanInfoId(String loanInfoId) throws Exception {
		return loanInfoConfigMapper.selectByPrimaryKey(loanInfoId);
	}

}

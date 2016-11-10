package com.cana.repayment.service.transaction.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.RepaymentTableLockMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleDistributeDetailMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.data.OfflineRepaymentData;
import com.cana.repayment.service.transaction.IRepaymentAdjustmentTransactionService;
import com.travelzen.framework.core.exception.WebException;

@Service("repaymentAdjustmentTransactionService")
public class RepaymentAdjustmentTransactionService implements IRepaymentAdjustmentTransactionService {

	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	
	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private RepaymentTableLockMapper repaymentTableLockMapper;

	@Resource
	private RepaymentSingleCollectMapper repaymentSingleCollectMapper;

	@Resource
	private RepaymentSingleDistributeDetailMapper repaymentSingleDistributeDetailMapper;
	
	@Resource 
	private RepaymentTableLockMapper tableLockMapper;
	
	@Override
	public void updatePlanAndExpenseAndSaveRepaymentDetail(OfflineRepaymentData offlineRepaymentData) {
		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentTableLockMapper.lockRepaymentLoanInfoById(offlineRepaymentData.getLoanInfoId()));
		// 获取版本号并判断版本号是否一致
		if(!StringUtils.equals(loanInfoBO.getCurrentVersion(), offlineRepaymentData.getLoanInfoOldVersion())){
			throw WebException.instance("放款信息或计划被修改，还款失败");
		}
		// 更新还款计划表
		for(RepaymentPlan repaymentPlan:offlineRepaymentData.getRepaymentPlanList()){
			RepaymentPlan repaymentPlanOld = repaymentPlanMapper.selectByPrimaryKey(repaymentPlan.getId());
			repaymentPlan.setOtherPenalty(repaymentPlanOld.getOverduePrincipalPenalty() + repaymentPlanOld.getOverdueInterestPenalty() + repaymentPlanOld.getOverdueServiceChargePenalty() 
					+ repaymentPlanOld.getOtherPenalty() - (null == repaymentPlan.getOtherPenalty() ? 0 : repaymentPlan.getOtherPenalty()));
			repaymentPlan.setPaidOtherPenalty(repaymentPlanOld.getPaidOtherPenalty() + (null == repaymentPlan.getPaidOtherPenalty() ? 0 : repaymentPlan.getPaidOtherPenalty()));
			repaymentPlanMapper.updateByPrimaryKeySelective(repaymentPlan);
		}
		// 更新还款费用表
		for(RepaymentExpense repaymentExpense:offlineRepaymentData.getRepaymentExpenseList()){
			repaymentExpenseMapper.updateByPrimaryKeySelective(repaymentExpense);
		}
		// 新增还款汇总
		for(RepaymentSingleCollect repaymentSingleCollect:offlineRepaymentData.getRepaymentSingleCollectList()){
			repaymentSingleCollectMapper.insertSelective(repaymentSingleCollect);
		}
		// 新增还款费用明细
		for(RepaymentSingleDistributeDetail repaymentSingleDistributeDetail:offlineRepaymentData.getRepaymentSingleDistributeDetailList()){
			repaymentSingleDistributeDetailMapper.insertSelective(repaymentSingleDistributeDetail);
		}
		// 修改版本号
		loanInfoBO.setLastVersion(offlineRepaymentData.getLoanInfoOldVersion());
		loanInfoBO.setCurrentVersion(offlineRepaymentData.getLoanInfoVersion());
//		repaymentLoanInfo.setRepaymentExpenseVersion(offlineRepaymentData.getRepaymentExpenseVersion());
		loanInfoBO.setChangeId(offlineRepaymentData.getChangeId());
		loanInfoBO.setChangeType(offlineRepaymentData.getChangeType());
//		loanInfoBO = new RepaymentLoanInfoBO(loanInfoBO.getId());
		loanInfoBO.duplicate();
		repaymentLoanInfoMapper.updateByPrimaryKey(loanInfoBO);
		loanInfoBO.createSnapshot();
	}
}

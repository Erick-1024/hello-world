package com.cana.repayment.service.transaction.impl;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.repayment.dao.mapper.RepaymentTableLockMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentExpenseMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.service.bo.RepaymentLoanInfoBO;
import com.cana.repayment.service.data.OfflineRepaymentData;
import com.cana.repayment.service.transaction.IActiveRepaymentTransactionService;
import com.cana.repayment.service.util.IRepaymentServiceHelper;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentStatus;
import com.cana.vbam.common.repayment.enums.ActiveRepaymentType;
import com.cana.vbam.common.repayment.enums.RepaymentMethod;
import com.cana.vbam.common.service.impl.VbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service("activeRepaymentTransactionServiceImpl")
public class ActiveRepaymentTransactionServiceImpl implements
		IActiveRepaymentTransactionService {
	
	@Resource
	private RepaymentPlanMapper repaymentPlanMapper;
	
	@Resource
	private RepaymentExpenseMapper repaymentExpenseMapper;
	
	@Resource
	private RepaymentLoanInfoMapper repaymentLoanInfoMapper;
	
	@Resource
	private RepaymentTableLockMapper repaymentTableLockMapper;
	
	@Resource
	private IRepaymentServiceHelper serviceHelper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private RepaymentSingleCollectMapper singleCollectMapper;
	
	@Resource
	private VbamCommonService vbamCommonService;
	
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
			repaymentPlan.setOtherPenalty(repaymentPlanOld.getOtherPenalty() + (null == repaymentPlan.getOtherPenalty() ? 0 : repaymentPlan.getOtherPenalty()));
			repaymentPlanMapper.updateByPrimaryKeySelective(repaymentPlan);
		}
		// 更新还款费用表
		for(RepaymentExpense repaymentExpense:offlineRepaymentData.getRepaymentExpenseList()){
			repaymentExpenseMapper.updateByPrimaryKeySelective(repaymentExpense);
		}
		// 修改版本号
		loanInfoBO.setLastVersion(offlineRepaymentData.getLoanInfoOldVersion());
		loanInfoBO.setCurrentVersion(offlineRepaymentData.getLoanInfoVersion());
		loanInfoBO.setChangeType(offlineRepaymentData.getChangeType());
		loanInfoBO.setActiveRepaymentStatus(ActiveRepaymentStatus.complete_active_repayment.name());
//		RepaymentLoanInfoBO loanInfoBO = new RepaymentLoanInfoBO(repaymentLoanInfo);
		loanInfoBO.duplicate();
		repaymentLoanInfoMapper.updateByPrimaryKey(loanInfoBO);
		loanInfoBO.createSnapshot();
		// 向重试记录表插入数据
		serviceHelper.insertAdjustSuccessNotificationRetryTaskRecord(loanInfoBO);
	}
	
	public void updateOnActiveRepaymentSuccess(String loanInfoId, String amount, ActiveRepaymentType activeRepaymentType, String repaymentCertificate){
		RepaymentLoanInfo loanInfo = repaymentTableLockMapper.lockLoanInfoById(loanInfoId);
		loanInfo.setActiveRepaymentStatus(ActiveRepaymentStatus.already_active_repayment.name());
		
		HashMap<String, String> extraData = new HashMap<>();
		if(StringUtils.isNotBlank(loanInfo.getExtraData()))
			extraData = new Gson().fromJson(loanInfo.getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		extraData.put(Constants.REPAYMENT_LOAN_INFO_EXTRA_KEY_ACTIVE, amount);
		extraData.put(Constants.REPAYMENT_LOAN_INFO_EXTRA_KEY_ACTIVE_TYPE, activeRepaymentType.name());
		loanInfo.setExtraData(new Gson().toJson(extraData));
		
		repaymentLoanInfoMapper.updateByPrimaryKeySelective(loanInfo);
		
		RepaymentSingleCollect repaymentRecord = createActiveRepaymentRecord(loanInfoId, amount, repaymentCertificate);
		singleCollectMapper.insertSelective(repaymentRecord);
		
		serviceHelper.insertActiveRepaymentSuccessNotificationRetryTaskRecord(loanInfo, repaymentRecord);
	}
	
	private RepaymentSingleCollect createActiveRepaymentRecord(String loanInfoId, String amount, String repaymentCertificate){
		RepaymentSingleCollect repaymentRecord = new RepaymentSingleCollect();
		repaymentRecord.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_COLLECT_ID, 4));
		repaymentRecord.setLoanInfoId(loanInfoId);
		repaymentRecord.setRepaymentType(RepaymentMethod.ACTIVE.name());
		repaymentRecord.setRepaymentCertificate(repaymentCertificate);
		repaymentRecord.setRepaymentTotalAmount(MoneyArithUtil.convertStringToMoney(amount));
		repaymentRecord.setRepaymentDate(vbamCommonService.getCurrentDate());
		repaymentRecord.setCreateTime(new Date());
		return repaymentRecord;
	}
}

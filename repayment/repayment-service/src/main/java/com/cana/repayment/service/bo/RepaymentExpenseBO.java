package com.cana.repayment.service.bo;

import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.mapper.gen.RepaymentExpenseSnapshotMapper;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentExpenseSnapshot;
import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.vbam.common.repayment.enums.ChargeType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentExpenseBO extends RepaymentExpense{
	
	private static final long serialVersionUID = -2563379187223266249L;
	
	private SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	private RepaymentExpenseSnapshotMapper expenseSnapshotMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentExpenseSnapshotMapper.class);
	
	private RepaymentSingleDistributeDetailBO allotDetailBO;

	public RepaymentExpenseBO(RepaymentExpense po){
		BeanUtils.copyProperties(po, this);
	}

	/**
	 * 是否有应还未还的
	 * @param curDate10
	 * @return
	 */
	public boolean hasUnpaid(String curDate10) {
		return unpaid(curDate10) > 0;
	}
	
	/**
	 * 应还未还金额
	 * @param curDate10
	 * @return
	 */
	public Long unpaid(String curDate10){
		if(curDate10.compareTo(getRepaymentDate()) >= 0)
			return getRepaymentAmount();
		else return 0L; 
	}

	/**
	 * 是否全部结清
	 * @return
	 */
	public boolean isSettled() {
		return getRepaymentAmount() == 0;
	}

	/**
	 * 还费用
	 * @param remainingAmount
	 * @param deductDate10
	 * @return
	 */
	public long pay(long remainingAmount, String deductDate10) {
		createAllotDetailIfMissing();
		long paid = Math.min(remainingAmount, unpaid(deductDate10));
		setPaidAmount(getPaidAmount() + paid);
		setRepaymentAmount(getRepaymentAmount() - paid);
		allotDetailBO.setPayExpense(paid);
		remainingAmount -= paid;
		return remainingAmount;
	}
	
	/**
	 * 如果还款明细还不存在，创建
	 */
	public RepaymentSingleDistributeDetailBO createAllotDetailIfMissing() {
		if(allotDetailBO == null){
			allotDetailBO = new RepaymentSingleDistributeDetailBO(new RepaymentSingleDistributeDetail());
			allotDetailBO.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_DISTRIBUTE_DETAIL_ID, 4));
			allotDetailBO.setRelatedType(ChargeType.REPAYMENTEXPENSE.name());
			allotDetailBO.setRelatedId(getId());
		}
		return allotDetailBO;
	}
	
	/**
	 * 返回还款明细
	 * @return
	 */
	public RepaymentSingleDistributeDetailBO allotDetail(){
		return allotDetailBO;
	}

	/**
	 * 冗余数据
	 */
	public void duplicate() {
		if(isSettled())
			setSettleStatus(SettleStatus.SETTLED.name());
		else setSettleStatus(SettleStatus.UNSETTLE.name());
	}

	/**
	 * 创建固定费用的快照
	 * @param loanInfoSnapshot
	 */
	public void createSnapshot(RepaymentLoanInfoSnapshot loanInfoSnapshot) {
		RepaymentExpenseSnapshot expenseSnapshot = new RepaymentExpenseSnapshot();
		BeanUtils.copyProperties(this, expenseSnapshot);
		expenseSnapshot.setRepaymentExpenseId(this.getId());
		expenseSnapshot.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_EXPENSE_SNAPSHOT_ID, 4));
		expenseSnapshot.setLoanInfoSnapshotId(loanInfoSnapshot.getId());
		expenseSnapshotMapper.insertSelective(expenseSnapshot);
	}
	
}

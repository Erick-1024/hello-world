package com.cana.repayment.service.bo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.mapper.gen.RepaymentPlanMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentPlanSnapshotMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentPlanSnapshot;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.service.IRepositoryService;
import com.cana.vbam.common.repayment.enums.ChargeType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentPlanBO extends RepaymentPlan{

	private static final long serialVersionUID = -4213384847235482404L;
	
	private IRepositoryService repositoryService = SpringApplicationContext.getApplicationContext().getBean(IRepositoryService.class);
	
	private SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	private RepaymentPlanSnapshotMapper planSnapshotMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentPlanSnapshotMapper.class);
	
	private RepaymentLoanInfoBO loanInfoBO;
	
	private LoanInfoConfig repaymentConfig;
	
	private RepaymentSingleDistributeDetailBO allotDetailBO;
	
	public RepaymentPlanBO(String planId){
		this(SpringApplicationContext.getApplicationContext().getBean(RepaymentPlanMapper.class).selectByPrimaryKey(planId));
	}
	
	public RepaymentPlanBO(RepaymentPlan po){
		BeanUtils.copyProperties(po, this);
	}
	
	/**
	 * 懒加载还款规则
	 * @return
	 */
	public LoanInfoConfig lazyLoadRepaymentConfig(){
		if(repaymentConfig != null)
			return repaymentConfig;
		repaymentConfig = repositoryService.getRepaymentConfigByLoanInfoId(getLoanInfoId());
		return repaymentConfig;
	}

	/**
	 * 懒加载放款信息
	 * @return
	 */
	public RepaymentLoanInfoBO lazyLoadLoanInfoBO(){
		if(loanInfoBO != null)
			return loanInfoBO;
		loanInfoBO = new RepaymentLoanInfoBO(getLoanInfoId());
		return loanInfoBO;
	}
	
	/**
	 * 注入LoanInfoBO
	 * @param loanInfoBO
	 */
	public void injectLoanInfoBO(RepaymentLoanInfoBO loanInfoBO){
		this.loanInfoBO = loanInfoBO;
	}
	
	/**
	 * 该计划是否在逾期状态
	 * @param curDate10
	 * @return
	 */
	public boolean inOverdueState(String curDate10){
		return totalOverdueAmount() > 0;
	}
	
	/**
	 * 该计划是否是否在逾期(结清后使用)
	 * @param curDate10
	 * @return
	 */
	public boolean inOverdueStateAfterSettled(String curDate10){
		return getRepaymentDate().compareTo(curDate10) < 0;
	}
	
	/**
	 * 总的逾期金额
	 * @return
	 */
	public long totalOverdueAmount(){
		return totalOverduePenalty() + overdueExtensionCharge() + getOverdueServiceCharge() + getOverdueInterest() + getOverduePrincipal();
	}
	
	/**
	 * 逾期展期
	 * @return
	 */
	public long overdueExtensionCharge() {
	    if(hasAccount()) // 还有应还金额，代表此计划不算逾期
	    	return 0;
        return getAccountExtensionCharge();
	}
	
	/**
	 * 正常展期
	 * @return
	 */
	public long normalExtensionCharge() {
	    if(hasAccount()) // 还有应还金额，代表此计划不算逾期
	    	return getAccountExtensionCharge();
        return 0L;
	}

	/**
	 * 是否有应还未还
	 * @return
	 */
	public boolean hasAccount() {
		return totalAccountAmount() > 0;
	}

	/**
	 * 应还总额
	 * @return
	 */
	public long totalAccountAmount() {
		return getAccountPrincipal() + getAccountInterest() + getAccountServiceCharge();
	}

	/**
	 * 应还逾期金额
	 * @return
	 */
	public long totalOverdueAccountAmount() {
		return getOverduePrincipal() + getOverdueInterest() + getOverdueServiceCharge() + totalOverduePenalty() + getAccountExtensionCharge();
	}

	/**
	 * 总的逾期罚息
	 * @return
	 */
	public long totalOverduePenalty() {
		return getOverduePrincipalPenalty() + getOverdueInterestPenalty() + getOverdueServiceChargePenalty() + getOtherPenalty();
	}
	
	/**
	 * 总的已还逾期罚息
	 * @return
	 */
	public long totalPaidOverduePenalty() {
		return getPaidOverduePrincipalPenalty() + getPaidOverdueInterestPenalty() + getPaidOverdueServiceChargePenalty() + getPaidOtherPenalty();
	}

	/**
	 * 是否在展期状态
	 * @param curDate10
	 * @return
	 */
	public boolean inExtensionState(String curDate10) {
		return beyondRepaymentDate(curDate10) && hasAccount();
	}
	
	/**
	 * 是否在正常状态
	 * @param curDate10
	 * @return
	 */
	public boolean inNormalState(String curDate10) {
		return !beyondRepaymentDate(curDate10) && hasAccount();
	}

	/**
	 * 传入的日期是否过了固定还款日
	 * @param curDate10
	 * @return
	 */
	private boolean beyondRepaymentDate(String curDate10) {
		return curDate10.compareTo(getRepaymentDate()) > 0;
	}

	/**
	 * 是否在展期时间段内
	 * @param date10
	 * @return
	 */
	public boolean inExtensionTimeRange(String date10) {
		return allowExtension() && date10.compareTo(extensionStartDate()) >= 0 && date10.compareTo(extensionEndDate()) <= 0;
	}
	
	/**
	 * 是否允许展期
	 * @return
	 */
	public boolean allowExtension(){
		return getExtensionDays() > 0;
	}
	
	/**
	 * 展期开始日期（包含）
	 * @return
	 */
	public String extensionStartDate(){
		return DateTimeUtil.date10(DateTimeUtil.addDay(DateTimeUtil.parseDate10(getRepaymentDate()), 1));
	}
	
	/**
	 * 展期结束日期（包含）
	 * @return
	 */
	public String extensionEndDate(){
		return DateTimeUtil.date10(DateTimeUtil.addDay(DateTimeUtil.parseDate10(getRepaymentDate()), getExtensionDays()));
	}

	/**
	 * 传入的日期是否是固定还款日
	 * @param date10
	 * @return
	 */
	public boolean isRepaymentDate(String date10) {
		return date10.compareTo(getRepaymentDate()) == 0;
	}

	/**
	 * 是否在指定的日期生成逾期
	 * @param date10
	 * @return
	 * 
	 */
	public boolean inOverdueTimeRange(String date10) {
		return date10.compareTo(generateOverdueDate()) >= 0;
	}

	/**
	 * 返回此计划生成逾期的日期
	 * @return
	 */
	private String generateOverdueDate() {
		return allowExtension() ? DateTimeUtil.date10(DateTimeUtil.addDay(DateTimeUtil.parseDate10(extensionEndDate()), 1)) : DateTimeUtil.date10(DateTimeUtil.addDay(DateTimeUtil.parseDate10(getRepaymentDate()), 1));
	}

	/**
	 * 返回还款明细
	 * @return
	 */
	public RepaymentSingleDistributeDetailBO allotDetail(){
		return allotDetailBO;
	}

	/**
	 * 如果还款明细还不存在，创建
	 */
	public RepaymentSingleDistributeDetailBO createAllotDetailIfMissing() {
		if(allotDetailBO == null){
			allotDetailBO = new RepaymentSingleDistributeDetailBO(new RepaymentSingleDistributeDetail());
			allotDetailBO.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_SINGLE_DISTRIBUTE_DETAIL_ID, 4));
			allotDetailBO.setRelatedType(ChargeType.REPAYMENTPLAN.name());
			allotDetailBO.setRelatedId(getId());
		}
		return allotDetailBO;
	}

	/**
	 * 是否全部结清
	 * @return
	 */
	public boolean isSettled() {
		return (totalAccountAmount() + totalOverdueAmount()) == 0;
	}


	/**
	 * 所有已还金额
	 * @return
	 */
	public long totalPaid() {
		return getPaidNormalPrincipal() + getPaidNormalInterest() + getPaidNormalServiceCharge()
		       + getPaidOverduePrincipal() + getPaidOverdueInterest() + getPaidOverdueServiceCharge()
		       + getPaidOverduePrincipalPenalty() + getPaidOverdueInterestPenalty() + getPaidOverdueServiceChargePenalty() + getPaidOtherPenalty()
		       + getPaidExtensionCharge() + getPaidEarlyRepaymentCharge();
	}

	/**
	 * 冗余一些数据
	 */
	public void duplicate() {
		if(isSettled())
			setSettleStatus(SettleStatus.SETTLED.name());
		else setSettleStatus(SettleStatus.UNSETTLE.name());
	}

	/**
	 * 创建还款计划的快照
	 * @param loanInfoSnapshot
	 */
	public void createSnapshot(RepaymentLoanInfoSnapshot loanInfoSnapshot) {
		RepaymentPlanSnapshot planSnapshot = new RepaymentPlanSnapshot();
		BeanUtils.copyProperties(this, planSnapshot);
		planSnapshot.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_PLAN_SNAPSHOT_ID, 4));
		planSnapshot.setLoanInfoSnapshotId(loanInfoSnapshot.getId());
		planSnapshotMapper.insertSelective(planSnapshot);
	}

	/**
	 * 展期的还款计划所有应还金额
	 * @return
	 */
	public long totalExtensionAmount() {
		return totalAccountAmount() + getAccountExtensionCharge();
	}
	
	/**
	 * 将po列表转换为bo列表
	 * @param poList
	 * @return
	 */
	public static List<RepaymentPlanBO> poList2boList(List<RepaymentPlan> poList){
		List<RepaymentPlanBO> boList = new ArrayList<>();
		for(RepaymentPlan po : poList){
			boList.add(new RepaymentPlanBO(po));
		}
		return boList;
	}

	/**
	 * 获取某一期还款计划的融资余额
	 * @param planBOs 所有的还款计划
	 * @param period 待计算融资余额的还款计划期数
	 * @return 融资余额等于该期计划之后（包含）的所有还款计划的应还本金、逾期本金之和
	 */
	public static long calcFinanceBalance(List<RepaymentPlanBO> planBOs, int period) {
		long balance = 0l;
		for (RepaymentPlanBO planBO : planBOs) {
			if (period <= planBO.getRepaymentPeriod()) {
				balance += planBO.getAccountPrincipal() + planBO.getOverduePrincipal();
			}
		}
		return balance;
	}
}

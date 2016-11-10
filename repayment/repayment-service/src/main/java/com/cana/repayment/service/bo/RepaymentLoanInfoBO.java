package com.cana.repayment.service.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoSnapshotMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.po.LoanInfoConfig;
import com.cana.repayment.dao.po.RepaymentExpense;
import com.cana.repayment.dao.po.RepaymentLoanInfo;
import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;
import com.cana.repayment.dao.po.RepaymentPlan;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleCollectExample;
import com.cana.repayment.service.IRepositoryService;
import com.cana.vbam.common.repayment.enums.InputMethod;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentLoanInfoBO extends RepaymentLoanInfo{

	private static final long serialVersionUID = -5289751507569556836L;
	
	private IRepositoryService repositoryService = SpringApplicationContext.getApplicationContext().getBean(IRepositoryService.class);
	
	private SequenceGenerator seqGen = SpringApplicationContext.getApplicationContext().getBean(SequenceGenerator.class);
	
	private RepaymentLoanInfoSnapshotMapper loanInfoSnapshotMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentLoanInfoSnapshotMapper.class);
	
	private RepaymentLoanInfoMapper loanInfoMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentLoanInfoMapper.class);

	private RepaymentSingleCollectMapper repaymentSummaryMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentSingleCollectMapper.class);
	
	private List<RepaymentPlanBO> plans;
	
	private List<RepaymentExpenseBO> expenses;
	
	private LoanInfoConfig repaymentConfig;
	
	private List<RepaymentSingleCollectBO> repaymentSummaryBOs;
	
	
	public RepaymentLoanInfoBO(String loanInfoId){
		this(SpringApplicationContext.getApplicationContext().getBean(RepaymentLoanInfoMapper.class).selectByPrimaryKey(loanInfoId));
	}
	
	public RepaymentLoanInfoBO(RepaymentLoanInfo po){
		BeanUtils.copyProperties(po, this);
	}
	
	/**
	 * 是否包含还款计划
	 * @return
	 */
	public boolean hasAnyPlans(){
		return !CollectionUtils.isEmpty(lazyLoadPlans());
	}
	 
	/**
	 * 判断是否合法（还款计划是否完整）
	 * @return
	 */
	public boolean islegal(){
		return !CollectionUtils.isEmpty(lazyLoadPlans()) && getRepaymentPeriod() == plans.size();
	}
	
	/**
	 * 懒加载所有的还款计划, 按照期数排序
	 * @return
	 */
	public List<RepaymentPlanBO> lazyLoadPlans(){
		if(plans != null)
			return plans;
		plans = new ArrayList<>();
		for(RepaymentPlan plan : repositoryService.getPlansByLoanInfoId(getId()))
			plans.add(new RepaymentPlanBO(plan));
		return plans;
	}
	
	/**
	 * 懒加载所有的固定费用，按照还款日期排序
	 * @return
	 */
	public List<RepaymentExpenseBO> lazyLoadExpenses(){
		if(expenses != null)
			return expenses;
		expenses = new ArrayList<>();
		for(RepaymentExpense expense : repositoryService.getExpensesByLoanInfoId(getId()))
			expenses.add(new RepaymentExpenseBO(expense));
		return expenses;
	}
	
	/**
	 * 懒加载还款规则
	 * @return
	 */
	public LoanInfoConfig lazyLoadRepaymentConfig(){
		if(repaymentConfig != null)
			return repaymentConfig;
		repaymentConfig = repositoryService.getRepaymentConfigByLoanInfoId(getId());
		return repaymentConfig;
	}
	
	/**
	 * 懒加载所有的还款明细
	 * @return
	 */
	public List<RepaymentSingleCollectBO> lazyLoadRepaymentSummaryBOs(){
		if(repaymentSummaryBOs != null)
			return repaymentSummaryBOs;
		repaymentSummaryBOs = new ArrayList<>();
		for(RepaymentSingleCollect summary : repositoryService.getRepaymentSummarysByLoanInfoId(getId()))
			repaymentSummaryBOs.add(new RepaymentSingleCollectBO(summary));
		return repaymentSummaryBOs;
	}

	/**
	 * 有逾期的还款计划
	 * @param curDate10
	 * @return
	 */
	public List<RepaymentPlanBO> overduePlans(String curDate10) {
		List<RepaymentPlanBO> overduePlans = new ArrayList<>();
		for(RepaymentPlanBO plan : lazyLoadPlans())
			if(plan.inOverdueState(curDate10))
				overduePlans.add(plan);
		return overduePlans;
	}
	
	/**
	 * 正常的还款计划
	 * @param curDate10
	 * @return
	 */
	public List<RepaymentPlanBO> normalPlans(String curDate10) {
		List<RepaymentPlanBO> normalPlans = new ArrayList<>();
		for(RepaymentPlanBO plan : lazyLoadPlans())
			if(plan.inNormalState(curDate10))
				normalPlans.add(plan);
		return normalPlans;
	}
	
	
	/**
	 * 固定还款日等于传入日期的正常还款计划
	 * @param curDate10
	 * @return
	 */
	public List<RepaymentPlanBO> normalPlansOnRepaymentDate(String curDate10) {
		List<RepaymentPlanBO> normalPlans = new ArrayList<>();
		for(RepaymentPlanBO planBO : normalPlans(curDate10)){
			if(planBO.getRepaymentDate().equals(curDate10))
				normalPlans.add(planBO);
		}
		return normalPlans;
	}
	
	/**
	 * 全部应还未还的费用总额
	 * @param curDate10
	 * @return
	 */
	public Long totalUnpaidExpensesAmount(String curDate10){
		Long total = 0L;
		for(RepaymentExpenseBO expense : unpaidExpenses(curDate10))
			total += expense.unpaid(curDate10);
		return total;
	}

	/**
	 * 应还未还的费用列表
	 * @param curDate10
	 * @return
	 */
	public List<RepaymentExpenseBO> unpaidExpenses(String curDate10) {
		List<RepaymentExpenseBO> expenses = new ArrayList<>();
		for(RepaymentExpenseBO expense : lazyLoadExpenses())
			if(expense.hasUnpaid(curDate10))
				expenses.add(expense);
		return expenses;
	}

	/**
	 * 传入的日期是否在某些还款计划的展期时间内
	 * @param date10
	 * @return
	 */
	public boolean inSomePlanExtensionTimeRange(String date10) {
		for(RepaymentPlanBO plan : lazyLoadPlans())
			if(plan.inExtensionTimeRange(date10))
				return true;
		return false;
	}


	/**
	 * 是否有计划可能在指定的生成逾期
	 * @param date10
	 * @return
	 */
	public boolean somePlanInOverdueTimeRangeAt(String date10) {
		for(RepaymentPlanBO plan : lazyLoadPlans())
			if(plan.inOverdueTimeRange(date10))
				return true;
		return false;
	}

	/**
	 * 总的逾期金额
	 * @param curDate10 
	 * @return
	 */
	public long totalOverdueAmount(String curDate10) {
		long totalOverdue = 0;
		for(RepaymentPlanBO plan : overduePlans(curDate10))
			totalOverdue += plan.totalOverdueAmount();
		return totalOverdue;
	}


	/**
	 * 在放款信息中冗余数据
	 */
	public void duplicate() {
		
		if(CollectionUtils.isEmpty(lazyLoadPlans()))
			return;
		
		boolean settled = true;
		long totalPaidAmount = 0;
		long financeBalance = 0;
		
		for(RepaymentPlanBO planBO : lazyLoadPlans()){
			settled = settled && planBO.isSettled();
			totalPaidAmount += planBO.totalPaid();
			financeBalance += planBO.getAccountPrincipal() + planBO.getOverduePrincipal();
		}
		
		for(RepaymentExpenseBO expenseBO : lazyLoadExpenses()){
			settled = settled && expenseBO.isSettled();
			totalPaidAmount += expenseBO.getPaidAmount();
		}
		if(!islegal()){
			settled = false;
		}
		if(settled)
			setSettleStatus(SettleStatus.SETTLED.name());
		else setSettleStatus(SettleStatus.UNSETTLE.name());
		
		setFinanceBalance(financeBalance);
		setPaidTotalAmount(totalPaidAmount);
	}

	/**
	 * 返回在展期状态中的还款计划
	 * @param date10
	 * @return
	 */
	public List<RepaymentPlanBO> plansInExtensionState(String date10) {
		List<RepaymentPlanBO> plans = new ArrayList<>();
		for(RepaymentPlanBO plan : lazyLoadPlans())
			if(plan.inExtensionState(date10))
				plans.add(plan);
		return plans;
	}


	/**
	 * 固定还款日等于传入日期的还款计划总的还款金额
	 * @param curDate10
	 * @return
	 */
	public long totalNormalAmountOnRepaymentDate(String curDate10) {
		long totalNormal = 0;
		for(RepaymentPlanBO plan : normalPlansOnRepaymentDate(curDate10))
			totalNormal += plan.totalAccountAmount();
		return totalNormal;
	}

	public long totalExtensionAmount(String curDate10) {
		long totalExtension = 0;
		for(RepaymentPlanBO plan : extensionPlans(curDate10))
			totalExtension += plan.totalExtensionAmount();
		return totalExtension;
	}

	public List<RepaymentPlanBO> extensionPlans(String curDate10) {
		List<RepaymentPlanBO> extensionPlans = new ArrayList<>();
		for(RepaymentPlanBO plan : lazyLoadPlans())
			if(plan.inExtensionState(curDate10))
				extensionPlans.add(plan);
		return extensionPlans;
	}

	
	/**
	 * 生成放款信息和项下还款计划和固定费用的快照
	 */
	public void createSnapshot(){
		RepaymentLoanInfoSnapshot loanInfoSnapshot = new RepaymentLoanInfoSnapshot();
		BeanUtils.copyProperties(this, loanInfoSnapshot);
		loanInfoSnapshot.setId(DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_LOAN_INFO_SNAPSHOT_ID, 4));
		loanInfoSnapshot.setLoanInfoId(this.getId());
		loanInfoSnapshot.setCreateTime(new Date());
		loanInfoSnapshotMapper.insertSelective(loanInfoSnapshot);
		
		for(RepaymentPlanBO planBO : lazyLoadPlans())
			planBO.createSnapshot(loanInfoSnapshot);
		
		for(RepaymentExpenseBO expenseBO : lazyLoadExpenses())
			expenseBO.createSnapshot(loanInfoSnapshot);
		
	}
	
	/**
	 * 更新自定义数据
	 * @param string
	 * @param businessSeq
	 */
	public void updateExtraData(String key, String value) {
		HashMap<String, String> extraData = new HashMap<>();
		if(StringUtils.isNotBlank(getExtraData()))
			extraData = new Gson().fromJson(getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		extraData.put(key, value);
		setExtraData(new Gson().toJson(extraData));
		loanInfoMapper.updateByPrimaryKey(this);
	}

	/**
	 * 获取指定key值的自定义数据
	 * @param key
	 * @return
	 */
	public String extraData(String key) {
		HashMap<String, String> extraData = new HashMap<>();
		if(StringUtils.isNotBlank(getExtraData()))
			extraData = new Gson().fromJson(getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		return extraData.get(key);
	}
	
	/**
	 * 是否包含非自动计算的还款计划
	 * @param loanInfoBO
	 * @return
	 */
	public boolean containNonAutoRepaymentPlans() {
		
		for(RepaymentPlanBO planBO : lazyLoadPlans())
			if(!InputMethod.AUTO.name().equals(planBO.getInputMethod()))
				return true;
		
		return false;
	}

	/**
	 * 根据放款ID获取上一次成功的还款日期，如果没有则返回null
	 */
	public String getLastSuccessRepaymentDate() {
		RepaymentSingleCollectExample example = new RepaymentSingleCollectExample();
		example.createCriteria().andLoanInfoIdEqualTo(getId());
		example.setOrderByClause("repayment_date desc");
		example.setLimitStart(0);
		example.setLimitEnd(1);
		List<RepaymentSingleCollect> repaymentRecord = repaymentSummaryMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(repaymentRecord))
			return null;
		return repaymentRecord.get(0).getRepaymentDate();
	}

}

package com.cana.asset.service.transaction.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.AssetInvoiceInfoMapper;
import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.mapper.gen.LoanInfoMapper;
import com.cana.asset.dao.mapper.gen.LoanPaidMapper;
import com.cana.asset.dao.mapper.gen.LoanPlanMapper;
import com.cana.asset.dao.mapper.gen.UnderlyingAssetMapper;
import com.cana.asset.dao.po.AssetInvoiceInfo;
import com.cana.asset.dao.po.AssetInvoiceInfoExample;
import com.cana.asset.dao.po.Credit;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.asset.dao.po.LoanInfo;
import com.cana.asset.dao.po.LoanInfoExample;
import com.cana.asset.dao.po.LoanPaid;
import com.cana.asset.dao.po.LoanPaidExample;
import com.cana.asset.dao.po.LoanPlan;
import com.cana.asset.dao.po.LoanPlanExample;
import com.cana.asset.dao.utils.IDGenerator;
import com.cana.asset.service.transaction.IAssetCreditTransactionService;
import com.cana.asset.service.transaction.IAssetExpenseTransactionService;
import com.cana.asset.service.transaction.IAssetFactorBusinessTransactionService;
import com.cana.asset.service.transaction.IAssetInvoiceTransactionService;
import com.cana.asset.service.transaction.IAssetLoanInfoTransactionService;
import com.cana.asset.service.transaction.util.AssetLoanInfoUtil;
import com.cana.asset.service.transaction.util.AssetLoanPlanAutoGenerator;
import com.cana.asset.service.transaction.util.LoanAndUnderlyingAssetIdUtils;
import com.cana.asset.service.transaction.util.ValidateRules;
import com.cana.vbam.common.asset.dto.BusinessCounterpartyDTO;
import com.cana.vbam.common.asset.dto.FactorBusinessDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.dto.InvoiceListDTO;
import com.cana.vbam.common.asset.dto.InvoiceQueryDTO;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.asset.enums.LoanState;
import com.cana.vbam.common.asset.loan.dto.AssetPaidPlanRequest;
import com.cana.vbam.common.asset.loan.dto.EditAssetLoanRequest;
import com.cana.vbam.common.asset.loan.dto.GenerateLoanPlanRequest;
import com.cana.vbam.common.asset.loan.dto.LoanPlanDTO;
import com.cana.vbam.common.credit.enums.CreditMode;
import com.cana.vbam.common.member.enums.user.UserType;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;
import com.cana.vbam.common.utils.AccountNoUtil;
import com.cana.vbam.common.utils.CanaDateUtils;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.NumberUtil;

/**
 * 放款
 * @author XuMeng
 *
 */
@Service
public class AssetLoanInfoTransactionServiceImpl implements IAssetLoanInfoTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private LoanInfoMapper loanInfoMapper;
	@Resource
	private LoanPlanMapper loanPlanMapper;
	@Resource
	private LoanPaidMapper loanPaidMapper;
	@Resource
	private ExpenseMapper expenseMapper;
	@Resource
	private AssetInvoiceInfoMapper assetInvoiceInfoMapper;
	@Resource
	private IAssetCreditTransactionService creditTransactionService;
	@Resource
	private AssetInvoiceInfoMapper invoiceInfoMapper;
	@Resource
	private IAssetExpenseTransactionService expenseTransactionService;
	@Resource
	private IAssetInvoiceTransactionService invoiceTransactionService;
	@Resource
	private IAssetFactorBusinessTransactionService businessTransactionService;
	@Resource
	private UnderlyingAssetMapper underlyingAssetMapper;
	
	@Override
	public Set<String> pickContractNosHasLoan(Set<String> contractNos) {
		if (CollectionUtils.isEmpty(contractNos))
			return Sets.newHashSet();

		LoanInfoExample example = new LoanInfoExample();
		example.createCriteria().andBusinessContractNoIn(Lists.newArrayList(contractNos));
		List<LoanInfo> loanInfos = loanInfoMapper.selectByExample(example);

		Set<String> pickedContractNos = Sets.newHashSet();
		if (CollectionUtils.isNotEmpty(loanInfos)) {
			for (LoanInfo loan : loanInfos) {
				pickedContractNos.add(loan.getBusinessContractNo());
			}
		}
		return pickedContractNos;
	}

	@Override
	public boolean checkContractNoHasLoan(String contractNo) {
		if (StringUtils.isBlank(contractNo))
			throw new IllegalArgumentException("contractNo can not be blank");

		LoanInfoExample example = new LoanInfoExample();
		example.createCriteria().andBusinessContractNoEqualTo(contractNo);
		List<LoanInfo> loanInfos = loanInfoMapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(loanInfos);
	}

	@Override
	public void deleteLoanById(UserVo userVo, String loanInfoId) {
		if (userVo == null || StringUtils.isBlank(loanInfoId))
			throw new IllegalArgumentException("参数不能为空");
		logger.info("用户[{}]执行删除放款[{}]操作", userVo.getUserId(), loanInfoId);

		LoanInfo loan = loanInfoMapper.lockByPrimaryKey(loanInfoId);
		if (loan == null)
			throw WebException.instance("放款不存在");
		if (!StringUtils.equals(userVo.getCustomerId(), loan.getFactorId()))
			throw WebException.instance("放款信息不存在");
		checkDenyPermByConfig(loan.getBusinessContractNo(), "删除");

		Credit credit = lockAndCheckCredit(userVo, loan.getBusinessContractNo());

		// 检查是否有还款
		LoanPaidExample example = new LoanPaidExample();
		example.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		List<LoanPaid> paids = loanPaidMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(paids))
			throw WebException.instance("不能删除已还款的放款");
		if (underlyingAssetMapper.selectByPrimaryKey(loanInfoId) != null)
			throw WebException.instance("不能删除已转为基础资产的放款");

		// 恢复额度
		creditTransactionService.recoveryLimit(credit.getId(), loan.getFinanceBalance(), userVo.getUserId());

		loanInfoMapper.deleteByPrimaryKey(loanInfoId);

		List<LoanPlan> plans = getPlansByLoanInfoId(loanInfoId);
		if (CollectionUtils.isNotEmpty(plans)) {
			for (LoanPlan plan : plans) {
				loanPlanMapper.deleteByPrimaryKey(plan.getId());
			}
		}

		ExpenseExample expenseExample = new ExpenseExample();
		expenseExample.createCriteria().andReftypeEqualTo(ExpenseType.LOAN.name()).andRefidEqualTo(loanInfoId);
		List<Expense> expenses = expenseMapper.selectByExample(expenseExample);
		if (CollectionUtils.isNotEmpty(expenses)) {
			for (Expense expense : expenses) {
				expenseMapper.deleteByPrimaryKey(expense.getId());
			}
		}

		// 解除与应收帐款的关联
		AssetInvoiceInfoExample invoiceExample = new AssetInvoiceInfoExample();
		invoiceExample.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		List<AssetInvoiceInfo> invoices = invoiceInfoMapper.selectByExample(invoiceExample);
		if (CollectionUtils.isNotEmpty(invoices)) {
			for (AssetInvoiceInfo invoice : invoices) {
				invoice.setLoanInfoId(null);
				invoice.setUpdateTime(new Date());
				invoiceInfoMapper.updateByPrimaryKey(invoice);
			}
		}

		if (false == checkContractNoHasLoan(loan.getBusinessContractNo()))
			updateBusinessLoanState(Sets.newHashSet(loan.getBusinessContractNo()), LoanState.UNLOAN);

		logger.info("用户[{}]执行删除放款[{}]成功", userVo.getUserId(), loanInfoId);
	}

	/**
	 * 检查当前合同的放款是否已经配置成不可操作
	 */
	private void checkDenyPermByConfig(String contractNo, String operation) {
		if (AssetLoanInfoUtil.getDenyModifyOrPaidContractNos().contains(contractNo)) {
			throw WebException.instance("不可以对当前合同号进行" + operation + "操作");
		}
	}

	/**
	 * 获取按期数排序的还款计划列表
	 */
	private List<LoanPlan> getPlansByLoanInfoId(String loanInfoId) {
		LoanPlanExample loanPlanExample = new LoanPlanExample();
		loanPlanExample.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		loanPlanExample.setOrderByClause("repayment_period asc");
		List<LoanPlan> plans = loanPlanMapper.selectByExample(loanPlanExample);
		return plans;
	}

	@Override
	public String createAssetLoan(UserVo userVo, EditAssetLoanRequest request) {

		checkEditLoanInfoRequestIsValid(userVo, request);
		checkEditLoanPlanRequestIsValid(userVo, request);

		Credit credit = lockAndCheckCredit(userVo, request.getContractNo());
		String loanInfoId = LoanAndUnderlyingAssetIdUtils.generateLoanInfoId(request.getContractNo());
		request.setLoanInfoId(loanInfoId);

		FactorBusinessDTO businessDTO = checkBusinessForLoanRequest(userVo, request);
		// 根据合同号和交易对手ID查询应收账款列表，检查请求中的应收账款列表必须是该查询结果的子集，且均未关联其他放款
		checkAndSaveInvoiceInfo(request, false);
		// 扣额度
		checkCreditLimitEnoughForCreatLoan(credit, request.getContractNo(), MoneyUtil.yuan2Cent(request.getFinanceAmount()));
		creditTransactionService.useLimit(credit.getId(), MoneyUtil.yuan2Cent(request.getFinanceAmount()), userVo.getUserId());
		// 保存放款
		saveLoanInfoForCreate(userVo, request, businessDTO);
		// 保存还款计划
		saveLoanPlansForCreateOrImportLoan(request);
		// 保存费用
		saveExpenses(request);

		updateBusinessLoanState(Sets.newHashSet(request.getContractNo()), LoanState.LOANED);

		LoanAndUnderlyingAssetIdUtils.updateLoanInfoIdSequance(loanInfoId);
		return loanInfoId;
	}

	@Override
	public Pair<ReturnCode, String> createAssetLoanForHomsom(UserVo factorVo, EditAssetLoanRequest request) {

		String loanInfoId = LoanAndUnderlyingAssetIdUtils.generateLoanInfoIdForHomsom(request.getContractNo());
		request.setLoanInfoId(loanInfoId);

		try {
			importAssetLoanInfo(factorVo, Lists.newArrayList(request), false);
		} catch (WebException e) {
			if (e.getRetCode() == ReturnCode.FINANCE_AMOUNT_EXCEED)
				return Pair.of(ReturnCode.FINANCE_AMOUNT_EXCEED, loanInfoId);

			logger.error("创建恒顺放款失败", e);
			throw e;
		}

		return Pair.of(ReturnCode.SUCCESS, loanInfoId);
	}

	/**
	 * 检查应收账款是否存在，且修改放款请求中关联的应收账款必须是从应收账款模块查询出来的应收帐款的子集，且不关联其他放款
	 * 
	 * 检查通过后则保存
	 * @param onlyCheck 如果为true，则只检查，不做数据库更新
	 */
	private void checkAndSaveInvoiceInfo(EditAssetLoanRequest request, boolean onlyCheck) {
		InvoiceQueryDTO queryDTO = new InvoiceQueryDTO();
		queryDTO.setBusinessContractNo(request.getContractNo());
		queryDTO.setCounterpartyId(request.getCounterpartyId());
		InvoiceListDTO invoiceDTOs = invoiceTransactionService.getInvByExample(queryDTO);
		if (invoiceDTOs == null || CollectionUtils.isEmpty(invoiceDTOs.getInvoiceInfoDTOs()))
			throw WebException.instance("应收账款不存在");

		Set<String> selectedInvoiceIds = Sets.newHashSet();
		for (String invId : request.getInvoiceInfoIds()) {
			if (selectedInvoiceIds.contains(invId))
				throw WebException.instance("选择的应收账款存在重复");
			selectedInvoiceIds.add(invId);
		}

		List<InvoiceInfoDTO> selectedInvoices = Lists.newArrayList();

		BigDecimal invAccountAmt = BigDecimal.ZERO;
		for (InvoiceInfoDTO invoice : invoiceDTOs.getInvoiceInfoDTOs()) {
			if (selectedInvoiceIds.contains(invoice.getId())) {
				// 判断该应收账款没有关联其他放款
				if (StringUtils.isNotEmpty(invoice.getLoanInfoId())
						&& !invoice.getLoanInfoId().equals(request.getLoanInfoId()))
					throw WebException.instance("单证号码［" + invoice.getInvoiceNo() + "］已关联其他放款，请重新选择应收账款");
				long invAmt = MoneyUtil.yuan2Cent(invoice.getInvoiceAmt());
				invAccountAmt = invAccountAmt.add(invoice.getFinancingRatio().divide(new BigDecimal("100")).multiply(new BigDecimal(invAmt)));
				selectedInvoices.add(invoice);
			}
		}

		if (invAccountAmt.setScale(0, RoundingMode.HALF_UP).longValue() < MoneyUtil.yuan2Cent(request.getFinanceAmount()))
			throw WebException.instance("融资金额不能大于应收账款最大可融资金额");

		if (selectedInvoices.size() != selectedInvoiceIds.size())
			throw WebException.instance("应收账款不存在");

		// 如果仅仅是检查，则直接返回
		if (onlyCheck)
			return;

		// 解绑所有与该放款相关联的应收帐款，将上述已选中的应收账款绑定此放款
		AssetInvoiceInfoExample example = new AssetInvoiceInfoExample();
		example.createCriteria().andLoanInfoIdEqualTo(request.getLoanInfoId());
		List<AssetInvoiceInfo> needUnbindInvoices = assetInvoiceInfoMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(needUnbindInvoices)) {
			for (AssetInvoiceInfo unbindInvoice : needUnbindInvoices) {
				if (selectedInvoiceIds.contains(unbindInvoice.getId())) // 编辑时，依然选择了该应收账款，则不用修改
					continue;
				unbindInvoice.setLoanInfoId(null);
				unbindInvoice.setUpdateTime(new Date());
				assetInvoiceInfoMapper.updateByPrimaryKey(unbindInvoice);
			}
		}

		for (InvoiceInfoDTO bindInvoice : selectedInvoices) {
			if (StringUtils.equals(bindInvoice.getLoanInfoId(), request.getLoanInfoId()))
				continue;
			AssetInvoiceInfo invoice = new AssetInvoiceInfo();
			invoice.setId(bindInvoice.getId());
			invoice.setLoanInfoId(request.getLoanInfoId());
			invoice.setUpdateTime(new Date());
			assetInvoiceInfoMapper.updateByPrimaryKeySelective(invoice);
		}
	}

	private void saveLoanInfoForCreate(UserVo userVo, EditAssetLoanRequest request, FactorBusinessDTO businessDTO) {
		LoanInfo existLoan = loanInfoMapper.selectByPrimaryKey(request.getLoanInfoId());
		if (existLoan != null)
			throw WebException.instance("已存在该放款编号");

		LoanInfo loan = convertLoanRequest(userVo, request, businessDTO);
		loan.setCreateTime(new Date());
		loan.setUpdateTime(new Date());

		loanInfoMapper.insertSelective(loan);
	}

	private void saveLoanInfoForUpdate(UserVo userVo, EditAssetLoanRequest request, FactorBusinessDTO businessDTO) {
		LoanInfo loan = convertLoanRequest(userVo, request, businessDTO);
		loan.setUpdateTime(new Date());

		loanInfoMapper.updateByPrimaryKeySelective(loan);
	}

	private void checkLoanInfoForUpdate(UserVo userVo, EditAssetLoanRequest request, LoanInfo existLoan, List<LoanPaid> paids) {
		if (existLoan == null)
			throw WebException.instance("放款不存在");
		if (underlyingAssetMapper.selectByPrimaryKey(request.getLoanInfoId()) != null)
			throw WebException.instance("不能修改已转为基础资产的放款");
		if (!StringUtils.equals(request.getContractNo(), existLoan.getBusinessContractNo()))
			throw WebException.instance("不可修改合同号");
		if (!StringUtils.equals(userVo.getCustomerId(), existLoan.getFactorId()))
			throw WebException.instance("无权限修改该放款");

		if (CollectionUtils.isNotEmpty(paids)) {
			// 如果有还款记录，则基本信息、放款信息
			if (!StringUtils.equals(request.getCounterpartyId(), existLoan.getCounterpartyId()))
				throw WebException.instance("已有还款的放款不能修改交易对手");
			if (MoneyUtil.yuan2Cent(request.getFinanceAmount()) != existLoan.getFinanceAmount().longValue())
				throw WebException.instance("已有还款的放款不能修改融资金额");
			if (!StringUtils.equals(request.getCurrency(), existLoan.getCurrency()))
				throw WebException.instance("已有还款的放款不能修改币种");
			if (!StringUtils.equals(request.getRepaymentType(), existLoan.getRepaymentMethod()))
				throw WebException.instance("已有还款的放款不能修改还款方式");
			if (!StringUtils.equals(request.getLoanPeriodUnit(), existLoan.getLoanPeriodUnit()))
				throw WebException.instance("已有还款的放款不能修改放款期限单位");
			if (request.getLoanPeriod() != existLoan.getLoanPeriod())
				throw WebException.instance("已有还款的放款不能修改放款期限");
			if (request.getDayCountConvention() != existLoan.getDayCountConvention())
				throw WebException.instance("已有还款的放款不能修改放款期限");
			if (!StringUtils.equals(request.getInterestRateUnit(), existLoan.getInterestRateUnit()))
				throw WebException.instance("已有还款的放款不能修改放款利率单位");
			if (MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()).compareTo(existLoan.getInterestRate()) != 0)
				throw WebException.instance("已有还款的放款不能修改放款利率");
			if (!StringUtils.equals(request.getLoanDate(), existLoan.getLoanDate()))
				throw WebException.instance("已有还款的放款不能修改放款日期");
			if (!StringUtils.equals(request.getDueDate(), existLoan.getDueDate()))
				throw WebException.instance("已有还款的放款不能修改到期日");
			if (MoneyArithUtil.convertStringToInterestRate(request.getPenaltyRate()).compareTo(existLoan.getPenaltyRate()) != 0)
				throw WebException.instance("已有还款的放款不能修改逾期费率");
			if (!StringUtils.equals(request.getRepaymentAccountNo(), existLoan.getAccountNo()))
				throw WebException.instance("已有还款的放款不能修改还款账号");
		}
	}

	private LoanInfo convertLoanRequest(UserVo userVo, EditAssetLoanRequest request, FactorBusinessDTO businessDTO) {
		LoanInfo loan = new LoanInfo();
		loan.setId(request.getLoanInfoId());
		loan.setBusinessContractNo(request.getContractNo());
		loan.setFactorId(userVo.getCustomerId());
		loan.setCounterpartyId(request.getCounterpartyId());
		loan.setCustomerId(businessDTO.getCustomerId());
		loan.setCustomerName(businessDTO.getCustomerName());
		loan.setProjectName(businessDTO.getProjectName());
		loan.setBusinessProduct(businessDTO.getBusinessProduct().name());
		loan.setFinanceAmount(MoneyUtil.yuan2Cent(request.getFinanceAmount()));
		loan.setFinanceBalance(loan.getFinanceAmount());
		loan.setCurrency(request.getCurrency());
		loan.setRepaymentMethod(request.getRepaymentType());
		loan.setLoanPeriodUnit(request.getLoanPeriodUnit());
		loan.setLoanPeriod(request.getLoanPeriod());
		loan.setDayCountConvention(request.getDayCountConvention());
		loan.setInterestRateUnit(request.getInterestRateUnit());
		loan.setInterestRate(MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()));
		loan.setLoanDate(request.getLoanDate());
		loan.setDueDate(request.getDueDate());
		loan.setPenaltyRate(MoneyArithUtil.convertStringToInterestRate(request.getPenaltyRate()));
		loan.setAccountNo(request.getRepaymentAccountNo()); 
		loan.setSettleStatus(SettleStatus.UNSETTLE.name());
		return loan;
	}

	/**
	 * 创建放款或者导入放款时，保存还款计划
	 */
	private void saveLoanPlansForCreateOrImportLoan(EditAssetLoanRequest request) {
		int period = 1;
		for (LoanPlanDTO planDTO : request.getPlans()) {
			LoanPlan plan = new LoanPlan();
			plan.setId(IDGenerator.generateAssetLoanPlanId());
			plan.setLoanInfoId(request.getLoanInfoId());
			plan.setRepaymentPeriod(period++);
			plan.setFinanceBalance(MoneyUtil.yuan2Cent(planDTO.getFinanceBalance()));
			plan.setValueDate(planDTO.getValueDate());
			plan.setSettleInterestDate(planDTO.getSettleInterestDate());
			plan.setRepaymentDate(planDTO.getRepaymentDate());
			plan.setAccountPrincipal(MoneyUtil.yuan2Cent(planDTO.getAccountPrincipal()));
			plan.setAccountInterest(MoneyUtil.yuan2Cent(planDTO.getAccountInterest()));
			plan.setSettleStatus(planDTO.getSettleStatus());
			plan.setCreateTime(new Date());
			plan.setUpateTime(new Date());
			loanPlanMapper.insertSelective(plan);
		}
	}

	private void saveExpenses(EditAssetLoanRequest request) {
		if (CollectionUtils.isNotEmpty(request.getExpenses())) {
			int seq = 1;
			for (EditAssetLoanRequest.Expense expense : request.getExpenses()) {
				Expense expensePO = new Expense();
				expensePO.setRefid(request.getLoanInfoId());
				expensePO.setReftype(ExpenseType.LOAN.name());
				expensePO.setExpenseSubject(expense.getSubject());
				expensePO.setAmount(MoneyUtil.yuan2Cent(expense.getAmount()));
				expensePO.setCreateTime(new Date());
				expensePO.setUpdateTime(new Date());
				expensePO.setSequence(seq++);
				expenseTransactionService.savaExpense(expensePO);
			}
		}
	}

	@Override
	public String updateAssetLoan(UserVo userVo, EditAssetLoanRequest request) {

		if (StringUtils.isBlank(request.getLoanInfoId()))
			throw WebException.instance("放款编号不能为空");
		checkEditLoanInfoRequestIsValid(userVo, request);
		checkEditLoanPlanRequestIsValid(userVo, request);

		checkDenyPermByConfig(request.getContractNo(), "修改");
		Credit credit = lockAndCheckCredit(userVo, request.getContractNo());

		LoanInfo existLoan = loanInfoMapper.lockByPrimaryKey(request.getLoanInfoId());
		List<LoanPaid> paids = getLoanPaidsByLoanInfoId(request.getLoanInfoId());

		checkLoanInfoForUpdate(userVo, request, existLoan, paids);
		FactorBusinessDTO businessDTO = checkBusinessForLoanRequest(userVo, request);
		// 根据合同号和交易对手ID查询应收账款列表，检查请求中的应收账款列表必须是该查询结果的子集，且均未关联其他放款
		if (CollectionUtils.isEmpty(paids))
			checkAndSaveInvoiceInfo(request, false);

		updateCreditLimitForUpdateLoan(credit, userVo, request, existLoan);
		// 更新放款
		saveLoanInfoForUpdate(userVo, request, businessDTO);
		// 更新还款计划
		saveLoanPlansForUpdateLoanOrImportPlan(request, paids);
		// 更新费用
		if (CollectionUtils.isEmpty(paids)) {
			expenseTransactionService.deleteExpenseByRef(request.getLoanInfoId(), ExpenseType.LOAN);
			saveExpenses(request);
		}

		duplicateLoanInfoSettleStatus(request.getLoanInfoId());

		return request.getLoanInfoId();
	}

	/**
	 * 将还款计划中的结清状态冗余到放款表中，如果全部还款计划均还清，则放款结清
	 * 调用此接口之前需保证放款与还款存在
	 * @param loanInfoId
	 * @return 是否修改了放款的结清状态
	 */
	private boolean duplicateLoanInfoSettleStatus(String loanInfoId) {
		SettleStatus settleStatus = SettleStatus.SETTLED;
		for (LoanPlan plan : getPlansByLoanInfoId(loanInfoId)) {
			if (SettleStatus.UNSETTLE.name().equals(plan.getSettleStatus()))
				settleStatus = SettleStatus.UNSETTLE;
		}

		LoanInfo loan = loanInfoMapper.selectByPrimaryKey(loanInfoId);
		if (settleStatus.name().equals(loan.getSettleStatus()))
			return false;

		loan.setSettleStatus(settleStatus.name());
		loan.setUpdateTime(new Date());
		loanInfoMapper.updateByPrimaryKeySelective(loan);
		return true;
	}

	/**
	 * 只扣修改后增加的额度，或恢复修改后减少的额度
	 */
	private void updateCreditLimitForUpdateLoan(Credit credit, UserVo userVo, EditAssetLoanRequest request, LoanInfo existLoan) {
		long updateLimit = MoneyUtil.yuan2Cent(request.getFinanceAmount()) - existLoan.getFinanceAmount();
		if (updateLimit > 0)
			creditTransactionService.useLimit(credit.getId(), updateLimit, userVo.getUserId());
		else if (updateLimit < 0)
			creditTransactionService.recoveryLimit(credit.getId(), updateLimit, userVo.getUserId());
	}

	private Credit lockAndCheckCredit(UserVo userVo, String contractNo) {
		Credit credit = creditTransactionService.lockByBussinessContractNo(contractNo);
		if (credit == null)
			throw WebException.instance("授信额度不存在");
		if (!StringUtils.equals(userVo.getCustomerId(), credit.getFactorId()))
			throw WebException.instance("无权使用该授信额度");
		return credit;
	}

	/**
	 * 修改放款或者导入还款计划时，修改数据库中的还款计划
	 * 可以任意修改没有还款冲销的还款，对于有还款冲销且未结清的，只允许修改逾期费和结清状态
	 * @param request
	 * @param paids 放款的还款记录
	 */
	private void saveLoanPlansForUpdateLoanOrImportPlan(EditAssetLoanRequest request, List<LoanPaid> paids) {
		List<LoanPlan> dbPlans = getPlansByLoanInfoId(request.getLoanInfoId());

		List<LoanPlanDTO> editPlans = request.getPlans();

		for (int period = 1; period <= editPlans.size() || period <= dbPlans.size(); period++) {
			if (period <= dbPlans.size()) { // 如果db存在
				LoanPlan dbPlan = dbPlans.get(period - 1);

				if (hasPaidRecord(paids, dbPlan)) { // 该计划有还款冲销记录，不允许修改及删除
					if (period > editPlans.size())
						throw WebException.instance("不允许删除已有还款冲销的还款计划");
					updatePaidLoanPlanByDTO(dbPlan, editPlans.get(period - 1));

				} else { // 该计划没有还款冲销记录，允许修改及删除

					if (period > editPlans.size())
						loanPlanMapper.deleteByPrimaryKey(dbPlan.getId());
					else
						updateUnpaidLoanPlanByDTO(dbPlan, editPlans.get(period - 1));
				}
			} else { // 如果db没有，则插入
				insertLoanPlanByDTO(request.getLoanInfoId(), period, editPlans.get(period - 1));
			}
		}
	}

	private List<LoanPaid> getLoanPaidsByLoanInfoId(String loanInfoId) {
		if (StringUtils.isEmpty(loanInfoId))
			throw new IllegalArgumentException("放款编号不能为空");
		LoanPaidExample paidExample = new LoanPaidExample();
		paidExample.createCriteria().andLoanInfoIdEqualTo(loanInfoId);
		List<LoanPaid> paids = loanPaidMapper.selectByExample(paidExample);
		return paids;
	}

	/**
	 * 更新一个有还款冲销记录的还款计划
	 * @param dbPlan
	 * @param planDTO
	 */
	private void updatePaidLoanPlanByDTO(LoanPlan dbPlan, LoanPlanDTO planDTO) {
		if (!StringUtils.equals(planDTO.getValueDate(), dbPlan.getValueDate()))
			throw WebException.instance("不允许修改已有还款冲销还款计划的起息日");
		if (!StringUtils.equals(planDTO.getSettleInterestDate(), dbPlan.getSettleInterestDate()))
			throw WebException.instance("不允许修改已有还款冲销还款计划的收益分配日");
		if (!StringUtils.equals(planDTO.getRepaymentDate(), dbPlan.getRepaymentDate()))
			throw WebException.instance("不允许修改已有还款冲销还款计划的还款日");
		if (MoneyUtil.yuan2Cent(planDTO.getAccountPrincipal()) != dbPlan.getAccountPrincipal())
			throw WebException.instance("不允许修改已有还款冲销还款计划的应还本金");
		if (MoneyUtil.yuan2Cent(planDTO.getAccountInterest()) != dbPlan.getAccountInterest())
			throw WebException.instance("不允许修改已有还款冲销还款计划的应还利息");

		boolean modified = false;
		if (MoneyUtil.yuan2Cent(planDTO.getFinanceBalance()) != dbPlan.getFinanceBalance()) {
			dbPlan.setFinanceBalance(MoneyUtil.yuan2Cent(planDTO.getFinanceBalance()));
			modified = true;
		}

		long accountOverdue = MoneyUtil.yuan2Cent(planDTO.getAccountOverdue());
		if (accountOverdue < dbPlan.getPaidOverdue())
			throw WebException.instance("应还逾期费不能小于已还逾期费");
		if (accountOverdue != dbPlan.getAccountOverdue()) {
			dbPlan.setAccountOverdue(accountOverdue);
			modified = true;
		}

		if (SettleStatus.SETTLED.name().equals(planDTO.getSettleStatus())
				&& !SettleStatus.SETTLED.name().equals(dbPlan.getSettleStatus())) {
			dbPlan.setSettleStatus(planDTO.getSettleStatus());
			modified = true;
		}

		if (modified) {
			dbPlan.setUpateTime(new Date());
			loanPlanMapper.updateByPrimaryKeySelective(dbPlan);
		}
	}

	/**
	 * 更新一个没有还款冲销记录的还款计划
	 * @param dbPlan
	 * @param planDTO
	 */
	private void updateUnpaidLoanPlanByDTO(LoanPlan dbPlan, LoanPlanDTO planDTO) {
		dbPlan.setFinanceBalance(MoneyUtil.yuan2Cent(planDTO.getFinanceBalance()));
		dbPlan.setValueDate(planDTO.getValueDate());
		dbPlan.setSettleInterestDate(planDTO.getSettleInterestDate());
		dbPlan.setRepaymentDate(planDTO.getRepaymentDate());
		dbPlan.setAccountPrincipal(MoneyUtil.yuan2Cent(planDTO.getAccountPrincipal()));
		dbPlan.setAccountInterest(MoneyUtil.yuan2Cent(planDTO.getAccountInterest()));
		dbPlan.setAccountOverdue(MoneyUtil.yuan2Cent(planDTO.getAccountOverdue()));
		dbPlan.setSettleStatus(planDTO.getSettleStatus());
		dbPlan.setUpateTime(new Date());
		loanPlanMapper.updateByPrimaryKeySelective(dbPlan);
	}

	private void insertLoanPlanByDTO(String loanInfoId, int period, LoanPlanDTO planDTO) {
		LoanPlan plan = new LoanPlan();
		plan.setId(IDGenerator.generateAssetLoanPlanId());
		plan.setLoanInfoId(loanInfoId);
		plan.setRepaymentPeriod(period);
		plan.setFinanceBalance(MoneyUtil.yuan2Cent(planDTO.getFinanceBalance()));
		plan.setValueDate(planDTO.getValueDate());
		plan.setSettleInterestDate(planDTO.getSettleInterestDate());
		plan.setRepaymentDate(planDTO.getRepaymentDate());
		plan.setAccountPrincipal(MoneyUtil.yuan2Cent(planDTO.getAccountPrincipal()));
		plan.setAccountInterest(MoneyUtil.yuan2Cent(planDTO.getAccountInterest()));
		plan.setAccountOverdue(MoneyUtil.yuan2Cent(planDTO.getAccountOverdue()));
		plan.setSettleStatus(planDTO.getSettleStatus());
		plan.setCreateTime(new Date());
		plan.setUpateTime(new Date());
		loanPlanMapper.insertSelective(plan);
	}

	private boolean hasPaidRecord(List<LoanPaid> paids, LoanPlan plan) {
		if (CollectionUtils.isEmpty(paids))
			return false;
		for (LoanPaid paid : paids) {
			if (StringUtils.equals(paid.getLoanPlanId(), plan.getId()))
				return true;
		}
		return false;
	}

	@Override
	public void checkImportAssetLoanInfoRequest(UserVo userVo, EditAssetLoanRequest request) {
		checkEditLoanInfoRequestIsValid(userVo, request);
		LoanAndUnderlyingAssetIdUtils.checkLoanInfoIdIsValid(request.getContractNo(), request.getLoanInfoId());
		Credit credit = lockAndCheckCredit(userVo, request.getContractNo());
		if (loanInfoMapper.selectByPrimaryKey(request.getLoanInfoId()) != null)
			throw WebException.instance("放款编号已存在");
		checkBusinessForLoanRequest(userVo, request);
		checkAndSaveInvoiceInfo(request, true);
		checkCreditLimitEnoughForCreatLoan(credit, request.getContractNo(), MoneyUtil.yuan2Cent(request.getFinanceAmount()));
	}

	@Override
	public void checkImportAssetLoanPlanRequest(UserVo userVo, EditAssetLoanRequest request) {
		checkEditLoanPlanRequestIsValid(userVo, request);
		if (StringUtils.isBlank(request.getLoanInfoId()))
			throw WebException.instance("放款编号不能为空");
		LoanInfo loan = loanInfoMapper.selectByPrimaryKey(request.getLoanInfoId());
		checkRequestForImportLoanPlan(loan, request, getLoanPaidsByLoanInfoId(request.getLoanInfoId()));
	}

	@Override
	public void importAssetLoanInfo(UserVo userVo, List<EditAssetLoanRequest> requests) {
		importAssetLoanInfo(userVo, requests, true);
	}

	private void importAssetLoanInfo(UserVo userVo, List<EditAssetLoanRequest> requests, boolean checkLoanInfoIdFormat) {

		if (CollectionUtils.isEmpty(requests))
			throw new IllegalArgumentException("参数不能为空");

		Set<String> contractNos = Sets.newHashSet();

		for (EditAssetLoanRequest request : requests) {
				
			checkEditLoanInfoRequestIsValid(userVo, request);
			if (checkLoanInfoIdFormat)
				LoanAndUnderlyingAssetIdUtils.checkLoanInfoIdIsValid(request.getContractNo(), request.getLoanInfoId());
	
			Credit credit = lockAndCheckCredit(userVo, request.getContractNo());
	
			FactorBusinessDTO businessDTO = checkBusinessForLoanRequest(userVo, request);
			// 根据合同号和交易对手ID查询应收账款列表，检查请求中的应收账款列表必须是该查询结果的子集，且均未关联其他放款
			checkAndSaveInvoiceInfo(request, false);
			// 扣额度
			checkCreditLimitEnoughForCreatLoan(credit, request.getContractNo(), MoneyUtil.yuan2Cent(request.getFinanceAmount()));
			creditTransactionService.useLimit(credit.getId(), MoneyUtil.yuan2Cent(request.getFinanceAmount()), userVo.getUserId());
			// 保存放款
			saveLoanInfoForCreate(userVo, request, businessDTO);
			// 自动生成还款计划并保存
			saveLoanPlansForImportLoan(userVo, request);
			// 保存费用
			saveExpenses(request);

			if (checkLoanInfoIdFormat)
				LoanAndUnderlyingAssetIdUtils.updateLoanInfoIdSequance(request.getLoanInfoId());

			contractNos.add(request.getContractNo());
		}

		updateBusinessLoanState(contractNos, LoanState.LOANED);
	}

	private void saveLoanPlansForImportLoan(UserVo userVo, EditAssetLoanRequest request) {
		GenerateLoanPlanRequest generateLoanPlanRequest = new GenerateLoanPlanRequest();
		generateLoanPlanRequest.setFinanceAmount(request.getFinanceAmount());
		generateLoanPlanRequest.setRepaymentType(request.getRepaymentType());
		generateLoanPlanRequest.setDueDate(request.getDueDate());
		generateLoanPlanRequest.setDayCountConvention(request.getDayCountConvention());
		generateLoanPlanRequest.setInterestRateUnit(request.getInterestRateUnit());
		generateLoanPlanRequest.setInterestRate(request.getInterestRate());
		generateLoanPlanRequest.setLoanDate(request.getLoanDate());
		List<LoanPlanDTO> plans = AssetLoanPlanAutoGenerator.generateLoanPlanDTO(generateLoanPlanRequest);
		request.setPlans(plans);
		checkEditLoanPlanRequestIsValid(userVo, request);
		saveLoanPlansForCreateOrImportLoan(request);
	}

	/**
	 * 根据合同号查询业务，检查业务必须存在，请求中的交易对手ID必须是业务里的交易对手的其中一个，请求中的还款账号必须是业务信息中的其中一个
	 * @param userVo
	 * @param request
	 * @return 返回该业务信息
	 */
	private FactorBusinessDTO checkBusinessForLoanRequest(UserVo userVo, EditAssetLoanRequest request) {
		FactorBusinessDTO business = businessTransactionService.queryFactorBusinessInfoByBusinessContractNo(request.getContractNo(), userVo.getCustomerId());
		if (business == null)
			throw WebException.instance("业务信息不存在");
		if (CollectionUtils.isEmpty(business.getCounterpartyList()))
			throw WebException.instance("业务未添加交易对手信息");

		String counterpartyId = request.getCounterpartyId();
		boolean existCounterparty = false;
		for (BusinessCounterpartyDTO counterparty : business.getCounterpartyList()) {
			if (StringUtils.equals(counterpartyId, counterparty.getCounterpartyId())) {
				existCounterparty = true;
				break;
			}
		}
		if (false == existCounterparty)
			throw WebException.instance("交易对手不存在于业务中");

		String accountNo = request.getRepaymentAccountNo();
		if (!StringUtils.equals(AccountNoUtil.parseBankAccountNo(accountNo), AccountNoUtil.parseBankAccountNo(business.getFactoringAccount()))
				&& !StringUtils.equals(AccountNoUtil.parseBankAccountNo(accountNo), AccountNoUtil.parseBankAccountNo(business.getSettlementAccount())))
			throw WebException.instance("还款账号不存在于业务中");

		return business;
	}

	@Override
	public void importAssetLoanPlan(UserVo userVo, List<EditAssetLoanRequest> requests) {
		if (CollectionUtils.isEmpty(requests))
			throw new IllegalArgumentException("参数不能为空");

		for (EditAssetLoanRequest request : requests) {
			
			checkEditLoanPlanRequestIsValid(userVo, request);
			if (StringUtils.isBlank(request.getLoanInfoId()))
				throw WebException.instance("放款编号不能为空");
	
			lockAndCheckCredit(userVo, request.getContractNo());
			LoanInfo loan = loanInfoMapper.lockByPrimaryKey(request.getLoanInfoId());

			List<LoanPaid> paids = getLoanPaidsByLoanInfoId(request.getLoanInfoId());

			checkRequestForImportLoanPlan(loan, request, paids);
			// 保存还款计划
			saveLoanPlansForUpdateLoanOrImportPlan(request, paids);
		}

	}

	/**
	 * 检查额度是否足够，如果是单笔额度还要检查是否存在放款
	 * @param amount 放款金额
	 */
	private void checkCreditLimitEnoughForCreatLoan(Credit lockedCredit, String contractNo, long amount) {
		if (CreditMode.SINGLE.name().equals(lockedCredit.getCreditMode())
				&& checkContractNoHasLoan(contractNo))
			throw WebException.instance("单笔额度不能重复放款");

		// 调用额度模块提供的检查接口，检查额度是否足够放款金额
		if (false == creditTransactionService.checkAvailableLimit(lockedCredit.getId(), amount))
			throw WebException.instance(ReturnCode.FINANCE_AMOUNT_EXCEED);
	}

	/**
	 * 导入还款计划时一笔放款没有还款计划，如果有则抛异常
	 */
	private void checkRequestForImportLoanPlan(LoanInfo loan, EditAssetLoanRequest request, List<LoanPaid> paids) {

		if (loan == null)
			throw WebException.instance("放款[" + request.getLoanInfoId() + "]不存在，不能导入还款计划");
		if (!StringUtils.equals(request.getContractNo(), loan.getBusinessContractNo()))
			throw WebException.instance("放款[" + request.getLoanInfoId() + "]业务合同号不一致，不能导入还款计划");
		if (MoneyUtil.yuan2Cent(request.getFinanceAmount()) != loan.getFinanceAmount())
			throw WebException.instance("放款[" + request.getLoanInfoId() + "]放款金额不一致，不能导入还款计划");
		if (!StringUtils.equals(request.getLoanDate(), loan.getLoanDate()))
			throw WebException.instance("放款[" + request.getLoanInfoId() + "]放款日期不一致，不能导入还款计划");
		if (!StringUtils.equals(request.getDueDate(), loan.getDueDate()))
			throw WebException.instance("放款[" + request.getLoanInfoId() + "]到期日不一致，不能导入还款计划");

		checkDenyPermByConfig(loan.getBusinessContractNo(), "导入还款计划");

		if (underlyingAssetMapper.selectByPrimaryKey(request.getLoanInfoId()) != null)
			throw WebException.instance("放款[" + request.getLoanInfoId() + "]已转为基础资产，不能导入还款计划");
		if (CollectionUtils.isNotEmpty(paids))
			throw WebException.instance("放款[" + request.getLoanInfoId() + "]已存在还款冲销记录，不能导入还款计划");
	}


	/**
	 * 校验放款的放款部分是否合法
	 * @param userVo 操作该放款的用户
	 * @param request 放款请求
	 */
	private void checkEditLoanInfoRequestIsValid(UserVo userVo, EditAssetLoanRequest request) {
		checkOperateUserIsFactor(userVo);
		if (request == null)
			throw new IllegalArgumentException("请求参数不能为空");
		if (StringUtils.isBlank(request.getContractNo()))
			throw WebException.instance("业务合同号不能为空");
		if (StringUtils.isBlank(request.getCounterpartyId()))
			throw WebException.instance("交易对手ID不能为空");
		if (StringUtils.isBlank(request.getFinanceAmount())
				|| !ValidateRules.regexAmountCheck(request.getFinanceAmount())
				|| MoneyUtil.yuan2Cent(request.getFinanceAmount()) <= 0)
			throw WebException.instance("融资金额不正确");
		if (StringUtils.isBlank(request.getCurrency()) || !request.getCurrency().equals(Currency.RMB.name()))
			throw WebException.instance("币种错误");
		if (!EnumUtils.isValidEnum(RepaymentType.class, request.getRepaymentType()))
			throw WebException.instance("不是合法的还款方式");

		RepaymentType repaymentType = RepaymentType.valueOf(request.getRepaymentType());
		if (repaymentType != RepaymentType.ORDER
				&& repaymentType != RepaymentType.MONTHLY
				&& repaymentType != RepaymentType.MATURITY)
			throw WebException.instance("暂不支持的还款方式");

		if (!EnumUtils.isValidEnum(DateUnit.class, request.getLoanPeriodUnit()))
			throw WebException.instance("不是合法的放款期限单位");
		if (request.getLoanPeriod() <= 0)
			throw WebException.instance("放款期限必须大于0");
		if (request.getDayCountConvention() != 360 && request.getDayCountConvention() != 365)
			throw WebException.instance("不支持的计息基准天数");
		if (!EnumUtils.isValidEnum(InterestRateUnit.class, request.getInterestRateUnit()))
			throw WebException.instance("不是合法的利率单位");
		if (!ValidateRules.regexPercentCheck(request.getInterestRate())
				|| MoneyArithUtil.convertStringToInterestRate(request.getInterestRate()).compareTo(BigDecimal.ZERO) <= 0)
			throw WebException.instance("利率不合法");
		if (!DateTimeUtil.validateDate10(request.getLoanDate()))
			throw WebException.instance("不是合法的放款日期");
		if (!DateTimeUtil.validateDate10(request.getDueDate()))
			throw WebException.instance("不是合法的到期日");
		if (request.getDueDate().compareTo(request.getLoanDate()) <= 0)
			throw WebException.instance("到期日必须大于放款日");
		if (!ValidateRules.regexPercentCheck(request.getPenaltyRate())
				|| MoneyArithUtil.convertStringToInterestRate(request.getPenaltyRate()).compareTo(BigDecimal.ZERO) <= 0)
			throw WebException.instance("逾期费率不合法");
		if (StringUtils.isBlank(request.getRepaymentAccountNo()))
			throw WebException.instance("还款账号不能为空");
		if (CollectionUtils.isNotEmpty(request.getExpenses())) {
			Set<String> expenseSubjects = Sets.newHashSet();
			for (EditAssetLoanRequest.Expense expense : request.getExpenses()) {
				if (StringUtils.isBlank(expense.getSubject()))
					throw WebException.instance("费用科目不能为空");
				if (!ValidateRules.regexAmountCheck(expense.getAmount()) || MoneyUtil.yuan2Cent(expense.getAmount()) <= 0)
					throw WebException.instance("费用金额不正确");
				if (expenseSubjects.contains(expense.getSubject()))
					throw WebException.instance("费用科目重复出现：" + expense.getSubject());
				expenseSubjects.add(expense.getSubject());
			}
		}

		if (CollectionUtils.isEmpty(request.getInvoiceInfoIds()))
			throw WebException.instance("应收账款不能为空");

	}


	/**
	 * 校验放款的还款部分是否合法
	 *
	 * @param userVo 操作该放款的用户
	 * @param request 放款请求
	 */
	private void checkEditLoanPlanRequestIsValid(UserVo userVo, EditAssetLoanRequest request) {
		checkOperateUserIsFactor(userVo);
		if (request == null)
			throw new IllegalArgumentException("请求参数不能为空");
		if (StringUtils.isBlank(request.getContractNo()))
			throw WebException.instance("业务合同不能为空");
		if (StringUtils.isBlank(request.getFinanceAmount())
				|| !ValidateRules.regexAmountCheck(request.getFinanceAmount())
				|| MoneyUtil.yuan2Cent(request.getFinanceAmount()) <= 0)
			throw WebException.instance("融资金额不正确");
		if (!DateTimeUtil.validateDate10(request.getLoanDate()))
			throw WebException.instance("不是合法的放款日期");
		if (!DateTimeUtil.validateDate10(request.getDueDate()))
			throw WebException.instance("不是合法的到期日");

		if (CollectionUtils.isEmpty(request.getPlans()))
			throw WebException.instance("还款计划不能为空");
		long totalAccountPricinpal = 0l;
		String shouldBeValueDate = request.getLoanDate();
		long shouldBeFinanceBalance = MoneyUtil.yuan2Cent(request.getFinanceAmount());
		for (LoanPlanDTO plan : request.getPlans()) {
			if (!DateTimeUtil.validateDate10(plan.getValueDate()))
				throw WebException.instance("起息日" + plan.getValueDate() + "不是有效的日期");
			if (!DateTimeUtil.validateDate10(plan.getSettleInterestDate()))
				throw WebException.instance("收益分配日" + plan.getSettleInterestDate() + "不是有效的日期");
			if (!DateTimeUtil.validateDate10(plan.getRepaymentDate()))
				throw WebException.instance("还款日" + plan.getRepaymentDate() + "不是有效的日期");
			long accountPrincipal = MoneyUtil.yuan2Cent(plan.getAccountPrincipal());
			if (accountPrincipal < 0)
				throw WebException.instance("应还本金不能小于0");
			totalAccountPricinpal += accountPrincipal;
			if (MoneyUtil.yuan2Cent(plan.getAccountInterest()) < 0)
				throw WebException.instance("应还利息不能小于0");
			if (shouldBeFinanceBalance != MoneyUtil.yuan2Cent(plan.getFinanceBalance()))
				throw WebException.instance("还款计划中融资余额不正确");
			shouldBeFinanceBalance -= accountPrincipal;

			if (!shouldBeValueDate.equals(plan.getValueDate()))
				throw WebException.instance("还款计划日期不连续");
			if (plan.getRepaymentDate().compareTo(plan.getValueDate()) <= 0)
				throw WebException.instance("还款日必须大于起息日");
			String shouldBeSettleDate = plan.getRepaymentDate();
			if (!shouldBeSettleDate.equals(plan.getSettleInterestDate()))
				throw WebException.instance("还款日必须等于结息日");
			if (!EnumUtils.isValidEnum(SettleStatus.class, plan.getSettleStatus()))
				throw WebException.instance("还款计划结清状态不合法");
			shouldBeValueDate = plan.getRepaymentDate();
		}
		if (!shouldBeValueDate.equals(request.getDueDate()))
			throw WebException.instance("还款计划最后的到期日不正确");
		if (totalAccountPricinpal != MoneyUtil.yuan2Cent(request.getFinanceAmount()))
			throw WebException.instance("应还本金不等于融资金额");
	}

	/**
	 * 检查操作放款的必须是保理商
	 */
	private static void checkOperateUserIsFactor(UserVo userVo) {
		if (userVo == null)
			throw new IllegalArgumentException("登录用户不能为空");
		if (userVo.getCustomer().getUserType() != UserType.FACTOR)
			throw WebException.instance("仅支持保理商编辑该放款");
	}

	@Override
	public boolean paidAssetLoanPlan(UserVo userVo, AssetPaidPlanRequest request) {
		checkPaidPlanRequest(userVo, request);

		logger.info("paidAssetLoanPlan data: {}", new Gson().toJson(request));

		LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(request.getLoanInfoId());
		checkLoanInfoAuthForPaid(userVo, loanInfo);
		checkDenyPermByConfig(loanInfo.getBusinessContractNo(), "还款冲销");

		Credit credit = creditTransactionService.lockByBussinessContractNo(loanInfo.getBusinessContractNo());
		if (credit == null)
			throw WebException.instance("授信额度不存在");

		loanInfo = loanInfoMapper.lockByPrimaryKey(request.getLoanInfoId());
		checkLoanInfoAuthForPaid(userVo, loanInfo);

		List<LoanPlan> plans = getPlansByLoanInfoId(request.getLoanInfoId());

		long paidPrincipal = 0;
		int forwardDays = 0;
		int overdueDays = 0;
		for (AssetPaidPlanRequest.PaidSinglePlan paidPlan : request.getPaidPlans()) {
			LoanPlan unsettledPlan = pickFirstUnsettledPlan(plans);

			paidSinglePlan(userVo, unsettledPlan, paidPlan);

			int diffDays = DateTimeUtil.diffInDay(DateTimeUtil.getDate10(paidPlan.getPaidDate()),
					DateTimeUtil.getDate10(unsettledPlan.getRepaymentDate()));
			int overdueFlag = paidPlan.getPaidDate().compareTo(unsettledPlan.getRepaymentDate());
			if (overdueFlag > 0 && diffDays > overdueDays)
				overdueDays = diffDays;
			else if (overdueFlag < 0 && diffDays > forwardDays)
				forwardDays = diffDays;

			paidPrincipal += MoneyUtil.yuan2Cent(paidPlan.getPaidPrincipal());
		}

		// 如果还了本金，则恢复授信额度
		if (paidPrincipal > 0) {
			creditTransactionService.recoveryLimit(credit.getId(), paidPrincipal, userVo.getUserId());
			loanInfo.setFinanceBalance(loanInfo.getFinanceBalance() - paidPrincipal);
		}
		if (loanInfo.getForwardDays() < forwardDays)
			loanInfo.setForwardDays(forwardDays);
		if (loanInfo.getOverdueDays() < overdueDays)
			loanInfo.setOverdueDays(overdueDays);
		loanInfo.setUpdateTime(new Date());
		loanInfoMapper.updateByPrimaryKeySelective(loanInfo);

		// 冗余结清状态到放款表
		duplicateLoanInfoSettleStatus(request.getLoanInfoId());

		logger.info("paidAssetLoanPlan success");
		return true;
	}

	private void paidSinglePlan(UserVo userVo, LoanPlan unsettledPlan, AssetPaidPlanRequest.PaidSinglePlan paidPlan) {
		// 检查还款计划ID是不是该放款的第一个未结清的还款计划
		if (unsettledPlan == null)
			throw WebException.instance("不存在未结清的还款计划");
		if (!StringUtils.equals(paidPlan.getLoanPlanId(), unsettledPlan.getId()))
			throw WebException.instance("还款顺序出现错误");

		// 检查还款金额是否满足该放款应还金额，结清状态是否满足
		long paidPrincipal = MoneyUtil.yuan2Cent(paidPlan.getPaidPrincipal());
		unsettledPlan.setPaidPrincipal(unsettledPlan.getPaidPrincipal() + paidPrincipal);
		long paidInterest = MoneyUtil.yuan2Cent(paidPlan.getPaidInterest());
		unsettledPlan.setPaidInterest(unsettledPlan.getPaidInterest() + paidInterest);
		long paidOverdue = MoneyUtil.yuan2Cent(paidPlan.getPaidOverdue());
		unsettledPlan.setPaidOverdue(unsettledPlan.getPaidOverdue() + paidOverdue);

		if (unsettledPlan.getPaidPrincipal() > unsettledPlan.getAccountPrincipal())
			throw WebException.instance("已还本金超过了应还本金");
		if (unsettledPlan.getPaidInterest() > unsettledPlan.getAccountInterest())
			throw WebException.instance("已还利息超过了应还利息");
		if (unsettledPlan.getPaidOverdue() > unsettledPlan.getAccountOverdue())
			throw WebException.instance("已还逾期费超过了应还逾期费");

		if (NumberUtil.equals(unsettledPlan.getPaidPrincipal(), unsettledPlan.getAccountPrincipal()) 
				&& NumberUtil.equals(unsettledPlan.getPaidInterest(), unsettledPlan.getAccountInterest())
				&& NumberUtil.equals(unsettledPlan.getPaidOverdue(), unsettledPlan.getAccountOverdue()))
			paidPlan.setSettleStatus(SettleStatus.SETTLED.name());

		if (StringUtils.isEmpty(unsettledPlan.getLastPaidDate())
				|| unsettledPlan.getLastPaidDate().compareTo(paidPlan.getPaidDate()) < 0)
			unsettledPlan.setLastPaidDate(paidPlan.getPaidDate());
		unsettledPlan.setSettleStatus(paidPlan.getSettleStatus());
		unsettledPlan.setUpateTime(new Date());
		loanPlanMapper.updateByPrimaryKeySelective(unsettledPlan);

		// 新增还款记录
		LoanPaid paid = new LoanPaid();
		paid.setId(IDGenerator.generateAssetLoanPaidId());
		paid.setLoanInfoId(unsettledPlan.getLoanInfoId());
		paid.setLoanPlanId(unsettledPlan.getId());
		paid.setPaidAmount(paidPrincipal + paidInterest + paidOverdue);
		paid.setPaidPrincipal(paidPrincipal);
		paid.setPaidInterest(paidInterest);
		paid.setPaidOverdue(paidOverdue);
		paid.setPaidDate(paidPlan.getPaidDate());
		paid.setCreateTime(new Date());
		paid.setUpdateTime(new Date());
		loanPaidMapper.insert(paid);
	}

	private LoanPlan pickFirstUnsettledPlan(List<LoanPlan> plans) {
		if (CollectionUtils.isEmpty(plans))
			return null;
		for (LoanPlan plan : plans) {
			if (SettleStatus.UNSETTLE.name().equals(plan.getSettleStatus()))
				return plan;
		}
		return null;
	}

	private void checkLoanInfoAuthForPaid(UserVo userVo, LoanInfo loanInfo) {
		if (loanInfo == null)
			throw WebException.instance("放款信息不存在");
		if (!StringUtils.equals(userVo.getCustomerId(), loanInfo.getFactorId()))
			throw WebException.instance("无权限操作该放款");
	}

	private void checkPaidPlanRequest(UserVo userVo, AssetPaidPlanRequest request) {
		checkOperateUserIsFactor(userVo);
		if (request == null)
			throw WebException.instance("请求参数不能为空");
		if (StringUtils.isBlank(request.getLoanInfoId()))
			throw WebException.instance("放款ID不能为空");

		if (CollectionUtils.isEmpty(request.getPaidPlans()))
			throw WebException.instance("还款冲销数据不能为空");
		for (AssetPaidPlanRequest.PaidSinglePlan paidPlan : request.getPaidPlans()) {
			if (StringUtils.isBlank(paidPlan.getLoanPlanId()))
				throw WebException.instance("还款计划ID不能为空");
			if (!DateTimeUtil.validateDate10(paidPlan.getPaidDate()))
				throw WebException.instance("入账日期不合法");
			if (!EnumUtils.isValidEnum(SettleStatus.class, paidPlan.getSettleStatus()))
				throw WebException.instance("结清状态不合法");

			long paidPricipal = MoneyUtil.yuan2Cent(paidPlan.getPaidPrincipal());
			if (paidPricipal < 0)
				throw WebException.instance("本金不能小于0");
			long paidInterest = MoneyUtil.yuan2Cent(paidPlan.getPaidInterest());
			if (paidInterest < 0)
				throw WebException.instance("利息不能小于0");
			long paidOverdue = MoneyUtil.yuan2Cent(paidPlan.getPaidOverdue());
			if (paidOverdue < 0)
				throw WebException.instance("逾期费不能小于0");
			if (paidPricipal + paidInterest + paidOverdue <=0)
				throw WebException.instance("本次还款金额必须大于0");
		}
	}

	/**
	 * 当新增、导入或者删除放款时，更新业务的放款状态
	 * @param loans 
	 * @param loanState
	 */
	private void updateBusinessLoanState(Set<String> contractNos, LoanState loanState) {
		if (CollectionUtils.isEmpty(contractNos))
			return;
		if (loanState == null)
			throw new IllegalArgumentException("放款状态不能为空");

		// 调用业务模块提供的更新放款状态接口
		businessTransactionService.updateLoanState(contractNos, loanState);
	}
}

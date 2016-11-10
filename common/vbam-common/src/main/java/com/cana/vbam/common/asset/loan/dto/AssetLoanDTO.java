package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.dto.InvoiceInfoDTO;
import com.cana.vbam.common.asset.enums.BusinessProduct;
import com.cana.vbam.common.repayment.enums.Currency;
import com.cana.vbam.common.repayment.enums.DateUnit;
import com.cana.vbam.common.repayment.enums.InterestRateUnit;
import com.cana.vbam.common.repayment.enums.RepaymentType;
import com.cana.vbam.common.repayment.enums.SettleStatus;

public class AssetLoanDTO implements Serializable {

	private static final long serialVersionUID = -7522455204321230044L;

	/**
     *主键
     */
    private String id;

    /**
     *业务合同号
     */
    private String businessContractNo;
    
    /**
     *创建放款的客户ID
     */
    private String factorId;

    /**
     *交易对手ID
     */
    private String counterpartyId;

    /**
     *融资客户Id
     */
    private String customerId;

    /**
     *融资客户公司名称
     */
    private String customerName;

    /**
     *项目名称
     */
    private String projectName;

    /**
     *业务产品，国内公开 有追索保理
     */
    private BusinessProduct businessProduct;

    /**
     *融资金额
     */
    private Long financeAmount;

    /**
     *融资余额
     */
    private Long financeBalance;

    /**
     *币种 
     */
    private Currency currency;

    /**
     *还本付息方式
     */
    private RepaymentType repaymentMethod;

    /**
     *放款期限单位
     */
    private DateUnit loanPeriodUnit;

    /**
     *放款期限
     */
    private Integer loanPeriod;

    /**
     *计息基准天数，360或者365
     */
    private Integer dayCountConvention;

    /**
     *利率单位
     */
    private InterestRateUnit interestRateUnit;

    /**
     *利率
     */
    private BigDecimal interestRate;

    /**
     *放款日
     */
    private String loanDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *逾期费率，日息
     */
    private BigDecimal penaltyRate;

    /**
     *还款账号
     */
    private String accountNo;

    /**
     *结清状态（已结清、未结清）
     */
    private SettleStatus settleStatus;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;
	
    /**
     * 费用信息
     */
    private List<ExpenseDTO> expenseList; 
    
    /**
     * 应收账款
     */
    private List<InvoiceInfoDTO> invoiceInfoList;
    
    /**
     * 还款信息
     */
    private List<LoanPlanDTO> loanPlanList;
    private boolean existPaidInfo; //是否存在还款
    private boolean underlyingAsset; // 是否是基础资产
    private boolean denyModifyOrPaid; // 拒绝修改和还款冲销，比如是恒顺项目增加的放款

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BusinessProduct getBusinessProduct() {
		return businessProduct;
	}

	public void setBusinessProduct(BusinessProduct businessProduct) {
		this.businessProduct = businessProduct;
	}

	public Long getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(Long financeAmount) {
		this.financeAmount = financeAmount;
	}

	public Long getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(Long financeBalance) {
		this.financeBalance = financeBalance;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public RepaymentType getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(RepaymentType repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public DateUnit getLoanPeriodUnit() {
		return loanPeriodUnit;
	}

	public void setLoanPeriodUnit(DateUnit loanPeriodUnit) {
		this.loanPeriodUnit = loanPeriodUnit;
	}

	public Integer getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(Integer loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public Integer getDayCountConvention() {
		return dayCountConvention;
	}

	public void setDayCountConvention(Integer dayCountConvention) {
		this.dayCountConvention = dayCountConvention;
	}

	public InterestRateUnit getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(InterestRateUnit interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(BigDecimal penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public SettleStatus getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(SettleStatus settleStatus) {
		this.settleStatus = settleStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpateTime() {
		return upateTime;
	}

	public void setUpateTime(Date upateTime) {
		this.upateTime = upateTime;
	}

	public List<ExpenseDTO> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<ExpenseDTO> expenseList) {
		this.expenseList = expenseList;
	}

	public List<InvoiceInfoDTO> getInvoiceInfoList() {
		return invoiceInfoList;
	}

	public void setInvoiceInfoList(List<InvoiceInfoDTO> invoiceInfoList) {
		this.invoiceInfoList = invoiceInfoList;
	}

	public List<LoanPlanDTO> getLoanPlanList() {
		return loanPlanList;
	}

	public void setLoanPlanList(List<LoanPlanDTO> loanPlanList) {
		this.loanPlanList = loanPlanList;
	}

	public boolean isExistPaidInfo() {
		return existPaidInfo;
	}

	public void setExistPaidInfo(boolean existPaidInfo) {
		this.existPaidInfo = existPaidInfo;
	}

	public boolean isUnderlyingAsset() {
		return underlyingAsset;
	}

	public void setUnderlyingAsset(boolean underlyingAsset) {
		this.underlyingAsset = underlyingAsset;
	}

	public boolean isDenyModifyOrPaid() {
		return denyModifyOrPaid;
	}

	public void setDenyModifyOrPaid(boolean denyModifyOrPaid) {
		this.denyModifyOrPaid = denyModifyOrPaid;
	}

    
}

package com.cana.vbam.common.asset.underlyingasset.dto;

import java.util.Date;

import com.cana.vbam.common.dto.Pagination;

/**
 * 基础资产日志返回结果集
 * @author yihong.tang
 */
public class UnderlyingAssetLogDTO extends Pagination {

	private static final long serialVersionUID = 1L;

	 /**
     *日志主键
     */
    private String id;

    /**
     *放款编号
     */
    private String loanInfoId;

    /**
     *业务合同号
     */
    private String businessContractNo;

    /**
     *借款人姓名，即放款中的融资客户公司名称
     */
    private String customerName;

    /**
     *借款人经济类型
     */
    private String customerEconomicCategory;

    /**
     *交易对手名称
     */
    private String counterparty;

    /**
     *交易对手经济类型
     */
    private String counterpartyEconomicCategory;
    
    /**
     *借款人所在地区
     */
    private String customerCity;

    /**
     *借款人所属行业
     */
    private String customerIndustry;
    
    /**
     *交易对手所在地区
     */
    private String counterpartyCity;

    /**
     *交易对手所属行业
     */
    private String counterpartyIndustry;

    /**
     *授信额度
     */
    private String creditLimit;

    /**
     *可用额度
     */
    private String creditBalance;

    /**
     *交易对手非承保额度，保理放款不存在此值
     */
    private String counterpartyLimit;

    /**
     *交易对手非承保余额，保理放款不存在此值
     */
    private String counterpartyBalance;

    /**
     *应收账款金额
     */
    private String invoiceAmount;

    /**
     *应收账款余额
     */
    private String invoiceBalance;

    /**
     *融资金额
     */
    private String financeAmount;

    /**
     *融资余额
     */
    private String financeBalance;

    /**
     *放款日（起息日）
     */
    private String loanDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *还款方式
     */
    private String repaymentMethod;

    /**
     *利率单位
     */
    private String interestRateUnit;

    /**
     *利率(50%)
     */
    private String interestRate;

    /**
     *放款期限
     */
    private String loanPeriod;

    /**
     *基础资产关联的专项计划编号
     */
    private String specialProgramId;
    
    /**
     *基础资产关联的专项计划名称
     */
    private String specialProgramName;

    /**
     *操作人的企业名称
     */
    private String operateCompanyName;

    /**
     *操作人的用户名
     */
    private String operateUsername;

    /**
     *操作类型，@see UnderlyingAssetOperateTypeEnum
     */
    private String operateType;

    /**
     *操作类型，@see UnderlyingAssetOperateTypeEnum(描述)
     */
    private String operateTypeDesc;
    
    /**
     *操作时间
     */
    private Date createTime;

    public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEconomicCategory() {
		return customerEconomicCategory;
	}

	public void setCustomerEconomicCategory(String customerEconomicCategory) {
		this.customerEconomicCategory = customerEconomicCategory;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getCounterpartyEconomicCategory() {
		return counterpartyEconomicCategory;
	}

	public void setCounterpartyEconomicCategory(String counterpartyEconomicCategory) {
		this.counterpartyEconomicCategory = counterpartyEconomicCategory;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerIndustry() {
		return customerIndustry;
	}

	public void setCustomerIndustry(String customerIndustry) {
		this.customerIndustry = customerIndustry;
	}

	public String getCounterpartyCity() {
		return counterpartyCity;
	}

	public void setCounterpartyCity(String counterpartyCity) {
		this.counterpartyCity = counterpartyCity;
	}

	public String getCounterpartyIndustry() {
		return counterpartyIndustry;
	}

	public void setCounterpartyIndustry(String counterpartyIndustry) {
		this.counterpartyIndustry = counterpartyIndustry;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(String creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getCounterpartyLimit() {
		return counterpartyLimit;
	}

	public void setCounterpartyLimit(String counterpartyLimit) {
		this.counterpartyLimit = counterpartyLimit;
	}

	public String getCounterpartyBalance() {
		return counterpartyBalance;
	}

	public void setCounterpartyBalance(String counterpartyBalance) {
		this.counterpartyBalance = counterpartyBalance;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceBalance() {
		return invoiceBalance;
	}

	public void setInvoiceBalance(String invoiceBalance) {
		this.invoiceBalance = invoiceBalance;
	}

	public String getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(String financeAmount) {
		this.financeAmount = financeAmount;
	}

	public String getFinanceBalance() {
		return financeBalance;
	}

	public void setFinanceBalance(String financeBalance) {
		this.financeBalance = financeBalance;
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

	public String getRepaymentMethod() {
		return repaymentMethod;
	}

	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}

	public String getInterestRateUnit() {
		return interestRateUnit;
	}

	public void setInterestRateUnit(String interestRateUnit) {
		this.interestRateUnit = interestRateUnit;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getSpecialProgramId() {
		return specialProgramId;
	}

	public void setSpecialProgramId(String specialProgramId) {
		this.specialProgramId = specialProgramId;
	}

	public String getOperateCompanyName() {
		return operateCompanyName;
	}

	public void setOperateCompanyName(String operateCompanyName) {
		this.operateCompanyName = operateCompanyName;
	}

	public String getOperateUsername() {
		return operateUsername;
	}

	public void setOperateUsername(String operateUsername) {
		this.operateUsername = operateUsername;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperateTypeDesc() {
		return operateTypeDesc;
	}

	public void setOperateTypeDesc(String operateTypeDesc) {
		this.operateTypeDesc = operateTypeDesc;
	}

	public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}

}

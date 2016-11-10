/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.yundaex.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class YundaexLoanInfoRecord implements Serializable {
    /**
     *主键
     */
    private String id;

    /**
     *放款编号
     */
    private String loanNo;

    /**
     *业务模式（保理商+融资商等）
     */
    private String businessMode;

    /**
     *录入方式（excal、手动）
     */
    private String inputMethod;

    /**
     *业务合同号
     */
    private String businessContractNo;

    /**
     *保理商id
     */
    private String factorId;

    /**
     *保理商公司名称（列表显示用）
     */
    private String factorCompany;

    /**
     *融资客户Id
     */
    private String financeId;

    /**
     *融资客户公司名称
     */
    private String financeCompany;

    /**
     *核心企业id
     */
    private String coreCompanyId;

    /**
     *核心企业名称
     */
    private String coreCompanyName;

    /**
     *凭证号码
     */
    private String voucherNo;

    /**
     *币种 
     */
    private String currency;

    /**
     *业务产品名称
     */
    private String businessProduct;

    /**
     *业务产品id
     */
    private String businessProductId;

    /**
     *应收账款金额
     */
    private Long receivablesAmount;

    /**
     *应收账款余额
     */
    private Long receivablesBalance;

    /**
     *融资金额
     */
    private Long financeAmount;

    /**
     *融资余额
     */
    private Long financeBalance;

    /**
     *利率
     */
    private BigDecimal interestRate;

    /**
     *当前监管关系Id，对应account_supervision表
     */
    private String accountSupervisionId;

    /**
     *账号
     */
    private String accountNo;

    /**
     *放款日
     */
    private String loanDate;

    /**
     *到期日
     */
    private String dueDate;

    /**
     *还本付息方式
     */
    private String repaymentMethod;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date upateTime;

    /**
     *放款期限
     */
    private String loanPeriod;

    /**
     *放款期限单位
     */
    private String loanPeriodUnit;

    /**
     *结清状态（已结清、未结清）
     */
    private String settleStatus;

    /**
     *利率单位
     */
    private String interestRateUnit;

    /**
     *业务流水号
     */
    private String businessSeq;

    /**
     *保理商回款账号
     */
    private String factorTransferInAccountNo;

    /**
     *保理商指定账扣时间
     */
    private String deductionTime;

    /**
     *扣款规则
     */
    private String deductionRule;

    /**
     *展期天数
     */
    private Integer extensionDays;

    /**
     *展期上浮利率值 或者是 展期上浮比例
     */
    private BigDecimal extensionRatio;

    /**
     *展期率计算方式
     */
    private String extensionChargeMethod;

    /**
     *提前还款手续费率
     */
    private BigDecimal earlyRepaymentChargeRatio;

    /**
     *罚息率或者是 罚息上浮比例
     */
    private BigDecimal penaltyRate;

    /**
     *罚息计算方式
     */
    private String chargeMethod;

    /**
     *是否使用节假日政策，即还款计划遇节假日时，自动计算展期
     */
    private Boolean useHolidayPolicy;

    private static final long serialVersionUID = 1L;

    /**
     *主键
     */
    public String getId() {
        return id;
    }

    /**
     *主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *放款编号
     */
    public String getLoanNo() {
        return loanNo;
    }

    /**
     *放款编号
     */
    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo == null ? null : loanNo.trim();
    }

    /**
     *业务模式（保理商+融资商等）
     */
    public String getBusinessMode() {
        return businessMode;
    }

    /**
     *业务模式（保理商+融资商等）
     */
    public void setBusinessMode(String businessMode) {
        this.businessMode = businessMode == null ? null : businessMode.trim();
    }

    /**
     *录入方式（excal、手动）
     */
    public String getInputMethod() {
        return inputMethod;
    }

    /**
     *录入方式（excal、手动）
     */
    public void setInputMethod(String inputMethod) {
        this.inputMethod = inputMethod == null ? null : inputMethod.trim();
    }

    /**
     *业务合同号
     */
    public String getBusinessContractNo() {
        return businessContractNo;
    }

    /**
     *业务合同号
     */
    public void setBusinessContractNo(String businessContractNo) {
        this.businessContractNo = businessContractNo == null ? null : businessContractNo.trim();
    }

    /**
     *保理商id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     *保理商id
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId == null ? null : factorId.trim();
    }

    /**
     *保理商公司名称（列表显示用）
     */
    public String getFactorCompany() {
        return factorCompany;
    }

    /**
     *保理商公司名称（列表显示用）
     */
    public void setFactorCompany(String factorCompany) {
        this.factorCompany = factorCompany == null ? null : factorCompany.trim();
    }

    /**
     *融资客户Id
     */
    public String getFinanceId() {
        return financeId;
    }

    /**
     *融资客户Id
     */
    public void setFinanceId(String financeId) {
        this.financeId = financeId == null ? null : financeId.trim();
    }

    /**
     *融资客户公司名称
     */
    public String getFinanceCompany() {
        return financeCompany;
    }

    /**
     *融资客户公司名称
     */
    public void setFinanceCompany(String financeCompany) {
        this.financeCompany = financeCompany == null ? null : financeCompany.trim();
    }

    /**
     *核心企业id
     */
    public String getCoreCompanyId() {
        return coreCompanyId;
    }

    /**
     *核心企业id
     */
    public void setCoreCompanyId(String coreCompanyId) {
        this.coreCompanyId = coreCompanyId == null ? null : coreCompanyId.trim();
    }

    /**
     *核心企业名称
     */
    public String getCoreCompanyName() {
        return coreCompanyName;
    }

    /**
     *核心企业名称
     */
    public void setCoreCompanyName(String coreCompanyName) {
        this.coreCompanyName = coreCompanyName == null ? null : coreCompanyName.trim();
    }

    /**
     *凭证号码
     */
    public String getVoucherNo() {
        return voucherNo;
    }

    /**
     *凭证号码
     */
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo == null ? null : voucherNo.trim();
    }

    /**
     *币种 
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *币种 
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     *业务产品名称
     */
    public String getBusinessProduct() {
        return businessProduct;
    }

    /**
     *业务产品名称
     */
    public void setBusinessProduct(String businessProduct) {
        this.businessProduct = businessProduct == null ? null : businessProduct.trim();
    }

    /**
     *业务产品id
     */
    public String getBusinessProductId() {
        return businessProductId;
    }

    /**
     *业务产品id
     */
    public void setBusinessProductId(String businessProductId) {
        this.businessProductId = businessProductId == null ? null : businessProductId.trim();
    }

    /**
     *应收账款金额
     */
    public Long getReceivablesAmount() {
        return receivablesAmount;
    }

    /**
     *应收账款金额
     */
    public void setReceivablesAmount(Long receivablesAmount) {
        this.receivablesAmount = receivablesAmount;
    }

    /**
     *应收账款余额
     */
    public Long getReceivablesBalance() {
        return receivablesBalance;
    }

    /**
     *应收账款余额
     */
    public void setReceivablesBalance(Long receivablesBalance) {
        this.receivablesBalance = receivablesBalance;
    }

    /**
     *融资金额
     */
    public Long getFinanceAmount() {
        return financeAmount;
    }

    /**
     *融资金额
     */
    public void setFinanceAmount(Long financeAmount) {
        this.financeAmount = financeAmount;
    }

    /**
     *融资余额
     */
    public Long getFinanceBalance() {
        return financeBalance;
    }

    /**
     *融资余额
     */
    public void setFinanceBalance(Long financeBalance) {
        this.financeBalance = financeBalance;
    }

    /**
     *利率
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     *利率
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    /**
     *当前监管关系Id，对应account_supervision表
     */
    public String getAccountSupervisionId() {
        return accountSupervisionId;
    }

    /**
     *当前监管关系Id，对应account_supervision表
     */
    public void setAccountSupervisionId(String accountSupervisionId) {
        this.accountSupervisionId = accountSupervisionId == null ? null : accountSupervisionId.trim();
    }

    /**
     *账号
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     *账号
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    /**
     *放款日
     */
    public String getLoanDate() {
        return loanDate;
    }

    /**
     *放款日
     */
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate == null ? null : loanDate.trim();
    }

    /**
     *到期日
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     *到期日
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate == null ? null : dueDate.trim();
    }

    /**
     *还本付息方式
     */
    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    /**
     *还本付息方式
     */
    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod == null ? null : repaymentMethod.trim();
    }

    /**
     *创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *更新时间
     */
    public Date getUpateTime() {
        return upateTime;
    }

    /**
     *更新时间
     */
    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
    }

    /**
     *放款期限
     */
    public String getLoanPeriod() {
        return loanPeriod;
    }

    /**
     *放款期限
     */
    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod == null ? null : loanPeriod.trim();
    }

    /**
     *放款期限单位
     */
    public String getLoanPeriodUnit() {
        return loanPeriodUnit;
    }

    /**
     *放款期限单位
     */
    public void setLoanPeriodUnit(String loanPeriodUnit) {
        this.loanPeriodUnit = loanPeriodUnit == null ? null : loanPeriodUnit.trim();
    }

    /**
     *结清状态（已结清、未结清）
     */
    public String getSettleStatus() {
        return settleStatus;
    }

    /**
     *结清状态（已结清、未结清）
     */
    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus == null ? null : settleStatus.trim();
    }

    /**
     *利率单位
     */
    public String getInterestRateUnit() {
        return interestRateUnit;
    }

    /**
     *利率单位
     */
    public void setInterestRateUnit(String interestRateUnit) {
        this.interestRateUnit = interestRateUnit == null ? null : interestRateUnit.trim();
    }

    /**
     *业务流水号
     */
    public String getBusinessSeq() {
        return businessSeq;
    }

    /**
     *业务流水号
     */
    public void setBusinessSeq(String businessSeq) {
        this.businessSeq = businessSeq == null ? null : businessSeq.trim();
    }

    /**
     *保理商回款账号
     */
    public String getFactorTransferInAccountNo() {
        return factorTransferInAccountNo;
    }

    /**
     *保理商回款账号
     */
    public void setFactorTransferInAccountNo(String factorTransferInAccountNo) {
        this.factorTransferInAccountNo = factorTransferInAccountNo == null ? null : factorTransferInAccountNo.trim();
    }

    /**
     *保理商指定账扣时间
     */
    public String getDeductionTime() {
        return deductionTime;
    }

    /**
     *保理商指定账扣时间
     */
    public void setDeductionTime(String deductionTime) {
        this.deductionTime = deductionTime == null ? null : deductionTime.trim();
    }

    /**
     *扣款规则
     */
    public String getDeductionRule() {
        return deductionRule;
    }

    /**
     *扣款规则
     */
    public void setDeductionRule(String deductionRule) {
        this.deductionRule = deductionRule == null ? null : deductionRule.trim();
    }

    /**
     *展期天数
     */
    public Integer getExtensionDays() {
        return extensionDays;
    }

    /**
     *展期天数
     */
    public void setExtensionDays(Integer extensionDays) {
        this.extensionDays = extensionDays;
    }

    /**
     *展期上浮利率值 或者是 展期上浮比例
     */
    public BigDecimal getExtensionRatio() {
        return extensionRatio;
    }

    /**
     *展期上浮利率值 或者是 展期上浮比例
     */
    public void setExtensionRatio(BigDecimal extensionRatio) {
        this.extensionRatio = extensionRatio;
    }

    /**
     *展期率计算方式
     */
    public String getExtensionChargeMethod() {
        return extensionChargeMethod;
    }

    /**
     *展期率计算方式
     */
    public void setExtensionChargeMethod(String extensionChargeMethod) {
        this.extensionChargeMethod = extensionChargeMethod == null ? null : extensionChargeMethod.trim();
    }

    /**
     *提前还款手续费率
     */
    public BigDecimal getEarlyRepaymentChargeRatio() {
        return earlyRepaymentChargeRatio;
    }

    /**
     *提前还款手续费率
     */
    public void setEarlyRepaymentChargeRatio(BigDecimal earlyRepaymentChargeRatio) {
        this.earlyRepaymentChargeRatio = earlyRepaymentChargeRatio;
    }

    /**
     *罚息率或者是 罚息上浮比例
     */
    public BigDecimal getPenaltyRate() {
        return penaltyRate;
    }

    /**
     *罚息率或者是 罚息上浮比例
     */
    public void setPenaltyRate(BigDecimal penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    /**
     *罚息计算方式
     */
    public String getChargeMethod() {
        return chargeMethod;
    }

    /**
     *罚息计算方式
     */
    public void setChargeMethod(String chargeMethod) {
        this.chargeMethod = chargeMethod == null ? null : chargeMethod.trim();
    }

    /**
     *是否使用节假日政策，即还款计划遇节假日时，自动计算展期
     */
    public Boolean getUseHolidayPolicy() {
        return useHolidayPolicy;
    }

    /**
     *是否使用节假日政策，即还款计划遇节假日时，自动计算展期
     */
    public void setUseHolidayPolicy(Boolean useHolidayPolicy) {
        this.useHolidayPolicy = useHolidayPolicy;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        YundaexLoanInfoRecord other = (YundaexLoanInfoRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoanNo() == null ? other.getLoanNo() == null : this.getLoanNo().equals(other.getLoanNo()))
            && (this.getBusinessMode() == null ? other.getBusinessMode() == null : this.getBusinessMode().equals(other.getBusinessMode()))
            && (this.getInputMethod() == null ? other.getInputMethod() == null : this.getInputMethod().equals(other.getInputMethod()))
            && (this.getBusinessContractNo() == null ? other.getBusinessContractNo() == null : this.getBusinessContractNo().equals(other.getBusinessContractNo()))
            && (this.getFactorId() == null ? other.getFactorId() == null : this.getFactorId().equals(other.getFactorId()))
            && (this.getFactorCompany() == null ? other.getFactorCompany() == null : this.getFactorCompany().equals(other.getFactorCompany()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getFinanceCompany() == null ? other.getFinanceCompany() == null : this.getFinanceCompany().equals(other.getFinanceCompany()))
            && (this.getCoreCompanyId() == null ? other.getCoreCompanyId() == null : this.getCoreCompanyId().equals(other.getCoreCompanyId()))
            && (this.getCoreCompanyName() == null ? other.getCoreCompanyName() == null : this.getCoreCompanyName().equals(other.getCoreCompanyName()))
            && (this.getVoucherNo() == null ? other.getVoucherNo() == null : this.getVoucherNo().equals(other.getVoucherNo()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getBusinessProduct() == null ? other.getBusinessProduct() == null : this.getBusinessProduct().equals(other.getBusinessProduct()))
            && (this.getBusinessProductId() == null ? other.getBusinessProductId() == null : this.getBusinessProductId().equals(other.getBusinessProductId()))
            && (this.getReceivablesAmount() == null ? other.getReceivablesAmount() == null : this.getReceivablesAmount().equals(other.getReceivablesAmount()))
            && (this.getReceivablesBalance() == null ? other.getReceivablesBalance() == null : this.getReceivablesBalance().equals(other.getReceivablesBalance()))
            && (this.getFinanceAmount() == null ? other.getFinanceAmount() == null : this.getFinanceAmount().equals(other.getFinanceAmount()))
            && (this.getFinanceBalance() == null ? other.getFinanceBalance() == null : this.getFinanceBalance().equals(other.getFinanceBalance()))
            && (this.getInterestRate() == null ? other.getInterestRate() == null : this.getInterestRate().equals(other.getInterestRate()))
            && (this.getAccountSupervisionId() == null ? other.getAccountSupervisionId() == null : this.getAccountSupervisionId().equals(other.getAccountSupervisionId()))
            && (this.getAccountNo() == null ? other.getAccountNo() == null : this.getAccountNo().equals(other.getAccountNo()))
            && (this.getLoanDate() == null ? other.getLoanDate() == null : this.getLoanDate().equals(other.getLoanDate()))
            && (this.getDueDate() == null ? other.getDueDate() == null : this.getDueDate().equals(other.getDueDate()))
            && (this.getRepaymentMethod() == null ? other.getRepaymentMethod() == null : this.getRepaymentMethod().equals(other.getRepaymentMethod()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpateTime() == null ? other.getUpateTime() == null : this.getUpateTime().equals(other.getUpateTime()))
            && (this.getLoanPeriod() == null ? other.getLoanPeriod() == null : this.getLoanPeriod().equals(other.getLoanPeriod()))
            && (this.getLoanPeriodUnit() == null ? other.getLoanPeriodUnit() == null : this.getLoanPeriodUnit().equals(other.getLoanPeriodUnit()))
            && (this.getSettleStatus() == null ? other.getSettleStatus() == null : this.getSettleStatus().equals(other.getSettleStatus()))
            && (this.getInterestRateUnit() == null ? other.getInterestRateUnit() == null : this.getInterestRateUnit().equals(other.getInterestRateUnit()))
            && (this.getBusinessSeq() == null ? other.getBusinessSeq() == null : this.getBusinessSeq().equals(other.getBusinessSeq()))
            && (this.getFactorTransferInAccountNo() == null ? other.getFactorTransferInAccountNo() == null : this.getFactorTransferInAccountNo().equals(other.getFactorTransferInAccountNo()))
            && (this.getDeductionTime() == null ? other.getDeductionTime() == null : this.getDeductionTime().equals(other.getDeductionTime()))
            && (this.getDeductionRule() == null ? other.getDeductionRule() == null : this.getDeductionRule().equals(other.getDeductionRule()))
            && (this.getExtensionDays() == null ? other.getExtensionDays() == null : this.getExtensionDays().equals(other.getExtensionDays()))
            && (this.getExtensionRatio() == null ? other.getExtensionRatio() == null : this.getExtensionRatio().equals(other.getExtensionRatio()))
            && (this.getExtensionChargeMethod() == null ? other.getExtensionChargeMethod() == null : this.getExtensionChargeMethod().equals(other.getExtensionChargeMethod()))
            && (this.getEarlyRepaymentChargeRatio() == null ? other.getEarlyRepaymentChargeRatio() == null : this.getEarlyRepaymentChargeRatio().equals(other.getEarlyRepaymentChargeRatio()))
            && (this.getPenaltyRate() == null ? other.getPenaltyRate() == null : this.getPenaltyRate().equals(other.getPenaltyRate()))
            && (this.getChargeMethod() == null ? other.getChargeMethod() == null : this.getChargeMethod().equals(other.getChargeMethod()))
            && (this.getUseHolidayPolicy() == null ? other.getUseHolidayPolicy() == null : this.getUseHolidayPolicy().equals(other.getUseHolidayPolicy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoanNo() == null) ? 0 : getLoanNo().hashCode());
        result = prime * result + ((getBusinessMode() == null) ? 0 : getBusinessMode().hashCode());
        result = prime * result + ((getInputMethod() == null) ? 0 : getInputMethod().hashCode());
        result = prime * result + ((getBusinessContractNo() == null) ? 0 : getBusinessContractNo().hashCode());
        result = prime * result + ((getFactorId() == null) ? 0 : getFactorId().hashCode());
        result = prime * result + ((getFactorCompany() == null) ? 0 : getFactorCompany().hashCode());
        result = prime * result + ((getFinanceId() == null) ? 0 : getFinanceId().hashCode());
        result = prime * result + ((getFinanceCompany() == null) ? 0 : getFinanceCompany().hashCode());
        result = prime * result + ((getCoreCompanyId() == null) ? 0 : getCoreCompanyId().hashCode());
        result = prime * result + ((getCoreCompanyName() == null) ? 0 : getCoreCompanyName().hashCode());
        result = prime * result + ((getVoucherNo() == null) ? 0 : getVoucherNo().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getBusinessProduct() == null) ? 0 : getBusinessProduct().hashCode());
        result = prime * result + ((getBusinessProductId() == null) ? 0 : getBusinessProductId().hashCode());
        result = prime * result + ((getReceivablesAmount() == null) ? 0 : getReceivablesAmount().hashCode());
        result = prime * result + ((getReceivablesBalance() == null) ? 0 : getReceivablesBalance().hashCode());
        result = prime * result + ((getFinanceAmount() == null) ? 0 : getFinanceAmount().hashCode());
        result = prime * result + ((getFinanceBalance() == null) ? 0 : getFinanceBalance().hashCode());
        result = prime * result + ((getInterestRate() == null) ? 0 : getInterestRate().hashCode());
        result = prime * result + ((getAccountSupervisionId() == null) ? 0 : getAccountSupervisionId().hashCode());
        result = prime * result + ((getAccountNo() == null) ? 0 : getAccountNo().hashCode());
        result = prime * result + ((getLoanDate() == null) ? 0 : getLoanDate().hashCode());
        result = prime * result + ((getDueDate() == null) ? 0 : getDueDate().hashCode());
        result = prime * result + ((getRepaymentMethod() == null) ? 0 : getRepaymentMethod().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpateTime() == null) ? 0 : getUpateTime().hashCode());
        result = prime * result + ((getLoanPeriod() == null) ? 0 : getLoanPeriod().hashCode());
        result = prime * result + ((getLoanPeriodUnit() == null) ? 0 : getLoanPeriodUnit().hashCode());
        result = prime * result + ((getSettleStatus() == null) ? 0 : getSettleStatus().hashCode());
        result = prime * result + ((getInterestRateUnit() == null) ? 0 : getInterestRateUnit().hashCode());
        result = prime * result + ((getBusinessSeq() == null) ? 0 : getBusinessSeq().hashCode());
        result = prime * result + ((getFactorTransferInAccountNo() == null) ? 0 : getFactorTransferInAccountNo().hashCode());
        result = prime * result + ((getDeductionTime() == null) ? 0 : getDeductionTime().hashCode());
        result = prime * result + ((getDeductionRule() == null) ? 0 : getDeductionRule().hashCode());
        result = prime * result + ((getExtensionDays() == null) ? 0 : getExtensionDays().hashCode());
        result = prime * result + ((getExtensionRatio() == null) ? 0 : getExtensionRatio().hashCode());
        result = prime * result + ((getExtensionChargeMethod() == null) ? 0 : getExtensionChargeMethod().hashCode());
        result = prime * result + ((getEarlyRepaymentChargeRatio() == null) ? 0 : getEarlyRepaymentChargeRatio().hashCode());
        result = prime * result + ((getPenaltyRate() == null) ? 0 : getPenaltyRate().hashCode());
        result = prime * result + ((getChargeMethod() == null) ? 0 : getChargeMethod().hashCode());
        result = prime * result + ((getUseHolidayPolicy() == null) ? 0 : getUseHolidayPolicy().hashCode());
        return result;
    }
}
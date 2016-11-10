/**
* Copyright (c) 2015, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.vbam.common.repayment.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepaymentLoanInfoAddDTO implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *放款编号
    */
    private String loanNo;

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
    *凭证号码
    */
    private String voucherNo;

    /**
    *币种 
    */
    private String currency;

    /**
    *业务产品
    */
    private String businessProduct;

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
    *关联的账号Id
    */
    private String accountId;

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
    *业务产品
    */
    public String getBusinessProduct() {
        return businessProduct;
    }

    /**
    *业务产品
    */
    public void setBusinessProduct(String businessProduct) {
        this.businessProduct = businessProduct == null ? null : businessProduct.trim();
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
    *关联的账号Id
    */
    public String getAccountId() {
        return accountId;
    }

    /**
    *关联的账号Id
    */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
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
}
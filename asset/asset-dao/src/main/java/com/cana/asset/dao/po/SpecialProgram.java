/**
 * Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
 */
package com.cana.asset.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SpecialProgram implements Serializable {
    /**
     *主键专项计划编号 
     */
    private String id;

    /**
     *专项计划名称
     */
    private String specialProgramName;

    /**
     *基础资产类型(保理)
     */
    private String underlyingAssetType;

    /**
     *管理人名称
     */
    private String managerName;

    /**
     *管理者id
     */
    private String managerId;

    /**
     *创建者名称
     */
    private String createrName;

    /**
     *创建者id
     */
    private String createrId;

    /**
     *律所
     */
    private String lawFirmName;

    /**
     *会计事务所
     */
    private String accountingFirmName;

    /**
     *监管银行
     */
    private String supervisionBank;

    /**
     *托管银行
     */
    private String custodianBank;

    /**
     *评级机构
     */
    private String ratingAgency;

    /**
     *资产评估机构
     */
    private String assetEvaluationAgency;

    /**
     *预计成立日期
     */
    private String estimateEstablishmentDate;

    /**
     *成立日期
     */
    private String establishmentDate;

    /**
     *预计到期日期
     */
    private String estimateDueDate;

    /**
     *法定到期日期
     */
    private String statutoryDueDate;

    /**
     *状态
     */
    private String status;

    /**
     *封包日期
     */
    private String encapsulationDate;

    /**
     *续存期
     */
    private Integer renewalPeriod;

    /**
     *总规模
     */
    private Long gross;

    /**
     *优先A
     */
    private Long preferA;

    /**
     *优先B
     */
    private Long preferB;

    /**
     *次级
     */
    private Long defer;

    /**
     *增信方式
     */
    private String trustMethod;

    /**
     *资产池本金余额
     */
    private Long assetPoolPrincipalBalance;

    /**
     *合同笔数
     */
    private Integer contractNum;

    /**
     *加权平均合同期限
     */
    private Integer weightedAverageContractPeriod;

    /**
     *融资人数量
     */
    private Integer financeNum;

    /**
     *加权平均利率
     */
    private BigDecimal weightedAverageInterestRate;

    /**
     *是否是循环购买结构
     */
    private Boolean cyclePurchaseStructure;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *删除标志默认为０
     */
    private Boolean deleted;

    /**
     *循环期
     */
    private Integer cyclePeriod;

    /**
     *资金归集日
     */
    private String capitalAccumulationDate;

    /**
     *循环购买日
     */
    private String cyclePurchaseDate;

    /**
     *收入回收日
     */
    private String incomeRecoveryDate;

    /**
     *回收款转付日
     */
    private String recyclingPaymentDate;

    /**
     *超额资金
     */
    private Long excessFund;

    /**
     *循环购买条款
     */
    private String cyclePurchaseTerm;

    /**
     *基础资产合格标准
     */
    private String underlyingAssetQualityStandard;

    /**
     *循环购买的加权平均利率
     */
    private BigDecimal cycleWeightedAverageInterestRate;

    private static final long serialVersionUID = 1L;

    /**
     *主键专项计划编号 
     */
    public String getId() {
        return id;
    }

    /**
     *主键专项计划编号 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *专项计划名称
     */
    public String getSpecialProgramName() {
        return specialProgramName;
    }

    /**
     *专项计划名称
     */
    public void setSpecialProgramName(String specialProgramName) {
        this.specialProgramName = specialProgramName == null ? null : specialProgramName.trim();
    }

    /**
     *基础资产类型(保理)
     */
    public String getUnderlyingAssetType() {
        return underlyingAssetType;
    }

    /**
     *基础资产类型(保理)
     */
    public void setUnderlyingAssetType(String underlyingAssetType) {
        this.underlyingAssetType = underlyingAssetType == null ? null : underlyingAssetType.trim();
    }

    /**
     *管理人名称
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     *管理人名称
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     *管理者id
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     *管理者id
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     *创建者名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     *创建者名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     *创建者id
     */
    public String getCreaterId() {
        return createrId;
    }

    /**
     *创建者id
     */
    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    /**
     *律所
     */
    public String getLawFirmName() {
        return lawFirmName;
    }

    /**
     *律所
     */
    public void setLawFirmName(String lawFirmName) {
        this.lawFirmName = lawFirmName == null ? null : lawFirmName.trim();
    }

    /**
     *会计事务所
     */
    public String getAccountingFirmName() {
        return accountingFirmName;
    }

    /**
     *会计事务所
     */
    public void setAccountingFirmName(String accountingFirmName) {
        this.accountingFirmName = accountingFirmName == null ? null : accountingFirmName.trim();
    }

    /**
     *监管银行
     */
    public String getSupervisionBank() {
        return supervisionBank;
    }

    /**
     *监管银行
     */
    public void setSupervisionBank(String supervisionBank) {
        this.supervisionBank = supervisionBank == null ? null : supervisionBank.trim();
    }

    /**
     *托管银行
     */
    public String getCustodianBank() {
        return custodianBank;
    }

    /**
     *托管银行
     */
    public void setCustodianBank(String custodianBank) {
        this.custodianBank = custodianBank == null ? null : custodianBank.trim();
    }

    /**
     *评级机构
     */
    public String getRatingAgency() {
        return ratingAgency;
    }

    /**
     *评级机构
     */
    public void setRatingAgency(String ratingAgency) {
        this.ratingAgency = ratingAgency == null ? null : ratingAgency.trim();
    }

    /**
     *资产评估机构
     */
    public String getAssetEvaluationAgency() {
        return assetEvaluationAgency;
    }

    /**
     *资产评估机构
     */
    public void setAssetEvaluationAgency(String assetEvaluationAgency) {
        this.assetEvaluationAgency = assetEvaluationAgency == null ? null : assetEvaluationAgency.trim();
    }

    /**
     *预计成立日期
     */
    public String getEstimateEstablishmentDate() {
        return estimateEstablishmentDate;
    }

    /**
     *预计成立日期
     */
    public void setEstimateEstablishmentDate(String estimateEstablishmentDate) {
        this.estimateEstablishmentDate = estimateEstablishmentDate == null ? null : estimateEstablishmentDate.trim();
    }

    /**
     *成立日期
     */
    public String getEstablishmentDate() {
        return establishmentDate;
    }

    /**
     *成立日期
     */
    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate == null ? null : establishmentDate.trim();
    }

    /**
     *预计到期日期
     */
    public String getEstimateDueDate() {
        return estimateDueDate;
    }

    /**
     *预计到期日期
     */
    public void setEstimateDueDate(String estimateDueDate) {
        this.estimateDueDate = estimateDueDate == null ? null : estimateDueDate.trim();
    }

    /**
     *法定到期日期
     */
    public String getStatutoryDueDate() {
        return statutoryDueDate;
    }

    /**
     *法定到期日期
     */
    public void setStatutoryDueDate(String statutoryDueDate) {
        this.statutoryDueDate = statutoryDueDate == null ? null : statutoryDueDate.trim();
    }

    /**
     *状态
     */
    public String getStatus() {
        return status;
    }

    /**
     *状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     *封包日期
     */
    public String getEncapsulationDate() {
        return encapsulationDate;
    }

    /**
     *封包日期
     */
    public void setEncapsulationDate(String encapsulationDate) {
        this.encapsulationDate = encapsulationDate == null ? null : encapsulationDate.trim();
    }

    /**
     *续存期
     */
    public Integer getRenewalPeriod() {
        return renewalPeriod;
    }

    /**
     *续存期
     */
    public void setRenewalPeriod(Integer renewalPeriod) {
        this.renewalPeriod = renewalPeriod;
    }

    /**
     *总规模
     */
    public Long getGross() {
        return gross;
    }

    /**
     *总规模
     */
    public void setGross(Long gross) {
        this.gross = gross;
    }

    /**
     *优先A
     */
    public Long getPreferA() {
        return preferA;
    }

    /**
     *优先A
     */
    public void setPreferA(Long preferA) {
        this.preferA = preferA;
    }

    /**
     *优先B
     */
    public Long getPreferB() {
        return preferB;
    }

    /**
     *优先B
     */
    public void setPreferB(Long preferB) {
        this.preferB = preferB;
    }

    /**
     *次级
     */
    public Long getDefer() {
        return defer;
    }

    /**
     *次级
     */
    public void setDefer(Long defer) {
        this.defer = defer;
    }

    /**
     *增信方式
     */
    public String getTrustMethod() {
        return trustMethod;
    }

    /**
     *增信方式
     */
    public void setTrustMethod(String trustMethod) {
        this.trustMethod = trustMethod == null ? null : trustMethod.trim();
    }

    /**
     *资产池本金余额
     */
    public Long getAssetPoolPrincipalBalance() {
        return assetPoolPrincipalBalance;
    }

    /**
     *资产池本金余额
     */
    public void setAssetPoolPrincipalBalance(Long assetPoolPrincipalBalance) {
        this.assetPoolPrincipalBalance = assetPoolPrincipalBalance;
    }

    /**
     *合同笔数
     */
    public Integer getContractNum() {
        return contractNum;
    }

    /**
     *合同笔数
     */
    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    /**
     *加权平均合同期限
     */
    public Integer getWeightedAverageContractPeriod() {
        return weightedAverageContractPeriod;
    }

    /**
     *加权平均合同期限
     */
    public void setWeightedAverageContractPeriod(Integer weightedAverageContractPeriod) {
        this.weightedAverageContractPeriod = weightedAverageContractPeriod;
    }

    /**
     *融资人数量
     */
    public Integer getFinanceNum() {
        return financeNum;
    }

    /**
     *融资人数量
     */
    public void setFinanceNum(Integer financeNum) {
        this.financeNum = financeNum;
    }

    /**
     *加权平均利率
     */
    public BigDecimal getWeightedAverageInterestRate() {
        return weightedAverageInterestRate;
    }

    /**
     *加权平均利率
     */
    public void setWeightedAverageInterestRate(BigDecimal weightedAverageInterestRate) {
        this.weightedAverageInterestRate = weightedAverageInterestRate;
    }

    /**
     *是否是循环购买结构
     */
    public Boolean getCyclePurchaseStructure() {
        return cyclePurchaseStructure;
    }

    /**
     *是否是循环购买结构
     */
    public void setCyclePurchaseStructure(Boolean cyclePurchaseStructure) {
        this.cyclePurchaseStructure = cyclePurchaseStructure;
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
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *删除标志默认为０
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     *删除标志默认为０
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     *循环期
     */
    public Integer getCyclePeriod() {
        return cyclePeriod;
    }

    /**
     *循环期
     */
    public void setCyclePeriod(Integer cyclePeriod) {
        this.cyclePeriod = cyclePeriod;
    }

    /**
     *资金归集日
     */
    public String getCapitalAccumulationDate() {
        return capitalAccumulationDate;
    }

    /**
     *资金归集日
     */
    public void setCapitalAccumulationDate(String capitalAccumulationDate) {
        this.capitalAccumulationDate = capitalAccumulationDate == null ? null : capitalAccumulationDate.trim();
    }

    /**
     *循环购买日
     */
    public String getCyclePurchaseDate() {
        return cyclePurchaseDate;
    }

    /**
     *循环购买日
     */
    public void setCyclePurchaseDate(String cyclePurchaseDate) {
        this.cyclePurchaseDate = cyclePurchaseDate == null ? null : cyclePurchaseDate.trim();
    }

    /**
     *收入回收日
     */
    public String getIncomeRecoveryDate() {
        return incomeRecoveryDate;
    }

    /**
     *收入回收日
     */
    public void setIncomeRecoveryDate(String incomeRecoveryDate) {
        this.incomeRecoveryDate = incomeRecoveryDate == null ? null : incomeRecoveryDate.trim();
    }

    /**
     *回收款转付日
     */
    public String getRecyclingPaymentDate() {
        return recyclingPaymentDate;
    }

    /**
     *回收款转付日
     */
    public void setRecyclingPaymentDate(String recyclingPaymentDate) {
        this.recyclingPaymentDate = recyclingPaymentDate == null ? null : recyclingPaymentDate.trim();
    }

    /**
     *超额资金
     */
    public Long getExcessFund() {
        return excessFund;
    }

    /**
     *超额资金
     */
    public void setExcessFund(Long excessFund) {
        this.excessFund = excessFund;
    }

    /**
     *循环购买条款
     */
    public String getCyclePurchaseTerm() {
        return cyclePurchaseTerm;
    }

    /**
     *循环购买条款
     */
    public void setCyclePurchaseTerm(String cyclePurchaseTerm) {
        this.cyclePurchaseTerm = cyclePurchaseTerm == null ? null : cyclePurchaseTerm.trim();
    }

    /**
     *基础资产合格标准
     */
    public String getUnderlyingAssetQualityStandard() {
        return underlyingAssetQualityStandard;
    }

    /**
     *基础资产合格标准
     */
    public void setUnderlyingAssetQualityStandard(String underlyingAssetQualityStandard) {
        this.underlyingAssetQualityStandard = underlyingAssetQualityStandard == null ? null : underlyingAssetQualityStandard.trim();
    }

    /**
     *循环购买的加权平均利率
     */
    public BigDecimal getCycleWeightedAverageInterestRate() {
        return cycleWeightedAverageInterestRate;
    }

    /**
     *循环购买的加权平均利率
     */
    public void setCycleWeightedAverageInterestRate(BigDecimal cycleWeightedAverageInterestRate) {
        this.cycleWeightedAverageInterestRate = cycleWeightedAverageInterestRate;
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
        SpecialProgram other = (SpecialProgram) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpecialProgramName() == null ? other.getSpecialProgramName() == null : this.getSpecialProgramName().equals(other.getSpecialProgramName()))
            && (this.getUnderlyingAssetType() == null ? other.getUnderlyingAssetType() == null : this.getUnderlyingAssetType().equals(other.getUnderlyingAssetType()))
            && (this.getManagerName() == null ? other.getManagerName() == null : this.getManagerName().equals(other.getManagerName()))
            && (this.getManagerId() == null ? other.getManagerId() == null : this.getManagerId().equals(other.getManagerId()))
            && (this.getCreaterName() == null ? other.getCreaterName() == null : this.getCreaterName().equals(other.getCreaterName()))
            && (this.getCreaterId() == null ? other.getCreaterId() == null : this.getCreaterId().equals(other.getCreaterId()))
            && (this.getLawFirmName() == null ? other.getLawFirmName() == null : this.getLawFirmName().equals(other.getLawFirmName()))
            && (this.getAccountingFirmName() == null ? other.getAccountingFirmName() == null : this.getAccountingFirmName().equals(other.getAccountingFirmName()))
            && (this.getSupervisionBank() == null ? other.getSupervisionBank() == null : this.getSupervisionBank().equals(other.getSupervisionBank()))
            && (this.getCustodianBank() == null ? other.getCustodianBank() == null : this.getCustodianBank().equals(other.getCustodianBank()))
            && (this.getRatingAgency() == null ? other.getRatingAgency() == null : this.getRatingAgency().equals(other.getRatingAgency()))
            && (this.getAssetEvaluationAgency() == null ? other.getAssetEvaluationAgency() == null : this.getAssetEvaluationAgency().equals(other.getAssetEvaluationAgency()))
            && (this.getEstimateEstablishmentDate() == null ? other.getEstimateEstablishmentDate() == null : this.getEstimateEstablishmentDate().equals(other.getEstimateEstablishmentDate()))
            && (this.getEstablishmentDate() == null ? other.getEstablishmentDate() == null : this.getEstablishmentDate().equals(other.getEstablishmentDate()))
            && (this.getEstimateDueDate() == null ? other.getEstimateDueDate() == null : this.getEstimateDueDate().equals(other.getEstimateDueDate()))
            && (this.getStatutoryDueDate() == null ? other.getStatutoryDueDate() == null : this.getStatutoryDueDate().equals(other.getStatutoryDueDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getEncapsulationDate() == null ? other.getEncapsulationDate() == null : this.getEncapsulationDate().equals(other.getEncapsulationDate()))
            && (this.getRenewalPeriod() == null ? other.getRenewalPeriod() == null : this.getRenewalPeriod().equals(other.getRenewalPeriod()))
            && (this.getGross() == null ? other.getGross() == null : this.getGross().equals(other.getGross()))
            && (this.getPreferA() == null ? other.getPreferA() == null : this.getPreferA().equals(other.getPreferA()))
            && (this.getPreferB() == null ? other.getPreferB() == null : this.getPreferB().equals(other.getPreferB()))
            && (this.getDefer() == null ? other.getDefer() == null : this.getDefer().equals(other.getDefer()))
            && (this.getTrustMethod() == null ? other.getTrustMethod() == null : this.getTrustMethod().equals(other.getTrustMethod()))
            && (this.getAssetPoolPrincipalBalance() == null ? other.getAssetPoolPrincipalBalance() == null : this.getAssetPoolPrincipalBalance().equals(other.getAssetPoolPrincipalBalance()))
            && (this.getContractNum() == null ? other.getContractNum() == null : this.getContractNum().equals(other.getContractNum()))
            && (this.getWeightedAverageContractPeriod() == null ? other.getWeightedAverageContractPeriod() == null : this.getWeightedAverageContractPeriod().equals(other.getWeightedAverageContractPeriod()))
            && (this.getFinanceNum() == null ? other.getFinanceNum() == null : this.getFinanceNum().equals(other.getFinanceNum()))
            && (this.getWeightedAverageInterestRate() == null ? other.getWeightedAverageInterestRate() == null : this.getWeightedAverageInterestRate().equals(other.getWeightedAverageInterestRate()))
            && (this.getCyclePurchaseStructure() == null ? other.getCyclePurchaseStructure() == null : this.getCyclePurchaseStructure().equals(other.getCyclePurchaseStructure()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCyclePeriod() == null ? other.getCyclePeriod() == null : this.getCyclePeriod().equals(other.getCyclePeriod()))
            && (this.getCapitalAccumulationDate() == null ? other.getCapitalAccumulationDate() == null : this.getCapitalAccumulationDate().equals(other.getCapitalAccumulationDate()))
            && (this.getCyclePurchaseDate() == null ? other.getCyclePurchaseDate() == null : this.getCyclePurchaseDate().equals(other.getCyclePurchaseDate()))
            && (this.getIncomeRecoveryDate() == null ? other.getIncomeRecoveryDate() == null : this.getIncomeRecoveryDate().equals(other.getIncomeRecoveryDate()))
            && (this.getRecyclingPaymentDate() == null ? other.getRecyclingPaymentDate() == null : this.getRecyclingPaymentDate().equals(other.getRecyclingPaymentDate()))
            && (this.getExcessFund() == null ? other.getExcessFund() == null : this.getExcessFund().equals(other.getExcessFund()))
            && (this.getCyclePurchaseTerm() == null ? other.getCyclePurchaseTerm() == null : this.getCyclePurchaseTerm().equals(other.getCyclePurchaseTerm()))
            && (this.getUnderlyingAssetQualityStandard() == null ? other.getUnderlyingAssetQualityStandard() == null : this.getUnderlyingAssetQualityStandard().equals(other.getUnderlyingAssetQualityStandard()))
            && (this.getCycleWeightedAverageInterestRate() == null ? other.getCycleWeightedAverageInterestRate() == null : this.getCycleWeightedAverageInterestRate().equals(other.getCycleWeightedAverageInterestRate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpecialProgramName() == null) ? 0 : getSpecialProgramName().hashCode());
        result = prime * result + ((getUnderlyingAssetType() == null) ? 0 : getUnderlyingAssetType().hashCode());
        result = prime * result + ((getManagerName() == null) ? 0 : getManagerName().hashCode());
        result = prime * result + ((getManagerId() == null) ? 0 : getManagerId().hashCode());
        result = prime * result + ((getCreaterName() == null) ? 0 : getCreaterName().hashCode());
        result = prime * result + ((getCreaterId() == null) ? 0 : getCreaterId().hashCode());
        result = prime * result + ((getLawFirmName() == null) ? 0 : getLawFirmName().hashCode());
        result = prime * result + ((getAccountingFirmName() == null) ? 0 : getAccountingFirmName().hashCode());
        result = prime * result + ((getSupervisionBank() == null) ? 0 : getSupervisionBank().hashCode());
        result = prime * result + ((getCustodianBank() == null) ? 0 : getCustodianBank().hashCode());
        result = prime * result + ((getRatingAgency() == null) ? 0 : getRatingAgency().hashCode());
        result = prime * result + ((getAssetEvaluationAgency() == null) ? 0 : getAssetEvaluationAgency().hashCode());
        result = prime * result + ((getEstimateEstablishmentDate() == null) ? 0 : getEstimateEstablishmentDate().hashCode());
        result = prime * result + ((getEstablishmentDate() == null) ? 0 : getEstablishmentDate().hashCode());
        result = prime * result + ((getEstimateDueDate() == null) ? 0 : getEstimateDueDate().hashCode());
        result = prime * result + ((getStatutoryDueDate() == null) ? 0 : getStatutoryDueDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getEncapsulationDate() == null) ? 0 : getEncapsulationDate().hashCode());
        result = prime * result + ((getRenewalPeriod() == null) ? 0 : getRenewalPeriod().hashCode());
        result = prime * result + ((getGross() == null) ? 0 : getGross().hashCode());
        result = prime * result + ((getPreferA() == null) ? 0 : getPreferA().hashCode());
        result = prime * result + ((getPreferB() == null) ? 0 : getPreferB().hashCode());
        result = prime * result + ((getDefer() == null) ? 0 : getDefer().hashCode());
        result = prime * result + ((getTrustMethod() == null) ? 0 : getTrustMethod().hashCode());
        result = prime * result + ((getAssetPoolPrincipalBalance() == null) ? 0 : getAssetPoolPrincipalBalance().hashCode());
        result = prime * result + ((getContractNum() == null) ? 0 : getContractNum().hashCode());
        result = prime * result + ((getWeightedAverageContractPeriod() == null) ? 0 : getWeightedAverageContractPeriod().hashCode());
        result = prime * result + ((getFinanceNum() == null) ? 0 : getFinanceNum().hashCode());
        result = prime * result + ((getWeightedAverageInterestRate() == null) ? 0 : getWeightedAverageInterestRate().hashCode());
        result = prime * result + ((getCyclePurchaseStructure() == null) ? 0 : getCyclePurchaseStructure().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCyclePeriod() == null) ? 0 : getCyclePeriod().hashCode());
        result = prime * result + ((getCapitalAccumulationDate() == null) ? 0 : getCapitalAccumulationDate().hashCode());
        result = prime * result + ((getCyclePurchaseDate() == null) ? 0 : getCyclePurchaseDate().hashCode());
        result = prime * result + ((getIncomeRecoveryDate() == null) ? 0 : getIncomeRecoveryDate().hashCode());
        result = prime * result + ((getRecyclingPaymentDate() == null) ? 0 : getRecyclingPaymentDate().hashCode());
        result = prime * result + ((getExcessFund() == null) ? 0 : getExcessFund().hashCode());
        result = prime * result + ((getCyclePurchaseTerm() == null) ? 0 : getCyclePurchaseTerm().hashCode());
        result = prime * result + ((getUnderlyingAssetQualityStandard() == null) ? 0 : getUnderlyingAssetQualityStandard().hashCode());
        result = prime * result + ((getCycleWeightedAverageInterestRate() == null) ? 0 : getCycleWeightedAverageInterestRate().hashCode());
        return result;
    }
}
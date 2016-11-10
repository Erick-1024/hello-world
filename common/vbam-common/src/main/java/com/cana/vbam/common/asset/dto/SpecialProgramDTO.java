package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;

/**
 * @author jiangzhou.Ren
 * @time 2016年8月29日上午10:15:27
 * SpecialProgramDTO
 */
public class SpecialProgramDTO implements Serializable{

	private static final long serialVersionUID = -1203407546931832448L;
	
	//原始权益人
	private List<OriginatorDTO> originators;
	
	//资产服务机构
	private List<ServiceAgencyDTO> serviceAgencys;
	
		/**
     *删除标志默认为０表示未删除１表示删除
     */
    private Boolean deleted;
	
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
    private BasicAssetType underlyingAssetType;

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
     * 托管银行
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
    private SpecialProgramStatus status;
    
    

    /**
     *续存期
     */
    private Integer renewalPeriod;

    /**
     *总规模
     */
    private String gross;

    /**
     *优先A
     */
    private String preferA;

    /**
     *优先B
     */
    private String preferB;

    /**
     *次级
     */
    private String defer;

    /**
     *增信方式
     */
    private String trustMethod;

    /**
     *资产池本金余额
     */
    private String assetPoolPrincipalBalance;

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
    private String excessFund;

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
    
    
    
    public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}

	
	public BasicAssetType getUnderlyingAssetType() {
		return underlyingAssetType;
	}

	public void setUnderlyingAssetType(BasicAssetType underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getLawFirmName() {
		return lawFirmName;
	}

	public void setLawFirmName(String lawFirmName) {
		this.lawFirmName = lawFirmName;
	}

	public String getAccountingFirmName() {
		return accountingFirmName;
	}

	public void setAccountingFirmName(String accountingFirmName) {
		this.accountingFirmName = accountingFirmName;
	}

	public String getSupervisionBank() {
		return supervisionBank;
	}

	public void setSupervisionBank(String supervisionBank) {
		this.supervisionBank = supervisionBank;
	}

	public String getRatingAgency() {
		return ratingAgency;
	}

	public void setRatingAgency(String ratingAgency) {
		this.ratingAgency = ratingAgency;
	}

	public String getAssetEvaluationAgency() {
		return assetEvaluationAgency;
	}

	public void setAssetEvaluationAgency(String assetEvaluationAgency) {
		this.assetEvaluationAgency = assetEvaluationAgency;
	}

	public String getEstimateEstablishmentDate() {
		return estimateEstablishmentDate;
	}

	public void setEstimateEstablishmentDate(String estimateEstablishmentDate) {
		this.estimateEstablishmentDate = estimateEstablishmentDate;
	}

	public String getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(String establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public String getEstimateDueDate() {
		return estimateDueDate;
	}

	public void setEstimateDueDate(String estimateDueDate) {
		this.estimateDueDate = estimateDueDate;
	}

	public String getStatutoryDueDate() {
		return statutoryDueDate;
	}

	public void setStatutoryDueDate(String statutoryDueDate) {
		this.statutoryDueDate = statutoryDueDate;
	}

	
	public SpecialProgramStatus getStatus() {
		return status;
	}

	public void setStatus(SpecialProgramStatus status) {
		this.status = status;
	}

	public Integer getRenewalPeriod() {
		return renewalPeriod;
	}

	public void setRenewalPeriod(Integer renewalPeriod) {
		this.renewalPeriod = renewalPeriod;
	}

	
	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getPreferA() {
		return preferA;
	}

	public void setPreferA(String preferA) {
		this.preferA = preferA;
	}

	public String getPreferB() {
		return preferB;
	}

	public void setPreferB(String preferB) {
		this.preferB = preferB;
	}

	public String getDefer() {
		return defer;
	}

	public void setDefer(String defer) {
		this.defer = defer;
	}

	public String getTrustMethod() {
		return trustMethod;
	}

	public void setTrustMethod(String trustMethod) {
		this.trustMethod = trustMethod;
	}


	public Integer getContractNum() {
		return contractNum;
	}

	public void setContractNum(Integer contractNum) {
		this.contractNum = contractNum;
	}

	public Integer getWeightedAverageContractPeriod() {
		return weightedAverageContractPeriod;
	}

	public void setWeightedAverageContractPeriod(Integer weightedAverageContractPeriod) {
		this.weightedAverageContractPeriod = weightedAverageContractPeriod;
	}

	public Integer getFinanceNum() {
		return financeNum;
	}

	public void setFinanceNum(Integer financeNum) {
		this.financeNum = financeNum;
	}

	public BigDecimal getWeightedAverageInterestRate() {
		return weightedAverageInterestRate;
	}

	public void setWeightedAverageInterestRate(BigDecimal weightedAverageInterestRate) {
		this.weightedAverageInterestRate = weightedAverageInterestRate;
	}

	public Boolean getCyclePurchaseStructure() {
		return cyclePurchaseStructure;
	}

	public void setCyclePurchaseStructure(Boolean cyclePurchaseStructure) {
		this.cyclePurchaseStructure = cyclePurchaseStructure;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCustodianBank() {
		return custodianBank;
	}

	public void setCustodianBank(String custodianBank) {
		this.custodianBank = custodianBank;
	}

	public Integer getCyclePeriod() {
		return cyclePeriod;
	}

	public void setCyclePeriod(Integer cyclePeriod) {
		this.cyclePeriod = cyclePeriod;
	}

	public String getCapitalAccumulationDate() {
		return capitalAccumulationDate;
	}

	public void setCapitalAccumulationDate(String capitalAccumulationDate) {
		this.capitalAccumulationDate = capitalAccumulationDate;
	}

	public String getCyclePurchaseDate() {
		return cyclePurchaseDate;
	}

	public void setCyclePurchaseDate(String cyclePurchaseDate) {
		this.cyclePurchaseDate = cyclePurchaseDate;
	}

	public String getIncomeRecoveryDate() {
		return incomeRecoveryDate;
	}

	public void setIncomeRecoveryDate(String incomeRecoveryDate) {
		this.incomeRecoveryDate = incomeRecoveryDate;
	}

	public String getRecyclingPaymentDate() {
		return recyclingPaymentDate;
	}

	public void setRecyclingPaymentDate(String recyclingPaymentDate) {
		this.recyclingPaymentDate = recyclingPaymentDate;
	}

	
	public String getAssetPoolPrincipalBalance() {
		return assetPoolPrincipalBalance;
	}

	public void setAssetPoolPrincipalBalance(String assetPoolPrincipalBalance) {
		this.assetPoolPrincipalBalance = assetPoolPrincipalBalance;
	}

	public String getExcessFund() {
		return excessFund;
	}

	public void setExcessFund(String excessFund) {
		this.excessFund = excessFund;
	}

	public String getCyclePurchaseTerm() {
		return cyclePurchaseTerm;
	}

	public void setCyclePurchaseTerm(String cyclePurchaseTerm) {
		this.cyclePurchaseTerm = cyclePurchaseTerm;
	}

	public String getUnderlyingAssetQualityStandard() {
		return underlyingAssetQualityStandard;
	}

	public void setUnderlyingAssetQualityStandard(String underlyingAssetQualityStandard) {
		this.underlyingAssetQualityStandard = underlyingAssetQualityStandard;
	}

	public BigDecimal getCycleWeightedAverageInterestRate() {
		return cycleWeightedAverageInterestRate;
	}

	public void setCycleWeightedAverageInterestRate(BigDecimal cycleWeightedAverageInterestRate) {
		this.cycleWeightedAverageInterestRate = cycleWeightedAverageInterestRate;
	}
    
	public List<OriginatorDTO> getOriginators() {
		return originators;
	}

	public void setOriginators(List<OriginatorDTO> originators) {
		this.originators = originators;
	}

	public List<ServiceAgencyDTO> getServiceAgencys() {
		return serviceAgencys;
	}

	public void setServiceAgencys(List<ServiceAgencyDTO> serviceAgencys) {
		this.serviceAgencys = serviceAgencys;
	}
	

    
	
}

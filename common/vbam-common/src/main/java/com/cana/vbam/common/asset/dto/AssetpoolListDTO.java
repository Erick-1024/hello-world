package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.asset.enums.BasicAssetType;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;

public class AssetpoolListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2978971524438510099L;

	/**
	 * 专项计划编号
	 */
	private String id ;
	
	/**
	 * 专项计划名称
	 */
	private String specialProgramName;
	
	/**
	 * 基础资产类型
	 */
	private BasicAssetType underlyingAssetType;
	
	private String underlyingAssetTypeDesc;
	
	/**
	 * 管理人id
	 */
	private String managerId;
	
	/**
	 * 管理人名称
	 */
	private String managerName;
	
	/** 预计成立日期   */
	private String estimateEstablishmentDate;
	
	/** 初始资产池规模(未封包前)   */
	private String originAssetpoolScale;
	
	/** 状态  */
	private SpecialProgramStatus status;
	
	private String statusDesc;
	
	/** 是否是循环购买结构 */
	private Boolean cyclePurchaseStructure;
	
	/** 当日应还收入   */
	private String accountIncome;
	
	/** 当日应还本金   */
	private String accountPrincipal;
	
	/** 当日应还总额   */
	private String accountAmount;
	
	/** 当日实际收入   */
	private String paidIncome;
	
	/** 当日实际本金   */
	private String paidPrincipal;
	
	/** 当日实际总额   */
	private String paidAmount;
	
	/** 当日未偿总额   */
	private String unpaidAmount;
	
	/** 律所   */
	private String lawFirmName;
	
	/** 会计事务所 */
	private String accountingFirmName;
	
	/** 监管银行 */
	private String supervisionBank;
	
	/** 托管银行 */
	private String custodianBank;
	
	/** 评级机构 */
	private String ratingAgency;
	
	/** 资产评估机构 */
	private String assetEvaluationAgency;
	
	//原始权益人
	private List<OriginatorDTO> originators;
	
	//资产服务机构
	private List<ServiceAgencyDTO> serviceAgencys;

	public Boolean getCyclePurchaseStructure() {
		return cyclePurchaseStructure;
	}

	public void setCyclePurchaseStructure(Boolean cyclePurchaseStructure) {
		this.cyclePurchaseStructure = cyclePurchaseStructure;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getEstimateEstablishmentDate() {
		return estimateEstablishmentDate;
	}

	public void setEstimateEstablishmentDate(String estimateEstablishmentDate) {
		this.estimateEstablishmentDate = estimateEstablishmentDate;
	}

	public String getOriginAssetpoolScale() {
		return originAssetpoolScale;
	}

	public void setOriginAssetpoolScale(String originAssetpoolScale) {
		this.originAssetpoolScale = originAssetpoolScale;
	}

	public SpecialProgramStatus getStatus() {
		return status;
	}

	public void setStatus(SpecialProgramStatus status) {
		this.status = status;
	}

	public String getAccountIncome() {
		return accountIncome;
	}

	public void setAccountIncome(String accountIncome) {
		this.accountIncome = accountIncome;
	}

	public String getAccountPrincipal() {
		return accountPrincipal;
	}

	public void setAccountPrincipal(String accountPrincipal) {
		this.accountPrincipal = accountPrincipal;
	}

	public String getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(String accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getPaidIncome() {
		return paidIncome;
	}

	public void setPaidIncome(String paidIncome) {
		this.paidIncome = paidIncome;
	}

	public String getPaidPrincipal() {
		return paidPrincipal;
	}

	public void setPaidPrincipal(String paidPrincipal) {
		this.paidPrincipal = paidPrincipal;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getUnpaidAmount() {
		return unpaidAmount;
	}

	public void setUnpaidAmount(String unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
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

	public String getCustodianBank() {
		return custodianBank;
	}

	public void setCustodianBank(String custodianBank) {
		this.custodianBank = custodianBank;
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

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getUnderlyingAssetTypeDesc() {
		return underlyingAssetTypeDesc;
	}

	public void setUnderlyingAssetTypeDesc(String underlyingAssetTypeDesc) {
		this.underlyingAssetTypeDesc = underlyingAssetTypeDesc;
	}
	
	
	
	
}

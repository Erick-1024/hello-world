package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

public class EarlyWarningCustomerExcelDTO implements Serializable{

	private static final long serialVersionUID = -8485061075055107916L;

	private String memberId;
	
	private String companyName;
	
	private String outCustomerName;
	
	private String limit;
	
	private String residualLimit;
	
	private String earlywarningLevelDesc;

	// 质押反担保覆盖率实际值
	private String actualCounterGuaranteeRate;
	// 质押反担保覆盖率标准值
	private String standardCounterGuaranteeRate;
	// 销售变化率实际值
	private String actualSalesChangeRate;
	// 销售变化率标准值
	private String standardSalesChangeRate;
	// 销售回款率实际值
	private String actualSalesRepaymentRate;
	// 销售回款率标准值
	private String standardSalesRepaymentRate;
	// 连续逾期次数实际值
	private String actualContinueOverdueNum;
	// 连续逾期次数标准值
	private String standardContinueOverdueNum;
	// 累计逾期次数实际值
	private String actualTotalOverdueNum;
	// 累计逾期次数标准值
	private String standardTotalOverdueNum;
	// 被法院执行（企业）实际值
	private String actualCourtExecutionCompany;
	// 被法院执行（企业）标准值
	private String standardCourtExecutionCompany;
	// 被法院执行（个人）实际值
	private String actualCourtExecutionIndividual;
	// 被法院执行（个人）标准值
	private String standardCourtExecutionIndividual;
	// 负面信息实际值
	private String actualNegativeReport;
	// 负面信息标准值
	private String standardNegativeReport;
	
	private String action;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getResidualLimit() {
		return residualLimit;
	}

	public void setResidualLimit(String residualLimit) {
		this.residualLimit = residualLimit;
	}

	public String getEarlywarningLevelDesc() {
		return earlywarningLevelDesc;
	}

	public void setEarlywarningLevelDesc(String earlywarningLevelDesc) {
		this.earlywarningLevelDesc = earlywarningLevelDesc;
	}

	public String getActualCounterGuaranteeRate() {
		return actualCounterGuaranteeRate;
	}

	public void setActualCounterGuaranteeRate(String actualCounterGuaranteeRate) {
		this.actualCounterGuaranteeRate = actualCounterGuaranteeRate;
	}

	public String getStandardCounterGuaranteeRate() {
		return standardCounterGuaranteeRate;
	}

	public void setStandardCounterGuaranteeRate(String standardCounterGuaranteeRate) {
		this.standardCounterGuaranteeRate = standardCounterGuaranteeRate;
	}

	public String getActualSalesChangeRate() {
		return actualSalesChangeRate;
	}

	public void setActualSalesChangeRate(String actualSalesChangeRate) {
		this.actualSalesChangeRate = actualSalesChangeRate;
	}

	public String getStandardSalesChangeRate() {
		return standardSalesChangeRate;
	}

	public void setStandardSalesChangeRate(String standardSalesChangeRate) {
		this.standardSalesChangeRate = standardSalesChangeRate;
	}

	public String getActualSalesRepaymentRate() {
		return actualSalesRepaymentRate;
	}

	public void setActualSalesRepaymentRate(String actualSalesRepaymentRate) {
		this.actualSalesRepaymentRate = actualSalesRepaymentRate;
	}

	public String getStandardSalesRepaymentRate() {
		return standardSalesRepaymentRate;
	}

	public void setStandardSalesRepaymentRate(String standardSalesRepaymentRate) {
		this.standardSalesRepaymentRate = standardSalesRepaymentRate;
	}

	public String getActualContinueOverdueNum() {
		return actualContinueOverdueNum;
	}

	public void setActualContinueOverdueNum(String actualContinueOverdueNum) {
		this.actualContinueOverdueNum = actualContinueOverdueNum;
	}

	public String getStandardContinueOverdueNum() {
		return standardContinueOverdueNum;
	}

	public void setStandardContinueOverdueNum(String standardContinueOverdueNum) {
		this.standardContinueOverdueNum = standardContinueOverdueNum;
	}

	public String getActualTotalOverdueNum() {
		return actualTotalOverdueNum;
	}

	public void setActualTotalOverdueNum(String actualTotalOverdueNum) {
		this.actualTotalOverdueNum = actualTotalOverdueNum;
	}

	public String getStandardTotalOverdueNum() {
		return standardTotalOverdueNum;
	}

	public void setStandardTotalOverdueNum(String standardTotalOverdueNum) {
		this.standardTotalOverdueNum = standardTotalOverdueNum;
	}

	public String getActualCourtExecutionCompany() {
		return actualCourtExecutionCompany;
	}

	public void setActualCourtExecutionCompany(String actualCourtExecutionCompany) {
		this.actualCourtExecutionCompany = actualCourtExecutionCompany;
	}

	public String getStandardCourtExecutionCompany() {
		return standardCourtExecutionCompany;
	}

	public void setStandardCourtExecutionCompany(String standardCourtExecutionCompany) {
		this.standardCourtExecutionCompany = standardCourtExecutionCompany;
	}

	public String getActualCourtExecutionIndividual() {
		return actualCourtExecutionIndividual;
	}

	public void setActualCourtExecutionIndividual(String actualCourtExecutionIndividual) {
		this.actualCourtExecutionIndividual = actualCourtExecutionIndividual;
	}

	public String getStandardCourtExecutionIndividual() {
		return standardCourtExecutionIndividual;
	}

	public void setStandardCourtExecutionIndividual(String standardCourtExecutionIndividual) {
		this.standardCourtExecutionIndividual = standardCourtExecutionIndividual;
	}

	public String getActualNegativeReport() {
		return actualNegativeReport;
	}

	public void setActualNegativeReport(String actualNegativeReport) {
		this.actualNegativeReport = actualNegativeReport;
	}

	public String getStandardNegativeReport() {
		return standardNegativeReport;
	}

	public void setStandardNegativeReport(String standardNegativeReport) {
		this.standardNegativeReport = standardNegativeReport;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}

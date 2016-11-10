package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;

import com.cana.vbam.common.early.warning.enums.YundaexEarlywarningEventCategory;

public class YundaexEarlyWarningExcelDTO implements Serializable{

	private static final long serialVersionUID = -8485061075055107916L;

	private String memberId;
	
	private String companyName;
	
	private String limit;
	
	private String residualLimit;
	
	private String earlywarningLevelDesc;

	// 是否收到税务、工商或质检等部门处罚 实际值
	private String actualDepartmentsPunish;
	// 是否收到税务、工商或质检等部门处罚 标准值
	private String standardDepartmentsPunish;
	// 公司业务性质有无重大变化 实际值
	private String actualBusinessNatureChange;
	// 公司业务性质有无重大变化 标准值
	private String standardBusinessNatureChange;
	// 股权结构变化 实际值
	private String actualOwnershipStructureChange;
	// 股权结构变化 标准值
	private String standardOwnershipStructureChange;
	// 负面新闻 实际值
	private String actualNegativeNews;
	// 负面新闻 标准值
	private String standardNegativeNews;
	// 诉讼纠纷 实际值
	private String actualLitigationDispute;
	// 诉讼纠纷 标准值
	private String standardLitigationDispute;
	// 短期借款 实际值
	private String actualShortTermLoan;
	// 短期借款 标准值
	private String standardShortTermLoan;
	// 客户态度 实际值
	private String actualCustomerAttitude;
	// 客户态度 标准值
	private String standardCustomerAttitude;
	// 其它 实际值
	private String actualOther;
	// 其它 标准值
	private String standardOther;
	
	// 系统 派件增长率 实际值
	private String actualRecandsendGrowthrate;
	// 系统 派件增长率 标准值
	private String standardRecandsendGrowthrate;
	// 系统 保证金余额/日子资金需求 实际值
	private String actualBailbalanceDayRequirements;
	// 系统 保证金余额/日子资金需求 标准值
	private String standardBailbalanceDayRequirements;
	// 系统 韵达评级 实际值
	private String actualYundaexgrade;
	// 系统 韵达评级 标准值
	private String standardYundaexgrade;
	// 系统 保证金余额 实际值
	private String actualBailbalance;
	// 系统 保证金余额 标准值
	private String standardBailbalance;
	// 系统 净资金增幅 实际值
	private String actualNetCashflowGrowthrate;
	// 系统 净资金增幅 标准值
	private String standardNetCashflowGrowthrate;
	// 系统 净资金增幅 实际值
	private String actualNetCashflow;
	// 系统 净资金增幅 标准值
	private String standardNetCashflow;
	// 系统 最大授信额度增幅 实际值
	private String actualCreditLimitGrowth;
	// 系统 最大授信额度增幅 标准值
	private String standardCreditLimitGrowth;
	// 系统 逾期次数 实际值
	private String actualOverdues;
	// 系统 逾期次数 标准值
	private String standardOverdues;
	

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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActualDepartmentsPunish() {
		return actualDepartmentsPunish;
	}

	public void setActualDepartmentsPunish(String actualDepartmentsPunish) {
		this.actualDepartmentsPunish = actualDepartmentsPunish;
	}

	public String getStandardDepartmentsPunish() {
		return standardDepartmentsPunish;
	}

	public void setStandardDepartmentsPunish(String standardDepartmentsPunish) {
		this.standardDepartmentsPunish = standardDepartmentsPunish;
	}

	public String getActualBusinessNatureChange() {
		return actualBusinessNatureChange;
	}

	public void setActualBusinessNatureChange(String actualBusinessNatureChange) {
		this.actualBusinessNatureChange = actualBusinessNatureChange;
	}

	public String getStandardBusinessNatureChange() {
		return standardBusinessNatureChange;
	}

	public void setStandardBusinessNatureChange(String standardBusinessNatureChange) {
		this.standardBusinessNatureChange = standardBusinessNatureChange;
	}

	public String getActualOwnershipStructureChange() {
		return actualOwnershipStructureChange;
	}

	public void setActualOwnershipStructureChange(String actualOwnershipStructureChange) {
		this.actualOwnershipStructureChange = actualOwnershipStructureChange;
	}

	public String getStandardOwnershipStructureChange() {
		return standardOwnershipStructureChange;
	}

	public void setStandardOwnershipStructureChange(String standardOwnershipStructureChange) {
		this.standardOwnershipStructureChange = standardOwnershipStructureChange;
	}

	public String getActualNegativeNews() {
		return actualNegativeNews;
	}

	public void setActualNegativeNews(String actualNegativeNews) {
		this.actualNegativeNews = actualNegativeNews;
	}

	public String getStandardNegativeNews() {
		return standardNegativeNews;
	}

	public void setStandardNegativeNews(String standardNegativeNews) {
		this.standardNegativeNews = standardNegativeNews;
	}

	public String getActualLitigationDispute() {
		return actualLitigationDispute;
	}

	public void setActualLitigationDispute(String actualLitigationDispute) {
		this.actualLitigationDispute = actualLitigationDispute;
	}

	public String getStandardLitigationDispute() {
		return standardLitigationDispute;
	}

	public void setStandardLitigationDispute(String standardLitigationDispute) {
		this.standardLitigationDispute = standardLitigationDispute;
	}

	public String getActualShortTermLoan() {
		return actualShortTermLoan;
	}

	public void setActualShortTermLoan(String actualShortTermLoan) {
		this.actualShortTermLoan = actualShortTermLoan;
	}

	public String getStandardShortTermLoan() {
		return standardShortTermLoan;
	}

	public void setStandardShortTermLoan(String standardShortTermLoan) {
		this.standardShortTermLoan = standardShortTermLoan;
	}

	public String getActualCustomerAttitude() {
		return actualCustomerAttitude;
	}

	public void setActualCustomerAttitude(String actualCustomerAttitude) {
		this.actualCustomerAttitude = actualCustomerAttitude;
	}

	public String getStandardCustomerAttitude() {
		return standardCustomerAttitude;
	}

	public void setStandardCustomerAttitude(String standardCustomerAttitude) {
		this.standardCustomerAttitude = standardCustomerAttitude;
	}

	public String getActualOther() {
		return actualOther;
	}

	public void setActualOther(String actualOther) {
		this.actualOther = actualOther;
	}

	public String getStandardOther() {
		return standardOther;
	}

	public void setStandardOther(String standardOther) {
		this.standardOther = standardOther;
	}

	public String getActualRecandsendGrowthrate() {
		return actualRecandsendGrowthrate;
	}

	public void setActualRecandsendGrowthrate(String actualRecandsendGrowthrate) {
		this.actualRecandsendGrowthrate = actualRecandsendGrowthrate;
	}

	public String getStandardRecandsendGrowthrate() {
		return standardRecandsendGrowthrate;
	}

	public void setStandardRecandsendGrowthrate(String standardRecandsendGrowthrate) {
		this.standardRecandsendGrowthrate = standardRecandsendGrowthrate;
	}

	public String getActualBailbalanceDayRequirements() {
		return actualBailbalanceDayRequirements;
	}

	public void setActualBailbalanceDayRequirements(String actualBailbalanceDayRequirements) {
		this.actualBailbalanceDayRequirements = actualBailbalanceDayRequirements;
	}

	public String getStandardBailbalanceDayRequirements() {
		return standardBailbalanceDayRequirements;
	}

	public void setStandardBailbalanceDayRequirements(String standardBailbalanceDayRequirements) {
		this.standardBailbalanceDayRequirements = standardBailbalanceDayRequirements;
	}

	public String getActualYundaexgrade() {
		return actualYundaexgrade;
	}

	public void setActualYundaexgrade(String actualYundaexgrade) {
		this.actualYundaexgrade = actualYundaexgrade;
	}

	public String getStandardYundaexgrade() {
		return standardYundaexgrade;
	}

	public void setStandardYundaexgrade(String standardYundaexgrade) {
		this.standardYundaexgrade = standardYundaexgrade;
	}

	public String getActualBailbalance() {
		return actualBailbalance;
	}

	public void setActualBailbalance(String actualBailbalance) {
		this.actualBailbalance = actualBailbalance;
	}

	public String getStandardBailbalance() {
		return standardBailbalance;
	}

	public void setStandardBailbalance(String standardBailbalance) {
		this.standardBailbalance = standardBailbalance;
	}

	public String getActualNetCashflowGrowthrate() {
		return actualNetCashflowGrowthrate;
	}

	public void setActualNetCashflowGrowthrate(String actualNetCashflowGrowthrate) {
		this.actualNetCashflowGrowthrate = actualNetCashflowGrowthrate;
	}

	public String getStandardNetCashflowGrowthrate() {
		return standardNetCashflowGrowthrate;
	}

	public void setStandardNetCashflowGrowthrate(String standardNetCashflowGrowthrate) {
		this.standardNetCashflowGrowthrate = standardNetCashflowGrowthrate;
	}

	public String getActualNetCashflow() {
		return actualNetCashflow;
	}

	public void setActualNetCashflow(String actualNetCashflow) {
		this.actualNetCashflow = actualNetCashflow;
	}

	public String getStandardNetCashflow() {
		return standardNetCashflow;
	}

	public void setStandardNetCashflow(String standardNetCashflow) {
		this.standardNetCashflow = standardNetCashflow;
	}
	
	public String getActualCreditLimitGrowth() {
		return actualCreditLimitGrowth;
	}

	public void setActualCreditLimitGrowth(String actualCreditLimitGrowth) {
		this.actualCreditLimitGrowth = actualCreditLimitGrowth;
	}

	public String getStandardCreditLimitGrowth() {
		return standardCreditLimitGrowth;
	}

	public void setStandardCreditLimitGrowth(String standardCreditLimitGrowth) {
		this.standardCreditLimitGrowth = standardCreditLimitGrowth;
	}

	public String getActualOverdues() {
		return actualOverdues;
	}

	public void setActualOverdues(String actualOverdues) {
		this.actualOverdues = actualOverdues;
	}

	public String getStandardOverdues() {
		return standardOverdues;
	}

	public void setStandardOverdues(String standardOverdues) {
		this.standardOverdues = standardOverdues;
	}
}

package com.cana.vbam.common.vj.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.enums.Education;
import com.cana.vbam.common.enums.IndustryType;
import com.cana.vbam.common.enums.Marriage;
import com.cana.vbam.common.vj.enums.DepositState;

public class CustomerData implements Serializable{
	
	private static final long serialVersionUID = 8474297661626398019L;

	private String area;
	
	private Marriage marriage;
	
	private Education highestEducation;
	
	private String housingFundAccountNo;
	
	private String socialSecurityAccountNo;
	
	private List<Bill> housingFundBillHistory;
	
	private List<Bill> socialSecurityBillHistory;
	
	private long depositBase;
	
	private DepositState depositState;
	
	private long socialSecurityDepositBase;
	
	private DepositState socialSecurityDepositState;
	
	private String companyName;
	
	private IndustryType industryType;
	
	private List<Bill> salaryBillHistory;
	
	private List<Bill> bonusBillHistory;
	
	private float vjScore;
	
	private String mobileNo;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Marriage getMarriage() {
		return marriage;
	}

	public void setMarriage(Marriage marriage) {
		this.marriage = marriage;
	}

	public Education getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(Education highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getHousingFundAccountNo() {
		return housingFundAccountNo;
	}

	public void setHousingFundAccountNo(String housingFundAccountNo) {
		this.housingFundAccountNo = housingFundAccountNo;
	}

	public String getSocialSecurityAccountNo() {
		return socialSecurityAccountNo;
	}

	public void setSocialSecurityAccountNo(String socialSecurityAccountNo) {
		this.socialSecurityAccountNo = socialSecurityAccountNo;
	}

	public List<Bill> getHousingFundBillHistory() {
		return housingFundBillHistory;
	}

	public void setHousingFundBillHistory(List<Bill> housingFundBillHistory) {
		this.housingFundBillHistory = housingFundBillHistory;
	}

	public List<Bill> getSocialSecurityBillHistory() {
		return socialSecurityBillHistory;
	}

	public void setSocialSecurityBillHistory(List<Bill> socialSecurityBillHistory) {
		this.socialSecurityBillHistory = socialSecurityBillHistory;
	}

	public long getDepositBase() {
		return depositBase;
	}

	public void setDepositBase(long depositBase) {
		this.depositBase = depositBase;
	}

	public DepositState getDepositState() {
		return depositState;
	}

	public void setDepositState(DepositState depositState) {
		this.depositState = depositState;
	}

	public long getSocialSecurityDepositBase() {
		return socialSecurityDepositBase;
	}

	public void setSocialSecurityDepositBase(long socialSecurityDepositBase) {
		this.socialSecurityDepositBase = socialSecurityDepositBase;
	}

	public DepositState getSocialSecurityDepositState() {
		return socialSecurityDepositState;
	}

	public void setSocialSecurityDepositState(DepositState socialSecurityDepositState) {
		this.socialSecurityDepositState = socialSecurityDepositState;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public IndustryType getIndustryType() {
		return industryType;
	}

	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}

	public List<Bill> getSalaryBillHistory() {
		return salaryBillHistory;
	}

	public void setSalaryBillHistory(List<Bill> salaryBillHistory) {
		this.salaryBillHistory = salaryBillHistory;
	}

	public List<Bill> getBonusBillHistory() {
		return bonusBillHistory;
	}

	public void setBonusBillHistory(List<Bill> bonusBillHistory) {
		this.bonusBillHistory = bonusBillHistory;
	}

	public float getVjScore() {
		return vjScore;
	}

	public void setVjScore(float vjScore) {
		this.vjScore = vjScore;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}

package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

import com.cana.vbam.common.asset.enums.SupervisionAgencyEnum;
import com.cana.vbam.common.asset.enums.UnderlyingAssetType;
import com.google.common.collect.Lists;

public class MarketDataProjectExcelDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6114877206002626869L;

	/**
     *项目名称
     */
    private String projectName;

    /**
     *计息起始日
     */
    private String valueDate;

    private String valueDateStr;
    /**
     *发起机构
     */
    private String originator;

    /**
     *发行总金额(亿元)
     */
    private String issueTotalAmount;

    /**
     *监管机构
     */
    private String supervisionAgency;
    
    private SupervisionAgencyEnum supervisionAgencyEnum;
    
    /**
     *基础资产类型
     */
    private String underlyingAssetType;

//    private UnderlyingAssetType assetType;
    /**
     *发行人
     */
    private String issuer;

    /**
     *发行月份
     */
    private String issueMonth;

    private int issueMonthNum;
    /**
     *AAA平均利率
     */
    private String aaaAverageInterestRate;

    /**
     *优先级平均利率
     */
    private String priorityAverageInterestRate;

    List<MarketDataProductExcelDTO> productExcelList;
    
	public List<MarketDataProductExcelDTO> getProductExcelList() {
		return productExcelList;
	}

	public void setProductExcelList(List<MarketDataProductExcelDTO> productExcelList) {
		this.productExcelList = productExcelList;
	}
	
	public void addProductExcelList(List<MarketDataProductExcelDTO> productExcelList){
		if(null == this.productExcelList)
			this.productExcelList = Lists.newArrayList();
		this.productExcelList.addAll(productExcelList);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public String getIssueTotalAmount() {
		return issueTotalAmount;
	}

	public void setIssueTotalAmount(String issueTotalAmount) {
		this.issueTotalAmount = issueTotalAmount;
	}

	public String getSupervisionAgency() {
		return supervisionAgency;
	}

	public void setSupervisionAgency(String supervisionAgency) {
		this.supervisionAgency = supervisionAgency;
	}

	public String getUnderlyingAssetType() {
		return underlyingAssetType;
	}

	public void setUnderlyingAssetType(String underlyingAssetType) {
		this.underlyingAssetType = underlyingAssetType;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssueMonth() {
		return issueMonth;
	}

	public void setIssueMonth(String issueMonth) {
		this.issueMonth = issueMonth;
	}

	public String getAaaAverageInterestRate() {
		return aaaAverageInterestRate;
	}

	public void setAaaAverageInterestRate(String aaaAverageInterestRate) {
		this.aaaAverageInterestRate = aaaAverageInterestRate;
	}

	public String getPriorityAverageInterestRate() {
		return priorityAverageInterestRate;
	}

	public void setPriorityAverageInterestRate(String priorityAverageInterestRate) {
		this.priorityAverageInterestRate = priorityAverageInterestRate;
	}

//	public UnderlyingAssetType getAssetType() {
//		return assetType;
//	}
//
//	public void setAssetType(UnderlyingAssetType assetType) {
//		this.assetType = assetType;
//	}

	public int getIssueMonthNum() {
		return issueMonthNum;
	}

	public void setIssueMonthNum(int issueMonthNum) {
		this.issueMonthNum = issueMonthNum;
	}

	public String getValueDateStr() {
		return valueDateStr;
	}

	public void setValueDateStr(String valueDateStr) {
		this.valueDateStr = valueDateStr;
	}

	public SupervisionAgencyEnum getSupervisionAgencyEnum() {
		return supervisionAgencyEnum;
	}

	public void setSupervisionAgencyEnum(SupervisionAgencyEnum supervisionAgencyEnum) {
		this.supervisionAgencyEnum = supervisionAgencyEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketDataProjectExcelDTO other = (MarketDataProjectExcelDTO) obj;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	
	
}

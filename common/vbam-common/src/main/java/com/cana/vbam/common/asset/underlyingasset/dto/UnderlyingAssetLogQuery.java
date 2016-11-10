package com.cana.vbam.common.asset.underlyingasset.dto;

import java.util.Date;
import java.util.List;

import com.cana.vbam.common.asset.enums.UnderlyingAssetOperateTypeEnum;
import com.cana.vbam.common.dto.Pagination;
import com.travelzen.framework.spring.web.format.annotation.DateFormat;

/**
 * 基础资产日志查询条件
 * 
 * @author yihong.tang
 */
public class UnderlyingAssetLogQuery extends Pagination {

	private static final long serialVersionUID = 1L;

	private String specialProgramName; // 专项计划名称
	private String loanInfoId; // 放款编号
	private String businessContractNo; // 业务合同号
	@DateFormat
	private Date operateStartDate; // 操作开始日期
	@DateFormat
	private Date operateEndDate; // 操作结束日期
	private String operateCompanyName; // 操作人的企业名称
	private UnderlyingAssetOperateTypeEnum operateType;// 操作类型
	
	//sql需要
	private String operateTypeStr;//操作类型（name）
	private String factorId;//保理商customerId
	private List<String> specialProgramIds;//专项计划id
	
	
	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
	}

	public String getBusinessContractNo() {
		return businessContractNo;
	}

	public void setBusinessContractNo(String businessContractNo) {
		this.businessContractNo = businessContractNo;
	}

	public Date getOperateStartDate() {
		return operateStartDate;
	}

	public void setOperateStartDate(Date operateStartDate) {
		this.operateStartDate = operateStartDate;
	}

	public Date getOperateEndDate() {
		return operateEndDate;
	}

	public void setOperateEndDate(Date operateEndDate) {
		this.operateEndDate = operateEndDate;
	}

	public String getOperateCompanyName() {
		return operateCompanyName;
	}

	public void setOperateCompanyName(String operateCompanyName) {
		this.operateCompanyName = operateCompanyName;
	}

	public UnderlyingAssetOperateTypeEnum getOperateType() {
		return operateType;
	}

	public void setOperateType(UnderlyingAssetOperateTypeEnum operateType) {
		this.operateType = operateType;
	}

	public String getOperateTypeStr() {
		return operateTypeStr;
	}

	public void setOperateTypeStr(String operateTypeStr) {
		this.operateTypeStr = operateTypeStr;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public List<String> getSpecialProgramIds() {
		return specialProgramIds;
	}

	public void setSpecialProgramIds(List<String> specialProgramIds) {
		this.specialProgramIds = specialProgramIds;
	}

}

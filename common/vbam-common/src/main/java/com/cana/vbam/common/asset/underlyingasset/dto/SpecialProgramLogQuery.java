package com.cana.vbam.common.asset.underlyingasset.dto;

import java.util.Date;
import java.util.List;

import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.asset.enums.UnderlyingAssetOperateTypeEnum;
import com.cana.vbam.common.dto.Pagination;
import com.travelzen.framework.spring.web.format.annotation.DateFormat;

/**
 * 专项计划日志查询条件
 * 
 * @author yihong.tang
 */
public class SpecialProgramLogQuery extends Pagination {

	private static final long serialVersionUID = 1L;

	private String specialProgramName; // 专项计划名称
	@DateFormat
	private Date operateStartDate; // 操作开始日期
	@DateFormat
	private Date operateEndDate; // 操作结束日期
	private String operateCompanyName; // 操作人的企业名称
	private SpecialProgramStatus operateType;// 操作类型
	
	public String getSpecialProgramName() {
		return specialProgramName;
	}

	public void setSpecialProgramName(String specialProgramName) {
		this.specialProgramName = specialProgramName;
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

	public SpecialProgramStatus getOperateType() {
		return operateType;
	}

	public void setOperateType(SpecialProgramStatus operateType) {
		this.operateType = operateType;
	}

}

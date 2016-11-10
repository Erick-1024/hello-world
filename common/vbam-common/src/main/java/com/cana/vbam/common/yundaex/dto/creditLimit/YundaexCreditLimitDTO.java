package com.cana.vbam.common.yundaex.dto.creditLimit;

import java.io.Serializable;
import java.util.Date;

/**
 * 韵达项目-额度列表结果DTO
 * 
 * @author guguanggong
 *
 */
public class YundaexCreditLimitDTO implements Serializable {

	private static final long serialVersionUID = 1099117941658714950L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * Cana用户的ID
	 */
	private String memberId;

	/**
	 * 客户名称（企业的全称）
	 */
	private String companyName;

	/**
	 * 授信方式（SYNTHETICAL:综合授信、SINGLE:单笔授信）
	 */
	private String creditMode;

	/**
	 * 总额度，精确到分
	 */
	private String totalLimit;

	/**
	 * 已使用额度，精确到分
	 */
	private String usedLimit;

	/**
	 * 额度生效日
	 */
	private String effectiveDate;

	/**
	 * 额度状态（NORMAL:正常、FREEZE:冻结、DISABLED:停用、INACTIVE:未激活）
	 */
	private String status;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 额度状态 NORMAL("正常"),FREEZE("冻结"),DISABLED("停用"),INACTIVE("未激活");
	 */
	private String limitStatus;
	
	private String applyType;
	
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getLimitStatus() {
		return limitStatus;
	}

	public void setLimitStatus(String limitStatus) {
		this.limitStatus = limitStatus;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCreditMode() {
		return creditMode;
	}

	public void setCreditMode(String creditMode) {
		this.creditMode = creditMode;
	}

	public String getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}

	public String getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(String usedLimit) {
		this.usedLimit = usedLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}
}

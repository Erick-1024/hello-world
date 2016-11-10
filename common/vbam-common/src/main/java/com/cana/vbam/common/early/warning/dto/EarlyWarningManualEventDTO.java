package com.cana.vbam.common.early.warning.dto;

import java.util.Date;

import com.travelzen.framework.spring.web.format.annotation.DateFormat;

public class EarlyWarningManualEventDTO extends EarlyWarningEventCommonRequest {

	private static final long serialVersionUID = 1L;

	// 产品ID
	private String productId;
	
	// 融资商ID
	private String financeId;
	
	// 公司名称
	private String companyName;
	
	// 外部客户ID
	private String outCustomerId;
	
	// 外部客户名称
	private String outCustomerName;
	
	// 预警种类
	private String earlywarningEventCategory;
	
	// 事件发生时间
	@DateFormat
	private Date date;
	
	// 执行金额
	private Long amount;
	
	// 事件描述
	private String represent;
	
	// 预警等级
	private String earlywarningLevel;
	
	// 附加数据
	private EarlyWarningManualEventExtra extra;
	
	// 预警事件审核时的附加说明
	private String reviewExtra;
	
	//事件种类
	private String earlywarningEventSubCategory;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getOutCustomerName() {
		return outCustomerName;
	}

	public void setOutCustomerName(String outCustomerName) {
		this.outCustomerName = outCustomerName;
	}

	public String getEarlywarningEventCategory() {
		return earlywarningEventCategory;
	}

	public void setEarlywarningEventCategory(String earlywarningEventCategory) {
		this.earlywarningEventCategory = earlywarningEventCategory;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}

	public String getEarlywarningLevel() {
		return earlywarningLevel;
	}

	public void setEarlywarningLevel(String earlywarningLevel) {
		this.earlywarningLevel = earlywarningLevel;
	}

	public EarlyWarningManualEventExtra getExtra() {
		return extra;
	}

	public void setExtra(EarlyWarningManualEventExtra extra) {
		this.extra = extra;
	}

	public String getReviewExtra() {
		return reviewExtra;
	}

	public void setReviewExtra(String reviewExtra) {
		this.reviewExtra = reviewExtra;
	}

	public String getEarlywarningEventSubCategory() {
		return earlywarningEventSubCategory;
	}

	public void setEarlywarningEventSubCategory(String earlywarningEventSubCategory) {
		this.earlywarningEventSubCategory = earlywarningEventSubCategory;
	}
	
}

package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.Date;

import com.travelzen.framework.spring.web.format.annotation.DateFormat;

public class EarlyWarningEventDetailDTO implements Serializable {

	private static final long serialVersionUID = 3274747579501524529L;
	
	/**
     *主键
     */
    private String id;
	
    /**
     *产品id
     */
    private String productId;

    /**
     *融资客户Id
     */
    private String financeId;
    
    /**
     * 外部客户ID
     */
    private String outCustomerId;
    
    /**
     *预警种类
     */
    private String type;

    /**
     *预警种类下面的子类型
     */
    private String subType;

    /**
     *预警等级
     */
    private String level;

    /**
     *录入事件的审核通过时间
     */
    private Date entryReviewTime;

    /**
     *发生时间
     */
    @DateFormat
    private Date occurTime;

    /**
     *金额。如：法院判决金额
     */
    private Long amount;

    /**
     *事件描述
     */
    private Object represent;

    /**
     *附加数据
     */
    private Object extraData;

    /**
     *状态
     */
    private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getOutCustomerId() {
		return outCustomerId;
	}

	public void setOutCustomerId(String outCustomerId) {
		this.outCustomerId = outCustomerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getEntryReviewTime() {
		return entryReviewTime;
	}

	public void setEntryReviewTime(Date entryReviewTime) {
		this.entryReviewTime = entryReviewTime;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Object getRepresent() {
		return represent;
	}

	public void setRepresent(Object represent) {
		this.represent = represent;
	}

	public Object getExtraData() {
		return extraData;
	}

	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

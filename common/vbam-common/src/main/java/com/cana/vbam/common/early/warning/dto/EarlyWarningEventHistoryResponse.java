package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.Date;

public class EarlyWarningEventHistoryResponse implements Serializable {

	private static final long serialVersionUID = -8736791911163693935L;

	/**
     *主键
     */
    private String id;

    /**
     *预警种类下面的子类型
     */
    private String subType;
    
    /**
     *录入事件的审核通过时间
     */
    private Date entryReviewTime;
    
    /**
     *发生时间
     */
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
    private String extraData;
    
    /**
     *状态
     */
    private String state;

    private String stateDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
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

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
    
}

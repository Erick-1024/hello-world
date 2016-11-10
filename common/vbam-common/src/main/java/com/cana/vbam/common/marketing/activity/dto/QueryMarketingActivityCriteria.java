package com.cana.vbam.common.marketing.activity.dto;

import java.io.Serializable;
import java.util.Date;

public class QueryMarketingActivityCriteria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8461528709180604345L;

	/**
     *活动类型。如：借款利率打折
     */
    private String type;

    /**
     *该活动适用的产品编号，为空代表适用于所有产品
     */
    private String productId;

    /**
     *当前日期
     */
    private Date curDate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

}

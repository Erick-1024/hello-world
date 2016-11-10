package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.Date;

public class EarlyWarningCustomerInformationResponse implements Serializable {

	private static final long serialVersionUID = 2659936748003231543L;

    /**
     *产品id
     */
    private String productId;

    /**
     *融资客户Id
     */
    private String financeId;

    /**
     *融资客户公司名称
     */
    private String financeCompany;

    /**
     * 外部平台客户ID
     */
    private String outCustomerId;
    
    /**
     * 外部平台客户名称
     */
    private String outCustomerName;
    
    /**
     *预警等级
     */
    private String level;

    /**
     *更新时间
     */
    private Date updateTime;
	
    private String earlywaringLevelDesc;

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

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEarlywaringLevelDesc() {
		return earlywaringLevelDesc;
	}

	public void setEarlywaringLevelDesc(String earlywaringLevelDesc) {
		this.earlywaringLevelDesc = earlywaringLevelDesc;
	}
    
}

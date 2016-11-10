package com.cana.vbam.common.early.warning.dto;

import java.io.Serializable;
import java.util.Date;

public class EarlyWarningLevelChangeHistoryDTO implements Serializable {

	private static final long serialVersionUID = 1781064077817557346L;

    /**
     *融资客户Id
     */
    private String financeId;

    /**
     *融资客户公司名称
     */
    private String financeCompany;
    
    /**
     * 外部客户名称
     */
    private String outCustomerName;

    /**
     *预警等级
     */
    private String level;

    /**
     *转入时间
     */
    private Date inTime;

    /**
     *转出时间
     */
    private Date outTime;
	
    private String levelDesc;

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

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getLevelDesc() {
		return levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}
    
}

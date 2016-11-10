package com.cana.vbam.common.credit.dto.limit;

import java.io.Serializable;
import java.util.Date;

import com.travelzen.framework.spring.web.format.annotation.DateFormat;

/**
 * 页面额度列表的查询条件
 * @author sugar
 *
 */
public class CustomerLimitListQueryDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    private String companyName;
    private String customerType;//客户类型（个人，企业）
    private String limitStart;//额度最小值
    private Long limitStartLong;//额度最小值long
    private String limitEnd;//额度最大值
    private Long limitEndLong;//额度最大值long
    @DateFormat
    private Date effectiveDateStart;
    @DateFormat
    private Date effectiveDateEnd;//页面上选择的日期加一天
    private String limitStatus;
    private String memberId; // 融资商ID
    private int page=1;
    private int pageSize=10;
    private String projectId;//产品id

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(String limitStart) {
        this.limitStart = limitStart;
    }

    public String getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(String limitEnd) {
        this.limitEnd = limitEnd;
    }

    public String getLimitStatus() {
        return limitStatus;
    }

    public void setLimitStatus(String limitStatus) {
        this.limitStatus = limitStatus;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public Long getLimitStartLong() {
		return limitStartLong;
	}

	public void setLimitStartLong(Long limitStartLong) {
		this.limitStartLong = limitStartLong;
	}

	public Long getLimitEndLong() {
		return limitEndLong;
	}

	public void setLimitEndLong(Long limitEndLong) {
		this.limitEndLong = limitEndLong;
	}

	public Date getEffectiveDateStart() {
		return effectiveDateStart;
	}

	public void setEffectiveDateStart(Date effectiveDateStart) {
		this.effectiveDateStart = effectiveDateStart;
	}

	public Date getEffectiveDateEnd() {
		return effectiveDateEnd;
	}

	public void setEffectiveDateEnd(Date effectiveDateEnd) {
		this.effectiveDateEnd = effectiveDateEnd;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

    
}

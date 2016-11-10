package com.cana.vbam.common.wechat.repayment;

import java.io.Serializable;

/**
 * 微信平台——融资信息查询条件
 * @author yihong.tang
 */
public class LoanInfoRequest implements Serializable{

	private static final long serialVersionUID = -3600802836951236742L;

	private int pageSize;//每页显示行数
	
	private String currentLoanInfoId;//当前展示的信息id,分页查询会导致数据重复展示
	
	private int offset = 0;//偏移量

	private String customerId;//当前登录用户id
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCurrentLoanInfoId() {
		return currentLoanInfoId;
	}

	public void setCurrentLoanInfoId(String currentLoanInfoId) {
		this.currentLoanInfoId = currentLoanInfoId;
	}
}

package com.cana.vbam.common.vj.dto;

import java.io.Serializable;

public class QueryLoanListRequest implements Serializable {

	private static final long serialVersionUID = 1190259275980444399L;
	
	private String canaCustomerId;//该用户在CANA平台的唯一标识
	
	private String loanDateStart;//放款日开始日期,格式：yyyy-MM-dd
	
	private String loanDateEnd;//放款日结束日期,格式：yyyy-MM-dd
	
	private String dueDateStart;//到期日开始日期,格式：yyyy-MM-dd
	
	private String dueDateEnd;//到期日结束日期,格式：yyyy-MM-dd

	private String settleStatus;//结清状态，SETTLED：已结清；UNSETTLE：未结清
	
	private int page=1;//查询页码，正整数，默认为1
	
	private int pageSize=10;//每页个数，正整数，默认为10，最大为30

	public String getCanaCustomerId() {
		return canaCustomerId;
	}

	public void setCanaCustomerId(String canaCustomerId) {
		this.canaCustomerId = canaCustomerId;
	}

	public String getLoanDateStart() {
		return loanDateStart;
	}

	public void setLoanDateStart(String loanDateStart) {
		this.loanDateStart = loanDateStart;
	}

	public String getLoanDateEnd() {
		return loanDateEnd;
	}

	public void setLoanDateEnd(String loanDateEnd) {
		this.loanDateEnd = loanDateEnd;
	}

	public String getDueDateStart() {
		return dueDateStart;
	}

	public void setDueDateStart(String dueDateStart) {
		this.dueDateStart = dueDateStart;
	}

	public String getDueDateEnd() {
		return dueDateEnd;
	}

	public void setDueDateEnd(String dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
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
	
	
}

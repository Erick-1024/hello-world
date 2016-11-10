package com.cana.vbam.common.credit.openapi;

import java.io.Serializable;

public class LoanInfoDetailRequest implements Serializable{

	private static final long serialVersionUID = -3816545756267183872L;
	
	private String institution;//机构
	
	private String customerId;//真旅网采购商Id
	
	private String loanDateStart;//放款日开始日期
	
	private String loanDateEnd;//放款日结束日期
	
	private String dueDateStart;//到期日开始日期
	
	private String dueDateEnd;//到期日结束日期
	
	private String settleStatus;//结清状态
	
	private int page=1;//查询页码
	
	private int pageSize=10;//每页个数
	
	private String sign;//签名

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}

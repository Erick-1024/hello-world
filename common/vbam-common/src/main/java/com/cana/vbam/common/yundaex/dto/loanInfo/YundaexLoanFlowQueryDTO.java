/**
 * 
 */
package com.cana.vbam.common.yundaex.dto.loanInfo;

import java.io.Serializable;

/**
 * @author guguanggong
 *
 */
public class YundaexLoanFlowQueryDTO implements Serializable {

	private static final long serialVersionUID = -7929946987948755841L;

	private String businessSeq;

	private String customerName;

	private String startDate;

	private String endDate;

	private String tradeStatus;

	private int page = 1; // 当前页数

	private int pageSize = 10; // 每页记录数

	private String financeCompany;

	private String operatorId; // 操作人ID

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getBusinessSeq() {
		return businessSeq;
	}

	public void setBusinessSeq(String businessSeq) {
		this.businessSeq = businessSeq;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
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

	public String getFinanceCompany() {
		return financeCompany;
	}

	public void setFinanceCompany(String financeCompany) {
		this.financeCompany = financeCompany;
	}

}

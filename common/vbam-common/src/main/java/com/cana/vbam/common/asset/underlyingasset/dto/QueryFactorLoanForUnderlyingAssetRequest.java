package com.cana.vbam.common.asset.underlyingasset.dto;

import com.cana.vbam.common.dto.Pagination;

/**
 * 基础资产查询可以转为基础资产的保理放款
 * 
 * @author XuMeng
 */
public class QueryFactorLoanForUnderlyingAssetRequest extends Pagination {

	private static final long serialVersionUID = 1L;

	private String loanInfoId; // 放款编号，模糊查询
	private String factorId; // 保理商ID
	private String financeBalanceLower; // 融资余额下限，单位元，包括当前值
	private String financeBalanceUpper; // 融资余额上限，单位元，包括当前值
	private String dueDateBegin; // 到期日上限，yyyy-MM-dd，包括当前值
	private String dueDateEnd; // 到期日下限，yyyy-MM-dd，包括当前值

	private Long financeBalanceCentLower; // 融资余额下限，单位分，包括当前值，用于数据库查询
	private Long financeBalanceCentUpper; // 融资余额上限，单位分，包括当前值，用于数据库查询

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getFinanceBalanceLower() {
		return financeBalanceLower;
	}

	public void setFinanceBalanceLower(String financeBalanceLower) {
		this.financeBalanceLower = financeBalanceLower;
	}

	public String getFinanceBalanceUpper() {
		return financeBalanceUpper;
	}

	public void setFinanceBalanceUpper(String financeBalanceUpper) {
		this.financeBalanceUpper = financeBalanceUpper;
	}

	public String getDueDateBegin() {
		return dueDateBegin;
	}

	public void setDueDateBegin(String dueDateBegin) {
		this.dueDateBegin = dueDateBegin;
	}

	public String getDueDateEnd() {
		return dueDateEnd;
	}

	public void setDueDateEnd(String dueDateEnd) {
		this.dueDateEnd = dueDateEnd;
	}

	public Long getFinanceBalanceCentLower() {
		return financeBalanceCentLower;
	}

	public void setFinanceBalanceCentLower(Long financeBalanceCentLower) {
		this.financeBalanceCentLower = financeBalanceCentLower;
	}

	public Long getFinanceBalanceCentUpper() {
		return financeBalanceCentUpper;
	}

	public void setFinanceBalanceCentUpper(Long financeBalanceCentUpper) {
		this.financeBalanceCentUpper = financeBalanceCentUpper;
	}

}

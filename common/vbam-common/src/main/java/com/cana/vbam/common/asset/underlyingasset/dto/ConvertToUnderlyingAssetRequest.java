package com.cana.vbam.common.asset.underlyingasset.dto;

import java.io.Serializable;
import java.util.List;

public class ConvertToUnderlyingAssetRequest implements Serializable{

	private static final long serialVersionUID = -380018825363404154L;

	private boolean convertAllMode; // 是否是全选模式，如果为false，则只转换loanInfoIds为基础资产，如果为true，则按条件查询出所有的保理放款，并排除excludeLoanInfoIds

	// ******* 非全选模式下使用的字段
	private List<String> loanInfoIds; // 放款编号

	// ******* 以下字段是全选模式下使用的字段
	private List<String> excludeLoanInfoIds; // 排除的放款IDs
	private String loanInfoId; // 放款编号，模糊查询
	private String financeBalanceLower; // 融资余额下限，单位元，包括当前值
	private String financeBalanceUpper; // 融资余额上限，单位元，包括当前值
	private String dueDateBegin; // 到期日上限，yyyy-MM-dd，包括当前值
	private String dueDateEnd; // 到期日下限，yyyy-MM-dd，包括当前值

	public List<String> getLoanInfoIds() {
		return loanInfoIds;
	}

	public void setLoanInfoIds(List<String> loanInfoIds) {
		this.loanInfoIds = loanInfoIds;
	}

	public boolean isConvertAllMode() {
		return convertAllMode;
	}

	public void setConvertAllMode(boolean convertAllMode) {
		this.convertAllMode = convertAllMode;
	}

	public List<String> getExcludeLoanInfoIds() {
		return excludeLoanInfoIds;
	}

	public void setExcludeLoanInfoIds(List<String> excludeLoanInfoIds) {
		this.excludeLoanInfoIds = excludeLoanInfoIds;
	}

	public String getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(String loanInfoId) {
		this.loanInfoId = loanInfoId;
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
	
	
}

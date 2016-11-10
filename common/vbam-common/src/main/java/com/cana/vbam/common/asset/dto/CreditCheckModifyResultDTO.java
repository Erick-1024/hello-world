package com.cana.vbam.common.asset.dto;

import java.io.Serializable;

/**
 * @author hu
 *
 */
public class CreditCheckModifyResultDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2106621252485024225L;

	private boolean allowModify = false;
	
	private boolean allowMode = false;
	
	private boolean allowCurreny = false;
	
	private boolean allowTotalLimit = false;
	
	private boolean allowExpense = false;
	
	private boolean allowEffectiveDate = false;
	
	private boolean allowDueDate = false;
	
	private boolean allowBusinessContractNo = false;

	public CreditCheckModifyResultDTO(){
		
	}
	
	public CreditCheckModifyResultDTO(boolean allow){
		this.allowModify = allow;
		this.allowMode = allow;
		this.allowCurreny = allow;
		this.allowTotalLimit = allow;
		this.allowExpense = allow;
		this.allowEffectiveDate = allow;
		this.allowDueDate = allow;
		this.allowBusinessContractNo = allow;
	}
	
	public boolean isAllowModify() {
		return allowModify;
	}

	public void setAllowModify(boolean allowModify) {
		this.allowModify = allowModify;
	}

	public boolean isAllowMode() {
		return allowMode;
	}

	public void setAllowMode(boolean allowMode) {
		this.allowMode = allowMode;
	}

	public boolean isAllowTotalLimit() {
		return allowTotalLimit;
	}

	public void setAllowTotalLimit(boolean allowTotalLimit) {
		this.allowTotalLimit = allowTotalLimit;
	}

	public boolean isAllowExpense() {
		return allowExpense;
	}

	public void setAllowExpense(boolean allowExpense) {
		this.allowExpense = allowExpense;
	}

	public boolean isAllowEffectiveDate() {
		return allowEffectiveDate;
	}

	public void setAllowEffectiveDate(boolean allowEffectiveDate) {
		this.allowEffectiveDate = allowEffectiveDate;
	}

	public boolean isAllowDueDate() {
		return allowDueDate;
	}

	public void setAllowDueDate(boolean allowDueDate) {
		this.allowDueDate = allowDueDate;
	}

	public boolean isAllowBusinessContractNo() {
		return allowBusinessContractNo;
	}

	public void setAllowBusinessContractNo(boolean allowBusinessContractNo) {
		this.allowBusinessContractNo = allowBusinessContractNo;
	}

	public boolean isAllowCurreny() {
		return allowCurreny;
	}

	public void setAllowCurreny(boolean allowCurreny) {
		this.allowCurreny = allowCurreny;
	}
}

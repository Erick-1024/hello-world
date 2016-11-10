package com.cana.vbam.common.asset.loan.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author hu
 *
 */
public class AssetLoanInfoRedisDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6842830905311940378L;
	
	private List<AssetLoanInfoExcelDTO> passLoanInfoList;
	
	private List<AssetLoanInfoExcelDTO> notPassLoanInfoList;
	
	private Set<String> loanInfoIds;
	
	private Set<String> invoiceNos;

	public List<AssetLoanInfoExcelDTO> getPassLoanInfoList() {
		return passLoanInfoList;
	}

	public void setPassLoanInfoList(List<AssetLoanInfoExcelDTO> passLoanInfoList) {
		this.passLoanInfoList = passLoanInfoList;
	}

	public List<AssetLoanInfoExcelDTO> getNotPassLoanInfoList() {
		return notPassLoanInfoList;
	}

	public void setNotPassLoanInfoList(List<AssetLoanInfoExcelDTO> notPassLoanInfoList) {
		this.notPassLoanInfoList = notPassLoanInfoList;
	}

	public Set<String> getLoanInfoIds() {
		return loanInfoIds;
	}

	public void setLoanInfoIds(Set<String> loanInfoIds) {
		this.loanInfoIds = loanInfoIds;
	}

	public Set<String> getInvoiceNos() {
		return invoiceNos;
	}

	public void setInvoiceNos(Set<String> invoiceNos) {
		this.invoiceNos = invoiceNos;
	}
}

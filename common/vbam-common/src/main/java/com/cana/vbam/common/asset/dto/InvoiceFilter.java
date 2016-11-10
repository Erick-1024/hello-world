package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class InvoiceFilter implements Serializable {
	
	private static final long serialVersionUID = -4482211253965093821L;

	/**
	 * redit 应收账款导入筛选通过
	 */
	private List<InvoiceListDTO> passInvoiceList;

	/**
	 * redit 应收账款导入筛选不通过
	 */
	private List<InvoiceListDTO> NotPassInvoiceList;

	public List<InvoiceListDTO> getPassInvoiceList() {
		return passInvoiceList;
	}

	public void setPassInvoiceList(List<InvoiceListDTO> passInvoiceList) {
		this.passInvoiceList = passInvoiceList;
	}

	public List<InvoiceListDTO> getNotPassInvoiceList() {
		return NotPassInvoiceList;
	}

	public void setNotPassInvoiceList(List<InvoiceListDTO> notPassInvoiceList) {
		NotPassInvoiceList = notPassInvoiceList;
	}

}

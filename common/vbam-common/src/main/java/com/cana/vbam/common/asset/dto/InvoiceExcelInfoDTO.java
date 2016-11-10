package com.cana.vbam.common.asset.dto;

import java.io.Serializable;
import java.util.List;

public class InvoiceExcelInfoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3345721613811879571L;
	
	private List<InvoiceRedisDTO> passInvoiceRedisDTO;
	private List<InvoiceRedisDTO> notPassInvoiceRedisDTO;
	public List<InvoiceRedisDTO> getPassInvoiceRedisDTO() {
		return passInvoiceRedisDTO;
	}
	public void setPassInvoiceRedisDTO(List<InvoiceRedisDTO> passInvoiceRedisDTO) {
		this.passInvoiceRedisDTO = passInvoiceRedisDTO;
	}
	public List<InvoiceRedisDTO> getNotPassInvoiceRedisDTO() {
		return notPassInvoiceRedisDTO;
	}
	public void setNotPassInvoiceRedisDTO(List<InvoiceRedisDTO> notPassInvoiceRedisDTO) {
		this.notPassInvoiceRedisDTO = notPassInvoiceRedisDTO;
	}

	
}

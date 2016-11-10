package com.cana.vbam.common.vj.dto;

import java.io.Serializable;
import java.util.List;

public class VJDownloadBankBillResponseData implements Serializable{

	private static final long serialVersionUID = -5117208179092955424L;
	
	private List<BankBillRecord> tranList;

	public List<BankBillRecord> getTranList() {
		return tranList;
	}

	public void setTranList(List<BankBillRecord> tranList) {
		this.tranList = tranList;
	}
	
}

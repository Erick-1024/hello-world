package com.cana.vbam.common.homsom.dto;

public class HomsomSettlementTicketExcelDTO extends HomsomSettlementTicketDTO {

	private static final long serialVersionUID = -397182203433167382L;

	/**
     *交易对手还款表ID
     */
    private String settlementCounterpartyId;
    
    /**
     *还款日期，格式:yyyy-MM-dd，即上传excel日期
     */
    private String settleDate;
	
	public String getSettlementCounterpartyId() {
		return settlementCounterpartyId;
	}

	public void setSettlementCounterpartyId(String settlementCounterpartyId) {
		this.settlementCounterpartyId = settlementCounterpartyId;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	
}

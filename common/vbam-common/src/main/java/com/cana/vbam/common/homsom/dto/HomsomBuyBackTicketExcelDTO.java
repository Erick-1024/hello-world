package com.cana.vbam.common.homsom.dto;

public class HomsomBuyBackTicketExcelDTO extends HomsomSettlementTicketDTO {

	private static final long serialVersionUID = -2555196118060490056L;

	/**
     *交易对手回购表ID
     */
    private String buybackCounterpartyId;
	
	public String getBuybackCounterpartyId() {
		return buybackCounterpartyId;
	}

	public void setBuybackCounterpartyId(String buybackCounterpartyId) {
		this.buybackCounterpartyId = buybackCounterpartyId;
	}

}

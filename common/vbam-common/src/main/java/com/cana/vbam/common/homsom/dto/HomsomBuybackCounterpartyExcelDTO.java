package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class HomsomBuybackCounterpartyExcelDTO implements Serializable {

	/**
     *交易对手名称，即单位客户名称
     */
    private String counterpartyName;

    private String totalSettlementAmount;
    
    /**
     *初始值为0，确认回购后为所有已回购客票的（贷款本金＋应还利息＋应还逾期费）
     */
    private String actualBuybackAmount;

	private static final long serialVersionUID = -6572377387996581449L;

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getTotalSettlementAmount() {
		return totalSettlementAmount;
	}

	public void setTotalSettlementAmount(String totalSettlementAmount) {
		this.totalSettlementAmount = totalSettlementAmount;
	}

	public String getActualBuybackAmount() {
		return actualBuybackAmount;
	}

	public void setActualBuybackAmount(String actualBuybackAmount) {
		this.actualBuybackAmount = actualBuybackAmount;
	}

}

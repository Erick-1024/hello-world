package com.cana.vbam.common.homsom.dto;

import java.io.Serializable;

public class HomsomSettlementCounterpartyExcelDTO implements Serializable {

    /**
     *交易对手名称，即单位客户名称
     */
    private String counterpartyName;

    /**
     *应核销总金额＝sum(提现金额+利息)
     */
    private String settleAmount;

    private String totalSettlementAmount;
    
    /**
     *初始值为0，核销时财务填写的到账总金额，即实际核销总金额＝已核销的（贷款本金＋应还利息＋应还逾期费）＋应退总金额
     */
    private String actualSettleAmount;

    /**
     *应退总金额
     */
    private String refundAmount;

    /**
     *应回购总金额＝未核销的（贷款本金＋应还利息？＋应还逾期费？）
     */
    private String buybackAmount;

	private static final long serialVersionUID = 8862626003787279164L;

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}

	public String getTotalSettlementAmount() {
		return totalSettlementAmount;
	}

	public void setTotalSettlementAmount(String totalSettlementAmount) {
		this.totalSettlementAmount = totalSettlementAmount;
	}

	public String getActualSettleAmount() {
		return actualSettleAmount;
	}

	public void setActualSettleAmount(String actualSettleAmount) {
		this.actualSettleAmount = actualSettleAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getBuybackAmount() {
		return buybackAmount;
	}

	public void setBuybackAmount(String buybackAmount) {
		this.buybackAmount = buybackAmount;
	}

}

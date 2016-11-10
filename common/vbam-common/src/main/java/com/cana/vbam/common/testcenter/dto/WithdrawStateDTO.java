package com.cana.vbam.common.testcenter.dto;

import java.io.Serializable;

import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.cana.vbam.common.bankgate.enums.TradeStatusFlag;

public class WithdrawStateDTO implements Serializable{
	
	private static final long serialVersionUID = -3956272239029541732L;

	private TradeStatusResultDTO tradeStatusResultDTO;
	
	private TradeStatusFlag tradeStatusFlag;

	public TradeStatusResultDTO getTradeStatusResultDTO() {
		return tradeStatusResultDTO;
	}

	public void setTradeStatusResultDTO(TradeStatusResultDTO tradeStatusResultDTO) {
		this.tradeStatusResultDTO = tradeStatusResultDTO;
	}

	public TradeStatusFlag getTradeStatusFlag() {
		return tradeStatusFlag;
	}

	public void setTradeStatusFlag(TradeStatusFlag tradeStatusFlag) {
		this.tradeStatusFlag = tradeStatusFlag;
	}
	
	
}

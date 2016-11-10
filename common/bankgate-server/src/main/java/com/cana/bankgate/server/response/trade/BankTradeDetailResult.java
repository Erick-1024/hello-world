/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.response.trade;

import java.io.Serializable;
import java.util.List;

import com.cana.bankgate.server.response.BankBaseResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author XuMeng
 *
 */
@XStreamAlias("stream")
public class BankTradeDetailResult extends BankBaseResult implements Serializable {

	private static final long serialVersionUID = -29920535230749194L;

	@XStreamAlias("list")
	private List<BankTradeDetailData> bankTradeDetailDatas; // 查询结果

	public List<BankTradeDetailData> getBankTradeDetailDatas() {
		return bankTradeDetailDatas;
	}

	public void setBankTradeDetailDatas(List<BankTradeDetailData> bankTradeDetailDatas) {
		this.bankTradeDetailDatas = bankTradeDetailDatas;
	}

}

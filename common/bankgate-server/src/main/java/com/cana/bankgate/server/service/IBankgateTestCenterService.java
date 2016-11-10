/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.server.service;

import com.cana.bankgate.server.enums.TransferFundType;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBalanceQueryDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBaseDTO;
import com.cana.vbam.common.bankgate.dto.request.TradeStatusQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;

/**
 * 测试中心网关打转实现
 * 
 * @author XuMeng
 *
 */
public interface IBankgateTestCenterService {

	/**
	 * 判断是不是生产环境
	 * @return
	 */
	public boolean isProdEnv();

	/**
	 * 查询账户余额
	 */
	public BankAccountBalanceResultDTO queryAccountBalance(BankAccountBalanceQueryDTO queryDTO);

	/**
	 * BF：转账；BG：解冻；BH：解冻支付；BR：冻结；BS：支付冻结 都属于强制转账<br>
	 */
	public <T extends BankAccountBaseDTO> BankBaseResultDTO transferFundForce(TransferFundType type, String gateSeq,
			T dto);
	
	public TradeStatusResultDTO queryTradeStatusResult(TradeStatusQueryDTO queryDTO);

}

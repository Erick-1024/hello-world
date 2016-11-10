package com.cana.yundaex.service;

import java.util.List;

import com.cana.vbam.common.credit.enums.CreditTransferStatus;
import com.cana.yundaex.dao.po.YundaexCreditTransfer;


/**
 * 韵达项目 放款实体卡记录接口
 * @author guguanggong
 *
 */
public interface IYundaexCreditTradeService {

	/**
	 * 获得所有状态处于提现中的授信交易记录
	 * 
	 * @return
	 */
	public List<YundaexCreditTransfer> getHandlingCreditTrade();
	
	/**
	 * 更新授信转账状态
	 * @param yundaexCreditTransfer
	 * @param creditTransferStatus 最新的放款状态
	 */
	public void updateCreditTransferStatus(YundaexCreditTransfer yundaexCreditTransfer, CreditTransferStatus creditTransferStatus);

}

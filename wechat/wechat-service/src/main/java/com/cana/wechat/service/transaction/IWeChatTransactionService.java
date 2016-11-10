package com.cana.wechat.service.transaction;

import java.util.List;

import com.cana.vbam.common.wechat.account.TradeRecordRequest;
import com.cana.vbam.common.wechat.account.TradeRecordResponse;
import com.cana.vbam.common.wechat.repayment.LoanInfoRequest;
import com.cana.vbam.common.wechat.repayment.LoanInfoResponse;

public interface IWeChatTransactionService {

	/**
	 * yihong.tang
     * 查询 流水明细
     * @param request
     */
    public List<TradeRecordResponse> queryTradeRecords(TradeRecordRequest request);
    
    /**
     * yihong.tang
	 * 查询 融资信息
	 * @param request
	 */
	public List<LoanInfoResponse> queryLoanInfos(LoanInfoRequest request);

}
package com.cana.wechat.dao.mapper;

import java.util.List;

import com.cana.vbam.common.wechat.account.TradeRecordResponse;
import com.cana.vbam.common.wechat.account.TradeRecordRequest;

public interface TradeRecordCustomMapper {
	
    int queryTradeRecordRowNumber(TradeRecordRequest request);
    
    List<TradeRecordResponse> find(TradeRecordRequest request);
}

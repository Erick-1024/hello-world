package com.cana.wechat.service.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cana.vbam.common.wechat.account.TradeRecordResponse;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.util.MoneyUtil;

@Component
public class WeChatConverter {
    
    /**
     * yihong.tang
     * 转换 流水明细
     * @param tradeRecordResponses
     */
    public void convertTradeRecordResponses(List<TradeRecordResponse> tradeRecordResponses) {
    	if (tradeRecordResponses == null || tradeRecordResponses.isEmpty())
    		tradeRecordResponses = Lists.newArrayList();
    	
    	for(TradeRecordResponse tradeRecordResponse : tradeRecordResponses){
    		if(tradeRecordResponse.getTradeType()!=null)
    			tradeRecordResponse.setTradeTypeDesc(tradeRecordResponse.getTradeType().getDesc());
    		
    		tradeRecordResponse.setAmountStr(MoneyUtil.cent2Yuan(tradeRecordResponse.getAmount() == null ? 0 : tradeRecordResponse.getAmount()));
    		
    		if(tradeRecordResponse.getStatus()!=null)
    			tradeRecordResponse.setStatusDesc(tradeRecordResponse.getStatus().getDesc());
    	}
    }

}

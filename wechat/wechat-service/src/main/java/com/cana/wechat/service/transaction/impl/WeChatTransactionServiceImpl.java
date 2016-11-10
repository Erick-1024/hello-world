package com.cana.wechat.service.transaction.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.vbam.common.wechat.account.TradeRecordRequest;
import com.cana.vbam.common.wechat.account.TradeRecordResponse;
import com.cana.vbam.common.wechat.repayment.LoanInfoRequest;
import com.cana.vbam.common.wechat.repayment.LoanInfoResponse;
import com.cana.wechat.dao.mapper.TradeRecordCustomMapper;
import com.cana.wechat.service.converter.WeChatConverter;
import com.cana.wechat.service.transaction.IWeChatTransactionService;

@Service
public class WeChatTransactionServiceImpl implements IWeChatTransactionService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private TradeRecordCustomMapper tradeRecordCustomMapper;
	
	@Resource
	private WeChatConverter weChatConverter;
	
	@Override
	public List<TradeRecordResponse> queryTradeRecords(TradeRecordRequest request) {
		//根据currentTradeRecordId得到offset
		if(StringUtils.isNotBlank(request.getCurrentTradeRecordId()))
			request.setOffset(tradeRecordCustomMapper.queryTradeRecordRowNumber(request));
		//得到list
		List<TradeRecordResponse> tradeRecordResponses = tradeRecordCustomMapper.find(request);
		//转换list
		weChatConverter.convertTradeRecordResponses(tradeRecordResponses);
		return tradeRecordResponses;
	}

	@Override
	public List<LoanInfoResponse> queryLoanInfos(LoanInfoRequest request) {
		
		return null;
	}
}

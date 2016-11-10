/**
 * Copyright © 2016-2029 Cana. All rights reserved.
 */
package com.cana.bankgate.test.trade;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.cana.bankgate.api.BankgateApi;
import com.cana.bankgate.server.request.fund.TradeStatusQuery;
import com.cana.bankgate.server.response.fund.TradeStatusResult;
import com.cana.bankgate.server.utils.HttpUtil;
import com.cana.vbam.common.bankgate.dto.request.BankTradeDetailQueryDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailResultDTO;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.FundBizType;
import com.google.gson.Gson;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml" })
public class BankTradeDetailQueryTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private BankgateApi api;

	@Test
	public void testQueryDeposit() throws Exception {
		BankTradeDetailQueryDTO queryDTO = new BankTradeDetailQueryDTO();
//		queryDTO.setAccountNo("3110210010051130118");
		queryDTO.setAccountNo("3110210005501061334");
		DateTime start = DateTime.now().minusDays(1180);
		DateTime end = DateTime.now();
		queryDTO.setStartDate(DateTimeUtil.date8(start));
		queryDTO.setEndDate(DateTimeUtil.date8(end)); // 银行测试服务器的时间比实际时间提前了105天
		BankTradeDetailResultDTO result = api.queryBankTradeDetailNonLogin(queryDTO);
		System.out.println(new Gson().toJson(result));
	}
	
	@Test
	public void testQueryTradeStatus() {
		TradeStatusQuery query = new TradeStatusQuery(BankBizType.query_trade_status);
	    query.setGateSeq("16060812155200909");
	    query.setBankUserName("knzcgl");
	    query.setFundBizType(FundBizType.transfer_fund);
		TradeStatusResult result = HttpUtil.request(query, TradeStatusResult.class);
		System.out.println(new Gson().toJson(result));
	}
}

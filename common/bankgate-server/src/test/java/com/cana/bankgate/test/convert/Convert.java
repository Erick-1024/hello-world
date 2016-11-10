package com.cana.bankgate.test.convert;

import org.junit.Test;

import com.cana.bankgate.server.request.fund.TradeStatusQuery;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.FundBizType;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Convert {

	@Test
	public void convertTest(){
		TradeStatusQuery query = new TradeStatusQuery(BankBizType.query_trade_status);
	    query.setGateSeq("111");
	    query.setBankUserName("222");
	    query.setFundBizType(FundBizType.transfer_fund);
//	    query.setAsss("2132131");
	    XStream xst = new XStream(new DomDriver());
	    xst.autodetectAnnotations(true);
	    System.out.println(xst.toXML(query));
	}
}

package test;

import org.junit.Test;

import com.cana.vbam.common.wechat.account.TradeRecordRequest;
import com.google.gson.Gson;
import com.travelzen.framework.net.http.HttpTookit;

public class ControllerTest {
	@Test
	public void TestGetTradeRecord() throws Exception {
		TradeRecordRequest request = new TradeRecordRequest();
		request.setPageSize(10);
		request.setCurrentTradeRecordId("16110913415312871");
		request.setCustomerId("cana-user");
		
		// 发送请求，获取响应
		System.out.println("请求：" + new Gson().toJson(request));
		String responseBodyText = HttpTookit.doPostJson("http://localhost:8380//wechat-openapi/wechat/account/tradeRecord", request);
		System.out.println("返回：" + responseBodyText);
		
	}
}

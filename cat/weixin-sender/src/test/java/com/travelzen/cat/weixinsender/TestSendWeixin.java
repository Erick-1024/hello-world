package com.travelzen.cat.weixinsender;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.common.base.Charsets;

public class TestSendWeixin {

	@Test
	public void test() throws Exception{
		DefaultHttpClient httpclient = new DefaultHttpClient(); 
		HttpResponse response = null;
		HttpEntity entity = null;
		HttpPost httpost = new HttpPost("http://10.3.41.145:12580"); // 引号中的参数是：servlet的地址
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("touser", "jiaxin.qian"));
		nvps.add(new BasicNameValuePair("corpid", "wxec0a68958ce8c541"));
		nvps.add(new BasicNameValuePair("corpsecret", "7MUCtQLPQ_KwyUER3YDbWIEekXXg4DyxCHymALAe4JQnUWqszbXrzhME0lzR8ryH"));
		nvps.add(new BasicNameValuePair("agentid", "1"));
		nvps.add(new BasicNameValuePair("content", "test"));
		nvps.add(new BasicNameValuePair("safe", "0"));
		nvps.add(new BasicNameValuePair("successAck", "success"));
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Charsets.UTF_8)); 
		response = httpclient.execute(httpost); // 执行
		entity = response.getEntity(); // 返回服务器响应
		try {
			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine()); // 服务器返回状态
			System.out.println("----------------------------------------");
			String responseString = null;
			if (response.getEntity() != null) {
				responseString = EntityUtils.toString(response.getEntity());
				System.out.println(responseString); // 打印出服务器响应的HTML代码
			}
		} finally {
			if (entity != null)
				entity.consumeContent(); // release connection gracefully
		}
		System.out.println("Login form get: " + response.getStatusLine());
		if (entity != null) {
			entity.consumeContent();
		}

	}

}

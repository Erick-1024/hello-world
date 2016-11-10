package com.travelzen.cat.mailsender;

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

public class TestSendMail {

	@Test
	public void test() throws Exception{
		DefaultHttpClient httpclient = new DefaultHttpClient(); 
		HttpResponse response = null;
		HttpEntity entity = null;
		HttpPost httpost = new HttpPost("http://192.168.160.19:12280"); // 引号中的参数是：servlet的地址
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("to", "shui.ren@travelzen.com"));
		nvps.add(new BasicNameValuePair("from", "biz.b2b@travelzen.com"));
		nvps.add(new BasicNameValuePair("username", "biz.b2b"));
		nvps.add(new BasicNameValuePair("password", "Fac723eb"));
		nvps.add(new BasicNameValuePair("subject", "邮件报警测试"));
		nvps.add(new BasicNameValuePair("content", "just test"));
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

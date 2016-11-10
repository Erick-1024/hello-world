package com.travelzen.framework.net.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleHttpClient {

	private static Logger logger = LoggerFactory.getLogger(SimpleHttpClient.class);

	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

	private static CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();

	public static String get(String url, List<NameValuePair> nameValuePairs) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < nameValuePairs.size(); i++) {
			buf.append(i == 0 ? "?" : "&");
			try {
				buf.append(nameValuePairs.get(i).getName()).append("=")
						.append(URLEncoder.encode(nameValuePairs.get(i).getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error("URL{}编码时发生错误", url, e);
			}
		}
		return getString(url + buf.toString());
	}

	public static String get(String url, NameValuePair[] nameValuePairs) {
		return get(url, Arrays.asList(nameValuePairs));
	}

	private static String getString(String url) {
		String result = null;
		HttpGet getMethod = new HttpGet(url);
		getMethod.setHeader("Content-Encoding", "UTF-8");
		try {
			HttpResponse response = client.execute(getMethod);
			result = EntityUtils.toString(response.getEntity());
			EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
			logger.error("请求URL{}时发生错误", url, e);
		}

		return result;
	}

	public static String post(String url, NameValuePair[] nameValuePairs) {
		return post(url, Arrays.asList(nameValuePairs));
	}

	public static String post(String url, List<NameValuePair> nameValuePairs) {
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = client.execute(httpPost);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
			logger.error("请求URL{}时发生错误", url, e);
		}
		return result;
	}

}

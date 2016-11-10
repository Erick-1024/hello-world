package com.travelzen.framework.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class HttpTookit {

	private static Logger logger = LoggerFactory.getLogger(HttpTookit.class);

	private volatile static HttpClient httpClient;
	private static final String CHARSET_NAME = "UTF-8";

	public static String doPostJson(String url, Object body) {
		HttpMethod method = getPostJsonMethod(url, body);
		return doPost(url, method);
	}
	
	public static String doPostForm(String url, Map<String, String> params) {
		HttpMethod method = getPostFormMethod(url, params);
		return doPost(url, method);
	}
	
	private static String doPost(String url, HttpMethod method) {
		logger.info("开始请求:{}", url);
		StringBuffer response = new StringBuffer();
		try {
			int responseCode = getClient().executeMethod(method);
			if (HttpStatus.SC_OK == responseCode) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), CHARSET_NAME));
				String line;
				while ((line = reader.readLine()) != null)
					response.append(line);
				reader.close();
			} else {
				logger.warn("执行HTTP Post请求" + url + "时，返回码为：" + String.valueOf(responseCode));
				return response.append(responseCode).toString();
			}
		} catch (IOException e) {
			logger.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} catch (Throwable thr) {
			logger.error("执行HTTP Post请求" + url + "时，发生异常！", thr);
		}finally {
			method.releaseConnection();
			logger.info("结束请求，并释放连接");
		}
		return response.toString();
	}
	
	private static HttpMethod getPostFormMethod(String url, Map<String, String> params) {
		PostMethod method = new PostMethod(url);
		if (params != null && params.size() > 0) {
			NameValuePair[] valueParms = new NameValuePair[params.size()];
			int i = 0;
			for (String key : params.keySet()) 
				valueParms[i++] = new NameValuePair(key, params.get(key));
			method.setRequestBody(valueParms);
		}
		return method;
	}
	
	private static HttpMethod getPostJsonMethod(String url, Object body) {
		PostMethod method = new PostMethod(url);
		RequestEntity entity = null;
		try {
			entity = new StringRequestEntity(new Gson().toJson(body), "application/json", CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			
		}
		method.setRequestEntity(entity);
		return method;
	}
	
	private static HttpClient getClient() {
		if (httpClient == null) {
			synchronized (HttpTookit.class) {
				if (httpClient == null) {
					HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
					HttpClientParams params = new HttpClientParams();
					params.setContentCharset(CHARSET_NAME);
					httpClient = new HttpClient(params, httpConnectionManager);
				}
			}
		}
		return httpClient;
	}
	
	public static String doGetJson(String url, List<NameValuePair> params) {
		HttpMethod method = getFormMethod(url, params);
		return doGet(url, method);
	}
	
	private static String doGet(String url, HttpMethod method) {
		byte[] body = null;
		int status = 0;
		String responseBodyText = StringUtils.EMPTY;
		try {
			status = getClient().executeMethod(method);
			body = method.getResponseBody();
			if (status == HttpServletResponse.SC_OK) {
				responseBodyText =  new String(body, CHARSET_NAME);
			} else {
				logger.warn("执行HTTP get请求" + url + "时，返回码为：" + String.valueOf(status));
				responseBodyText = String.valueOf(status);
			}
			
		} catch (HttpException e) {
			logger.error("执行HTTP get请求" + url + "时，发生异常！", e);
		} catch (IOException e) {
			logger.error("执行HTTP get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
			logger.info("结束请求，并释放连接");
		}
		return responseBodyText;
	}
	
	private static HttpMethod getFormMethod(String url, List<NameValuePair> params) {
		GetMethod method = new GetMethod(url);
		if (CollectionUtils.isNotEmpty(params))
			method.setQueryString(params.toArray(new NameValuePair[params.size()]));
		return method;
	}
	
}
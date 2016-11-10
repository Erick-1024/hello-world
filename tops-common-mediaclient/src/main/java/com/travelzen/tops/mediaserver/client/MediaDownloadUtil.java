/**
 * author: Simon Lee
 * Date  : Aug 26, 2013
 */
package com.travelzen.tops.mediaserver.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;

public class MediaDownloadUtil {
	private static String configFilePath = "properties/media-client.properties";

	public static void download(String mediaId, String savePath) throws IOException, URISyntaxException {
		String downloadURI = TopsConfReader.getConfContent(configFilePath, "media.client.downloadURL", ConfScope.R);
		StringBuilder url = new StringBuilder(downloadURI);
		String filename = savePath;
		if (savePath.contains("/")) {
			filename = savePath.substring(savePath.lastIndexOf("/") + 1);
		}

		if (!downloadURI.endsWith("?")) {
			url.append("?");
		}
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		params.add(new BasicNameValuePair("mediaType", "download"));
		params.add(new BasicNameValuePair("mediaImageId", mediaId));
		params.add(new BasicNameValuePair("mediaName", filename));
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url.append(paramString);
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet();
		httpGet.setURI(new URI(url.toString()));
		HttpResponse response = client.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 404) {
			throw new IOException();
		}
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		OutputStream output = new FileOutputStream(savePath);
		IOUtils.copy(content, output);
		IOUtils.closeQuietly(content);
		IOUtils.closeQuietly(output);
	}

	/**
	 * 下载文件
	 *
	 * @param mediaId
	 * @param os
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void download(String mediaId, OutputStream os) throws IOException, URISyntaxException {
		String downloadURI = TopsConfReader.getConfContent(configFilePath, "media.client.downloadURL", ConfScope.R);
		StringBuilder url = new StringBuilder(downloadURI);
		if (!downloadURI.endsWith("?")) {
			url.append("?");
		}
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		params.add(new BasicNameValuePair("mediaType", "download"));
		params.add(new BasicNameValuePair("mediaImageId", mediaId));
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url.append(paramString);
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		HttpGet httpGet = new HttpGet();
		httpGet.setURI(new URI(url.toString()));
		HttpResponse response = client.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 404) {
			throw new IOException();
		}
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		IOUtils.copy(content, os);
		IOUtils.closeQuietly(content);
		IOUtils.closeQuietly(os);
	}

	/**
	 * 下载文件
	 *
	 * @param mediaId
	 * @param os
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void downloadContract(String mediaId, OutputStream os) throws IOException, URISyntaxException {
		String downloadURI = TopsConfReader.getConfContent(configFilePath, "media.client.downloadURL", ConfScope.R);
		StringBuilder url = new StringBuilder(downloadURI);
		if (!downloadURI.endsWith("?")) {
			url.append("?");
		}
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		params.add(new BasicNameValuePair("mediaType", "contract"));
		params.add(new BasicNameValuePair("mediaImageId", mediaId));
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url.append(paramString);
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		HttpGet httpGet = new HttpGet();
		httpGet.setURI(new URI(url.toString()));
		HttpResponse response = client.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 404) {
			throw new IOException();
		}
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		IOUtils.copy(content, os);
		IOUtils.closeQuietly(content);
		IOUtils.closeQuietly(os);
	}

}

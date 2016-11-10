package com.travelzen.tops.mediaserver.client;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.core.dict.MediaState;
import com.travelzen.framework.picture.TZPhotoUtil;
import com.travelzen.framework.util.HashUtils;

public class MediaClient {

	private static Logger LOG = LoggerFactory.getLogger(MediaClient.class);

	private static HClient client;

	public MediaClient() {
		client = new HClient();
	}

	public MediaClient(String ip) {
		client = new HClient();
		setHost(ip, 8080, "/tops-mediaserver/uploadImageService");
	}

	public void setHost(String ip, int port, String url) {
		client.setHost(ip, port, url);
	}
	
	public void setTarget(String url){
		client.setTarget(url);
	}

	public String uploadPhoto(String filename, String mediaType) {
		String mediaStringId = filename.replaceAll("/", "");
		if (mediaStringId.length() < 7) {
			return null;
		}
		mediaStringId = mediaStringId.substring(mediaStringId.length() - 7, mediaStringId.length() - 1);
		String mediaId = "" + HashUtils.murmurHash2(mediaStringId);
		uploadPhoto(filename, mediaId, mediaType);
		return mediaId;
	}

	public boolean uploadPhoto(String filename, String mediaId, String mediaType) {

		File file = new File(filename);
		if (!file.exists()) {
			return false;
		}
		client.setHeader("Media-Name", file.getName());
		client.setHeader("Media-Type", mediaType);
		client.setHeader("Media-Id", mediaId);
		client.setHeader("Media-Cmd", "add");

		client.uploadFile(file);
		if (!client.executePost()) {
			LOG.error("post to media server failed. [{}]", filename);
			return false;
		}
		client.clearFile();
		String status = client.getStatus();
		if (MediaState.OK.toString().equals(status)) {
			client.close();
			return true;
		}
		client.close();
		return false;
	}


	public boolean uploadNoticeTemplate(byte[] b, String docId, String mediaType, String fileName) throws IOException {
		if (b == null || b.length == 0) {
			return false;
		}
		if (fileName != null) {
			client.setHeader("Media-Name", URLEncoder.encode(fileName, "UTF-8"));
		} else {
			client.setHeader("Media-Name", "");
		}
		client.setHeader("Media-Type", mediaType);
		client.setHeader("Media-Id", docId);
		client.setHeader("Media-Cmd", "add");

		client.setByteArr(b);
		if (!client.executePost()) {
			LOG.error("post to media server failed.");
			return false;
		}
		client.clearFile();
		String status = client.getStatus();
		if (MediaState.OK.toString().equals(status)) {
			client.close();
			return true;
		}
		client.close();
		return false;
	}

	public boolean uploadPhoto(byte[] b, String mediaId, String mediaType, String fileName) throws IOException {
		if (b == null || b.length == 0) {
			return false;
		}
		if (fileName != null) {
			client.setHeader("Media-Name", URLEncoder.encode(fileName, "UTF-8"));
		} else {
			client.setHeader("Media-Name", "");
		}
		client.setHeader("Media-Type", mediaType);
		client.setHeader("Media-Id", mediaId);
		client.setHeader("Media-Cmd", "add");

		client.setByteArr(b);
		if (!client.executePost()) {
			LOG.error("post to media server failed.");
			return false;
		}
		client.clearFile();
		String status = client.getStatus();
		if (status.equals(MediaState.OK.toString())) {
			client.close();
			return true;
		}
		client.close();
		return false;
	}

	/**
	 * 上传裁剪的图片 裁剪（正方形）
	 *
	 * @param b
	 * @param mediaId
	 * @param mediaType
	 * @return
	 * @throws IOException
	 */
	public boolean uploadPhotoWithSquareWidth(byte[] b, String mediaId, String mediaType, int width) throws IOException {
		b = TZPhotoUtil.reduceSquareImage(b, "jpg", width);
		return uploadPhoto(b, mediaId, mediaType, null);
	}

	/**
	 * 上传 缩放 指定宽度
	 *
	 * @param id
	 * @param filename
	 * @param mediaType
	 * @return
	 */
	public boolean uploadPhotoWithWidth(byte[] b, String mediaId, String mediaType, int width) throws IOException {
		b = TZPhotoUtil.reduceImageWithWidth(b, "jpg", width);
		return uploadPhoto(b, mediaId, mediaType, null);
	}

	/**
	 * 上传　缩放　指定高度
	 *
	 * @param id
	 * @param filename
	 * @param mediaType
	 * @return
	 */
	public boolean uploadPhotoWithHeight(byte[] b, String mediaId, String mediaType, int height) throws IOException  {
		b = TZPhotoUtil.reduceImageWithHeight(b, "jpg", height);
		return uploadPhoto(b, mediaId, mediaType, null);
	}

	/**
	 * 删除文件
	 * @param mediaId
	 * @param mediaType
	 * @return
	 * @throws IOException
	 */
	public boolean removeFile(String mediaId, String mediaType) throws IOException {
		if(StringUtils.isBlank(mediaId)){
			return false;
		}
		client.setHeader("Media-Type", mediaType);
		client.setHeader("Media-Id", mediaId);
		client.setHeader("Media-Cmd", "delete");

		if (!client.executePost()) {
			LOG.error("post to media server failed.");
			return false;
		}
		client.clearFile();
		String status = client.getStatus();
		if (status.equals(MediaState.OK.toString())) {
			client.close();
			return true;
		}
		client.close();
		return false;
	}
}
